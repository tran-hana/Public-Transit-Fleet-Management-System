/* 
 * File: UserDTO.java 
 * Author: Ha Nhu Y Tran,041165059,Group 5
 * Date: April 03, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor:  Ramanjeet Singh 
 * Purpose: This class represents Data Transfer Object (DTO) for the User entity.
 * Represents a system user, either a Transit Manager or an Operator. 
 * This class is used to transfer user data between application layers.
 */
package dataaccesslayer;

import java.util.List;
import transferobjects.UserDTO;

/**
 * DAO interface for accessing User data. Defines methods for CRUD operations on
 * users.
 * @see dataaccesslayer
 * @see transferobjects.UserDTO
 * @author Ha Nhu Y Tran
 * @version 1.0
 * @since 21.0.5
 */
public interface UsersDao {

    /**
     * Adds a new user into the database.
     *
     * @param user the user to insert
     */
    void addUser(UserDTO user);

    /**
     * Retrieves a user by their email and password.
     *
     * @param email the user's email
     * @param password the user's password
     * @return the UserDTO if found, if not returns null
     */
    UserDTO getUserByEmailAndPassword(String email, String password);
    
    List<UserDTO> getUserByRole(String role);
}
