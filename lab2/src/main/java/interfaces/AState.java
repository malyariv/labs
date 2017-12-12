package interfaces;
/**
 * The abstract class {@code AState} is a GoF patterns. Here, it allows different
 * behaviour of chemicals upon different exposures.
 */
import java.util.List;

public abstract class AState {

    /** a state name*/
    private String name;

    protected AState(String name) {
        this.name = name;
    }

    /**
     * Generates a response to heating.
     */
    public void heating(IObserverable obs){};
    /**
     * Allows adding some conditions of chemical interaction.
     */
    public boolean check(IChemicalIObserver c){
        return true;
    };
    /**
     * Provides dissolution of reagents.
     */
    public void dissolve(IObserverable obs, List<IChemicalIObserver> reagents){};

    @Override
    public String toString() {
        return name;
    }
}
