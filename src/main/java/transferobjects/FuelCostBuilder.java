/*
 * File: FuelCostBuilder.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: The builder class for constructing instances of FuelCostDTO
 * used to represent the fuel cost data of vehicles in the Public Transit Fleet Management System.
 */
package transferobjects;

/**
 * FuelCostBuilder is a builder class used to create instances of FuelCostDTO.
 * It provides methods to set the properties of the fuel cost data for a vehicle
 * such as vehicle ID, vehicle type, and cost. This pattern ensures that a
 * FuelCostDTO object is constructed in a flexible and readable way.
 *
 * @author Quoc Phong Tran
 * @version 1.0
 * @since 21.0.5
 */
public class FuelCostBuilder {

    // Properties of the fuel cost data to be built
    private int vehicleId;
    private String vehicleType;
    private double cost;

    /**
     * Sets the vehicle ID for the fuel cost data.
     *
     * @param vehicleId the ID of the vehicle
     * @return the current FuelCostBuilder instance to allow method chaining
     */
    public FuelCostBuilder setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    /**
     * Sets the vehicle type for the fuel cost data.
     *
     * @param vehicleType the type of the vehicle (bus, train)
     * @return the current FuelCostBuilder instance to allow method chaining
     */
    public FuelCostBuilder setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    /**
     * Sets the cost of the fuel used by the vehicle.
     *
     * @param cost the fuel cost value
     * @return the current FuelCostBuilder instance to allow method chaining
     */
    public FuelCostBuilder setCost(double cost) {
        this.cost = cost;
        return this;
    }

    /**
     * Builds and returns a FuelCostDTO object using the set properties.
     *
     * @return a new instance of FuelCostDTO containing the fuel cost
     * information
     */
    public FuelCostDTO build() {
        return new FuelCostDTO(vehicleId, vehicleType, cost);
    }

}
