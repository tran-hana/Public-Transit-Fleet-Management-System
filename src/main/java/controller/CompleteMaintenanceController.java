/*
 * File: CompleteMaintenanceController.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: This controller handles the logic for marking a maintenance task as complete
 * in the Public Transit Fleet Management System. It calculates the cost in cents
 * and delegates the update to the data access layer.
*/

package controller;

import dataaccesslayer.MaintenanceDao;
import dataaccesslayer.MaintenanceDaoImpl;

/**
 * Controller that handles maintenance completion logic.
 * @author Quoc Phong Tran
 * @see dataaccesslayer
 * @version 1.0
 * @since 21.0.5
 */
 
public class CompleteMaintenanceController {
    
    private final MaintenanceDao dao;
    
    /**
     * Constructor that initializes the data access object.
     */
    public CompleteMaintenanceController() {
        this.dao = new MaintenanceDaoImpl();
    }
    
    /**
     * Constructor that accepts a data access object for dependency injection.
     * 
     * @param dao The maintenance data access object
     */
    public CompleteMaintenanceController(MaintenanceDao dao) {
        this.dao = dao;
    }
    
    /**
     * Completes a maintenance task with the given ID and cost.
     * 
     * @param maintenanceId The ID of the maintenance to complete
     * @param costDollars The cost in dollars (will be converted to cents)
     */
    public void completeMaintenance(int maintenanceId, double costDollars) {
        int costCents = (int) Math.round(costDollars * 100);
        dao.completeMaintenance(maintenanceId, costCents);
    }
}