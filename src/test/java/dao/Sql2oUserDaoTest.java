package dao;

import models.Department;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;

//public class Sql2oUserDaoTest {
//   private Connection conn;
//   private Sql2oUserDao userDao;
//   private Sql2oDepartmentDao departmentDao;
//   private Sql2oNewsDao newsDao;
//
//   @Before
//   public void setUp() throws Exception {
////      String connectionString = "jdbc:h2:~/tevin.db;INIT=RUNSCRIPT from 'classpath:dao/create.sql'";
////      Sql2o sql2o = new Sql2o(connectionString, "tevin", "index");
//      String connectionString = "jdbc:postgresql://ec2-18-214-238-28.compute-1.amazonaws.com:5432/d9ffe8pvlkorio"; //!
//      Sql2o sql2o = new Sql2o(connectionString, "xwayyrtgsuiqfd", "da122c88c2120d7e0a9715bc103cc0a9d4e70295d156544e72a0b882db3e6fcd"); //!
//      userDao = new Sql2oUserDao(sql2o);
//      departmentDao = new Sql2oDepartmentDao(sql2o);
//      newsDao = new Sql2oNewsDao(sql2o);
//      conn = DB.sql2o.open();
//   }
//
//   @After
//   public void tearDown() throws Exception {
//      conn.close();
//   }
//
//   @Test
//   public void addingUserSetsId() throws Exception {
//      User testUser = setupUser();
//      assertEquals(1, testUser.getId());
//   }
//
//   @Test
//   public void getAll() throws Exception {
//      User user1 = setupUser();
//      User user2 = setupUser();
//      assertEquals(2,userDao.getAll().size());
//   }
//
//   @Test
//   public void getAllUsersByDepartment() throws Exception {
//      Department testDepartment = setupDepartment();
//      Department otherDepartment = setupDepartment();
//
//      User user1 = setupUserForDepartment(testDepartment);
//      User user2 = setupUserForDepartment(testDepartment);
//      User userForOtherDepartment = setupUserForDepartment(otherDepartment);
//      assertEquals(3, userDao.getAllUsersByDepartment(testDepartment.getId()).size());
//   }
//
//   @Test
//   public void deleteById() throws Exception {
//      User testUser = setupUser();
//      User otherUser = setupUser();
//      assertEquals(2, userDao.getAll().size());
//      userDao.deleteById(testUser.getId());
//      assertEquals(1, userDao.getAll().size());
//   }
//
//   @Test
//   public void clearAll() throws Exception {
//      User testUser = setupUser();
//      User otherUser = setupUser();
//      userDao.clearAll();
//      assertEquals(0, userDao.getAll().size());
//   }
//
//   //helpers
//   private User setupUser() {
//      User user = new User("Gideon", 1, "Maintains System");
//      userDao.add(user);
//      return user;
//   }
//
//   private User setupUserForDepartment(Department otherDepartment) {
//      User user = new User("Gideon", 1, "Maintains system");
//      userDao.add(user);
//      return user;
//   }
//
//   private Department setupDepartment() {
//      Department department = new Department("Finance", "Maintains Financial Records", 13);
//      departmentDao.add(department);
//      return department;
//   }
//}