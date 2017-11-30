package businesslogic;

import Interfaces.*;
import dataclasses.*;
import java.util.*;

public class Reactor implements IFReactor {
    private List<TestPortion> reagents;
    private List<IFChemicals> products=new ArrayList<>();


    private void getProducts(Reaction reaction) {
        List<String> list=reaction.getProducts();
        for (int i=0;i<list.size();i++) {
            IFChemicals c= ChemicalFactory.getChemical(list.get(i));
            products.add(c);
        }
    }
    private double calculateMol(Reaction reaction) {
        Map<Double,String> mol=new HashMap<>();
        Integer[] coef=reaction.getReagentsCoeff();
        double minMol=1000000000000.0;
        for (int i=0;i<reagents.size();i++) {
            IFChemicals c=reagents.get(i).getChemical();
            double d;
            if (c.getState()==State.GAS) {
                d=1000;
            }
            else {
                d=10.0;
            }
            d=d/c.getMolarMass()/coef[i];
            if (c.getState()==State.LIQUID||c.getState()==State.GAS) d=d*c.getDensity();
            if (d<minMol) {
                minMol=d;
            }
            mol.put(d,c.getName());
        }
        return minMol;
    }
    @Override
    public List<TestPortion> execute(Reaction reaction, List<TestPortion> chem) {
        List<TestPortion> productsOut=new ArrayList<>();
        reagents=new ArrayList<>(chem);
        describeLeft(reaction);

        System.out.println("\n\tOperations");
        new Operations().execute(reaction.getCondition());

        getProducts(reaction);
        double mol=calculateMol(reaction)*reaction.getYield();
        Integer[] coeff=reaction.getProductsCoeff();
        for (int i=0;i<products.size();i++) {
            IFChemicals c=products.get(i);
            if (c.getState()==State.POWDER) {
                productsOut.add(new TestPortion<IFChemicals>(c, mol*coeff[i]*c.getMolarMass()));
            }
            else {
                productsOut.add(new TestPortion<IFChemicals>(c, mol*coeff[i]*c.getMolarMass()/c.getDensity()));
            }
        }
        describeRight(productsOut);
        return new ArrayList<>(productsOut);
    }

    private void describeLeft(Reaction reaction){
        System.out.println(reaction.getEquation());
        System.out.println("\tReagents");
        for (TestPortion c:reagents) {
            switch (c.getChemical().getState()) {
                case POWDER: System.out.println("We take "+ c.getAmount() +" mg of "+c.getName()+" powder."); break;
                case LIQUID: System.out.println("We take "+ c.getAmount() +" ml of "+c.getName()+" pure solution."); break;
                default: System.out.println("We perform in "+c.getName());
            }
        }
    }
    private void describeRight(List<TestPortion> list){
        System.out.println("\n\tProducts");
        StringBuilder sb=new StringBuilder("We get ");
        for (TestPortion t:list){
            IFChemicals c=t.getChemical();
            if (c.getState()==State.POWDER) {
                String s=String.format("%.2f", t.getAmount());
                sb.append(s);
                sb.append(" mg of ");
            }
            else {
                String s=String.format("%.2f", t.getAmount());
                sb.append(s);
                sb.append(" ml of ");
            }
            sb.append(c.getName());
            sb.append(" ");
            sb.append(c.getState());
            sb.append(".");
            System.out.println(sb);
            sb=new StringBuilder("We get ");
        }

    }

}
