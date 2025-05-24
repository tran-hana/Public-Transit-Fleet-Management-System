/*
 * File: Servlet_ScheduleMaintenance.java
 * Author: Cheng Qian, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Servlet for scheduling a new maintenance task for a specific vehicle component
 * in the Public Transit Fleet Management System. This servlet handles both HTTP GET and
 * POST requests and communicates with the controller to process vehicle maintenance data.
 */
package viewlayer;

import controller.ScheduleMaintenanceController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles scheduling a new maintenance task for a specific vehicle component.
 * This servlet is triggered when a Transit Manager clicks the "Schedule
 * Maintenance" button on an alert. It receives the component ID via POST and
 * inserts a new maintenance record with the given date and default cost of 0.
 *
 * @author Cheng Qian
 * @see controller.ScheduleMaintenanceController;
 * @see java.io.IOException;
 * @see java.io.PrintWriter;
 * @see javax.servlet.ServletException;
 * @see javax.servlet.http.HttpServlet;
 * @see javax.servlet.http.HttpServletRequest;
 * @see javax.servlet.http.HttpServletResponse;
 * @version 1.0
 * @since 21.0.5
 */
public class Servlet_ScheduleMaintenance extends HttpServlet {

    private ScheduleMaintenanceController controller;

    /**
     * Initializes the servlet by creating instances of the controller
     *
     * @throws ServletException if an initialization error occurs
     */
    @Override
    public void init() throws ServletException {
        controller = new ScheduleMaintenanceController();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods. If valid parameters are provided, schedules a maintenance
     * record. Responds with a confirmation message.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Get the result from the controller
        ScheduleMaintenanceController.MaintenanceResult result = controller.processScheduleMaintenance(request);

        // Display the appropriate view based on the result
        if (result.isSuccess()) {
            displaySuccessPage(response, result.getComponentId(), result.getFormattedDate());
        } else {
            displayErrorPage(response);
        }
    }

    /**
     * Displays a success page when maintenance is successfully scheduled.
     *
     * @param response the HTTP servlet response
     * @param componentId the ID of the component for which maintenance was
     * scheduled
     * @param displayDate the formatted date of the scheduled maintenance
     * @throws IOException if an I/O error occurs
     */
    public void displaySuccessPage(HttpServletResponse response, int componentId, String displayDate) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Maintenance Scheduled</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f4f4f9; color: #333; }");
            out.println("h1 { color: #4682B4; }");
            out.println("a { display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #4682B4; color: white; text-decoration: none; border-radius: 5px; }");
            out.println("a:hover { background-color: #315f86; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Maintenance scheduled for component ID: " + componentId + " on " + displayDate + "</h1>");
            out.println("<a href='Servlet_MaintenanceAlerts'>Back to Alerts</a>");
            out.println("</body></html>");
        }
    }

    /**
     * Displays an error page when maintenance scheduling fails.
     *
     * @param response the HTTP servlet response
     * @throws IOException if an I/O error occurs
     */
    public void displayErrorPage(HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Error</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f4f4f9; color: #333; }");
            out.println("h1 { color: #d9534f; }");
            out.println("a { display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #4682B4; color: white; text-decoration: none; border-radius: 5px; }");
            out.println("a:hover { background-color: #315f86; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Invalid component ID or date</h1>");
            out.println("<a href='Servlet_MaintenanceAlerts'>Back to Alerts</a>");
            out.println("</body></html>");
        }
    }

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
        return "Servlet to handle scheduling of maintenance for components";
    }
}
