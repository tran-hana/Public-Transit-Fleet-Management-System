/*
 * File: Servlet_Report_Cost_Usage.java
 * Author: Minh Hoang Vu, 041154298, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Servlet for generating a fuel cost report for the Public Transit Fleet Management System.
 * This servlet processes both HTTP GET and POST requests to generate and display fuel cost data for the vehicles in the fleet.
 */
package viewlayer;

import businesslayer.FuleCostReportAdapter;
import businesslayer.Report;
import transferobjects.FuelCostDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for generating and displaying the fuel cost report for the vehicles
 * in the Public Transit Fleet Management System. The servlet handles both HTTP
 * GET and POST requests and communicates with the business layer to process
 * fuel cost data.
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
public class Servlet_Report_Cost_Usage extends HttpServlet {

    private Report fuelCostReportAdapter;

    /**
     * Initializes the servlet and creates an instance of the
     * FuelCostReportAdapter for generating the fuel cost report.
     *
     * @throws ServletException if a servlet-specific error occurs during
     * initialization
     */
    @Override
    public void init() throws ServletException {
        fuelCostReportAdapter = new FuleCostReportAdapter();
    }

    /**
     * Processes both GET and POST requests to generate the fuel cost report.
     *
     * @param request the HttpServletRequest object containing the request data
     * @param response the HttpServletResponse object used to send the response
     * @throws ServletException if a servlet-specific error occurs during
     * request processing
     * @throws IOException if an I/O error occurs during request or response
     * handling
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Generate fuel cost report from business logic layer
        List<FuelCostDTO> fuelCosts = fuelCostReportAdapter.generateReport();

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Fuel Cost Report - Public Transit Fleet Management System</title>");
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
            out.println("<h2>Fuel Cost Report</h2>");

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Vehicle ID</th>");
            out.println("<th>Vehicle Type</th>");
            out.println("<th>Fuel Cost (in dollars)</th>");
            out.println("</tr>");

            if (fuelCosts.isEmpty()) {
                out.println("<tr><td colspan='3'>No fuel cost data available</td></tr>");
            } else {
                for (FuelCostDTO cost : fuelCosts) {
                    out.println("<tr>");
                    out.println("<td>" + cost.getVehicleId() + "</td>");
                    out.println("<td>" + cost.getVehicleType() + "</td>");
                    out.println("<td>$" + String.format("%.2f", cost.getCost()) + "</td>");
                    out.println("</tr>");
                }
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
        return "Short description";
    }// </editor-fold>

}
