package interfaces;

/**
 * The interface {@code IObserver} allows receiving information
 * from interface {@code IObserverable}
 */

public interface IObserver {
    /**
     * Generates a response to action.
     */
    void update(IObserverable obs);
}
