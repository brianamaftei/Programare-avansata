package Compulsory;

import java.util.Objects;

public class Project implements Comparable<Object>, Node {

    private String name;
    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public int compareTo(Object other) {
        if (other == null) throw new NullPointerException();
        if (!(other instanceof Project project)) throw new ClassCastException("Incomparable objects");
        return this.name.compareTo(project.name);
    }

    @Override
    public String toString() {
        return "Project{" + "name='" + name + '\'' + '}';
    }

    @Override
    public String name() {
        return name;
    }

}
