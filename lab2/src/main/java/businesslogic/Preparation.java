package businesslogic;

import Interfaces.*;
import dataclasses.*;
import java.util.*;

public class Preparation implements IFPreparation {
    private List<IFChemicals> chemicals=new ArrayList<>();
    private List<TestPortion> chems=new ArrayList<>();


    private void getChemicals(Reaction r) {
        List<String> list=r.getReagents();
        for (String s:list) {
            IFChemicals c= ChemicalFactory.getChemical(s);
            chemicals.add(c);
        }
    }

    public List<TestPortion> getReagents(Reaction r){
        getChemicals(r);
        for (IFChemicals c:chemicals) {
            chems.add(new TestPortion(c,10));
        }
        return new ArrayList<>(chems);
    }
}
