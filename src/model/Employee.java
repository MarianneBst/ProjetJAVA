package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public class Employee extends Person{
    private UUID id;
    private LocalDateTime startHour;
    private LocalDateTime endHour;
    private LocalDateTime creditHour;
    private ArrayList<Tally> tallies; //all tallies of the day, list of object Tally
    private StandardDepartment standardDepartment;


    // Constructor
    public Employee(String name, String firstName, LocalDateTime startHour, LocalDateTime endHour, StandardDepartment standardDepartment) {
        super(name, firstName);
        tallies = new ArrayList<>();
        this.id = UUID.randomUUID();
        this.startHour = startHour;
        this.endHour = endHour;
        this.creditHour = LocalDateTime.of(0,1,1,0,0); // years 0, month 01, day 01, hour 00, minute 00
        this.standardDepartment = standardDepartment;
    }

    // Getter and setter
    public UUID getId() {
        return id;
    }

    public LocalDateTime getStartHour() {

        return startHour;
    }

    public LocalDateTime getCreditHour() {

        return creditHour;
    }

    public StandardDepartment getStandardDepartment() {
        return standardDepartment;
    }

    public Tally getLastTally(){
        return tallies.get(tallies.size() - 1);
    }

    public LocalDateTime getEndHour() {

        return endHour;
    }

    void setEndHour(LocalDateTime endHour) {

        this.endHour = endHour;
    }

    void setCreditHour(LocalDateTime creditHour) {

        this.creditHour = creditHour;
    }

    void addTally(Tally tally){
        // TODO: 10/04/2017 Update credits
//        Date checkDate;
//        int minutes;
//        checkDate = tally.getCheckDate();
//        minutes = checkDate.getMinutes();
        tallies.add(tally);
    }

    void setStandardDepartment(StandardDepartment standardDepartment) {
        this.standardDepartment = standardDepartment;
    }

    void setStartHour(LocalDateTime startHour) {

        this.startHour = startHour;
    }

    @Override
    public String toString() {
        return super.toString() + ", ID = " + id + "StartHour : " + startHour + "EndHour : " + endHour +" CreditHour : " + creditHour;
    }

}


