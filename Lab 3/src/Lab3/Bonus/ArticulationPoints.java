package Lab3.Bonus;

import Lab3.Compulsory.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * <a href="https://cp-algorithms.com/graph/cutpoints.html#implementation">...</a>
 */
public class ArticulationPoints {
    private int timer;
    private final HashMap<Node, Boolean> visited;
    private final HashMap<Node, Integer> lowSeen;
    private final HashMap<Node, Integer> firstSeen;
    private final HashMap<Node, LinkedList<Node>> adjacencyList;
    private final HashSet<Node> articulationPoints;

    /**
     * Constructs a new ArticulationPoints object.
     * Initializes the variables and the data structures needed for the algorithm
     * @param graph The graph for which the articulation points are to be found
     */
    public ArticulationPoints(Graph graph) {
        this.timer = 0;
        this.visited = new HashMap<>();
        this.firstSeen = new HashMap<>();
        this.lowSeen = new HashMap<>();
        this.adjacencyList = graph.getAdjacencyList();
        this.articulationPoints = new HashSet<>();
        initialise();
    }

    /**
     * Initializes the variables and the data structures needed for the algorithm
     */
    public void initialise() {
        timer = 0;
        for (var entry : adjacencyList.keySet()) {
            visited.put(entry, false);
            firstSeen.put(entry, -1);
            lowSeen.put(entry, -1);
        }
        for (var entry : adjacencyList.keySet()) {
            if (!visited.get(entry)) {
                dfs(entry, null);
            }
        }
    }

    /**
     * Performs a depth-first search on the graph, starting from the given node
     * Keeps track of the visited nodes, the discovery times, and the lowest discovery time reachable from a node
     * Determines if a node is an articulation point
     *
     * @param current  The node currently being visited.
     * @param previous The previous node in the depth-first search.
     */
    private void dfs(Node current, Node previous) {
        int children = 0;
        visited.put(current, true);
        firstSeen.put(current, timer);
        lowSeen.put(current, timer);
        timer++;
        LinkedList<Node> adjacencyNodes = adjacencyList.get(current);
        for (Node adjacencyNode : adjacencyNodes) {
            if (!adjacencyNode.equals(previous)) {
                if (visited.get(adjacencyNode)) {
                    int minimum = Math.min(lowSeen.get(current), lowSeen.get(adjacencyNode));
                    lowSeen.put(current, minimum);
                } else {
                    dfs(adjacencyNode, current);
                    int minimum = Math.min(lowSeen.get(current), lowSeen.get(adjacencyNode));
                    lowSeen.put(current, minimum);
                    if (previous != null && lowSeen.get(adjacencyNode) >= firstSeen.get(current)) {
                        articulationPoints.add(current);
                    }
                    children++;
                }
            }
        }
        if (previous == null && children > 1) {
            articulationPoints.add(current);
        }
    }

    /**
     * Returns the set of articulation points found in the graph
     * @return The set of articulation points found in the graph
     */
    public HashSet<Node> getArticulationPoints() {
        return articulationPoints;
    }
}