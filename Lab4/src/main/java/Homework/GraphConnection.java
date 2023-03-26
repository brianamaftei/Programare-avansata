package Homework;

import Compulsory.Node;

import java.util.*;

/**
 * The GraphConnection class represents a graph connection between node
 */

public class GraphConnection {
    protected Map<Node, List<Node>> prefMap = new HashMap<>();

    public void setPrefMap(Map<Node, List<Node>> prefMap) {
        this.prefMap = prefMap;
    }

    public boolean existsNode(Node node) {
        return prefMap.containsKey(node);
    }

    public Map<Node, List<Node>> getPrefMap() {
        return prefMap;
    }

    public void addNode(Node node) {
        if (!existsNode(node)) {
            prefMap.put(node, new LinkedList<>());
        }
    }

    public void addDirectedEdge(Node start, Node end) {
        addNode(start);
        prefMap.get(start).add(end);
    }

    /**
     * Removes a node from the prefMap
     * Also removes all edges going to or from the node
     * @param node The node to remove.
     */
    public void removeNodeAndEdges(Node node) {
        prefMap.remove(node);
        for (var entry : prefMap.values()) {
            entry.remove(node);
        }
    }

    @Override
    public String toString() {
        return "GraphConnection{" +
                "prefMap=" + prefMap +
                '}';
    }

    public void printTheGraph() {
        System.out.println("The graph: ");
        StringBuilder str = new StringBuilder();
        for (var entry : prefMap.keySet()) {
            List<Node> nodes = prefMap.get(entry);
            str.append(entry.toString());
            str.append(": ");
            for (Node element : nodes) {
                str.append(element.toString());
                str.append(" ");
            }
            str.append("\n");
        }
        System.out.println(str);
        System.out.println();
    }
}

