/*
 * File: ConsumptionAlertsController.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: This controller handles fuel and energy consumption alert processing in the Public Transit Fleet Management System.
 * It retrieves all consumption data, evaluates each record against expected values, and generates alert messages for any anomalies.
 */
package controller;

import dataaccesslayer.ConsumptionDao;
import dataaccesslayer.ConsumptionDaoImpl;
import transferobjects.ConsumptionDTO;
import businesslayer.alert.AlertService;
import businesslayer.alert.ConsumptionMonitor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Controller for handling consumption alerts logic.
 *
 * @author Quoc Phong Tran
 * @see dataaccesslayer
 * @see transferobjects
 * @see businesslayer
 * @see java.util.HashSet;
 * @see java.util.List;
 * @see java.util.Set;
 * @see javax.servlet.ServletException;
 * @see javax.servlet.http.HttpServletRequest;
 * @see java.io.IOException;
 * @version 1.0
 * @since 21.0.5
 */
public class ConsumptionAlertsController {

    /**
     * Processes consumption data and generates alerts.
     *
     * @param request the HTTP servlet request
     * @return List of alert messages
     * @throws ServletException if servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public List<String> processConsumptionAlerts(HttpServletRequest request)
            throws ServletException, IOException {

        // Initialize the consumption monitor and attach alert service
        ConsumptionMonitor consumptionMonitor = new ConsumptionMonitor();
        AlertService alertService = new AlertService(request);
        consumptionMonitor.attach(alertService);

        // Retrieve consumption records from the DAO
        ConsumptionDao consumptionDao = new ConsumptionDaoImpl();
        List<ConsumptionDTO> records = consumptionDao.getAllConsumptionWithRate();

        // Track vehicles that triggered alerts
        Set<Integer> alerted = new HashSet<>();

        // Evaluate consumption for each record
        for (ConsumptionDTO record : records) {
            int vehicleId = record.getVehicleId();
            if (alerted.contains(vehicleId)) {
                continue;
            }

            double expected = record.getExpectedConsumption();
            double actual = record.getActualConsumption();
            if (actual > expected) {
                consumptionMonitor.evaluate(
                        String.valueOf(vehicleId),
                        record.getDistance(),
                        actual,
                        record.getExpectedRate(),
                        record.getVehicleType()
                );
                alerted.add(vehicleId);
            }
        }

        // Return alert messages from the observer
        @SuppressWarnings("unchecked")
        List<String> alertMessages = (List<String>) request.getAttribute("alertMessages");
        return alertMessages;
    }

    /**
     * Gets the title for the alerts page.
     *
     * @return the title for the alerts page
     */
    public String getAlertsTitle() {
        return "Fuel/Energy Consumption Alerts";
    }
}
