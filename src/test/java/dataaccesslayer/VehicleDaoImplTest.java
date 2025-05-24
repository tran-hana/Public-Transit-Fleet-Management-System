/*
 * File: VehicleDaoImplTest.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 06, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose: This JUnit test class verifies CRUD operations of the VehicleDaoImpl class.
 * It tests adding, updating, retrieving, and deleting vehicles from the 'vehicle' table.
 * All exceptions are handled using try-catch blocks and validated using real database actions.
 */

package dataaccesslayer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import transferobjects.VehicleDTO;
import transferobjects.DieselBusDTO;

import java.util.List;

public class VehicleDaoImplTest {

    private final VehicleDaoImpl dao = new VehicleDaoImpl();

    // Helper method to create a test vehicle object
    private VehicleDTO createTestVehicle() {
        return new DieselBusDTO(9999, "Diesel Bus", "Diesel", 40, 123, 0.08);
    }

    /**
     * Test addVehicle() with a valid vehicle.
     */
    @Test
    public void testAddVehicle() {

        VehicleDTO vehicle = createTestVehicle();
        try {
            dao.addVehicle(vehicle);
        } catch (Exception e) {
            fail("addVehicle() threw an unexpected exception: " + e.getMessage());
        }
    }

    /**
     * Test updateVehicle() by modifying a known vehicle.
     */
    @Test
    public void testUpdateVehicle() {

        VehicleDTO vehicle = createTestVehicle();
        vehicle.setMaxPassengers(50);
        vehicle.setConsumptionRate(0.09);

        try {
            dao.updateVehicle(vehicle);
        } catch (Exception e) {
            fail("updateVehicle() threw an unexpected exception: " + e.getMessage());
        }
    }

    /**
     * Test getVehicleById() using a known ID.
     */
    @Test
    public void testGetVehicleById() {

        try {
            VehicleDTO result = dao.getVehicleById(1002); 
            assertNotNull(result, "Expected vehicle with ID 1002 to be found.");
            assertEquals(1002, result.getId());
        } catch (Exception e) {
            fail("getVehicleById() threw an unexpected exception: " + e.getMessage());
        }
    }

    /**
     * Test getAllVehicles() returns a list with vehicles.
     */
    @Test
    public void testGetAllVehicles() {
 
        try {
            List<VehicleDTO> vehicles = dao.getAllVehicles();
            assertNotNull(vehicles, "Vehicle list should not be null.");
            assertFalse(vehicles.isEmpty(), "Vehicle list should not be empty.");
        } catch (Exception e) {
            fail("getAllVehicles() threw an unexpected exception: " + e.getMessage());
        }
    }

    /**
     * Test deleteVehicle() on the test vehicle ID.
     */
    @Test
    public void testDeleteVehicle() {

        try {
            dao.deleteVehicle(9999);
        } catch (Exception e) {
            fail("deleteVehicle() threw an unexpected exception: " + e.getMessage());
        }
    }    
}
