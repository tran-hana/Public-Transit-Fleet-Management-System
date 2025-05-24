/*
 * File: DataSourceTest.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 05, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose: This JUnit test class verifies the functionality of the DataSource class. It tests
 * whether a valid JDBC connection can be established using the getConnection() method and ensures
 * that no exceptions are thrown when the database properties are loaded correctly.
 */

package dataaccesslayer;

import java.sql.Connection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DataSourceTest {

    public DataSourceTest() {
    }

    /*
     * Test of getConnection method, of class DataSource.
     * This test ensures that a non-null JDBC connection is returned
     * and that no exception is thrown during the connection process.
     */
    @Test
    public void testGetConnection() {

        Connection conn = null;

        try {
            conn = DataSource.getConnection();
            assertNotNull(conn, "Connection should not be null.");
            assertFalse(conn.isClosed(), "Connection should be open.");
        } catch (Exception e) {
            fail("getConnection() threw an unexpected exception: " + e.getMessage());
        }
    }
}
