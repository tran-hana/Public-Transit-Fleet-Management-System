
package transferobjects;

import java.time.LocalDateTime;

/**
 * Data Transfer Object representing a maintenance record for a vehicle component.
 * Stores maintenance date, associated component, and maintenance cost in cents.
 * 
 * @author Cheng Qian
 */
public class MaintenanceDTO {

    private int id;
    private int componentId;
    private LocalDateTime date;
    private int costCents;

    /**
     * Constructor for scheduling maintenance (no cost yet).
     *
     * @param id the ID of the maintenance record
     * @param componentId the ID of the component to maintain
     * @param date the scheduled maintenance date
     */
    public MaintenanceDTO(int id, int componentId, LocalDateTime date) {
        this.id = id;
        this.componentId = componentId;
        this.date = date;
        this.costCents = 0;
    }

    /**
     * Constructor for a completed maintenance record.
     *
     * @param id the ID of the maintenance record
     * @param componentId the component maintained
     * @param date the date of maintenance
     * @param costCents the cost of the maintenance in cents
     */
    public MaintenanceDTO(int id, int componentId, LocalDateTime date, int costCents) {
        this.id = id;
        this.componentId = componentId;
        this.date = date;
        this.costCents = costCents;
    }

    /**
     * Default constructor of a maintenance record.
     */
    public MaintenanceDTO() {}

    
    /**
     * Gets the ID of this maintenance record.
     *
     * @return the maintenance ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of this maintenance record.
     *
     * @param id the maintenance ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the component associated with this maintenance.
     *
     * @return the component ID
     */
    public int getComponentId() {
        return componentId;
    }

    /**
     * Sets the component ID for this maintenance.
     *
     * @param componentId the ID of the component
     */
    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    /**
     * Gets the date of the scheduled or completed maintenance.
     *
     * @return the maintenance date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets the date of the scheduled or completed maintenance.
     *
     * @param date the maintenance date
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Gets the cost of the maintenance in cents.
     *
     * @return the cost in cents
     */
    public int getCostCents() {
        return costCents;
    }

    /**
     * Sets the cost of the maintenance in cents.
     *
     * @param costCents the maintenance cost to set
     */
    public void setCostCents(int costCents) {
        this.costCents = costCents;
    }

    /**
     * Checks whether the maintenance has been completed (i.e., cost recorded).
     *
     * @return true if cost > 0, otherwise false
     */
    public boolean isCompleted() {
        return costCents > 0;
    }

    /**
     * Returns a string representation of this maintenance record.
     *
     * @return a formatted string with component ID, date, and cost
     */
    @Override
    public String toString() {
        return "MaintenanceDTO{" +
                "componentId=" + componentId +
                ", date=" + date +
                ", costCents=" + costCents +
                ", cost=$" + String.format("%.2f", costCents / 100.0) +
                '}';
    }
}
