package model;

import java.time.LocalDateTime;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public class Manager extends Employee implements Leader{

    private String mail;

    //Constructors
    public Manager(String name, String firstName, LocalDateTime startHour, LocalDateTime endHour, String mail) {
        super(name, firstName, startHour, endHour);
        this.mail = mail;
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
