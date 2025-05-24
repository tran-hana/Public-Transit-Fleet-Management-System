/*
 * File: VehiclesBusinessLogic.java
 * Author: Ha Nhu Y Tran, 041165059, Group 5
 * Date: April 04, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project (Group 5)
 * Professor: Ramanjeet Singh
 * Purpose:  This class handles business logic for vehicles, including input validation,
 * data cleanup, and CRUD operations for the VehicleDao.
 */

package businesslayer;

import dataaccesslayer.VehicleDao;
import dataaccesslayer.VehicleDaoImpl;
import transferobjects.VehicleDTO;
import transferobjects.DieselBusDTO;
import transferobjects.DieselElectricTrainDTO;
import transferobjects.ElectricLightRailDTO;
import java.util.List;

/**
 * This class handles business logic for vehicles, including input validation,
 * data cleanup, and CRUD operations for the VehicleDao.
 * @author Ha Nhu Y Tran
 * @see businesslayer
 * @see transferobjects.DieselElectricTrainDTO
 * @see transferobjects.ElectricLightRailDTO
 * @see transferobjects.DieselBusDTO
 * @see transferobjects.VehicleDTO
 * @see dataaccesslayer.VehicleDao
 * @see dataaccesslayer.VehicleDaoImpl
 * @version 1.0
 * @since 21.0.5
 */
public class VehiclesBusinessLogic {

    /**
     * Maximum character length for the vehicle type.
     */
    private static final int TYPE_MAX_LENGTH = 30;

    /**
     * Maximum character length for the fuel type.
     */
    private static final int FUEL_TYPE_MAX_LENGTH = 20;

    /**
     * DAO used to interact with the vehicle table in the database.
     */
    private VehicleDao vehicleDao;

    /* Create a new instance and initializes implementation 
     *to handle vehicle-related database operations.    
     */
    public VehiclesBusinessLogic() {
        this.vehicleDao = new VehicleDaoImpl();
    }

    /**
     * Adds a new vehicle after cleaning and validating its data.
     *
     * @param vehicle the vehicle to add
     * @return 
     * @throws ValidationException if validation fails
     */
    public String addVehicle(VehicleDTO vehicle) throws ValidationException {
        try {
            // Check if vehicle with same ID already exists
            VehicleDTO existingVehicle = getVehicleById(vehicle.getId());
            if (existingVehicle != null) {
                return "Failed to add the vehicle with ID: " + vehicle.getId() + " because it already exists";
            }
        
            // Clean and validate the data
            cleanData(vehicle);
            validateVehicle(vehicle);
        
            // Add the vehicle to the database
            vehicleDao.addVehicle(vehicle);
            return "Vehicle with ID: " + vehicle.getId() + " successfully added";
        } catch (ValidationException e) {
            return "Failed to add vehicle: " + e.getMessage();
        } catch (Exception e) {
            return "An error occurred while adding the vehicle: " + e.getMessage();
        }
    }

    /**
     * Updates an existing vehicle record.
     *
     * @param vehicle the updated vehicle
     * @throws ValidationException if validation fails
     */
    public void updateVehicle(VehicleDTO vehicle) throws ValidationException {
        cleanData(vehicle);
        validateVehicle(vehicle);
        vehicleDao.updateVehicle(vehicle);
    }

    /**
     * Retrieves a vehicle by ID.
     *
     * @param id the vehicle ID
     * @return the vehicle or null if not found
     */
    public VehicleDTO getVehicleById(int id) {
        return vehicleDao.getVehicleById(id);
    }

    /**
     * Retrieves all vehicles in the system.
     *
     * @return list of vehicles
     */
    public List<VehicleDTO> getAllVehicles() {
        return vehicleDao.getAllVehicles();
    }

    /**
     * Deletes a vehicle by ID
     *
     * @param vehicleId the ID of the vehicle to delete
     */
    public void deleteVehicle(int vehicleId) {
        vehicleDao.deleteVehicle(vehicleId);
    }

