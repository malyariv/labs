package businesslogic;
/**
 * The class {@code SolutionFactory} generates {@code IChemicalIObserver} objects
 * using data from file.
 */
import dataclasses.*;
import interfaces.*;
import java.util.HashMap;
import java.util.Map;

public class SolutionFactory implements IFactory {
    /** a cache for chemicals*/
    private static Map<String,Substance> cacheSolutions=new HashMap<>();
    /**
     * Returns the {@code IChemicalIObserver} object.
     */
    public IChemicalIObserver getChemical(Object... args) {
        String s=(String) args[0];
        Marker marker=(Marker) args[1];
        SolidsFactory factory=new SolidsFactory();
        Substance substance=(Substance) factory.getChemical(s);
        if (substance.getMarker()==Marker.ACID&&marker==Marker.SOLUTION) marker=Marker.ACIDSOLUTION;
        substance.setMarker(marker);
        cacheSolutions.put(s,substance);
        return cacheSolutions.get(s);
    }
}
