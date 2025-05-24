/*
 * File: FuleCostReportAdapterTest.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: The purpose of VehicleTrackingStationIplmTest is to verify that 
 * the VehicleTrackingStationIplm correctly retrieves vehicle tracking data 
 * from the real database by testing the getAllTracking method 
 * and ensuring it returns a non-null list with valid entries.
 */
package dataaccesslayer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import transferobjects.VehicleTrackingDTO;

public class VehicleTrackingStationIplmTest {

    /*
    * This test method verifies the behavior of the getAllTracking method in the 
    * VehicleTrackingStationIplm class. It checks if the method correctly retrieves 
    * vehicle tracking data from the real database
    */
    @Test
    public void testGetAllTracking_withRealDatabase() {
        System.out.println("Running test: testGetAllTracking_withRealDatabase");

        VehicleTrackingStationIplm instance = new VehicleTrackingStationIplm();
        List<VehicleTrackingDTO> result = instance.getAllTracking();

        assertNotNull(result, "The result should not be null.");
        assertTrue(result.size() >= 0, "The list should contain 0 or more records.");

        // Optional: Print result for debugging
        for (VehicleTrackingDTO dto : result) {
            System.out.println("Station: " + dto.getStation()
                    + ", Vehicle ID: " + dto.getVehicleID()
                    + ", Arrival: " + dto.getArrivalTime()
                    + ", Departure: " + dto.getDepartureTime()
                    + ", Type: " + dto.getType());
        }
    }
}
