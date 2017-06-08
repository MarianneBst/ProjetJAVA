package view;

import model.StandardDepartment;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by Marianne
 * on 27/05/2017.
 */
public class DptTableModel extends AbstractTableModel{
    private String[] header = {"Name", "Number of emloyees"};
    private ArrayList<StandardDepartment> dataDpt;

    /**
     * Instantiates a new Dpt table model.
     */
    public DptTableModel() {
        this.dataDpt = new ArrayList<>();
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 0){
            return String.class;
        }

        return Integer.class;
    }

    @Override
    public int getRowCount() {
        return dataDpt.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return dataDpt.get(rowIndex).getName();
        }
        else{
            return dataDpt.get(rowIndex).getNbEmployees();
        }
    }

    /**
     * Get element at standard department.
     *
     * @param index the index
     * @return the standard department
     */
    public StandardDepartment getElementAt(int index){
        return dataDpt.get(index);
    }

    /**
     * Add dpt.
     *
     * @param standardDepartment the standard department
     */
    public void addDpt(StandardDepartment standardDepartment){
        dataDpt.add(standardDepartment);

        fireTableRowsInserted(dataDpt.size()-1, dataDpt.size()-1);
    }

    /**
     * Remove dpt.
     *
     * @param index the index
     */
    public void removeDpt(int index){
        dataDpt.remove(index);
        fireTableRowsDeleted(index, index);
    }

    /**
     * Remove all dpt.
     */
    public void removeAllDpt(){
        while (dataDpt.size() != 0){
            removeDpt(0);
        }

    }

    /**
     * Gets data dpt.
     *
     * @return the data dpt
     */
    public ArrayList<StandardDepartment> getDataDpt() {
        return dataDpt;
    }

    /**
     * Sets data dpt.
     *
     * @param dataDpt the data dpt
     */
    public void setDataDpt(ArrayList<StandardDepartment> dataDpt) {
        this.dataDpt = dataDpt;
        fireTableDataChanged();
    }
}
