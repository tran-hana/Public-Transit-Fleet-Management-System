/* File name: ValidationException.java
 * Author: Ha Nhu Y Tran, 041165059
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Assignment 1
 * Date: Feb 10, 2025
 * Professor: Ramanjeet Singh
 * Purpose:  This class defines a custom exception for handling validation errors 
 * in the Recipients project. It extends the Exception class and provides 
 * multiple constructors to specify error messages and causes.
 */package businesslayer;

/**
 * Custom exception class for validation errors in the Recipients project. This
 * exception is thrown when recipient data does not meet validation criteria,
 * such as missing required fields or exceeding length constraints.
 *
 * @see Exception
 * @author Ha Nhu Y Tran
 * @version 1.0
 * @since 21.0.5
 */
public class ValidationException extends Exception {

    /**
     * Default constructor with a generic validation error message.
     */
    public ValidationException() {
        super("Data not in a valid format");
    }

    /**
     * Constructor that allows specifying a custom validation error message.
     *
     * @param message The specific validation error message.
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Constructor that allows specifying a custom validation error message and
     * a cause for the exception.
     *
     * @param message The specific validation error message.
     * @param throwable The original exception that caused this validation
     * error..
     */
    public ValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Constructor that allows specifying the cause of the validation exception.
     *
     * @param throwable The original exception that caused this validation
     * error..
     */
    public ValidationException(Throwable throwable) {
        super(throwable);
    }
}
