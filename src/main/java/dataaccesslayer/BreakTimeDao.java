/*
 * File: BreakTimeDao.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: This interface defines operations for managing vehicle break times in the system.
 * It provides methods for inserting break start time and updating break end time 
 * in the 'break_time' table using JDBC.
 */

package dataaccesslayer;

import java.time.LocalDateTime;

/**
 * DAO interface for managing break time records in the database.
 * Implemented using JDBC to interact with the 'break_time' table.
 * Provides functionality to insert a start time and update the end time
 * for a specific vehicle assignment.
 * 
 * @author Ha Nhu Y Tran
 * @see dataaccesslayer
 * @see java.time.LocalDateTime
 * @version 1.0
 * @since 21.0.5
 */
public interface BreakTimeDao {

    /**
     * Inserts a new break start time into the break_time table.
     *
     * @param assignID  the assignment ID to associate the break with
     * @param startTime the start time of the break
     * @return true if the break start was inserted successfully 
     * or false if a break is already in progress or if an error occurred
     */
    boolean insertBreakStart(int assignID, LocalDateTime startTime);

    /**
     * Updates the end time of the latest open break record for the given assignment.
     *
     * @param assignID the assignment ID whose break is being ended
     * @param endTime  the end time of the break
     */
    void updateBreakEnd(int assignID, LocalDateTime endTime);
}
