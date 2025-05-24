/*
 * File: Servlet_ViewVehicles.java
 * Author: Minh Hoang Vu, 041154298, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: This servlet is responsible for displaying and managing vehicle data in the Public Transit 
 * Fleet Management System. It handles both HTTP GET and POST requests and communicates with 
 * the ViewVehicleController to retrieve, process, and display information about vehicles in 
 * the system.
 */
package viewlayer;

import controller.ViewVehicleController;
import transferobjects.VehicleDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet is responsible for displaying and managing vehicle data in the
 * Public Transit Fleet Management System. It handles both HTTP GET and POST
 * requests and communicates with the ViewVehicleController to retrieve,
 * process, and display information about vehicles in the system.
 *
 * @author Minh Hoang Vu
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
public class Servlet_ViewVehicles extends HttpServlet {

    private ViewVehicleController controller;

    /**
     * Initializes the servlet and creates an instance of AddVehicleController
     * to handle the business logic.
     *
     * @throws ServletException if the servlet fails to initialize
     */
    @Override
    public void init() throws ServletException {
        controller = new ViewVehicleController();
    }

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

        // First, check if there is a need to process any vehicle actions
        if (request.getParameter("vehicleId") != null) {
            controller.processVehicleAction(request, response);
            // If an action was processed and forwarded, we don't need to continue
            if (response.isCommitted()) {
                return;
            }
        }

        // Retrieve vehicles from the database using the controller
        List<VehicleDTO> vehicles = controller.getAllVehicles();

        // Display the vehicles
        displayVehiclesPage(response, vehicles);
    }

    /**
     * Displays the vehicles management page
     *
     * @param response The HTTP response
     * @param vehicles List of vehicles to display
     * @throws IOException If writing to the response fails
     */
    private void displayVehiclesPage(HttpServletResponse response, List<VehicleDTO> vehicles) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Vehicle Management - Public Transit Fleet Management System</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f9; color: #333; }");
            out.println("header { background-color: #4682B4; padding: 20px; text-align: center; color: white; }");
            out.println("header h1 { margin: 0; }");
            out.println("h2 { text-align: center; }");
            out.println("nav { text-align: center; padding: 15px; background-color: #f8f9fa; border-bottom: 1px solid #ddd; }");
            out.println("nav a { margin: 10px; padding: 10px 20px; background-color: #dbe9f5; border: 1px solid #4682B4; border-radius: 5px; color: #4682B4; text-decoration: none; font-weight: bold; }");
            out.println("nav a:hover { background-color: #b0cde9; color: #2c5d8a; }");
            out.println("table { width: 80%; margin: 20px auto; border-collapse: collapse; background-color: #fff; }");
            out.println("table, th, td { border: 1px solid #ddd; }");
            out.println("th, td { padding: 10px; text-align: center; }");
            out.println("th { background-color: #4682B4; color: white; }");
            out.println("button { margin: 5px; padding: 5px 10px; background-color: #dbe9f5; border: 1px solid #4682B4; border-radius: 5px; cursor: pointer; }");
            out.println("button:hover { background-color: #b0cde9; }");
            out.println(".add-new { display: block; width: 150px; margin: 20px auto; padding: 10px; background-color: #4682B4; color: white; text-align: center; text-decoration: none; border-radius: 5px; }");
            out.println(".add-new:hover { background-color: #315f86; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("<h1>The Public Transit Fleet Management System</h1>");
            out.println("</header>");
            out.println("<nav>");
            out.println("<a href=\"Servlet_Main_Manager\">Home</a>");
            out.println("<a href=\"Servlet_Signin\">Sign Out</a>");
            out.println("</nav>");
            out.println("<section>");
            out.println("<h2>Vehicle Management</h2>");

            // Add new vehicle button
            out.println("<a href=\"Servlet_AddVehicle\" class=\"add-new\">Add New Vehicle</a>");

            // Table to display vehicle information
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Vehicle Number</th>");
            out.println("<th>Type</th>");
            out.println("<th>Fuel Type</th>");
            out.println("<th>Max Passengers</th>");
            out.println("<th>Route</th>");
            out.println("<th>Consumption Rate</th>");
            out.println("<th>Actions</th>");
            out.println("</tr>");

            // Populate table rows from the database
            for (VehicleDTO vehicle : vehicles) {
                out.println("<tr>");
                out.println("<td>" + vehicle.getId() + "</td>");
                out.println("<td>" + vehicle.getType() + "</td>");
                out.println("<td>" + vehicle.getFuelType() + "</td>");
                out.println("<td>" + vehicle.getMaxPassengers() + "</td>");
                out.println("<td>" + vehicle.getRoute() + "</td>");
                out.println("<td>" + vehicle.getConsumptionRate() + "</td>");
                out.println("<td>");
                out.println("<button onclick=\"location.href='Servlet_UpdateVehicle?vehicleId=" + vehicle.getId() + "'\">Update</button>");
                out.println("<button onclick=\"location.href='Servlet_DeleteVehicle?vehicleId=" + vehicle.getId() + "'\">Delete</button>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</table>");
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
        return "Servlet to view vehicles";
    }// </editor-fold>

}
