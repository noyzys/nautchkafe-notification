package dev.nautchkafe.fmap.notification.translation;

import io.vavr.control.Option;

import java.util.Locale;

/**
 * A functional interface for translating a given key into a localized string.
 *
 * Implementations of this interface should provide a method to perform translation
 * based on a specific key and locale.
 */
@FunctionalInterface
interface TranslatorFlow {

    /**
     * Translates the given key into a localized string for the specified locale.
     *
     * @param key The key to be translated. It should not be null.
     * @param locale The locale for which the translation is to be performed. It should not be null.
     * @return An {@link Optional} containing the translated string if the translation
     * is successful, otherwise an empty Optional.
     */
    Option<String> translate(final String key, final Locale locale);
}
