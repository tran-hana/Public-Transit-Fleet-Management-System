/*
 * File: VehiclesBusinessLogicTest.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 05, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose: This JUnit test class verifies the functionality of the VehiclesBusinessLogic class.
 * It tests vehicle creation, validation, data cleanup, and database-related operations using
 * the business logic layer. All exception handling is done using try-catch blocks.
 */

package businesslayer;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import transferobjects.VehicleDTO;
import transferobjects.DieselBusDTO;

public class VehiclesBusinessLogicTest {

    private final VehiclesBusinessLogic logic = new VehiclesBusinessLogic();

    @Test
    public void testAddVehicle() {
        System.out.println("Running testAddVehicle...");
        VehicleDTO vehicle = new DieselBusDTO(9999, "Diesel Bus", "Diesel", 40, 123, 0.08);

        try {
            logic.addVehicle(vehicle);
        } catch (Exception e) {
            fail("addVehicle() threw an unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateVehicle() {
        System.out.println("Running testUpdateVehicle...");
        VehicleDTO vehicle = new DieselBusDTO(9999, "Diesel Bus", "Diesel", 45, 123, 0.09);

        try {
            logic.updateVehicle(vehicle);
        } catch (Exception e) {
            fail("updateVehicle() threw an unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testGetVehicleById() {
        System.out.println("Running testGetVehicleById...");
        int id = 1002;
        VehicleDTO result = logic.getVehicleById(id);
        assertNotNull(result, "Vehicle should be found for ID: " + id);
        assertEquals(id, result.getId());
    }

    @Test
    public void testGetAllVehicles() {
        System.out.println("Running testGetAllVehicles...");
        List<VehicleDTO> result = logic.getAllVehicles();
        assertNotNull(result, "List of vehicles should not be null");
        assertFalse(result.isEmpty(), "Vehicle list should not be empty");
    }

    @Test
    public void testDeleteVehicle() {
        System.out.println("Running testDeleteVehicle...");
        int vehicleId = 9999; 

        try {
            logic.deleteVehicle(vehicleId);
        } catch (Exception e) {
            fail("deleteVehicle() threw an unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testCreateVehicle() {
        System.out.println("Running testCreateVehicle...");

        VehicleDTO vehicle = VehiclesBusinessLogic.createVehicle(8888, "Diesel Bus", "Diesel", 30, 55, 0.07);
        assertNotNull(vehicle);
        assertEquals("Diesel Bus", vehicle.getType());

        try {
            VehiclesBusinessLogic.createVehicle(0, "Unknown Type", "Hybrid", 20, 10, 0.05);
            fail("Expected IllegalArgumentException for unknown type");
        } catch (IllegalArgumentException ex) {
            assertEquals("Unsupported vehicle type: Unknown Type", ex.getMessage());
        }
    }

    @Test
    public void testCleanData() {
        System.out.println("Running testCleanData...");
        VehicleDTO vehicle = new DieselBusDTO(2000, "   Diesel Bus   ", "   Diesel   ", 30, 50, 0.1);

        logic.cleanData(vehicle);

        assertEquals("Diesel Bus", vehicle.getType());
        assertEquals("Diesel", vehicle.getFuelType());
    }

    @Test
    public void testValidateVehicle() {
        System.out.println("Running testValidateVehicle...");

        VehicleDTO valid = new DieselBusDTO(1, "Electric Light Rail", "Electric", 100, 25, 0.03);

        try {
            logic.validateVehicle(valid);
        } catch (ValidationException e) {
            fail("validateVehicle() threw an exception on valid data: " + e.getMessage());
        }

        VehicleDTO invalid = new DieselBusDTO(2, "", "", 0, 0, 0);

        try {
            logic.validateVehicle(invalid);
            fail("Expected ValidationException for invalid vehicle input");
        } catch (ValidationException e) {
            System.out.println("ValidationException correctly thrown: " + e.getMessage());
        }
    }
}
