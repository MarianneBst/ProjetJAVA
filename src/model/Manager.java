package model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public class Manager extends Employee implements Leader, Serializable{

    private String mail;

    /**
     * Instantiates a new Manager.
     *
     * @param name      the name
     * @param firstName the first name
     * @param startHour the start hour
     * @param endHour   the end hour
     * @param mail      the mail
     */
//Constructors
    public Manager(String name, String firstName, LocalDateTime startHour, LocalDateTime endHour, String mail) {
        super(name, firstName, startHour, endHour);
        this.mail = mail;
    }

    /**
     * Instantiates a new Manager.
     */
    public Manager() {
        super("", "", LocalDateTime.now(), LocalDateTime.now());
    }

    //Getter and Setter
    @Override
    public String getAdressMail() {
        return mail;
    }

    @Override
    public void setAdressMail(String newAdress) {
        mail = newAdress;
    }

    @Override
    public String toString() {
        return super.toString() + " Mail : " + mail;
    }
}
