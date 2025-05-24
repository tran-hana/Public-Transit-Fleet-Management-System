/*
 * File: VehicleDaoImpl.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose: This class implements the VehicleDao interface to perform CRUD operations on the 
 * 'vehicle' table using JDBC. This class uses a singleton DataSource 
 * connection and follows clean design principles with try-with-resources.
 */
package dataaccesslayer;

import transferobjects.VehicleDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * This class implements the VehicleDao interface to perform CRUD operations on the 
 * 'vehicle' table using JDBC. This class uses a singleton DataSource 
 * connection and follows clean design principles with try-with-resources.
 * @author Ha Nhu Y Tran
 * @see dataaccesslayer
 * @see transferobjects.VehicleDTO
 * @see java.sql.*
 * @see java.util.List * 
 * @version 1.0
 * @since 21.0.5
 */
public class VehicleDaoImpl implements VehicleDao {

    /**
     * Inserts a new vehicle into the database.
     *
     * @param vehicle the vehicle to be added
     */
    @Override
    public void addVehicle(VehicleDTO vehicle) {
        String query = "INSERT INTO vehicle (id, type, fuel_type, max_passengers, route, consumption_rate) VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection con = DataSource.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, vehicle.getId()); // Vehicle Number (Unique ID)
            pstmt.setString(2, vehicle.getType());
            pstmt.setString(3, vehicle.getFuelType());
            pstmt.setInt(4, vehicle.getMaxPassengers());
            pstmt.setInt(5, vehicle.getRoute());
            pstmt.setDouble(6, vehicle.getConsumptionRate());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error! Cannot add the new vehicle: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a vehicle by its unique ID.
     *
     * @param id the vehicle ID to search for
     * @return the VehicleDTO if found, or null otherwise
     */
    @Override
    public VehicleDTO getVehicleById(int id) {
        String query = "SELECT * FROM vehicle WHERE id = ?";

        try (
            Connection con = DataSource.getConnection(); 
            PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new VehicleDTO(
                            rs.getInt("id"),
                            rs.getString("type"),
                            rs.getString("fuel_type"),
                            rs.getInt("max_passengers"),
                            rs.getInt("route"),
                            rs.getDouble("consumption_rate")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error! Cannot retrieve vehicle by ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Retrieves all vehicles from the database.
     *
     * @return a list of all VehicleDTOs
     */
    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<VehicleDTO> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicle";

        try (
            Connection con = DataSource.getConnection(); 
            PreparedStatement pstmt = con.prepareStatement(query); 
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                VehicleDTO vehicle = new VehicleDTO(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("fuel_type"),
                        rs.getInt("max_passengers"),
                        rs.getInt("route"),
                        rs.getDouble("consumption_rate")
                );
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            System.err.println("Error! Cannot retrieve all vehicles: " + e.getMessage());
            e.printStackTrace();
        }

        return vehicles;
    }

    /**
     * Updates an existing vehicle record in the database.
     *
     * @param vehicle the updated vehicle object
     */
    @Override
    public void updateVehicle(VehicleDTO vehicle) {
        String query = "UPDATE vehicle SET type = ?, fuel_type = ?, max_passengers = ?, route = ?, consumption_rate = ? WHERE id = ?";

        try (
                Connection con = DataSource.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, vehicle.getType());
            pstmt.setString(2, vehicle.getFuelType());
            pstmt.setInt(3, vehicle.getMaxPassengers());
            pstmt.setInt(4, vehicle.getRoute());
            pstmt.setDouble(5, vehicle.getConsumptionRate());
            pstmt.setInt(6, vehicle.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating vehicle: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Deletes a vehicle by ID.
     *
     * @param vehicleId the ID of the vehicle to delete
     */
    @Override
    public void deleteVehicle(int vehicleId) {
        String deleteVehicleSql = "DELETE FROM vehicle WHERE id = ?";

        try (Connection con = DataSource.getConnection(); 
            PreparedStatement pstmt = con.prepareStatement(deleteVehicleSql)) {

            pstmt.setInt(1, vehicleId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 0) {
                System.err.println("No vehicle found with the entered ID: " + vehicleId);
            } else {
                System.out.println("Vehicle with ID " + vehicleId + " deleted successfully.");
            }

        } catch (SQLException ex) {
            // If constraint error, attempt to force delete by dropping/re-adding constraints
            if (ex.getMessage().toLowerCase().contains("foreign key constraint")) {
                System.err.println("Foreign key constraint exist. Constraints have been dropped/readded to delete vehicle.");
                deleteVehicleWithFkConstraint(vehicleId);
            } else {
                System.err.println("Database issue: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    
    /**
     * Deletes a vehicle from the database by temporarily dropping foreign key (FK)
     * constraints from other tables (`vehicle_assign`, `fuel_consumption`,
     * `maintenance`. After the deletion, FK constraints are restored.
     *
     * @param vehicleId the ID of the vehicle to delete
     */
    public void deleteVehicleWithFkConstraint(int vehicleId) {
        
        // Drop foreign key constraints
        String dropAssignmentFK = "ALTER TABLE vehicle_assign DROP FOREIGN KEY fk_assign_vehicle";
        String dropFuelFK = "ALTER TABLE consumption DROP FOREIGN KEY fk_consumption_vehicle";
        String dropMaintenanceFK = "ALTER TABLE maintenance DROP FOREIGN KEY fk_maintenance_component";
        String dropComponentFK = "ALTER TABLE component DROP FOREIGN KEY fk_component_vehicle";  

        // Delete vehicle
        String deleteVehicleSql = "DELETE FROM vehicle WHERE id = ?";

        // Re-add foreign key constraints
        String addAssignmentFK = "ALTER TABLE vehicle_assign ADD CONSTRAINT fk_assign_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)";
        String addFuelFK = "ALTER TABLE consumption ADD CONSTRAINT fk_consumption_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)";
        String addMaintenanceFK = "ALTER TABLE maintenance ADD CONSTRAINT fk_maintenance_component FOREIGN KEY (component_id) REFERENCES component(id)";
        String addComponentFK = "ALTER TABLE component ADD CONSTRAINT fk_component_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)";        
             
        try (Connection con = DataSource.getConnection()) {
            con.setAutoCommit(false); // Start transaction

            try (
                Statement dropConstraintStmt = con.createStatement(); 
                PreparedStatement deleteStmt = con.prepareStatement(deleteVehicleSql); 
                Statement addConstraintStmt = con.createStatement()) {
                // Drop all dependent foreign key constraints
                dropConstraintStmt.executeUpdate(dropAssignmentFK);
                dropConstraintStmt.executeUpdate(dropFuelFK);
                dropConstraintStmt.executeUpdate(dropMaintenanceFK);
                dropConstraintStmt.executeUpdate(dropComponentFK);

                // Delete the vehicle
                deleteStmt.setInt(1, vehicleId);
                int rowsAffected = deleteStmt.executeUpdate();

                if (rowsAffected == 0) {
                    throw new SQLException("No vehicle found with the entered ID: " + vehicleId);
                }
                
                //  Clean VehicleAssign: set vehicle_id to NULL where the vehicle no longer exists
                String cleanVehicleAssign = "UPDATE vehicle_assign SET vehicle_id = NULL WHERE vehicle_id NOT IN (SELECT id FROM vehicle)";
                addConstraintStmt.executeUpdate(cleanVehicleAssign);

                //  Clean consumption: set vehicle_id to NULL where the vehicle no longer exists
                String cleanConsumption = "UPDATE consumption SET vehicle_id = NULL WHERE vehicle_id NOT IN (SELECT id FROM vehicle)";
                addConstraintStmt.executeUpdate(cleanConsumption);

                // Clean maintenance: set component_id to NULL where the component no longer exists
                String cleanMaintenance = "UPDATE maintenance SET component_id = NULL WHERE component_id NOT IN (SELECT id FROM component)";
                addConstraintStmt.executeUpdate(cleanMaintenance);

                // Clean component: set vehicle_id to NULL where the vehicle no longer exists
                String cleanComponent = "UPDATE component SET vehicle_id = NULL WHERE vehicle_id NOT IN (SELECT id FROM vehicle)";
                addConstraintStmt.executeUpdate(cleanComponent);

                //  Re-add the constraints
                addConstraintStmt.executeUpdate(addAssignmentFK);
                addConstraintStmt.executeUpdate(addFuelFK);
                addConstraintStmt.executeUpdate(addMaintenanceFK);
                addConstraintStmt.executeUpdate(addComponentFK);

                //  Commit changes
                con.commit();
                System.out.println("Vehicle with ID " + vehicleId + " This vehicle is deleted successfully.");

            } catch (SQLException e) {
                con.rollback(); // Roll back if anything fails
                System.err.println("Transaction failed! This is rolled back.");
                e.printStackTrace();
            } finally {
                con.setAutoCommit(true); // Restore default commit behavior
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
