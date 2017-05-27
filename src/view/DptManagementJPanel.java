package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marianne
 * on 27/05/2017.
 */
public class DptManagementJPanel extends JPanel{
    private JPanel panel1;
    private JButton removeButton;
    private JButton addButton;
    private JButton modifyButton;
    private JTable departmentTable;
    private DptTableModel dptTableModel;

    public DptManagementJPanel() {
        super();
        //taille min de la fenetre de cet onglet
        setSize(600, 400);
        setMinimumSize(getSize());
        //outil qui permet d'étendre les panel interne en meme temps que la fenetre principal
        setLayout(new BorderLayout());
        // def quel panel il doit afficher
        add(panel1);
        setName("Department Management");

        //grise les boutons
        modifyButton.setEnabled(false);
        removeButton.setEnabled(false);

        //instancie dptTableModel
        dptTableModel = new DptTableModel();
        departmentTable.setModel(dptTableModel);

        //regarde si il y a une selection et dégrise les boutons
        ListSelectionModel listSelectionModel = departmentTable.getSelectionModel();
        listSelectionModel.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }

            int[] selection = departmentTable.getSelectedRows();

            boolean isSelection = selection != null && selection.length != 0;
            removeButton.setEnabled(isSelection);

            boolean isOnlyOneSelection = isSelection && selection.length == 1;
            modifyButton.setEnabled(isOnlyOneSelection);
        });
    }
}
