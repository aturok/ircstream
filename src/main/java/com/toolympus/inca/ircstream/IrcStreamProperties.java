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
     * The name
     */
    @Value("${spring.application.name:ircstream.source}")
    private String name;

    /**
     * The comma-separated list of channels to grab
     */
    private String channels;

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

}
