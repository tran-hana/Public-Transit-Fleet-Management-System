/*
 * File: VehicleDTO.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose: This class models a Data Transfer Object (DTO) for the Vehicle entity.
 * It is used to transfer vehicle data between application layers and the
 * database table 'vehicle'.
 */

package transferobjects;

/**
 * This class models a Data Transfer Object (DTO) for the Vehicle entity.
 * It is used to transfer vehicle data between application layers and the
 * database table 'vehicle'. 
 * @author Ha Nhu Y Tran
 * @see transferobjects
 * @version 1.0
 * @since 21.0.5
 */
public class VehicleDTO {
    
    /**
     * Unique identifier for the vehicle.
     */
    private int id;

    /**
     * The type of vehicle (e.g., Diesel Bus, Electric Light Rail, Diesel-Electric Train).
     */
    private String type;

    /**
     * The fuel or energy type used by the vehicle (e.g., Diesel, CNG, Electric).
     */
    private String fuelType;

    /**
     * The maximum number of passengers the vehicle can carry.
     */
    private int maxPassengers;

    /**
     * The route number or ID currently assigned to the vehicle.
     */
    private int route;

    /**
     * The rate of fuel or energy consumption for the vehicle.
     */
    private double consumptionRate;

    /**
     * Default constructor.
     */
    public VehicleDTO() {}

    /**
     * Parameterized constructor for vehicle DTO.
     * 
     * @param id              unique identifier of the vehicle
     * @param type            vehicle type (e.g., Diesel Bus, Electric Light Rail)
     * @param fuelType        fuel or energy type (e.g., Diesel, CNG, Electric)
     * @param maxPassengers   maximum number of passengers the vehicle can carry
     * @param route           assigned route number or ID
     * @param consumptionRate rate of fuel or energy consumption
     */
    public VehicleDTO(int id, String type, String fuelType, int maxPassengers, int route, double consumptionRate) {
        this.id = id;
        this.type = type;
        this.fuelType = fuelType;
        this.maxPassengers = maxPassengers;
        this.route = route;
        this.consumptionRate = consumptionRate;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public int getRoute() {
        return route;
    }

    public void setRoute(int route) {
        this.route = route;
    }

    public double getConsumptionRate() {
        return consumptionRate;
    }

    public void setConsumptionRate(double consumptionRate) {
        this.consumptionRate = consumptionRate;
    }
}
