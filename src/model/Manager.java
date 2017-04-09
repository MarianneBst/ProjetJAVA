package model;

import java.util.Date;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public class Manager extends Employee implements Leader{

    private String mail;

    public Manager(String name, String firstName, Date startHour, Date endHour, Date creditHour, String mail) {
        super(name, firstName, startHour, endHour, creditHour);
        this.mail = mail;
    }

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
