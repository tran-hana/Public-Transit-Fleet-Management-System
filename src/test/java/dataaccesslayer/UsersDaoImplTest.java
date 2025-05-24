/*
 * File: UsersDaoImplTest.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 05, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose: This JUnit test class validates the functionality of the UsersDaoImpl class.
 * It verifies user insertion, authentication, and retrieval based on user role
 * using actual data from the 'user' table in the ptfms database.
 */

package dataaccesslayer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import transferobjects.UserDTO;

import java.util.List;

public class UsersDaoImplTest {

    private final UsersDaoImpl instance = new UsersDaoImpl();

    /**
     * Test the addUser() method by inserting a new user.
     */
    @Test
    public void testAddUser() {
        UserDTO user = new UserDTO();
        user.setName("JUnit Test User");
        user.setEmail("junituser@example.com");
        user.setPassword("junitpass");
        user.setRole("Operator");

        try {
            instance.addUser(user);
        } catch (Exception e) {
            fail("addUser() threw an unexpected exception: " + e.getMessage());
        }

    }

    /*
     * Test getUserByEmailAndPassword() for login authentication.
     */
    @Test
    public void testGetUserByEmailAndPassword() {
        String email = "carol@example.com";
        String password = "carol123";

        UserDTO user = instance.getUserByEmailAndPassword(email, password);
        assertNotNull(user, "User should be found with correct email and password");
        assertEquals(email, user.getEmail());
        assertEquals("Operator", user.getRole()); 
    }

    /*
     * Test getUserByRole() for retrieving all users with a specific role.
     */
    @Test
    public void testGetUserByRole() {
        String role = "Operator";
        List<UserDTO> users = instance.getUserByRole(role);

        assertNotNull(users, "Returned list should not be null");
        assertFalse(users.isEmpty(), "Returned list should not be empty");
        for (UserDTO user : users) {
            assertEquals(role, user.getRole(), "Each user should have role: " + role);
        }
    }
}
