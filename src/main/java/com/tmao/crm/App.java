package com.tmao.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.tmao.crm"})
public class App {

    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }

}
