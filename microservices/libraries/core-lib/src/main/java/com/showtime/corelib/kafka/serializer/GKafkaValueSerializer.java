package com.showtime.corelib.kafka.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Vengatesan Nagarajan
 */
@Slf4j
public class GKafkaValueSerializer<V> implements Serializer<V> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {
    }

    @Override
    public byte[] serialize(String s, V data) {
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] serializedMessage = null;
        try {
            String message = objectMapper.writeValueAsString(data);
            serializedMessage = message.getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            log.error("##### parsing error for kafka messages by this class -> " + data.getClass().getName(), e);
        } catch (Exception e) {
            log.error("##### parsing error for kafka messages by this class -> " + data.getClass().getName(), e);
        }
        return serializedMessage;
    }

    @Override
    public void close() {
    }
}
