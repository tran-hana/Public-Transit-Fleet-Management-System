/*
 * File: StationDTO.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose:  This class defines a Data Transfer Object (DTO) for the Station entity.
 * This is used to transfer station data between the
 * database access layer and the business or presentation layers.
 */

package transferobjects;

/**
 * This class defines a Data Transfer Object (DTO) for the Station entity.
 * This is used to transfer station data between the
 * database access layer and the business or presentation layers.
 * 
 * @author Ha Nhu Y Tran
 * @see transferobjects
 * @version 1.0
 * @since 21.0.5
 */
public class StationDTO {

    /**
     * Primary key for the station.
     */
    private int id;

    /**
     * Name of the station.
     */
    private String name;

    /**
     * Default constructor.
     */
    public StationDTO() {}

    /**
     * Constructs a StationDTO with the id and name.
     * 
     * @param id   the station's unique identifier
     * @param name the station's name
     */
    public StationDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the station ID.
     * 
     * @return the ID of the station
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the station ID.
     * 
     * @param id the ID to set for the station
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the station.
     * 
     * @return the station's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the station.
     * 
     * @param name the new name of the station
     */
    public void setName(String name) {
        this.name = name;
    }
}
