package interfaces;
/**
 * The interface {@code ChemicalObserver} extends {@code ChemicalObserver}
 * and allows operations with chemical reagents
 */
import java.util.List;

public interface ChemicalObserver extends Observer,Chemicable {
    /**
     * Informs chemical about heating.
     */
    void heating(Observerable obs);
    /**
     * Provides dissolution of reagents.
     */
    void dissolve(Observerable obs, List<ChemicalObserver> reagents);
    /**
     * Provides reaction between reagents.
     */
    void react(Observerable obs, List<ChemicalObserver> reagents);
}
