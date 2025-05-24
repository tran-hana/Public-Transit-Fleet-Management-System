/*
 * File: VehicleFuelUsageLogic.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: The logic for generating vehicle fuel usage reports in the Public Transit Fleet Management System.
 * It dynamically selects the appropriate report generation strategy based on the vehicle type.
 */
package businesslayer;

import java.util.List;
import transferobjects.ConsumptionDTO;

/**
 * VehicleFuelUsageLogic is responsible for generating vehicle fuel usage
 * reports based on the vehicle type (Bus, Train, Rail). It utilizes the
 * strategy pattern to select the appropriate fuel usage report generation
 * strategy for the specified vehicle type.
 *
 * @author Quoc Phong Tran
 * @see java.util.List;
 * @see transferobjects.ConsumptionDTO;
 * @version 1.0
 * @since 21.0.5
 */
public class VehicleFuelUsageLogic {

    /**
     * Generates a fuel usage report for the specified vehicle type. This method
     * uses the strategy pattern to select the appropriate report generation
     * strategy based on the vehicle type.
     *
     * @param type the type of the vehicle ("bus", "train", "rail")
     * @return a list of ConsumptionDTO containing the fuel usage data for the
     * specified vehicle type
     * @throws IllegalArgumentException if an unsupported vehicle type is
     * provided
     */
    public List<ConsumptionDTO> generateReportByType(String type) {

        FuelUsageReportStrategy strategy = getStrategyByType(type);

        return strategy.getVehicleFuelUsage();
    }

    /**
     * Selects the appropriate FuelUsageReportStrategy based on the vehicle
     * type. The strategy pattern is used here to delegate the fuel usage report
     * generation to the correct strategy implementation.
     *
     * @param type the type of the vehicle ("bus", "train", "rail")
     * @return the appropriate FuelUsageReportStrategy instance
     * @throws IllegalArgumentException if an unsupported vehicle type is
     * provided
     */
    private FuelUsageReportStrategy getStrategyByType(String type) {
        switch (type.toLowerCase()) {
            case "bus":
                return new BusFuelUsageReport();
            case "train":
                return new TrainFuelUsageReport();
            case "rail":
                return new RailEnergyUsageReport();
            default:
                throw new IllegalArgumentException("Unsupported vehicle type: " + type);
        }
    }
}
