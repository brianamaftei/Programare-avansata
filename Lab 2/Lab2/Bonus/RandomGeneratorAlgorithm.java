package Lab2.Bonus;

import Lab2.Compulsory.Location;
import Lab2.Homework.*;

public class RandomGeneratorAlgorithm extends Algorithm {
    private final Problem problem;

    /**
     *
     * @param problem Instance of the problem for which we generate random instances of locations and roads
     * @param locations how many locations we want
     * @param roads how many roads we want
     */
    public RandomGeneratorAlgorithm(Problem problem, int locations, int roads) {
        this.problem = problem;
        generate(locations, roads);
    }

    /**
     *
     * @param locations we generate locations here randomly.
     */
    public void generateLocations(int locations) {
        while (locations > 0) {
            if (locations % 3 == 0) {
                City city = new City("City" + (int) (Math.random() * 100_000_000), Math.random() * 1_000, Math.random() * 1_000, 777);
                problem.addLocation(city);
            } else if (locations % 3 == 1) {
                AirPort airport = new AirPort("Airport" + (int) (Math.random() * 100_000_000), Math.random() * 1_000, Math.random() * 1_000, 42);
                problem.addLocation(airport);
            } else {
                GasStation gasStation = new GasStation("GasStation" + (int) (Math.random() * 100_000_000), Math.random() * 1_000, Math.random() * 1_000, 6.90);
                problem.addLocation(gasStation);
            }
            locations--;
        }
    }

    /**
     *
     * @param roads how many roads we want to generate randomly
     */
    public void generateRoads(int roads) {
        Location[] locations = problem.getLocations();
        while (roads > 0) {
            int start = (int) (Math.random() * problem.getLocationsCount());
            int end = (int) (Math.random() * problem.getLocationsCount());
            if (start != end) {
                if (roads % 3 == 0) {
                    Highway highway = new Highway((int) (Math.random() * 1_000_000), (int) (Math.random() * 10_000)+1, locations[start], locations[end]);
                    problem.addRoad(highway);
                } else if (roads % 3 == 1) {
                    Country country = new Country((int) (Math.random() * 1_000_000), (int) (Math.random() * 10_000)+1, locations[start], locations[end]);
                    problem.addRoad(country);
                } else {
                    Express express = new Express((int) (Math.random() * 1_000_000), (int) (Math.random() * 10_000)+1, locations[start], locations[end]);
                    problem.addRoad(express);
                }
                roads--;
            }
        }
    }

    /**
     * If it's not a valid generation, we regenerate it recursively
     * @param locations how many locations
     * @param roads how many roads
     */
    public void generate(int locations, int roads) {
        generateLocations(locations);
        generateRoads(roads);
        if (!problem.isValid()) {
            problem.setLocationsCount(0);
            problem.setRoadsCount(0);
            generate(locations, roads);
        }
    }
}
