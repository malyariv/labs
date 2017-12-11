package interfaces;
/**
 * The interface {@code State} is a GoF patterns. Here, it allows different
 * behaviour of chemicals upon different exposures.
 */
import java.util.List;

public interface State {
    /**
     * Generates a response to heating.
     */
    void heating(Observerable obs);
    /**
     * Allows adding some conditions of chemical interaction.
     */
    boolean check(ChemicalObserver c);
    /**
     * Provides dissolution of reagents.
     */
    void dissolve(Observerable obs, List<ChemicalObserver> reagents);
}
