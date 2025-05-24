/*
 * File: VehicleTrackingStationIplm.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Implementation of the VehicleTrackingStationDao interface. 
 * This class retrieves vehicle tracking data from the database for the 
 * Public Transit Fleet Management System.
 */
package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import transferobjects.VehicleTrackingDTO;

/**
 * Implementation class for the VehicleTrackingStationDao interface. This class
 * handles the retrieval of vehicle tracking information, including station
 * name, arrival and departure times, vehicle ID, and vehicle type from the
 * database.
 *
 * @author Quoc Phong Tran
 * @see java.sql.Connection;
 * @see java.sql.PreparedStatement;
 * @see java.sql.ResultSet;
 * @see java.sql.SQLException;
 * @see java.time.LocalDateTime;
 * @see java.util.ArrayList;
 * @see java.util.List;
 * @see transferobjects.VehicleTrackingDTO;
 * @version 1.0
 * @since 21.0.5
 */
public class VehicleTrackingStationIplm implements VehicleTrackingStationDao {

    /**
     * Retrieves all vehicle tracking records from the database. Each record
     * contains station name, arrival and departure timestamps, vehicle ID, and
     * vehicle type.
     *
     * @return a list of VehicleTrackingDTO objects representing vehicle
     * tracking data
     */
    @Override
    public List<VehicleTrackingDTO> getAllTracking() {

        List<VehicleTrackingDTO> vehicleTrackings = new ArrayList<>();

        String query = "SELECT s.name, vt.arrival_time, vt.departure_time, va.vehicle_id, v.type FROM vehicle_tracking vt "
                + "JOIN station s ON vt.station_id = s.id "
                + "JOIN vehicle_assign va ON vt.assign_id = va.id "
                + "JOIN vehicle v ON va.vehicle_id = v.id";

        try (
                // Establish a database connection
                Connection con = DataSource.getConnection(); PreparedStatement prsm = con.prepareStatement(query);// Prepare the SQL statement for execution
                 ResultSet rs = prsm.executeQuery()) {

            while (rs.next()) {
                LocalDateTime arrivalTime = rs.getTimestamp("arrival_time").toLocalDateTime();
                LocalDateTime departureTime = rs.getTimestamp("departure_time").toLocalDateTime();
                VehicleTrackingDTO vehicleTracking = new VehicleTrackingDTO();
                vehicleTracking.setStation(rs.getString("name"));
                vehicleTracking.setArrivalTime(arrivalTime);
                vehicleTracking.setDepartureTime(departureTime);
                vehicleTracking.setVehicleID(rs.getInt("vehicle_id"));
                vehicleTracking.setType(rs.getString("type"));
                // Add recipient to the list
                vehicleTrackings.add(vehicleTracking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return the list of retrieved recipients
        return vehicleTrackings;
    }
}
