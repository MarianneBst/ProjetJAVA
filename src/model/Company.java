package model;

import java.io.Serializable;
import java.time.LocalDate;
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

    /**
     * Instantiates a new Company.
     *
     * @param name the name
     * @param boss the boss
     */
//Constructor
    public Company(String name, Boss boss) {
        this.name = name;
        this.standardDepartmentList = new ArrayList<>();
        this.boss = boss;
        this.managementDepartment = new ManagementDepartment(boss);
    }


    /**
     * Gets name.
     *
     * @return the name
     */
// Getter and setter
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
        setChanged();
    }

    /**
     * Gets boss.
     *
     * @return the boss
     */
    public Boss getBoss() {
        return boss;
    }

    /**
     * Sets boss.
     *
     * @param boss the boss
     */
    public void setBoss(Boss boss) {
        this.boss = boss;
        setChanged();
    }

    /**
     * Gets management department.
     *
     * @return the management department
     */
    public ManagementDepartment getManagementDepartment() {
        return managementDepartment;
    }

    /**
     * Gets standard department by name.
     *
     * @param name the name
     * @return the standard department by name
     * @throws IllegalArgumentException the illegal argument exception
     */
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

    /**
     * Gets nb standard department.
     *
     * @return the nb standard department
     * @throws IllegalArgumentException the illegal argument exception
     */
    public int getNbStandardDepartment() throws IllegalArgumentException{
        return standardDepartmentList.size();
    }

    /**
     * Get all employees array list.
     *
     * @return the array list
     */
    public ArrayList<Employee> getAllEmployees(){
        ArrayList<Employee> result = new ArrayList<>();

        //Add the managers
        result.addAll(managementDepartment.getEmployeesList());

        result.addAll(getOnlyEmployees());

        return result;
    }

    public ArrayList<Employee> getOnlyEmployees(){
        ArrayList<Employee> result = new ArrayList<>();

        for (StandardDepartment standardDepartment : standardDepartmentList) {
            result.addAll(standardDepartment.getEmployeesList());
        }

        return result;
    }

    /**
     * Gets standard department list.
     *
     * @return the standard department list
     */
    public ArrayList<StandardDepartment> getStandardDepartmentList() {
        return standardDepartmentList;
    }

    /**
     * Add standard department.
     *
     * @param department the department
     * @throws IllegalArgumentException the illegal argument exception
     */
// Add and Delete from list
    public void addStandardDepartment(StandardDepartment department) throws IllegalArgumentException{

        if( department == null){
            throw new IllegalArgumentException("Null argument");
        }

        standardDepartmentList.add(department);
        managementDepartment.addEmployee((Manager) department.getLeader());
        setChanged();
    }

    /**
     * Delete standard department.
     *
     * @param department the department
     * @throws IllegalStateException the illegal state exception
     */
    public void deleteStandardDepartment(StandardDepartment department)throws IllegalStateException{

        if (department.getNbEmployees() != 0){
            throw new IllegalStateException("You must move all the employees to another department");
        }

        if( department == null){
            throw new IllegalArgumentException("Null argument");
        }
        if( !standardDepartmentList.remove(department)){
            throw new IllegalArgumentException("Department not found");
        }
        managementDepartment.deleteEmployee((Employee) department.getLeader());
        setChanged();
    }


    /**
     * Add employee into standard dpt.
     *
     * @param employee the employee
     */
//Methode pour le controller
    public void addEmployeeIntoStandardDpt(Employee employee){
        employee.getStandardDepartment().addEmployee(employee);
        setChanged();
    }

    /**
     * Remove employee from standard dpt.
     *
     * @param employee the employee
     */
    public void removeEmployeeFromStandardDpt(Employee employee){
        employee.getStandardDepartment().deleteEmployee(employee);
        setChanged();
    }

    /**
     * Modify employee.
     *
     * @param employeeToModify the employee to modify
     * @param newEmployee      the new employee
     */
    public void modifyEmployee(Employee employeeToModify, Employee newEmployee){
        employeeToModify.setStandardDepartment(newEmployee.getStandardDepartment());
        employeeToModify.setEndHour(newEmployee.getEndHour());
        employeeToModify.setStartHour(newEmployee.getStartHour());
        employeeToModify.setName(newEmployee.getName());
        employeeToModify.setFirstName(newEmployee.getFirstName());
        setChanged();
    }

    /**
     * Modify dpt.
     *
     * @param dptToModify the dpt to modify
     * @param dptCreated  the dpt created
     */
    public void modifyDpt(StandardDepartment dptToModify, StandardDepartment dptCreated) {
        dptToModify.setName(dptCreated.getName());
        setChanged();
    }

    /**
     * Add tally.
     *
     * @param tally the tally
     */
    public void addTally(Tally tally) {
        getStandardDepartmentByName(tally.getEmployee().getStandardDepartment().getName()).getEmployeeByID(tally.getEmployee().getId()).addTally(tally);
        setChanged();
    }

    /**
     * Gets all tallies.
     *
     * @return the all tallies
     */
    public ArrayList<Tally> getAllTallies() {
        ArrayList<Tally> result = new ArrayList<>();
        ArrayList<Employee> employees = getAllEmployees();

        for (Employee employee : employees) {
            result.addAll(employee.getTallies());
        }
        return result;
    }

    /**
     * Gets all tallies of today.
     *
     * @return the all tallies of today
     */
    public ArrayList<Tally> getAllTalliesOfToday() {
        ArrayList<Tally> result = new ArrayList<>();

        for (Tally tally : getAllTallies()) {
            if (tally.getCheckDate().toLocalDate().compareTo(LocalDate.now()) == 0){
                result.add(tally);
            }
        }

        return result;
    }
}
