package dev.nautchkafe.fmap.notification.extension

import dev.nautchkafe.fmap.notification.NotificationDispatcher
import dev.nautchkafe.fmap.notification.NotificationMessageKey
import net.kyori.adventure.audience.Audience
import org.bukkit.entity.Player

typealias NotificationMessage = String

/**
 * Dispatches a notification to the player using the provided NotificationDispatcher.
 *
 * This extension function allows a Player to send a notification using the specified
 * dispatcher and notification key.
 *
 * @param dispatcher the NotificationDispatcher responsible for publishing notifications
 * @param notificationKey the key identifying the type of notification to be sent
 * @param message the content of the notification message
 */
fun Player.dispatchNotification(
    dispatcher: NotificationDispatcher,
    notificationKey: NotificationMessageKey,
    message: NotificationMessage
) = dispatcher.publishNotification(this, notificationKey, message)

/**
 * Broadcasts a notification message to all available audiences using the NotificationDispatcher.
 *
 * This extension function simplifies the process of sending a broadcast message
 * by using the dispatcher directly.
 *
 * @param contentMessage the content of the message to be broadcasted
 */
fun NotificationDispatcher.broadcastNotification(contentMessage: String) = this.broadcast(contentMessage)

/**
 * Dispatches a notification to the audience using the provided NotificationDispatcher.
 *
 * This extension function allows an Audience to receive a notification message,
 * which is created and sent using the NotificationDispatcher.
 *
 * @param dispatcher the NotificationDispatcher responsible for publishing notifications
 * @param notificationKey the key identifying the type of notification to be sent
 * @param message the content of the notification message
 * @throws IllegalArgumentException if the notification could not be sent to the audience
 */
fun Audience.dispatchNotification(
    dispatcher: NotificationDispatcher,
    notificationKey: NotificationMessageKey,
    message: NotificationMessage
) {
    val notificationComponent = dispatcher.notificationFactory.createNotificationMessage(message)
    notificationComponent.peek { message ->
        dispatcher.publishNotification(this, notificationKey, message)
    }
        .onEmpty {
            throw IllegalArgumentException("Could not send notification to the audience.")
        }
}
