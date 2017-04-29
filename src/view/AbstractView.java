package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observer;

/**
 * Created by Marianne
 * on 29/04/2017.
 */
public abstract class AbstractView extends JFrame implements Observer {

    //Constructor
    public AbstractView(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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

    public void showError(String message) {
        //custom title, error icon
        JOptionPane.showMessageDialog(this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    abstract public void registerListener(ActionListener actionListener);

}