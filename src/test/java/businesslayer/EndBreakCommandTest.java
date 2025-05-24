/*
 * File: EndBreakCommandTest.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 06, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose:
 * This test class validates the EndBreakCommand to ensure it properly delegates
 * the break-ending operation to BreakService and returns true after execution.
 */

package businesslayer;

import dataaccesslayer.BreakTimeDao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EndBreakCommandTest {

    /*
     * Test of execute method, of class EndBreakCommand.
     * This test simulates ending a break using a mock BreakService.
     */
    @Test
    public void testExecute() {

        try {
            // Mock BreakTimeDao with an implementation
            BreakTimeDao mockDao = new BreakTimeDao() {
                @Override
                public boolean insertBreakStart(int assignId, java.time.LocalDateTime startTime) {
                    return true; 
                }

                @Override
                public void updateBreakEnd(int assignId, java.time.LocalDateTime endTime) {
                    // Simulate successful update
                    System.out.println("Mock updateBreakEnd() called with assignId: " + assignId);
                }
            };

            // Inject mock DAO into service
            BreakService service = new BreakService(mockDao);
            int assignId = 456;

            // Create and execute EndBreakCommand
            EndBreakCommand command = new EndBreakCommand(service, assignId);
            boolean result = command.execute();

            // Assert result is true
            assertTrue(result, "EndBreakCommand should return true after successful execution.");

        } catch (Exception e) {
            fail("Exception during EndBreakCommand execution: " + e.getMessage());
        }
    }
}
