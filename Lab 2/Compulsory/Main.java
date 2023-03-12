package Lab2.Compulsory;

/**
 * In the main class we instantiate a few examples of locations and a road and then output them to the console
 */
public class Main {
    public static void main(String[] args) {
        Location c1 = new Location("Vaslui", LocationType.city, 10.0, 20.0);
        System.out.println(c1);
        Road briana = new Road(RoadType.HIGHWAY, 1000, 200);
        System.out.println(briana);
    }
}