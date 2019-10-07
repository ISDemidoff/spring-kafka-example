package com.isdemidoff.kafka.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

/**
 * Generic dto with various fields, adjusted for fasterxml's jackson mapper.
 */
@Getter
@ToString
public class SomeDto {
    private final String message;
    private final Long timestamp;
    private final UUID uuid;

    @JsonCreator
    public SomeDto(
            @JsonProperty("message") final String message,
            @JsonProperty("timestamp") final Long timestamp,
            @JsonProperty("uuid") final UUID uuid
    ) {
        this.message = message;
        this.timestamp = timestamp;
        this.uuid = uuid;
    }
}
