/*
 * File: gpsTrackingBusinessLogic.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose: This class manages the GPS tracking business logic. It retrieves station data 
 * and simulates live tracking for a vehicle by shuffling stations.
 */

package businesslayer;
import dataaccesslayer.StationDao;
import dataaccesslayer.StationDaoImpl;
import transferobjects.StationDTO;

import java.util.Collections;
import java.util.List;

/**
 * Purpose: This class manages the GPS tracking business logic. It retrieves station data 
 * and simulates live tracking for a vehicle by shuffling stations. 
 * @author Ha Nhu Y Tran
 * @see businesslayer
 * @see dataaccesslayer.StationDao
 * @see dataaccesslayer.StationDaoImpl
 * @see  transferobjects.StationDTO
 * @version 1.0
 * @since 21.0.5
 */
public class gpsTrackingBusinessLogic {
    
    /**
     * Data Access Object (DAO) for retrieving station data from the database.
     */
    private final StationDao stationDao;

    /**
     * Default constructor initializing the Station DAO.
     */
    public gpsTrackingBusinessLogic() {
        this.stationDao = new StationDaoImpl();
    }

    /**
     * Simulates GPS tracking by returning a shuffled list of station DTOs.
     *
     * @param vehicleId the vehicle ID (used for context if needed)
     * @return a list of StationDTOs in simulated travel order
     */
    public List<StationDTO> simulateGpsTracking(int vehicleId) {
        List<StationDTO> stations = stationDao.getAllStations();

        // Simulate vehicle route dynamically
        Collections.shuffle(stations);       
        return stations;
    }
}
