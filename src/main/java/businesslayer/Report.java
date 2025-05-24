/*
 * File: Report.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: This interface defines the contract for generating reports in the 
 * Public Transit Fleet Management System. It serves as a template for creating
 * various report types by specifying a method to generate reports.
 */
package businesslayer;

import java.util.List;

/**
 * The Report interface defines the contract for generating reports in the
 * Public Transit Fleet Management System. It is a generic interface designed to
 * work with different report types, allowing for flexibility in generating
 * various reports such as maintenance cost reports, fuel usage reports.
 *
 * @author Quoc Phong Tran
 * @see java.util.List;
 * @version 1.0
 * @since 21.0.5
 */
public interface Report<T> {

    /**
     * Generates a report and returns a list of report data.
     * 
     * This method must be implemented by any class that generates a specific
     * type of report. The report data returned is of type T, which can
     * be a DTO (Data Transfer Object) representing the report's contents.
     *
     * @return a list of report data of type T
     */
    List<T> generateReport();
}
