/*
 * File: Servlet_Vehicle_Tracking.java
 * Author: Minh Hoang Vu, 041154298, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Servlet for viewing vehicle tracking details in the Public Transit Fleet Management
 * System. This servlet handles both HTTP GET and POST requests to retrieve vehicle tracking
 * information from the controller and dynamically generates an HTML page to display this data.
 */
package viewlayer;

import controller.VehicleTrackingController;
import transferobjects.VehicleTrackingDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet class that handles vehicle tracking view. Retrieves a list of vehicle
 * tracking data and generates a dynamic HTML page. Handles both GET and POST
 * HTTP methods.
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
public class Servlet_Vehicle_Tracking extends HttpServlet {

    // Controller responsible for retrieving vehicle tracking data
    private VehicleTrackingController controller;

    /**
     * Initializes the servlet and sets up the controller instance.
     *
     * @throws ServletException if initialization fails
     */
    @Override
    public void init() throws ServletException {
        controller = new VehicleTrackingController();
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

        // Use the controller to get vehicle tracking data
        List<VehicleTrackingDTO> vehicleTrackings = controller.getVehicleTrackingData();

        // Display the data
        displayVehicleTrackingPage(response, vehicleTrackings);
    }

    /**
     * Displays the vehicle tracking information in an HTML page.
     *
     * @param response HTTP servlet response
     * @param vehicleTrackings list of vehicle tracking data
     * @throws IOException if an I/O error occurs
     */
    private void displayVehicleTrackingPage(HttpServletResponse response, List<VehicleTrackingDTO> vehicleTrackings)
            throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Vehicle Tracking - Public Transit Fleet Management System</title>");
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
            out.println("<h2>Vehicle Tracking</h2>");

            // Table to display vehicle tracking information
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Vehicle ID</th>");
            out.println("<th>Type</th>");
            out.println("<th>Station</th>");
            out.println("<th>Departure Time</th>");
            out.println("<th>Arrival Time</th>");
            out.println("</tr>");

            // Populate table rows from the data provided by controller
            for (VehicleTrackingDTO tracking : vehicleTrackings) {
                out.println("<tr>");
                out.println("<td>" + tracking.getVehicleID() + "</td>");
                out.println("<td>" + tracking.getType() + "</td>");
                out.println("<td>" + tracking.getStation() + "</td>");
                out.println("<td>" + tracking.getDepartureTime() + "</td>");
                out.println("<td>" + tracking.getArrivalTime() + "</td>");
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
        return "Servlet Vehicle Tracking";
    }// </editor-fold>

}
