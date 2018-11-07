package com.toolympus.inca.ircstream;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.toolympus.inca.ircstream.IrcStreamProperties;

@EnableBinding(Source.class)
@EnableConfigurationProperties(IrcStreamProperties.class)
@SpringBootApplication
public class IrcStreamApplication {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(IrcStreamApplication.class, args);

        try {
            context.getBean(IrcListener.class).start();
        } catch(IllegalArgumentException exc) {
            exc.printStackTrace();
            context.close();
        }
    }
}
