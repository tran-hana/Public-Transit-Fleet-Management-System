/*
 * File: StartBreakCommandTest.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 06, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose:
 * This test class verifies the behavior of StartBreakCommand,
 * ensuring it correctly delegates to BreakService and returns true
 * when a break is successfully started.
 */

package businesslayer;

import dataaccesslayer.BreakTimeDao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StartBreakCommandTest {

    /**
     * Test of execute method, of class StartBreakCommand.
     * This test simulates a break start using a mock BreakTimeDao.
     */
    @Test
    public void testExecute() {
        try {
            // Mock BreakTimeDao to always return true for startBreak
            BreakTimeDao mockDao = new BreakTimeDao() {
                @Override
                public boolean insertBreakStart(int assignId, java.time.LocalDateTime startTime) {
                    return true;
                }

                @Override
                public void updateBreakEnd(int assignId, java.time.LocalDateTime endTime) {
                    // Not needed for this test
                }
            };

            // Inject the mock DAO into BreakService
            BreakService service = new BreakService(mockDao);
            int assignId = 123;

            // Create command instance
            StartBreakCommand command = new StartBreakCommand(service, assignId);

            // Execute and assert result
            boolean result = command.execute();
            assertTrue(result, "StartBreakCommand should return true when break is started successfully.");

        } catch (Exception e) {
            fail("Exception thrown during testExecute(): " + e.getMessage());
        }
    }
}
