/*
 * File: BreakInvoker.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: This class acts as the invoker in the Command design pattern for break operations.
 * It holds a reference to a BreakCommand and invokes its execution. 
 */

package businesslayer;

/**
 * The BreakInvoker class is part of the Command design pattern.
 * It holds and executes a BreakCommand, allowing for the abstraction of break logic
 * (such as starting or ending a break) from the invoker.
 * 
 * @author Ha Nhu Y Tran
 * @version 1.0
 * @since 21.0.5
 */
public class BreakInvoker {

    /**
     * The command to be executed (start or end break).
     */
    private BreakCommand command;

    /**
     * Sets the command to be executed.
     *
     * @param command the BreakCommand instance (e.g., StartBreakCommand, EndBreakCommand)
     */
    public void setCommand(BreakCommand command) {
        this.command = command;
    }

    /**
     * Executes the currently set command.
     * @return true if the command was executed successfully, false otherwise.
     */
    public boolean executeCommand() {
        return command.execute();
    }
}
