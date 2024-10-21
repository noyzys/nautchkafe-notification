package dev.nautchkafe.fmap.notification;

/**
 * The NotificationAudience interface extends the NotificationPublisher interface to
 * provide the capability of broadcasting messages to an audience.
 */
interface NotificationAudience extends NotificationPublisher {

    /**
     * Broadcasts a message to all audience members.
     *
     * @param contentMessage the message content to be broadcasted
     */
    void broadcast(final String contentMessage);
}
