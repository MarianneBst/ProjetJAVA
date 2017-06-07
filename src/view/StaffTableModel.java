package view;

import model.Employee;
import model.StandardDepartment;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Marianne
 * on 05/06/2017.
 */
public class StaffTableModel extends AbstractTableModel {

    private String[] header = {"ID", "Name", "Firstname", "Department", "Start Hour", "End Hour", "Credit Hour"};
    private ArrayList<Employee> dataEmployee;

    public StaffTableModel() {
        this.dataEmployee = new ArrayList<>();
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
        if(columnIndex == 3){
            return StandardDepartment.class;
        }
        if(columnIndex == 4 || columnIndex == 5 || columnIndex == 6){
            return LocalDateTime.class;
        }
        else return null;
    }

    @Override
    public int getRowCount() {
        return dataEmployee.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex){
            case 0 :
                return dataEmployee.get(rowIndex).getId();
            case 1 :
                return dataEmployee.get(rowIndex).getName();
            case 2 :
                return dataEmployee.get(rowIndex).getFirstName();
            case 3 :
                return dataEmployee.get(rowIndex).getStandardDepartment();
            case 4 :
                return dataEmployee.get(rowIndex).getStartHour();
            case 5 :
                return dataEmployee.get(rowIndex).getEndHour();
            case 6 :
                return dataEmployee.get(rowIndex).getCreditHour();
            default: return null;
        }

    }

    public Employee getElementAt(int index){
        return dataEmployee.get(index);
    }

    //A quoi servent ces fonctions ?????
    public void addEmployee(Employee employee){
        dataEmployee.add(employee);

        fireTableRowsInserted(dataEmployee.size()-1, dataEmployee.size()-1);
    }

    public void removeEmployee(int index){
        dataEmployee.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public void removeAllEmployee(){
        while (dataEmployee.size() != 0){
            removeEmployee(0);
        }

    }

    public ArrayList<Employee> getDataEmployee() {
        return dataEmployee;
    }

    public void setDataEmployee(ArrayList<Employee> dataEmployee) {
        this.dataEmployee = dataEmployee;
        fireTableDataChanged();
    }
}
