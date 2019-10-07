package com.isdemidoff.kafka.producer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for test purposes. Here we can send any text payload with out message.
 * @see KafkaProducer
 */
@RestController("kafka")
@AllArgsConstructor
public class ProducerRestController {

    private final KafkaProducer kafkaProducer;

    @PostMapping
    public ResponseEntity<?> postSomething(@RequestParam("msg") final String msg) {
        kafkaProducer.sendMsg(msg);
        return ResponseEntity.ok(null);
    }

}
