package dataclasses;
/**
 * The enumeration {@code Tasks} is a set of tasks.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Tasks {
    NaCl("NaOH", "HCl"), KCl("KOH", "HCl"), CuCl2("Cu(OH)2", "HCl"),
    Na2CO3("NaOH", "H2CO3"), CuCO3("Cu(OH)2", "H2CO3");

    /** a list of components to synthesize the current chemical*/
    private List<String> components=new ArrayList<>();

    /**
     * Constructs a <code>Task</code>.
     */
    Tasks(String... comp){
        components= Arrays.asList(comp);
    }
    /**
     * Returns the list of components to synthesize the current chemical
     */
    public List<String> getComponents() {
        return new ArrayList<>(components);
    }
    /**
     * Returns the list of all components to synthesize all chemicals in the set
     */
    public static List<String> getAllComponents(){
        List<String> list=new ArrayList<>();
        for (Tasks t:Tasks.values()){
            list.addAll(t.getComponents());
        }
        return list;
    }
}
