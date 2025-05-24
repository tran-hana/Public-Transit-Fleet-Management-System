/*
 * File: ConsumptionReportController.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: This controller is responsible for generating consumption reports for vehicles.
 * It acts as an intermediary between the servlet and the business logic layer,
 * handling the retrieval and processing of vehicle consumption data based on vehicle type.
 */
package controller;

import businesslayer.VehicleFuelUsageLogic;
import transferobjects.ConsumptionDTO;
import java.util.List;

/**
 * Controller for handling consumption report operations. This class serves as
 * an intermediary between the servlet and business logic.
 *
 * @author Quoc Phong Tran
 * @see businesslayer
 * @see transferobjects
 * @see java.util.List;
 * @version 1.0
 * @since 21.0.5
 */
public class ConsumptionReportController {

    // Instance of the business logic layer for vehicle fuel usage
    private VehicleFuelUsageLogic vehicleFuelUsageLogic;

    /**
     * Constructor initializes the business logic component.
     */
    public ConsumptionReportController() {
        this.vehicleFuelUsageLogic = new VehicleFuelUsageLogic();
    }

    /**
     * Retrieves consumption data from the business layer filtered by vehicle
     * type.
     *
     * @param vehicleType The type of vehicle to filter by
     * @return List of ConsumptionDTO objects containing the report data
     */
    public List<ConsumptionDTO> getConsumptionReportByType(String vehicleType) {
        return vehicleFuelUsageLogic.generateReportByType(vehicleType);
    }
}
