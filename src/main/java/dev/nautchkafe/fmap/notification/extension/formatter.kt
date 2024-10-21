import dev.nautchkafe.fmap.notification.NotificationFormatter

/**
 * Attempts to apply a custom formatting function to the content of the NotificationFormatter.
 *
 * This extension function allows for a flexible way to format the content using a
 * provided lambda expression.
 *
 * @param formatter a function that takes a String and returns a formatted String
 * @return a new instance of NotificationFormatter with the formatted content
 */
fun NotificationFormatter.attempt(formatter: (String) -> String): NotificationFormatter =
    this.formatter { content -> formatter(content) }

/**
 * Replaces a specified placeholder in the NotificationFormatter content with the given replacement.
 *
 * This extension function utilizes the existing placeholder method of NotificationFormatter
 * to replace the placeholder with the provided replacement string.
 *
 * @param placeholder the placeholder to be replaced
 * @param replacement the replacement string for the placeholder
 * @return a new instance of NotificationFormatter with updated content
 */
fun NotificationFormatter.placeholder(placeholder: String, replacement: String): NotificationFormatter =
    this.placeholder(placeholder, replacement)

/**
 * Replaces multiple placeholders in the NotificationFormatter content with their respective replacements.
 *
 * This extension function processes a vararg of Pair<String, String> to replace each
 * specified placeholder with its corresponding replacement, using a functional approach.
 *
 * @param replacements an array of pairs, where each pair contains a placeholder and its replacement
 * @return a new instance of NotificationFormatter with all specified placeholders replaced
 */
fun NotificationFormatter.withPlaceholders(vararg replacements: Pair<String, String>): NotificationFormatter =
    replacements.fold(this) { formatted, (placeholder, replacement) ->
        formatted.placeholder(placeholder, replacement)
    }

/**
 * Converts the content of the NotificationFormatter to uppercase.
 *
 * This extension function applies a formatting function to the content, resulting in
 * a new NotificationFormatter instance with the content in uppercase.
 *
 * @return a new instance of NotificationFormatter with content converted to uppercase
 */
fun NotificationFormatter.toUpperCase(): NotificationFormatter =
    this.formatter { content -> content.toUpperCase() }
