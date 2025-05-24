/*
 * File: Servlet_DeleteVehicle.java
 * Author: Minh Hoang Vu, 041154298, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Servlet for deleting a vehicle from the Public Transit Fleet Management
 * System. This servlet handles both HTTP GET and POST requests, presenting a 
 * confirmation interface and invoking business logic through a controller.
 */
package viewlayer;

import controller.DeleteVehicleController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for deleting a vehicle in the Public Transit Fleet Management System.
 *
 * @author Minh Hoang Vu
 * @see controller.DeleteVehicleController;
 * @see java.io.IOException;
 * @see java.io.PrintWriter;
 * @see javax.servlet.ServletException;
 * @see javax.servlet.http.HttpServlet;
 * @see javax.servlet.http.HttpServletRequest;
 * @see javax.servlet.http.HttpServletResponse;
 * @version 1.0
 * @since 21.0.5
 */
public class Servlet_DeleteVehicle extends HttpServlet {

    private DeleteVehicleController controller;

    /**
     * Initializes the servlet and its controller.
     */
    @Override
    public void init() throws ServletException {
        controller = new DeleteVehicleController();
    }

    /**
     * Handles both GET and POST requests to either display the confirmation
     * page or perform the actual deletion.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        if (controller.isGetRequest(request)) {
            // Display confirmation page
            int vehicleId = controller.getVehicleId(request);
            displayConfirmationPage(response, vehicleId);
        } else {
            // Process deletion
            int vehicleId = controller.getVehicleId(request);
            processVehicleDeletion(response, vehicleId);
        }
    }

    /**
     * Displays the confirmation HTML form for vehicle deletion.
     *
     * @param response the HTTP response
     * @param vehicleId the ID of the vehicle to confirm deletion for
     * @throws IOException if output stream fails
     */
    private void displayConfirmationPage(HttpServletResponse response, int vehicleId) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Delete Vehicle - Public Transit Fleet Management System</title>");
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
            out.println("<a href=\"Servlet_ViewVehicles\">Back to Vehicle Management</a>");
            out.println("</nav>");
            out.println("<section>");
            out.println("<h2>Confirm Delete</h2>");
            out.println("<p>Are you sure you want to delete the vehicle with Vehicle Number: <b>" + vehicleId + "</b>?</p>");
            out.println("<form action=\"Servlet_DeleteVehicle\" method=\"POST\">");
            out.println("<input type=\"hidden\" name=\"vehicleId\" value=\"" + vehicleId + "\">");
            out.println("<button type=\"submit\">Delete</button>");
            out.println("<button type=\"button\" onclick=\"location.href='Servlet_ViewVehicles'\">Cancel</button>");
            out.println("</form>");
            out.println("</section>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Processes vehicle deletion and displays a success or error message.
     *
     * @param response the HTTP response
     * @param vehicleId the ID of the vehicle to delete
     * @throws IOException if output stream fails
     */
    private void processVehicleDeletion(HttpServletResponse response, int vehicleId) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            try {
                // Delete the vehicle using the controller
                controller.deleteVehicle(vehicleId);

                // Display success page
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Vehicle Deleted - Public Transit Fleet Management System</title>");
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
                out.println("<h1>The Public Transit Fleet Management System</h1>");
                out.println("</header>");
                out.println("<section>");
                out.println("<h2>Vehicle Deleted Successfully</h2>");
                out.println("<p>The vehicle has been removed from the database.</p>");
                out.println("<button onclick=\"location.href='Servlet_ViewVehicles'\">View All Vehicles</button>");
                out.println("</section>");
                out.println("</body>");
                out.println("</html>");
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<p style='color:red;'>Error deleting vehicle: " + e.getMessage() + "</p>");
            }
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
        return "Delete vehicle servlet";
    }// </editor-fold>
}
