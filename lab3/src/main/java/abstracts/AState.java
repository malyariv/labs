package abstracts;

/**
 * The class {@code AState} is an abstract class with a single method add(T t)
 * which will be implemented by inheritors.
 */
public abstract class AState<T> {
    public abstract boolean add(T t);
}
