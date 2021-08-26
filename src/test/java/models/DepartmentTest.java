package models;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DepartmentTest {

    @Test
    public void departmentInstantiatesCorrectly() {
        Department department = new Department("Finance", "Maintains Financial records", 13);
        assertEquals(true, department instanceof Department);
    }

    @Test
    public void getsDepartmentName() {
        Department department = new Department("Finance", "Maintains Financial records", 13);
        assertEquals("Finance", department.getDepartmentName());
    }

    @Test
    public void getsDepartmentDescription() {
        Department department = new Department("Finance", "Maintains Financial records", 13);
        assertEquals("Maintains Financial records", department.getDepartmentDescription());
    }

    @Test
    public void getsTotalEmployees() {
        Department department = new Department("Finance", "Maintains Financial records", 13);
        assertEquals(13,department.getTotalEmployees());
    }

    @Test
    public void setsDepartmentName() {
        Department department = new Department("Finance", "Maintains Financial records", 13);
        department.setName("Finance");
        assertEquals("Finance", department.getDepartmentName());
    }

    @Test
    public void setsDepartmentDescription() {
        Department department = new Department("Finance", "Maintains Financial records", 13);
        department.setDescription("Maintains Financial records");
        assertEquals("Maintains Financial records", department.getDepartmentDescription());
    }

    @Test
    public void setsTotalEmployees() {
        Department department = new Department("Finance", "Maintains Financial records", 13);
        department.setTotalEmployees(13);
        assertEquals(13,department.getTotalEmployees());
    }

    @Test
    public void setId() {
        Department department = new Department("Finance", "Maintains Financial records", 13);
        department.setId(1);
        assertEquals(1,department.getId());
    }
}