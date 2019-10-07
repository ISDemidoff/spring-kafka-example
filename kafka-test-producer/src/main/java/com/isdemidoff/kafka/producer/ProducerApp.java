package com.isdemidoff.kafka.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.isdemidoff.kafka.producer")
public class ProducerApp {
    public static void main(final String[] args) {
        //noinspection resource
        SpringApplication.run(ProducerApp.class, args);
    }
}
