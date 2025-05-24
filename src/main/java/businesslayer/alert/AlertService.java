/*
 * File: AlertService.java
 * Author: Cheng Qian
 * Date: April 03, 2025
 * Course: CST8288 – OOP with Design Patterns
 * Assignment: Final Project
 * Professor: Ramanjeet Singh
 *
 * Purpose:
 * This class implements the Observer interface for consumption alert notifications.
 * When a consumption alert is triggered, this class receives the message and attaches
 * it to the HttpServletRequest, so that it can be displayed on the web page.
 */

package businesslayer.alert;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the Observer interface for displaying alerts in the web layer.
 * This observer sets an alert message as a request attribute to be used in JSP.
 * 
 * @author Cheng Qian
 * @version 1.0
 * @since 21.0.5
 */
public class AlertService implements Observer {

    private HttpServletRequest request;

    /**
     * Constructs an AlertService with the current HTTP request.
     * 
     * @param request the current HttpServletRequest object
     */
    public AlertService(HttpServletRequest request) {
        this.request = request;
        request.setAttribute("alertMessages", new ArrayList<String>());
    }

    /**
     * Receives an alert message and sets it as a request attribute.
     * 
     * @param message the alert message to be displayed
     */
    @Override
    public void update(String message) {
        List<String> alerts = (List<String>) request.getAttribute("alertMessages");
        alerts.add(message);
    }
}
