package Lab2.Bonus;

import Lab2.Compulsory.Location;
import Lab2.Homework.Problem;

public class Solution {
    /**
     *
     * @param locations How many locations we test for
     * @param roads How many roads we test for.
     */
    public Solution(int locations, int roads) {
        Problem problem = new Problem(locations, roads);
        RandomGeneratorAlgorithm randomGeneratorAlgorithm = new RandomGeneratorAlgorithm(problem, locations, roads);
        int i, j;
        Location[] locationArray = problem.getLocations();
        DijkstraAlgorithm dijkstraAlgorithm;
        for (i = 0; i < locations; i++) {
            dijkstraAlgorithm = new DijkstraAlgorithm(locationArray[i], problem);
            for (j = 0; j < locations; j++) {
                if (i != j) {
                    if (dijkstraAlgorithm.printDistance(locationArray[j]) != Integer.MAX_VALUE) {
                        System.out.println("Shortest path: " + locationArray[i].getName() + " - "
                                + locationArray[j].getName() + ": " + dijkstraAlgorithm.printDistance(locationArray[j]));
                    }
                }
            }
        }
        for (i = 0; i < locations; i++) {
            dijkstraAlgorithm = new DijkstraAlgorithm(problem, locationArray[i]);
            for (j = 0; j < locations; j++) {
                if (i != j) {
                    if (dijkstraAlgorithm.printDistance(locationArray[j]) != Integer.MAX_VALUE) {
                        System.out.println("Fastest path: " + locationArray[i].getName() + " - "
                                + locationArray[j].getName() + ": " + dijkstraAlgorithm.printDistance(locationArray[j]));
                    }
                }
            }
        }
    }
}
