package models;

import java.util.Objects;

public class Department {
    public String name;
    public String description;
    public int totalemployees;
    private int id;

    public Department(String name, String description, int totalemployees) {
        this.name = name;
        this.description = description;
        this.totalemployees= totalemployees;
    }

    public String getDepartmentName() {
        return name;
    }

    public String getDepartmentDescription() {
        return description;
    }

    public int getTotalEmployees() {
        return totalemployees;
    }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTotalEmployees(int totalemployees) {
        this.totalemployees = totalemployees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return totalemployees == that.totalemployees &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, totalemployees);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

