package dataaccesslayer;

import transferobjects.ComponentDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the ComponentDao interface using JDBC.
 * Provides operations to access and update component records.
 *
 * @see transferobjects.ComponentDTO
 * @see java.sql.Connection
 * @see java.sql.PreparedStatement
 * @see java.sql.ResultSet
 * @see java.sql.SQLException
 * @author Cheng Qian
 * @since 21.0.5
 */
public class ComponentDaoImpl implements ComponentDao {

    @Override
    public List<ComponentDTO> getAllComponents() {
        List<ComponentDTO> components = new ArrayList<>();
        String sql = "SELECT * FROM component";

        try (
            Connection con = DataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                ComponentDTO component = new ComponentDTO();
                component.setId(rs.getInt("id"));
                component.setVehicleId(rs.getInt("vehicle_id"));
                component.setType(rs.getString("type"));
                component.setUsageHours(rs.getInt("usage_hours"));
                components.add(component);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching component data: " + e.getMessage());
        }

        return components;
    }

    @Override
    public void updateUsageHours(int componentId, int usageHours) {
        String sql = "UPDATE component SET usage_hours = ? WHERE id = ?";

        try (
            Connection con = DataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setInt(1, usageHours);
            stmt.setInt(2, componentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating usage hours: " + e.getMessage());
        }
    }

    @Override
    public List<ComponentDTO> getComponentsByVehicle(int vehicleId) {
        List<ComponentDTO> components = new ArrayList<>();
        String sql = "SELECT * FROM component WHERE vehicle_id = ?";

        try (
            Connection con = DataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ComponentDTO component = new ComponentDTO();
                component.setId(rs.getInt("id"));
                component.setVehicleId(rs.getInt("vehicle_id"));
                component.setType(rs.getString("type"));
                component.setUsageHours(rs.getInt("usage_hours"));
                components.add(component);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching components by vehicle: " + e.getMessage());
        }

        return components;
    }
} 
