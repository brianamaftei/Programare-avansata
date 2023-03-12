package Lab2.Compulsory;

/**
 * Location class
 */
public class Location {
    protected String name;
    protected LocationType type;
    protected double x, y;


    public Location(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Location(String name, LocationType type, double x, double y) {
        this.name = name;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Location{" + "name='" + name + '\'' + ", type='" + type + '\'' + ", x=" + x + ", y=" + y + '}';
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public LocationType getType() {
        return type;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    /**
     * @param obj a given object
     * @return if 2 locations are equals by name and type
     */
    public boolean equals(Object obj) {

        if (!(obj instanceof Location)) {
            return false;
        }

        Location other = (Location) obj;
        return this.name.equals(other.name) && this.getClass() == other.getClass();
    }
}





