package Lab3.Compulsory;

import java.util.Collections;
import java.util.Map;

/**
 * Company class
 */
public record Company(String name) implements Node, Comparable<Company> {

    @Override
    public int compareTo(Company other) {
        if (other != null){
            return this.name.compareTo(other.name);
        }
        else return -1;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Map<Node, String> getRelationships() {
        return Collections.emptyMap();
    }
}
