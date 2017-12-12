package interfaces;
/**
 * The interface {@code IReactionAdapter} allows receiving information
 * about chemical reaction
 */
import dataclasses.ConnectionProblemException;

import java.util.List;

public interface IReactionAdapter {
    /**
     * Returns a list of products from reaction between two chemicals.
     */
    List<String> getProducts(String s1, String s2);
}
