package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marianne
 * on 27/05/2017.
 */
public class StaffManagementJPanel extends JPanel{
    private JTable employeeTable;
    private JButton removeButton;
    private JButton modifyButton;
    private JButton addButton;
    private JPanel mainStaffPanel;

    public StaffManagementJPanel() {
        super();
        //taille min de la fenetre de cet onglet
        setSize(600, 400);
        setMinimumSize(getSize());
        //outil qui permet d'étendre les panel interne en meme temps que la fenetre principal
        setLayout(new BorderLayout());
        // def quel panel il doit afficher
        add(mainStaffPanel);
        setName("Staff Management");

        //grise les boutons
        modifyButton.setEnabled(false);
        removeButton.setEnabled(false);
    }
}
