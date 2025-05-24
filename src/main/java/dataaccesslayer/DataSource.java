/* 
 * File: DataSource.java 
 * Author: Ha Nhu Y Tran,041165059 & Cheng Qian, 041167176, Group 5
 * Date: April 03, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor:  Ramanjeet Singh 
 * Purpose: This class handles the database connection using the Singleton design pattern.
 * It guarantees a single shared connection instance across the application and ensures thread safety through a synchronized method.
 */

package dataaccesslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class handles the database connection using the Singleton design pattern.
 * It guarantees a single shared connection instance across the application and 
 * ensures thread safety through a synchronized method. 
 * 
 * @author Ha Nhu Y Tran
 * @author Cheng Qian
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 * @see java.sql.SQLException
 * @see java.io.IOException;
 * @see java.io.InputStream
 * @see java.util.Properties
 * @version 1.0
 * @since 21.0.5
 * 
 */

public class DataSource {

    /**
     * Singleton instance of the database Connection.
     */
    private static Connection connection;

    /**
     * Private constructor to prevent instantiation.
     */
    private DataSource() { }

    /**
     * Returns a singleton database connection. If the connection is not initialized or is closed,
     * a new one is created. Otherwise, the existing connection is returned.
     *
     * @return a singleton Connection to the database
     * @throws SQLException if a database access error occurs
     */
    public static synchronized Connection getConnection() throws SQLException {
        String[] connectionInfo = openPropsFile();
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure the MySQL JDBC driver is loaded
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(
                    connectionInfo[0], 
                    connectionInfo[1], 
                    connectionInfo[2]
            );
        }
        return connection;
    }
    


    /**
     * Reads database connection properties from the database.properties file.
     * The method loads the JDBC URL, username, and password from the properties file 
     * and returns them as a String array. 
     *
     * @return a String array containing the JDBC URL, username, and password.
     */
    private static String[] openPropsFile() {
        Properties props = new Properties();
        String[] info = new String[3]; 
        try (InputStream in = DataSource.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (in == null) {
                throw new IOException("database.properties not found in classpath");
            }
            props.load(in);
            info[0] = props.getProperty("jdbc.url");
            info[1] = props.getProperty("jdbc.username");
            info[2] = props.getProperty("jdbc.password");
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        return info;
    }
}
