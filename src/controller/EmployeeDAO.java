package controller;

import model.Person;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Ronan
 * on 08/06/2017.
 */
public class EmployeeDAO extends Person{
    private UUID id;
    private LocalDateTime startHour;
    private LocalDateTime endHour;
    private LocalDateTime creditHour;
    private String standardDepartmentName;

    public EmployeeDAO(String name, String firstName, UUID id, LocalDateTime startHour, LocalDateTime endHour, LocalDateTime creditHour, String standardDepartmentName) {
        super(name, firstName);
        this.id = id;
        this.startHour = startHour;
        this.endHour = endHour;
        this.creditHour = creditHour;
        this.standardDepartmentName = standardDepartmentName;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getStartHour() {
        return startHour;
    }

    public LocalDateTime getEndHour() {
        return endHour;
    }

    public LocalDateTime getCreditHour() {
        return creditHour;
    }

    public String getStandardDepartmentName() {
        return standardDepartmentName;
    }
}
