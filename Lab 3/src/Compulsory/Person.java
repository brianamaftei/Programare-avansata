package Compulsory;

/**
 * Person class
 */
public class Person implements Node, Comparable<Person> {
    private String name;

    public Person(String name) {
        this.name = name;
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


    @Override
    public String toString() {
        return name;
    }

}
