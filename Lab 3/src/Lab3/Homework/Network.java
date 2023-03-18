package Lab3.Homework;

import Lab3.Compulsory.Node;
import Lab3.Compulsory.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Network {
    private List<Node> nodes = new ArrayList<>();
    public Network() {
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }


    public void addNode(Node node) {
        nodes.add(node);
    }

    /**
     * In the first part we calculate in contor the number of the vertexes which start from the given node and in duplicate
     * we stock the ones that ends in the given node
     * Moving on we increase the counter by going through the all nodes and finding vertexes starting from them and ending in the given node
     * After that we eliminate the duplicate vertexes from the contor
     * @param node the given node
     * @return returns the number of connections between a node and his neighbours without duplicates
     */

    public int importanceOfANode(Node node) {

        Map<Node, String> thisNodeRelationships = node.getRelationships();
        int counter = 0;
        int duplicate = 0;
        for (var element : thisNodeRelationships.keySet()) {
            counter++;
            for (var entryKey : element.getRelationships().keySet()) {
                if (entryKey.equals(node)) duplicate++;
            }
        }

        for (var node1 : nodes) {
            for (var element : node1.getRelationships().keySet()) {
                if (element.equals(node)) counter++;
            }
        }
        return counter - duplicate;

    }

    /**
     * The method sorts the nodes from the array list by their importance, by calling the method importanceOfANode
     */

    public void sort() {
        this.nodes.sort((A, B) -> {
            int importanceA = importanceOfANode(A);
            int importanceB = importanceOfANode(B);
            return Integer.compare(importanceB, importanceA);
        });
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        response.append("Network:\n");
        for (Node node : nodes) {
            response.append(node);
            response.append(" importance ");
            response.append(importanceOfANode(node));
            response.append('\n');

        }
        return response.toString();
    }

}
