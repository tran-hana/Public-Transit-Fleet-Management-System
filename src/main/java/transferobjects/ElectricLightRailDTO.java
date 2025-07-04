/*
 * File: ElectricLightRailDTO.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: This class models an electric light rail vehicle in the fleet system. Inherits all shared
 * fields from VehicleDTO. This class is used by the Simple Factory to create
 * the specific type vehicle objects.
 */

package transferobjects;

/**
 * This class models an electric light rail vehicle in the fleet system. Inherits all shared
 * fields from VehicleDTO. This class is used by the Simple Factory to create
 * the specific type vehicle objects.
 * @author Ha Nhu Y Tran
 * @see transferobjects
 * @version 1.0
 * @since 21.0.5
 */
public class ElectricLightRailDTO extends VehicleDTO {

    public ElectricLightRailDTO() {}

    /**
     * Constructs a ElectricLightRailDT with all vehicle fields.
     *
     * @param id              the unique vehicle ID
     * @param type            the vehicle type ("Diesel Electric Train")
     * @param fuelType        the fuel type used by the train 
     * @param maxPassengers   the maximum number of passengers
     * @param route           the assigned route number or ID
     * @param consumptionRate the rate of fuel consumption
     */
    public ElectricLightRailDTO(int id, String type, String fuelType, int maxPassengers, int route, double consumptionRate) {
        super(id, type, fuelType, maxPassengers, route, consumptionRate);
    }
}
