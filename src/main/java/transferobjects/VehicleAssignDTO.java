/*
 * File: VehicleAssignDTO.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose: This class represents the assignment of a vehicle to an operator.
 * It is used to track which user (operator) is responsible for which vehicle.
 * This DTO is used to transfer assignment data between layers of the application.
 */

package transferobjects;

/**
 * Data Transfer Object (DTO) for vehicle assignment data.
 * This is used in assigning operators to vehicles and is referenced
 * by several other parts of the system like tracking and breaks.
 * 
 * @author Ha Nhu Y Tran
 * @see transferobjects
 * @version 1.0
 * @since 21.0.5
 */
public class VehicleAssignDTO {

    /**
     * Primary key for the vehicle assignment.
     */
    private int id;

    /**
     * ID of the vehicle assigned to the operator.
     */
    private int vehicleID;

    /**
     * ID of the operator assigned to the vehicle.
     */
    private int operatorID;

    /**
     * Default constructor.
     */
    public VehicleAssignDTO() {
    }

    /**
     * Constructs a VehicleAssignDTO with all fields initialized.
     *
     * @param id         the unique ID of the vehicle assignment
     * @param vehicleID  the ID of the assigned vehicle
     * @param operatorID the ID of the assigned operator
     */
    public VehicleAssignDTO(int id, int vehicleID, int operatorID) {
        this.id = id;
        this.vehicleID = vehicleID;
        this.operatorID = operatorID;
    }

    /**
     * Gets the assignment ID.
     *
     * @return the assignment ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the assignment ID.
     *
     * @param id the assignment ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the vehicle ID in this assignment.
     *
     * @return the vehicle ID
     */
    public int getVehicleID() {
        return vehicleID;
    }

    /**
     * Sets the vehicle ID for this assignment.
     *
     * @param vehicleID the vehicle ID
     */
    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    /**
     * Gets the operator ID assigned to the vehicle.
     *
     * @return the operator ID
     */
    public int getOperatorID() {
        return operatorID;
    }

    /**
     * Sets the operator ID for this assignment.
     *
     * @param operatorID the operator ID
     */
    public void setOperatorID(int operatorID) {
        this.operatorID = operatorID;
    }
}
