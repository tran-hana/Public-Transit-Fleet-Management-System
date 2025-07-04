/*
 * File: Servlet_MaintenanceAlerts.java
 * Author: Cheng Qian, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Servlet for displaying maintenance alerts in the Public Transit Fleet Management
 * System. This servlet handles both HTTP GET and POST requests, communicates with 
 * the controller to evaluate alerts, and displays them in a styled HTML format.
 */
package viewlayer;

import controller.MaintenanceAlertsController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet that displays component maintenance alerts. Uses a dedicated
 * controller for business logic processing.
 *
 * @author Cheng Qian
 * @see controller.MaintenanceAlertsController;
 * @see java.util.List;
 * @see java.io.IOException;
 * @see java.io.PrintWriter;
 * @see javax.servlet.ServletException;
 * @see javax.servlet.http.HttpServlet;
 * @see javax.servlet.http.HttpServletRequest;
 * @see javax.servlet.http.HttpServletResponse;
 * @version 1.0
 * @since 21.0.5
 */
public class Servlet_MaintenanceAlerts extends HttpServlet {

    // Controller that handles business logic for maintenance alerts
    private MaintenanceAlertsController controller;

    /**
     * Initializes the servlet and its controller. Called once when the servlet
     * is first loaded.
     *
     * @throws ServletException if servlet initialization fails
     */
    @Override
    public void init() throws ServletException {
        // Initialize the controller when servlet is created
        controller = new MaintenanceAlertsController();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods. Uses controller to evaluate maintenance alerts and displays
     * them.
     *
     * @param request the HTTP servlet request
     * @param response the HTTP servlet response
     * @throws ServletException if servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Use the controller to process maintenance alerts
        List<String> alertMessages = controller.processMaintenanceAlerts(request);

        // Render the alert page with the retrieved messages
        renderAlertPage(response, alertMessages, "Component Maintenance Alerts");
    }

    // Renders an HTML page displaying all maintenance alerts in styled boxes.
    private void renderAlertPage(HttpServletResponse response, List<String> alerts, String title)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>" + title + "</title>");
            out.println("<style>");
            // Common styles for your system
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f9; color: #333; }");
            out.println("header { background-color: #4682B4; padding: 20px; text-align: center; color: white; }");
            out.println("section { padding: 20px; text-align: center; }");
            out.println(".alert-box { background-color: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; padding: 15px; margin: 15px auto; border-radius: 5px; width: 60%; }");
            out.println(".no-alert { color: #28a745; font-weight: bold; }");
            out.println("nav { text-align: center; padding: 15px; background-color: #f8f9fa; border-bottom: 1px solid #ddd; }");
            out.println("nav a { margin: 10px; padding: 10px 20px; background-color: #dbe9f5; border: 1px solid #4682B4; border-radius: 5px; color: #4682B4; text-decoration: none; font-weight: bold; }");
            out.println("nav a:hover { background-color: #b0cde9; color: #2c5d8a; }");
            out.println("h2 { color: #333; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            // Header Section
            out.println("<header>");
            out.println("<h1>Public Transit Fleet Management System</h1>");
            out.println("</header>");

            // Navigation Bar
            out.println("<nav>");
            out.println("<a href=\"Servlet_Main_Manager\">Home</a>");
            out.println("<a href=\"Servlet_Signin\">Sign Out</a>");
            out.println("</nav>");

            // Main Content Section
            out.println("<section>");
            out.println("<h2>" + title + "</h2>");

            // Display alerts or a no-alert message
            if (alerts != null && !alerts.isEmpty()) {
                for (String alert : alerts) {
                    out.println("<div class='alert-box'>" + alert + "</div>");
                }
            } else {
                out.println("<p class='no-alert'>No alerts triggered.</p>");
            }

            out.println("</section>");
            out.println("</body>");
            out.println("</html>");
        }
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
        return "Servlet to display component maintenance alerts";
    }
}
