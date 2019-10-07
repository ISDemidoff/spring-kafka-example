package com.isdemidoff.kafka.common.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Spring's great invention for configurations.
 * Of course these props should be separated in producer and consumer-specific.
 * <br/>
 * Little guide: https://www.baeldung.com/configuration-properties-in-spring-boot
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "kafka")
public class KafkaProps {
    private String bootstrapAddress;
    private String groupId;
}
