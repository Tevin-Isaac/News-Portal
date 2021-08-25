package models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    @Test
    public void userInstantiatesCorrectly() {
        User user = new User ("Gideon",1, "Maintains System");
        assertEquals(true, user instanceof User);
    }

    @Test
    public void getUsernameCorrectly() {
        User user = new User ("Gideon",1, "Maintains System");
        assertEquals("Gideon", user.getUsername());
    }

    @Test
    public void getDepartmentIdCorrectly() {
        User user = new User ("Gideon",1, "Maintains System");
        assertEquals(1, user.getDepartmentId());
    }

    @Test
    public void getUserRoleCorrectly() {
        User user = new User ("Gideon",1, "Maintains System");
        assertEquals("Maintains System", user.getRole());
    }

    @Test
    public void setUsername() {
        User user = new User ("Gideon",1, "Maintains System");
        user.setUsername("Gideon");
        assertEquals("Gideon", user.getUsername());
    }

    @Test
    public void setDepartmentId() {
        User user = new User ("Gideon",1, "Maintains System");
        user.setDepartmentId(1);
        assertEquals(1, user.getDepartmentId());
    }

    @Test
    public void setUser() {
        User user = new User ("Gideon",1, "Maintains System");
        user.setRole("Maintains System");
        assertEquals("Maintains System", user.getRole());
    }

    @Test
    public void setId() {
        User user = new User ("Gideon",1, "Maintains System");
        user.setId(1);
        assertEquals(1,user.getId());
    }
}