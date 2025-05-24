/*
 * File: VehicleAssignDao.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: This interface defines the contract for data access operations on the vehicle_assign table.
 * It allows fetching vehicle assignment records for a given operator.
 */

package dataaccesslayer;

import transferobjects.VehicleAssignDTO;

/**
 * Interface for accessing vehicle assignment data from the database.
 * Provides methods to retrieve assignments based on operator ID.
 * @see dataaccesslayer
 * @author Ha Nhu Y Tran
 * @version 1.0
 * @since 21.0.5
 */
public interface VehicleAssignDao {

    /**
     * Retrieves the vehicle assignment record associated with a given operator ID.
     *
     * @param operatorID the ID of the operator
     * @return a VehicleAssignDTO containing the assignment, or null if not found
     */
    VehicleAssignDTO getVehicleAssignByOperatorID(int operatorID);
}
