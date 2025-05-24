/*
 * File: Servlet_Log_Break_Time.java
 * Author: Minh Hoang Vu, 041154298, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Servlet for logging the start and end of break times for operators
 * in the Public Transit Fleet Management System. This servlet handles both
 * HTTP GET and POST requests and communicates with the controller to process
 * break time data based on the vehicle assignment.
 */
package viewlayer;

import controller.BreakTimeController;
import transferobjects.VehicleAssignDTO;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet for logging operator break times in the Public Transit Fleet
 * Management System. Handles both GET and POST requests to either show the
 * break time options or log start/end time. Only accessible to authenticated
 * users with valid vehicle assignments.
 *
 * @author Minh Hoang Vu
 * @see controller.BreakTimeController;
 * @see transferobjects.VehicleAssignDTO;
 * @see javax.servlet.http.HttpSession;
 * @see java.io.IOException;
 * @see java.io.PrintWriter;
 * @see javax.servlet.ServletException;
 * @see javax.servlet.http.HttpServlet;
 * @see javax.servlet.http.HttpServletRequest;
 * @see javax.servlet.http.HttpServletResponse;
 * @version 1.0
 * @since 21.0.5
 */
public class Servlet_Log_Break_Time extends HttpServlet {

    private BreakTimeController controller;

    /**
     * Initializes the servlet and its controller when the servlet is first
     * loaded.
     *
     * @throws ServletException if initialization fails
     */
    @Override
    public void init() throws ServletException {
        // Initialize the controller when servlet is created
        controller = new BreakTimeController();
    }

