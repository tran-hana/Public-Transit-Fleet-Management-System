/*
 * File: StationDaoImpl.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose: This class implements the StationDao interface and provides the logic
 * for retrieving station records from the database. It uses JDBC to connect
 * to the database.
 */

package dataaccesslayer;

import transferobjects.StationDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the StationDao interface and provides the logic
 * for retrieving station records from the database. It uses JDBC to connect
 * to the database.
 * @see transferobjects.StationDT
 * @see java.sql.*
 * @see java.util.ArrayList
 * @see java.util.List
 * @version 1.0
 * @since 21.0.5
 */
public class StationDaoImpl implements StationDao {

    /**
     * Retrieves all stations from the 'station' table.
     *
     * @return a list of StationDTO objects
     */
    @Override
    public List<StationDTO> getAllStations() {
        List<StationDTO> stations = new ArrayList<>();
        String sql = "SELECT id, name FROM station";

        try (
            Connection con = DataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
                StationDTO station = new StationDTO();
                station.setId(rs.getInt("id"));
                station.setName(rs.getString("name"));
                stations.add(station);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving stations: " + e.getMessage());
            e.printStackTrace();
        }

        return stations;
    }
}
