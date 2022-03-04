package com.oyvindh.chatbot;

import com.vaadin.flow.component.dependency.NpmPackage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import org.alicebot.ab.Bot;
import org.alicebot.ab.configuration.BotConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * The entry point of the Spring Boot application.
 **/
//This is the basic starter for a Spring Boot application except for the npm part.
@SpringBootApplication
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@NpmPackage(value = "line-awesome", version = "1.3.0")
public class Application extends SpringBootServletInitializer {
    // Here it initializes the Spring Boot server.
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    // Here we configure the bot that we've called Alice by giving it its path and name.
    // In Spring you use @Bean to tell something to run at a specific point.
    public Bot alice() {
        return new Bot(BotConfiguration.builder()
            .name("alice")
            .path("src/main/resources")
            .build()
        );
    }
    @Bean
    // here it makes a delay through this method, and we will call upon this method a different place.
    public ScheduledExecutorService executorService() {
        return Executors.newScheduledThreadPool(2);
    }
}
