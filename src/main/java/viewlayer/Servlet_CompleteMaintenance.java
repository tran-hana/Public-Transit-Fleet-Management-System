/*
 * File: Servlet_CompleteMaintenance.java
 * Author: Cheng Qian, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Servlet for completing a maintenance task in the Public Transit Fleet 
 * Management System. It handles both GET and POST requests and invokes the controller 
 * logic to process completion and cost data for a maintenance task.
 */
package viewlayer;

import controller.CompleteMaintenanceController;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet for completing a maintenance task in the Public Transit Fleet
 * Management System. It handles both HTTP GET and POST requests and delegates
 * business logic to the controller.
 *
 * GET and POST requests both expect the following parameters: ID of the
 * maintenance task to complete. Cost associated with the completed maintenance.
 *
 * @author Cheng Qian
 * @see controller.CompleteMaintenanceController;
 * @see java.io.IOException;
 * @see java.io.PrintWriter;
 * @see javax.servlet.ServletException;
 * @see javax.servlet.http.HttpServlet;
 * @see javax.servlet.http.HttpServletRequest;
 * @see javax.servlet.http.HttpServletResponse;
 * @version 1.0
 * @since 21.0.5
 */
public class Servlet_CompleteMaintenance extends HttpServlet {

    // Controller instance for handling the business logic
    private CompleteMaintenanceController controller;

    /**
     * Initializes the servlet and creates an instance of the controller.
     */
    @Override
    public void init() {
        controller = new CompleteMaintenanceController();
    }

    /**
     * Processes both GET and POST requests to complete a maintenance task. It
     * parses request parameters, invokes the controller method, and generates a
     * response view.
     *
     * @param request servlet request object containing parameters
     * @param response servlet response object used to generate the view
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int maintenanceId = Integer.parseInt(request.getParameter("maintenanceId"));
            double costDollars = Double.parseDouble(request.getParameter("costDollars"));

            controller.completeMaintenance(maintenanceId, costDollars);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Maintenance Completed</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f9; color: #333; }");
            out.println("header { background-color: #4682B4; padding: 20px; text-align: center; color: white; }");
            out.println("header h1 { margin: 0; }");
            out.println("nav { text-align: center; padding: 15px; background-color: #f8f9fa; border-bottom: 1px solid #ddd; }");
            out.println("nav a { margin: 10px; padding: 10px 20px; background-color: #dbe9f5; border: 1px solid #4682B4; border-radius: 5px; color: #4682B4; text-decoration: none; font-weight: bold; }");
            out.println("nav a:hover { background-color: #b0cde9; color: #2c5d8a; }");
            out.println("section { padding: 20px; text-align: center; }");
            out.println("button { padding: 10px 20px; background-color: #4682B4; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 1em; }");
            out.println("button:hover { background-color: #315f86; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("<h1>The Public Transit Fleet Management System</h1>");
            out.println("</header>");
            out.println("<nav>");
            out.println("<a href='Servlet_MaintenanceAlerts'>Back to Maintenance Alerts</a>");
            out.println("</nav>");
            out.println("<section>");
            out.println("<h2>Maintenance Completed</h2>");
            out.println("<p>Maintenance ID <b>" + maintenanceId + "</b> has been completed successfully.</p>");
            out.println("<p>Total Cost: <b>$" + costDollars + "</b></p>");
            out.println("<button onclick=\"location.href='Servlet_MaintenanceAlerts'\">Back to Maintenance Alerts</button>");
            out.println("</section>");
            out.println("</body>");
            out.println("</html>");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input parameters");
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
        return "Servlet to complete a scheduled maintenance by recording cost and resetting usage.";
    }
}
