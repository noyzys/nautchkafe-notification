package dev.nautchkafe.fmap.notification.translation;

import java.util.Locale;

/**
 * Represents a translation text in a specific locale.
 * This immutable record stores both the locale of the translation and the translation text itself.
 */
public record Translation(Locale locale, String translation) {
}
