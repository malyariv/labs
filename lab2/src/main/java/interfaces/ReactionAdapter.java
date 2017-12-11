package interfaces;
/**
 * The interface {@code ReactionAdapter} allows receiving information
 * about chemical reaction
 */
import java.util.List;

public interface ReactionAdapter {
    /**
     * Returns a list of products from reaction between two chemicals.
     */
    List<String> getProducts(String s1, String s2);
}
