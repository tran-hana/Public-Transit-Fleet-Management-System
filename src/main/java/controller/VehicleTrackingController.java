/*
 * File: VehicleTrackingController.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Controller for handling vehicle tracking operations in the Public Transit Fleet Management System.
 * This controller processes the vehicle tracking information, retrieves data from the business logic layer, 
 * and provides it to the view layer for display.
*/

package controller;

import businesslayer.Report;
import businesslayer.VehicleTrackingStationReport;
import transferobjects.VehicleTrackingDTO;
import java.util.List;

/**
 * Controller class for handling vehicle tracking operations.
 * This class separates business logic from the view layer.
 * 
 * @author Quoc Phong Tran
 * @see businesslayer.VehicleTrackingStationReport;
 * @see transferobjects.VehicleTrackingDTO;
 * @see java.util.List;
 * @version 1.0
 * @since 21.0.5
 */
public class VehicleTrackingController {
    
    private Report vehicleTrackingBusinessLogic;
    
    /**
     * Constructor that initializes the business logic component.
     */
    public VehicleTrackingController() {
        vehicleTrackingBusinessLogic = new VehicleTrackingStationReport();
    }
    
    /**
     * Retrieves vehicle tracking information from the business layer.
     * 
     * @return List of vehicle tracking data objects
     */
    public List<VehicleTrackingDTO> getVehicleTrackingData() {
        return vehicleTrackingBusinessLogic.generateReport();
    }
}
