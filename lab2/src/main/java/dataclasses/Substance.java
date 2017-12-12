package dataclasses;

/**
 * The class {@code Substance}, implementing interface {@code IChemicalIObserver},
 * is a model of a chemical reagent. It contains methods simulating
 * response of the reagent upon different exposures.
 */
import businesslogic.*;
import interfaces.*;
import java.util.List;

public class Substance implements IChemicalIObserver {
    /** a chemical formula of a chemical*/
    private String formula;
    /** a current state of a chemical, i.e., solid, solution */
    private AState currentAState;
    /** object implementing interface {@code IReactionAdapter}
     * provides receiving information about chemical reactions*/
    private IReactionAdapter IReactionAdapter =new ReactionAdapterFile();
    /** indicates the class of a chemical, i.e., acid, salt*/
    private Marker marker;

    /**
     * Constructs a <code>Substance</code>.
     */
    public Substance(String formula, Marker marker) {
        this.formula = formula;
        this.marker = marker;
        setState(marker);
    }

    /** sets the marker value and calls the method setState()*/
    public void setMarker(Marker marker) {
        this.marker = marker;
        setState(marker);
    }

    /** sets the currentAState value depending on marker value */
    private void setState(Marker marker){
        switch (marker){
            case WATER: currentAState =new Water(); break;
            case ACID: currentAState =new Acid(); break;
            case ACIDSOLUTION: currentAState =new AcidSolution(); break;
            case SOLUTION: currentAState =new Solution(); break;
            default: currentAState =new Solid();
        }
    }
    /**
     * Constructs a new <code>Substance</code> object using another.
     */
    public Substance(Substance substance) {
        this.formula = substance.getFormula();
        this.marker = substance.getMarker();
        setState(this.marker);
    }

    /**
     * Implementation of method of interface {@code IChemicable}.
     */
    @Override
    public String getFormula() {
        return formula;
    }
    /**
     * Implementation of method of interface {@code IChemicable}.
     */
    @Override
    public Marker getMarker() {
        return marker;
    }
    /**
     * Implementation of method of interface {@code IChemicable}.
     */
    @Override
    public String getState() {
        return currentAState.toString();
    }
    /**
     * Implementation of method of interface {@code IChemicalIObserver}.
     */
    @Override
    public void heating(IObserverable obs) {
        currentAState.heating(obs);
    }
    /**
     * Implementation of method of interface {@code IChemicalIObserver}.
     */
    @Override
    public void dissolve(IObserverable obs, List<IChemicalIObserver> reagents) {
        currentAState.dissolve(obs, reagents);
    }

    /**
     * Implementation of method of interface {@code IChemicalIObserver}.
     */
    public void react(IObserverable obs, List<IChemicalIObserver> reagents){
        List<String> products=null;
        for (int i = 0; i < reagents.size(); i++) {
            IChemicalIObserver c = reagents.get(i);
            if (formula.equals(c.getFormula())) continue;
            if (!currentAState.check(c)) continue;
            products = IReactionAdapter.getProducts(formula, c.getFormula());
            if (products == null) continue;
            for (String s : products) {
                obs.addChemical(s);
            }
            obs.removeChemical(this);
            obs.removeChemical(c);
        }
    }
   /**
     * Implementation of method of interface {@code IObserver}.
     */
    @Override
    public void update(IObserverable obs) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Substance)) return false;
        Substance substance = (Substance) o;
        if (!formula.equals(substance.formula)) return false;
        return marker == substance.marker;
    }

    @Override
    public int hashCode() {
        int result = formula.hashCode();
        result = 31 * result + marker.hashCode();
        return result;
    }


//_____________________________________________________________________
    /**
     * The inner class {@code Solid}, extending abstract class {@code AState},
     * is a model of a solid chemical reagent. It contains methods simulating
     * response of the reagent upon different exposures.
     */
    private class Solid extends AState {
        /**
         * Overrides the method of superclass {@code AState}.
         */
        @Override
        public boolean check(IChemicalIObserver c) {
            if (c.getState().equals("Solid")) return false;
            return true;
        }


        @Override
            public String toString() {
            return "Solid";
        }
        }


    /**
     * The inner class {@code Water}, extending abstract class {@code AState},
     * is a model of water. It contains methods simulating
     * response of the reagent upon different exposures.
     */
    private class Water extends AState {
        /**
         * Overrides the method of superclass {@code AState}.
         */
        @Override
        public void heating(IObserverable obs) {
            System.out.println("All water was evaporated.");
            obs.removeChemical(Substance.this);
        }
        /**
         * Overrides the method of superclass {@code AState}.
         */
        @Override
        public void dissolve(IObserverable obs, List<IChemicalIObserver> reagents) {
            for (int i=0;i<reagents.size();i++){
                IChemicalIObserver c=reagents.get(i);
                if (formula.equals(c.getFormula())) continue;
                if (c.getState().equals("Solution")) {
                    obs.removeChemical(Substance.this);
                    continue;
                }
                List<String>products = IReactionAdapter.getProducts(formula, c.getFormula());
                if (products == null) continue;
                for (String s : products) {
                    obs.addChemical(s);
                }
                obs.removeChemical(Substance.this);
                obs.removeChemical(c);
            }
        }

        @Override
        public String toString() {
            return "Solvent";
        }
    }


    /**
     * The inner class {@code Solution}, extending abstract class {@code AState},
     * is a model of an aqueous solution of a chemical reagent.
     * It contains methods simulating response of the reagent upon different exposures.
     */
    private class Solution extends AState {
        /**
         * Overrides the method of superclass {@code AState}.
         */
        @Override
        public void heating(IObserverable obs) {
            System.out.println("You can observe the growth of "+formula+" crystals");
            obs.removeChemical(Substance.this);
            obs.addChemical(formula);
        }

        /**
         * Overrides the method of superclass {@code AState}.
         */
        @Override
        public void dissolve(IObserverable obs, List<IChemicalIObserver> reagents) {
            for (int i=0;i<reagents.size();i++){
                IChemicalIObserver c=reagents.get(i);
                if (formula.equals(c.getFormula())) continue;
                if (c.getState().equals("Solvent")||(c.getState().equals("Solution"))) continue;
                List<String>products = IReactionAdapter.getProducts("H2O", c.getFormula());
                if (products == null) continue;
                for (String s : products) {
                    obs.addChemical(s);
                }
                obs.removeChemical(c);
            }
        }

        @Override
        public String toString() {
            return "Solution";
        }
    }
    /**
     * The inner class {@code AcidSolution}, extending abstract class {@code AState},
     * is a model of a diluted acid. It contains methods
     * simulating response of the reagent upon different exposures.
     */
    private class AcidSolution extends AState {
        /**
         * Overrides the method of superclass {@code AState}.
         */
        @Override
        public void heating(IObserverable obs) {
            System.out.println(formula+" becomes concentrated.");
        }
        /**
         * Overrides the method of superclass {@code AState}.
         */
        @Override
        public void dissolve(IObserverable obs, List<IChemicalIObserver> reagents) {
            new Solution().dissolve(obs,reagents);
        }

        @Override
        public String toString() {
            return "Solution";
        }
    }
    /**
     * The inner class {@code Acid}, extending abstract class {@code AState},
     * is a model of an acid. It contains methods simulating response of the reagent
     * upon different exposures.
     */
    private class Acid extends AState {
        @Override
        public String toString() {
            return "Acid";
        }
    }
}
