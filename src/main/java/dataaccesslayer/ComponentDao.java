package dataaccesslayer;

import java.util.List;
import transferobjects.ComponentDTO;

/**
 * DAO interface for accessing component records from the database.
 * Provides methods to retrieve and update component usage information.
 * 
 * @author Cheng Qian
 */
public interface ComponentDao {

    /**
     * Retrieves all component records.
     * 
     * @return list of ComponentDTO objects
     */
    List<ComponentDTO> getAllComponents();

    /**
     * Updates the usage hours of a specific component.
     * 
     * @param componentId the ID of the component to update
     * @param usageHours the new usage hours value
     */
    void updateUsageHours(int componentId, int usageHours);
    
    /**
     * Retrieves all components associated with a specific vehicle.
     *
     * @param vehicleId the ID of the vehicle
     * @return list of ComponentDTO objects for the specified vehicle
     */
    List<ComponentDTO> getComponentsByVehicle(int vehicleId);
}
