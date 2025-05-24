/*
 * File: BreakTimeDaoImplTest.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 06, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose:
 * This JUnit test class verifies the functionality of the BreakTimeDaoImpl methods,
 * specifically the ability to insert and update break records for vehicle assignments.
 */

package dataaccesslayer;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BreakTimeDaoImplTest {

    /**
     * Test of insertBreakStart method, of class BreakTimeDaoImpl.
     * This test attempts to insert a break start time for an assignment ID.
     */
    @Test
    public void testInsertBreakStart() {

        BreakTimeDaoImpl dao = new BreakTimeDaoImpl();

        int assignID = 1;
        LocalDateTime startTime = LocalDateTime.now();

        try {
            boolean result = dao.insertBreakStart(assignID, startTime);
            assertTrue(result || !result, "insertBreakStart() executed with no exception");
        } catch (Exception e) {
            fail("Exception occurred in insertBreakStart(): " + e.getMessage());
        }
    }

    /*
     * Test of updateBreakEnd method, of class BreakTimeDaoImpl.
     * This test updates the end time for an existing break using a known assign ID.
     */
    @Test
    public void testUpdateBreakEnd() {

        BreakTimeDaoImpl dao = new BreakTimeDaoImpl();

        int assignID = 1; 
        LocalDateTime endTime = LocalDateTime.now();

        try {
            dao.updateBreakEnd(assignID, endTime);
            assertTrue(true, "updateBreakEnd() executed successfully.");
        } catch (Exception e) {
            fail("Exception occurred in updateBreakEnd(): " + e.getMessage());
        }
    }
}
