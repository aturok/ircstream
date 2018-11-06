package com.toolympus.inca.ircstream;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.toolympus.inca.ircstream.Message;
import com.toolympus.inca.ircstream.IrcStreamProperties;

@EnableBinding(Source.class)
@EnableConfigurationProperties(IrcStreamProperties.class)
@SpringBootApplication
public class IrcStreamApplication {

    @Autowired
    private IrcStreamProperties properties;

    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
    public MessageSource<Message> timeMessageSource() {

        return () -> {
            Message msg = new Message();
            msg.id = "124";
            msg.date = "2018-10-16";
            msg.category = properties.getChannels();
            msg.content = "message";

            ObjectMapper mapper = new ObjectMapper();
            return MessageBuilder.withPayload(msg).build();
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(IrcStreamApplication.class, args);
    }
}
