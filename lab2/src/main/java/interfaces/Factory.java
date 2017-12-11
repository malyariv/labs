package interfaces;
/**
 * The interface {@code Factory} is a GoF patterns. Here, it allows generation of chemicals
 */
public interface Factory {
    /**
     * Generates chemical using some arguments.
     */
    ChemicalObserver getChemical(Object... args);
}
