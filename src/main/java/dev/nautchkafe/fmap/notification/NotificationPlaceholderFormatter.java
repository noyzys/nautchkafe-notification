package dev.nautchkafe.fmap.notification;

/**
 * Functional interface for formatting notification placeholders.
 *
 * This interface defines a single method format that takes a context string
 * and returns a formatted string based on the provided context. The implementation
 * of this method should handle specific formatting logic applicable to the context
 * of notifications.
 */
@FunctionalInterface
interface NotificationPlaceholderFormatter {

    /**
     * Formats the given context string for use in notifications.
     *
     * @param context the context string to be formatted.
     * @return a formatted string based on the provided context.
     */
    String format(final String context);
}
