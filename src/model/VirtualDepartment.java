package model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public abstract class VirtualDepartment {
    private String name;
    private ArrayList<Employee> employeesList;
    private Leader leader;

    //Constructor
    public VirtualDepartment(String name, Leader leader) {
        this.name = name;
        this.employeesList = new ArrayList<>();
        this.leader = leader;
    }

    // Setter and Getter
    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public Leader getLeader() {
        return leader;
    }

    void setLeader(Leader leader) {
        this.leader = leader;
    }

    public int getNbEmployees(){
        return employeesList.size();
    }

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

    public ArrayList<Employee> getEmployeesList() {
        return employeesList;
    }



    // Add and Delete from a list
    void addEmployee(Employee employee) throws IllegalArgumentException{

        if( employee == null){
            throw new IllegalArgumentException("Null argument");
        }
        employeesList.add(employee);
    }

    void deleteEmployee(Employee employee) throws IllegalArgumentException{
        if( employee == null){
            throw new IllegalArgumentException("Null argument");
        }
        if(!employeesList.remove(employee)){
            throw new IllegalArgumentException("Employee not found");
        }
    }


    @Override
    public String toString() {
        return "Name : " + name + "Number employees : " + getNbEmployees() + "Leader : " + leader;
    }
}
