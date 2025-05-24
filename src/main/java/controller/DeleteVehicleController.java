/*
 * File: DeleteVehicleController.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: This controller handles the logic for deleting a vehicle from the Public Transit Fleet Management System. 
 * It processes requests to remove vehicles, interacts with the business logic layer to perform the deletion, 
 * and handles HTTP request types to determine the appropriate action.
*/

package controller;

import businesslayer.VehiclesBusinessLogic;
import javax.servlet.http.HttpServletRequest;

/**
 * Controller for delete vehicle operations.
 * @author Quoc Phong Tran
 * @see businesslayer;
 * @see javax.servlet.http.HttpServletRequest;
 * @version 1.0
 * @since 21.0.5
 */
public class DeleteVehicleController {
    
    private VehiclesBusinessLogic vehiclesBusinessLogic;
    
    /**
     * Constructor initializes the business logic.
     */
    public DeleteVehicleController() {
        vehiclesBusinessLogic = new VehiclesBusinessLogic();
    }
    
    /**
     * Gets vehicle ID from request parameters.
     * 
     * @param request the HTTP servlet request
     * @return the vehicle ID
     */
    public int getVehicleId(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("vehicleId"));
    }
    
    /**
     * Deletes a vehicle from the database.
     * 
     * @param vehicleId the ID of the vehicle to delete
     * @throws Exception if an error occurs during deletion
     */
    public void deleteVehicle(int vehicleId) throws Exception {
        vehiclesBusinessLogic.deleteVehicle(vehicleId);
    }
    
    /**
     * Determines if the request is a GET or POST.
     * 
     * @param request the HTTP servlet request
     * @return true if the request is a GET, false if it's a POST
     */
    public boolean isGetRequest(HttpServletRequest request) {
        return "GET".equalsIgnoreCase(request.getMethod());
    }
}