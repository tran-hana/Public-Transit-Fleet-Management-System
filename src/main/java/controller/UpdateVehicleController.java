/*
 * File: UpdateVehicleController.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Controller for handling vehicle update operations in the Public Transit Fleet Management System. 
 * This controller processes form data submitted via HTTP request, creates or updates a VehicleDTO,
 * and passes it to the business logic layer for updating the vehicle in the system.
*/

package controller;

import businesslayer.VehiclesBusinessLogic;
import transferobjects.VehicleDTO;
import javax.servlet.http.HttpServletRequest;

/**
 * Controller class for handling vehicle update operations.
 * This class separates business logic from the view layer.
 * 
 * @author Quoc Phong Tran
 * @see businesslayer.VehiclesBusinessLogic;
 * @see transferobjects.VehicleDTO;
 * @see javax.servlet.http.HttpServletRequest;
 * @version 1.0
 * @since 21.0.5
 */
public class UpdateVehicleController {
    
    public VehiclesBusinessLogic vehiclesBusinessLogic;
    
    /**
     * Constructor that initializes the business logic component.
     */
    public UpdateVehicleController() {
        vehiclesBusinessLogic = new VehiclesBusinessLogic();
    }
    
    /**
     * Retrieves a vehicle by its ID from the business layer.
     * 
     * @param vehicleId The ID of the vehicle to retrieve
     * @return The vehicle data transfer object
     */
    public VehicleDTO getVehicleById(int vehicleId) {
        return vehiclesBusinessLogic.getVehicleById(vehicleId);
    }
    
    /**
     * Processes the form data to update an existing vehicle in the system.
     * 
     * @param request The HTTP request containing form parameters
     * @return Response message indicating success or failure
     */
    public String processUpdateVehicle(HttpServletRequest request) {
        try {
            // Extract form data from the request
            int id = Integer.parseInt(request.getParameter("id"));
            String type = request.getParameter("type");
            String fuelType = request.getParameter("fuelType");
            int maxPassengers = Integer.parseInt(request.getParameter("maxPassengers"));
            int route = Integer.parseInt(request.getParameter("route"));
            double consumptionRate = Double.parseDouble(request.getParameter("consumptionRate"));
            
            // Create a VehicleDTO using the factory method
            VehicleDTO vehicle = VehiclesBusinessLogic.createVehicle(
                id, type, fuelType, maxPassengers, route, consumptionRate);
            
            // Update the vehicle through business logic
            vehiclesBusinessLogic.updateVehicle(vehicle);
            
            return "Vehicle with ID " + id + " was updated successfully";
        } catch (NumberFormatException e) {
            return "Failed to update vehicle: Invalid number format in one of the fields";
        } catch (Exception e) {
            // Log exception details
            e.printStackTrace();
            return "Failed to update vehicle: " + e.getMessage();
        }
    }
}
