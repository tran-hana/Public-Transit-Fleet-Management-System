/*
 * File: FuleCostReportAdapter.java
 * Author: Quoc Phong Tran, 041134348, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: Implements the Report interface to adapt fuel consumption data 
 * into a fuel cost report format for the Public Transit Fleet Management System.
 */
package businesslayer;

import dataaccesslayer.ConsumptionDao;
import dataaccesslayer.ConsumptionDaoImpl;
import java.util.ArrayList;
import java.util.List;
import transferobjects.ConsumptionDTO;
import transferobjects.FuelCostDTO;

/**
 * Implements the Report interface to generate a list of FuelCostDTO objects by
 * calculating the fuel cost for each vehicle based on its consumption and fuel
 * type.
 *
 * @author Quoc Phong Tran
 * @see java.util.List;
 * @see dataaccesslayer.ConsumptionDao;
 * @see dataaccesslayer.ConsumptionDaoImpl;
 * @see java.util.ArrayList;
 * @see transferobjects.ConsumptionDTO;
 * @see transferobjects.FuelCostDTO;
 * @version 1.0
 * @since 21.0.5
 */
public class FuleCostReportAdapter implements Report {

    private ConsumptionDao consumption;

    /**
     * Constructs a new FuelCostReportAdapter with a default
     * {@code ConsumptionDaoImpl}.
     */
    public FuleCostReportAdapter() {
        consumption = new ConsumptionDaoImpl();
    }

    /**
     * Generates a report of fuel costs for all vehicles based on their fuel
     * consumption.
     *
     * @return a list of {@link FuelCostDTO} objects containing the calculated
     * fuel costs
     */
    @Override
    public List generateReport() {

        // Static fuel prices
        final double DIESEL_PRICE = 1.45;
        final double ELECTRIC_PRICE = 0.9;

        List<FuelCostDTO> fuelCosts = new ArrayList<>();
        List<ConsumptionDTO> consumptionList = consumption.getAllConsumptionWithRate();

        for (ConsumptionDTO cs : consumptionList) {
            double cost = 0.0;
            String type = cs.getVehicleType();

            if (type.equalsIgnoreCase("Diesel Bus")) {
                cost = cs.getActualConsumption() * DIESEL_PRICE;
            } else if (type.equalsIgnoreCase("Electric Light Rail")) {
                cost = cs.getActualConsumption() * ELECTRIC_PRICE;
            } else if (type.equalsIgnoreCase("Diesel-Electric Train")) {

                cost = cs.getActualConsumption() * (0.5 * DIESEL_PRICE + 0.5 * ELECTRIC_PRICE);
            }

            FuelCostDTO fuelCost = FuelCostDTO.builder()
                    .setVehicleId(cs.getVehicleId())
                    .setVehicleType(type)
                    .setCost(cost)
                    .build();

            fuelCosts.add(fuelCost);
        }
        return fuelCosts;
    }
}
