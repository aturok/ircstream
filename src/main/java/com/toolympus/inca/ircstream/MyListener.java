package com.toolympus.inca.ircstream;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class MyListener extends ListenerAdapter {
        @Override
        public void onGenericMessage(GenericMessageEvent event) {
                System.out.println(event.getMessage());
        }

        public static void main(String[] args) throws Exception {
                Configuration configuration = new Configuration.Builder()
                                .setName("ircsource")
                                .addServer("irc.rizon.net")
                                .addAutoJoinChannel("#aturoktest")
                                .addListener(new MyListener())
                                .buildConfiguration();

                PircBotX bot = new PircBotX(configuration);
                bot.startBot();
        }
}