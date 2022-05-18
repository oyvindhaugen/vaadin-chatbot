package com.oyvindh.chatbot;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class TerminateBean {
    public void CTX() {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE).run();

        int exitCode = SpringApplication.exit(ctx, new ExitCodeGenerator() {
            @Override
            public int getExitCode() {
                // return the error code
                return 0;
            }
        });
        System.exit(0);
    }
    public void Exiting() {System.exit(0);}
}