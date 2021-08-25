package dao;

import models.Department;
import models.News;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import org.sql2o.Connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql2oNewsDaoTest {
    private Sql2oNewsDao newsDao;
    private Sql2oDepartmentDao departmentDao;
    private  Sql2oUserDao userDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingNewsSetsId() throws Exception {
        News testNews = setupNews();
        assertEquals(1, testNews.getId());
    }

    @Test
    public void addNewsToDepartmentAddsNewsCorrectly() throws Exception {

        Department testDepartment = setupDepartment();
        Department altDepartment = setupAltDepartment();

        departmentDao.add(testDepartment);
        departmentDao.add(altDepartment);

        News testNews = setupNews();

        newsDao.add(testNews);

        newsDao.addNewsToDepartment(testNews, testDepartment);
        newsDao.addNewsToDepartment(testNews, altDepartment);

        assertEquals(2, newsDao.getAllDepartmentNews(testNews.getId()).size());
    }

    @Test
    public void addedNewsAreReturnedFromGetAll() throws Exception {
        News testNews = setupNews();
        assertEquals(1, newsDao.getAll().size());
    }

    @Test
    public void getNewsInDepartment() {
        Department testDepartment = setupDepartment();
        News testNews1 = setupNewsForDepartment();
        News testNews2 = setupNewsForDepartment();
        assertEquals(0, newsDao.getAllDepartmentNews(testDepartment.getId()).size());
    }

    @Test
    public void deleteById() {
        News testNews = setupNews();
        News testNews2 = setupNews();
        assertEquals(2, newsDao.getAll().size());
        newsDao.deleteById(testNews.getId());
        assertEquals(1, newsDao.getAll().size());
    }

    @Test
    public void clearAll() {
        News testNews = setupNews();
        News testNews2 = setupNews();
        newsDao.clearAll();
        assertEquals(0, newsDao.getAll().size());
    }

    @Test
    public void deletingDepartmentAlsoUpdatesJoinTable() throws Exception {
        News testNews = new News("Sushi", "New recipe Unveiled", 5);
        newsDao.add(testNews);

        Department testDepartment = setupDepartment();
        departmentDao.add(testDepartment);

        Department altDepartment = setupAltDepartment();
        departmentDao.add(altDepartment);

        departmentDao.addDepartmentToNews(testDepartment, testNews);
        departmentDao.addDepartmentToNews(altDepartment, testNews);

        departmentDao.deleteById(testDepartment.getId());
        assertEquals(0, departmentDao.getAllNewsForDepartments(testDepartment.getId()).size() );
    }

    //helpers
    public News setupNews() {
        News news= new News("Sushi", "New recipe Unveiled", 5);
        newsDao.add(news);
        return news;
    }

    public Department setupDepartment() {
        Department department = new Department("Finance", "Maintains Financial records",13);
        departmentDao.add(department);
        return department;
    }

    public News setupNewsForDepartment() {
        News news= new News("Sushi", "New recipe Unveiled", 5);
        newsDao.add(news);
        return news;
    }

    public Department setupAltDepartment() {
        Department department = new Department("Human Resource", "check Employees welfare", 4 );
        departmentDao.add(department);
        return department;
    }
}