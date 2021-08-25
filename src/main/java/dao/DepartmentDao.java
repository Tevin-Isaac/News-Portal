package dao;

import models.Department;
import models.News;
import models.User;

import java.util.List;

public interface DepartmentDao {

    //create
    void add(Department department);
    void addDepartmentToNews(Department department, News news);

    //read
    List<Department> getAll();
    Department findById(int id);
    List<User> getAllUsers(int departmentid);
    List<News> getAllNewsForDepartments(int departmentid);

    //delete
    void deleteById(int id);
    void clearAll();



    //update
//    void update(int id, String name, String description, int totalemployees);
}