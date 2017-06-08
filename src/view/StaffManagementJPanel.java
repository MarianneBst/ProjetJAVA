package view;

import model.Company;
import model.Employee;
import model.StandardDepartment;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
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
    private JTextField filterText;
    private StaffTableModel staffTableModel;
    private StaffJDialog employeeJDialog;
    private ArrayList<StandardDepartment> standardDepartmentList;
    private TableRowSorter sorter;

    /**
     * Instantiates a new Staff management j panel.
     *
     * @param actionListener         the action listener
     * @param standardDepartmentList the standard department list
     */
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
        if (standardDepartmentList.size() == 0){
            addButton.setEnabled(false);
        }

        //instancie dptTableModel
        staffTableModel = new StaffTableModel();
        employeeTable.setModel(staffTableModel);
        this.standardDepartmentList = standardDepartmentList;

        //créer un fliter
        sorter = new TableRowSorter<StaffTableModel>(staffTableModel);
        employeeTable.setRowSorter(sorter);

        //ajoute un listener au textfield
        filterText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                newFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                newFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                newFilter();
            }
        });

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

    /**
     * Gets employee j dialog.
     *
     * @return the employee j dialog
     */
    public StaffJDialog getEmployeeJDialog() {
        return employeeJDialog;
    }

    /**
     * My update.
     *
     * @param company the company
     */
//met à jour la fenetre des employer depuis la liste dans le modele (à qui on vient d'ajouter un employer
    // depuis la fenetre de dialogue
    public void myUpdate(Company company) {
        staffTableModel.setDataEmployee(company.getAllEmployees());
        standardDepartmentList = company.getStandardDepartmentList();
        if (standardDepartmentList.size() > 0){
            addButton.setEnabled(true);
        }
    }

    /**
     * Gets selected employee.
     *
     * @return the selected employee
     */
    public Employee getSelectedEmployee() {
        return staffTableModel.getElementAt(employeeTable.getSelectedRow());
    }

    /**
     * Gets selected employees.
     *
     * @return the selected employees
     */
    public ArrayList<Employee> getSelectedEmployees() {
        ArrayList<Employee> result = new ArrayList<>();
        for (int index : employeeTable.getSelectedRows()) {
            result.add(staffTableModel.getElementAt(index));
        }
        return result;
    }

    private void newFilter() {
        RowFilter<StaffTableModel, Object> rf = null;
        try {
            rf = RowFilter.regexFilter("(?i)" + filterText.getText(),1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
