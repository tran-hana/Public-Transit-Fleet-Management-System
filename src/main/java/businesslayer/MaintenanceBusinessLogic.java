/*
 * File: MaintenanceBusinessLogic.java
 * Author: Cheng Qian
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project
 * Professor: Ramanjeet Singh
 *
 * Purpose:
 * This class encapsulates the business logic for accessing component maintenance data.
 * It delegates data access to the DAO layer and can be used by servlets or service classes
 * to fetch processed maintenance records.
 */

package businesslayer;

import dataaccesslayer.MaintenanceDao;
import dataaccesslayer.MaintenanceDaoImpl;
import dataaccesslayer.DataSource;
import transferobjects.MaintenanceDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Handles business logic for retrieving maintenance records.
 */
public class MaintenanceBusinessLogic {

    private final MaintenanceDao dao;

    /**
     * Constructs the business logic object and initializes the DAO.
     */
    public MaintenanceBusinessLogic() {
        this.dao = new MaintenanceDaoImpl();
    }

    /**
     * Gets all maintenance records from the database.
     *
     * @return list of maintenance records
     * @throws SQLException if a database error occurs
     */
    public List<MaintenanceDTO> getAllMaintenances() throws SQLException {
        try (Connection conn = DataSource.getConnection()) {
            return dao.getAllMaintenances();
        }
    }
}
