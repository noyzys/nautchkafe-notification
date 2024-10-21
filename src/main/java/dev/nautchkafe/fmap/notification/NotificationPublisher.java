package dev.nautchkafe.fmap.notification;

import io.vavr.control.Option;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

/**
 * The NotificationPublisher provides an interface for publishing notifications
 * and managing dispatch of the notification messages to various audiences and senders.
 */
interface NotificationPublisher {

    /**
     * Publishes a notification to a specific command sender with a defined notification key and content message.
     *
     * @param sender The person or entity who sends the notification.
     * @param notificationKey The key that identifies the type of notification to be sent.
     * @param contentMessage The content of the message to be sent as part of the notification.
     */
    void publishNotification(final CommandSender sender,
                             final NotificationMessageKey notificationKey,
                             final String contentMessage);

    /**
     * Publishes a notification to an audience with a specified notification key and message component.
     *
     * @param audience The audience who will receive the notification.
     * @param notificationKey The key that defines the specific notification to be sent.
     * @param message The message component that is part of the notification.
     */
    void publishNotification(final Audience audience,
                             final NotificationMessageKey notificationKey,
                             final Component message);

    /**
     * Attempts to dispatch a notification to the given audience, identified by the notification key and message component.
     * The dispatch operation can either be successful or fail, denoted by an Option wrapper around the notification object.
     *
     * @param audience The audience to whom the notification is dispatched.
     * @param notificationKey The key representing the specific type of notification.
     * @param message The component defining the message of the notification.
     * @return An Option of Notification indicating the result of the dispatch operation.
     * If successful, it contains the dispatched Notification object, otherwise it contains None.
     */
    Option<Notification> dispatchNotification(final Audience audience,
                                              final NotificationMessageKey notificationKey,
                                              final Component message);
}
