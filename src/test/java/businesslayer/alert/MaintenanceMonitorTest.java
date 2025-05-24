
package businesslayer.alert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import transferobjects.ComponentDTO;
import transferobjects.MaintenanceDTO;
import transferobjects.VehicleDTO;

/**
 * Unit test for MaintenanceMonitor.
 * Verifies maintenance alerts are generated only when usage exceeds threshold.
 * 
 * @author Cheng Qian
 */
public class MaintenanceMonitorTest {
    
    private MaintenanceMonitor monitor;
    private TestObserver observer;
    
    public MaintenanceMonitorTest() {
    }
    
    @BeforeEach
    public void setUp() {
        monitor = new MaintenanceMonitor();
        observer = new TestObserver();
        monitor.attach(observer);
    }
      
    
    /**
     * Tests evaluate method triggers alert when usage exceeds threshold and no prior maintenance exists.
     */
    @Test
    public void testEvaluate_usageExceedsThreshold_shouldNotify() {
        ComponentDTO component = new ComponentDTO();
        component.setId(1);
        component.setType("brake");
        component.setUsageHours(300);

        VehicleDTO vehicle = new VehicleDTO();
        vehicle.setId(101);
        vehicle.setType("Diesel Bus");

        MaintenanceDTO latest = null; // No prior maintenance
        int threshold = 250;

        monitor.evaluate(component, vehicle, latest, threshold);

        assertTrue(observer.wasNotified(), "Should trigger alert when usage exceeds threshold with no maintenance record");
        assertTrue(observer.getMessage().contains("Diesel Bus"));
        assertTrue(observer.getMessage().contains("brake"));
    }

    /**
     * Tests evaluate method does not trigger alert if usage is below threshold.
     */
    @Test
    public void testEvaluate_usageWithinThreshold_shouldNotNotify() {
        ComponentDTO component = new ComponentDTO();
        component.setId(2);
        component.setType("engine");
        component.setUsageHours(400);

        VehicleDTO vehicle = new VehicleDTO();
        vehicle.setId(202);
        vehicle.setType("Electric Light Rail");

        MaintenanceDTO latest = null;
        int threshold = 500;

        monitor.evaluate(component, vehicle, latest, threshold);

        assertFalse(observer.wasNotified(), "Should not trigger alert when usage is within threshold");
    }

    // Mock observer to capture alert
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
