/*
 * File: UserBusinessLogic.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 03, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose:
 * This class handles the business logic for user operations. It includes methods
 * to clean and validate user input, as well as to add new users and retrieve
 * users from the database for login authentication. 
 */
package businesslayer;

import dataaccesslayer.UsersDao;
import dataaccesslayer.UsersDaoImpl;
import transferobjects.OperatorDTO;
import transferobjects.TransitManagerDTO;
import transferobjects.UserDTO;

/**
 * This class handles the business logic for user operations. It includes
 * methods to clean and validate user input, as well as to add new users and
 * retrieve users from the database for login authentication.
 *
 * @see businesslayer
 * @see dataaccesslayer.UsersDao
 * @see dataaccesslayer.UsersDaoImpl
 * @see jtransferobjects.UserDTO
 * @author Ha Nhu Y Tran
 * @version 1.0
 * @since 21.0.5
 */

public class UsersBusinessLogic {

    /**
     * Maximum number of characters allowed for the user's name.
     */
    private static final int NAME_MAX_LENGTH = 50;

    /**
     * Maximum number of characters allowed for the user's email.
     */
    private static final int EMAIL_MAX_LENGTH = 50;

    /**
     * Maximum number of characters allowed for the user's password.
     */
    private static final int PASSWORD_MAX_LENGTH = 50;

    /**
     * Maximum number of characters allowed for the user's role.
     */
    private static final int ROLE_MAX_LENGTH = 50;

    /**
     * The DAO (Data Access Object) interacting with the database.
     */
    private UsersDao usersDao;

    /**
     * Default constructor to initialize the instance.
     */
    public UsersBusinessLogic() {
        this.usersDao = new UsersDaoImpl();
    }

    /**
     * Adds a new user to the database after cleaning and validation.
     *
     * @param user the user to add
     * @throws ValidationException if validation fails
     */
    public void addUser(UserDTO user) throws ValidationException {
        cleanData(user);
        validateUser(user);
        usersDao.addUser(user);
    }

    /**
     * Retrieves a user from the database by email and password for login.
     *
     * @param email the user’s email
     * @param password the user’s password
     * @return the matching UserDTO object, or null if not found
     */
    public UserDTO getUserByEmailAndPassword(String email, String password) {
        return usersDao.getUserByEmailAndPassword(email.trim().toLowerCase(), password.trim());
    }
    
    /**
     * Creates a specific UserDTO object based on the user role.
     *
     * @param role the user role ("Operator" or "Transit Manager")
     * @param id the user ID
     * @param name the user's name
     * @param email the user's email
     * @param password the user's password
     * @return a subclass of UserDTO corresponding to the role
     */
    public static UserDTO createUserDTO(String role, int id, String name, String email, String password) {
        if ("Transit Manager".equalsIgnoreCase(role)) {
            return new TransitManagerDTO(id, name, email, password, role);
        } else if ("Operator".equalsIgnoreCase(role)) {
            return new OperatorDTO(id, name, email, password, role);
        } else {
            throw new IllegalArgumentException("User role is not valid: " + role);
        }
    }
    
    
    /**
     * Cleans user input by trimming whitespace and normalizing fields.
     *
     * @param user the user object to clean
     */
    public void cleanData(UserDTO user) {
        if (user.getName() != null) {
            user.setName(user.getName().trim());
        }

        if (user.getEmail() != null) {
            user.setEmail(user.getEmail().trim());
        }

        if (user.getPassword() != null) {
            user.setPassword(user.getPassword().trim());
        }

        if (user.getRole() != null) {
            user.setRole(user.getRole().trim());
        }
    }

    /**
     * Validates user fields individually using field length and null
     * constraints.
     *
     * @param user the UserDTO to validate
     * @throws ValidationException if any field fails validation
     */
    private void validateUser(UserDTO user) throws ValidationException {
        validateString(user.getName(), "name", NAME_MAX_LENGTH, false);
        validateString(user.getEmail(), "email", EMAIL_MAX_LENGTH, false);
        validateString(user.getPassword(), "password", PASSWORD_MAX_LENGTH, false);
        validateString(user.getRole(), "role", ROLE_MAX_LENGTH, false);

        if (!user.getEmail().contains("@")) {
            throw new ValidationException("Email format is invalid, please ensure to include '@'.");
        }

        if (user.getPassword().length() < 8) {
            throw new ValidationException("Password must be at least 8 characters.");
        }

        String role = user.getRole().toLowerCase();
        if (!role.equals("operator") && !role.equals("transit manager")) {
            throw new ValidationException("Role must be either 'Operator' or 'Transit Manager'.");
        }
    }

    /**
     * String validation method for author fields.
     *
     * @param value the value to check
     * @param fieldName the name of the field (for error messages)
     * @param maxLength max length allowed
     * @param isNullAllowed whether the field can be null
     * @throws ValidationException if validation fails
     */
    private void validateString(String value, String fieldName, int maxLength, boolean isNullAllowed)
            throws ValidationException {
        if (value == null && isNullAllowed) {
        } else if (value == null && !isNullAllowed) {
            throw new ValidationException(String.format("%s cannot be null",
                    fieldName));
        } else if (value.length() == 0) {
            throw new ValidationException(String.format("%s cannot be empty or only whitespace",
                    fieldName));
        } else if (value.length() > maxLength) {
            throw new ValidationException(String.format("%s cannot exceed %d characters",
                    fieldName, maxLength));
        }
    }
}
