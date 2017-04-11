package model;

import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marianne
 * on 04/04/2017.
 */
public class VirtualDepartmentTest {

    private VirtualDepartment department;
    private Employee employee;
    private Manager manager;

    @Before
    public void setUp() throws Exception{
        manager = new Manager("Rene", "Guillaume", new Date(0), new Date(0), new Date(0), "rene.guillaume@apple.com");
        department = new StandardDepartment("Informatique", manager);
        employee = new Employee("Ronan","Guillaume", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));

    }

    @Test
    public void getNbEmployees() throws Exception {
        assertEquals("Initialisation with 0 employee failed",0, department.getNbEmployees());
        department.addEmployee(employee);
        assertEquals("Expected 1 employee failed",1, department.getNbEmployees());
    }

    @Test
    public void getEmployeeByID() throws Exception {
        department.addEmployee(employee);
        assertEquals("Wrong ID",employee, department.getEmployeeByID(employee.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getEmployeeByIDWithNullArgument() throws Exception {
        department.getEmployeeByID(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getEmployeeByIDWithWrongArgument() throws Exception {
        department.getEmployeeByID(employee.getId());
    }

    @Test
    public void addEmployee() throws Exception {
        department.addEmployee(employee);
        assertEquals("Expected 1 employee failed",1, department.getNbEmployees());
        assertEquals("Wrong employee",employee, department.getEmployeeByID(employee.getId()));//verification employee is in the
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEmployeeWithNullArgument() throws Exception {
        department.addEmployee(null);
    }

    @Test
    public void deleteEmployee() throws Exception {
        department.addEmployee(employee);
        department.deleteEmployee(employee);
        assertEquals("Expected 0 employee failed",0, department.getNbEmployees());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteEmployeeWithNullArgument() throws Exception {
        department.deleteEmployee(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void deleteEmployeeWithWrongArgument() throws Exception {
        department.deleteEmployee(employee);
    }


}