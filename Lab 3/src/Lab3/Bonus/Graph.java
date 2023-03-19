package Lab3.Bonus;

import Lab3.Compulsory.Node;
import Lab3.Homework.Network;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {
    private final HashMap<Node, LinkedList<Node>> adjacency;

    public Graph() {
        this.adjacency = new HashMap<>();
    }

    public void addNetwork(Network network) {
        List<Node> nodes = network.getNodes();
        for (Node node : nodes) {
            addNode(node);
            Map<Node, String> relationships = node.getRelationships();
            for (var entry : relationships.entrySet()) {
                addEdge(node, entry.getKey());
            }
        }
    }

    private void addEdge(Node start, Node end) {
        addDirectedEdge(start, end);
        addDirectedEdge(end, start);
    }

    private void addDirectedEdge(Node start, Node end) {
        addNode(start);
        adjacency.get(start).add(end);
    }

    private boolean existsNode(Node node) {
        return adjacency.containsKey(node);
    }

    private void addNode(Node node) {
        if (!existsNode(node)) {
            adjacency.put(node, new LinkedList<>());
        }
    }

    public HashMap<Node, LinkedList<Node>> getAdjacencyList() {
        return adjacency;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (var entry : adjacency.keySet()) {
            LinkedList<Node> adjacencyNodes = adjacency.get(entry);
            str.append(entry.name());
            str.append(": ");
            for (Node adjacencyNode : adjacencyNodes) {
                str.append(adjacencyNode.name());
                str.append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }
}
