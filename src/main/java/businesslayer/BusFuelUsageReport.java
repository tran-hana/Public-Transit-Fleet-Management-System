/*
 * File: BusFuelUsageReport.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Implements the FuelUsageReportStrategy interface to generate 
 * a fuel usage report specifically for Diesel Buses in the 
 * Public Transit Fleet Management System.
 */
package businesslayer;

import dataaccesslayer.ConsumptionDao;
import dataaccesslayer.ConsumptionDaoImpl;
import java.util.List;
import transferobjects.ConsumptionDTO;

/**
 * This class provides the logic to retrieve fuel consumption data specifically
 * for Diesel-powered buses from the database via the ConsumptionDao.
 *
 * @author Quoc Phong Tran
 * @see java.util.List;
 * @see dataaccesslayer.ConsumptionDao;
 * @see dataaccesslayer;
 * @version 1.0
 * @since 21.0.5
 */
public class BusFuelUsageReport implements FuelUsageReportStrategy {

    /**
     * DAO instance for accessing fuel consumption data
     */
    private ConsumptionDao vehicleFuelUsage;

    /**
     * Constructs a BusFuelUsageReport and initializes the DAO implementation.
     */
    public BusFuelUsageReport() {
        vehicleFuelUsage = new ConsumptionDaoImpl();
    }

    /**
     * Retrieves fuel usage data for all Diesel Buses.
     *
     * @return a list of {@link ConsumptionDTO} containing fuel usage and rate
     * information
     */
    @Override
    public List<ConsumptionDTO> getVehicleFuelUsage() {
        return vehicleFuelUsage.getAllConsumptionWithRateByType("Diesel Bus");
    }
}
