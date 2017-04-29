import controller.MainController;
import model.Boss;
import model.Company;

public class Main {

    public static void main(String[] args) {

        Boss boss = new Boss("BOST", "Marianne", "marianne.bost@etu.univ-tours.fr");

        Company company = new Company("Unicorn and co", boss);

        MainController mainController = new MainController(company);
    }
}