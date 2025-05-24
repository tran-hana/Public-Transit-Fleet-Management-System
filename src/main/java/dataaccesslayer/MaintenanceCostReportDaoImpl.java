/*
 * File: MaintenanceCostReportDaoImpl.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Implements the MaintenanceCostReportDao interface to provide 
 * the logic for retrieving maintenance cost reports from the database.
 */
package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import transferobjects.MaintenanceCostDTO;

/**
 * Implementation of the MaintenanceCostReportDao interface. This class connects
 * to the database, executes queries, and maps the result set to
 * MaintenanceCostDTO objects representing maintenance cost records. It serves
 * as the DAO layer between the business logic and the data source.
 *
 * @author Quoc Phong Tran
 * @see java.util.List;
 * @see transferobjects.MaintenanceCostDTO;
 * @version 1.0
 * @since 21.0.5
 */
public class MaintenanceCostReportDaoImpl implements MaintenanceCostReportDao {

    /**
     * Retrieves all maintenance cost records from the database. This includes
     * information about the cost, maintenance date, vehicle ID and type, and
     * the component type associated with the maintenance.
     *
     * @return a list of MaintenanceCostDTO objects containing maintenance cost
     * data
     */
    @Override
    public List<MaintenanceCostDTO> getAllMaintenanceCost() {
        List<MaintenanceCostDTO> list = new ArrayList<>();

        String query = "SELECT m.cost_cents, m.date, v.id AS vehicle_id, v.type AS vehicle_type, "
                + " c.type AS component_type "
                + "FROM maintenance m "
                + "JOIN component c ON m.component_id = c.id "
                + "JOIN vehicle v ON c.vehicle_id = v.id";

        try (
                Connection con = DataSource.getConnection(); PreparedStatement stmt = con.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
                MaintenanceCostDTO record = new MaintenanceCostDTO();
                record.setCost(rs.getInt("cost_cents"));
                record.setDate(date);
                record.setVehicleId(rs.getInt("vehicle_id"));
                record.setVehiclType(rs.getString("vehicle_type"));
                record.setComponentType(rs.getString("component_type"));

                list.add(record);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaintenanceCostReportDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

}
