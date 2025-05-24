/*
 * File: StartBreakCommand.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose: This class implements the Command design pattern to log a break period
 * for a vehicle assignment. It encapsulates the request to start a break 
 * and delegates execution to the BreakService (receiver).
 */

package businesslayer;

/**
 * The class implements the  interface and represents a command to start a break for a specific assignment.
 * This command holds a reference to a receiver and an assignment ID, and when executed, it calls the receiver's
 * method.
 * @author Ha Nhu Y Tran
 * @version 1.0
 * @since Java 21.0.5
 * @see businesslayer
 * @see BreakCommand
 * @see BreakService
 */
public class StartBreakCommand implements BreakCommand {

    /**
     * The service responsible for executing the break start logic.
     */
    private final BreakService receiver;

    /**
     * The ID of the vehicle assignment for which the break is to be started.
     */
    private final int assignId;

    /**
     * Constructs a new StartBreakCommand with the given receiver and assignment ID.
     *
     * @param receiver the BreakService responsible for handling the break
     * @param assignId the assignment ID of the vehicle
     */
    public StartBreakCommand(BreakService receiver, int assignId) {
        this.receiver = receiver;
        this.assignId = assignId;
    }

    /**
     * Executes the command to start a break for the given assignment ID.
     * Delegates the action to the  BreakService.
     */
    @Override
    public boolean execute() {
        return receiver.startBreak(assignId);        
    }
}
