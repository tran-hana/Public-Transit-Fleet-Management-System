/*
 * File: MaintenanceCostDTO.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Data transfer object (DTO) to represent maintenance cost data of vehicles
 * in the Public Transit Fleet Management System. This class encapsulates 
 * maintenance cost information, including vehicle ID, cost, date of maintenance, 
 * vehicle type, and component type.
 */
package transferobjects;

import java.time.LocalDateTime;

/**
 * MaintenanceCostDTO is a Data Transfer Object (DTO) class used to encapsulate
 * the maintenance cost information for a vehicle. This includes the vehicle's
 * ID, the cost of maintenance, the maintenance date, the vehicle type, and the
 * component that required maintenance.
 *
 * @author Quoc Phong Tran
 * @see java.time.LocalDateTime;
 * @version 1.0
 * @since 21.0.5
 */
public class MaintenanceCostDTO {

    // Vehicle ID associated with the maintenance record
    private int vehicleId;

    // The cost of the maintenance performed on the vehicle
    private int cost;

    // The date and time when the maintenance was performed
    private LocalDateTime date;

    // Type of the vehicle (e.g., bus, train)
    private String vehiclType;

    // Type of the component that underwent maintenance
    private String componentType;

    /**
     * Gets the vehicle ID associated with this maintenance cost record.
     *
     * @return the vehicle ID
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * Sets the vehicle ID for this maintenance cost record.
     *
     * @param vehicleId the vehicle ID to set
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Gets the cost of the maintenance performed on the vehicle.
     *
     * @return the cost of the maintenance
     */
    public int getCost() {
        return cost;
    }

    /**
     * Sets the cost of the maintenance performed on the vehicle.
     *
     * @param cost the cost to set
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Gets the date and time when the maintenance was performed.
     *
     * @return the maintenance date and time
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets the date and time when the maintenance was performed.
     *
     * @param date the date and time to set
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Gets the type of the vehicle associated with this maintenance record.
     *
     * @return the vehicle type (e.g., "Bus", "Train")
     */
    public String getVehiclType() {
        return vehiclType;
    }

    /**
     * Sets the type of the vehicle for this maintenance cost record.
     *
     * @param vehiclType the vehicle type to set
     */
    public void setVehiclType(String vehiclType) {
        this.vehiclType = vehiclType;
    }

    /**
     * Gets the type of the component that underwent maintenance.
     *
     * @return the component type (e.g., "Engine", "Brakes")
     */
    public String getComponentType() {
        return componentType;
    }

    /**
     * Sets the type of the component that underwent maintenance.
     *
     * @param componentType the component type to set
     */
    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

}
