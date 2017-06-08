package model;

import java.io.Serializable;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public abstract class Person implements Serializable {
    private String name;
    private String firstName;

    /**
     * Instantiates a new Person.
     *
     * @param name      the name
     * @param firstName the first name
     */
// Constructor
    public Person(String name, String firstName) {
        this.name = name;
        this.firstName = firstName;
    }


    /**
     * Gets name.
     *
     * @return the name
     */
//Getter and Setter
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    @Override
    public String toString() {
        return "Name : " + name + " FirstName : " + firstName;
    }
}
