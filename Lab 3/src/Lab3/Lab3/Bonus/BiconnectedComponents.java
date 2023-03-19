package Lab3.Bonus;

import Lab3.Compulsory.Node;

import java.util.*;

/**
 * The BiconnectedComponents class is used to find the biconnected components of a graph
 * Biconnected components are subgraphs of a graph that are connected and are not separated by the removal of any single vertex
 * The class uses the Depth-First Search algorithm to find the biconnected components of the graph
 */
public class BiconnectedComponents {
    private int timer;
    private final HashMap<Node, LinkedList<Node>> adjacencyList;
    private final Stack<Node> stack1;
    private final Stack<Node> stack2;
    private final HashMap<Node, Integer> firstSeen, lowSeen;
    private final HashMap<Node, Node> parents;

    private final ArrayList<HashSet<Node>> components;

    public BiconnectedComponents(Graph graph) {
        this.adjacencyList = graph.getAdjacencyList();
        this.parents = new HashMap<>();
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
        this.firstSeen = new HashMap<>();
        this.lowSeen = new HashMap<>();
        this.components = new ArrayList<>();
        this.components.add(new HashSet<>());
        initialise();
    }

    /**
     * The initialise() method initialises the hashmaps with default values and performs a DFS on each node of the graph
     */
    public void initialise() {
        timer = 0;
        for (var entry : adjacencyList.keySet()) {
            firstSeen.put(entry, -1);
            lowSeen.put(entry, -1);
            parents.put(entry, null);
        }

        for (var entry : adjacencyList.keySet()) {
            if (firstSeen.get(entry) == -1) {
                dfs(entry);

                if (!stack1.empty()) {
                    while (!stack1.empty()) {
                        Node start = stack1.pop();
                        Node end = stack2.pop();
                        components.get(components.size() - 1).add(start);
                        components.get(components.size() - 1).add(end);
                    }
                    components.add(new HashSet<>());
                }
            }
        }
    }

    /**
     * The dfs() method performs a DFS search on the graph and identifies the biconnected components
     * @param currentNode node from which dfs is running
     */
    private void dfs(Node currentNode) {
        timer++;
        firstSeen.put(currentNode, timer);
        lowSeen.put(currentNode, timer);
        int children = 0;
        LinkedList<Node> adjacencyNodes = adjacencyList.get(currentNode);
        for (Node adjacencyNode : adjacencyNodes) {
            if (firstSeen.get(adjacencyNode) == -1) {
                children++;
                parents.put(adjacencyNode, currentNode);
                stack1.push(currentNode);
                stack2.push(adjacencyNode);
                dfs(adjacencyNode);
                if (lowSeen.get(currentNode) > lowSeen.get(adjacencyNode))
                    lowSeen.put(currentNode, lowSeen.get(adjacencyNode));
                if ((firstSeen.get(currentNode) == 1 && children > 1) || (firstSeen.get(currentNode) > 1 && lowSeen.get(adjacencyNode) >= firstSeen.get(currentNode))) {
                    while (!stack1.peek().equals(currentNode) || !stack2.peek().equals(adjacencyNode)) {
                        Node start = stack1.pop();
                        Node end = stack2.pop();
                        components.get(components.size() - 1).add(start);
                        components.get(components.size() - 1).add(end);
                    }
                    Node start = stack1.pop();
                    Node end = stack2.pop();
                    components.get(components.size() - 1).add(start);
                    components.get(components.size() - 1).add(end);
                    components.add(new HashSet<>());
                }
            } else if (!adjacencyNode.equals(parents.get(currentNode)) && firstSeen.get(adjacencyNode) < firstSeen.get(currentNode)) {
                if (lowSeen.get(currentNode) > firstSeen.get(adjacencyNode))
                    lowSeen.put(currentNode, firstSeen.get(adjacencyNode));
                stack1.push(currentNode);
                stack2.push(adjacencyNode);
            }
        }
    }

    public ArrayList<HashSet<Node>> getComponents() {
        return components;
    }
}
