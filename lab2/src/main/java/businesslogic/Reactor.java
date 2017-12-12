package businesslogic;

/**
 * The class {@code Reactor}, implementing interface {@code IObserverable},
 * is a container for objects implementing interface {@code IChemicalIObserver}.
 * This class is a model of chemical reactor where chemical interaction
 * between chemical reagents occurs.
 */

import dataclasses.ConnectionProblemException;
import dataclasses.Marker;
import dataclasses.QuitException;
import interfaces.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class Reactor<T extends IChemicalIObserver> implements IObserverable {
    /** the current list of chemical reagents in the reactor*/
    private List<IChemicalIObserver> reagents=new ArrayList<>();
    /** the current list of chemical reagents to be removed from the reactor*/
    private Set<IChemicalIObserver> removeList =new HashSet<>();
    /** the current list of chemical reagents to be synthesized in the reactor*/
    private Set<IChemicalIObserver> productsList =new HashSet<>();
    /** a factory which generates chemical reagents*/
    private ChemicalGenerator chemicalGenerator=new ChemicalGenerator();

    /**
     * Returns true if there is no reagents in the reactor,
     * i.e.,the current list of chemical reagents is empty.
     */
    public boolean isEmpty() {
        return reagents.isEmpty();
    }

    /**
     * Generates a chemical and puts it into the current list of reagents.
     */
    public void add(String s) throws ConnectionProblemException {
        IChemicalIObserver c= chemicalGenerator.getChemical(s);
        if (c==null) throw new ConnectionProblemException();
        if (reagents.contains(c)) return;
        addObserver(c);
        System.out.println(s+" was put into reactor");
    }
    /**
     * Removes all chemicals from the current list of reagents.
     */
    public void clear() {
        reagents.clear();
        System.out.println("Now reactor is empty.");
    }

    /**
     * Simulates an interaction between chemicals by sending them messages
     * and evoking corresponding methods.
     */
    public void mix() {
        notifyObservers(chemicalObserver
                -> chemicalObserver.dissolve(this, getReagents()));
        notifyObservers(chemicalObserver
                -> chemicalObserver.react(this, getReagents()));
    }
    /**
     * Simulates a heating process by sending messages to reagents
     * and evoking corresponding method.
     */
    public void heat() {
        notifyObservers(chemicalObserver -> chemicalObserver.heating(this));
    }
    /**
     * Removes reagent from the current list of reagents
     * which present in the current list of chemical reagents
     * to be removed from the reactor
     */
    private void remove() {
        for (IChemicalIObserver c:removeList){
            removeObserver(c);
        }
        removeList.clear();
    }
    /**
     * Adds reagents to the current list from the list of chemical reagents
     * to be synthesized in the reactor
     */
    private void reagentsRefresh(){
        for (IChemicalIObserver c:productsList){
            addObserver(c);
            switch (c.getMarker()){
                case SOLUTION:
                    System.out.println("An aqueous solution of "+c.getFormula()+" was prepared.");
                    break;
                case ACIDSOLUTION:
                    System.out.println("A diluted solution of "+c.getFormula()+" was prepared.");
                    break;
                default: System.out.println(c.getFormula()+" was synthesized.");
            }
        }
        productsList.clear();
    }
    /**
     * Returns all chemicals from the current list of reagents
     */
    public List<IChemicalIObserver> getReagents() {
        return new ArrayList<>(reagents);
    }
    /**
     * Prints all chemicals from the current list of reagents
     */
    public void showReagents() {
        System.out.println("\tTotal: "+reagents.size()+" reagents in reactor");
        for (IChemicalIObserver c:reagents){
            System.out.print("\t"+c.getFormula()+"("+c.getMarker()+")");
        }
        System.out.println();
    }
    /**
     * Implementation of method of interface {@code IObserverable}.
     */
    @Override
    public void addObserver(IChemicalIObserver o) {
        reagents.add(o);
    }
    /**
     * Implementation of method of interface {@code IObserverable}.
     */
    @Override
    public void removeObserver(IChemicalIObserver o) {
        reagents.remove(o);
    }
    /**
     * Implementation of method of interface {@code IObserverable}.
     */
    @Override
    public void notifyObservers(Consumer<IChemicalIObserver> cons) {
        int changes=0;
        do{
            for (IChemicalIObserver c:reagents){
                cons.accept(c);
            }
            changes=removeList.size()+productsList.size();
            if (!removeList.isEmpty()) {
                remove();
            }
            if (!productsList.isEmpty()) {
                reagentsRefresh();
            }
        }while (changes>0);
    }
    /**
     * Implementation of method of interface {@code IObserverable}.
     */
    @Override
    public void addChemical(String chem) {
        ChemicalGenerator generator=new ChemicalGenerator();
        IChemicalIObserver c=null;
        if (chem.endsWith("sol")){
            c= generator.getChemical(chem.substring(0,chem.length()-3), Marker.SOLUTION);
        }
        else {
            c= generator.getChemical(chem);
        }
        productsList.add(c);
    }
    /**
     * Implementation of method of interface {@code IObserverable}.
     */
    @Override
    public void removeChemical(IChemicalIObserver chem) {
        removeList.add(chem);
    }
}
