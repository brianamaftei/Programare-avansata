package Lab3.Homework;

import Lab3.Compulsory.Company;

public class Main {
    public static void main(String[] args) {

        Network network = new Network();
        Designer d1 = new Designer("Jafar", new Date(23, 4, 2002), "Framer");
        Designer d2 = new Designer("Sultan", new Date(22, 3, 1965), "Framer");
        Programmer p1 = new Programmer("Aladdin", new Date(13, 12, 1991), "C++");
        Programmer p2 = new Programmer("Jasmine", new Date(15, 1, 1994), "Java");
        Company c1 = new Company("Amazon");

        network.addNode(p1);
        network.addNode(p2);
        network.addNode(d1);
        network.addNode(d2);
        network.addNode(c1);

        d1.addRelationship(p1, "best-friends");
        p2.addRelationship(p1, "marriage");
        d1.addRelationship(p2, "enemies");
        d2.addRelationship(d1, "strangers");
        d2.addRelationship(p1, "enemies");
        d1.addRelationship(d2, "strangers");
        p1.addRelationship(d2, "friends");
        p1.addRelationship(p2, "boss");

        System.out.println(network);
        network.sort();
        System.out.println(network);
    }

}