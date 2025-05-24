/*
 * File: DieselElectricTrainDTO.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh 
 * Purpose: This class models a hybrid diesel-electric train in the fleet system. Inherits all shared
 * fields from VehicleDTO. This class is used by the Simple Factory to create
 * specific type vehicle objects.
 */

package transferobjects;

/**
 * This class models a hybrid diesel-electric train in the fleet system. Inherits all shared
 * fields from VehicleDTO. This class is used by the Simple Factory to create
 * role-specific vehicle objects.
 * @author Ha Nhu Y Tran
 * @see transferobjects
 * @version 1.0
 * @since Java 21
 */
public class DieselElectricTrainDTO extends VehicleDTO {
    
    // Default constructor
    public DieselElectricTrainDTO() {}
    
    /**
     * Constructs a DieselElectricTrainDTO with all vehicle fields.
     *
     * @param id              the unique vehicle ID
     * @param type            the vehicle type ("Diesel Electric Train")
     * @param fuelType        the fuel type used by the train 
     * @param maxPassengers   the maximum number of passengers
     * @param route           the assigned route number or ID
     * @param consumptionRate the rate of fuel consumption
     */
    public DieselElectricTrainDTO(int id, String type, String fuelType, int maxPassengers, int route, double consumptionRate) {
        super(id, type, fuelType, maxPassengers, route, consumptionRate);
    }
}
