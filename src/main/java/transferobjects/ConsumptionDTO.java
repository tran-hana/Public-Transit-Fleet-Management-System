/*
 * File: ConsumptionDTO.java
 * Author: Cheng Qian
 * Date: April 03, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project
 * Professor: Ramanjeet Singh
 *
 * Purpose:
 * This class represents the Data Transfer Object (DTO) for the Consumption entity.
 * It encapsulates one record of actual vehicle energy/fuel consumption, along with
 * its associated distance and expected consumption rate from the vehicle table.
 * It is used to pass consumption-related data between the data access and business layers.
 */

package transferobjects;

/**
 * DTO representing a record from the consumption table with the corresponding
 * vehicle's expected consumption rate and vehicle type.
 *
 * @author Cheng Qian
 * @version 1.1
 * @since 21.0.5
 */
public class ConsumptionDTO {
    
    private int vehicleId;
    private double distance;
    private double actualConsumption;
    private double expectedRate;
    private String vehicleType;

    /**
     * Constructs a ConsumptionDTO with all required fields.
     * 
     * @param vehicleId the ID of the vehicle
     * @param distance the distance travelled by the vehicle
     * @param actualConsumption the actual amount of fuel or energy consumed
     * @param expectedRate the expected consumption rate from the vehicle table
     * @param vehicleType the vehicle's type (e.g. Diesel Bus, Electric Light Rail)
     */
    public ConsumptionDTO(int vehicleId, double distance, double actualConsumption, double expectedRate, String vehicleType) {
        this.vehicleId = vehicleId;
        this.distance = distance;
        this.actualConsumption = actualConsumption;
        this.expectedRate = expectedRate;
        this.vehicleType = vehicleType;
    }

    /**
     * Gets the ID of the vehicle.
     * 
     * @return the vehicle ID
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * Gets the distance travelled by the vehicle.
     * 
     * @return the distance in kilometers
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Gets the actual amount of fuel or energy consumed.
     * 
     * @return the actual consumption in liters or kWh
     */
    public double getActualConsumption() {
        return actualConsumption;
    }

    /**
     * Gets the expected fuel or energy consumption rate of the vehicle.
     * 
     * @return the expected consumption rate (e.g., L/km or kWh/km)
     */
    public double getExpectedRate() {
        return expectedRate;
    }

    /**
     * Gets the type of the vehicle (used to determine fuel or energy based classification).
     * 
     * @return the vehicle type (e.g. Diesel Bus, Electric Light Rail)
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * Calculates the expected total consumption for the given distance.
     * 
     * @return the expected consumption amount
     */
    public double getExpectedConsumption() {
        return distance * expectedRate;
    }
}
