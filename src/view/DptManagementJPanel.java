package view;

import model.Company;
import model.StandardDepartment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private DptJDialog dptJDialog;

    /**
     * Instantiates a new Dpt management j panel.
     *
     * @param actionListener the action listener
     */
    public DptManagementJPanel(ActionListener actionListener) {
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

        //??
        addButton.addActionListener(e -> {
            dptJDialog = new DptJDialog(actionListener, null);
            dptJDialog.setVisible(true);
        });

        modifyButton.addActionListener(e -> {
            dptJDialog = new DptJDialog(actionListener, dptTableModel.getElementAt(departmentTable.getSelectedRow()));
            dptJDialog.setVisible(true);
        });

        removeButton.setActionCommand("Remove Dpt");
        removeButton.addActionListener(actionListener);

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

    /**
     * Gets dpt j dialog.
     *
     * @return the dpt j dialog
     */
    public DptJDialog getDptJDialog() {
        return dptJDialog;
    }

    /**
     * My update.
     *
     * @param company the company
     */
    public void myUpdate(Company company) {
        dptTableModel.setDataDpt(company.getStandardDepartmentList());
        //setDataEmployee
    }

    /**
     * Gets selected dpt.
     *
     * @return the selected dpt
     */
    public StandardDepartment getSelectedDpt() {
        return dptTableModel.getElementAt(departmentTable.getSelectedRow());
    }

    /**
     * Gets selected dpts.
     *
     * @return the selected dpts
     */
    public ArrayList<StandardDepartment> getSelectedDpts() {
        ArrayList<StandardDepartment> result = new ArrayList<>();
        for (int index : departmentTable.getSelectedRows()) {
            result.add(dptTableModel.getElementAt(index));
        }
        return result;
    }
}
