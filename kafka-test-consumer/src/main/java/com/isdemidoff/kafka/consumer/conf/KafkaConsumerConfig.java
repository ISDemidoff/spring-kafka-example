package com.isdemidoff.kafka.consumer.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isdemidoff.kafka.common.SomeDto;
import com.isdemidoff.kafka.common.SomeDtoMapper;
import com.isdemidoff.kafka.common.conf.KafkaCommonConfig;
import com.isdemidoff.kafka.common.conf.KafkaProps;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka consumer config.
 * Here we declare beans to connect to kafka cluster as reader.
 */
@EnableKafka
@EnableConfigurationProperties(KafkaProps.class)
@Configuration
@Import(KafkaCommonConfig.class)
@AllArgsConstructor
public class KafkaConsumerConfig {

    private final KafkaProps kafkaProps;

    @Bean
    public ConsumerFactory<String, SomeDto> consumerFactory(final ObjectMapper mapper) {

        final Map<String, Object> props = new HashMap<>();

        // Here we should place at least one reachable kafka endpoint, comma-separated
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProps.getBootstrapAddress());

        // Setting up groupId (please read more about groups and partitions in any kafka reference)
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProps.getGroupId());

        // Putting key deserializer (both serializer and deserializer added this way shuld have consistent no-args constructor)
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        final DefaultKafkaConsumerFactory<String, SomeDto> stringSomeDtoDefaultKafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(props);

        // If we could not provide no-args constructor, we can pass any prepared object after creating factory
        stringSomeDtoDefaultKafkaConsumerFactory.setValueDeserializer(new SomeDtoMapper(mapper));

        return stringSomeDtoDefaultKafkaConsumerFactory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SomeDto>
    kafkaListenerContainerFactory(final ConsumerFactory<String, SomeDto> consumerFactory) {

        final ConcurrentKafkaListenerContainerFactory<String, SomeDto> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

}
