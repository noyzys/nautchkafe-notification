package dev.nautchkafe.fmap.notification.display;

import dev.nautchkafe.fmap.notification.Notification;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;

/**
 * The {@code TextNotification} class implements the {@code Notification} interface,
 * encapsulating a text message notification.
 */
public final class TextNotification implements Notification {

    private final Component textMessage;

    public TextNotification(final Component textMessage) {
        this.textMessage = textMessage;
    }

    /**
     * Dispatches this text notification to the specified audience.
     *
     * @param audience The audience to which the notification should be sent.
     */
    @Override
    public void dispatch(final Audience audience) {
        audience.sendMessage(textMessage);
    }

    @Override
    public Component getMessage() {
        return textMessage;
    }
}
