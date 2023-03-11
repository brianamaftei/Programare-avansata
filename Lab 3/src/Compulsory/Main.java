package Compulsory;

import java.util.*;

/**
 * Prints to the screen a list of nodes that was added
 */
public class Main {
    public static void main(String[] args) {

        List<Node> nodes = new ArrayList<>();
        Person p1 = new Person("Aladin");
        Company c1 = new Company("Amazon");

        nodes.add(p1);
        nodes.add(c1);

        System.out.println(nodes);

    }
}