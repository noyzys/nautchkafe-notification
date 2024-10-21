package dev.nautchkafe.fmap.notification.translation;

import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.control.Option;

import java.util.Locale;

/**
 * Implements the TranslatorFlow interface to provide functionality
 * for managing and retrieving translations based on translation keys and locale.
 */
public final class TranslatorKeyMapper implements TranslatorFlow {

     // multimap :*
    private final Map<String, List<Translation>> translations;

    public TranslatorKeyMapper(final Map<String, List<Translation>> put) {
        this.translations = HashMap.empty();
    }

    /**
     * Translates a given key for the specified locale.
     *
     * @param key the translation key
     * @param locale the locale for which translation is desired
     * @return an Option containing the translated string if found, otherwise an empty Option
     */
    @Override
    public Option<String> translate(final String key, final Locale locale) {
        return findKey(key)
                .flatMap(translationsList -> translationsList
                        .find(translation -> translation.locale().equals(locale))
                        .map(Translation::translation));
    }

/*    public Option<String> translate(final String key, final Locale locale, final Map<String, String> placeholders) {
        return translate(key, locale).map(translation -> {
            return placeholders.toList()
                    .fold(toDoTuplet));
        });*/

    /**
     * Retrieves the list of translations associated with the given key.
     *
     * @param key the translation key
     * @return an Option containing the list of translations if key is found, otherwise an empty Option
     */
    private Option<List<Translation>> findKey(final String key) {
        return translations.get(key);
    }

    /**
     * Appends a new translation to the translation list for a specified key and locale.
     *
     * @param key the translation key
     * @param locale the locale of the new translation
     * @param translation the translation text
     */
    public void appendTranslation(final String key, final Locale locale, final String translation) {
        final Translation translationEntry = new Translation(locale, translation);
        final List<Translation> updatedTranslations = translations
                .get(key)
                .getOrElse(List.empty())
                .append(translationEntry);

        translations.put(key, updatedTranslations);
    }
}
