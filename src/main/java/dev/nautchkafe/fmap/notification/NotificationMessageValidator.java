package dev.nautchkafe.fmap.notification;

import io.vavr.control.Validation;

/**
 * This class provides a utility method to validate notification messages.
 * It ensures that the message content is neither null nor empty.
 */
final class NotificationMessageValidator {

    private NotificationMessageValidator() {
    }

    /**
     * Validates the content of the notification message.
     *
     * @param contentMessage The content of the message to validate.
     * @return a Validation object containing either the valid message or an exception.
     */
    public static Validation<IllegalArgumentException, String> validate(String contentMessage) {
        return contentMessage == null || contentMessage.trim().isEmpty()
                ? Validation.invalid(new IllegalArgumentException("Message cannot be null or empty"))
                : Validation.valid(contentMessage);
    }
}