    /**
     * Processes both HTTP GET and POST requests to handle operator break
     * logging.
     *
     * @param request the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        // Safely get session (do not create new one if it doesn't exist)
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user_id") == null) {
            // Redirect or show error if user is not authenticated
            response.sendRedirect("Servlet_Signin");
            return;
        }

        int user_id = (int) session.getAttribute("user_id");

        // Use controller to get vehicle assignment
        VehicleAssignDTO vehicleAssignDTO = controller.getVehicleAssignment(user_id);

        if (vehicleAssignDTO == null) {
            renderNoAssignmentError(response);
            return;
        }

        int assignId = vehicleAssignDTO.getId();
        int vehicleId = vehicleAssignDTO.getVehicleID();

        try (PrintWriter out = response.getWriter()) {
            if (action != null) {
                String formattedTime = controller.getCurrentFormattedTime();

                // Generate the confirmation screen
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Break Time Logged</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f9; color: #333; }");
                out.println("header { background-color: #4682B4; padding: 20px; text-align: center; color: white; }");
                out.println("header h1 { margin: 0; }");
                out.println("nav { text-align: center; padding: 15px; background-color: #f8f9fa; border-bottom: 1px solid #ddd; }");
                out.println("nav a { margin: 10px; padding: 10px 20px; background-color: #dbe9f5; border: 1px solid #4682B4; border-radius: 5px; color: #4682B4; text-decoration: none; font-weight: bold; }");
                out.println("nav a:hover { background-color: #b0cde9; color: #2c5d8a; }");
                out.println("section { padding: 20px; text-align: center; }");
                out.println("a.button { display: inline-block; margin: 15px auto; padding: 15px 25px; background-color: #dbe9f5; border: 2px solid #4682B4; border-radius: 5px; color: #4682B4; text-decoration: none; font-size: 1.2em; font-weight: bold; }");
                out.println("a.button:hover { background-color: #b0cde9; color: #2c5d8a; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<header>");
                out.println("<h1>Break Time Logged</h1>");
                out.println("<nav>");
                out.println("<a href=\"Servlet_Main_Operator\">Home</a>");
                out.println("<a href=\"Servlet_Signin\">Sign Out</a>");
                out.println("</nav>");
                out.println("</header>");
                out.println("<section>");

                boolean success = false;
                if ("start".equals(action)) {
                    // Use controller to start break
                    success = controller.startBreak(assignId);
                    if (success) {
                        out.println("<h2>Start Break Time Logged Successfully</h2>");
                        out.println("<p>Assigned Vehicle ID: " + vehicleId + "</p>");
                        out.println("<p>Start Time: " + formattedTime + "</p>");
                    } else {
                        out.println("<h2>Failed to Start Break</h2>");
                        out.println("<p>You already have an active break without end time.</p>");
                    }
                } else if ("end".equals(action)) {
                    // Use controller to end break
                    success = controller.endBreak(assignId);
                    out.println("<h2>End Break Time Logged Successfully</h2>");
                    out.println("<p>Assigned Vehicle ID: " + vehicleId + "</p>");
                    out.println("<p>End Time: " + formattedTime + "</p>");
                }
                out.println("<a class=\"button\" href=\"Servlet_Main_Operator\">Return to Dashboard</a>");
                out.println("</section>");
                out.println("</body>");
                out.println("</html>");
            } else {
                // Generate the main screen with two buttons
                generateMainScreen(out);
            }
        }
    }

    /**
     * Renders error page when operator has no vehicle assignment.
     *
     * @param response HTTP response to write to
     * @throws IOException if an I/O error occurs
     */
    private void renderNoAssignmentError(HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Assignment Error - Public Transit Fleet Management System</title>");
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
            out.println("<h1>Public Transit Fleet Management System</h1>");
            out.println("</header>");
            out.println("<nav>");
            out.println("<a href=\"Servlet_Main_Operator\">Home</a>");
            out.println("<a href=\"Servlet_Signin\">Sign Out</a>");
            out.println("</nav>");
            out.println("<section>");
            out.println("<h2>Error: No Vehicle Assigned</h2>");
            out.println("<p>You are not assigned any vehicle yet.</p>");
            out.println("<button onclick=\"location.href='Servlet_Main_Operator'\">Return to Dashboard</button>");
            out.println("</section>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Generates the main screen with break time options.
     *
     * @param out PrintWriter to write HTML output
     */
    private void generateMainScreen(PrintWriter out) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Operator Dashboard - Public Transit Fleet Management System</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f9; color: #333; }");
        out.println("header { background-color: #4682B4; padding: 20px; text-align: center; color: white; }");
        out.println("header h1 { margin: 0; }");
        out.println("nav { text-align: center; padding: 15px; background-color: #f8f9fa; border-bottom: 1px solid #ddd; }");
        out.println("nav a { margin: 10px; padding: 10px 20px; background-color: #dbe9f5; border: 1px solid #4682B4; border-radius: 5px; color: #4682B4; text-decoration: none; font-weight: bold; }");
        out.println("nav a:hover { background-color: #b0cde9; color: #2c5d8a; }");
        out.println("section { padding: 20px; text-align: center; }");
        out.println("a.button { display: inline-block; margin: 15px auto; padding: 15px 25px; background-color: #dbe9f5; border: 2px solid #4682B4; border-radius: 5px; color: #4682B4; text-decoration: none; font-size: 1.2em; font-weight: bold; }");
        out.println("a.button:hover { background-color: #b0cde9; color: #2c5d8a; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<header>");
        out.println("<h1>The Public Transit Fleet Management System</h1>");
        out.println("<nav>");
        out.println("<a href=\"Servlet_Main_Operator\">Home</a>");
        out.println("<a href=\"Servlet_Signin\">Sign Out</a>");
        out.println("</nav>");
        out.println("</header>");
        out.println("<section>");
        out.println("<h2>Role: Operator</h2>");
        out.println("<a class=\"button\" href=\"Servlet_Log_Break_Time?action=start\">Start Break</a>");
        out.println("<a class=\"button\" href=\"Servlet_Log_Break_Time?action=end\">End Break</a>");
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
     * @param response servlet request
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
        return "Log Break Time servlet";
    }// </editor-fold>
}
