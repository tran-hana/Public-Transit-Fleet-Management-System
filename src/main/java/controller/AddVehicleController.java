/*
 * File: AddVehicleController.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Controller for adding a new vehicle to the Public Transit Fleet Management System. 
 * This controller processes form data submitted via HTTP request, creates a VehicleDTO,
 * and passes it to the business logic layer for insertion into the system.
*/

package controller;

import businesslayer.VehiclesBusinessLogic;
import transferobjects.VehicleDTO;
import javax.servlet.http.HttpServletRequest;


/**
 * Controller class for handling vehicle addition operations.
 * This class separates business logic from the view layer.
 * @author Quoc Phong Tran
 * @see businesslayer.VehiclesBusinessLogic;
 * @see transferobjects.VehicleDTO; 
 * @see javax.servlet.http.HttpServletRequest;
 * @version 1.0
 * @since 21.0.5
 */
public class AddVehicleController {
    
    // Instance of the business logic layer for vehicle-related operations
    private VehiclesBusinessLogic vehiclesBusinessLogic;
    
    /**
     * Constructor that initializes the business logic component.
     */
    public AddVehicleController() {
        vehiclesBusinessLogic = new VehiclesBusinessLogic();
    }
    
    /**
     * Processes the form data to create and add a new vehicle to the system.
     * 
     * @param request The HTTP request containing form parameters
     * @return Response message from business logic
     */
    public String processAddVehicle(HttpServletRequest request) {
        try {
            // Capture form data from the request
            int id = Integer.parseInt(request.getParameter("id"));
            String type = request.getParameter("type");
            String fuelType = request.getParameter("fuelType");
            int maxPassengers = Integer.parseInt(request.getParameter("maxPassengers"));
            int route = Integer.parseInt(request.getParameter("route"));
            double consumptionRate = Double.parseDouble(request.getParameter("consumptionRate"));

            // Create a new VehicleDTO using factory method
            VehicleDTO vehicle = VehiclesBusinessLogic.createVehicle(id, type, fuelType, maxPassengers, route, consumptionRate);

            // Call the business logic layer to add the vehicle and get response message
            return vehiclesBusinessLogic.addVehicle(vehicle);
        } catch (NumberFormatException e) {
            return "Failed to add vehicle: Invalid number format in one of the fields";
        } catch (Exception e) {
            // Log exception details
            e.printStackTrace();
            return "Failed to add vehicle: " + e.getMessage();
        }
    }
}