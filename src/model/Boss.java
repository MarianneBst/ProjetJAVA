package model;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public class Boss extends Person implements Leader{

    private String mail;

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
