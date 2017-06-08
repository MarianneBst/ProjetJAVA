package model;

import java.io.Serializable;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public class StandardDepartment extends VirtualDepartment implements Serializable {

    /**
     * Instantiates a new Standard department.
     *
     * @param name    the name
     * @param manager the manager
     */
    public StandardDepartment(String name, Manager manager) {
        super(name, manager);
        manager.setStandardDepartment(this);
    }

    @Override
    void addEmployee(Employee employee) throws IllegalArgumentException {
        super.addEmployee(employee);
        employee.setStandardDepartment(this);
    }

    @Override
    public String toString() {
        return getName();
    }



}
