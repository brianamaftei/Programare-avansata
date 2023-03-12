package Lab2.Compulsory;

/**
 * The Road class with his constructors, getters, setters, the equals method and the method overriding
 *
 */
public class Road {
    protected RoadType type;
    protected int length;
    protected int speedLimit;

    protected Location start, end;

    public Road(int length, int speedLimit, Location start, Location end) {
        this.length = length;
        this.speedLimit = speedLimit;
        this.start = start;
        this.end = end;
    }

    public Road(RoadType type, int length, int speedLimit) {
        this.type = type;
        this.length = length;
        this.speedLimit = speedLimit;
    }

    //getters

    public int getLength() {
        return length;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public Location getEnd() {
        return end;
    }

    public Location getStart() {
        return start;
    }


    public void setLength(int length) {
        this.length = length;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    @Override
    public String toString() {
        return "Road{" + "type=" + type + ", length=" + length + ", speedLimit=" + speedLimit + '}';
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Road)) {
            return false;
        }
        Road other = (Road) obj;
        return this.length == other.length && this.speedLimit == other.speedLimit && this.getClass() == other.getClass();
    }
}
