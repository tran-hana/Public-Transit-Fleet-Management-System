/*
 * File: Servlet_Report_Consumption.java
 * Author: Minh Hoang Vu, 041154298, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: ervlet for generating and displaying consumption reports for the Public Transit Fleet Management
 * System. This servlet handles both HTTP GET and POST requests to allow users to view reports based on vehicle type.
 */
package viewlayer;

import controller.ConsumptionReportController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobjects.ConsumptionDTO;
import java.util.List;

/**
 * Servlet for generating and displaying consumption reports for the Public
 * Transit Fleet Management System. This servlet processes both HTTP GET and
 * POST requests.
 *
 * GET request: Displays a form to select the vehicle type and generate the
 * report. POST request: Submits vehicle type data to the controller and
 * displays the resulting consumption report.
 *
 * @author Minh Hoang Vu
 * @see controller.ConsumptionReportController;
 * @see java.io.IOException;
 * @see java.io.PrintWriter;
 * @see javax.servlet.ServletException;
 * @see javax.servlet.http.HttpServlet;
 * @see javax.servlet.http.HttpServletRequest;
 * @see javax.servlet.http.HttpServletResponse;
 * @see transferobjects.ConsumptionDTO;
 * @see java.util.List;
 * @version 1.0
 * @since 21.0.5
 */
public class Servlet_Report_Consumption extends HttpServlet {

    private ConsumptionReportController controller;

    /**
     * Initializes the servlet and creates an instance of AddVehicleController
     * to handle the business logic.
     *
     * @throws ServletException if the servlet fails to initialize
     */
    @Override
    public void init() throws ServletException {
        controller = new ConsumptionReportController();
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

        try (PrintWriter out = response.getWriter()) {
            if ("GET".equalsIgnoreCase(request.getMethod())) {
                displayReportForm(out);
            } else if ("POST".equalsIgnoreCase(request.getMethod())) {
                // Retrieve user input
                String type = request.getParameter("type");

                // Get data from controller
                List<ConsumptionDTO> report = controller.getConsumptionReportByType(type);

                // Display the report
                displayReport(out, report);
            }
        }
    }

    /**
     * Displays the form for selecting the vehicle type.
     *
     * @param out PrintWriter for HTML output
     */
    private void displayReportForm(PrintWriter out) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Consumption Reports - Public Transit Fleet Management System</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f9; color: #333; }");
        out.println("header { background-color: #4682B4; padding: 20px; text-align: center; color: white; }");
        out.println("header h1 { margin: 0; }");
        out.println("nav { text-align: center; padding: 15px; background-color: #f8f9fa; border-bottom: 1px solid #ddd; }");
        out.println("nav a { margin: 10px; padding: 10px 20px; background-color: #dbe9f5; border: 1px solid #4682B4; border-radius: 5px; color: #4682B4; text-decoration: none; font-weight: bold; }");
        out.println("nav a:hover { background-color: #b0cde9; color: #2c5d8a; }");
        out.println("section { padding: 20px; text-align: center; }");
        out.println("form { display: inline-block; padding: 20px; background-color: #fff; border: 1px solid #ddd; border-radius: 8px; box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); }");
        out.println("select { width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ccc; border-radius: 5px; }");
        out.println("input[type=\"text\"] { width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ccc; border-radius: 5px; }");
        out.println("button { width: 100%; padding: 10px; background-color: #4682B4; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 1em; }");
        out.println("button:hover { background-color: #315f86; }");
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
        out.println("<h2>Consumption Report</h2>");
        out.println("<form action=\"Servlet_Report_Consumption\" method=\"POST\">");
        out.println("<label for=\"type\">Select Vehicle Type:</label>");
        out.println("<select id=\"type\" name=\"type\" required>");
        out.println("<option value=\"Bus\">Bus</option>");
        out.println("<option value=\"Train\">Train</option>");
        out.println("<option value=\"Rail\">Light Rail</option>");
        out.println("</select>");
        out.println("<button type=\"submit\">Generate Report</button>");
        out.println("</form>");
        out.println("</section>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Displays the consumption report data.
     *
     * @param out PrintWriter for HTML output
     * @param report List of consumption data to display
     */
    private void displayReport(PrintWriter out, List<ConsumptionDTO> report) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Consumption Report - Public Transit Fleet Management System</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f9; color: #333; }");
        out.println("header { background-color: #4682B4; padding: 20px; text-align: center; color: white; }");
        out.println("header h1 { margin: 0; }");
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
        out.println("<h1>Public Transit Fleet Management System</h1>");
        out.println("</header>");
        out.println("<nav>");
        out.println("<a href=\"Servlet_Main_Manager\">Home</a>");
        out.println("<a href=\"Servlet_Signin\">Sign Out</a>");
        out.println("</nav>");
        out.println("<section>");
        out.println("<h2>Consumption Report</h2>");
        if (report.isEmpty()) {
            out.println("<p>No data available for the selected vehicle type.</p>");
        } else {
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Vehicle Number</th>");
            out.println("<th>Vehicle Type</th>");
            out.println("<th>Consumption Rate</th>");
            out.println("<th>Expected Consumption</th>");
            out.println("<th>ActualConsumption</th>");
            out.println("</tr>");
            for (ConsumptionDTO usage : report) {
                out.println("<tr>");
                out.println("<td>" + usage.getVehicleId() + "</td>");
                out.println("<td>" + usage.getVehicleType() + "</td>");
                out.println("<td>" + usage.getExpectedRate() + "</td>");
                out.println("<td>" + String.format("%.1f", usage.getExpectedConsumption()) + "</td>");
                out.println("<td>" + String.format("%.1f", usage.getActualConsumption()) + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }
        out.println("</section>");
        out.println("</body>");
        out.println("</html>");
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
        return "Servlet for generating and displaying consumption reports";
    }// </editor-fold>
}
