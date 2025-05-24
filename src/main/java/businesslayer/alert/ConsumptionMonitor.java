package businesslayer.alert;

import java.util.Map;

/**
 * The ConsumptionMonitor class calculates whether a vehicle's consumption
 * is excessive by comparing actual consumption to expected consumption
 * (based on distance and vehicle consumption rate). If excessive,
 * it notifies all registered observers.
 *
 * This class is part of an Observer pattern implementation. It acts as the Subject
 * and notifies registered observers (e.g. AlertService) when an alert condition is met.
 * It also uses a simplified form of the Strategy pattern by mapping vehicle types to
 * behavior (fuel vs. energy classification) without introducing separate strategy classes.
 *
 * @see businesslayer.alert.Subject
 * @see businesslayer.alert.Observer
 * @author Cheng Qian
 * @version 1.0
 * @since 21.0.5
 */
public class ConsumptionMonitor extends Subject {

    /**
     * Evaluates a vehicle's actual consumption against expected rate.
     * If the actual consumption exceeds expected, observers will be notified.
     *
     * @param vehicleId the vehicle identifier (as String)
     * @param distance the distance traveled in kilometers
     * @param actualConsumption the actual measured consumption
     * @param expectedRate the vehicle's expected consumption rate (e.g., L/km)
     * @param vehicleType the vehicle's type (e.g. Diesel Bus, Electric Light Rail)
     */
    public void evaluate(String vehicleId, double distance, double actualConsumption, double expectedRate, String vehicleType) {
        double expectedConsumption = distance * expectedRate;
        if (actualConsumption > expectedConsumption) {

            Map<String, String> typeMapping = Map.of(
                "electric", "energy",
                "diesel", "fuel",
                "hybrid", "fuel"
            );

            String key = vehicleType.toLowerCase();
            String consumptionType = typeMapping.entrySet().stream()
                    .filter(e -> key.contains(e.getKey()))
                    .map(Map.Entry::getValue)
                    .findFirst()
                    .orElse("fuel");

            String unit = consumptionType.equals("energy") ? "kWh" : "L";

            String alert = capitalize(consumptionType) + " Consumption Alert: <strong>" + vehicleType + " " + vehicleId +
               "</strong> usage exceeds expected level in the recent trip.  <br> Actual: " + actualConsumption + unit + ", Expected max: " +
               String.format("%.1f", expectedConsumption) + unit;


            notifyObservers(alert);
        }
    }

    private String capitalize(String word) {
        if (word == null || word.isEmpty()) return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
