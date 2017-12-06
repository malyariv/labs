package businesslogic;

import data.*;
import interfaces.Chemicals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ChemFactory {
    public Chemicals getSalt(Ions... ions){
        return new Salt(ions);
    }
    public Chemicals getSalt(){
        Ions positive=null;
        Ions negative=null;
        while (positive==null||negative==null) {
            Ions ion=randomIon();
            if (ion.getCharge()>0&&ion!=Ions.H&&positive==null){
                positive=ion;
            }
            if (ion.getCharge()<0&&ion!=Ions.OH&&negative==null){
                negative=ion;
            }
        }
        return new Salt(positive,negative);
    }
    public Chemicals getAlkali(Ions i){
        return new Alkali(i);
    }
    public Chemicals getAlkali(){
        Ions positive=null;
        while (positive==null) {
            Ions ion=randomIon();
            if (ion.getCharge()>0&&ion!=Ions.H){
                positive=ion;
            }
        }
        return new Alkali(positive);
    }
    public Chemicals getAcid(Ions i){
        return new Acid(i);
    }
    public Chemicals getAcid(){
        Ions negative=null;
        while (negative==null) {
            Ions ion=randomIon();
            if (ion.getCharge()<0&&ion!=Ions.OH){
                negative=ion;
            }
        }
        return new Acid(negative);
    }
    public Chemicals getWater(){
        return new Water();
    }
    private Ions randomIon(){
        Random random=new Random();
        int value=random.nextInt(Ions.values().length);
        Ions ion=null;
        for (Ions i : Ions.values()) {
            if (i.ordinal()==value) {
                ion=i;
                break;
            }
        }
        return ion;
    }
    public List<Chemicals> getReagents(Chemicals need){
        List<Chemicals> list=new ArrayList<>();
        list.add(getAcid(need.getNegativeIon()));
        list.add(getAlkali(need.getPositiveIon()));
        list.add(getWater());
        while (true){
            Chemicals alkali=getAlkali();
            if (!list.contains(alkali)) {
                list.add(alkali);
                break;
            }
        }
        while (true){
            Chemicals acid=getAcid();
            if (!list.contains(acid)) {
                list.add(acid);
                break;
            }
        }
        Collections.shuffle(list);
        return list;
    }
}
