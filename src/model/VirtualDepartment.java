package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public abstract class VirtualDepartment implements Serializable {
    private String name;
    private ArrayList<Employee> employeesList;
    private Leader leader;

    /**
     * Instantiates a new Virtual department.
     *
     * @param name   the name
     * @param leader the leader
     */
//Constructor
    public VirtualDepartment(String name, Leader leader) {
        this.name = name;
        this.employeesList = new ArrayList<>();
        this.leader = leader;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
// Setter and Getter
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * Gets leader.
     *
     * @return the leader
     */
    public Leader getLeader() {
        return leader;
    }

    /**
     * Sets leader.
     *
     * @param leader the leader
     */
    void setLeader(Leader leader) {
        this.leader = leader;
    }

    /**
     * Get nb employees int.
     *
     * @return the int
     */
    public int getNbEmployees(){
        return employeesList.size();
    }

    /**
     * Gets employee by id.
     *
     * @param id the id
     * @return the employee by id
     * @throws IllegalArgumentException the illegal argument exception
     */
    public Employee getEmployeeByID(UUID id) throws IllegalArgumentException{

        Employee result = null;//ne pas laisser un objet null tout seul soit lui attribuer valeur soit supprimer

        if(id == null){
            throw new IllegalArgumentException("ID null");
        }
        for (Employee employee: employeesList) {
            if(employee.getId().equals(id)){
                result = employee;
                break;
            }
        }

        if( result == null){
            throw new IllegalArgumentException("Wrong ID");
        }
        return result;
    }

    /**
     * Gets employees list.
     *
     * @return the employees list
     */
    public ArrayList<Employee> getEmployeesList() {
        return employeesList;
    }


    /**
     * Add employee.
     *
     * @param employee the employee
     * @throws IllegalArgumentException the illegal argument exception
     */
// Add and Delete from a list
    void addEmployee(Employee employee) throws IllegalArgumentException{

        if( employee == null){
            throw new IllegalArgumentException("Null argument");
        }
        employeesList.add(employee);
    }

    /**
     * Delete employee.
     *
     * @param employee the employee
     * @throws IllegalArgumentException the illegal argument exception
     */
    void deleteEmployee(Employee employee) throws IllegalArgumentException{
        if( employee == null){
            throw new IllegalArgumentException("Null argument");
        }
        if(!employeesList.remove(employee)){
            if (employee.getClass() == Manager.class){
                throw new IllegalArgumentException("You are trying to remove a manager");
            }
            throw new IllegalArgumentException("Employee not found");
        }
    }


    @Override
    public String toString() {
        return "Name : " + name + "Number employees : " + getNbEmployees() + "Leader : " + leader;
    }
}
