package com.toolympus.inca.ircstream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ConfigurableApplicationContext;

@EnableBinding(Source.class)
@EnableConfigurationProperties(IrcStreamProperties.class)
@SpringBootApplication
public class IrcStreamApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IrcStreamApplication.class, args);

        try {
            context.getBean(IrcListener.class).start();
        }
        catch(Exception exc) {
            exc.printStackTrace();
        }
        finally {
            context.getBean(IrcListener.class).stop();
            context.close();
        }
    }
}
