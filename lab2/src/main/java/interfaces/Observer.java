package interfaces;

/**
 * The interface {@code Observer} allows receiving information
 * from interface {@code Observerable}
 */

public interface Observer {
    /**
     * Generates a response to action.
     */
    void update(Observerable obs);
}
