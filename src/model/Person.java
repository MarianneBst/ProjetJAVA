package model;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public abstract class Person {
    private String name;
    private String firstName;

    public Person(String name, String firstName) {
        this.name = name;
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Name : " + name + "FirstName : " + firstName;
    }
}
