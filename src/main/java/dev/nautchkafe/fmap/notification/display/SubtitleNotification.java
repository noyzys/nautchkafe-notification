package dev.nautchkafe.fmap.notification.display;

import dev.nautchkafe.fmap.notification.Notification;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;

/**
 * Represents a notification that only displays a subtitle to the audience.
 */
public final class SubtitleNotification implements Notification {

    private final Component subtitleMessage;

    public SubtitleNotification(final Component subtitleMessage) {
        this.subtitleMessage = subtitleMessage;
    }

    /**
     * Dispatches the subtitle message as a part of a title to a provided audience.
     *
     * @param audience the {@code Audience} to whom the subtitle will be shown, not null
     */
    @Override
    public void dispatch(final Audience audience) {
        audience.showTitle(Title.title(Component.empty(), subtitleMessage));
    }

    @Override
    public Component getMessage() {
        return subtitleMessage;
    }
}
