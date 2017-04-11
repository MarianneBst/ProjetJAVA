package model;

import java.util.HashMap;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public class Compagny {
    private String name;
    private HashMap<String, StandardDepartment> standardDepartmentList;
    private ManagementDepartment managementDepartment;
    private Boss boss;

    public Compagny(String name, Boss boss) {
        this.name = name;
        this.standardDepartmentList = new HashMap<>();
        this.boss = boss;
        this.managementDepartment = new ManagementDepartment(boss);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void addStandardDepartment(StandardDepartment department) throws IllegalArgumentException{

        if( department == null){
            throw new IllegalArgumentException("null argument");
        }

        standardDepartmentList.put(department.getName(), department);
    }

    public int getNbStandardDepartment() throws IllegalArgumentException{
        return standardDepartmentList.size();
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


    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public ManagementDepartment getManagementDepartment() {
        return managementDepartment;
    }
}
