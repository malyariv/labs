package dataclasses;

import java.util.ArrayList;
import java.util.List;

public enum Elements {
    H(1.0),C(12.0), N(14.0),O(16.0), Na(23.0), Mg(24.3), Al(27.0),
    Si(28.1), P(31.0), S(32.1), Cl(35.4), K(39.1), Ca(40.1), Fe(55.8),
    Cu(63.5), Ag(107.9), Au(197), Pb(207.2);
    private double atomicWeight;

    Elements(double aw){
        atomicWeight=aw;
    }

    public double getAtomicWeight() {
        return atomicWeight;
    }

    public static Elements[] getElements(String formula){
        List<Elements> elements=new ArrayList<>();
        int i=0, amount=1, l=formula.length();
        char[] chars=formula.toCharArray();
        for (; i<l;i++){
            Elements el=null;
            String name=null;
            if ((i<l-1)&&Character.isUpperCase(chars[i])&&Character.isLowerCase(chars[i+1])) {
                name = new String(new char[]{chars[i], chars[i + 1]});
                i++;
            }
            else {
                name = new String(new char[]{chars[i]});
            }
            el=Elements.valueOf(name);
            if ((i<l-1)&&Character.isDigit(chars[i+1])) {
                amount=chars[i+1]-48;
                i++;
            }
            for (int j=1;j<=amount;j++){
                elements.add(el);
            }
            amount=1;
        }
        Elements[] elem=new Elements[elements.size()];
        elem=elements.toArray(elem);
        return elem;
    }
    public static double getMolarMass(Elements[] elements){
        double mass=0.0;
        for (Elements e:elements){
            mass+=e.atomicWeight;
        }
        return mass;
    }
}
