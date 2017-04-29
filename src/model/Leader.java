package model;

/**
 * Created by Marianne
 * on 09/04/2017.
 */
// interface because more efficient, used by Manager and boss
public interface Leader {

    String getAdressMail();
    void setAdressMail(String newAdress);
}
