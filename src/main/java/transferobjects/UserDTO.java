/* 
 * File: UserDTO.java 
 * Author: Ha Nhu Y Tran,041165059,Group 5
 * Date: April 03, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor:  Ramanjeet Singh 
 * Purpose: This interface is responsible for accessing and managing user data
 * in the data layer. This allows to add new user to database and 
 * retrieve user's name and password for login authentication.
 */
package transferobjects;


/**
 * This interface is responsible for accessing and managing user data
 * in the data layer. This allows to add new user to database and 
 * retrieve user's name and password for login authentication.
 * @author Ha Nhu Y Tran
 * @version 1.0
 * @since 21.0.5
 * @see transferobjects
 */
public class UserDTO {

    /**
     * Unique identifier for the user in the system.
     */
    private int id;

    /**
     * Full name of the user.
     */
    private String name;

    /**
     * Email address of the user, used for login and communication.
     */
    private String email;

    /**
     * Encrypted password used for user authentication.
     */
    private String password;

    /**
     * Role of the user in the system (e.g., "Manager" or "Operator").
     */
    private String role;
    
    /**
     * Default constructor.
     */
    public UserDTO() {
    }

    /**
     * Parameterized constructor to initialize an instance object.
     *
     * @param id       the unique ID of the user
     * @param name     the user's name
     * @param email    the user's email
     * @param password the user's password
     * @param role     the user's role (Manager or Operator)
     */
    public UserDTO(int id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserDTO(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    /**
     * Gets the user's ID.
     *
     * @return the user ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the user's ID.
     *
     * @param id the user ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the user's name.
     *
     * @return the user name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     *
     * @param name the user name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user's email.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     *
     * @param email the email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's role.
     *
     * @return the user role (e.g., Manager, Operator)
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     *
     * @param role the user role (e.g., Manager, Operator)
     */
    public void setRole(String role) {
        this.role = role;
    }
}