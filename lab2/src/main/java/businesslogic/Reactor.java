package businesslogic;



import dataclasses.Marker;
import interfaces.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class Reactor<T extends ChemicalObserver> implements Observerable{
    private List<ChemicalObserver> reagents=new ArrayList<>();
    private Set<ChemicalObserver> removeList =new HashSet<>();
    private Set<ChemicalObserver> productsList =new HashSet<>();
    private ChemicalGenerator chemicalGenerator=new ChemicalGenerator();


    public boolean isEmpty() {
        return reagents.isEmpty();
    }

    public void add(String s){
        ChemicalObserver c= chemicalGenerator.getChemical(s);
        if (reagents.contains(c)) return;
        addObserver(c);
        System.out.println(s+" was put into reactor");
    }

    public void clear() {
        reagents.clear();
        System.out.println("Now reactor is empty.");
    }

    public void mix() {
        notifyObservers(chemicalObserver
                -> chemicalObserver.dissolve(this, getReagents()));
        notifyObservers(chemicalObserver
                -> chemicalObserver.react(this, getReagents()));
    }

    public void heat() {
        notifyObservers(chemicalObserver -> chemicalObserver.heating(this));
    }

    @Override
    public void addObserver(ChemicalObserver o) {
        reagents.add(o);
    }

    @Override
    public void removeObserver(ChemicalObserver o) {
        reagents.remove(o);
    }

    @Override
    public void notifyObservers(Consumer<ChemicalObserver> cons) {
        int changes=0;
        do{
            for (ChemicalObserver c:reagents){
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

    private void remove() {
        for (ChemicalObserver c:removeList){
            removeObserver(c);
        }
        removeList.clear();
    }
    private void reagentsRefresh(){
        for (ChemicalObserver c:productsList){
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

    public List<ChemicalObserver> getReagents() {
        return new ArrayList<>(reagents);
    }

    public void showReagents() {
        for (ChemicalObserver c:reagents){
            System.out.print("\t"+c.getFormula()+"("+c.getMarker()+")");
        }
        System.out.println();
    }

    @Override
    public void addChemical(String chem) {
        ChemicalGenerator generator=new ChemicalGenerator();
        ChemicalObserver c=null;
        if (chem.endsWith("sol")){
            c= generator.getChemical(chem.substring(0,chem.length()-3), Marker.SOLUTION);
        }
        else {
            c= generator.getChemical(chem);
        }
        productsList.add(c);
    }

    @Override
    public void removeChemical(ChemicalObserver chem) {
        removeList.add(chem);
    }
}
