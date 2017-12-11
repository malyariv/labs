package businesslogic;
/**
 * The class {@code ChemicalFactoryImpl2} generates {@code ChemicalObserver} objects
 * using data from file.
 */
import dataclasses.*;
import interfaces.*;
import java.util.HashMap;
import java.util.Map;

public class ChemicalFactoryImpl2 implements Factory{
    /** a cache for chemicals*/
    private static Map<String,Substance> cacheSolutions=new HashMap<>();
    /**
     * Returns the {@code ChemicalObserver} object.
     */
    public ChemicalObserver getChemical(Object... args) {
        String s=(String) args[0];
        Marker marker=(Marker) args[1];
        ChemicalFactoryImpl1 factory=new ChemicalFactoryImpl1();
        Substance substance=(Substance) factory.getChemical(s);
        if (substance.getMarker()==Marker.ACID&&marker==Marker.SOLUTION) marker=Marker.ACIDSOLUTION;
        substance.setMarker(marker);
        cacheSolutions.put(s,substance);
        return cacheSolutions.get(s);
    }
}
