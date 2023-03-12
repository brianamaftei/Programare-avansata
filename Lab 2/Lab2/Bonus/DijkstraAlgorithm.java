package Lab2.Bonus;

import Lab2.Compulsory.Location;
import Lab2.Compulsory.Road;
import Lab2.Homework.Problem;

import java.util.PriorityQueue;

public class DijkstraAlgorithm extends Algorithm {
    private final int[] distance;
    private final Problem problem;

    /**
     * Constructor which runs the dijkstra algorithm
     * <a href="https://www.geeksforgeeks.org/dijkstras-algorithm-for-adjacency-list-representation-greedy-algo-8/">...</a>
     * @param start starting location for dijkstra
     * @param problem problem instance we are running it on
     */
    public DijkstraAlgorithm(Location start, Problem problem) {
        this.problem = problem;
        distance = new int[this.problem.getLocationsCount()];
        int i;
        for (i = 0; i < this.problem.getLocationsCount(); i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Location> queue = new PriorityQueue<>((n1, n2) -> distance[this.problem.getPositionOfLocation(n1)] - distance[this.problem.getPositionOfLocation(n2)]);
        distance[this.problem.getPositionOfLocation(start)] = 0;
        Location[] locations = this.problem.getLocations();
        for (i = 0; i < this.problem.getLocationsCount(); i++)
            if (locations[i].equals(start)) {
                queue.add(locations[i]);
            }
        Road[] roads = this.problem.getRoads();
        while (!queue.isEmpty()) {
            Location current = queue.poll();
            for (i = 0; i < this.problem.getLocationsCount(); i++)
                if (current.equals(roads[i].getStart())) {
                    Location neighbor = roads[i].getEnd();
                    int newDistance = distance[this.problem.getPositionOfLocation(current)] + roads[i].getLength();
                    if (newDistance < distance[this.problem.getPositionOfLocation(neighbor)]) {
                        distance[this.problem.getPositionOfLocation(neighbor)] = newDistance;
                        queue.remove(neighbor);
                        queue.add(neighbor);
                    }
                }
        }
    }

    /**
     * Overloading for the constructor, this one calculates the fastest path instead of the shortest path.
     * @param problem instance of the problem
     * @param start starting location
     */
    public DijkstraAlgorithm(Problem problem, Location start) {
        this.problem = problem;
        distance = new int[this.problem.getLocationsCount()];
        int i;
        for (i = 0; i < this.problem.getLocationsCount(); i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Location> queue = new PriorityQueue<>((n1, n2) -> distance[this.problem.getPositionOfLocation(n1)] - distance[this.problem.getPositionOfLocation(n2)]);
        distance[this.problem.getPositionOfLocation(start)] = 0;
        Location[] locations = this.problem.getLocations();
        for (i = 0; i < this.problem.getLocationsCount(); i++)
            if (locations[i].equals(start)) {
                queue.add(locations[i]);
            }
        Road[] roads = this.problem.getRoads();
        while (!queue.isEmpty()) {
            Location current = queue.poll();
            for (i = 0; i < this.problem.getLocationsCount(); i++)
                if (current.equals(roads[i].getStart())) {
                    Location neighbor = roads[i].getEnd();
                    int newDistance = distance[this.problem.getPositionOfLocation(current)] + roads[i].getLength() / roads[i].getSpeedLimit();
                    if (newDistance < distance[this.problem.getPositionOfLocation(neighbor)]) {
                        distance[this.problem.getPositionOfLocation(neighbor)] = newDistance;
                        queue.remove(neighbor);
                        queue.add(neighbor);
                    }
                }
        }
    }

    /**
     *
     * @param target destination location for dijkstra's algorithm
     * @return prints the shortest path / the fastest path for the target
     */
    public int printDistance(Location target) {
        return distance[problem.getPositionOfLocation(target)];
    }
}
