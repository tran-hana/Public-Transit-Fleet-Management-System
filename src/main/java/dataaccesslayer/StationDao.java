/*
 * File: StationDao.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose: This interface determines operations for retrieving all station in
 * the database. It is primarily used for simulation of GPS tracking functionality.
 */

package dataaccesslayer;

import transferobjects.StationDTO;
import java.util.List;

/**
 * This interface determines operations for retrieving all station in
 * the database. It is primarily used for simulation of GPS tracking functionality.
 * 
 * @author Ha Nhu Y Tran
 * @see transferobjects.StationDTO
 * @see java.util.List
 * @version 1.0
 * @since 21.0.5
 */
public interface StationDao {

    /**
     * Retrieves all stations from the database.
     *
     * @return a list of StationDTO representing all stations
     */
    List<StationDTO> getAllStations();
}
