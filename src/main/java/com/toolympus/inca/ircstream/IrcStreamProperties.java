package com.toolympus.inca.ircstream;

import org.hibernate.validator.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for the telegram Source module.
 *
 * @author aturok
 */
@ConfigurationProperties("ircstream")
public class IrcStreamProperties {

    /**
     * The IRC server
     */
    private String ircServer;

    /**
     * The IRC nickname of the bot
     */
    private String ircNickname;

    /**
     * The comma-separated list of channels to grab
     */
    private String channels;

    @NotBlank
    public String getIrcServer() {
        return ircServer;
    }

    public void setIrcServer(String ircServer) {
        this.ircServer = ircServer;
    }

    @NotBlank
    public String getIrcNickname() {
        return ircNickname;
    }

    public void setIrcNickname(String ircNickname) {
        this.ircNickname = ircNickname;
    }

    @NotBlank
    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

}
