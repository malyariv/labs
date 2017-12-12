package businesslogic;
/**
 * The class {@code ChemicalGenerator} implements the IFactory method pattern.
 * It generates {@code SolidsFactory} or {@code SolutionFactory}
 * depending on number of arguments.
 */
import interfaces.*;

public class ChemicalGenerator {
    /** a IFactory to be generated*/
    private IFactory IFactory =null;

    /**
     * Returns the {@code IChemicalIObserver} object depending on arguments.
     */
    public IChemicalIObserver getChemical(Object... args){
        if (args.length==1) {
            IFactory =new SolidsFactory();
        }
        else {
            IFactory =new SolutionFactory();
        }
        return IFactory.getChemical(args);
    };
}
