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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int compareTo(Person other) {
        if (other instanceof Person) return this.name.compareTo(other.name);
        else return -1;
    }

    public void addRelationship(Node node, String value) {
        relationships.put(node, value);
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Map<Node, String> getRelationships() {
        return relationships;
    }

    public void setRelationships(Map<Node, String> relationships) {
        this.relationships = relationships;
    }

    /**
     *
     * @return returns a string that is the concatenation of all the nodes in this person's relationships
     */

    public String printRelationships() {
        String response = "";
        if (!relationships.isEmpty()) response = response + ", relationships=";
        else response = response + ", no relationship";

        for (var entry : relationships.entrySet()) {
            response = response + " '";
            response = response + entry.getKey().getName();
            response = response + " - " + entry.getValue() + "'";
        }
        return response;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", birthDate=" + birthDate + printRelationships() + '}';
    }

    public boolean equals(Object obj) {

        if (!(obj instanceof Person)) {
            return false;
        }

        Person other = (Person) obj;
        return this.name.equals(other.name) && this.getClass() == other.getClass();
    }


}
