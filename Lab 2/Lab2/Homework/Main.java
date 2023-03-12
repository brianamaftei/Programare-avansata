package Lab2.Homework;

public class Main {
    public static void main(String[] args) {
        /**
         * We instantiate 3 objects:2 locations and a road between them
         */
        Problem problem = new Problem(20, 13);
        City city1 = new City("Cluj-Napoca", 31.0, 34.6, 700000);
        GasStation gasStation1 = new GasStation("OMV", 45.9, 56.0, 55);
        Highway highway1 = new Highway(5000, 80, city1, gasStation1);

        /**
         * We add them into the problem object and prints on the screen the information we has given and
         * after that we call a method that it verifies if the instance is valid and
         * another that checks if there is a path between the 2 locations
         */
        problem.addLocation(city1);
        problem.addLocation(gasStation1);

        problem.addRoad(highway1);
        problem.print();

        System.out.println(problem.isValid());
        System.out.println(problem.existsAPath(city1, gasStation1));

    }
}
