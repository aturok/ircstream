package com.toolympus.inca.ircstream;

import java.util.List;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class IrcListener extends ListenerAdapter {

        @Autowired
        private IrcStreamProperties properties;

        @Override
        public void onGenericMessage(GenericMessageEvent event) {
                Message msg = new Message();
                msg.id = "124";
                msg.date = "2018-10-16";
                msg.category = "";
                msg.content = event.getMessage();

                System.out.println("got:: " + event.getMessage());

                source.output().send(MessageBuilder.withPayload(msg).build());
        }

        private Source source;

        public void start() throws Exception {
                System.out.println("starting up");
                Configuration configuration = new Configuration.Builder()
                                .setName(properties.getIrcNickname())
                                .addServer(properties.getIrcServer())
                                .addAutoJoinChannels(Arrays.asList(properties.getChannels()))
                                .addListener(this)
                                .buildConfiguration();

                PircBotX bot = new PircBotX(configuration);
                bot.startBot();
        }

        @Autowired
        public IrcListener(Source source) {
                this.source = source;
        }
}