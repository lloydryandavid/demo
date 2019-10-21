package org.lrd.customerapi;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import java.util.Arrays;


@SpringBootApplication
@EnableJpaAuditing
public class CustomerApplication {

    private static ConfigurableApplicationContext ctx;

    public static void main(String[] args) {
        ctx = SpringApplication.run(CustomerApplication.class, args);
    }

    public static void stop(){
        ctx.stop();
    }

    public static boolean isRunning() {
        return ctx.isRunning();
    }

}