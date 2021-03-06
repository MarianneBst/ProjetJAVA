package view;

import model.Company;
import model.StandardDepartment;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
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
    private JTextField filterText;
    private DptTableModel dptTableModel;
    private DptJDialog dptJDialog;
    private TableRowSorter sorter;

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

        //créer un fliter
        sorter = new TableRowSorter<DptTableModel>(dptTableModel);
        departmentTable.setRowSorter(sorter);

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

    private void newFilter() {
        RowFilter<DptTableModel, Object> rf = null;
        try {
            rf = RowFilter.regexFilter("(?i)" + filterText.getText(),0);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
