/*
 * File: OperatorPerformance.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Class representing the performance metrics of an operator in the 
 * Public Transit Fleet Management System. It includes the operator's user details,
 * on-time rate, and efficiency rating.
 */
package transferobjects;

/**
 * OperatorPerformance class represents the performance metrics of an operator.
 * It contains information about the operator's user details, their on-time
 * rate, and efficiency. This data can be used to assess and report the
 * operator's overall performance in the transit system.
 *
 * @author Quoc Phong Tran
 * @version 1.0
 * @since 21.0.5
 */
public class OperatorPerformance {

    // User details associated with the operator's performance
    private UserDTO user;

    // The on-time rate of the operator, represented as a percentage
    private double onTimeRate;

    // The efficiency rating of the operator
    private String efficiency;

    /**
     * Constructs an OperatorPerformance object with specified user
     * details, on-time rate, and efficiency.
     *
     * @param user the user details of the operator
     * @param onTimeRate the on-time rate of the operator
     * @param efficiency the efficiency rating of the operator
     */
    public OperatorPerformance(UserDTO user, double onTimeRate, String efficiency) {
        this.user = user;
        this.onTimeRate = onTimeRate;
        this.efficiency = efficiency;
    }

    /**
     * Default constructor for OperatorPerformance. This constructor
     * initializes the object with default values.
     */
    public OperatorPerformance() {
    }

    /**
     * Gets the user details associated with this operator's performance.
     *
     * @return the user details of the operator
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * Sets the user details for this operator's performance.
     *
     * @param user the user details to set
     */
    public void setUser(UserDTO user) {
        this.user = user;
    }

    /**
     * Gets the on-time rate of the operator.
     *
     * @return the on-time rate as a percentage
     */
    public double getOnTimeRate() {
        return onTimeRate;
    }

    /**
     * Sets the on-time rate for this operator's performance.
     *
     * @param onTimeRate the on-time rate to set
     */
    public void setOnTimeRate(double onTimeRate) {
        this.onTimeRate = onTimeRate;
    }

    /**
     * Gets the efficiency rating of the operator.
     *
     * @return the efficiency rating 
     */
    public String getEfficiency() {
        return efficiency;
    }

    /**
     * Sets the efficiency rating for this operator's performance.
     *
     * @param efficiency the efficiency rating to set
     */
    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }

}
