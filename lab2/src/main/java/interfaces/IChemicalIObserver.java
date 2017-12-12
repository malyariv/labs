package interfaces;
/**
 * The interface {@code IChemicalIObserver} extends {@code IObserver} and {@code IChemicable}.
 * It allows operations with chemical reagents
 */
import java.util.List;

public interface IChemicalIObserver extends IObserver, IChemicable {
    /**
     * Informs chemical about heating.
     */
    void heating(IObserverable obs);
    /**
     * Provides dissolution of reagents.
     */
    void dissolve(IObserverable obs, List<IChemicalIObserver> reagents);
    /**
     * Provides reaction between reagents.
     */
    void react(IObserverable obs, List<IChemicalIObserver> reagents);
}
