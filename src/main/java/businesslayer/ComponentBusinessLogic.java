/*
 * File: ComponentBusinessLogic.java
 * Author: Cheng Qian
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project
 * Professor: Ramanjeet Singh
 *
 * Purpose:
 * This class encapsulates the business logic for accessing vehicle component data.
 * It delegates data access to the DAO layer and can be used by servlets or service classes
 * to fetch processed component records.
 */

package businesslayer;

import dataaccesslayer.ComponentDao;
import dataaccesslayer.ComponentDaoImpl;
import transferobjects.ComponentDTO;

import java.util.List;

/**
 * Business logic layer for handling component operations.
 * 
 * @author Cheng Qian
 * @see dataaccesslayer.ComponentDao
 * @see dataaccesslayer.ComponentDaoImpl
 * @see transferobjects.ComponentDTO
 * @see java.util.List
 */

public class ComponentBusinessLogic {

    private final ComponentDao componentDao;

    /**
     * Initializes the component business logic with a DAO implementation.
     */
    public ComponentBusinessLogic() {
        this.componentDao = new ComponentDaoImpl();
    }

    /**
     * Retrieves all components from the database.
     *
     * @return list of all ComponentDTO objects
     */
    public List<ComponentDTO> getAllComponents() {
        return componentDao.getAllComponents();
    }

    /**
     * Retrieves all components associated with a specific vehicle.
     *
     * @param vehicleId the vehicle ID
     * @return list of ComponentDTOs for the specified vehicle
     */
    public List<ComponentDTO> getComponentsByVehicle(int vehicleId) {
        return componentDao.getComponentsByVehicle(vehicleId);
    }

    /**
     * Updates the usage hours for a given component.
     *
     * @param componentId the component ID to update
     * @param usageHours the new usage hours
     */
    public void updateUsageHours(int componentId, int usageHours) {
        componentDao.updateUsageHours(componentId, usageHours);
    }
} 