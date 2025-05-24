/*
 * File: Servlet_Signin.java
 * Author: Minh Hoang Vu, 041154298, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Servlet for handling user sign-in functionality for the Public Transit Fleet Management System.
 * It processes both GET and POST requests to authenticate users and provide the login interface.
 */
package viewlayer;

import controller.SigninController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for handling the sign-in functionality in the Public Transit Fleet
 * Management System. This servlet processes both GET and POST requests to
 * either display the login form or submit user credentials for authentication
 * through the controller.
 *
 * @author Minh Hoang Vu
 * @see controller.SigninController;
 * @see java.io.IOException;
 * @see java.io.PrintWriter;
 * @see javax.servlet.ServletException;
 * @see javax.servlet.http.HttpServlet;
 * @see javax.servlet.http.HttpServletRequest;
 * @see javax.servlet.http.HttpServletResponse;
 * @version 1.0
 * @since 21.0.5
 */
public class Servlet_Signin extends HttpServlet {

    private SigninController controller;

    /**
     * Sets the controller to be used for processing the sign-in request.
     *
     * @param controller The controller responsible for processing the sign-in
     * data.
     */
    public void setController(SigninController controller) {
        this.controller = controller;
    }

    /**
     * Initializes the servlet and sets up the controller.
     *
     * @throws ServletException If an error occurs during servlet
     * initialization.
     */
    @Override
    public void init() throws ServletException {
        controller = new SigninController();
    }

    /**
     * Processes the HTTP request. This method handles both GET and POST
     * requests by calling the appropriate logic based on the request type.
     *
     * @param request The HttpServletRequest object that contains the client's
     * request.
     * @param response The HttpServletResponse object that contains the server's
     * response.
     * @throws ServletException If the request cannot be processed.
     * @throws IOException If an input or output error occurs.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // Process request using controller
            String errorMessage = controller.processSignin(request, response);

            // If error or initial request, show login page
            if (errorMessage != null || request.getParameter("email") == null) {
                showLoginPage(out, errorMessage);
            }
        }
    }

    /**
     * Displays the login page with an optional error message.
     *
     * @param out The PrintWriter used to write the HTML response.
     * @param errorMessage The error message to display, if any.
     */
    private void showLoginPage(PrintWriter out, String errorMessage) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Sign In - Public Transit Fleet Management System</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f9; color: #333; }");
        out.println("header { background-color: #4682B4; padding: 20px; text-align: center; color: white; }");
        out.println("header h1 { margin: 0; }");
        out.println("section { padding: 20px; text-align: center; }");
        out.println("form { display: inline-block; padding: 20px; background-color: #ffffff; border: 1px solid #ddd; border-radius: 8px; box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); }");
        out.println("input[type=\"text\"], input[type=\"password\"] { width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ccc; border-radius: 5px; }");
        out.println("button { width: 100%; padding: 10px; background-color: #4682B4; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 1em; }");
        out.println("button:hover { background-color: #315f86; }");
        out.println(".error { color: red; margin: 10px 0; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<header>");
        out.println("<h1>Welcome to The Public Transit Fleet Management System</h1>");
        out.println("</header>");
        out.println("<section>");
        out.println("<h2>Sign In</h2>");

        if (errorMessage != null) {
            out.println("<p class='error'>" + errorMessage + "</p>");
        }

        out.println("<form action='Servlet_Signin' method='POST'>");
        out.println("<label for='email'>Email:</label>");
        out.println("<input type='text' id='email' name='email' placeholder='Enter your email' required><br>");
        out.println("<label for='password'>Password:</label>");
        out.println("<input type='password' id='password' name='password' placeholder='Enter your password' required><br>");
        out.println("<button type='submit'>Sign In</button>");
        out.println("</form>");
        out.println("<p>Don't have an account? <a href='Servlet_Signup'>Register</a></p>");
        out.println("</section>");
        out.println("</body>");
        out.println("</html>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet to Sign in";
    }// </editor-fold>

}
