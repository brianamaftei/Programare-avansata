package Homework;

import Compulsory.Node;
import Compulsory.Project;
import Compulsory.Student;
import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.min;

/**
 * The Problem class represents a problem of assigning students to projects, using a graph-based approach
 * The class provides methods for generating a random problem instance, finding students with lower preferences,
 * solving the problem using a greedy algorithm, and printing the resulting graph structure.
 */

public class Problem extends GraphConnection {

    private int maximumCardinality = 0;

    public Problem(int students, int projects, int checkOptions) {
        generateRandom(students, projects, checkOptions);
    }

    /**
     * Returns a list of students that have a number of preferences lower than the average number of preferences
     * @return the list of students with lower preferences
     */
    public List<Student> findsStudentsWithLowerPreferences() {
        double averagePreference;
        List<Student> result;
        averagePreference = prefMap.values().stream().mapToDouble(List::size).average().orElse(0.0);
        result = prefMap.keySet().stream().filter(s -> s instanceof Student).map(s -> (Student) s).filter(s -> prefMap.get(s).size() < averagePreference).collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     * Prints the list of students that have a number of preferences lower than the average number of preferences.
     */
    public void printsTheStudentsWithLowerPreferences() {
        System.out.println("The students that have a number of preferences lower than the average number of preferences: ");
        findsStudentsWithLowerPreferences().forEach(System.out::println);
        System.out.println();
    }

    /**
     * Adds a directed edge between the given student and project nodes.
     * @param student the student node
     * @param project the project node
     */

    public void traceStudentProjectEdge(Student student, Project project) {
        addDirectedEdge(student, project);
        addDirectedEdge(project, student);
    }

    /**
     * Generates a random problem instance with the given number of students, projects, and total options
     * @param students the number of students
     * @param projects the number of projects
     * @param totalOptions the total number of options to consider when assigning students to projects
     */
    public void generateRandom(int students, int projects, int totalOptions) {
        Faker faker = new Faker();
        var arrayOfStudents = IntStream.rangeClosed(0, students - 1).mapToObj(i -> new Student(faker.superhero().name())).toArray(Student[]::new);
        var arrayOfProjects = IntStream.rangeClosed(0, projects - 1).mapToObj(i -> new Project(faker.funnyName().name())).toArray(Project[]::new);
        int indexS;
        int indexP;

        for (int i = 0; i < totalOptions; i++) {
            indexS = min(students - 1, (int) ((students + projects) * Math.random()));
            indexP = min(projects - 1, (int) ((students + projects) * Math.random()));
            traceStudentProjectEdge(arrayOfStudents[indexS], arrayOfProjects[indexP]);
        }
    }

    /**
     * It starts by finding the student with the minimum number of preferences, and assigns that student to their highest preferred project
     * It then removes the assigned student and project from the prefMap, and repeats the process until no students are left without a project
     */
    public void solveTheGreedy() {
        System.out.println("The students and the project assign: ");
        Node thisNode = findNodeMinimumDegree();
        while (thisNode != null) {
            List<Node> options = prefMap.get(thisNode);
            if (!options.isEmpty()) {
                maximumCardinality++;
            }
            Node assignedNode = null;
            for (Node entry : options) {
                removeNodeAndEdges(entry);
                assignedNode = entry;
            }
            removeNodeAndEdges(thisNode);

            if (thisNode instanceof Student) {
                System.out.println(thisNode+ "-" + assignedNode);
            } else {
                System.out.println(assignedNode + "-" + thisNode);
            }

            thisNode = findNodeMinimumDegree();
        }
        System.out.println();
    }

    /**
     * This method finds the node with the minimum degree in the prefMap
     * @return the node with the minimum degree, or null if prefMap is empty
     */

    public Node findNodeMinimumDegree() {
        int minValue = -1;
        Map.Entry<Node, List<Node>> minNode = null;
        for (var entry : prefMap.entrySet()) {
            int currentSize = entry.getValue().size();
            if (minValue == -1 || currentSize < minValue) {
                minNode = entry;
                minValue = currentSize;
            }
        }
        if (minNode != null) {
            return minNode.getKey();
        }
        return null;
    }


    public int getMaximumCardinality() {
        return maximumCardinality;
    }

    public void setMaximumCardinality(int maximumCardinality) {
        this.maximumCardinality = maximumCardinality;
    }

    @Override
    public void printTheGraph() {
        super.printTheGraph();
    }
}
