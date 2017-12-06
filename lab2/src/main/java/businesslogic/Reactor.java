package businesslogic;

import data.*;
import interfaces.Chemicals;

import java.util.*;

public class Reactor <T extends Chemicals>{
    private List<T> reagents=new ArrayList<>();
    private ChemFactory factory=new ChemFactory();

    public void add(T t){
        if (reagents.contains(t)) {
            System.out.println(t.getFormula()+" has been already added!");
            return;
        }
        if (!reagents.isEmpty()) {
            Chemicals chem=reagents.get(0);
            if (chem.getPositiveIon() == Ions.H && t.getMarker() == Marker.ACID) {
                System.out.println("Solution is already acid! First clear reactor!");
                return;
            }
            if (chem.getNegativeIon() == Ions.OH && t.getMarker() == Marker.ALKALI) {
                System.out.println("First clear reactor!");
                return;
            }

            if (chem.getMarker()==Marker.SOLUTION&&chem.getNegativeIon()!=Ions.OH){
                System.out.println("Try another operation!");
                return;
            }
        }
        System.out.println(t.getFormula()+" was added into reactor.");
        reagents.add(t);
        Collections.sort(reagents, (o1,o2)->o1.getMarker().getPriority()-o2.getMarker().getPriority());
    }

    public void clear(){
        reagents.clear();
        System.out.println("Reactor is empty now.");
    }

    public void mix(){
        Marker m0=reagents.get(0).getMarker();
        Marker m1=reagents.get(1).getMarker();
        if(m0.getPriority()>=Marker.ALKALI.getPriority()) {
            System.out.println("Nothing happens");
            return;
        }
        if (m0==Marker.WATER){
            if (reagents.get(1).dissolve()){
                dissolution();
                return;
            }
            else {
                System.out.println("You can see a suspension of "+reagents.get(1).getFormula());
            }
        }
        if (m0==Marker.ACID&&m1==Marker.ALKALI){
            react();
            return;
        }
        if (m0==Marker.SOLUTION&&reagents.get(0).getNegativeIon()==Ions.OH&&m1==Marker.ACID){
            react();
            return;
        }
    }

    private void dissolution(){
        if (reagents.get(1).getMarker()==Marker.ACID){
            System.out.println(reagents.get(1).getFormula() +" acid was diluted.");
            reagents.remove(0);
            return;
        }
        T t= reagents.get(1);
        Chemicals solution=new Solution(t.getPositiveIon(),t.getNegativeIon());
        reagents.clear();
        reagents.add((T)solution);
        System.out.println("You have prepared an aqueous solution of "+t.getFormula());
    }

    private void react(){
        Ions pos=null;
        Ions neg=null;
        T t1=reagents.get(0);
        T t2=reagents.get(1);
        if (t1.getPositiveIon()==Ions.H){
            pos=t2.getPositiveIon();
            neg=t1.getNegativeIon();
        }
        else {
            pos=t1.getPositiveIon();
            neg=t2.getNegativeIon();
        }
        reagents.clear();
        Chemicals salt=new Salt(pos,neg);
        reagents.add((T) new Water());
        reagents.add((T) salt);
        if (salt.dissolve()) dissolution();
        else precipitation(salt);
    }

    public void evaporation(){
        T r0=reagents.get(0);
        if (reagents.size()==1){
            if (r0.getMarker()==Marker.WATER){
                reagents.clear();
                System.out.println("Reactor is empty now.");
                return;
            }
            else {
                Chemicals ch=null;
                if (r0.getNegativeIon()==Ions.OH) {
                    ch=factory.getAlkali(r0.getPositiveIon());
                }
                else {
                    ch=factory.getSalt(r0.getPositiveIon(),r0.getNegativeIon());
                }
                reagents.clear();
                reagents.add((T)ch);
                System.out.println("You can observe the growth of "+ch.getFormula()+" crystals after evaporation");
                return;
            }
        }
        else {
            reagents.remove(0);
            System.out.println("You can observe the growth of "+reagents.get(0).getFormula()+" crystals after evaporation");
            return;
        }

    }

    private void precipitation(Chemicals chemicals){
        System.out.println("You can observe the precipitation of "+chemicals.getFormula()+" crystals in water");
    }

    public int getReagentsAmount(){
        return reagents.size();
    }

    public List<T> getReagents() {
        return reagents;
    }
}
