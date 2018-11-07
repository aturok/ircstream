package com.toolympus.inca.ircstream;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class IrcListener extends ListenerAdapter {
    private final String sourceLabel = "IRC";
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS");

    @Autowired
    private IrcStreamProperties properties;

    @Autowired
    private Source source;
    private PircBotX bot;
    
    public IrcListener() {
        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
    
    @Override
    public void onMessage(MessageEvent event) {
        Message msg = new Message();
        // This is a client-only ID, IRC doesn't have real message IDs yet - see https://ircv3.net/specs/extensions/message-ids.html
        msg.id = Long.toString(event.getId());
        msg.date = dateFormatter.format(new Date(event.getTimestamp()));
        msg.source = sourceLabel;
        msg.category = event.getChannel().getName();
        msg.channel_id = event.getChannel().getChannelId().toString();
        msg.author = event.getUser().getLogin();
        msg.content = event.getMessage();
        
        source.output().send(MessageBuilder.withPayload(msg).build());
    }
        
    /**
    * Configures and starts the IRC bot in a blocking manner.
    * @throws Exception
    */
    public void start() throws Exception {
        List<String> channels = Arrays.asList(properties.getChannels().split(","));
        channels.replaceAll((s) -> { return s.trim(); });
        channels.removeIf((s) -> s.isEmpty());
        
        if(channels.isEmpty())
        throw new IllegalArgumentException("IRC channels to listen not provided");
        
        Configuration configuration = new Configuration.Builder()
        .setName(properties.getIrcNickname())
        .addServer(properties.getIrcServer())
        .addAutoJoinChannels(channels)
        .addListener(this)
        .setAutoReconnect(true)
        .buildConfiguration();
        
        bot = new PircBotX(configuration);
        bot.startBot();
    }
    
    /**
    * Stops the IRC bot, eventually closing connection to server
    */
    public void stop() {
        bot.close();
    }
}