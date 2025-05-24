/*
 * File: FuleCostReportAdapterTest.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: FuleCostReportAdapter verifies that the FuleCostReportAdapter correctly interacts 
 * with the ConsumptionDao to retrieve and process the data for generating a fuel cost report.
*/

package businesslayer;

import dataaccesslayer.ConsumptionDao;
import dataaccesslayer.ConsumptionDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import java.util.Arrays;
import transferobjects.ConsumptionDTO;

import static org.mockito.Mockito.*;

public class FuleCostReportAdapterTest {

    private FuleCostReportAdapter instance;
    private ConsumptionDao consumptionDaoMock;

    @BeforeEach
    public void setUp() {
        // Mock the ConsumptionDaoImpl 
        consumptionDaoMock = mock(ConsumptionDaoImpl.class);
        // Mocking the method to return the data
        when(consumptionDaoMock.getAllConsumptionWithRate()).thenReturn(Arrays.asList(
            new ConsumptionDTO(1, 500, 100, 0, "Diesel Bus"),
            new ConsumptionDTO(2, 300, 200, 0, "Electric Light Rail"),
            new ConsumptionDTO(3, 400, 150, 0, "Diesel-Electric Train")
        ));
        // Instantiate the FuleCostReportAdapter (no parameters)
        instance = new FuleCostReportAdapter();  
    }


}
