package Lab3.Homework;

/**
 * Date class contains the components of a date
 */

public class Date {
    protected int day;
    protected int month;
    protected int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return day + "-" + month + "-" + year;
    }
}
