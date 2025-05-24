/*
 * File: Servlet_AddVehicle.java
 * Author: Minh Hoang Vu, 041154298, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Servlet for adding a new vehicle to the Public Transit Fleet Management
 * System. This servlet handles both HTTP GET and POST requests and
 * communicates with the controller to process vehicle data.
 */

package viewlayer;

import controller.AddVehicleController;
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
 * @see controller.AddVehicleController;
 * @see java.io.IOException;
 * @see java.io.PrintWriter;
 * @see javax.servlet.ServletException;
 * @see javax.servlet.http.HttpServlet;
 * @see javax.servlet.http.HttpServletRequest;
 * @see javax.servlet.http.HttpServletResponse;
 * @version 1.0
 * @since 21.0.5
 */
public class Servlet_AddVehicle extends HttpServlet {

    private AddVehicleController controller;

    /**
     * Initializes the servlet and creates an instance of AddVehicleController
     * to handle the business logic.
     *
     * @throws ServletException if the servlet fails to initialize
     */
    @Override
    public void init() throws ServletException {
        controller = new AddVehicleController();
    }

    /**
     * Handles both GET and POST requests.
     *
     * @param request the HTTP servlet request
     * @param response the HTTP servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            displayAddVehicleForm(response);
        } else if ("POST".equalsIgnoreCase(request.getMethod())) {
            processAddVehicleSubmission(request, response);
        }
    }

    /**
     * Generates and displays the HTML form for adding a new vehicle.
     *
     * @param response the HTTP response used to write the HTML content
     * @throws IOException if an I/O error occurs while writing the response
     */
    private void displayAddVehicleForm(HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Add New Vehicle - Public Transit Fleet Management System</title>");
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
            out.println("<h2>Add New Vehicle</h2>");
            out.println("<form action=\"Servlet_AddVehicle\" method=\"POST\">");
            out.println("<label for=\"id\">Vehicle Number:</label>");
            out.println("<input type=\"number\" id=\"id\" name=\"id\" required><br>");
            out.println("<label for=\"type\">Vehicle Type:</label>");
            out.println("<select id=\"type\" name=\"type\" required>");
            out.println("<option value=\"Diesel Bus\">Diesel Bus</option>");
            out.println("<option value=\"Electric Light Rail\">Electric Light Rail</option>");
            out.println("<option value=\"Diesel-Electric Train\">Diesel-Electric Train</option>");
            out.println("</select><br>");
            out.println("<label for=\"fuelType\">Fuel Type:</label>");
            out.println("<select id=\"fuelType\" name=\"fuelType\" required>");
            out.println("<option value=\"Diesel\">Diesel</option>");
            out.println("<option value=\"Electric\">Electric</option>");
            out.println("<option value=\"Hybrid\">Hybrid</option>");
            out.println("</select><br>");
            out.println("<label for=\"maxPassengers\">Max Passengers:</label>");
            out.println("<input type=\"number\" id=\"maxPassengers\" name=\"maxPassengers\" required><br>");
            out.println("<label for=\"route\">Route:</label>");
            out.println("<input type=\"number\" id=\"route\" name=\"route\" required><br>");
            out.println("<label for=\"consumptionRate\">Consumption Rate:</label>");
            out.println("<input type=\"number\" id=\"consumptionRate\" name=\"consumptionRate\" step = \"0.01\" required><br>");
            out.println("<button type=\"submit\">Save</button>");
            out.println("</form>");
            out.println("</section>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the POST form submission for adding a vehicle. Calls the
     * controller to process the vehicle details.
     *
     * @param request the HTTP request containing form data
     * @param response the HTTP response to return a success or error page
     * @throws IOException if an I/O error occurs while writing the response
     */
    private void processAddVehicleSubmission(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Call the controller to process the add vehicle action and get response message
        String result = controller.processAddVehicle(request);

        // Check if operation was successful
        if (result.startsWith("Vehicle with ID")) {
            displaySuccessPage(response, result);
        } else {
            displayErrorPage(response, result);
        }
    }

    /**
     * Displays a confirmation page with a success message.
     *
     * @param response the HTTP response object
     * @param message the success message to display
     * @throws IOException if an I/O error occurs
     */
    private void displaySuccessPage(HttpServletResponse response, String message) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Vehicle Added - Public Transit Fleet Management System</title>");
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
            out.println("<h2>Vehicle Operation Result</h2>");
            out.println("<p class=\"success-message\">" + message + "</p>");
            out.println("<button onclick=\"location.href='Servlet_ViewVehicles'\">View All Vehicles</button>");
            out.println("</section>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Displays an error page with the given error message.
     *
     * @param response the HTTP response object
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
            out.println("button { padding: 10px 20px; background-color: #4682B4; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 1em; }");
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
            out.println("<button onclick=\"location.href='Servlet_AddVehicle'\">Try Again</button>");
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
        return "Servlet to Track GPS";
    }// </editor-fold>

}
