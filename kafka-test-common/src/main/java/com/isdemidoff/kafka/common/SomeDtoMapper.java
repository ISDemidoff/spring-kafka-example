package com.isdemidoff.kafka.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;
import java.util.Map;

/**
 * SomeDto mapper class for (de)serializing it with kafka producer/consumer.
 * <br/>
 * NB! Generic custom dto translator (mapper) should be created for any dto you want to transfer.
 */
@Slf4j
@AllArgsConstructor
public class SomeDtoMapper implements Serializer<SomeDto>, Deserializer<SomeDto> {

    private final ObjectMapper mapper;
    private static final byte[] ZERO_BYTES = new byte[0];

    @Override
    public SomeDto deserialize(final String topic, final byte[] data) {
        SomeDto result;
        try {
            result = mapper.readValue(data, SomeDto.class);
        } catch (final IOException e) {
            log.error("Could not read data from json in bytes: {}", data, e);
            result = null;
        }
        return result;
    }

    @Override
    public void configure(final Map<String, ?> configs, final boolean isKey) { }

    @Override
    public byte[] serialize(final String topic, final SomeDto data) {
        byte[] result;
        try {
            result = mapper.writeValueAsBytes(data);
        } catch (final JsonProcessingException e) {
            log.error("Could not write data as json in bytes: {}", data, e);
            result = ZERO_BYTES;
        }
        return result;
    }

    @Override
    public void close() { }
}
