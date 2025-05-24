package dataaccesslayer;

import transferobjects.ConsumptionDTO;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implements the ConsumptionDao using JDBC to fetch records from the consumption and vehicle tables.
 * 
 * @see transferobjects.ConsumptionDTO
 * @see java.sql.Connection
 * @see java.sql.PreparedStatement
 * @see java.sql.ResultSet
 * @see java.sql.SQLException
 * @author Cheng Qian
 * @version 1.1
 * @since 21.0.5
 */
public class ConsumptionDaoImpl implements ConsumptionDao {

    /**
     * Retrieves all consumption records joined with vehicle's consumption rate and type.
     *
     * @return a list of ConsumptionDTO objects
     */
    @Override
    public List<ConsumptionDTO> getAllConsumptionWithRate() {
        List<ConsumptionDTO> list = new ArrayList<>();

        String query = "SELECT vehicle_id, distance_travelled, actual_consumption, consumption_rate, type "
                     + "FROM consumption "
                     + "JOIN vehicle ON consumption.vehicle_id = vehicle.id";

        try (
            Connection con = DataSource.getConnection();
            PreparedStatement prepStatement = con.prepareStatement(query);
            ResultSet rs = prepStatement.executeQuery()
        ) {
            while (rs.next()) {
                ConsumptionDTO record = new ConsumptionDTO(
                    rs.getInt("vehicle_id"),
                    rs.getDouble("distance_travelled"),
                    rs.getDouble("actual_consumption"),
                    rs.getDouble("consumption_rate"),
                    rs.getString("type")
                );
                list.add(record);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching consumption data: " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Inserts a new consumption record into the database.
     *
     * @param consumptionDTO the ConsumptionDTO containing data to insert
     */
    @Override
    public void addRecord(ConsumptionDTO consumptionDTO) {
        String insertSQL = "INSERT INTO consumption (vehicle_id, distance_travelled, actual_consumption) "
                         + "VALUES (?, ?, ?)";

        try (
            Connection con = DataSource.getConnection();
            PreparedStatement prepStatement = con.prepareStatement(insertSQL)
        ) {
            prepStatement.setInt(1, consumptionDTO.getVehicleId());
            prepStatement.setDouble(2, consumptionDTO.getDistance());
            prepStatement.setDouble(3, consumptionDTO.getActualConsumption());
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting consumption record: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<ConsumptionDTO> getAllConsumptionWithRateByType(String type) {
        List<ConsumptionDTO> list = new ArrayList<>();

        String query = "SELECT vehicle_id, distance_travelled, actual_consumption, consumption_rate, type "
                     + "FROM consumption "
                     + "JOIN vehicle ON consumption.vehicle_id = vehicle.id "
                     + "WHERE type = ?";

        try (
            Connection con = DataSource.getConnection();
            PreparedStatement prepStatement = con.prepareStatement(query);
        ) {
            prepStatement.setString(1, type);
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                ConsumptionDTO record = new ConsumptionDTO(
                    rs.getInt("vehicle_id"),
                    rs.getDouble("distance_travelled"),
                    rs.getDouble("actual_consumption"),
                    rs.getDouble("consumption_rate"),
                    rs.getString("type")
                );
                list.add(record);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching consumption data: " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }
}
