package dev.nautchkafe.fmap.notification;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;

/**
 * Notification interface defines a common structure for implementation
 * of notifying various audiences with different message components.
 */
public interface Notification {

    /**
     * Dispatches the message to a specified audience.
     * This method must be implemented to define the message sending behavior.
     *
     * @param audience The audience to whom the message should be dispatched.
     */
    void dispatch(final Audience audience);

    /**
     * Retrieves the message component associated with this notification.
     * This method must be implemented to return the current message component.
     *
     * @return Component representing the message intended for dispatch.
     */
    Component getMessage();
}
