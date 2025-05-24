/*
 * File: RailEnergyUsageReport.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: This class implements the FuelUsageReportStrategy interface
 * to generate energy usage reports for electric light rail vehicles in the 
 * Public Transit Fleet Management System. It retrieves consumption data from 
 * the data access layer for these vehicles.
 */
package businesslayer;

import dataaccesslayer.ConsumptionDao;
import dataaccesslayer.ConsumptionDaoImpl;
import java.util.List;
import transferobjects.ConsumptionDTO;

/**
 * RailEnergyUsageReport class implements the FuelUsageReportStrategy interface
 * and is responsible for generating energy usage reports for electric light
 * rail vehicles in the public transit system.
 *
 * @author Quoc Phong Tran
 * @see java.util.List;
 * @see dataaccesslayer;
 * @see transferobjects;
 * @version 1.0
 * @since 21.0.5
 */
public class RailEnergyUsageReport implements FuelUsageReportStrategy {

    private ConsumptionDao vehicleFuelUsage;

    /**
     * Constructs a new RailEnergyUsageReport instance. This constructor
     * initializes the data access object for consumption data with the default
     * ConsumptionDaoImpl implementation.
     */
    public RailEnergyUsageReport() {
        vehicleFuelUsage = new ConsumptionDaoImpl();
    }

    /**
     * Retrieves fuel consumption data for electric light rail vehicles. This
     * method queries the data access layer to get all consumption records for
     * vehicles of type "Electric Light Rail" and returns the data as a list of
     * ConsumptionDTO objects.
     *
     * @return a list of ConsumptionDTO objects containing consumption
     * information for electric light rail vehicles
     */
    @Override
    public List<ConsumptionDTO> getVehicleFuelUsage() {
        return vehicleFuelUsage.getAllConsumptionWithRateByType("Electric Light Rail");
    }

}
