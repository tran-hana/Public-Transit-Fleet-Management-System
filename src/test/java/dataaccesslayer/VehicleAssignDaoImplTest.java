/*
 * File: VehicleAssignDaoImplTest.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 06, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose: This JUnit test verifies that the getVehicleAssignByOperatorID method in
 * VehicleAssignDaoImpl returns the correct assignment when a valid operator ID is provided,
 * and returns null when no assignment is found.
 */

package dataaccesslayer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import transferobjects.VehicleAssignDTO;


public class VehicleAssignDaoImplTest {

    /**
     * Test retrieving a vehicle assignment for a valid operator ID.
     */
    @Test
    public void testGetVehicleAssignByValidOperatorID() {

        int operatorID = 2; 
        VehicleAssignDaoImpl instance = new VehicleAssignDaoImpl();

        try {
            VehicleAssignDTO result = instance.getVehicleAssignByOperatorID(operatorID);
            assertNotNull(result, "Expected non-null assignment for operator ID 2");
            assertEquals(operatorID, result.getOperatorID(), "Operator ID should match input");
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * Test retrieving a vehicle assignment with a non-existing operator ID.
     */
    @Test
    public void testGetVehicleAssignByInvalidOperatorID() {
        int operatorID = 9999; // this is not in database
        VehicleAssignDaoImpl instance = new VehicleAssignDaoImpl();

        try {
            VehicleAssignDTO result = instance.getVehicleAssignByOperatorID(operatorID);
            assertNull(result, "Expected null for non-existent operator ID");
        } catch (Exception e) {
            fail("Exception occurred while testing invalid operator ID: " + e.getMessage());
        }
    }
}
