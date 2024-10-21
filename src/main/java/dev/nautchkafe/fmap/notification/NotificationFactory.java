package dev.nautchkafe.fmap.notification;

import io.vavr.control.Option;
import io.vavr.control.Try;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A factory class for creating notification messages using MiniMessage format.
 */
public final class NotificationFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationFactory.class);
    private final MiniMessage miniMessage;

    public NotificationFactory(MiniMessage miniMessage) {
        this.miniMessage = miniMessage;
    }

    /**
     * Creates a notification message component if the input string is valid.
     *
     * @param contentMessage The string content intended for notification.
     * @return An option of component if the content is valid and correctly formatted, empty otherwise.
     */
    public Option<Component> createNotificationMessage(final String contentMessage) {
        return NotificationMessageValidator.validate(contentMessage)
                .toOption()
                .flatMap(this::createMessageDeserialization);
    }

    /**
     * Deserializes the validated message string into a component.
     *
     * @param contentMessage The validated string content to be deserialized.
     * @return An option of component, empty if any error occurs during deserialization.
     */
    private Option<Component> createMessageDeserialization(final String contentMessage) {
        return Try.of(() -> miniMessage.deserialize(contentMessage))
                .onFailure(e -> LOGGER.error("Invalid message format: {}", e.getMessage()))
                .toOption();
    }
}

