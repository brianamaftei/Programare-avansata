package Compulsory;

/**
 * Company class
 */
public class Company implements Node, Comparable<Company> {
    private String name;

    public Company(String name) {
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
    public int compareTo(Company other) {
        if (other instanceof Company) return this.name.compareTo(other.name);
        else return -1;
    }

    @Override
    public String toString() {
        return name;
    }
}
