package com.toolympus.inca.ircstream;

import java.util.List;
import java.util.Arrays;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class IrcListener extends ListenerAdapter {
        @Override
        public void onGenericMessage(GenericMessageEvent event) {
                System.out.println("====");
                System.out.println(event.getMessage());
                System.out.println("====");
                System.out.println("");
        }

        private String _name;
        private String _server;
        private List<String> _channels;

        public IrcListener(String name, String server, String channels) {
                _name = name;
                _server = server;
                _channels = Arrays.asList(channels.split(","));
        }

        public void start() throws Exception {
                Configuration configuration = new Configuration.Builder()
                                .setName(_name)
                                .addServer(_server)
                                .addAutoJoinChannels(_channels)
                                .addListener(this)
                                .buildConfiguration();

                PircBotX bot = new PircBotX(configuration);
                bot.startBot();
        }
}