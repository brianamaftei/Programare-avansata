package Lab2.Homework;

import Lab2.Compulsory.Location;
import Lab2.Compulsory.Road;

import java.util.LinkedList;

/**
 * Problem class contains methods used to print on the screen the solution of the requirements
 */

public class Problem {
    private Location[] locations;
    private Road[] roads;
    private int roadsCount, locationsCount;

    /**
     * It increments the number of roads and in the same time assigns the roads vector at that index with a given road
     *
     * @param road is a given road from the main class
     */
    public void addRoad(Road road) {
        roads[roadsCount++] = road;
    }

    /**
     * It increments the number of locations and assigns the locations vector at that index with a given road
     *
     * @param location is a given location from the main class
     */

    public void addLocation(Location location) {
        locations[locationsCount++] = location;
    }

    /**
     * euclideanDistance calculate the shortest distance between 2 locations
     *
     * @param road will be a road from the roads vector based on the requirements
     * @return the euclidean distance calculated with a mathematical formula
     */

    public double euclideanDistance(Road road) {
        double x1, x2, y1, y2, distance;
        x1 = road.getStart().getX();
        x2 = road.getEnd().getX();
        y1 = road.getStart().getY();
        y2 = road.getEnd().getY();
        distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        return distance;
    }

    /**
     * @param road will be a road from the roads vector based on the requirements
     * @return if the length of the road  is larger or equal with the euclidean distance returns true, otherwise false
     */
    public boolean isEuclideanDistance(Road road) {
        return euclideanDistance(road) <= road.getLength();
    }

    /**
     * Here the code goes through the locations and verify if it exists one identical with another
     * In the second for it starts from the next index to minimize the number of comparisons
     *
     * @return if all the locations are different returns true, false otherwise
     */

    public boolean isValid() {
        int i, j;
        for (i = 0; i < locationsCount; i++) {
            for (j = i + 1; j < locationsCount; j++) {
                if (locations[i].equals(locations[j])) {
                    return false;
                }
            }
        }

        for (i = 0; i < roadsCount; i++) {
            if (!isEuclideanDistance(roads[i])) {
                return false;
            }
            for (j = i + 1; j < roadsCount; j++) {
                if (roads[i].equals(roads[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public Problem(int roadsNumber, int locationsNumber) {
        this.locations = new Location[locationsNumber];
        this.roads = new Road[roadsNumber];
        this.roadsCount = 0;
        this.locationsCount = 0;
    }

    public Location[] getLocations() {
        return locations;
    }

    public void setLocations(Location[] locations) {
        this.locations = locations;
    }

    public Road[] getRoads() {
        return roads;
    }

    public void setRoads(Road[] roads) {
        this.roads = roads;
    }

    public int getRoadsCount() {
        return roadsCount;
    }

    public void setRoadsCount(int roadsCount) {
        this.roadsCount = roadsCount;
    }

    public int getLocationsCount() {
        return locationsCount;
    }

    public void setLocationsCount(int locationsCount) {
        this.locationsCount = locationsCount;
    }

    /**
     * @param location the location we are looking for in the vector
     * @return returns the position in the locations[] array of a specified location
     */
    public int getPositionOfLocation(Location location) {
        int i;
        for (i = 0; i < locationsCount; i++)
            if (location.equals(locations[i])) {
                return i;
            }
        return -1;
    }

    /**
     * @param start starting location for which we create an adjacency list
     * @return returns the adjacency list for the starting location. The list is stored as an array of roads.
     */
    public Road[] initializeAdjacencyList(Location start) {
        Road[] adjacencyList = new Road[roadsCount];
        int i, counter;
        counter = 0;
        for (i = 0; i < roadsCount; i++) {
            if (start.equals(roads[i].getStart())) {
                adjacencyList[counter] = roads[i];
                counter++;
            }
        }
        return adjacencyList;
    }

    /**
     * @param start starting location for a DFS
     * @param end   end location for the DFS
     * @return returns true if the two locations have a path connecting them, false if not
     */
    public boolean existsAPath(Location start, Location end) {
        boolean[] visited = new boolean[locationsCount];
        LinkedList<Location> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Location startLocation = queue.poll();
            if (startLocation.equals(end)) {
                return true;
            }

            Road[] adjacencyList = initializeAdjacencyList(startLocation);
            for (Road road : adjacencyList) {
                Location endLocation = road.getEnd();
                int position;
                position = getPositionOfLocation(endLocation);
                if (!visited[position]) {
                    queue.add(endLocation);
                    visited[position] = true;
                }
            }

        }

        return false;
    }

    /**
     * Prints the locations and the roads on the screen if they exist
     */
    public void print() {
        int i;
        if (locationsCount != 0) {
            System.out.println("Locations:");
            for (i = 0; i < locationsCount; i++)
                System.out.println((i + 1) + ". " + locations[i]);
        } else {
            System.out.println("There are no locations added");
        }

        if (roadsCount != 0) {
            System.out.println("Roads:");
            for (i = 0; i < roadsCount; i++)
                System.out.println((i + 1) + ". " + roads[i]);
        } else {
            System.out.println("There are no roads added");
        }
    }
}
