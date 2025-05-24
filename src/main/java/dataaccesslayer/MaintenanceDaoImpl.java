package dataaccesslayer;

import transferobjects.MaintenanceDTO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the MaintenanceDao interface using JDBC.
 * Handles scheduling and completing maintenance operations.
 *
 * @author Cheng Qian
 */
public class MaintenanceDaoImpl implements MaintenanceDao {

    /**
     * Schedules a maintenance by inserting a new record without cost.
     *
     * @param componentId the ID of the component to maintain
     * @param date        the scheduled maintenance date
     */
    @Override
    public void scheduleMaintenance(int componentId, LocalDateTime date) {
        String sql = "INSERT INTO maintenance (component_id, date) VALUES (?, ?)";

        try (
            Connection con = DataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setInt(1, componentId);
            stmt.setTimestamp(2, Timestamp.valueOf(date));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error scheduling maintenance: " + e.getMessage());
        }
    }

    /**
     * Completes a maintenance by setting cost and resetting component usage hours.
     *
     * @param maintenanceId the ID of the maintenance record to complete
     * @param costCents     the maintenance cost in cents
     */
    @Override
    public void completeMaintenance(int maintenanceId, int costCents) {
        String getComponentSQL = "SELECT component_id FROM maintenance WHERE id = ?";
        String updateMaintenanceSQL = "UPDATE maintenance SET cost_cents = ?, date = ? WHERE id = ?";
        String updateComponentSQL = "UPDATE component SET usage_hours = 0 WHERE id = ?";

        try (
            Connection con = DataSource.getConnection();
            PreparedStatement getCompStmt = con.prepareStatement(getComponentSQL);
            PreparedStatement updateMaintStmt = con.prepareStatement(updateMaintenanceSQL);
            PreparedStatement updateCompStmt = con.prepareStatement(updateComponentSQL)
        ) {
            getCompStmt.setInt(1, maintenanceId);
            ResultSet rs = getCompStmt.executeQuery();

            if (rs.next()) {
                int componentId = rs.getInt("component_id");

                updateMaintStmt.setInt(1, costCents);
                updateMaintStmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                updateMaintStmt.setInt(3, maintenanceId);

                updateMaintStmt.executeUpdate();

                updateCompStmt.setInt(1, componentId);
                updateCompStmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.err.println("Error completing maintenance: " + e.getMessage());
        }
    }

    /**
     * Retrieves all maintenance records.
     *
     * @return list of MaintenanceDTO objects
     */
    @Override
    public List<MaintenanceDTO> getAllMaintenances() {
        List<MaintenanceDTO> maintenances = new ArrayList<>();
        String sql = "SELECT id, component_id, date, cost_cents FROM maintenance";

        try (
            Connection con = DataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                MaintenanceDTO maintenance = new MaintenanceDTO(
                    rs.getInt("id"),
                    rs.getInt("component_id"),
                    rs.getTimestamp("date").toLocalDateTime(),
                    rs.getInt("cost_cents")
                );

                maintenances.add(maintenance);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching maintenance records: " + e.getMessage());
        }

        return maintenances;
    }

    /**
     * Gets the most recent maintenance record for a given component.
     *
     * @param componentId the component ID
     * @return the latest MaintenanceDTO, or null if none exists
     */
    @Override
    public MaintenanceDTO getLatestMaintenanceForComponent(int componentId) {
        String sql = "SELECT id, component_id, date, cost_cents FROM maintenance " +
                     "WHERE component_id = ? ORDER BY date DESC LIMIT 1";

        try (
            Connection con = DataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setInt(1, componentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new MaintenanceDTO(
                    rs.getInt("id"),
                    rs.getInt("component_id"),
                    rs.getTimestamp("date").toLocalDateTime(),
                    rs.getInt("cost_cents")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving latest maintenance: " + e.getMessage());
        }

        return null;
    }
} 
