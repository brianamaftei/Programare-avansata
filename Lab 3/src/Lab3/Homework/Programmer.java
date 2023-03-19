package Lab3.Homework;

import Lab3.Compulsory.Person;

public class Programmer extends Person {

    private final String skilledProgrammingLanguage;

    public Programmer(String name, Date birthDate, String programmingLanguage) {
        super(name, birthDate);
        this.skilledProgrammingLanguage = programmingLanguage;
    }

    @Override
    public String toString() {
        return "Programmer{" + "programmingLanguage='" + skilledProgrammingLanguage + '\'' + ", name='" + name + '\'' + ", birthDate=" + birthDate + printRelationships() + '}';
    }
}
