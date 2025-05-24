package businesslayer.alert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for ConsumptionMonitor.
 * Verifies excessive consumption triggers observer notifications.
 * 
 * @author Cheng Qian
 */
public class ConsumptionMonitorTest {

    private ConsumptionMonitor monitor;
    private TestObserver observer;

    @BeforeEach
    public void setUp() {
        monitor = new ConsumptionMonitor();
        observer = new TestObserver();
        monitor.attach(observer);
    }

    /**
     * Tests evaluate method for excessive consumption case.
     */
    @Test
    public void testEvaluate_excessiveConsumption_shouldNotifyObserver() {
        String vehicleId = "1001";
        double distance = 100.0; // in km
        double expectedRate = 0.1; // 0.1 L/km -> expected Consumption = 10L
        double actualConsumption = 12.0; // exceeds expected
        String vehicleType = "Diesel Bus";

        monitor.evaluate(vehicleId, distance, actualConsumption, expectedRate, vehicleType);

        assertTrue(observer.wasNotified(), "Observer should be notified for excessive consumption.");
        assertTrue(observer.getMessage().contains("Diesel Bus"),
                "Alert message should contain the vehicle type.");
        assertTrue(observer.getMessage().contains("1001"),
                "Alert message should contain the vehicle ID.");
    }
    
    /**
 * Tests evaluate method when consumption is within expected range.
 * Verifies observer is not notified in normal usage.
 */
@Test
public void testEvaluate_normalConsumption_shouldNotNotifyObserver() {
    String vehicleId = "1002";
    double distance = 100.0;
    double expectedRate = 0.1; // expected = 10.0L
    double actualConsumption = 9.5; // within expected
    String vehicleType = "Electric Light Rail";

    monitor.evaluate(vehicleId, distance, actualConsumption, expectedRate, vehicleType);

    assertFalse(observer.wasNotified(), "Observer should not be notified for normal consumption.");
}


    // A test observer that captures the alert message
    private static class TestObserver implements Observer {
        private String message;

        @Override
        public void update(String message) {
            this.message = message;
        }

        public boolean wasNotified() {
            return message != null;
        }

        public String getMessage() {
            return message;
        }
    }
}
