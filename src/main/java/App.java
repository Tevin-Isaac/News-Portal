import com.google.gson.Gson;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUserDao;
import models.Department;
import models.News;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
          return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
      }

    public static void main(String[] args) {
        Sql2oNewsDao newsDao;
        Sql2oDepartmentDao departmentDao;
        Sql2oUserDao userDao;
        Connection conn;
        Gson gson = new Gson();

       String connectionString = "jdbc:h2:~/tevin.db;INIT=RUNSCRIPT from 'classpath:dao/create.sql'";
       Sql2o sql2o = new Sql2o(connectionString, "tevin", "index");
//        String connectionString = "jdbc:postgresql://ec2-18-214-238-28.compute-1.amazonaws.com:5432/d9ffe8pvlkorio"; //!
//        Sql2o sql2o = new Sql2o(connectionString, "xwayyrtgsuiqfd", "da122c88c2120d7e0a9715bc103cc0a9d4e70295d156544e72a0b882db3e6fcd"); //!

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

