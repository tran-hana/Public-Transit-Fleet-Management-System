/*
 * File: Servlet_Signup.java
 * Author: Minh Hoang Vu, 041154298, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Servlet for handling user sign-up requests for the Public Transit Fleet Management System
 */
package viewlayer;

import controller.SignupController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for handling user sign-up requests for the Public Transit Fleet
 * Management System
 *
 * @author Minh Hoang Vu
 * @see controller.SignupController;
 * @see java.io.IOException;
 * @see java.io.PrintWriter;
 * @see javax.servlet.ServletException;
 * @see javax.servlet.http.HttpServlet;
 * @see javax.servlet.http.HttpServletRequest;
 * @see javax.servlet.http.HttpServletResponse;
 * @version 1.0
 * @since 21.0.5
 */
public class Servlet_Signup extends HttpServlet {

    private SignupController controller;

    /**
     * Initializes the servlet and the controller for handling the sign-up
     * process. This method is called when the servlet is loaded into memory.
     */
    @Override
    public void init() throws ServletException {
        controller = new SignupController();
    }

    /**
     * Processes requests for both HTTP GET and POST methods.
     *
     * @param request the HttpServletRequest object that contains the request
     * the client made
     * @param response the HttpServletResponse object that contains the response
     * the servlet returns
     * @throws ServletException if the request cannot be processed
     * @throws IOException if an input or output error occurs during processing
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // Process request using controller
            String errorMessage = controller.processSignup(request, response);

            if (errorMessage != null) {
                // Show signup page with error
                showSignupPage(out, errorMessage);
            } else if (controller.isSignupSuccessful(request)) {
                // Show success page
                showSuccessPage(out);
            } else {
                // Show signup page (for first visit)
                showSignupPage(out, null);
            }
        }
    }

    /**
     * Displays the sign-up page with an optional error message.
     *
     * @param out the PrintWriter used to write the response content
     * @param errorMessage the error message to display, or null if no error
     */
    private void showSignupPage(PrintWriter out, String errorMessage) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Sign Up</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f9; color: #333; }");
        out.println("header { background-color: #4682B4; padding: 20px; text-align: center; color: white; }");
        out.println("header h1 { margin: 0; }");
        out.println("form { display: block; width: 300px; margin: 20px auto; padding: 20px; background-color: #ffffff; border: 1px solid #ddd; border-radius: 8px; box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); }");
        out.println("input[type=\"text\"], input[type=\"email\"], input[type=\"password\"] { width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ccc; border-radius: 5px; }");
        out.println("label { font-weight: bold; display: block; margin-bottom: 5px; }");
        out.println("button { width: 100%; padding: 10px; background-color: #4682B4; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 1em; }");
        out.println("button:hover { background-color: #315f86; }");
        out.println(".error { color: red; margin-bottom: 10px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<header>");
        out.println("<h1>Sign Up for The Public Transit Fleet Management System</h1>");
        out.println("</header>");
        out.println("<section>");
        if (errorMessage != null) {
            out.println("<p class='error'>" + errorMessage + "</p>");
        }
        out.println("<form action='Servlet_Signup' method='POST'>");
        out.println("<label for='name'>Name:</label>");
        out.println("<input type='text' id='name' name='name' required>");
        out.println("<label for='role'>Role:</label>");
        out.println("<input type='text' id='role' name='role' required>");
        out.println("<label for='email'>Email:</label>");
        out.println("<input type='email' id='email' name='email' required>");
        out.println("<label for='password'>Password:</label>");
        out.println("<input type='password' id='password' name='password' required>");
        out.println("<button type='submit'>Sign Up</button>");
        out.println("<p style='text-align: center;'>Already registered? <a href='Servlet_Signin'>Sign In</a></p>");
        out.println("</form>");
        out.println("</section>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Displays the success page after successful registration.
     *
     * @param out the PrintWriter used to write the response content
     */
    private void showSuccessPage(PrintWriter out) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Sign Up Successful</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f9; color: #333; }");
        out.println("header { background-color: #4682B4; padding: 20px; text-align: center; color: white; }");
        out.println("header h1 { margin: 0; }");
        out.println("section { padding: 20px; text-align: center; }");
        out.println("button { padding: 10px 20px; background-color: #4682B4; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 1em; }");
        out.println("button:hover { background-color: #315f86; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<header>");
        out.println("<h1>Sign Up Successful</h1>");
        out.println("</header>");
        out.println("<section>");
        out.println("<p>Your account has been created successfully.</p>");
        out.println("<button onclick=\"location.href='Servlet_Signin'\">Go to Sign In</button>");
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
        return "Servlet to Sign up";
    }// </editor-fold>

}
