import com.google.gson.Gson;
import dao.Sql2oUserDao;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;
import models.Department;
import models.News;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        Sql2oNewsDao newsDao;
        Sql2oDepartmentDao departmentDao;
        Sql2oUserDao userDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/Organisational-News-Portal.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        departmentDao= new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();

        post("/department/new", "application/json", (request, response) -> {
            Department department = gson.fromJson(request.body(), Department.class);
            departmentDao.add(department);
            response.status(201);
            response.type("application/json");
            return gson.toJson(department);
        });

        get("/department", "application/json", (request, response) -> {
            response.type("application/json");
            return gson.toJson(departmentDao.getAll());
        });

        get("/department/:id", "application/json",(request, response) -> {
            response.type("application/json");
            int departmentId = Integer.parseInt(request.params("id"));
            response.type("application/json");
            return gson.toJson(departmentDao.findById(departmentId));
        });

        get("/department/:id/news", "application/json",(request, response) -> {
            int departmentId = Integer.parseInt(request.params("id"));
            Department departmentToFind = departmentDao.findById(departmentId);

            List<Department> allNews;
            allNews = newsDao.getAllDepartmentNews(departmentId);
            response.type("application/json");
            return gson.toJson(allNews);
        });

        post("/users/new", "application/json", (request, response) -> {
            User user = gson.fromJson(request.body(), User.class);
            userDao.add(user);
            response.status(201);
            response.type("application/json");
            return gson.toJson(user);
        });

        get("/users", "application/json", (request, response) -> {
            response.type("application/json");
            return gson.toJson(userDao.getAll());
        });

        get("/users/:id", "application/json",(request, response) -> {
            response.type("application/json");
            int departmentId = Integer.parseInt(request.params("id"));
            response.type("application/json");
            return gson.toJson(userDao.findById(departmentId));
        });

        post("/news/new", "application/json", (request, response) -> {
            News news = gson.fromJson(request.body(), News.class);
            newsDao.add(news);
            response.status(201);
            response.type("application/json");
            return gson.toJson(news);
        });

        get("/news", "application/json", (request, response) -> {
            response.type("application/json");
            return gson.toJson(newsDao.getAll());
        });

        get("/news/:id", "application/json",(request, response) -> {
            response.type("application/json");
            int departmentId = Integer.parseInt(request.params("id"));
            response.type("application/json");
            return gson.toJson(newsDao.findById(departmentId));
        });

        //filters
        after((request, response) -> {
            response.type("application/json");
        });
    }
}
