/*
 * File: UsersBusinessLogicTest.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 05, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose: This JUnit test class validates the functionality of the UsersBusinessLogic class.
 * It tests adding a new user, retrieving a user by credentials, creating a role-specific
 * UserDTO, and cleaning user input. These tests verify business rules and data processing 
 * logic before interacting with the database.
 */

package businesslayer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import transferobjects.UserDTO;
import transferobjects.OperatorDTO;
import transferobjects.TransitManagerDTO;

public class UsersBusinessLogicTest {

    private final UsersBusinessLogic logic = new UsersBusinessLogic();

    // Test addUser() with a valid user.     
    @Test
    public void testAddUser() {
        UserDTO user = new UserDTO();
        user.setName("Test User");
        user.setEmail("testuser@ptfms.ca");
        user.setPassword("securepass");
        user.setRole("Operator");

        try {
            logic.addUser(user);
        } catch (Exception e) {
            fail("addUser() threw an unexpected exception: " + e.getMessage());
        }

    }

    // Test getUserByEmailAndPassword() with an existing user.
    @Test
    public void testGetUserByEmailAndPassword() {
        String email = "carol@example.com";
        String password = "carol123";

        UserDTO user = logic.getUserByEmailAndPassword(email, password);
        assertNotNull(user, "Expected a user to be returned for valid credentials");
        assertEquals(email, user.getEmail());
        assertEquals("Operator", user.getRole());
    }

    // Test createUserDTO() returns correct subclass based on role.     
    @Test
    public void testCreateUserDTO() {
        UserDTO operator = UsersBusinessLogic.createUserDTO("Operator", 1, "Op", "op@ptfms.ca", "password123");
        assertTrue(operator instanceof OperatorDTO);

        UserDTO manager = UsersBusinessLogic.createUserDTO("Transit Manager", 2, "Mgr", "mgr@ptfms.ca", "adminpass");
        assertTrue(manager instanceof TransitManagerDTO);

        try {
            UsersBusinessLogic.createUserDTO("UnknownRole", 3, "X", "x@x.com", "xpass");
            fail("Expected IllegalArgumentException to be thrown for an unknown role.");
        } catch (IllegalArgumentException ex) {
            assertEquals("User role is not valid: UnknownRole", ex.getMessage());
        }
    }

    /**
     * Test cleanData() trims all input fields.
     */
    @Test
    public void testCleanData() {
        UserDTO user = new UserDTO();
        user.setName("  Name With Spaces  ");
        user.setEmail("  email@ptfms.ca  ");
        user.setPassword("  password123  ");
        user.setRole("  Operator  ");

        logic.cleanData(user);

        assertEquals("Name With Spaces", user.getName());
        assertEquals("email@ptfms.ca", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("Operator", user.getRole());
    }
}
