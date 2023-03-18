package Lab3.Homework;

import Lab3.Compulsory.Person;

/**
 * Designer class
 */
public class Designer extends Person {
    //this attribute contain the name of the program that one designer uses
    private String prototypingTool;

    public Designer(String name, Date birthDate, String prototypingTool) {
        super(name, birthDate);
        this.prototypingTool = prototypingTool;
    }

    @Override
    public String toString() {
        return "Designer{" + "prototypingTool='" + prototypingTool + '\'' + ", name='" + name + '\'' + ", birthDate=" + birthDate + printRelationships() + '}';
    }
}
