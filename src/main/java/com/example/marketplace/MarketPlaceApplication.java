package com.example.marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MarketPlaceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MarketPlaceApplication.class, args);
        String port = context.getEnvironment().getProperty("server.port");
        System.out.println("http://localhost:" + port + "/");
    }
}
