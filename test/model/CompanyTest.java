package model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Created by Marianne
 * on 09/04/2017.
 */
public class CompanyTest {

    private Company company;
    private StandardDepartment standardDepartment;
    private Boss boss;
    private Manager manager;

    @Before
    public void setUp() throws Exception {
        boss = new Boss("Ronan", "Guillaume", "ronan.guilaume@apple.com");
        company = new Company("RONAN INC", boss);
        manager = new Manager("Alphonse", "Guillaume", LocalDateTime.of(1,1,1,1,1,1), LocalDateTime.of(1,1,1,1,1,1), "alphonse.guillaume@apple.com");
        standardDepartment = new StandardDepartment("Design", manager);

    }

    @Test
    public void getNbStandardDepartment() throws Exception {
        assertEquals("Initialisation with 0 standard department failed",0, company.getNbStandardDepartment());
        company.addStandardDepartment(standardDepartment);
        assertEquals("Expected 1 standard department failed",1, company.getNbStandardDepartment());
    }

    @Test
    public void getStandardDepartmentByName() throws Exception{
        company.addStandardDepartment(standardDepartment);
        assertEquals("Wrong name",standardDepartment, company.getStandardDepartmentByName(standardDepartment.getName()));
    }

    @Test
    public void addStandardDepartment() throws Exception {

        company.addStandardDepartment(standardDepartment);
        assertEquals("Expected 1 standard department failed",1, company.getNbStandardDepartment());
        assertEquals("Wrong standard department",standardDepartment, company.getStandardDepartmentByName(standardDepartment.getName()));
    }


    @Test
    public void deleteStandardDepartment() throws Exception {

        company.addStandardDepartment(standardDepartment);
        company.deleteStandardDepartment(standardDepartment);
        assertEquals("Expected 0 department failed",0, company.getNbStandardDepartment());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteStandardDepartmentWithNullArgument() throws Exception {
        company.deleteStandardDepartment(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void deleteEmployeeWithWrongArgument() throws Exception {
        company.deleteStandardDepartment(standardDepartment);
    }

}