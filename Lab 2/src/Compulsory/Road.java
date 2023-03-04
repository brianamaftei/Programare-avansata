package Compulsory;

public class Road {
    private RoadType type;
    private int length;
    private int speedLimit;

    public Road(RoadType type, int length, int speedLimit) {
        this.length = length;
        this.type = type;
        this.speedLimit = speedLimit;
    }
    //getters

    public int getLength() {
        return length;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public RoadType getType() {
        return type;
    }

    //setters

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

    public void setType(RoadType type) {
        this.type = type;
    }

//public boolean equals(Object obj){
//        if(obj==null|| !(obj instanceof Road)){
//            return false;
//        }
//        Road other = (Road) obj;
//        return name.equals(other.name);
//    }}
}
