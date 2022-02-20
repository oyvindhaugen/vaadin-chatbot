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
 */
@SpringBootApplication
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@NpmPackage(value = "line-awesome", version = "1.3.0")
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public Bot alice() {
        return new Bot(BotConfiguration.builder()
            .name("alice")
            .path("src/main/resources")
            .build()
        );
    }
    @Bean
    public ScheduledExecutorService executorService() {
        return Executors.newScheduledThreadPool(2);
    }
}
