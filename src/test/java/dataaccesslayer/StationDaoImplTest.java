/*
 * File: StationDaoImplTest.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 05, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose: This JUnit test class verifies the functionality of the StationDaoImpl class.
 * It tests whether station records are correctly retrieved from the 'station' table
 * using the getAllStations() method. All error handling is performed using try-catch.
 */

package dataaccesslayer;

import transferobjects.StationDTO;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class StationDaoImplTest {

    /*
     * Test of getAllStations method, of class StationDaoImpl.
     * This test ensures that the method returns a non-null list of station records
     * and that at least one station exists in the database.
     */
    @Test
    public void testGetAllStations() {
        System.out.println("Running testGetAllStations...");

        StationDaoImpl instance = new StationDaoImpl();

        try {
            List<StationDTO> result = instance.getAllStations();

            // The list should not be null
            assertNotNull(result, "List of stations should not be null.");

            // There should be at least one station from the sample data
            assertFalse(result.isEmpty(), "Station list should not be empty.");

        } catch (Exception e) {
            fail("getAllStations() threw an unexpected exception: " + e.getMessage());
        }
    }
}
