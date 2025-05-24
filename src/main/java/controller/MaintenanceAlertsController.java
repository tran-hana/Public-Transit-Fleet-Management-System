/*
 * File: MaintenanceAlertsController.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Controller for handling maintenance alert business logic. This controller processes the evaluation of 
 * vehicle components against predefined thresholds, generates maintenance alerts, and interacts with the 
 * data access layer to retrieve component and maintenance data.
*/

package controller;

import businesslayer.alert.AlertService;
import businesslayer.alert.MaintenanceMonitor;
import dataaccesslayer.ComponentDao;
import dataaccesslayer.ComponentDaoImpl;
import dataaccesslayer.MaintenanceDao;
import dataaccesslayer.MaintenanceDaoImpl;
import dataaccesslayer.VehicleDao;
import dataaccesslayer.VehicleDaoImpl;
import transferobjects.ComponentDTO;
import transferobjects.MaintenanceDTO;
import transferobjects.VehicleDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * Controller class for handling maintenance alert business logic.
 * This separates the controller logic from the servlet presentation layer.
 * 
 * @author Quoc Phong Tran
 * @see businesslayer;
 * @see dataaccesslayer;
 * @see transferobjects;
 * @see java.util.HashMap;
 * @see java.util.Map;
 * @see java.util.List;
 * @see javax.servlet.http.HttpServletRequest;
 * @version 1.0
 * @since 21.0.5
 */
public class MaintenanceAlertsController {
    
    private final ComponentDao componentDao;
    private final VehicleDao vehicleDao;
    private final MaintenanceDao maintenanceDao;
    private final Map<String, Integer> thresholds;
    
    /**
     * Constructor initializes data access objects and component thresholds.
     */
    public MaintenanceAlertsController() {
        this.componentDao = new ComponentDaoImpl();
        this.vehicleDao = new VehicleDaoImpl();
        this.maintenanceDao = new MaintenanceDaoImpl();
        
        // Initialize thresholds map
        this.thresholds = new HashMap<>();
        thresholds.put("brake", 250);
        thresholds.put("wheels", 400);
        thresholds.put("engine", 500);
        thresholds.put("catenary", 300);
    }
    
    /**
     * Process maintenance alerts by evaluating all components against their thresholds.
     * 
     * @param request The HttpServletRequest to store alert messages
     * @return List of alert messages generated
     */
    public List<String> processMaintenanceAlerts(HttpServletRequest request) {
        // Create and setup the maintenance monitor and alert service
        MaintenanceMonitor maintenanceMonitor = new MaintenanceMonitor();
        AlertService alertService = new AlertService(request);
        maintenanceMonitor.attach(alertService);
        
        // Get all components to evaluate
        List<ComponentDTO> components = componentDao.getAllComponents();
        
        // Evaluate each component against its threshold
        for (ComponentDTO component : components) {
            VehicleDTO vehicle = vehicleDao.getVehicleById(component.getVehicleId());
            MaintenanceDTO latest = maintenanceDao.getLatestMaintenanceForComponent(component.getId());
            int threshold = thresholds.getOrDefault(component.getType().toLowerCase(), Integer.MAX_VALUE);
            
            maintenanceMonitor.evaluate(component, vehicle, latest, threshold);
        }
        
        // Return the alert messages stored in the request attribute
        @SuppressWarnings("unchecked")
        List<String> alertMessages = (List<String>) request.getAttribute("alertMessages");
        return alertMessages;
    }
    
    /**
     * Get all components from the database.
     * 
     * @return List of all components
     */
    public List<ComponentDTO> getAllComponents() {
        return componentDao.getAllComponents();
    }
    
    /**
     * Get component maintenance thresholds.
     * 
     * @return Map of component types to their maintenance thresholds
     */
    public Map<String, Integer> getThresholds() {
        return new HashMap<>(thresholds);
    }
}