/*
 * File: Servlet_Report_Performance_Operator.java
 * Author: Minh Hoang Vu, 041154298, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Servlet for generating an operator performance report in the Public Transit Fleet Management System.
 * This servlet handles both HTTP GET and POST requests and communicates with the controller to process and display operator performance data.
 */
package viewlayer;

import controller.OperatorPerformanceController;
import transferobjects.OperatorPerformance;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for displaying the operator performance report in the Public Transit
 * Fleet Management System. This servlet processes both HTTP GET and POST
 * requests to fetch and display operator performance data. It communicates with
 * the controller to retrieve the data, which is then rendered as an HTML table.
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
public class Servlet_Report_Performance_Operator extends HttpServlet {

    private OperatorPerformanceController controller;

    /**
     * Initializes the servlet and creates an instance of AddVehicleController
     * to handle the business logic.
     *
     * @throws ServletException if the servlet fails to initialize
     */
    @Override
    public void init() throws ServletException {
        controller = new OperatorPerformanceController();
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
            // Get data from controller
            List<OperatorPerformance> performanceList = controller.processRequest(request, response);

            // Render the view
            renderView(out, performanceList);
        }
    }

    /**
     * Renders the HTML view
     *
     * @param out PrintWriter to output HTML
     * @param performanceList List of operator performance data
     */
    private void renderView(PrintWriter out, List<OperatorPerformance> performanceList) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Operator Performance Report - Public Transit Fleet Management System</title>");
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
        out.println(".excellent { color: green; font-weight: bold; }");
        out.println(".good { color: #70e000; font-weight: bold; }");
        out.println(".average { color: orange; font-weight: bold; }");
        out.println(".poor { color: red; font-weight: bold; }");
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
        out.println("<h2>Operator Performance Report</h2>");

        if (performanceList.isEmpty()) {
            out.println("<p>No operator performance data available.</p>");
        } else {
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Operator Name</th>");
            out.println("<th>Email</th>");
            out.println("<th>On-Time Rate</th>");
            out.println("<th>Efficiency</th>");
            out.println("</tr>");

            for (OperatorPerformance performance : performanceList) {
                String efficiencyClass = controller.getEfficiencyClass(performance.getEfficiency());

                out.println("<tr>");
                out.println("<td>" + performance.getUser().getName() + "</td>");
                out.println("<td>" + performance.getUser().getEmail() + "</td>");
                out.println(String.format("<td>%.2f%%</td>", performance.getOnTimeRate() * 100));
                out.println("<td class=\"" + efficiencyClass + "\">" + performance.getEfficiency() + "</td>");
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
        return "Short description";
    }// </editor-fold>
}
