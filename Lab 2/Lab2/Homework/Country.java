package Lab2.Homework;

import Lab2.Compulsory.Location;
import Lab2.Compulsory.Road;

/**
 * Country subclass
 */

public class Country extends Road {

    public Country(int length, int speedLimit, Location start, Location end) {
        super(length, speedLimit, start, end);
    }

    @Override
    public String toString() {
        return "Country{" +
                "length=" + length +
                ", speedLimit=" + speedLimit +
                ", start=" + start.getName() +
                ", end=" + end.getName() +
                '}';
    }
}
