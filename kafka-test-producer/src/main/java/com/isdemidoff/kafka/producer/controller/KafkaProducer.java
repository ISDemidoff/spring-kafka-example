package com.isdemidoff.kafka.producer.controller;

import com.isdemidoff.kafka.common.SomeDto;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;

/**
 * The easiest message sender.
 * To send a message, you should pass topic and object you sending.
 */
@Configuration
@AllArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, SomeDto> kafkaTemplate;

    public void sendMsg(final String msg) {
        kafkaTemplate.send(
                "testtopic",
                new SomeDto(msg, System.currentTimeMillis(), UUID.randomUUID()));
    }

}
