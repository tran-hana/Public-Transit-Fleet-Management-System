/*
 * File: MaintenanceCostReportAdapter.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Adapter class that implements the Report interface to return 
 * maintenance cost data using the MaintenanceCostReportDao implementation.
 * It allows uniform report generation through the Report interface.
 */
package businesslayer;

import dataaccesslayer.MaintenanceCostReportDao;
import dataaccesslayer.MaintenanceCostReportDaoImpl;
import java.util.List;

/**
 * Implements the Report interface to adapt the functionality of the
 * MaintenanceCostReportDao for use in generating maintenance-related reports
 * within the Public Transit Fleet Management System.
 *
 * @author Quoc Phong Tran
 * @see java.util.List;
 * @see dataaccesslayer;
 * @version 1.0
 * @since 21.0.5
 */
public class MaintenanceCostReportAdapter implements Report {

    private MaintenanceCostReportDao mcr;

    /**
     * Constructs a new MaintenanceCostReportAdapter with a default
     * implementation of {@link MaintenanceCostReportDaoImpl}.
     */
    public MaintenanceCostReportAdapter() {
        mcr = new MaintenanceCostReportDaoImpl();
    }

    /**
     * Generates a maintenance cost report by retrieving all records from the
     * underlying data source.
     *
     * @return a list of maintenance cost report records
     */
    @Override
    public List generateReport() {
        return mcr.getAllMaintenanceCost();
    }

}
