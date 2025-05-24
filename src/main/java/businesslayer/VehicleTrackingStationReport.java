/*
 * File: VehicleTrackingStationReport.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Provides the implementation of a report for vehicle tracking station data.
 * It retrieves a list of tracking data from the data source using the VehicleTrackingStationDao.
 */
package businesslayer;

import dataaccesslayer.VehicleTrackingStationDao;
import dataaccesslayer.VehicleTrackingStationIplm;
import java.util.List;

/**
 * VehicleTrackingStationReport implements the Report interface and is
 * responsible for generating a report related to vehicle tracking stations. It
 * uses the VehicleTrackingStationDao to retrieve tracking data from the data
 * source. The report generation logic is delegated to the
 * VehicleTrackingStationIplm implementation.
 *
 * @author Quoc Phong Tran
 * @see java.util.List;
 * @see dataaccesslayer.VehicleTrackingStationDao;
 * @see dataaccesslayer.VehicleTrackingStationIplm;
 * @version 1.0
 * @since 21.0.5
 */
public class VehicleTrackingStationReport implements Report {

    // Data access object for vehicle tracking station data 
    private VehicleTrackingStationDao vehicleTrackingDao = null;

    /**
     * Constructor initializes the VehicleTrackingStationDao implementation. It
     * sets up the data access object for retrieving vehicle tracking data.
     */
    public VehicleTrackingStationReport() {
        vehicleTrackingDao = new VehicleTrackingStationIplm();
    }

    /**
     * Generates a report for vehicle tracking stations by retrieving all
     * tracking data. This method calls the DAO to fetch the data and return it
     * as a list.
     *
     * @return a list of vehicle tracking data retrieved from the data source
     */
    @Override
    public List generateReport() {
        return vehicleTrackingDao.getAllTracking();
    }
}
