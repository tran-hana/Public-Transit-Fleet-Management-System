/*
 * File: Servlet_ConsumptionAlerts
 * Author: Cheng Qian, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Servlet for displaying excessive fuel/energy consumption alerts 
 * in the Public Transit Fleet Management System. It handles both GET and 
 * POST requests, communicates with the controller to gather alert data, and 
 * renders an alert view page
 */
package viewlayer;

import controller.ConsumptionAlertsController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet responsible for evaluating and displaying fuel/energy consumption
 * alerts in the Public Transit Fleet Management System.
 *
 * Includes columns for Actual and Expected Consumption in the alerts table.
 *
 * @author Cheng Qian
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
public class Servlet_ConsumptionAlerts extends HttpServlet {

    /**
     * Controller that processes consumption data and generates alerts.
     */
    private ConsumptionAlertsController controller;

    /**
     * Initializes the servlet and sets up the controller instance.
     *
     * @throws ServletException if an error occurs during initialization
     */
    @Override
    public void init() throws ServletException {
        controller = new ConsumptionAlertsController();
    }

    /**
     * Processes HTTP requests (GET and POST), retrieves alert messages from the
     * controller, and sends an HTML response displaying the alerts in styled
     * boxes.
     *
     * @param request the HTTP request object
     * @param response the HTTP response object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an input/output error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get alert data from controller
        List<String> alertMessages = controller.processConsumptionAlerts(request);

        // Get title from controller
        String title = controller.getAlertsTitle();

        // Render the view
        renderAlertPage(response, alertMessages, title);
    }

    /**
     * Renders an HTML page displaying all consumption alerts in styled boxes
     *
     * @param response the HTTP servlet response
     * @param alerts list of alert messages
     * @param title the page title
     * @throws IOException if an I/O error occurs
     */
    private void renderAlertPage(HttpServletResponse response, List<String> alerts, String title)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>" + title + "</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f9; color: #333; }");
            out.println("header { background-color: #4682B4; padding: 20px; text-align: center; color: white; }");
            out.println("nav { text-align: center; padding: 15px; background-color: #f8f9fa; border-bottom: 1px solid #ddd; }");
            out.println("nav a { margin: 10px; padding: 10px 20px; background-color: #dbe9f5; border: 1px solid #4682B4; border-radius: 5px; color: #4682B4; text-decoration: none; font-weight: bold; }");
            out.println("nav a:hover { background-color: #b0cde9; color: #2c5d8a; }");
            out.println("section { padding: 20px; text-align: center; }");
            out.println(".alert-box { background-color: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; padding: 15px; margin: 15px auto; border-radius: 5px; width: 60%; }");
            out.println(".no-alert { color: #28a745; font-weight: bold; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("<h1>Public Transit Fleet Management System</h1>");
            out.println("</header>");
            out.println("<nav>");
            out.println("<a href=\"Servlet_Main_Manager\">Home</a>");
            out.println("<a href=\"Servlet_Signin\">Sign Out</a>");
            out.println("</nav>");
            out.println("<section>");
            out.println("<h2>" + title + "</h2>");

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

    @Override
    public String getServletInfo() {
        return "Servlet to display fuel/energy consumption alerts";
    }// </editor-fold>
}
