package org.example;

import java.util.stream.IntStream;
import java.util.*;

/**
 * We generate the students using IntStream
 * We then add the students to the linked list 'listOfStudents'
 * We generate in the same way the problems
 * But we add them in a tree set 'listOfProjects'
 */
public class Main {
    public static void main(String[] args) {
        var students = IntStream.rangeClosed(0, 3).mapToObj(i -> new Student("S" + i) ).toArray(Student[]::new);
        List<Student> listOfStudents = new LinkedList<>(Arrays.asList(students));
        Collections.sort(listOfStudents);

        var problems = IntStream.rangeClosed(0, 3).mapToObj(i -> new Project("S" + i) ).toArray(Project[]::new);
        TreeSet<Project> listOfProjects = new TreeSet<>();
        listOfProjects.addAll(Arrays.asList(problems));

        System.out.println(listOfStudents);
        System.out.println(listOfProjects);

    }
}