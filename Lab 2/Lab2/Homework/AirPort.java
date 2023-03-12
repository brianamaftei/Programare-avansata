package Lab2.Homework;
import Lab2.Compulsory.Location;

/**
 * Airport subclass
 */
public class AirPort extends Location{
    private int numberTerminals;

    public AirPort(String name, double x, double y, int numberTerminals) {
        super(name, x, y);
        this.numberTerminals = numberTerminals;
    }

    public int getNumberTerminals() {
        return numberTerminals;
    }

    public void setNumberTerminals(int numberTerminals) {
        this.numberTerminals = numberTerminals;
    }

    @Override
    public String toString() {
        return "AirPort{" +
                "numberTerminals=" + numberTerminals +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
