/*
 * File: gpsTrackingBusinessLogicTest.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 06, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose: This test verifies that gpsTrackingBusinessLogic correctly retrieves
 * and returns a shuffled list of stations using the simulateGpsTracking method.
 */

package businesslayer;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import transferobjects.StationDTO;

public class gpsTrackingBusinessLogicTest {

    /*
     * Test that simulateGpsTracking() returns a non-null, non-empty shuffled list of stations.
     */
    @Test
    public void testSimulateGpsTracking() {
        int vehicleId = 1003;  
        gpsTrackingBusinessLogic logic = new gpsTrackingBusinessLogic();

        try {
            List<StationDTO> result = logic.simulateGpsTracking(vehicleId);
            assertNotNull(result, "Station list should not be null");
            assertFalse(result.isEmpty(), "Station list should not be empty");
            System.out.println("Retrieved " + result.size() + " station(s) for vehicle ID " + vehicleId);
        } catch (Exception e) {
            fail("simulateGpsTracking threw an unexpected exception: " + e.getMessage());
        }
    }
}
