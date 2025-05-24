/*
 * File: Servlet_TrackGPS.java
 * Author: Minh Hoang Vu, 041154298, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: This servlet allows managers to track the GPS location of a specific 
 * vehicle in the Public Transit Fleet Management System. It handles HTTP GET 
 * requests to display a form and POST requests to process the vehicle number, 
 * verify its existence, and simulate GPS tracking by displaying the route data.
 */
package viewlayer;

import businesslayer.gpsTrackingBusinessLogic;
import businesslayer.VehiclesBusinessLogic;
import transferobjects.StationDTO;
import transferobjects.VehicleDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Servlet_TrackGPS class enables GPS tracking functionality for a specific
 * vehicle within the Public Transit Fleet Management System.
 *
 * @author Minh Hoang Vu
 * @see businesslayer.gpsTrackingBusinessLogic;
 * @see businesslayer.VehiclesBusinessLogic;
 * @see transferobjects.StationDTO;
 * @see transferobjects.VehicleDTO;
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
public class Servlet_TrackGPS extends HttpServlet {

    private gpsTrackingBusinessLogic gpsLogic;
    private VehiclesBusinessLogic vehicleLogic;

    /**
     * Initializes business logic objects for GPS tracking and vehicle data
     * access.
     *
     * @throws ServletException if the servlet cannot be initialized properly
     */
    @Override
    public void init() throws ServletException {
        gpsLogic = new gpsTrackingBusinessLogic();
        vehicleLogic = new VehiclesBusinessLogic();
    }

    /**
     * Processes both GET and POST requests for GPS tracking. - If no vehicle ID
     * is provided, displays the input form. - If a vehicle ID is provided,
     * validates the vehicle and displays the GPS tracking data.
     *
     * @param request the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String vehicleId = request.getParameter("id");

        // Display the input form for the vehicle number
        if (vehicleId == null || vehicleId.isEmpty()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Track GPS - Public Transit Fleet Management System</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f9; color: #333; }");
            out.println("header { background-color: #4682B4; padding: 20px; text-align: center; color: white; }");
            out.println("nav { text-align: center; padding: 15px; background-color: #f8f9fa; border-bottom: 1px solid #ddd; }");
            out.println("nav a { margin: 10px; padding: 10px 20px; background-color: #dbe9f5; border: 1px solid #4682B4; border-radius: 5px; color: #4682B4; text-decoration: none; font-weight: bold; }");
            out.println("nav a:hover { background-color: #b0cde9; color: #2c5d8a; }");
            out.println("section { padding: 20px; text-align: center; }");
            out.println("form { margin: 20px auto; padding: 20px; background-color: #fff; border: 1px solid #ddd; border-radius: 8px; box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); width: 50%; }");
            out.println("input[type='text'] { width: 90%; padding: 10px; margin-bottom: 10px; border: 1px solid #ccc; border-radius: 5px; }");
            out.println("button { padding: 10px 20px; background-color: #4682B4; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 1em; }");
            out.println("button:hover { background-color: #315f86; }");
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
            out.println("<h2>Vehicle GPS Tracking</h2>");
            out.println("<form action=\"Servlet_TrackGPS\" method=\"POST\">");
            out.println("<label for=\"id\">Enter Vehicle Number:</label><br>");
            out.println("<input type=\"text\" id=\"id\" name=\"id\" placeholder=\"Enter Vehicle Number\" required><br>");
            out.println("<button type=\"submit\">Track GPS</button>");
            out.println("</form>");
            out.println("</section>");
            out.println("</body>");
            out.println("</html>");
        } else {
            // Retrieve GPS tracking data for the vehicle
            int vehicleIdParsed = Integer.parseInt(vehicleId); // Ensure it's valid
            // Check if vehicle exists using existing in database
            VehicleDTO vehicle = vehicleLogic.getVehicleById(vehicleIdParsed);

            if (vehicle == null) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error - Vehicle Not Found</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f9; color: #333; }");
                out.println("header { background-color: #4682B4; padding: 20px; text-align: center; color: white; }");
                out.println("header h1 { margin: 0; }");
                out.println("nav { text-align: center; padding: 15px; background-color: #f8f9fa; border-bottom: 1px solid #ddd; }");
                out.println("nav a { margin: 10px; padding: 10px 20px; background-color: #dbe9f5; border: 1px solid #4682B4; border-radius: 5px; color: #4682B4; text-decoration: none; font-weight: bold; }");
                out.println("nav a:hover { background-color: #b0cde9; color: #2c5d8a; }");
                out.println("section { text-align: center; padding: 50px; color: red; }");
                out.println("button { padding: 10px 20px; background-color: #4682B4; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 1em; }");
                out.println("button:hover { background-color: #315f86; }");
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
                out.println("<h2>Error: Vehicle Not Found</h2>");
                out.println("<p>No vehicle found in the database with ID: <strong>" + vehicleIdParsed + "</strong>.</p>");
                out.println("<button onclick=\"location.href='Servlet_TrackGPS'\">Back to GPS Tracking Page</button>");
                out.println("</section>");
                out.println("</body>");
                out.println("</html>");
                return;
            }

            List<StationDTO> stations = gpsLogic.simulateGpsTracking(vehicleIdParsed);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>GPS Tracking - Public Transit Fleet Management System</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f9; color: #333; }");
            out.println("header { background-color: #4682B4; padding: 20px; text-align: center; color: white; }");
            out.println("nav { text-align: center; padding: 15px; background-color: #f8f9fa; border-bottom: 1px solid #ddd; }");
            out.println("nav a { margin: 10px; padding: 10px 20px; background-color: #dbe9f5; border: 1px solid #4682B4; border-radius: 5px; color: #4682B4; text-decoration: none; font-weight: bold; }");
            out.println("nav a:hover { background-color: #b0cde9; color: #2c5d8a; }");
            out.println("section { padding: 20px; text-align: center; }");
            out.println("table { width: 80%; margin: 20px auto; border-collapse: collapse; background-color: #fff; }");
            out.println("table, th, td { border: 1px solid #ddd; }");
            out.println("th, td { padding: 10px; text-align: center; }");
            out.println("th { background-color: #4682B4; color: white; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("<h1>Live GPS Tracking for Vehicle " + vehicleId + "</h1>");
            out.println("</header>");
            out.println("<nav>");
            out.println("<a href=\"Servlet_Main_Manager\">Home</a>");
            out.println("<a href=\"Servlet_Signin\">Sign Out</a>");
            out.println("</nav>");
            out.println("<section>");
            out.println("<h2>Live GPS Tracking</h2>");

            if (stations == null || stations.isEmpty()) {
                out.println("<p style='color:red;'>No GPS tracking data available for vehicle: " + vehicleId + "</p>");
            } else {
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>Station ID</th>");
                out.println("<th>Station Name</th>");
                out.println("</tr>");

                for (StationDTO station : stations) {
                    out.println("<tr>");
                    out.println("<td>" + station.getId() + "</td>");
                    out.println("<td>" + station.getName() + "</td>");
                    out.println("</tr>");
                    out.flush();
                    try {
                        Thread.sleep(2000); // Simulate delay of 1 second between locations
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                out.println("</table>");
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
        return "Track GPS servlet";
    }// </editor-fold>
}
