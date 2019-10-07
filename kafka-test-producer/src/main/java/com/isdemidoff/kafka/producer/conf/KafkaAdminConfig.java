package com.isdemidoff.kafka.producer.conf;

import com.isdemidoff.kafka.common.conf.KafkaProps;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

// I could not manage to create topics by a producer (so they're created by kafka itself).
// Not sure whether it should be done one way or another.
//@Configuration
@EnableConfigurationProperties(KafkaProps.class)
@AllArgsConstructor
public class KafkaAdminConfig {

    private final KafkaProps kafkaProps;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        final Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, this.kafkaProps.getBootstrapAddress());
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic isdemidoffTopic() {
        return new NewTopic("testtopic", 1, (short) 1);
    }
}
