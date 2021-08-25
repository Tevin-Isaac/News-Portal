package models;

public class User {
    private String username;
    private int departmentid;
    private String role;
    private int id;

    public User(String username, int departmentid, String role) {
        this.username = username;
        this.departmentid = departmentid;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public int getDepartmentId() {
        return departmentid;
    }

    public String getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDepartmentId(int departmentid) {
        this.departmentid = departmentid;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
