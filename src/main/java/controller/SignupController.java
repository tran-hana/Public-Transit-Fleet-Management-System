/*
 * File: SignupController.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Controller for handling user signup operations in the Public Transit Fleet Management System. 
 * This controller processes form data submitted via HTTP request, validates the input, 
 * and passes the new user data to the business logic layer for insertion into the system.
*/


package controller;

import businesslayer.UsersBusinessLogic;
import transferobjects.UserDTO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller class for handling vehicle addition operations.
 * This class separates business logic from the view layer.
 * @author Quoc Phong Tran
 * @see businesslayer.UsersBusinessLogic;
 * @see transferobjects.UserDTO;
 * @see javax.servlet.http.HttpServletRequest;
 * @see javax.servlet.http.HttpServletResponse;
 * @see java.io.IOException;
 * @version 1.0
 * @since 21.0.5
 */


public class SignupController {
    
    public UsersBusinessLogic businessLogic;
    
    public SignupController() {
        this.businessLogic = new UsersBusinessLogic();
    }
    
    /**
     * Processes the signup request
     * 
     * @param request The HTTP request
     * @param response The HTTP response
     * @return Error message if signup fails, null if successful
     * @throws ServletException If servlet processing fails
     * @throws IOException If I/O operations fail
     */
    public String processSignup(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        
        if (!"POST".equalsIgnoreCase(request.getMethod())) {
            return null;
        }
        
        // Get form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        
        // Validate form data
        if (name == null || email == null || password == null || role == null) {
            return "All fields are required.";
        }
        
        try {
            // Create new user
            UserDTO newUser = new UserDTO(name, email, password, role);
            businessLogic.addUser(newUser);
            
            // Signup successful
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    /**
     * Checks if signup was successful
     * 
     * @param request The HTTP request
     * @return true if signup was successful (POST request with no errors)
     */
    public boolean isSignupSuccessful(HttpServletRequest request) {
        return "POST".equalsIgnoreCase(request.getMethod()) && 
               request.getParameter("name") != null &&
               request.getParameter("email") != null &&
               request.getParameter("password") != null &&
               request.getParameter("role") != null;
    }
}