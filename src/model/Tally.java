package model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Marianne
 * on 11/04/2017.
 */
public class Tally implements Serializable {
    private Employee employee;
    private LocalDateTime checkDate;

    /**
     * Instantiates a new Tally.
     *
     * @param employee  the employee
     * @param checkDate the check date
     */
// Constructor
    public Tally(Employee employee, LocalDateTime checkDate) {

        this.employee = employee;
        this.checkDate = checkDate;

    }

    /**
     * Gets check date.
     *
     * @return the check date
     */
// Getter and Setter
    public LocalDateTime getCheckDate() {
        return checkDate;
    }

    /**
     * Gets employee.
     *
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Sets employee.
     *
     * @param employee the employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
