package com.isdemidoff.kafka.producer.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isdemidoff.kafka.common.SomeDtoMapper;
import com.isdemidoff.kafka.common.conf.KafkaCommonConfig;
import com.isdemidoff.kafka.common.conf.KafkaProps;
import com.isdemidoff.kafka.common.SomeDto;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka producer config: here we are declaring beans for connection with kafka cluster.
 */
@Configuration
@EnableConfigurationProperties(KafkaProps.class)
@Import(KafkaCommonConfig.class)
@AllArgsConstructor
public class KafkaProducerConfig {

    private final KafkaProps kafkaProps;

    @Bean
    public ProducerFactory<String, SomeDto> producerFactory(final ObjectMapper mapper) {

        final Map<String, Object> configProps = new HashMap<>();

        // Here we should place at least one reachable kafka endpoint, comma-separated
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProps.getBootstrapAddress());

        // Putting key serializer (both serializer and deserializer added this way shuld have consistent no-args constructor)
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        final DefaultKafkaProducerFactory<String, SomeDto> stringSomeDtoDefaultKafkaProducerFactory = new DefaultKafkaProducerFactory<>(configProps);

        // If we could not provide no-args constructor, we can pass any prepared object after creating factory
        stringSomeDtoDefaultKafkaProducerFactory.setValueSerializer(new SomeDtoMapper(mapper));

        return stringSomeDtoDefaultKafkaProducerFactory;
    }

    @Bean
    public KafkaTemplate<String, SomeDto> kafkaTemplate(final ProducerFactory<String, SomeDto> producerFactory) {
        // Pretty straightforward bean creation
        return new KafkaTemplate<>(producerFactory);
    }
}
