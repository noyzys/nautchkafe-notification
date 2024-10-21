package dev.nautchkafe.fmap.notification.display;

import dev.nautchkafe.fmap.notification.Notification;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;

/**
 * <p>A final class representing a title notification that can be dispatched to an audience.</p>
 *
 * <p>This class implements the {@link Notification} interface and is responsible for encapsulating
 * a title message which can be shown to an audience.</p>
 */
public final class TitleNotification implements Notification {

    private final Component titleMessage;

    public TitleNotification(final Component titleMessage) {
        this.titleMessage = titleMessage;
    }

    /**
     * Dispatches the title message to the specified audience.
     *
     * @param audience the {@link Audience} to which the title message will be dispatched
     */
    @Override
    public void dispatch(final Audience audience) {
        audience.showTitle(Title.title(titleMessage, Component.empty()));
    }

    @Override
    public Component getMessage() {
        return titleMessage;
    }
}
