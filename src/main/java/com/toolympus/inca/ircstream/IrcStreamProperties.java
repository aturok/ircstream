package com.toolympus.inca.ircstream;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Configuration properties for the telegram Source module.
 *
 * @author aturok
 */
@ConfigurationProperties("ircstream")
@Validated
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
     * The IRC account password of the bot
     */
    private String ircPassword;

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
    public String getIrcPassword() {
        return ircPassword;
    }

    public void setIrcPassword(String ircPassword) {
        this.ircPassword = ircPassword;
    }

    @NotBlank
    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

}
