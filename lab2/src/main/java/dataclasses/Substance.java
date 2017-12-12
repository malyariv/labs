package dataclasses;

/**
 * The class {@code Substance}, implementing interface {@code ChemicalObserver},
 * is a model of a chemical reagent. It contains methods simulating
 * response of the reagent upon different exposures.
 */
import businesslogic.*;
import interfaces.*;
import java.util.List;

public class Substance implements ChemicalObserver {
    /** a chemical formula of a chemical*/
    private String formula;
    /** a current state of a chemical, i.e., solid, solution */
    private State currentState;
    /** object implementing interface {@code ReactionAdapter}
     * provides receiving information about chemical reactions*/
    private ReactionAdapter reactionAdapter=new ReactionAdapterFile();
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

    /** sets the currentState value depending on marker value */
    private void setState(Marker marker){
        switch (marker){
            case WATER: currentState=new Water(); break;
            case ACID: currentState=new Acid(); break;
            case ACIDSOLUTION: currentState=new AcidSolution(); break;
            case SOLUTION: currentState=new Solution(); break;
            default: currentState=new Solid();
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
     * Implementation of method of interface {@code Chemicable}.
     */
    @Override
    public String getFormula() {
        return formula;
    }
    /**
     * Implementation of method of interface {@code Chemicable}.
     */
    @Override
    public Marker getMarker() {
        return marker;
    }
    /**
     * Implementation of method of interface {@code Chemicable}.
     */
    @Override
    public String getState() {
        return currentState.toString();
    }
    /**
     * Implementation of method of interface {@code ChemicalObserver}.
     */
    @Override
    public void heating(Observerable obs) {
        currentState.heating(obs);
    }
    /**
     * Implementation of method of interface {@code ChemicalObserver}.
     */
    @Override
    public void dissolve(Observerable obs, List<ChemicalObserver> reagents) {
        currentState.dissolve(obs, reagents);
    }

    /**
     * Implementation of method of interface {@code ChemicalObserver}.
     */
    public void react(Observerable obs, List<ChemicalObserver> reagents){
        List<String> products=null;
        for (int i = 0; i < reagents.size(); i++) {
            ChemicalObserver c = reagents.get(i);
            if (formula.equals(c.getFormula())) continue;
            if (!currentState.check(c)) continue;
            products = reactionAdapter.getProducts(formula, c.getFormula());
            if (products == null) continue;
            for (String s : products) {
                obs.addChemical(s);
            }
            obs.removeChemical(this);
            obs.removeChemical(c);
        }
    }
   /**
     * Implementation of method of interface {@code Observer}.
     */
    @Override
    public void update(Observerable obs) {
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
     * The inner class {@code Solid}, implementing interface {@code State},
     * is a model of a solid chemical reagent. It contains methods simulating
     * response of the reagent upon different exposures.
     */
    private class Solid implements State{
        /**
         * Implementation of method of interface {@code State}.
         */
        @Override
        public void heating(Observerable obs) {
        }
        /**
         * Implementation of method of interface {@code State}.
         */
        @Override
        public boolean check(ChemicalObserver c) {
            if (c.getState().equals("Solid")) return false;
            return true;
        }
        /**
         * Implementation of method of interface {@code State}.
         */
        @Override
        public void dissolve(Observerable obs, List<ChemicalObserver> reagents) {
        }

    @Override
        public String toString() {
            return "Solid";
        }
    }


    /**
     * The inner class {@code Water}, implementing interface {@code State},
     * is a model of water. It contains methods simulating
     * response of the reagent upon different exposures.
     */
    private class Water implements State{
        /**
         * Implementation of method of interface {@code State}.
         */
        @Override
        public void heating(Observerable obs) {
            System.out.println("All water was evaporated.");
            obs.removeChemical(Substance.this);
        }
        /**
         * Implementation of method of interface {@code State}.
         */
        @Override
        public void dissolve(Observerable obs, List<ChemicalObserver> reagents) {
            for (int i=0;i<reagents.size();i++){
                ChemicalObserver c=reagents.get(i);
                if (formula.equals(c.getFormula())) continue;
                if (c.getState().equals("Solution")) {
                    obs.removeChemical(Substance.this);
                    continue;
                }
                List<String>products = reactionAdapter.getProducts(formula, c.getFormula());
                if (products == null) continue;
                for (String s : products) {
                    obs.addChemical(s);
                }
                obs.removeChemical(Substance.this);
                obs.removeChemical(c);
            }
        }
        /**
         * Implementation of method of interface {@code State}.
         */
        @Override
        public boolean check(ChemicalObserver c) {
            return true;
        }

        @Override
        public String toString() {
            return "Solvent";
        }
    }


    /**
     * The inner class {@code Solution}, implementing interface {@code State},
     * is a model of an aqueous solution of a chemical reagent.
     * It contains methods simulating response of the reagent upon different exposures.
     */
    private class Solution implements State{
        /**
         * Implementation of method of interface {@code State}.
         */
        @Override
        public void heating(Observerable obs) {
            System.out.println("You can observe the growth of "+formula+" crystals");
            obs.removeChemical(Substance.this);
            obs.addChemical(formula);
        }
        /**
         * Implementation of method of interface {@code State}.
         */
        @Override
        public boolean check(ChemicalObserver c) {
            return true;
        }
        /**
         * Implementation of method of interface {@code State}.
         */
        @Override
        public void dissolve(Observerable obs, List<ChemicalObserver> reagents) {
            for (int i=0;i<reagents.size();i++){
                ChemicalObserver c=reagents.get(i);
                if (formula.equals(c.getFormula())) continue;
                if (c.getState().equals("Solvent")||(c.getState().equals("Solution"))) continue;
                List<String>products = reactionAdapter.getProducts("H2O", c.getFormula());
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
     * The inner class {@code AcidSolution}, implementing interface {@code State},
     * is a model of a diluted acid. It contains methods
     * simulating response of the reagent upon different exposures.
     */
    private class AcidSolution implements State{
        /**
         * Implementation of method of interface {@code State}.
         */
        @Override
        public void heating(Observerable obs) {
            System.out.println(formula+" becomes concentrated.");
        }
        /**
         * Implementation of method of interface {@code State}.
         */
        @Override
        public boolean check(ChemicalObserver c) {
            return true;
        }
        /**
         * Implementation of method of interface {@code State}.
         */
        @Override
        public void dissolve(Observerable obs, List<ChemicalObserver> reagents) {
            new Solution().dissolve(obs,reagents);
        }

        @Override
        public String toString() {
            return "Solution";
        }
    }
    /**
     * The inner class {@code Acid}, implementing interface {@code State},
     * is a model of an acid. It contains methods simulating response of the reagent
     * upon different exposures.
     */
    private class Acid implements State{
        /**
         * Implementation of method of interface {@code State}.
         */
        @Override
        public void heating(Observerable obs) {
        }
        /**
         * Implementation of method of interface {@code State}.
         */
        @Override
        public void dissolve(Observerable obs, List<ChemicalObserver> reagents) {
        }
        /**
         * Implementation of method of interface {@code State}.
         */
        @Override
        public boolean check(ChemicalObserver c) {
            return true;
        }

        @Override
        public String toString() {
            return "Acid";
        }
    }
}
