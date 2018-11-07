package com.toolympus.inca.ircstream;

public class Message {

    /**
     * Message ID
     */
    public String id;

    /**
     * Message timestamp, ISO date in UTC
     */
    public String date;

    /**
     * Predefined string, the same for all messages. Default: IRC
     */
    public String source;

    /**
     * Channel title
     */
    public String category;

    /**
     * Channel ID
     */
    public String channel_id;

    /**
     * Message sender id
     */
    public String author;

    /**
     * Message text
     */
    public String content;
}
