package model;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Marianne
 * on 09/04/2017.
 */
public class CompagnyTest {

    private Compagny compagny;
    private StandardDepartment standardDepartment;
    private Boss boss;
    private Manager manager;

    @Before
    public void setUp() throws Exception {
        boss = new Boss("Ronan", "Guillaume", "ronan.guilaume@apple.com");
        compagny = new Compagny("RONAN INC", boss);
        manager = new Manager("Alphonse", "Guillaume", new Date(0), new Date(0), new Date(0), "alphonse.guillaume@apple.com");
        standardDepartment = new StandardDepartment("Design", manager);

    }

    @Test
    public void getNbStandardDepartment() throws Exception {
        assertEquals("Initialisation with 0 standard department failed",0, compagny.getNbStandardDepartment());
        compagny.addStandardDepartment(standardDepartment);
        assertEquals("Expected 1 standard department failed",1, compagny.getNbStandardDepartment());
    }

    @Test
    public void getStandardDepartmentByName() throws Exception{
        compagny.addStandardDepartment(standardDepartment);
        assertEquals("Wrong name",standardDepartment, compagny.getStandardDepartmentByName(standardDepartment.getName()));
    }

    @Test
    public void addStandardDepartment() throws Exception {

        compagny.addStandardDepartment(standardDepartment);
        assertEquals("Expected 1 standard department failed",1, compagny.getNbStandardDepartment());
        assertEquals("Wrong standard department",standardDepartment, compagny.getStandardDepartmentByName(standardDepartment.getName()));
    }


    @Test
    public void deleteStandardDepartment() throws Exception {

        compagny.addStandardDepartment(standardDepartment);
        compagny.deleteStandardDepartment(standardDepartment);
        assertEquals("Expected 0 department failed",0, compagny.getNbStandardDepartment());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteStandardDepartmentWithNullArgument() throws Exception {
        compagny.deleteStandardDepartment(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void deleteEmployeeWithWrongArgument() throws Exception {
        compagny.deleteStandardDepartment(standardDepartment);
    }

}