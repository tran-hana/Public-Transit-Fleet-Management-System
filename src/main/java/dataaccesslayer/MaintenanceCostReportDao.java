/*
 * File: MaintenanceCostReportDao.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Interface for data access related to maintenance cost reports 
 * in the Public Transit Fleet Management System.
 * It defines the method contract for retrieving all maintenance costs.
 */
package dataaccesslayer;

import java.util.List;
import transferobjects.MaintenanceCostDTO;

/**
 * Data access object (DAO) interface for retrieving maintenance cost reports.
 * This interface defines the method to retrieve a list of maintenance cost
 * records from the data source.
 *
 * @author Quoc Phong Tran
 * @see java.util.List;
 * @see transferobjects.MaintenanceCostDTO;
 * @version 1.0
 * @since 21.0.5
 */
public interface MaintenanceCostReportDao {

    /**
     * Retrieves all maintenance cost records from the data source. Each record
     * includes details such as cost in cents, date of maintenance, vehicle ID
     * and type, and component type.
     *
     * @return a list of MaintenanceCostDTO objects containing maintenance cost
     * data
     */
    List<MaintenanceCostDTO> getAllMaintenanceCost();
}
