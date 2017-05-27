package controller;

import model.Company;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Marianne
 * on 30/04/2017.
 */
public class MainController implements ActionListener {
    private Company company;
    private MainView mainView;

    public MainController(Company company) {
        this.company = company;
        this.mainView = new MainView(this);
        company.addObserver(mainView);
    }


    // call when action in button
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()){
            case "Add Dpt":
                company.addStandardDepartment(mainView.getDepartmentCreated());
                break;
        }
        company.notifyObservers();
        System.out.println("Ronan c'est le meilleur ♥ ");


    }
}
