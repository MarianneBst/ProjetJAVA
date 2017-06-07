package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public class Company extends Observable implements Serializable {
    private String name;
    private ArrayList<StandardDepartment> standardDepartmentList;
    private ManagementDepartment managementDepartment;
    private Boss boss;
    //list for tally of the day

    //Constructor
    public Company(String name, Boss boss) {
        this.name = name;
        this.standardDepartmentList = new ArrayList<>();
        this.boss = boss;
        this.managementDepartment = new ManagementDepartment(boss);
    }


    // Getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setChanged();
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
        setChanged();
    }

    public ManagementDepartment getManagementDepartment() {
        return managementDepartment;
    }

    public StandardDepartment getStandardDepartmentByName(String name) throws IllegalArgumentException{

        StandardDepartment result = null;

        if(name == null){
            throw new IllegalArgumentException("Name null");
        }

        for (StandardDepartment standardDpt: standardDepartmentList) {
            if(standardDpt.getName().equals(name)) {
             result = standardDpt;
             break;// saut deux paranthèses
            }
        }

        if( result == null){
            throw new IllegalArgumentException("Wrong name");// = pb au niveau de l'argument
        }
        return result;
    }

    public int getNbStandardDepartment() throws IllegalArgumentException{
        return standardDepartmentList.size();
    }

    public ArrayList<Employee> getAllEmployees(){
        ArrayList<Employee> result = new ArrayList<>();

        //Add the managers
        result.addAll(managementDepartment.getEmployeesList());

        for (StandardDepartment standardDepartment : standardDepartmentList) {
            result.addAll(standardDepartment.getEmployeesList());
        }

        return result;
    }

    public ArrayList<StandardDepartment> getStandardDepartmentList() {
        return standardDepartmentList;
    }

    // Add and Delete from list
    public void addStandardDepartment(StandardDepartment department) throws IllegalArgumentException{

        if( department == null){
            throw new IllegalArgumentException("Null argument");
        }

        standardDepartmentList.add(department);
        managementDepartment.addEmployee((Manager) department.getLeader());
        setChanged();
    }

    public void deleteStandardDepartment(StandardDepartment department){

        if( department == null){
            throw new IllegalArgumentException("Null argument");
        }
        if( !standardDepartmentList.remove(department)){
            throw new IllegalArgumentException("Department not found");
        }
        setChanged();
    }


    //Methode pour le controller
    public void addEmployeeIntoStandardDpt(Employee employee){
        employee.getStandardDepartment().addEmployee(employee);
        setChanged();
    }
    public void removeEmployeeFromStandardDpt(Employee employee){
        employee.getStandardDepartment().deleteEmployee(employee);
        setChanged();
    }

    public void modifyEmployee(Employee employeeToModify, Employee newEmployee){
        employeeToModify.setStandardDepartment(newEmployee.getStandardDepartment());
        employeeToModify.setCreditHour(newEmployee.getCreditHour());
        employeeToModify.setEndHour(newEmployee.getEndHour());
        employeeToModify.setStartHour(newEmployee.getStartHour());
        employeeToModify.setName(newEmployee.getName());
        employeeToModify.setFirstName(newEmployee.getFirstName());
        setChanged();
    }

}
