package model;

/**
 * Created by Marianne
 * on 09/04/2017.
 */
// interface because more efficient, used by Manager and boss
public interface Leader {

    /**
     * Gets adress mail.
     *
     * @return the adress mail
     */
    String getAdressMail();

    /**
     * Sets adress mail.
     *
     * @param newAdress the new adress
     */
    void setAdressMail(String newAdress);
}
