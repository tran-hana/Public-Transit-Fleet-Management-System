/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transferobjects;

/**
 * Data Transfer Object representing a vehicle component.
 * Stores the component type, associated vehicle, and current usage hours.
 * 
 * @author Cheng Qian
 */
public class ComponentDTO {

    /**
     * The unique ID of the component.
     */
    private int id;

    /**
     * The ID of the vehicle this component belongs to.
     */
    private int vehicleId;

    /**
     * The type of the component (e.g., "brake", "engine", "wheels", "catenary").
     */
    private String type;

    /**
     * The current accumulated usage hours of the component.
     */
    private int usageHours;

    /**
     * Default constructor.
     */
    public ComponentDTO() {}

    /**
     * Full constructor.
     *
     * @param id          the ID of the component
     * @param vehicleId   the ID of the associated vehicle
     * @param type        the type of the component
     * @param usageHours  the current usage hours
     */
    public ComponentDTO(int id, int vehicleId, String type, int usageHours) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.type = type;
        this.usageHours = usageHours;
    }

    /**
     * Gets the ID of the component.
     *
     * @return the component ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the component.
     *
     * @param id the component ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the vehicle the component belongs to.
     *
     * @return the vehicle ID
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * Sets the ID of the vehicle the component belongs to.
     *
     * @param vehicleId the vehicle ID to set
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Gets the type of the component (e.g., "brake").
     *
     * @return the component type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the component.
     *
     * @param type the component type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the current usage hours of the component.
     *
     * @return the usage hours
     */
    public int getUsageHours() {
        return usageHours;
    }

    /**
     * Sets the current usage hours of the component.
     *
     * @param usageHours the usage hours to set
     */
    public void setUsageHours(int usageHours) {
        this.usageHours = usageHours;
    }

    /**
     * Returns a string representation of the component.
     *
     * @return a summary of the component details
     */
    @Override
    public String toString() {
        return "ComponentDTO{" +
                "id=" + id +
                ", vehicleId=" + vehicleId +
                ", type='" + type + '\'' +
                ", usageHours=" + usageHours +
                '}';
    }
}
