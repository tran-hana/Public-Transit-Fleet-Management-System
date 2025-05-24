/*
 * File: UsersDaoImpl.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 03, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh * 
 * Purpose:
 * This class implements the UsersDao interface using JDBC to interact with the
 * database. It includes methods for adding new users, retrieving users
 * for login authentication, and fetching all registered users.
 * It uses a singleton database connection provided by the DataSource class.
 */

package dataaccesslayer;

import transferobjects.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the UsersDao interface using JDBC to interact with the
 * database. It includes methods for adding new users, retrieving users
 * for login authentication, and fetching all registered users.
 * It uses a singleton database connection provided by the DataSource class.
 * @see transferobjects.UserDTO
 * @see java.sql.Connection
 * @see java.sql.PreparedStatement
 * @see java.sql.ResultSet
 * @see java.sql.SQLException
 * @see java.sql.Statement
 * @author Ha Nhu Y Tran
 * @version 1.0
 * @since 21.0.
 */
public class UsersDaoImpl implements UsersDao {

    /**
     * Inserts a new user record into the database.
     *
     * @param user the UserDTO object containing the new user's data
     */
    @Override
    public void addUser(UserDTO user) {
        String query = "INSERT INTO user (name, email, password, role) VALUES (?, ?, ?, ?)";

        try (
            Connection con = DataSource.getConnection();
            PreparedStatement prepStatement = con.prepareStatement(query)
        ) {
            prepStatement.setString(1, user.getName());
            prepStatement.setString(2, user.getEmail());
            prepStatement.setString(3, user.getPassword());
            prepStatement.setString(4, user.getRole());
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a user from the database using their email and password.
     * This method is primarily used for login authentication.
     *
     * @param email the email address entered by the user
     * @param password the password entered by the user
     * @return a UserDTO object if credentials match a record; otherwise null
     */
    @Override
    public UserDTO getUserByEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (
            Connection con = DataSource.getConnection();
            PreparedStatement prepStatement = con.prepareStatement(query)
        ) {
            prepStatement.setString(1, email);
            prepStatement.setString(2, password);

            try (ResultSet rs = prepStatement.executeQuery()) {
                if (rs.next()) {
                    return new UserDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }    

    @Override
    public List<UserDTO> getUserByRole(String role) {
        List<UserDTO> users = new ArrayList<>();
    
    String query = "SELECT * FROM user WHERE role = ?";
    
    try (
        Connection con = DataSource.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query)
    ) {
        // Set the parameter for the vehicle type
        pstmt.setString(1, role);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                
                users.add(user);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return users;
    }
}
