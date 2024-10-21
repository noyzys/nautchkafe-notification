package dev.nautchkafe.fmap.notification;

import io.vavr.Function1;
import org.apache.commons.lang3.StringUtils;

/**
 * A class responsible for formatting notification messages with placeholders.
 */
public final class NotificationFormatter {

    private final String content;
    private final Function1<String, String> placeholderFunctor;

    public NotificationFormatter(final String content) {
        this.content = content;
        this.placeholderFunctor = Function1.identity();
    }

    /**
     * Replaces a placeholder in the content with a specified replacement.
     *
     * @param placeholder The placeholder to be replaced.
     * @param replacement The replacement for the placeholder.
     * @return A new instance of NotificationFormatter with updated content.
     */
    public NotificationFormatter placeholder(final String placeholder, final String replacement) {
        final Function1<String, String> newPlaceholder = toReplace -> StringUtils.replace(toReplace, placeholder, replacement);
        return new NotificationFormatter(newPlaceholder.apply(content));
    }

    /**
     * Applies a custom formatter.kt to the content.
     *
     * @param placeholderFormatter An implementation of a placeholder formatter.kt interface.
     * @return A new instance of NotificationFormatter with formatted content.
     */
    public NotificationFormatter tryFormatter(final NotificationPlaceholderFormatter placeholderFormatter) {
        return new NotificationFormatter(placeholderFormatter.format(content));
    }

    /**
     * Applies a general formatter.kt function to the content.
     *
     * @param formatter A function that takes a String and returns a formatted String.
     * @return A new instance of NotificationFormatter with formatted content.
     */
    public NotificationFormatter formatter(final Function1<String, String> formatter) {
        return new NotificationFormatter(formatter.apply(content));
    }

    /**
     * Applies all added placeholder modifications and returns the formatted content.
     *
     * @return The formatted message as a String.
     */
    public String format() {
        return placeholderFunctor.apply(content);
    }
}
