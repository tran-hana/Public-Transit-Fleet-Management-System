/*
 * File: OperatorDTO.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 03, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh  
 * 
 * Purpose:
 * This class models a user with "Operator" role. It extends the base UserDTO class 
 * following Simple Factory pattern to create role-specific user objects. 
 */

package transferobjects;

/**
 * This class models a user with "Operator" role. It extends the base UserDTO class 
 * following Simple Factory pattern to create role-specific user objects. 
 * 
 * @see transferobjects.UserDTO
 * @author Ha Nhu Y Tran
 * @version 1.0
 * @since Java 21
 */
public class OperatorDTO extends UserDTO {

    /**
     * Default constructor.
     */
    public OperatorDTO() {}

    /**
     * Constructs an OperatorDTO with all user fields.
     *
     * @param id       the user ID
     * @param name     the full name of the operator
     * @param email    the email address
     * @param password the password
     * @param role     the user role ("Operator")
     */
    public OperatorDTO(int id, String name, String email, String password, String role) {
        super(id, name, email, password, role);
    }
}
