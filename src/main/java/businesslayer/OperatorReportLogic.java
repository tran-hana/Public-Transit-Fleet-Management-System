/*
 * File: OperatorReportLogic.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: This class handles business logic for generating operator 
 * performance reports in the Public Transit Fleet Management System.
 * It retrieves user data based on roles and simulates performance ratings.
 */
package businesslayer;

import dataaccesslayer.UsersDao;
import dataaccesslayer.UsersDaoImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import transferobjects.OperatorPerformance;
import transferobjects.UserDTO;

/**
 * The OperatorReportLogic class is responsible for retrieving a list of transit
 * system operators based on their role and generating simulated performance
 * ratings for them.
 *
 * @author Quoc Phong Tran
 * @see java.util.List;
 * @see dataaccesslayer;
 * @see java.util.ArrayList;
 * @see java.util.Random;
 * @see transferobjects
 * @version 1.0
 * @since 21.0.5
 */
public class OperatorReportLogic {

    UsersDao users = null;

    /**
     * Constructs a new {@code OperatorReportLogic} instance with the default
     * {@link UsersDaoImpl} implementation.
     */
    public OperatorReportLogic() {
        users = new UsersDaoImpl();
    }

    /**
     * Retrieves a list of users by their role and generates random performance
     * ratings and efficiency evaluations.
     *
     * @param role the user role (e.g., "Operator") used to filter users
     * @return a list of {@link OperatorPerformance} objects containing user
     * information, random performance rate, and efficiency level
     */
    public List<OperatorPerformance> getOperatorPerformanceList(String role) {
        List<UserDTO> operators = users.getUserByRole(role);
        List<OperatorPerformance> result = new ArrayList<>();

        Random rand = new Random();
        for (UserDTO op : operators) {
            double rate = 0.3 + (0.69 * rand.nextDouble());
            String efficiency = evaluate(rate);
            result.add(new OperatorPerformance(op, rate, efficiency));
        }

        return result;
    }

    /**
     * Evaluates a performance rate and returns a string representing
     * efficiency.
     *
     * @param rate the numerical performance rating between 0 and 1
     * @return a qualitative description of the performance: "Excellent",
     * "Good", "Average", or "Poor"
     */
    private String evaluate(double rate) {
        if (rate >= 0.9) {
            return "Excellent";
        } else if (rate >= 0.75) {
            return "Good";
        } else if (rate >= 0.5) {
            return "Average";
        } else {
            return "Poor";
        }
    }

}
