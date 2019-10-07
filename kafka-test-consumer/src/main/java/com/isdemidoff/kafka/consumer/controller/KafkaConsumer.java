package com.isdemidoff.kafka.consumer.controller;

import com.isdemidoff.kafka.common.SomeDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * The easiest kafka consumer.
 * Listener called when another message arrived to kafka.
 * <br/>
 * In this example consumer works also as message store (I know that is not right, but OK for example).
 */
@Slf4j
@Getter
@Component
public class KafkaConsumer implements MessageListener<String, SomeDto> {

    private final List<SomeDto> messages = new LinkedList<>();

//    @KafkaListener(topics = "isdemidoff", groupId = "test-group")
//    public void listen(final String message) {
//        log.info("Got message: {}", message);
//        messages.add(message);
//        log.debug("Saved message: {}", message);
//    }

    @Override
    @KafkaListener(topics = "testtopic")
    public void onMessage(final ConsumerRecord<String, SomeDto> data) {
        log.info("Got message: {}", data.value());
        messages.add(data.value());
        log.debug("Saved message: {}", data.value());
    }
}
