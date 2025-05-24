/*
 * File: Servlet_Main_Manager.java
 * Author: Minh Hoang Vu, 041154298, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: This servlet serves as the main dashboard for Transit Managers in the
 * Public Transit Fleet Management System. It handles both HTTP GET and POST
 * requests, manages dashboard navigation, and delegates user actions to the
 * appropriate servlets.
 */
package viewlayer;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class is a servlet that provides the main dashboard interface for Transit
 * Managers in the Public Transit Fleet Management System. It handles both GET
 * and POST requests and delegates navigation based on user-selected actions to
 * various controller servlets.
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
public class Servlet_Main_Manager extends HttpServlet {

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

        String action = request.getParameter("action");
        try (PrintWriter out = response.getWriter()) {
            if (action != null) {
                switch (action) {
                    case "Home":
                        request.getRequestDispatcher("/Servlet_Main_Manager").forward(request, response);
                        break;
                    case "VehicleManagement":
                        request.getRequestDispatcher("/Servlet_ViewVehicles").forward(request, response);
                        break;
                    case "VehicleTracking":
                        request.getRequestDispatcher("/Servlet_Vehicle_Tracking").forward(request, response);
                        break;
                    case "GPSTracking":
                        request.getRequestDispatcher("/Servlet_TrackGPS").forward(request, response);
                        break;
                    case "FuelAlerts":
                        request.getRequestDispatcher("/Servlet_ConsumptionAlert").forward(request, response);
                        break;
                    case "MaintenanceAlerts":
                        request.getRequestDispatcher("/Servlet_MaintenanceAlert").forward(request, response);
                        break;
                    case "Reports":
                        request.getRequestDispatcher("/Servlet_Report_Controller").forward(request, response);
                        break;
                    case "LogTime":
                        request.getRequestDispatcher("/Servlet_Log_Break_Time").forward(request, response);
                        break;
                    case "SignOut":
                        response.sendRedirect("/Servlet_Signin");
                        break;
                    default:
                        System.err.println("Invalid action: " + action);
                        out.println("<p>Error: Invalid action specified.</p>");
                        break;
                }
            } else {
                generateDashboard(out);
            }
        }
    }

    /**
     * Generates and writes the HTML content of the Transit Manager's dashboard.
     * The dashboard provides navigation links to different functional areas.
     *
     * @param out the PrintWriter used to write the HTML response
     */
    private void generateDashboard(PrintWriter out) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Manager Dashboard - Public Transit Fleet Management System</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f9; color: #333; }");
        out.println("header { background-color: #4682B4; padding: 20px; text-align: center; color: white; }");
        out.println("header h1 { margin: 0; }");
        out.println("nav { text-align: center; padding: 15px; background-color: #f8f9fa; border-bottom: 1px solid #ddd; }");
        out.println("nav a { margin: 10px; padding: 10px 20px; background-color: #dbe9f5; border: 1px solid #4682B4; border-radius: 5px; color: #4682B4; text-decoration: none; font-weight: bold; }");
        out.println("nav a:hover { background-color: #b0cde9; color: #2c5d8a; }");
        out.println("section { padding: 20px; text-align: center; }");
        out.println("section a { display: block; margin: 10px auto; padding: 15px 20px; background-color: #dbe9f5; border: 2px solid #4682B4; border-radius: 5px; color: #4682B4; text-decoration: none; font-size: 1.1em; font-weight: bold; max-width: 300px; }");
        out.println("section a:hover { background-color: #b0cde9; color: #2c5d8a; }");
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
        out.println("<h2>Role: Transit Manager</h2>");
        out.println("<a href=\"Servlet_ViewVehicles\">Vehicle Management</a>");
        out.println("<a href=\"Servlet_Vehicle_Tracking\">Vehicle Tracking</a>");
        out.println("<a href=\"Servlet_TrackGPS\">GPS Tracking</a>");
        out.println("<a href=\"Servlet_ConsumptionAlerts\">Fuel/Energy Alerts</a>");
        out.println("<a href=\"Servlet_MaintenanceAlerts\">Maintenance Alerts</a>");
        out.println("<a href=\"Servlet_Report_Controller\">Reports</a>");
        out.println("<a class=\"button\" href=\"Servlet_Log_Break_Time\">Log your time</a>");
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
        return "Manager dashboard servlet";
    }// </editor-fold>
}
