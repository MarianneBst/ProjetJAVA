package view;

import model.Tally;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Marianne
 * on 08/06/2017.
 */
public class TallyTableModel extends AbstractTableModel {
    private String[] header = {"Id", "Name","First name", "Date", "Hour"};
    private ArrayList<Tally> dataTally;

    /**
     * Instantiates a new Tally table model.
     */
    public TallyTableModel() {
        dataTally = new ArrayList<>();
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 0){
            return UUID.class;
        }
        if(columnIndex == 1 || columnIndex == 2){
            return String.class;
        }
        if(columnIndex == 3 || columnIndex == 4){
            return LocalDateTime.class;
        }
        else return null;
    }

    @Override
    public int getRowCount() {
        return dataTally.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0 :
                return dataTally.get(rowIndex).getEmployee().getId();
            case 1 :
                return dataTally.get(rowIndex).getEmployee().getName();
            case 2 :
                return dataTally.get(rowIndex).getEmployee().getFirstName();
            case 3 :
                return dataTally.get(rowIndex).getCheckDate();
            case 4 :
                return dataTally.get(rowIndex).getCheckDate();
            default: return null;
        }

    }

    /**
     * Get element at tally.
     *
     * @param index the index
     * @return the tally
     */
    public Tally getElementAt(int index){
        return dataTally.get(index);
    }

    /**
     * Add tally.
     *
     * @param tally the tally
     */
//A quoi servent ces fonctions ?????
    public void addTally(Tally tally){
        dataTally.add(tally);

        fireTableRowsInserted(dataTally.size()-1, dataTally.size()-1);
    }

    /**
     * Remove tally.
     *
     * @param index the index
     */
    public void removeTally(int index){
        dataTally.remove(index);
        fireTableRowsDeleted(index, index);
    }

    /**
     * Remove all tallies.
     */
    public void removeAllTallies(){
        while (dataTally.size() != 0){
            removeTally(0);
        }

    }

    /**
     * Gets data tally.
     *
     * @return the data tally
     */
    public ArrayList<Tally> getDataTally() {
        return dataTally;
    }

    /**
     * Sets data tally.
     *
     * @param dataTally the data tally
     */
    public void setDataTally(ArrayList<Tally> dataTally) {
        this.dataTally = dataTally;
        fireTableDataChanged();
    }
}
