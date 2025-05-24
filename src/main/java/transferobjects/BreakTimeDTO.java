/*
 * File: BreakTimeDTO.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose: This class represents the break time information associated with a vehicle assignment.
 * It stores the ID of the break, the assignment it belongs to, the start and end timestamps 
 * of the break. This DTO is used for transferring break time data between layers.
 */

package transferobjects;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for representing a vehicle break time.
 * This object holds information about when a vehicle takes a break
 * during its assignment, including start and end times.
 * 
 * @author Ha Nhu Y Tran
 * @version 1.0
 * @since 21.0.5
 */
public class BreakTimeDTO {

    /**
     * Unique identifier for the break time record.
     */
    private int id;

    /**
     * The ID of the vehicle assignment associated with this break.
     */
    private int assignID;

    /**
     * The timestamp indicating when the break started.
     */
    private LocalDateTime startTime;

    /**
     * The timestamp indicating when the break ended (can be null if ongoing).
     */
    private LocalDateTime endTime;


    /**
     * Default constructor.
     */
    public BreakTimeDTO() {
    }

    /**
     * Constructs a BreakTimeDTO with all fields initialized.
     *
     * @param id        the unique ID of the break
     * @param assignID  the assignment ID to which the break belongs
     * @param startTime the starting time of the break
     * @param endTime   the ending time of the break (may be null if ongoing)
     */
    public BreakTimeDTO(int id, int assignID, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.assignID = assignID;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Gets the break's unique ID.
     *
     * @return the break ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the break's unique ID.
     *
     * @param id the break ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the assignment ID associated with this break.
     *
     * @return the assignment ID
     */
    public int getAssignID() {
        return assignID;
    }

    /**
     * Sets the assignment ID associated with this break.
     *
     * @param assignID the assignment ID
     */
    public void setAssignID(int assignID) {
        this.assignID = assignID;
    }

    /**
     * Gets the start time of the break.
     *
     * @return the start time
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the break.
     *
     * @param startTime the start time
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the end time of the break.
     *
     * @return the end time (can be null if break is ongoing)
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time of the break.
     *
     * @param endTime the end time (can be null if break is ongoing)
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
