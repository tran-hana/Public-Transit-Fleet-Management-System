/*
 * File: FuleCostReportAdapterTest.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: The test verifies that FuleCostReportAdapter interacts correctly 
 * with ConsumptionDao to generate a fuel cost report.
*/


package controller;

import businesslayer.ValidationException;
import businesslayer.VehiclesBusinessLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import transferobjects.VehicleDTO;

public class AddVehicleControllerTest {

    @InjectMocks
    private AddVehicleController addVehicleController;

    @Mock
    private VehiclesBusinessLogic vehiclesBusinessLogic;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    public void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessAddVehicle_Success() throws ValidationException {
        // Set up mock request parameters
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("type")).thenReturn("Diesel Bus"); 
        when(request.getParameter("fuelType")).thenReturn("Diesel");
        when(request.getParameter("maxPassengers")).thenReturn("40");
        when(request.getParameter("route")).thenReturn("101");
        when(request.getParameter("consumptionRate")).thenReturn("15.0");

        // Create a mock VehicleDTO to simulate the result of the vehicle creation
        VehicleDTO mockVehicleDTO = mock(VehicleDTO.class);

        // Mock the addVehicle method to return a success message
        when(vehiclesBusinessLogic.addVehicle(any(VehicleDTO.class))).thenReturn("Vehicle added successfully");

        // Act: Call the method under test
        String result = addVehicleController.processAddVehicle(request);

        // Debugging: Print the result to help troubleshoot
        System.out.println("Result: " + result);

        // Assert: Verify the result
        assertEquals("Vehicle added successfully", result, "The result should be the success message.");

        // Verify that addVehicle was called once with any VehicleDTO
        verify(vehiclesBusinessLogic, times(1)).addVehicle(any(VehicleDTO.class));
    }

    @Test
    public void testProcessAddVehicle_InvalidNumberFormat() throws ValidationException {
        // Arrange: Set up mock request with invalid number for maxPassengers
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("type")).thenReturn("Diesel Bus");
        when(request.getParameter("fuelType")).thenReturn("Diesel");
        when(request.getParameter("maxPassengers")).thenReturn("invalid"); 
        when(request.getParameter("route")).thenReturn("101");
        when(request.getParameter("consumptionRate")).thenReturn("10.5");

        // Act: Call the method under test
        String result = addVehicleController.processAddVehicle(request);

        // Debugging: Print the result to help troubleshoot
        System.out.println("Result: " + result);

        // Assert: Verify the result contains the expected error message
        assertTrue(result.contains("Failed to add vehicle: Invalid number format in one of the fields"), 
                   "The result should contain the specific error message about invalid number format.");
        
        // Verify that addVehicle was not called due to invalid input
        verify(vehiclesBusinessLogic, times(0)).addVehicle(any(VehicleDTO.class));
    }

    @Test
    public void testProcessAddVehicle_Exception() throws ValidationException {
        // Arrange: Set up mock request with valid parameters
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("type")).thenReturn("Diesel Bus");
        when(request.getParameter("fuelType")).thenReturn("Diesel");
        when(request.getParameter("maxPassengers")).thenReturn("40");
        when(request.getParameter("route")).thenReturn("101");
        when(request.getParameter("consumptionRate")).thenReturn("15.0");

        // Mock the addVehicle method to throw an exception
        when(vehiclesBusinessLogic.addVehicle(any(VehicleDTO.class))).thenThrow(new RuntimeException("Something went wrong"));

        // Act: Call the method under test
        String result = addVehicleController.processAddVehicle(request);

        // Debugging: Print the result to help troubleshoot
        System.out.println("Result: " + result);

        // Assert: Verify that the exception handling returns the correct error message
        assertTrue(result.contains("Failed to add vehicle: Something went wrong"), 
                   "The result should contain the exception message.");

        // Verify that addVehicle was called once
        verify(vehiclesBusinessLogic, times(1)).addVehicle(any(VehicleDTO.class));
    }
}
