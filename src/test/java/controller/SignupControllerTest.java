/*
 * File: FuleCostReportAdapterTest.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: The purpose of SignupControllerTest is to verify that 
 * the SignupController correctly handles valid and invalid signup requests, 
 * processes user data, and provides appropriate error messages for missing fields.
*/

package controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import businesslayer.UsersBusinessLogic;
import controller.SignupController;
import transferobjects.UserDTO;

public class SignupControllerTest {

    @Mock
    public UsersBusinessLogic businessLogic;  // Mocking the business logic layer

    @Mock
    private HttpServletRequest request;  // Mocking HttpServletRequest

    @Mock
    private HttpServletResponse response;  // Mocking HttpServletResponse

    private SignupController signupController;
    

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        signupController = new SignupController();  // Using default constructor
        signupController.businessLogic = businessLogic;  // Injecting mocked business logic
    }

    // Case 1: Valid signup request
    @Test
    public void testProcessSignup_ValidRequest() throws Exception {
        // Arrange
        String name = "John Doe";
        String email = "john.doe@example.com";
        String password = "securepassword";
        String role = "operator";

        // Mocking request parameters
        when(request.getMethod()).thenReturn("POST");
        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getParameter("role")).thenReturn(role);

        // Mocking business logic
        doNothing().when(businessLogic).addUser(any(UserDTO.class));

        // Act
        String result = signupController.processSignup(request, response);

        // Assert
        assertNull(result, "Expected successful signup to return null.");
        verify(businessLogic).addUser(any(UserDTO.class));  
    }

    // Case 2: Signup request with missing fields
    @Test
    public void testProcessSignup_MissingFields() throws Exception {
        // Arrange: Missing name
        when(request.getMethod()).thenReturn("POST");
        when(request.getParameter("name")).thenReturn(null);
        when(request.getParameter("email")).thenReturn("john.doe@example.com");
        when(request.getParameter("password")).thenReturn("securepassword");
        when(request.getParameter("role")).thenReturn("operator");

        // Act
        String result = signupController.processSignup(request, response);

        // Assert
        assertEquals("All fields are required.", result, "Expected error message for missing fields.");

        // Arrange: Missing email
        when(request.getParameter("name")).thenReturn("John Doe");
        when(request.getParameter("email")).thenReturn(null);
        when(request.getParameter("password")).thenReturn("securepassword");
        when(request.getParameter("role")).thenReturn("operator");

        // Act
        result = signupController.processSignup(request, response);

        // Assert
        assertEquals("All fields are required.", result, "Expected error message for missing fields.");
    }

    // Case 3: Checking if signup was successful (POST request and all parameters are provided)
    @Test
    public void testIsSignupSuccessful() {
        // Arrange
        when(request.getMethod()).thenReturn("POST");
        when(request.getParameter("name")).thenReturn("John Doe");
        when(request.getParameter("email")).thenReturn("john.doe@example.com");
        when(request.getParameter("password")).thenReturn("securepassword");
        when(request.getParameter("role")).thenReturn("operator");

        // Act
        boolean result = signupController.isSignupSuccessful(request);

        // Assert
        assertTrue(result, "Expected signup to be successful with all required fields.");

        // Test with missing parameters
        when(request.getParameter("name")).thenReturn(null);  

        result = signupController.isSignupSuccessful(request);

        // Assert
        assertFalse(result, "Expected signup to fail with missing name.");
    }
}
