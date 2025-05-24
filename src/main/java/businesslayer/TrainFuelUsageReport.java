/*
 * File: TrainFuelUsageReport.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Implementation of the FuelUsageReportStrategy interface for generating
 * fuel usage reports for Diesel-Electric Trains in the Public Transit Fleet Management System.
 * This class retrieves fuel consumption data for the specified vehicle type from the data source.
 */
package businesslayer;

import dataaccesslayer.ConsumptionDao;
import dataaccesslayer.ConsumptionDaoImpl;
import java.util.List;
import transferobjects.ConsumptionDTO;

/**
 * TrainFuelUsageReport is a concrete implementation of the
 * FuelUsageReportStrategy interface. It generates a fuel usage report for
 * Diesel-Electric Trains by retrieving data from the data source using the
 * ConsumptionDao interface.
 *
 * @author Quoc Phong Tran
 * @see java.util.List;
 * @see dataaccesslayer;
 * @see transferobjects;
 * @version 1.0
 * @since 21.0.5
 */
public class TrainFuelUsageReport implements FuelUsageReportStrategy {

    private ConsumptionDao vehicleFuelUsage;

    /**
     * Constructs a new TrainFuelUsageReport instance. Initializes the
     * ConsumptionDao implementation used to retrieve fuel consumption
     * data.
     */
    public TrainFuelUsageReport() {
        vehicleFuelUsage = new ConsumptionDaoImpl();
    }

    /**
     * Retrieves fuel usage data for Diesel-Electric Trains.
     *
     * @return a list of ConsumptionDTO containing fuel consumption data
     * for Diesel-Electric Trains.
     */
    @Override
    public List<ConsumptionDTO> getVehicleFuelUsage() {
        return vehicleFuelUsage.getAllConsumptionWithRateByType("Diesel-Electric Train");
    }
}
