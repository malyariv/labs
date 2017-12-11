package interfaces;

/**
 * The interface {@code Observerable} allows informing
 * objects from extending interface {@code ChemicalObserver}
 * and changing some fields
 */
import java.util.function.Consumer;

public interface Observerable {
    /**
     * Adds a new item to the list of observers.
     */
    void addObserver(ChemicalObserver o);
    /**
     * Removes the observer from the list of observers.
     */
    void removeObserver(ChemicalObserver o);
    /**
     * Notifies all observers from the list of observers.
     */
    void notifyObservers(Consumer<ChemicalObserver> c);
    /**
     * Adds a new item to the generation list of chemicals.
     */
    void addChemical(String chem);
    /**
     * Removes the chemical from the list of chemicals.
     */
    void removeChemical(ChemicalObserver chem);

}
