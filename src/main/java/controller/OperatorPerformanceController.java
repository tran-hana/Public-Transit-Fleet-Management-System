/*
 * File: OperatorPerformanceController.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Controller for handling operator performance report logic in the Public Transit Fleet Management System.
 * This controller processes HTTP requests, retrieves operator performance data, and provides it to the view layer.
 */

package controller;

import businesslayer.OperatorReportLogic;
import transferobjects.OperatorPerformance;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller for handling operator performance report logic.
 * 
 * @author Quoc Phong Tran
 * @see businesslayer.OperatorReportLogic;
 * @see transferobjects.OperatorPerformance;
 * @see javax.servlet.http.HttpServletRequest;
 * @see javax.servlet.ServletException;
 * @see javax.servlet.http.HttpServletResponse;
 * @see java.io.IOException;
 * @see java.util.List;
 * @version 1.0
 * @since 21.0.5
 */
public class OperatorPerformanceController {
    
    public OperatorReportLogic operatorReportLogic;
    
    public OperatorPerformanceController() {
        operatorReportLogic = new OperatorReportLogic();
    }
    
    /**
     * Process the request and prepare data for the view
     * 
     * @param request servlet request
     * @param response servlet response
     * @return List of operator performance data
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public List<OperatorPerformance> processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
            
        // Fetch operator performance data
        String role = "Operator"; // Role to filter by (you can customize this)
        return operatorReportLogic.getOperatorPerformanceList(role);
    }
    
    /**
     * Determine the CSS class for efficiency rating
     * 
     * @param efficiency The efficiency rating
     * @return The corresponding CSS class
     */
    public String getEfficiencyClass(String efficiency) {
        switch (efficiency.toLowerCase()) {
            case "excellent":
                return "excellent";
            case "good":
                return "good";
            case "average":
                return "average";
            case "poor":
                return "poor";
            default:
                return ""; // Default style
        }
    }
}
