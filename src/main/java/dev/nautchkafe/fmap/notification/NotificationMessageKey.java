package dev.nautchkafe.fmap.notification;

import dev.nautchkafe.fmap.notification.display.ActionBarNotification;
import dev.nautchkafe.fmap.notification.display.SubtitleNotification;
import dev.nautchkafe.fmap.notification.display.TextNotification;
import dev.nautchkafe.fmap.notification.display.TitleNotification;
import io.vavr.Function2;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;

/**
 * An enumeration of different types of notifications that can be sent to an audience.
 * Each type is associated with a factory method to create a specific notification.
 * Represents a chat display for creating notifications:
 * Utilizes {@link TextNotification} .
 * Utilizes {@link TitleNotification}
 * Utilizes {@link SubtitleNotification}
 * Utilizes {@link ActionBarNotification}
 */
public enum NotificationMessageKey {
    CHAT((audience, message) -> new TextNotification(message)),
    TITLE((audience, message) -> new TitleNotification(message)),
    SUBTITLE((audience, message) -> new SubtitleNotification(message)),
    ACTIONBAR((audience, message) -> new ActionBarNotification(message));

    private final Function2<Audience, Component, Notification> factory;

    /**
     * Constructs a new instance of {@code NotificationMessageKey} with a specific factory method.
     *
     * @param factory A functional interface that accepts an {@link Audience} and a {@link Component}
     * to produce a {@link Notification}.
     */
    NotificationMessageKey(Function2<Audience, Component, Notification> factory) {
        this.factory = factory;
    }

    /**
     * Creates a notification using the associated factory method.
     *
     * @param audience The target audience for the notification.
     * @param message The message component of the notification.
     * @return A new instance of {@link Notification}.
     */
    public Notification createNotification(Audience audience, Component message) {
        return factory.apply(audience, message);
    }
}
