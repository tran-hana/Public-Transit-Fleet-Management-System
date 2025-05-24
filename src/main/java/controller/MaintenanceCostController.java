/*
 * File: MaintenanceCostController.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Controller for handling maintenance cost report operations in the Public Transit Fleet Management System.
 * This controller acts as an intermediary between the servlet and the business logic, retrieving the maintenance 
 * cost report from the business layer and providing it to the servlet for further processing or display.
 */


package controller;

import businesslayer.MaintenanceCostReportAdapter;
import businesslayer.Report;
import transferobjects.MaintenanceCostDTO;
import java.util.List;

/**
 * Controller for handling maintenance cost report operations.
 * This class serves as an intermediary between the servlet and the business logic.
 * 
 * @author Quoc Phong Tran
 * @see businesslayer.MaintenanceCostReportAdapter
 * @see transferobjects.MaintenanceCostDTO
 * @see javax.servlet.http.HttpServletRequest
 * @version 1.0
 * @since 21.0.5
 */
public class MaintenanceCostController {
    
    private Report maintenanceCostReportAdapter;
    
    /**
     * Constructor initializes the business logic adapter.
     */
    public MaintenanceCostController() {
        this.maintenanceCostReportAdapter = new MaintenanceCostReportAdapter();
    }
    
    /**
     * Retrieves maintenance cost data from the business layer.
     * 
     * @return List of MaintenanceCostDTO objects containing the report data
     */
    public List<MaintenanceCostDTO> getMaintenanceCostReport() {
        return maintenanceCostReportAdapter.generateReport();
    }
}