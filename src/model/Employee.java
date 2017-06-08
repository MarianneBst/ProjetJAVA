package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
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
    private int creditHour;
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
        this.creditHour = 0;
    }

    public Employee(String name, String firstName, UUID id, LocalDateTime startHour, LocalDateTime endHour, int creditHour, StandardDepartment standardDepartment) {
        super(name, firstName);
        this.id = id;
        this.startHour = startHour;
        this.endHour = endHour;
        this.creditHour = creditHour;
        this.standardDepartment = standardDepartment;
        this.tallies = new ArrayList<>();
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
        this.creditHour = 0;
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
    public int getCreditHour() {

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
    void setCreditHour(int creditHour) {

        this.creditHour = creditHour;
    }

    /**
     * Add tally.
     *
     * @param tally the tally
     */
    void addTally(Tally tally){
        LocalDateTime checkDate = tally.getCheckDate(); //le check
        int formerCredit = getCreditHour(); // l'ancien credit

        //passage des valeurs en minutes
        int minStartHour = startHour.getHour()*60 + startHour.getMinute();
        int minEndHour = endHour.getHour()*60 + endHour.getMinute();
        int minCheckDate = checkDate.getHour()*60 + checkDate.getMinute();
        int tmpStart = 0, tmpEnd = 0, tmpCredit = 0;
        int tmpVrai = 0, tmpNorm = 0;

//        // si Check IN
//        if(tallies.size()%2 == 0){
//            tmpStart = minStartHour - minCheckDate;
//            tmpCredit = tmpStart + formerCredit;
//        }
//        else{
//            tmpEnd = minCheckDate - minEndHour;
//            tmpCredit = tmpEnd + formerCredit;
//        }

        if(tallies.size()%2 == 0){
            tmpCredit = creditHour;
        }
        else{
            tmpNorm = minEndHour - minStartHour;
            if(tmpNorm < 0){
                tmpNorm += 24*60;
            }
            Tally formerTally = getLastTally();
            int minFormerTally = formerTally.getCheckDate().getHour()*60 + formerTally.getCheckDate().getMinute();
            tmpVrai = minCheckDate - minFormerTally;
            if(tmpVrai < 0){
                tmpVrai += 24*60;
            }
            tmpCredit = tmpVrai - tmpNorm;
        }

        creditHour += tmpCredit;
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

    public String[] getRecord() {
        String[] result;
        String recordStr = "";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        recordStr += getName() + "," + getFirstName() + "," + id.toString() + "," + startHour.format(formatter) + "," + endHour.format(formatter) + "," + creditHour + "," + standardDepartment.getName();
        result = recordStr.split(",");

        return result;
    }
}


