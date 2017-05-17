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
    }


    // call when action in button
    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Ronan c'est le meilleur ♥ ");


    }
}
