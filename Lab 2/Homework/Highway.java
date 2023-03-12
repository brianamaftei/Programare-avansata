package Lab2.Homework;

import Lab2.Compulsory.Location;
import Lab2.Compulsory.Road;

/**
 * Higway subclass
 */

public class Highway extends Road {
    public Highway(int length, int speedLimit, Location start, Location end) {
        super(length, speedLimit, start, end);
    }

    @Override
    public String toString() {
        return "Highway{" +
                "length=" + length +
                ", speedLimit=" + speedLimit +
                ", start=" + start.getName() +
                ", end=" + end.getName() +
                '}';
    }
}
