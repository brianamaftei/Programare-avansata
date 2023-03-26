package Homework;

public class Main {

    public static void main(String[] args) {

        Problem problem = new Problem(22, 23, 70);
        problem.printTheGraph();
        problem.printsTheStudentsWithLowerPreferences();
        problem.solveTheGreedy();
        problem.getMaximumCardinality();

    }
}