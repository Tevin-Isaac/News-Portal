package dao;

import models.Department;
import models.News;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

class Sql2oDepartmentDaoTest {
    private Connection conn;
    private Sql2oDepartmentDao departmentDao;
    private Sql2oNewsDao newsDao;
    private Sql2oUserDao userDao;

    @Before
    public void setUp() throws Exception {
//         String connectionString = "jdbc:h2:~/tevin.db;INIT=RUNSCRIPT from 'classpath:dao/create.sql'";
//        Sql2o sql2o = new Sql2o(connectionString, "tevin", "index");
        String connectionString = "jdbc:postgresql://ec2-18-214-238-28.compute-1.amazonaws.com:5432/d9ffe8pvlkorio"; //!
        Sql2o sql2o = new Sql2o(connectionString, "xwayyrtgsuiqfd", "da122c88c2120d7e0a9715bc103cc0a9d4e70295d156544e72a0b882db3e6fcd"); //!
        departmentDao = new Sql2oDepartmentDao(DB.sql2o);
        newsDao= new Sql2oNewsDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = DB.sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingDepartmentSetsId() throws Exception {
        Department testDepartment = setupDepartment();
        assertNotEquals(0, testDepartment.getId());
    }

    @Test
    public void addedDepartmentsAreReturnedFromGetAll() throws Exception {
        Department testDepartment = setupDepartment();
        assertEquals(1, departmentDao.getAll().size());
    }

    @Test
    public void noDepartmentsReturnsEmptyList() throws Exception {
        assertEquals(0, departmentDao.getAll().size());
    }

    @Test
    public void findByIdReturnsCorrectDepartment() throws Exception {
        Department testDepartment = setupDepartment();
        Department otherDepartment = setupDepartment();
        assertEquals(testDepartment, departmentDao.findById(testDepartment.getId()));
    }

//    @Test
//    public void updateCorrectlyUpdatesAllFields() throws Exception {
//        Department testDepartment = setupDepartment();
//        departmentDao.update(testDepartment.getId(), "a", "b", 4);
//        Department foundDepartment = departmentDao.findById(testDepartment.getId());
//        assertEquals("a", foundDepartment.getDepartmentName());
//        assertEquals("b", foundDepartment.getDepartmentDescription());
//        assertEquals(4, foundDepartment.getTotalEmployees());
//    }

    @Test
    public void deleteByIdDeletesCorrectDepartment() throws Exception {
        Department testDepartment = setupDepartment();
        Department otherDepartment = setupDepartment();
        departmentDao.deleteById(testDepartment.getId());
        assertEquals(1, departmentDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        Department testDepartment = setupDepartment();
        Department otherDepartment = setupDepartment();
        departmentDao.clearAll();
        assertEquals(0, departmentDao.getAll().size());
    }

    @Test
    public void DepartmentReturnsNewsCorrectly() throws Exception {
        News testNews = new News("BMW", "Electric car launch date revealed", 3);
        newsDao.add(testNews);

        News otherNews = new News("Health", "New covid vaccine discovered", 5);
        newsDao.add(otherNews);

        Department testDepartment = setupDepartment();
        departmentDao.add(testDepartment);
        departmentDao.addDepartmentToNews(testDepartment, testNews);
        departmentDao.addDepartmentToNews(testDepartment, otherNews);

        News[] news = {testNews, otherNews};
        assertEquals(Arrays.asList(news), departmentDao.getAllNewsForDepartments(testDepartment.getId()));
    }

    //helpers
    public Department setupDepartment() {
        Department department = new Department("Finance","Maintain Financial Records",13);
        departmentDao.add(department);
        return department;
    }


}