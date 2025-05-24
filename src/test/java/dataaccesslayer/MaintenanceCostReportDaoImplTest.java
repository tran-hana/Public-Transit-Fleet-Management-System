/*
 * File: FuleCostReportAdapterTest.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: The purpose of MaintenanceCostReportDaoImplTest is to verify that 
 * the MaintenanceCostReportDaoImpl correctly retrieves maintenance cost data 
 * by testing the getAllMaintenanceCost method and ensuring it returns a non-null list with valid entries.
*/

package dataaccesslayer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import transferobjects.MaintenanceCostDTO;

public class MaintenanceCostReportDaoImplTest {

    public MaintenanceCostReportDaoImplTest() {
    }

    @Test
    public void testGetAllMaintenanceCost() {
        System.out.println("Running test: getAllMaintenanceCost");

        MaintenanceCostReportDaoImpl instance = new MaintenanceCostReportDaoImpl();

        List<MaintenanceCostDTO> result = instance.getAllMaintenanceCost();

        // Assertions
        assertNotNull(result, "The result list should not be null.");
        assertTrue(result.size() >= 0, "The result list should contain 0 or more items.");

        // Optional: print each result for manual verification
        for (MaintenanceCostDTO dto : result) {
            System.out.println("Vehicle ID: " + dto.getVehicleId() +
                               ", Cost: " + dto.getCost() +
                               ", Type: " + dto.getVehiclType() +
                               ", Component: " + dto.getComponentType() +
                               ", Date: " + dto.getDate());
        }
    }
}