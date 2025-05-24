package businesslayer.alert;

import java.time.LocalDateTime;
import transferobjects.ComponentDTO;
import transferobjects.MaintenanceDTO;
import transferobjects.VehicleDTO;

/**
 * The MaintenanceMonitor class evaluates whether a component's usage hours
 * exceed a predefined threshold. If exceeded, observers are notified.
 *
 * This class is part of the Observer pattern. It acts as a Subject and notifies
 * registered observers (such as AlertService) when maintenance is required.
 *
 * @see businesslayer.alert.Subject
 * @see businesslayer.alert.Observer
 * @author Cheng Qian
 * @version 1.1
 * @since 21.0.5
 */
public class MaintenanceMonitor extends Subject {

    /**
     * Evaluates a component's usage and decides if maintenance is needed.
     * Builds either a scheduling form or a completion form depending on cost.
     *
     * @param component the component data object
     * @param vehicle the vehicle to which the component belongs
     * @param maintenance the latest maintenance record for this component
     * @param threshold the usage hours limit for this component type
     */
    public void evaluate(ComponentDTO component, VehicleDTO vehicle, MaintenanceDTO maintenance, int threshold) {
        if (vehicle != null && component != null) {
        
            if (component.getUsageHours() > threshold) {
                String extra;
                if (maintenance != null && maintenance.getCostCents() == 0 && maintenance.getDate().isAfter(LocalDateTime.now())) {
                    String formattedDate = maintenance.getDate().toString().replace('T', ' ');
                    extra = buildScheduledMessage(formattedDate, maintenance.getId(), maintenance.getCostCents());
                } else {
                    extra = buildScheduleForm(component.getId());
                }


                String alert = "Maintenance Alert: <strong>" + vehicle.getType() + " " + vehicle.getId() + " — " + component.getType() + "</strong> requires maintenance.<br>" +
                               "Recorded usage: " + component.getUsageHours() + "h, Recommended limit: " + threshold + "h." +
                               "<br>" + extra;

                notifyObservers(alert);
            }
        }
    }

    /**
     * Constructs HTML form to schedule a maintenance task for the component.
     *
     * @param componentId the ID of the component
     * @return HTML form as string
     */
    private String buildScheduleForm(int componentId) {
        return "<form action='Servlet_ScheduleMaintenance' method='POST' style='margin-top:10px;'>" +
               "<input type='hidden' name='componentId' value='" + componentId + "'>" +
               "<input type='datetime-local' name='date' required " +
               "placeholder='2025-04-15T10:00' title='Format: yyyy-MM-ddTHH:mm'>" +
               "<button type='submit' style='margin-left:10px;'>Schedule Maintenance</button>" +
               "</form>";
    }

    /**
     * Constructs a display message indicating the component already has maintenance scheduled.
     * If cost not registered, allows user to enter cost and complete maintenance.
     *
     * @param date the scheduled date string to show
     * @param maintenanceId the maintenance ID record
     * @param costCents current cost in cents, 0 means not completed yet
     * @return formatted HTML message
     */
    private String buildScheduledMessage(String date, int maintenanceId, int costCents) {
        StringBuilder sb = new StringBuilder();
        sb.append("<em>Maintenance scheduled for: ").append(date).append("</em>");

        if (costCents == 0) {
            sb.append("<br><form action='Servlet_CompleteMaintenance' method='POST'>")
              .append("<input type='hidden' name='maintenanceId' value='").append(maintenanceId).append("'>")
              .append("<label for='costDollars'>Enter Cost: </label>")
              .append("<input type='number' name='costDollars' step='0.01' min='0' required>")
              .append("<button type='submit' style='margin-left:10px;'>Complete Maintenance</button>")
              .append("</form>");
        }

        return sb.toString();
    }
}
