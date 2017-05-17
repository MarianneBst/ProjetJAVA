package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Marianne
 * on 29/04/2017.
 */
public class MainView extends JFrame implements Observer {
    private JPanel mainPanel;
    private JButton ronanButton;
    private JButton exitButton;
    private JTabbedPane tabbedPane1;

    //constructor,
    public MainView(ActionListener actionListener){
        super("Start");

        //Set margin
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        mainPanel.setBorder(padding);
        // def principal panel
        setContentPane(mainPanel); // ou choisir l'autre méthode

        if(actionListener != null){
            registerListener(actionListener);
        }
        //set minimum size and set the position
        improvePlacement();

        // action when we click on exitButton
        exitButton.addActionListener(e -> {
            dispose();
            System.exit(0);
        });

        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // ?
    }

    // set minimum size and set the position
    protected void improvePlacement() {
        //Minimize the frame's size and freeze the minimum size
        pack();
        setMinimumSize(getSize());

        //Set the frame on the middle screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    //error icon
    public void showError(String message) {
        // custom title, parentComponent ?
        JOptionPane.showMessageDialog(this, message,"Error", JOptionPane.ERROR_MESSAGE);
    }

    // all button or frame where it action use the model
    public void registerListener(ActionListener actionListener) {
        ronanButton.addActionListener(actionListener);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
