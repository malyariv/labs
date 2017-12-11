package businesslogic;
/**
 * The class {@code TaskGenerator} generates a chemical compound
 * and a list of answers containing chemical reagents
 */
import dataclasses.Tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TaskGenerator {
    /**
     * Returns the formula of chemical compound to be synthesized
     */
    public String generateCompound() {
        Random random=new Random();
        int r=random.nextInt(Tasks.values().length);
        for (Tasks t:Tasks.values()){
            if(t.ordinal()==r)
                return ""+t;
        }
        return null;
    }
    /**
     * Returns a list of random chemical reagents
     * and ones for synthesis of a chemical with a formula @param s.
     */
    public List<String> generateElements(String s) {
        List<String> listAllComponents=Tasks.getAllComponents();
        List<String> list=new ArrayList<>();
        Tasks t=Tasks.valueOf(s);
        list.add("H2O");
        list.addAll(t.getComponents());
        Random random=new Random();
        int require=5-list.size();
        for (int i=0;i<require;){
            int r=random.nextInt(listAllComponents.size());
            String component=listAllComponents.get(r);
            if (!list.contains(component)) {
                list.add(component);
                i++;
            }
        }
        Collections.shuffle(list);
        return list;
    }
}
