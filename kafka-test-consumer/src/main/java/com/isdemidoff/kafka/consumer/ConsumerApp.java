package com.isdemidoff.kafka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.isdemidoff.kafka.consumer")
public class ConsumerApp {
    public static void main(final String[] args) {
        //noinspection resource
        SpringApplication.run(ConsumerApp.class, args);
    }
}
