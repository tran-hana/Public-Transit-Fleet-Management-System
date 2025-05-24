/*
 * File: VehicleTrackingDTO.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Data Transfer Object (DTO) for representing vehicle tracking information in 
 * the Public Transit Fleet Management System. This class captures vehicle tracking data 
 * such as station details, arrival and departure times, vehicle ID, and type.
 */
package transferobjects;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing vehicle tracking information. This
 * class encapsulates details of a vehicle's movements through a station,
 * including the arrival and departure times, the station name, the vehicle's
 * unique identifier, and the type of vehicle.
 *
 * @author Quoc Phong Tran
 * @see java.time.LocalDateTime;
 * @version 1.0
 * @since 21.0.5
 */
public class VehicleTrackingDTO {

    // Station name where the vehicle is tracked
    private String station;

    // The time when the vehicle arrives at the station
    private LocalDateTime arrivalTime;

    // The time when the vehicle departs from the station
    private LocalDateTime departureTime;

    // Unique identifier for the vehicle
    private int vehicleID;

    // Type of the vehicle
    private String type;

    /**
     * Constructs a new VehicleTrackingDTO with the specified station, arrival
     * time, departure time, vehicle ID, and vehicle type.
     *
     * @param station the name of the station
     * @param arrivalTime the arrival time of the vehicle at the station
     * @param departureTime the departure time of the vehicle from the station
     * @param vehicleID the unique identifier for the vehicle
     * @param type the type of the vehicle (e.g., Bus, Train)
     */
    public VehicleTrackingDTO(String station, LocalDateTime arrivalTime, LocalDateTime departureTime, int vehicleID, String type) {
        this.station = station;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.vehicleID = vehicleID;
        this.type = type;
    }

    /**
     * Default constructor for VehicleTrackingDTO. Initializes the object with
     * default values.
     */
    public VehicleTrackingDTO() {
    }

    /**
     * Gets the station where the vehicle was tracked.
     *
     * @return the name of the station
     */
    public String getStation() {
        return station;
    }

    /**
     * Sets the station where the vehicle is tracked.
     *
     * @param station the name of the station
     */
    public void setStation(String station) {
        this.station = station;
    }

    /**
     * Gets the arrival time of the vehicle at the station.
     *
     * @return the arrival time
     */
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Sets the arrival time for the vehicle at the station.
     *
     * @param arrivalTime the arrival time to set
     */
    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Gets the departure time of the vehicle from the station.
     *
     * @return the departure time
     */
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    /**
     * Sets the departure time for the vehicle from the station.
     *
     * @param departureTime the departure time to set
     */
    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * Gets the unique identifier for the vehicle.
     *
     * @return the vehicle ID
     */
    public int getVehicleID() {
        return vehicleID;
    }

    /**
     * Sets the unique identifier for the vehicle.
     *
     * @param vehicleID the vehicle ID to set
     */
    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    /**
     * Gets the type of the vehicle
     *
     * @return the type of the vehicle
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the vehicle
     *
     * @param type the type of the vehicle to set
     */
    public void setType(String type) {
        this.type = type;
    }

}
