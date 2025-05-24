/*
 * File: FuleCostReportAdapterTest.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: The purpose of UpdateVehicleControllerTest is to verify that 
 * the UpdateVehicleController correctly interacts with the VehiclesBusinessLogic, 
 * handles vehicle retrieval, and processes updates, including handling invalid data formats.
*/

package controller;
import businesslayer.ValidationException;
import controller.UpdateVehicleController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import javax.servlet.http.HttpServletRequest;
import transferobjects.VehicleDTO;
import businesslayer.VehiclesBusinessLogic;

public class UpdateVehicleControllerTest {

    private UpdateVehicleController instance;
    private VehiclesBusinessLogic vehiclesBusinessLogic;
    private HttpServletRequest request;

    @BeforeEach
    public void setUp() {
        vehiclesBusinessLogic = mock(VehiclesBusinessLogic.class);  // Mocking VehiclesBusinessLogic
        request = mock(HttpServletRequest.class);  // Mocking HttpServletRequest
        instance = new UpdateVehicleController();
        instance.vehiclesBusinessLogic = vehiclesBusinessLogic;  // Injecting mock VehiclesBusinessLogic
    }

    // Case 1: Testing getVehicleById
    @Test
    public void testGetVehicleById() {
        // Arrange
        int vehicleId = 1;
        VehicleDTO expectedVehicle = new VehicleDTO(1, "Bus", "Diesel", 50, 10, 8.5);  

        // Mock the behavior of the business logic layer
        when(vehiclesBusinessLogic.getVehicleById(vehicleId)).thenReturn(expectedVehicle);

        // Act
        VehicleDTO result = instance.getVehicleById(vehicleId);

        // Assert
        assertEquals(expectedVehicle, result, "Expected the vehicle to be returned by getVehicleById.");
    }


    // Case 2: Testing processUpdateVehicle with invalid number format
    @Test
    public void testProcessUpdateVehicle_InvalidNumberFormat() {
        // Arrange: Simulating invalid format for maxPassengers (non-numeric value)
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("type")).thenReturn("Bus");
        when(request.getParameter("fuelType")).thenReturn("Diesel");
        when(request.getParameter("maxPassengers")).thenReturn("invalid");  
        when(request.getParameter("route")).thenReturn("10");
        when(request.getParameter("consumptionRate")).thenReturn("8.5");

        // Act
        String result = instance.processUpdateVehicle(request);

        // Assert
        assertEquals("Failed to update vehicle: Invalid number format in one of the fields", result, "Expected error message for invalid number format.");
    }

}
