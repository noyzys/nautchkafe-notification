package dev.nautchkafe.fmap.notification;

import io.vavr.Function2;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;

/**
 * A final class representing a NotificationKey, which is composed of a key and a factory function
 * for creating notifications.
 */
final class NotificationKey {

    private final String key;
    private final Function2<Audience, Component, Notification> factory;

    NotificationKey(final String key, final Function2<Audience, Component, Notification> factory) {
        this.key = key;
        this.factory = factory;
    }

    /**
     * Returns the key associated with this NotificationKey.
     *
     * @return the key of this NotificationKey
     */
    String getKey() {
        return key;
    }

    /**
     * Creates a Notification using the specified audience and message.
     *
     * @param audience the target audience for the notification
     * @param message the message component for the notification
     * @return the created Notification object
     */
    Notification createNotification(final Audience audience, final Component message) {
        return factory.apply(audience, message);
    }
}
