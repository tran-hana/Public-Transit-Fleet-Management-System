/*
 * File: ViewVehicleController.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Controller for managing vehicle-related operations (view, add, update, delete) 
 * in the Public Transit Fleet Management System. 
 * This controller retrieves vehicle data and forwards requests to appropriate action handlers (add, update, delete).
 */
package controller;

import businesslayer.VehiclesBusinessLogic;
import transferobjects.VehicleDTO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller class for handling vehicle addition operations. This class
 * separates business logic from the view layer.
 *
 * @author Quoc Phong Tran
 * @see businesslayer.VehiclesBusinessLogic;
 * @see transferobjects.VehicleDTO;
 * @see javax.servlet.http.HttpServletRequest;
 * @see javax.servlet.http.HttpServletResponse;
 * @see javax.servlet.ServletException;
 * @see java.io.IOException;
 * @see java.util.List;
 * @version 1.0
 * @since 21.0.5
 */
public class ViewVehicleController {
    
    private final VehiclesBusinessLogic vehiclesBusinessLogic;
    
    public ViewVehicleController() {
        this.vehiclesBusinessLogic = new VehiclesBusinessLogic();
    }

    /**
     * Gets all vehicles from the database
     *
     * @return List of all vehicles
     */
    public List<VehicleDTO> getAllVehicles() {
        return vehiclesBusinessLogic.getAllVehicles();
    }

    /**
     * Gets a specific vehicle by ID
     *
     * @param vehicleId The ID of the vehicle to retrieve
     * @return The vehicle with the specified ID, or null if not found
     */
    public VehicleDTO getVehicleById(int vehicleId) {

        return vehiclesBusinessLogic.getVehicleById(vehicleId);
    }

    /**
     * Processes vehicle action requests (add, update, delete)
     *
     * @param request The HTTP request
     * @param response The HTTP response
     * @throws ServletException If servlet processing fails
     * @throws IOException If I/O operations fail
     */
    public void processVehicleAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String vehicleIdParam = request.getParameter("vehicleId");

        if (vehicleIdParam != null) {
            int vehicleId = Integer.parseInt(vehicleIdParam);

            // Determine which action to take based on the servlet path
            String servletPath = request.getServletPath();

            if (servletPath.contains("AddVehicle")) {
                // Forward to add vehicle page
                request.getRequestDispatcher("/Servlet_AddVehicle").forward(request, response);
            } else if (servletPath.contains("UpdateVehicle")) {
                // Forward to update vehicle page with vehicle ID
                request.getRequestDispatcher("/Servlet_UpdateVehicle").forward(request, response);
            } else if (servletPath.contains("DeleteVehicle")) {
                // Forward to delete confirmation page
                request.getRequestDispatcher("/Servlet_DeleteVehicle").forward(request, response);
            }
        }

    }
}
