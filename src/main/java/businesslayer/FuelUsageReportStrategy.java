/*
 * File: FuelUsageReportStrategy.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Defines the strategy interface for generating fuel usage reports 
 * for various types of vehicles in the Public Transit Fleet Management System.
 */
package businesslayer;

import java.util.List;
import transferobjects.ConsumptionDTO;

/**
 * This interface is part of the Strategy Design Pattern used in the Public
 * Transit Fleet Management System to allow for different reporting strategies
 * based on vehicle types (e.g., Diesel Bus, Electric Bus).
 *
 * @author Quoc Phong Tran
 * @see java.util.List;
 * @see transferobjects.ConsumptionDTO;
 * @version 1.0
 * @since 21.0.5
 */
public interface FuelUsageReportStrategy {

    /**
     * Retrieves fuel usage data for a specific type of vehicle.
     *
     * @return a list of {@link ConsumptionDTO} containing fuel usage and
     * related data
     */
    List<ConsumptionDTO> getVehicleFuelUsage();
}
