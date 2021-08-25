package dao;

import models.Department;
import models.News;
import java.util.List;

public interface NewsDao {

    //create
    void add (News news);
    void addNewsToDepartment(News news, Department department);

    //read
    List<News> getAll();
    News findById(int id);
    List <Department> getAllDepartmentNews(int newsid);

    //delete
    void deleteById(int id);
    void clearAll();
}
