/*
 * File: Servlet_Main_Operator.java
 * Author: Minh Hoang Vu, 041154298, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Servlet for displaying the Transit Operator dashboard in the Public Transit
 * Fleet Management System. This servlet handles both HTTP GET and POST requests 
 * and routes actions such as navigating to the home page, logging time, or signing out.
 */
package viewlayer;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for adding a new vehicle to the Public Transit Fleet Management
 * System. This servlet handles both HTTP GET and POST requests and communicates
 * with the controller to process vehicle data.
 *
 * GET request: Displays the form to add a new vehicle. POST request: Submits
 * the vehicle data to the controller and displays the result.
 *
 * @author Minh Hoang Vu
 * @see java.io.IOException;
 * @see java.io.PrintWriter;
 * @see javax.servlet.ServletException;
 * @see javax.servlet.http.HttpServlet;
 * @see javax.servlet.http.HttpServletRequest;
 * @see javax.servlet.http.HttpServletResponse;
 * @version 1.0
 * @since 21.0.5
 */
public class Servlet_Main_Operator extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        try (PrintWriter out = response.getWriter()) {
            if (action != null) {
                switch (action) {
                    case "Home":
                        request.getRequestDispatcher("/Servlet_Main_Operator").forward(request, response);
                        break;
                    case "LogTime":
                        request.getRequestDispatcher("/Servlet_Log_Break_Time").forward(request, response);
                        break;
                    case "SignOut":
                        request.getRequestDispatcher("/Servlet_Signin").forward(request, response);
                        break;
                    default:
                        out.println("<p>Error: Invalid action specified.</p>");
                        break;
                }
            } else {
                generateDashboard(out);
            }
        }
    }

    /**
     * Generates and outputs the HTML content for the Transit Operator
     * dashboard. This method creates the structure of the dashboard page
     * including the header, navigation menu, and a section with buttons to log
     * break time or sign out.
     *
     * The page is styled using inline CSS and displayed directly through the
     * PrintWriter output stream.
     *
     * @param out the PrintWriter used to send the HTML response to the client
     */
    private void generateDashboard(PrintWriter out) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Operator Dashboard - Public Transit Fleet Management System</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f9; color: #333; }");
        out.println("header { background-color: #4682B4; padding: 20px; text-align: center; color: white; }");
        out.println("header h1 { margin: 0; }");
        out.println("nav { text-align: center; padding: 15px; background-color: #f8f9fa; border-bottom: 1px solid #ddd; }");
        out.println("nav a { margin: 10px; padding: 10px 20px; background-color: #dbe9f5; border: 1px solid #4682B4; border-radius: 5px; color: #4682B4; text-decoration: none; font-weight: bold; }");
        out.println("nav a:hover { background-color: #b0cde9; color: #2c5d8a; }");
        out.println("section { padding: 20px; text-align: center; }");
        out.println("a.button { display: inline-block; margin: 15px auto; padding: 15px 25px; background-color: #dbe9f5; border: 2px solid #4682B4; border-radius: 5px; color: #4682B4; text-decoration: none; font-size: 1.2em; font-weight: bold; }");
        out.println("a.button:hover { background-color: #b0cde9; color: #2c5d8a; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<header>");
        out.println("<h1>The Public Transit Fleet Management System</h1>");
        out.println("</header>");
        out.println("<nav>");
        out.println("<a href=\"Servlet_Main_Operator\">Home</a>");
        out.println("<a href=\"Servlet_Signin\">Sign Out</a>");
        out.println("</nav>");
        out.println("<section>");
        out.println("<h2>Role: Transit Operator</h2>");
        out.println("<a class=\"button\" href=\"Servlet_Log_Break_Time\">Log your time</a>");
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
        return "Operator dashboard servlet";
    }// </editor-fold>
}
