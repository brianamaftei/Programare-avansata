package Lab3.Bonus;

import Lab3.Homework.Date;
import Lab3.Homework.Network;
import Lab3.Homework.Programmer;

public class Main {
    public static void main(String[] args) {

        Network network = new Network();
        Graph graph = new Graph();
        Programmer p1 = new Programmer("A", new Date(1, 3, 2002), "C++");
        Programmer p2 = new Programmer("B", new Date(4, 7, 1999), "C#");
        Programmer p3 = new Programmer("C", new Date(5, 5, 2003), "C");
        Programmer p4 = new Programmer("D", new Date(7, 5, 2003), "C");
        Programmer p5 = new Programmer("E", new Date(7, 5, 2003), "C");
        Programmer p6 = new Programmer("F", new Date(7, 5, 2003), "C");

        p1.addRelationship(p5, "sisters");
        p2.addRelationship(p3, "sisters");
        p3.addRelationship(p4, "sisters");
        p4.addRelationship(p2, "sisters");
        p4.addRelationship(p1, "sisters");
        p5.addRelationship(p6, "sisters");

        network.addNode(p1);
        network.addNode(p2);
        network.addNode(p3);
        network.addNode(p4);
        network.addNode(p5);
        network.addNode(p6);


        graph.addNetwork(network);
        System.out.println(graph);

        ArticulationPoints articulationPoints = new ArticulationPoints(graph);
        System.out.println(articulationPoints.getArticulationPoints());
        System.out.println();

        BiconnectedComponents biconnectedComponents = new BiconnectedComponents(graph);
        System.out.println(biconnectedComponents.getComponents());
    }
}
