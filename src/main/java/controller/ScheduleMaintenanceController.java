/*
 * File: ScheduleMaintenanceController.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: CController for handling maintenance scheduling operations in the Public Transit Fleet Management System.
 * This controller processes HTTP request parameters, validates them, and schedules maintenance through the data access layer.
 */
package controller;

import dataaccesslayer.MaintenanceDao;
import dataaccesslayer.MaintenanceDaoImpl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.servlet.http.HttpServletRequest;

/**
 * Controller class for handling maintenance scheduling operations. This class
 * separates business logic from the view layer and servlet.
 *
 * @author Quoc Phong Tran
 * @see dataaccesslayer.MaintenanceDao;
 * @see dataaccesslayer.MaintenanceDaoImpl;
 * @see javax.servlet.http.HttpServletRequest;
 * @see java.time.LocalDateTime;
 * @see java.time.format.DateTimeFormatter;
 * @see java.time.format.DateTimeParseException;
 * @version 1.0
 * @since 21.0.5
 */
public class ScheduleMaintenanceController {

    private MaintenanceDao maintenanceDao;

    /**
     * Constructor that initializes the data access object.
     */
    public ScheduleMaintenanceController() {
        maintenanceDao = new MaintenanceDaoImpl();
    }

    /**
     * Inner class to hold the result of a maintenance scheduling operation.
     */
    public static class MaintenanceResult {

        private boolean success;
        private int componentId;
        private String formattedDate;

        public MaintenanceResult(boolean success, int componentId, String formattedDate) {
            this.success = success;
            this.componentId = componentId;
            this.formattedDate = formattedDate;
        }

        public boolean isSuccess() {
            return success;
        }

        public int getComponentId() {
            return componentId;
        }

        public String getFormattedDate() {
            return formattedDate;
        }
    }

    /**
     * Processes the request parameters and schedules maintenance if valid.
     *
     * @param request The HTTP request containing parameters
     * @return The result of the maintenance scheduling operation
     */
    public MaintenanceResult processScheduleMaintenance(HttpServletRequest request) {
        String componentIdParam = request.getParameter("componentId");
        String scheduledDate = request.getParameter("date");

        try {
            // Validate parameters
            if (componentIdParam == null || !componentIdParam.matches("\\d+")
                    || scheduledDate == null || scheduledDate.isBlank()) {
                return new MaintenanceResult(false, 0, null);
            }

            // Parse and process values
            int componentId = Integer.parseInt(componentIdParam);

            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime parsedDate = LocalDateTime.parse(scheduledDate, inputFormatter);

            // Schedule the maintenance through DAO
            maintenanceDao.scheduleMaintenance(componentId, parsedDate);

            // Format the date for display
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String displayDate = parsedDate.format(outputFormatter);

            return new MaintenanceResult(true, componentId, displayDate);

        } catch (NumberFormatException | DateTimeParseException e) {
            // Log the exception if needed
            e.printStackTrace();
            return new MaintenanceResult(false, 0, null);
        } catch (Exception e) {
            // Log any other exceptions
            e.printStackTrace();
            return new MaintenanceResult(false, 0, null);
        }
    }
}
