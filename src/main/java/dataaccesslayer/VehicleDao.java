/*
 * File: VehicleDao.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: This interface determines CRUD operations for vehicle data access. This DAO interface is
 * implemented using JDBC to interact with the MySQL database table 'vehicle'.
 */

package dataaccesslayer;
import transferobjects.VehicleDTO;
import java.util.List;

/**
 * This interface determines CRUD operations for vehicle data access. This DAO interface is
 * implemented using JDBC to interact with the MySQL database table 'vehicle'.
 * @author Ha Nhu Y Tran
 * @see dataaccesslayer
 * @see transferobjects.DieselElectricTrainDTO
 * @see transferobjects.ElectricLightRailDTO
 * @see transferobjects.DieselBusDTO
 * @see transferobjects.VehicleDT
 * @version 1.0
 * @since 21.0.5
 */

public interface VehicleDao {

    /**
     * Inserts a new vehicle into the database.
     *
     * @param vehicle the vehicle to add
     */
    void addVehicle(VehicleDTO vehicle);

    /**
     * Retrieves a vehicle by its unique ID.
     *
     * @param id the vehicle ID
     * @return the matched vehicle or null if not found
     */
    VehicleDTO getVehicleById(int id);

    /**
     * Retrieves all vehicles from the database.
     *
     * @return a list of all vehicles in the database
     */
    List<VehicleDTO> getAllVehicles();

    /**
     * Updates an existing vehicle record in the database.
     *
     * @param vehicle the updated vehicle object
     */
    void updateVehicle(VehicleDTO vehicle);

    /**
     * Deletes a vehicle record from the database by ID.
     *
     * @param id the ID of the vehicle to delete
     */
    void deleteVehicle(int id);
}
