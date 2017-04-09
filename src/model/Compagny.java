package model;

import java.util.HashMap;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public class Compagny {
    private String name;
    private HashMap<String, StandardDepartment> listStandardDepartment;
    private ManagementDepartment managementDepartment;
    private Boss boss;

    public Compagny(String name, Boss boss) {
        this.name = name;
        this.listStandardDepartment = new HashMap<>();
        this.boss = boss;
        this.managementDepartment = new ManagementDepartment(boss);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StandardDepartment getStandardDepartementByName(String name){
        return listStandardDepartment.get(name);
    }

    public void addStandardDepartment(StandardDepartment department){
        listStandardDepartment.put(department.getName(), department);
    }

    public int getNbStandardDepartment(){
        return listStandardDepartment.size();
    }

    public void deleteStandardDepartment(StandardDepartment department){
        listStandardDepartment.remove(department.getName());
    }


}
