package interfaces;
/**
 * The interface {@code IFactory} is a GoF patterns. Here, it allows generation of chemicals
 */
public interface IFactory {
    /**
     * Generates chemical using some arguments.
     */
    IChemicalIObserver getChemical(Object... args);
}
