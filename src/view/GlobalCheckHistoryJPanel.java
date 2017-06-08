package view;

import model.Company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Marianne
 * on 27/05/2017.
 */
public class GlobalCheckHistoryJPanel extends JPanel{
    private JPanel mainPanel;
    private JTable tallyTable;
    private TallyTableModel tallyTableModel;

    /**
     * Instantiates a new Global check history j panel.
     */
    public GlobalCheckHistoryJPanel() {
        setName("Global check in/out history");
        setSize(700,420);
        setLayout(new BorderLayout());
        setMinimumSize(getSize());
        add(mainPanel);

        tallyTableModel = new TallyTableModel();
        tallyTable.setModel(tallyTableModel);
        tallyTable.setAutoCreateRowSorter(true);
    }

    /**
     * My update.
     *
     * @param company the company
     */
    public void myUpdate(Company company) {
        tallyTableModel.setDataTally(company.getAllTallies());
    }
}
