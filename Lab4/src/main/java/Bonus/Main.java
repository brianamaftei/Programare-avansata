package Bonus;

import Compulsory.Node;
import Homework.GraphConnection;
import Homework.Problem;
import org.jgrapht.Graph;
import org.jgrapht.alg.matching.DenseEdmondsMaximumCardinalityMatching;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.List;
import java.util.Map;

public class Main {
    /**
     * This method uses the graph storage format I've used (An adjacency list) and transforms it into the JGraphT format,
     * so it can be used in its algorithms.
     * @param problem The problem which gets transformed into the JGraphT format for a graph
     * @return the JGraphT graph format
     */
    public static Graph<Node, DefaultEdge> transformGraphFromProblemToJGraphT(Problem problem) {
        Graph<Node, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        Map<Node, List<Node>> adjacencyList = problem.getPrefMap();
        for (var entry : adjacencyList.keySet()) {
            graph.addVertex(entry);
        }
        for (var entry : adjacencyList.entrySet()) {
            for (var adjNode : entry.getValue()) {
                graph.addEdge(entry.getKey(), adjNode);
            }
        }
        return graph;
    }

    public static void main(String[] args) {
        var problem = new Problem(10, 10, 10);
        var graph = transformGraphFromProblemToJGraphT(problem);
        var algorithm = new DenseEdmondsMaximumCardinalityMatching<>(graph);
        System.out.println(algorithm.getMatching().getWeight());
    }
}
