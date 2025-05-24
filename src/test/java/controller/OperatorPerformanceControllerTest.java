/*
 * File: OperatorPerformanceControllerTest.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: The purpose of OperatorPerformanceControllerTest is to verify that 
 * the OperatorPerformanceController correctly processes operator performance data, 
 * interacts with the business logic, and handles efficiency ratings properly.
*/

package controller;

import controller.OperatorPerformanceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import transferobjects.OperatorPerformance;
import businesslayer.OperatorReportLogic;
import transferobjects.UserDTO;

public class OperatorPerformanceControllerTest {

    private OperatorPerformanceController instance;
    private OperatorReportLogic operatorReportLogic;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    public void setUp() {
        operatorReportLogic = mock(OperatorReportLogic.class);  // Mocking the OperatorReportLogic
        request = mock(HttpServletRequest.class);  // Mocking HttpServletRequest
        response = mock(HttpServletResponse.class);  // Mocking HttpServletResponse
        instance = new OperatorPerformanceController();
        instance.operatorReportLogic = operatorReportLogic;  // Injecting mock OperatorReportLogic
    }

    // Case 1: Testing processRequest with a user object
@Test
public void testProcessRequest() throws Exception {
    // Arrange: Prepare a User object and OperatorPerformance list to be returned by the business logic layer
    UserDTO user1 = new UserDTO("Carol Lin", "carol@example.com", "carol123", "Operator");
    UserDTO user2 = new UserDTO("John Doe", "john.doe@example.com", "john123", "Operator");

    // Prepare OperatorPerformance data using the User object and performance data
    List<OperatorPerformance> expectedList = Arrays.asList(
        new OperatorPerformance(user1,95, "good"),
        new OperatorPerformance(user2,80, "excellent")
    );

    // Mock the behavior of operatorReportLogic to return expected data
    when(operatorReportLogic.getOperatorPerformanceList("Operator")).thenReturn(expectedList);

    // Act: Call the processRequest method
    List<OperatorPerformance> result = instance.processRequest(request, response);

    // Assert: Verify the result matches the expected data
    assertEquals(expectedList, result, "Expected the operator performance list to be returned.");
}

    // Case 2: Testing getEfficiencyClass with valid ratings
    @Test
    public void testGetEfficiencyClass() {
        // Arrange: Input values for efficiency ratings
        String efficiencyExcellent = "excellent";
        String efficiencyGood = "good";
        String efficiencyAverage = "average";
        String efficiencyPoor = "poor";

        // Act & Assert: Check the returned CSS class for each efficiency rating
        assertEquals("excellent", instance.getEfficiencyClass(efficiencyExcellent), "Expected 'excellent' class for 'excellent' rating.");
        assertEquals("good", instance.getEfficiencyClass(efficiencyGood), "Expected 'good' class for 'good' rating.");
        assertEquals("average", instance.getEfficiencyClass(efficiencyAverage), "Expected 'average' class for 'average' rating.");
        assertEquals("poor", instance.getEfficiencyClass(efficiencyPoor), "Expected 'poor' class for 'poor' rating.");
    }

    // Case 3: Testing getEfficiencyClass with invalid efficiency rating
    @Test
    public void testGetEfficiencyClass_InvalidRating() {
        // Arrange: An invalid efficiency rating
        String efficiencyInvalid = "excellentExcellent";  

        // Act: Call getEfficiencyClass with an invalid rating
        String result = instance.getEfficiencyClass(efficiencyInvalid);

        // Assert: Verify the result is an empty string (default case)
        assertEquals("", result, "Expected default CSS class for invalid efficiency rating.");
    }
}
