/*
 * File: VehicleAssignDaoImpl.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: This class implements the VehicleAssignDao interface and provides the logic
 * for accessing vehicle assignment data using JDBC. It allows retrieval of
 * assignment records by operator ID from the 'vehicle_assign' table.
 */

package dataaccesslayer;

import transferobjects.VehicleAssignDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implements the VehicleAssignDao interface for retrieving vehicle assignments.
 * Connects to the database using JDBC to fetch data from the 'vehicle_assign' table.
 * 
 * @author Ha Nhu Y Tran
 * @see java.sql.Connection
 * @see java.sql.PreparedStatement
 * @see java.sql.ResultSet
 * @see java.sql.SQLException
 * @version 1.0
 * @since 21.0.5
 */
public class VehicleAssignDaoImpl implements VehicleAssignDao {

    /**
     * Retrieves a vehicle assignment record by operator ID.
     *
     * @param operatorID the ID of the operator
     * @return the corresponding VehicleAssignDTO or null if not found
     */
    @Override
    public VehicleAssignDTO getVehicleAssignByOperatorID(int operatorID) {
        String query = "SELECT * FROM vehicle_assign WHERE operator_id = ?";

        try (
            Connection con = DataSource.getConnection();
            PreparedStatement prepStatement = con.prepareStatement(query)
        ) {
            prepStatement.setInt(1, operatorID);

            try (ResultSet rs = prepStatement.executeQuery()) {
                if (rs.next()) {
                    return new VehicleAssignDTO(
                        rs.getInt("id"),
                        rs.getInt("vehicle_id"),
                        rs.getInt("operator_id")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching vehicle assignment: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
