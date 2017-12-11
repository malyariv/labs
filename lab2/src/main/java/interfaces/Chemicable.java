package interfaces;
/**
 * The interface {@code Chemicable} allows receiving information
 * about used chemical reagents
 */
import dataclasses.Marker;

public interface Chemicable {
    /**
     * Returns the chemical formula of a chemical reagent.
     */
    String getFormula();
    /**
     * Returns the chemical class of a chemical reagent (acid, salt, alkali,...).
     */
    Marker getMarker();
    /**
     * Returns the state of a chemical reagent (solid, solution,...).
     */
    String getState();

}
