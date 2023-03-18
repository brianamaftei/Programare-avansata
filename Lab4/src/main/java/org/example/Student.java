package org.example;

public class Student implements Comparable<Object>{
    private String name;

    public Student(String name) {
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
        if(!(other instanceof Student student))
            throw new ClassCastException("Incomparable objects");
        return this.name.compareTo(student.name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
