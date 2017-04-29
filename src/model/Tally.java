package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Marianne
 * on 11/04/2017.
 */
public class Tally {
    private Employee employee;
    private LocalDateTime checkDate;

    // Constructor
    public Tally(Employee employee, LocalDateTime checkDate) {

        this.employee = employee;
        this.checkDate = checkDate;

    }

    // Getter and Setter
    public LocalDateTime getCheckDate() {
        return checkDate;
    }

    public Employee getEmployee() {
        return employee;
    }
}
