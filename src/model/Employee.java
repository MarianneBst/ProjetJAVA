package model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public class Employee extends Person{
    private UUID id;
    private Date startHour;
    private Date endHour;
    private Date creditHour; //heures supp ou retard

    public Employee(String name, String firstName, Date startHour, Date endHour, Date creditHour) {
        super(name, firstName);
        this.startHour = startHour;
        this.endHour = endHour;
        this.creditHour = creditHour;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public Date getStartHour() {

        return startHour;
    }

    public void setStartHour(Date startHour) {

        this.startHour = startHour;
    }

    public Date getEndHour() {

        return endHour;
    }

    public void setEndHour(Date endHour) {

        this.endHour = endHour;
    }

    public Date getCreditHour() {

        return creditHour;
    }

    public void setCreditHour(Date creditHour) {

        this.creditHour = creditHour;
    }

    @Override
    public String toString() {
        return super.toString() + "ID = " + id + "StartHour : " + startHour + "EndHour : " + endHour +" CreditHour : " + creditHour;
    }
}
