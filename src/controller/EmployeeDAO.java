package controller;

import model.Person;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Marianne
 * on 08/06/2017.
 */
public class EmployeeDAO extends Person{
    private UUID id;
    private LocalDateTime startHour;
    private LocalDateTime endHour;
    private int creditHour;
    private String standardDepartmentName;

    /**
     * Instantiates a new Employee dao.
     *
     * @param name                   the name
     * @param firstName              the first name
     * @param id                     the id
     * @param startHour              the start hour
     * @param endHour                the end hour
     * @param creditHour             the credit hour
     * @param standardDepartmentName the standard department name
     */
    public EmployeeDAO(String name, String firstName, UUID id, LocalDateTime startHour, LocalDateTime endHour, int creditHour, String standardDepartmentName) {
        super(name, firstName);
        this.id = id;
        this.startHour = startHour;
        this.endHour = endHour;
        this.creditHour = creditHour;
        this.standardDepartmentName = standardDepartmentName;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
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
     * Gets end hour.
     *
     * @return the end hour
     */
    public LocalDateTime getEndHour() {
        return endHour;
    }

    /**
     * Gets credit hour.
     *
     * @return the credit hour
     */
    public int getCreditHour() {
        return creditHour;
    }

    /**
     * Gets standard department name.
     *
     * @return the standard department name
     */
    public String getStandardDepartmentName() {
        return standardDepartmentName;
    }
}
