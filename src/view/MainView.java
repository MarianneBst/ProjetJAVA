package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * Created by Marianne
 * on 29/04/2017.
 */
public class MainView extends  AbstractView{
    private JPanel mainPanel;
    private JButton ronanButton;
    private JButton exitButton;
    private JTabbedPane tabbedPane1;

    //constructor
    public MainView(ActionListener actionListener){
        super("Start");

        //Set margin
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        mainPanel.setBorder(padding);
        // def principal panel
        setContentPane(mainPanel);

        if(actionListener != null){
            registerListener(actionListener);
        }

        improvePlacement();

        // action when we click on exitButton
        exitButton.addActionListener(e -> {
            dispose();
            System.exit(0);
        });

        setVisible(true);
    }

    // all button or frame where it action use the model
    @Override
    public void registerListener(ActionListener actionListener) {
        ronanButton.addActionListener(actionListener);

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