    /**
     * Simple Factory method to create a specific VehicleDTO subclass based on
     * the provided type.
     *
     * @param id the vehicle ID
     * @param type the vehicle type (e.g., "Diesel Bus")
     * @param fuelType the fuel type
     * @param maxPassengers the maximum number of passengers
     * @param route the assigned route number
     * @param consumptionRate the fuel/energy consumption rate
     * @return an instance of a subclass of VehicleDTO
     * @throws IllegalArgumentException if the type is unknown
     */
    public static VehicleDTO createVehicle(int id, String type, String fuelType,
            int maxPassengers, int route, double consumptionRate) {
        if (type == null) {
            throw new IllegalArgumentException("Vehicle type cannot be null.");
        }

        switch (type.trim().toLowerCase()) {
            case "diesel bus":
                return new DieselBusDTO(id, type, fuelType, maxPassengers, route, consumptionRate);

            case "diesel-electric train":
                return new DieselElectricTrainDTO(id, type, fuelType, maxPassengers, route, consumptionRate);

            case "electric light rail":
                return new ElectricLightRailDTO(id, type, fuelType, maxPassengers, route, consumptionRate);

            default:
                throw new IllegalArgumentException("Unsupported vehicle type: " + type);
        }
    }


    /**
     * Trims whitespace from string fields.
     *
     * @param vehicle the vehicle to clean
     */
    public void cleanData(VehicleDTO vehicle) {
        if (vehicle.getType() != null) {
            vehicle.setType(vehicle.getType().trim());
        }
        if (vehicle.getFuelType() != null) {
            vehicle.setFuelType(vehicle.getFuelType().trim());
        }
    }

    /**
     * Validates all vehicle fields.
     *
     * @param vehicle the vehicle to validateVehicle
     * @throws ValidationException if any validation rule is violated
     */
    public void validateVehicle(VehicleDTO vehicle) throws ValidationException {
    validateString(vehicle.getType(), "Vehicle Type", TYPE_MAX_LENGTH, false);
    validateString(vehicle.getFuelType(), "Fuel type", FUEL_TYPE_MAX_LENGTH, false);

    validateInt(vehicle.getMaxPassengers(), "Maximum passengers");
    validateInt(vehicle.getRoute(), "route");
    validateDouble(vehicle.getConsumptionRate(), "Consumption Rate");
}
   

    /**
     * String validation method for author fields.
     *
     * @param value the value to check
     * @param fieldName the name of the field (for error messages)
     * @param maxLength max length allowed
     * @param isNullAllowed whether the field can be null
     * @throws ValidationException if validation fails
     */
    private void validateString(String value, String fieldName, int maxLength, boolean isNullAllowed)
            throws ValidationException {
        if (value == null && isNullAllowed) {
        } else if (value == null && !isNullAllowed) {
            throw new ValidationException(String.format("%s cannot be null",
                    fieldName));
        } else if (value.length() == 0) {
            throw new ValidationException(String.format("%s cannot be empty or only whitespace",
                    fieldName));
        } else if (value.length() > maxLength) {
            throw new ValidationException(String.format("%s cannot exceed %d characters",
                    fieldName, maxLength));
        }
    }

    /**
     * Validates that a double value is greater than 0.
     *
     * @param value the double value to validateVehicle
     * @param fieldName the field name (used in error messages)
     * @throws ValidationException if the value is less than or equal to 0
     */
    private void validateDouble(double value, String fieldName) throws ValidationException {
        if (value <= 0.0) {
            throw new ValidationException(fieldName + " must be greater than 0.");
        }
    }

    /**
     * Validates that an integer is greater than 0.
     *
     * @param value the integer value to validateVehicle
     * @param fieldName the field name (used in error messages)
     * @throws ValidationException if the value is less than or equal to 0
     */
    private void validateInt(int value, String fieldName) throws ValidationException {
        if (value <= 0) {
            throw new ValidationException(fieldName + " must be greater than 0.");
        }
    }

}
