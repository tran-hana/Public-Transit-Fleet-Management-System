package dataaccesslayer;

import transferobjects.MaintenanceDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DAO interface for accessing maintenance records.
 * Provides methods to schedule and complete maintenance activities.
 *
 * @author Cheng Qian
 */
public interface MaintenanceDao {

    /**
     * Schedules a maintenance without a cost.
     *
     * @param componentId the ID of the component
     * @param date        the scheduled maintenance date
     */
    void scheduleMaintenance(int componentId, LocalDateTime date);

    /**
     * Completes a maintenance record by setting cost and resetting usage.
     *
     * @param maintenanceId the ID of the maintenance record
     * @param costCents     the maintenance cost in cents
     */
    void completeMaintenance(int maintenanceId, int costCents);

    /**
     * Retrieves all maintenance records.
     *
     * @return list of MaintenanceDTO objects
     */
    List<MaintenanceDTO> getAllMaintenances();

    /**
     * Gets the most recent maintenance record for a component.
     *
     * @param componentId the component ID
     * @return the latest MaintenanceDTO or null if not found
     */
    MaintenanceDTO getLatestMaintenanceForComponent(int componentId);
} 
