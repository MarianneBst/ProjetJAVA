package view;

import model.Company;
import model.Employee;
import model.StandardDepartment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private StaffTableModel staffTableModel;
    private StaffJDialog employeeJDialog;
    private ArrayList<StandardDepartment> standardDepartmentList;

    public StaffManagementJPanel(ActionListener actionListener, ArrayList<StandardDepartment> standardDepartmentList) {
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

        //instancie dptTableModel
        staffTableModel = new StaffTableModel();
        employeeTable.setModel(staffTableModel);
        this.standardDepartmentList = standardDepartmentList;

        //?? on definit le bouton addbutton et on ajouter le controller (actionListener)
        addButton.addActionListener(e -> {
            employeeJDialog = new StaffJDialog(actionListener, null, standardDepartmentList);
            employeeJDialog.setVisible(true);
        });

        // definition du bouton modifie
        modifyButton.addActionListener(e-> {
                employeeJDialog = new StaffJDialog(actionListener,
                        staffTableModel.getElementAt(employeeTable.getSelectedRow()), standardDepartmentList);
                employeeJDialog.setVisible(true);
        });

        //definition du bouton remove
        removeButton.setActionCommand("Remove Employee");
        removeButton.addActionListener(actionListener);

        //regarde si il y a une selection et dégrise les boutons
        ListSelectionModel listSelectionModel = employeeTable.getSelectionModel();
        listSelectionModel.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }

            int[] selection = employeeTable.getSelectedRows();

            boolean isSelection = selection != null && selection.length != 0;
            removeButton.setEnabled(isSelection);

            boolean isOnlyOneSelection = isSelection && selection.length == 1;
            modifyButton.setEnabled(isOnlyOneSelection);
        });
    }

    public StaffJDialog getEmployeeJDialog() {
        return employeeJDialog;
    }

    //met à jour la fenetre des employer depuis la liste dans le modele (à qui on vient d'ajouter un employer
    // depuis la fenetre de dialogue
    public void myUpdate(Company company) {
        staffTableModel.setDataEmployee(company.getAllEmployees());
    }

    public Employee getSelectedEmployee() {
        return staffTableModel.getElementAt(employeeTable.getSelectedRow());
    }

    public ArrayList<Employee> getSelectedEmployees() {
        ArrayList<Employee> result = new ArrayList<>();
        for (int index : employeeTable.getSelectedRows()) {
            result.add(staffTableModel.getElementAt(index));
        }
        return result;
    }
}
