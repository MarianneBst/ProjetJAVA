package model;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public abstract class VirtualDepartment {
    private String name;
    private HashMap<UUID, Employee> employeesList;
    private Leader leader;

    public VirtualDepartment(String name, Leader leader) {
        this.name = name;
        this.employeesList = new HashMap<>();
        this.leader = leader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }

    public int getNbEmployees(){
        return employeesList.size();
    }

    public Employee getEmployeeByID(UUID id) throws IllegalArgumentException{

        if(id == null){
            throw new IllegalArgumentException("ID null");
        }
        Employee employee = employeesList.get(id);
        if( employee == null){
            throw new IllegalArgumentException("wrong ID");
        }
        return employee;
    }

    public void addEmployee(Employee employee) throws IllegalArgumentException{

        if( employee == null){
            throw new IllegalArgumentException("null argument");
        }
        employeesList.put(employee.getId(), employee);
    }

    public void deleteEmployee(Employee employee) throws IllegalArgumentException{
        if( employee == null){
            throw new IllegalArgumentException("null argument");
        }
        if( employeesList.remove(employee.getId()) == null){
            throw new IllegalArgumentException("Employee not found");
        }
        employeesList.remove(employee.getId());
    }

    @Override
    public String toString() {
        return "Name : " + name + "Number employees : " + getNbEmployees() + "Leader : " + leader;
    }
}
