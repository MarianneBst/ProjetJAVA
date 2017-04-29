package model;

import java.util.HashMap;
import java.util.Observable;
import java.util.UUID;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public class Company extends Observable {
    private String name;
    private HashMap<String, StandardDepartment> standardDepartmentList;
    private ManagementDepartment managementDepartment;
    private Boss boss;
    //list for tally of the day

    //Constructor
    public Company(String name, Boss boss) {
        this.name = name;
        this.standardDepartmentList = new HashMap<>();
        this.boss = boss;
        this.managementDepartment = new ManagementDepartment(boss);
    }


    // Getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public ManagementDepartment getManagementDepartment() {
        return managementDepartment;
    }

    public StandardDepartment getStandardDepartmentByName(String name) throws IllegalArgumentException{

        if(name == null){
            throw new IllegalArgumentException("Name null");
        }
        StandardDepartment standardDepartment = standardDepartmentList.get(name);
        if( standardDepartment == null){
            throw new IllegalArgumentException("wrong name");
        }
        return standardDepartment;
    }

    public int getNbStandardDepartment() throws IllegalArgumentException{
        return standardDepartmentList.size();
    }

    public HashMap<UUID, Employee> getAllEmployees(){
        HashMap<UUID, Employee> result = new HashMap<>();

        result.putAll(managementDepartment.getEmployeesList());

        for (StandardDepartment standardDepartment : standardDepartmentList.values()) {
            result.putAll(standardDepartment.getEmployeesList());
        }

        return result;
    }


    // Add and Delete from list
    public void addStandardDepartment(StandardDepartment department) throws IllegalArgumentException{

        if( department == null){
            throw new IllegalArgumentException("null argument");
        }

        standardDepartmentList.put(department.getName(), department);
    }

    public void deleteStandardDepartment(StandardDepartment department){

        if( department == null){
            throw new IllegalArgumentException("null argument");
        }
        if( standardDepartmentList.remove(department.getName()) == null){
            throw new IllegalArgumentException("Department not found");
        }

        standardDepartmentList.remove(department.getName());
    }



}
