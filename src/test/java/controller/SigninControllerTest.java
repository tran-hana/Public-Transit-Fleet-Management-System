/*
 * File: FuleCostReportAdapterTest.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: The purpose of SigninControllerTest is to verify that the SigninController 
 * correctly processes sign-in requests, handles invalid or missing credentials, 
 * and returns appropriate error messages.
*/

package controller;
import businesslayer.UsersBusinessLogic;
import controller.SigninController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import transferobjects.UserDTO;

public class SigninControllerTest {

    @Mock
    private UsersBusinessLogic businessLogic;  // Mocking the business logic layer

    @Mock
    private HttpServletRequest request;  // Mocking HttpServletRequest

    @Mock
    private HttpServletResponse response;  // Mocking HttpServletResponse

    @Mock
    private HttpSession session;  // Mocking the session

    private SigninController signinController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        signinController = new SigninController();  // Using default constructor
    }

    
    @Test
    public void testProcessSignin_NullEmailOrPassword() throws Exception {
        // Test case for null email
        when(request.getParameter("email")).thenReturn(null);
        when(request.getParameter("password")).thenReturn("pass123");

        String result = signinController.processSignin(request, response);

        assertNull(result, "Expected null result for null email, which should show the login page.");
    }

    @Test
    public void testProcessSignin_NullPassword() throws Exception {
        // Test case for null password
        when(request.getParameter("email")).thenReturn("alice@example.com");
        when(request.getParameter("password")).thenReturn(null);

        String result = signinController.processSignin(request, response);

        assertNull(result, "Expected null result for null password, which should show the login page.");
    }

    
    @Test
    public void testProcessSignin_InvalidCredentials() throws Exception {
        // Arrange
        String email = "alice@example.com";
        String password = "wrongpassword";  // Incorrect password

        // Mocking request parameters
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        
        // Mocking the business logic to return null (invalid credentials)
        when(businessLogic.getUserByEmailAndPassword(email, password)).thenReturn(null);
        
        // Act
        String result = signinController.processSignin(request, response);

        // Assert
        assertEquals("Invalid Username or Password!", result, "Expected error message for invalid credentials.");
    }
}
