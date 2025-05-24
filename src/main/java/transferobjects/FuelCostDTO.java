/*
 * File: FuelCostDTO.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Data transfer object (DTO) to represent the fuel cost data of vehicles
 * in the Public Transit Fleet Management System. This class encapsulates 
 * the fuel cost information for a vehicle, including vehicle ID, vehicle type,and cost.
 */
package transferobjects;

/**
 * FuelCostDTO is a Data Transfer Object (DTO) class used to encapsulate the
 * fuel cost information for a vehicle. It contains properties such as the
 * vehicle's ID, type, and the associated fuel cost. This class is designed to
 * transfer data between different layers of the application.
 *
 * @author Quoc Phong Tran
 * @version 1.0
 * @since 21.0.5
 */
public class FuelCostDTO {

    // Vehicle ID
    private int vehicleId;

    // Type of the vehicle (e.g., bus, train)
    private String vehicleType;

    // The fuel cost for the vehicle
    private double cost;

    /**
     * Constructs a new FuelCostDTO with the specified values for vehicle ID,
     * vehicle type, and fuel cost.
     *
     * @param vehicleId the unique ID of the vehicle
     * @param vehicleType the type of the vehicle ("Bus", "Train")
     * @param cost the fuel cost associated with the vehicle
     */
    public FuelCostDTO(int vehicleId, String vehicleType, double cost) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.cost = cost;
    }

    /**
     * Provides the builder to create a new instance of FuelCostDTO.
     *
     * @return a new instance of FuelCostBuilder to construct a FuelCostDTO
     */
    public static FuelCostBuilder builder() {
        return new FuelCostBuilder();
    }

    /**
     * Gets the vehicle ID of this fuel cost record.
     *
     * @return the vehicle ID
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * Sets the vehicle ID for this fuel cost record.
     *
     * @param vehicleId the vehicle ID to set
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Gets the vehicle type of this fuel cost record.
     *
     * @return the vehicle type
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * Sets the vehicle type for this fuel cost record.
     *
     * @param vehicleType the vehicle type to set
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * Gets the fuel cost associated with this vehicle.
     *
     * @return the fuel cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Sets the fuel cost for this vehicle.
     *
     * @param cost the fuel cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

}
