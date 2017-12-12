package interfaces;

/**
 * The interface {@code IObserverable} allows informing
 * objects from extending interface {@code IChemicalIObserver}
 * and changing some fields
 */
import java.util.function.Consumer;

public interface IObserverable {
    /**
     * Adds a new item to the list of observers.
     */
    void addObserver(IChemicalIObserver o);
    /**
     * Removes the observer from the list of observers.
     */
    void removeObserver(IChemicalIObserver o);
    /**
     * Notifies all observers from the list of observers.
     */
    void notifyObservers(Consumer<IChemicalIObserver> c);
    /**
     * Adds a new item to the generation list of chemicals.
     */
    void addChemical(String chem);
    /**
     * Removes the chemical from the list of chemicals.
     */
    void removeChemical(IChemicalIObserver chem);

}
