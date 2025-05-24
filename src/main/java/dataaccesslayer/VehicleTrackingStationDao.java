/*
 * File: VehicleTrackingStationDao.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Interface for data access related to vehicle tracking reports 
 * in the Public Transit Fleet Management System. It defines the method 
 * contract for retrieving all vehicle tracking records.
 */
package dataaccesslayer;

import java.util.List;
import transferobjects.VehicleTrackingDTO;

/**
 * The VehicleTrackingStationDao interface provides a data access abstraction
 * for retrieving vehicle tracking records from the data source in the Public
 * Transit Fleet Management System.
 *
 * @author Quoc Phong Tran
 * @see java.util.List;
 * @see transferobjects.VehicleTrackingDTO;
 * @version 1.0
 * @since 21.0.5
 */
public interface VehicleTrackingStationDao {

    /**
     * Retrieves all vehicle tracking records from the data source. Each record
     * includes relevant information such as vehicle ID, location, timestamp,
     * and station details.
     *
     * @return a list of VehicleTrackingDTO objects representing tracking data
     */
    List<VehicleTrackingDTO> getAllTracking();
}
