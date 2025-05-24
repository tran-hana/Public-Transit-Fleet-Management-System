/*
 * File: Servlet_ConsumptionReport.java
 * Author: Cheng Qian, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Servlet that generates and displays a report of all vehicle consumption 
 * records retrieved from the business logic layer in the Public Transit Fleet Management System.
 */
package viewlayer;

import businesslayer.ConsumptionBusinessLogic;
import transferobjects.ConsumptionDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet that generates a simple HTML report showing all vehicle consumption
 * records. It uses the ConsumptionBusinessLogic to retrieve data from the
 * database.
 *
 * @author Cheng Qian
 * @see java.sql.SQLException;
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
public class Servlet_ConsumptionReport extends HttpServlet {

    /**
     * Handles both GET and POST requests.
     *
     * @param request the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Vehicle Consumption Report</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Vehicle Consumption Report</h2>");

            try {
                ConsumptionBusinessLogic logic = new ConsumptionBusinessLogic();
                List<ConsumptionDTO> list = logic.getAllConsumptionWithRate();

                if (list.isEmpty()) {
                    out.println("<p>No records found.</p>");
                } else {
                    out.println("<ul>");
                    for (ConsumptionDTO c : list) {
                        out.printf("<li>Vehicle ID: %d | Distance: %.2f | Actual: %.2f | Expected Rate: %.2f</li>%n",
                                c.getVehicleId(), c.getDistance(), c.getActualConsumption(), c.getExpectedRate());
                    }
                    out.println("</ul>");
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                out.println("<p style='color:red;'>Error connecting to database.</p>");
                out.printf("<pre>%s</pre>%n", ex.getMessage());
            }

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
    public String getServletInfo() {
        return "Servlet that shows vehicle consumption report.";
    }
}
