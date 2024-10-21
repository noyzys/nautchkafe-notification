package dev.nautchkafe.fmap.notification;

import io.vavr.control.Option;
import io.vavr.control.Validation;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.AudienceProvider;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

/**
 * The {@code NotificationDispatcher} class is responsible for managing and
 * dispatching notifications to various audiences. It implements the
 * {@link NotificationAudience} interface to ensure that notifications can be
 * broadcasted and published to specified audiences.
 * <p>
 * This class uses an {@link AudienceProvider} to retrieve audiences and a
 * {@link NotificationFactory} to create notification messages. Messages are
 * validated using {@link NotificationMessageValidator} before broadcasting.
 * </p>
 */
public final class NotificationDispatcher implements NotificationAudience {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationDispatcher.class);
    private final AudienceProvider audienceProvider;
    private final NotificationFactory notificationFactory;

    public NotificationDispatcher(final AudienceProvider audienceProvider, final MiniMessage miniMessage) {
        this.audienceProvider = audienceProvider;
        this.notificationFactory = new NotificationFactory(miniMessage);
    }

    /**
     * Broadcasts a notification to all available audiences.
     *
     * @param contentMessage the message content to be broadcasted
     */
    @Override
    public void broadcast(final String contentMessage) {
        final Audience audience = audienceProvider.all();

         // We throw an exception if an error occurs.
        NotificationMessageValidator.validate(contentMessage)
                .fold(error -> {
                    LOGGER.error("Invalid message format: {}", error.getMessage());
                    throw error; 
                }, message -> notificationFactory.createNotificationMessage(message)
                        .peek(deserializedMsg -> publishNotification(audience, NotificationMessageKey.CHAT, deserializedMsg))
                        .onEmpty(() -> LOGGER.error("Failed to deserialize message: {}", message)));
    }

    /**
     * Publishes a notification to a specific command sender.
     *
     * @param sender the command sender
     * @param notificationKey the key identifying the type of notification
     * @param contentMessage the message content to be sent
     * @throws IllegalArgumentException if the sender is invalid or an error occurs while publishing
     */
    @Override
    public void publishNotification(final CommandSender sender, final NotificationMessageKey notificationKey, final String contentMessage) {
        audience(sender).flatMap(audience -> notificationFactory.createNotificationMessage(contentMessage)
                        .flatMap(message -> dispatchNotification(audience, notificationKey, message)))
                .getOrElseThrow(() -> new IllegalArgumentException("Sender is not a valid audience or could not publish notification for key: " + notificationKey.name()));
    }

    /**
     * Publishes a notification to a specified audience with a preformatted component message.
     *
     * @param audience the audience to receive the notification
     * @param notificationKey the key identifying the type of notification
     * @param message the preformatted component message
     * @throws IllegalArgumentException if an error occurs while publishing
     */
    @Override
    public void publishNotification(final Audience audience, final NotificationMessageKey notificationKey, final Component message) {
        dispatchNotification(audience, notificationKey, message)
                .getOrElseThrow(() -> new IllegalArgumentException("Could not publish notification for key: " + notificationKey.name()));
    }

    /**
     * Dispatches a notification to a specified audience.
     *
     * @param audience the audience to receive the notification
     * @param notificationKey the key identifying the type of notification
     * @param message the component message
     * @return an optional notification, or empty if dispatching fails
     */
    @Override
    public Option<Notification> dispatchNotification(final Audience audience, final NotificationMessageKey notificationKey, final Component message) {
        return Option.of(notificationKey)
                .map(key -> key.createNotification(audience, message))
                .map(notification -> {
                    dispatch(notificationKey.name(), audience, notification);

                    return notification;
                });
    }

    /**
     * Publishes a notification to a specific player.
     *
     * @param player the player to receive the notification
     * @param notificationKey the key identifying the type of notification
     * @param contentMessage the message content to be sent
     * @throws IllegalArgumentException if an error occurs while dispatching
     */
    public void publishNotification(final Player player, final NotificationMessageKey notificationKey, final String contentMessage) {
        final Audience audience = audienceProvider.player(player.getUniqueId());
        final Validation<IllegalArgumentException, String> validation = NotificationMessageValidator.validate(contentMessage);

        validation.fold(error -> {
                    LOGGER.error("Invalid message format: {}", error.getMessage());
                    throw error;
                }, message -> {

                    final Option<Component> notificationComponent = notificationFactory.createNotificationMessage(message);
                    notificationComponent
                            .peek(notification -> dispatchNotification(audience, notificationKey, notification))
                            .onEmpty(() -> {
                                throw new IllegalArgumentException("Could not dispatch notification to player: " + player.getName());
                            });

                    return message;
                }
        );
    }

    /**
     * Internal method to dispatch a notification to an audience.
     *
     * @param notificationType the type of notification
     * @param audience the audience to receive the notification
     * @param notification the notification to be sent
     */
    private void dispatch(final String notificationType, final Audience audience, final Notification notification) {
        LOGGER.info("Sending notification of type: {}", notificationType);
        notification.dispatch(audience);
    }

    /**
     * Retrieves an appropriate audience for a given command sender.
     *
     * @param sender the command sender
     * @return an optional audience, or the console audience if the sender is not valid
     */
    private Option<Audience> audience(final CommandSender sender) {
        return Option.of(sender)
                .filter(Player.class::isInstance)
                .map(Player.class::cast)
                .map(player -> audienceProvider.player(player.getUniqueId()))
                .orElse(Option.of(audienceProvider.console()));
    }
}

