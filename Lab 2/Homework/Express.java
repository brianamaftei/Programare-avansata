package Lab2.Homework;

import Lab2.Compulsory.Location;
import Lab2.Compulsory.Road;

/**
 * Express subclass
 */

public class Express extends Road{
    public Express(int length, int speedLimit, Location start, Location end) {
        super(length, speedLimit, start, end);
    }

    @Override
    public String toString() {
        return "Express{" +
                "length=" + length +
                ", speedLimit=" + speedLimit +
                ", start=" + start.getName() +
                ", end=" + end.getName() +
                '}';
    }
}
