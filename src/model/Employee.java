package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public class Employee extends Person implements Serializable{
    private UUID id;
    private LocalDateTime startHour;
    private LocalDateTime endHour;
    private LocalDateTime creditHour;
    private ArrayList<Tally> tallies; //all tallies of the day, list of object Tally
    private StandardDepartment standardDepartment;


    /**
     * Instantiates a new Employee.
     *
     * @param name      the name
     * @param firstName the first name
     * @param startHour the start hour
     * @param endHour   the end hour
     */
// Constructor
    public Employee(String name, String firstName, LocalDateTime startHour, LocalDateTime endHour) {
        super(name, firstName);
        tallies = new ArrayList<>();
        this.id = UUID.randomUUID();
        this.startHour = startHour;
        this.endHour = endHour;
        this.creditHour = LocalDateTime.of(0,1,1,0,0); // years 0, month 01, day 01, hour 00, minute 00
    }

    /**
     * Instantiates a new Employee.
     *
     * @param name       the name
     * @param firstName  the first name
     * @param startHour  the start hour
     * @param endHour    the end hour
     * @param department the department
     */
    public Employee(String name, String firstName, LocalDateTime startHour, LocalDateTime endHour, StandardDepartment department) {
        super(name, firstName);
        tallies = new ArrayList<>();
        this.id = UUID.randomUUID();
        this.startHour = startHour;
        this.endHour = endHour;
        this.creditHour = LocalDateTime.of(0,1,1,0,0);
        standardDepartment = department;

    }

    /**
     * Gets id.
     *
     * @return the id
     */
// Getter and setter
    public UUID getId() {
        return id;
    }

    /**
     * Gets start hour.
     *
     * @return the start hour
     */
    public LocalDateTime getStartHour() {

        return startHour;
    }

    /**
     * Gets credit hour.
     *
     * @return the credit hour
     */
    public LocalDateTime getCreditHour() {

        return creditHour;
    }

    /**
     * Gets standard department.
     *
     * @return the standard department
     */
    public StandardDepartment getStandardDepartment() {
        return standardDepartment;
    }

    /**
     * Get last tally tally.
     *
     * @return the tally
     */
    public Tally getLastTally(){
        return tallies.get(tallies.size() - 1);
    }

    /**
     * Gets end hour.
     *
     * @return the end hour
     */
    public LocalDateTime getEndHour() {

        return endHour;
    }

    /**
     * Sets end hour.
     *
     * @param endHour the end hour
     */
    void setEndHour(LocalDateTime endHour) {

        this.endHour = endHour;
    }

    /**
     * Sets credit hour.
     *
     * @param creditHour the credit hour
     */
    void setCreditHour(LocalDateTime creditHour) {

        this.creditHour = creditHour;
    }

    /**
     * Add tally.
     *
     * @param tally the tally
     */
    void addTally(Tally tally){
        // TODO: 10/04/2017 Update credits

        tally.setEmployee(this);
        tallies.add(tally);
    }

    /**
     * Sets standard department.
     *
     * @param standardDepartment the standard department
     */
    void setStandardDepartment(StandardDepartment standardDepartment) {
        this.standardDepartment = standardDepartment;
    }

    /**
     * Sets start hour.
     *
     * @param startHour the start hour
     */
    void setStartHour(LocalDateTime startHour) {

        this.startHour = startHour;
    }

    @Override
    public String toString() {
        return super.toString() + " ID : " + id;
    }

    /**
     * Gets tallies.
     *
     * @return the tallies
     */
    public ArrayList<Tally> getTallies() {
        return tallies;
    }
}


