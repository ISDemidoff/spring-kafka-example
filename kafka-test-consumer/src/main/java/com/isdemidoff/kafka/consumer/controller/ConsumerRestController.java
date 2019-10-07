package com.isdemidoff.kafka.consumer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Easy rest controller to see stored messages.
 */
@RestController("kafka")
@AllArgsConstructor
public class ConsumerRestController {

    private final KafkaConsumer kafkaConsumer;

    @GetMapping
    public ResponseEntity<?> getAllMessages() {
        return ResponseEntity.ok(kafkaConsumer.getMessages());
    }

}
