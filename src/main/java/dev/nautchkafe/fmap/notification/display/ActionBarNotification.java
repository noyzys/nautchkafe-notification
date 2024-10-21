package dev.nautchkafe.fmap.notification.display;

import dev.nautchkafe.fmap.notification.Notification;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;

/**
 * A final class that implements the Notification interface to handle action bar notifications.
 */
public final class ActionBarNotification implements Notification {

    private final Component actionBarMessage;

    public ActionBarNotification(final Component actionBarMessage) {
        this.actionBarMessage = actionBarMessage;
    }

    /**
     * Sends the action bar message to the specified audience.
     *
     * @param audience The audience to receive the action bar message.
     */
    @Override
    public void dispatch(final Audience audience) {
        audience.sendActionBar(actionBarMessage);
    }

    @Override
    public Component getMessage() {
        return actionBarMessage;
    }
}
