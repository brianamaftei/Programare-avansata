package org.example;

public class Project implements Comparable<Object>{

    private String name;

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object other) {
        if(other==null)
            throw new NullPointerException();
        if(!(other instanceof Project project))
            throw new ClassCastException("Incomparable objects");
        return this.name.compareTo(project.name);
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                '}';
    }
}
