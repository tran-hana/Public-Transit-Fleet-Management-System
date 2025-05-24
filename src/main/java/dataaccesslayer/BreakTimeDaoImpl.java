/*
 * File: BreakTimeDaoImpl.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: This class implements the BreakTimeDao interface to manage vehicle break records in the database.
 * It provides JDBC-based operations for inserting start time and updating end time for breaks.
 */

package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.ResultSet;


/**
 * Implements methods to interact with the break_time table using JDBC.
 * Supports adding break start times and updating break end times.
 *
 * @author Ha Nhu Y Tran
 * @see java.sql.Connection
 * @see java.sql.PreparedStatement
 * @see java.sql.SQLException
 * @see java.time.LocalDateTime
 * @version 1.0
 * @since 21.0.5
 */
public class BreakTimeDaoImpl implements BreakTimeDao {

    /**
     * Inserts a new break record into the break_time table with start time only.
     * The end time will be updated later using updateBreakEnd.
     *
     * @param assignID  the assignment ID associated with the break
     * @param startTime the start time of the break
     * @return true if the break start was inserted successfully 
     * or false if a break is already in progress or if an error occurred
     */
    @Override
    public boolean insertBreakStart(int assignID, LocalDateTime startTime) {
    String checkQuery = "SELECT COUNT(*) FROM break_time WHERE assign_id = ? AND end_time IS NULL";
    String insertQuery = "INSERT INTO break_time (assign_id, start_time) VALUES (?, ?)";

    try (
        Connection con = DataSource.getConnection();
        PreparedStatement checkStmt = con.prepareStatement(checkQuery)
    ) {
        checkStmt.setInt(1, assignID);
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next() && rs.getInt(1) > 0) {
            System.err.println("Break already in progress for assign_id: " + assignID);
            return false; // Prevent duplicate open break
        }

        try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
            insertStmt.setInt(1, assignID);
            insertStmt.setTimestamp(2, Timestamp.valueOf(startTime));
            insertStmt.executeUpdate();
            return true; //  Insert successful
        }

    } catch (SQLException e) {
        System.err.println("Error inserting break start: " + e.getMessage());
        e.printStackTrace();
        return false; 
    }
}


    /**
     * Updates an existing break record in the break_time table by setting the end time.
     *
     * @param assignID the assignment ID of the break to update
     * @param endTime  the end time to set for the break
     */
    @Override
    public void updateBreakEnd(int assignID, LocalDateTime endTime) {
        String query = "UPDATE break_time SET end_time = ? WHERE assign_id = ? AND end_time IS NULL";

        try (
            Connection con = DataSource.getConnection();
            PreparedStatement prepStatement = con.prepareStatement(query)
        ) {
            prepStatement.setTimestamp(1, Timestamp.valueOf(endTime));
            prepStatement.setInt(2, assignID);
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating break end: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
