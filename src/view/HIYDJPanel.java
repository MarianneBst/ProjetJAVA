package view;

import model.Company;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;

/**
 * Created by Marianne
 * on 27/05/2017.
 */
public class HIYDJPanel extends JPanel{
    private JPanel mainPanel;
    private JTable tallyTable;
    private JTextField filterName;
    private JTextField filterFirstname;
    private JTextField filterDate;
    private JTextField filterHour;
    private JTextField filterDpt;
    private TallyTableModel tallyTableModel;
    private TableRowSorter sorter;

    /**
     * Instantiates a new Hiydj panel.
     */
    public HIYDJPanel() {
        setName("How is your day ?");
        setSize(700,420);
        setLayout(new BorderLayout());
        setMinimumSize(getSize());
        add(mainPanel);

        tallyTableModel = new TallyTableModel();
        tallyTable.setModel(tallyTableModel);
        tallyTable.setAutoCreateRowSorter(true);

        //créer un fliter
        sorter = new TableRowSorter<TallyTableModel>(tallyTableModel);
        tallyTable.setRowSorter(sorter);

        //ajoute un listener au textfield NAME
        filterName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                newFilter(filterName,1);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                newFilter(filterName,1);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                newFilter(filterName,1);
            }
        });

        //ajoute un listener au textfield FIRSTNAME
        filterFirstname.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                newFilter(filterFirstname,2);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                newFilter(filterFirstname,2);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                newFilter(filterFirstname,2);
            }
        });

        //ajoute un listener au textfield DATE
        filterDate.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                newFilter(filterDate,3);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                newFilter(filterDate,3);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                newFilter(filterDate,3);
            }
        });

        //ajoute un listener au textfield HOUR
        filterHour.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                newFilter(filterHour,4);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                newFilter(filterHour,4);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                newFilter(filterHour,4);
            }
        });

        //ajoute un listener au textfield DPT
        filterDpt.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                newFilter(filterDpt,5);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                newFilter(filterDpt,5);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                newFilter(filterDpt,5);
            }
        });
    }

    /**
     * My update.
     *
     * @param company the company
     */
    public void myUpdate(Company company) {
        tallyTableModel.setDataTally(company.getAllTalliesOfToday());
    }

    private void newFilter(JTextField filterText, int index) {
        RowFilter<DptTableModel, Object> rf = null;
        try {
            rf = RowFilter.regexFilter("(?i)" + filterText.getText(),index);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
}
