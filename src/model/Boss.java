package model;

import java.io.Serializable;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
// implement leader -> implement same fct
public class Boss extends Person implements Leader, Serializable{

    private String mail;

    /**
     * Instantiates a new Boss.
     *
     * @param name      the name
     * @param firstName the first name
     * @param mail      the mail
     */
    public Boss(String name, String firstName, String mail) {
        super(name, firstName);
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
