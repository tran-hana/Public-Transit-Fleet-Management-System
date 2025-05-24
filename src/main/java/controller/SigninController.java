/*
 * File: SigninController.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Controller for handling user sign-in logic in the Public Transit Fleet Management System. 
 * This controller processes the HTTP request parameters (email and password), validates the user credentials,
 * and stores the user information in a session upon successful login.
*/

package controller;

import businesslayer.UsersBusinessLogic;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import transferobjects.UserDTO;
import java.io.IOException;

/**
 * Controller class responsible for handling user sign-in operations.
 * It verifies user credentials and handles session management after login.
 * 
 * @author Quoc Phong Tran
 * @see businesslayer.UsersBusinessLogic;
 * @see transferobjects.UserDTO;
 * @see javax.servlet.http.HttpServletRequest;
 * @see javax.servlet.http.HttpServletResponse;
 * @see java.io.IOException;
 * @see javax.servlet.ServletException;
 * @see javax.servlet.http.HttpSession;
 * @version 1.0
 * @since 21.0.5
 */

public class SigninController {
    
    private UsersBusinessLogic businessLogic;
    
    public SigninController() {
        this.businessLogic = new UsersBusinessLogic();
    }
    
    /**
     * Handles the sign-in logic
     * 
     * @param request The HTTP request
     * @param response The HTTP response
     * @return Error message if login fails, null if successful
     * @throws ServletException If servlet processing fails
     * @throws IOException If I/O operations fail
     */
    public String processSignin(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // If email or password is null, return null to show login page
        if (email == null || password == null) {
            return null;
        }
        
        try {
            UserDTO user = businessLogic.getUserByEmailAndPassword(email, password);
            
            if (user != null) {
                // Store user information in session
                HttpSession session = request.getSession();
                session.setAttribute("user_id", user.getId());
                session.setAttribute("name", user);
                session.setAttribute("role", user.getRole().toLowerCase());
                
                // Redirect based on role
                switch (user.getRole().toLowerCase()) {
                    case "transit manager":
                        request.getRequestDispatcher("/Servlet_Main_Manager").forward(request, response);
                        break;
                    case "operator":
                        request.getRequestDispatcher("/Servlet_Main_Operator").forward(request, response);
                        break;
                    default:
                        session.invalidate();
                        return "Access Denied: Invalid User Role.";
                }
                return null; // No error, successful login and redirect
            } else {
                return "Invalid Username or Password!";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}