import controller.MainController;
import model.Boss;
import model.Company;

/**
 * The type Main.
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        Boss boss = new Boss("BOST", "Marianne", "marianne.bost@etu.univ-tours.fr");

        Company company = new Company("Unicorn and co", boss);

        MainController mainController = new MainController(company);
    }
}