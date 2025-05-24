/*
 * File: ConsumptionBusinessLogic.java
 * Author: Cheng Qian
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project
 * Professor: Ramanjeet Singh
 *
 * Purpose:
 * This class encapsulates the business logic for accessing vehicle consumption data.
 * It delegates data access to the DAO layer and can be used by servlets or service classes
 * to fetch processed consumption records.
 */

package businesslayer;

import dataaccesslayer.ConsumptionDao;
import dataaccesslayer.ConsumptionDaoImpl;
import dataaccesslayer.DataSource;
import transferobjects.ConsumptionDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Handles business logic for retrieving consumption records.
 */
public class ConsumptionBusinessLogic {

    private final ConsumptionDao dao;

    /**
     * Constructs the business logic object and initializes the DAO.
     */
    public ConsumptionBusinessLogic() {
        this.dao = new ConsumptionDaoImpl();
    }

    /**
     * Gets all vehicle consumption records with their expected rates.
     *
     * @return list of consumption records
     * @throws SQLException if a database error occurs
     */
    public List<ConsumptionDTO> getAllConsumptionWithRate() throws SQLException {
        try (Connection conn = DataSource.getConnection()) {
            return dao.getAllConsumptionWithRate();
        }
    }
}
