package dev.nautchkafe.fmap.notification;

import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Option;

/**
 * A cache class that maps string keys to specific notification message keys using a static map.
 * It provides a method to fetch the corresponding NotificationMessageKey based on the input string key.
 */
public final class NotificationKeyMapper {

    private static final Map<String, NotificationMessageKey> NOTIFICATION_FACTORIES = HashMap.ofEntries(
            Map.entry(NotificationMessageKey.CHAT.name(), NotificationMessageKey.CHAT),
            Map.entry(NotificationMessageKey.ACTIONBAR.name(), NotificationMessageKey.ACTIONBAR),
            Map.entry(NotificationMessageKey.TITLE.name(), NotificationMessageKey.TITLE),
            Map.entry(NotificationMessageKey.SUBTITLE.name(), NotificationMessageKey.SUBTITLE)
    );

    /**
     * Finds and returns the corresponding NotificationMessageKey for the given key.
     *
     * @param key The key as a String to find its corresponding NotificationMessageKey.
     * @return Optional containing the matched NotificationMessageKey, or an empty Optional if the key does not exist.
     */
    Option<NotificationMessageKey> findNotification(final String key) {
        return NOTIFICATION_FACTORIES.get(key).toOption();
    }
}
