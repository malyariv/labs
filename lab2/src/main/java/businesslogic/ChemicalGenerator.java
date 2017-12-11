package businesslogic;
/**
 * The class {@code ChemicalGenerator} implements the factory method pattern.
 * It generates {@code ChemicalFactoryImpl1} or {@code ChemicalFactoryImpl2}
 * depending on number of arguments.
 */
import interfaces.*;

public class ChemicalGenerator {
    /** a factory to be generated*/
    private Factory factory=null;

    /**
     * Returns the {@code ChemicalObserver} object depending on arguments.
     */
    public ChemicalObserver getChemical(Object... args){
        if (args.length==1) {
            factory=new ChemicalFactoryImpl1();
        }
        else {
            factory=new ChemicalFactoryImpl2();
        }
        return factory.getChemical(args);
    };
}
