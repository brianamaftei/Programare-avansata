package Compulsory;

public class Location {
    private String name;
    private LocationType type;
    private double x, y; //coordonate

    public Location() {
    } //constructor

    public Location(String name) { //constructor
        this.name = name;
    }

    public Location(String name, double x, double y, LocationType type) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.type = type;

    }

    @Override
    public String toString() {
        return "Location{" + "name='" + name + '\'' + ", type='" + type + '\'' + ", x=" + x + ", y=" + y + '}';
    }
// ... getters

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public LocationType getType() {
        return type;
    }
    //setters

    public void setName(String name) {
        this.name = name;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setType(LocationType type) {
        this.type = type;
    }
}





