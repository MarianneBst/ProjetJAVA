package view;

import model.Employee;
import model.StandardDepartment;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Marianne
 * on 05/06/2017.
 */
public class StaffTableModel extends AbstractTableModel {

    private String[] header = {"ID", "Name", "Firstname", "Department", "Start Hour", "End Hour", "Credit Hour"};
    private ArrayList<Employee> dataEmployee;

    /**
     * Instantiates a new Staff table model.
     */
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
        if(columnIndex == 4 || columnIndex == 5){
            return LocalDateTime.class;
        }
        if(columnIndex == 6){
            return Integer.class;
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
                return LocalTime.of(dataEmployee.get(rowIndex).getStartHour().getHour(),
                        dataEmployee.get(rowIndex).getStartHour().getMinute());
            case 5 :
                return LocalTime.of(dataEmployee.get(rowIndex).getEndHour().getHour(),
                        dataEmployee.get(rowIndex).getEndHour().getMinute());
            case 6 :
                int minCredit = dataEmployee.get(rowIndex).getCreditHour();
                String result = "";

                if(minCredit < 0){
                    result += "-";
                    minCredit *= -1;
                }
                int resCredit = 0;
                int tmpHour = 0, tmpMin = 0;

                tmpHour = minCredit / 60;
                tmpMin = minCredit - (tmpHour*60);
                return result + tmpHour + " h "+ tmpMin;
            default: return null;
        }

    }

    /**
     * Get element at employee.
     *
     * @param index the index
     * @return the employee
     */
    public Employee getElementAt(int index){
        return dataEmployee.get(index);
    }

    /**
     * Add employee.
     *
     * @param employee the employee
     */
//A quoi servent ces fonctions ?????
    public void addEmployee(Employee employee){
        dataEmployee.add(employee);

        fireTableRowsInserted(dataEmployee.size()-1, dataEmployee.size()-1);
    }

    /**
     * Remove employee.
     *
     * @param index the index
     */
    public void removeEmployee(int index){
        dataEmployee.remove(index);
        fireTableRowsDeleted(index, index);
    }

    /**
     * Remove all employee.
     */
    public void removeAllEmployee(){
        while (dataEmployee.size() != 0){
            removeEmployee(0);
        }

    }

    /**
     * Gets data employee.
     *
     * @return the data employee
     */
    public ArrayList<Employee> getDataEmployee() {
        return dataEmployee;
    }

    /**
     * Sets data employee.
     *
     * @param dataEmployee the data employee
     */
    public void setDataEmployee(ArrayList<Employee> dataEmployee) {
        this.dataEmployee = dataEmployee;
        fireTableDataChanged();
    }
}
