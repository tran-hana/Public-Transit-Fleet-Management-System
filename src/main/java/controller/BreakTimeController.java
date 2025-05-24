/*
 * File: BreakTimeController.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: This controller manages operator break time logic within the Public Transit Fleet Management System.
 * It handles retrieving vehicle assignments, starting/ending breaks, and formatting current time.
 * The Command design pattern is used for starting and ending breaks.
 */
package controller;

import businesslayer.BreakCommand;
import businesslayer.BreakInvoker;
import businesslayer.BreakService;
import businesslayer.EndBreakCommand;
import businesslayer.StartBreakCommand;
import dataaccesslayer.BreakTimeDao;
import dataaccesslayer.BreakTimeDaoImpl;
import dataaccesslayer.VehicleAssignDao;
import dataaccesslayer.VehicleAssignDaoImpl;
import transferobjects.VehicleAssignDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Controller class for handling break time business logic. This separates the
 * controller logic from the servlet presentation layer.
 *
 * @author Quoc Phong Tran
 * @see businesslayer
 * @see dataaccesslayer
 * @see transferobjects
 * @see java.time.LocalDateTime;
 * @see java.time.format.DateTimeFormatter;
 * @version 1.0
 * @since 21.0.5
 */
public class BreakTimeController {

    // Formatter to convert LocalDateTime to string in a readable format
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Data access objects to interact with database
    private final VehicleAssignDao vehicleAssignDao;
    private final BreakTimeDao breakTimeDao;

    // Invoker that executes command objects for break operations
    private final BreakInvoker invoker;

    /**
     * Constructor initializes data access objects and the command invoker.
     */
    public BreakTimeController() {
        this.vehicleAssignDao = new VehicleAssignDaoImpl();
        this.breakTimeDao = new BreakTimeDaoImpl();
        this.invoker = new BreakInvoker();
    }

    /**
     * Get vehicle assignment for a given operator.
     *
     * @param operatorId The ID of the operator
     * @return VehicleAssignDTO if assigned, null otherwise
     */
    public VehicleAssignDTO getVehicleAssignment(int operatorId) {
        return vehicleAssignDao.getVehicleAssignByOperatorID(operatorId);
    }

    /**
     * Process the start break action.
     *
     * @param assignId The vehicle assignment ID
     * @return true if successful, false otherwise
     */
    public boolean startBreak(int assignId) {
        BreakService receiver = new BreakService(breakTimeDao);
        BreakCommand startCommand = new StartBreakCommand(receiver, assignId);
        invoker.setCommand(startCommand);
        return invoker.executeCommand();
    }

    /**
     * Process the end break action.
     *
     * @param assignId The vehicle assignment ID
     * @return true if successful, false otherwise
     */
    public boolean endBreak(int assignId) {
        BreakService receiver = new BreakService(breakTimeDao);
        BreakCommand endCommand = new EndBreakCommand(receiver, assignId);
        invoker.setCommand(endCommand);
        return invoker.executeCommand();
    }

    /**
     * Get the current formatted time.
     *
     * @return String representation of current time formatted with defined
     * pattern
     */
    public String getCurrentFormattedTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        return currentTime.format(FORMATTER);
    }
}
