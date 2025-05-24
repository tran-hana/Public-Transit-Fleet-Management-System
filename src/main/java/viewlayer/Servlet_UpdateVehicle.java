/*
 * File: Servlet_UpdateVehicle.java
 * Author: Minh Hoang Vu, 041154298, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Servlet for updating vehicle information in the Public Transit Fleet Management System.
 */
package viewlayer;

import controller.UpdateVehicleController;
import transferobjects.VehicleDTO;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for updating vehicle information in the Public Transit Fleet
 * Management System.
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
public class Servlet_UpdateVehicle extends HttpServlet {

    private UpdateVehicleController controller;

    /**
     * Initializes the servlet and creates an instance of AddVehicleController
     * to handle the business logic.
     *
     * @throws ServletException if the servlet fails to initialize
     */
    @Override
    public void init() throws ServletException {
        controller = new UpdateVehicleController();
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

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            // Retrieve the existing vehicle details from the database via controller
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
            VehicleDTO vehicle = controller.getVehicleById(vehicleId);

            displayUpdateForm(response, vehicle);
        } else if ("POST".equalsIgnoreCase(request.getMethod())) {
            // Process form submission via controller
            String result = controller.processUpdateVehicle(request);

            if (result.startsWith("Vehicle with ID")) {
                displaySuccessPage(response);
            } else {
                displayErrorPage(response, result);
            }
        }
    }

    /**
     * Displays the form for updating a vehicle with pre-filled values.
     *
     * @param response the HTTP servlet response
     * @param vehicle the vehicle object containing current values
     * @throws IOException if an I/O error occurs
     */
    private void displayUpdateForm(HttpServletResponse response, VehicleDTO vehicle) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Update Vehicle - Public Transit Fleet Management System</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f9; color: #333; }");
            out.println("header { background-color: #4682B4; padding: 20px; text-align: center; color: white; }");
            out.println("header h1 { margin: 0; }");
            out.println("nav { text-align: center; padding: 15px; background-color: #f8f9fa; border-bottom: 1px solid #ddd; }");
            out.println("nav a { margin: 10px; padding: 10px 20px; background-color: #dbe9f5; border: 1px solid #4682B4; border-radius: 5px; color: #4682B4; text-decoration: none; font-weight: bold; }");
            out.println("nav a:hover { background-color: #b0cde9; color: #2c5d8a; }");
            out.println("section { padding: 20px; text-align: center; }");
            out.println("form { display: inline-block; padding: 20px; background-color: #ffffff; border: 1px solid #ddd; border-radius: 8px; box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); }");
            out.println("select, input[type=\"number\"] { width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ccc; border-radius: 5px; }");
            out.println("button { width: 100%; padding: 10px; background-color: #4682B4; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 1em; }");
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
            out.println("<h2>Update Vehicle Details</h2>");
            out.println("<form action=\"Servlet_UpdateVehicle\" method=\"POST\">");
            out.println("<label for=\"number\">Vehicle Number:</label>");
            out.println("<input type=\"number\" id=\"id\" name=\"id\" value=\"" + vehicle.getId() + "\">");
            out.println("<label for=\"type\">Vehicle Type:</label>");
            out.println("<select id=\"type\" name=\"type\" required>");
            out.println("<option value=\"Diesel Bus\"" + (vehicle.getType().equals("Diesel Bus") ? " selected" : "") + ">Diesel Bus</option>");
            out.println("<option value=\"Electric Light Rail\"" + (vehicle.getType().equals("Electric Light Rail") ? " selected" : "") + ">Electric Light Rail</option>");
            out.println("<option value=\"Diesel-Electric Train\"" + (vehicle.getType().equals("Diesel-Electric Train") ? " selected" : "") + ">Diesel-Electric Train</option>");
            out.println("</select><br>");
            out.println("<label for=\"fuelType\">Fuel Type:</label>");
            out.println("<select id=\"fuelType\" name=\"fuelType\" required>");
            out.println("<option value=\"Diesel\"" + (vehicle.getFuelType().equals("Diesel") ? " selected" : "") + ">Diesel</option>");
            out.println("<option value=\"Electric\"" + (vehicle.getFuelType().equals("Electric") ? " selected" : "") + ">Electric</option>");
            out.println("<option value=\"Hybrid\"" + (vehicle.getFuelType().equals("Hybrid") ? " selected" : "") + ">Hybrid</option>");
            out.println("</select><br>");
            out.println("<label for=\"maxPassengers\">Max Passengers:</label>");
            out.println("<input type=\"number\" id=\"maxPassengers\" name=\"maxPassengers\" value=\"" + vehicle.getMaxPassengers() + "\" required><br>");
            out.println("<label for=\"route\">Route:</label>");
            out.println("<input type=\"number\" id=\"route\" name=\"route\" value=\"" + vehicle.getRoute() + "\" required><br>");
            out.println("<label for=\"consumptionRate\">Consumption Rate:</label>");
            out.println("<input type=\"number\" id=\"consumptionRate\" name=\"consumptionRate\" step=\"0.01\" value=\"" + vehicle.getConsumptionRate() + "\" required><br>");
            out.println("<button type=\"submit\">Save</button>");
            out.println("</form>");
            out.println("</section>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Displays a success confirmation page.
     *
     * @param response the HTTP servlet response
     * @throws IOException if an I/O error occurs
     */
    private void displaySuccessPage(HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Vehicle Updated - Public Transit Fleet Management System</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f9; color: #333; }");
            out.println("header { background-color: #4682B4; padding: 20px; text-align: center; color: white; }");
            out.println("section { padding: 20px; text-align: center; }");
            out.println(".success-message { color: #28a745; margin: 20px 0; font-weight: bold; }");
            out.println("button { padding: 10px 20px; background-color: #4682B4; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 1em; }");
            out.println("button:hover { background-color: #315f86; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("<h1>The Public Transit Fleet Management System</h1>");
            out.println("</header>");
            out.println("<section>");
            out.println("<h2>Vehicle Updated Successfully</h2>");
            out.println("<p class=\"success-message\">The vehicle information has been updated in the system.</p>");
            out.println("<button onclick=\"location.href='Servlet_ViewVehicles'\">View All Vehicles</button>");
            out.println("</section>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Displays an error page with a specific message.
     *
     * @param response the HTTP servlet response
     * @param errorMessage the error message to display
     * @throws IOException if an I/O error occurs
     */
    private void displayErrorPage(HttpServletResponse response, String errorMessage) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Error - Public Transit Fleet Management System</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f9; color: #333; }");
            out.println("header { background-color: #4682B4; padding: 20px; text-align: center; color: white; }");
            out.println("section { padding: 20px; text-align: center; }");
            out.println(".error { color: #d9534f; margin: 20px 0; }");
            out.println("button { padding: 10px 20px; background-color: #4682B4; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 1em; margin: 5px; }");
            out.println("button:hover { background-color: #315f86; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("<h1>The Public Transit Fleet Management System</h1>");
            out.println("</header>");
            out.println("<section>");
            out.println("<h2>Error</h2>");
            out.println("<p class=\"error\">" + errorMessage + "</p>");
            out.println("<button onclick=\"history.back()\">Go Back</button>");
            out.println("<button onclick=\"location.href='Servlet_ViewVehicles'\">Return to Vehicle Management</button>");
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
        return "Servlet to Update Vehicle";
    }// </editor-fold>

}
