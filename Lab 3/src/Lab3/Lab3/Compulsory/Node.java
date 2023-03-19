package Lab3.Compulsory;

import java.util.Map;

/**
 * Interface for Company and Person
 */
public interface Node {
    String name();

    Map<Node, String> getRelationships();
}
