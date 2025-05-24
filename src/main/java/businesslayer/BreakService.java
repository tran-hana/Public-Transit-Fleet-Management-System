/*
 * File: BreakService.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose: This service class provides operations for managing break time functionality
 * in the system. It uses the BreakTimeDao interface to interact with the break_time table.
 * This class acts as a bridge between the controller/command layer and the data access layer.
 */

package businesslayer;

import dataaccesslayer.BreakTimeDao;
import java.time.LocalDateTime;

/**
 * This class handles operations related to vehicle operator breaks.
 * It delegates the actual data handling to the  BreakTimeDao.
 * This class supports inserting the start and end times of a break for a given assignment.
 * It is typically used by the controller or command layer to trigger break events.
 * @see businesslayer
 * @see dataaccesslayer.BreakTimeDao
 * @see java.time.LocalDateTime
 * @author Ha Nhu Y Tran
 * @version 1.0
 * @since 21.0.5
 */
public class BreakService {

    /**
     * DAO for break time operations (insert/update).
     */
    private final BreakTimeDao dao;

    /**
     * Constructs a BreakService using the specified BreakTimeDao.
     *
     * @param dao the data access object for break_time operations
     */
    public BreakService(BreakTimeDao dao) {
        this.dao = dao;
    }

    /**
     * Starts a break by inserting the current timestamp as the start time.
     *
     * @param assignID the assignment ID associated with the operator
     */
    public boolean startBreak(int assignID) {
        return dao.insertBreakStart(assignID, LocalDateTime.now());
    }

    /**
     * Ends a break by updating the current timestamp as the end time.
     *
     * @param assignID the assignment ID associated with the operator
     * @return true if the break end time was updated successfully
     */
    public boolean endBreak(int assignID) {
        dao.updateBreakEnd(assignID, LocalDateTime.now());
        return true;
    }
}
