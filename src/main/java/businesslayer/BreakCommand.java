/*
 * File: BreakCommand.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: This interface defines the Command design pattern's command contract for break operations.
 * It provides a single method `execute()` that concrete command classes will implement
 * to perform break-related actions (such as starting or ending a break).
 */

package businesslayer;

/**
 * This interface defines the Command design pattern's command for break operations.
 * It provides a single method execute() that concrete command classes will implement
 * to perform break-related actions (such as starting or ending a break).
 * @author Ha Nhu Y Tran
 * @see businesslayer
 * @version 1.0
 * @since 21.0.5
 */
public interface BreakCommand {

    /**
     * Executes the break-related operation.
     */
    boolean execute();
}
