package Lab3.Compulsory;

import Lab3.Homework.Date;

import java.util.HashMap;
import java.util.Map;

/**
 * Person class
 */
public class Person implements Node, Comparable<Person> {
    protected String name;
    protected Date birthDate;
    protected Map<Node, String> relationships = new HashMap<>();

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public String name() {
        return name;
    }


    @Override
    public int compareTo(Person other) {
        if (other != null) {
            return this.name.compareTo(other.name);
        }
        else return -1;
    }

    public void addRelationship(Node node, String value) {
        relationships.put(node, value);
    }

    public Map<Node, String> getRelationships() {
        return relationships;
    }

    /**
     * @return returns a string that is the concatenation of all the nodes in this person's relationships
     */

    public String printRelationships() {
        StringBuilder response = new StringBuilder();
        if (!relationships.isEmpty()) response.append(", relationships=");
        else response.append(", no relationship");

        for (var entry : relationships.entrySet()) {
            response.append(" '");
            response.append(entry.getKey().name());
            response.append(" - ").append(entry.getValue()).append("'");
        }
        return response.toString();
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", birthDate=" + birthDate + printRelationships() + '}';
    }

    public boolean equals(Object obj) {

        if (!(obj instanceof Person other)) {
            return false;
        }

        return this.name.equals(other.name) && this.getClass() == other.getClass();
    }


}
