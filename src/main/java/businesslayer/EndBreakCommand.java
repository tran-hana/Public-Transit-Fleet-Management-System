/*
 * File: EndBreakCommand.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose: This class is a concrete implementation of the Command design pattern used to end a break.
 * It encapsulates a request to end a break for a specific vehicle assignment and delegates 
 * the actual work to the BreakService (the receiver).
 */

package businesslayer;

/**
 * Concrete command class that ends a break using the  BreakService.
 * Implements the BreakCommand interface as part of the Command design pattern.
 * When the execute() method is called, it delegates the task to the receiver
 * with the required assignment ID.
 * 
 * @author Ha Nhu Y Tran
 * @version 1.0
 * @since 21.0.5
 */
public class EndBreakCommand implements BreakCommand {

    /**
     * The service object that performs the actual end-break logic.
     */
    private final BreakService receiver;

    /**
     * The assignment ID for which the break should end.
     */
    private final int assignId;

    /**
     * Constructs an EndBreakCommand with a receiver and assignment ID.
     *
     * @param receiver the BreakService that performs the operation
     * @param assignId the ID of the assignment whose break should be ended
     */
    public EndBreakCommand(BreakService receiver, int assignId) {
        this.receiver = receiver;
        this.assignId = assignId;
    }

    /**
     * Executes the end break operation by invoking the receiver’s endBreak() method.
     * @retun true if the end break operation is executed successfully
     */
    @Override
    public boolean execute() {
        receiver.endBreak(assignId);
        return true;
    }
}
