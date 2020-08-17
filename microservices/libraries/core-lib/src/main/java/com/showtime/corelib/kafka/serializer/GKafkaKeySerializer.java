package com.showtime.corelib.kafka.serializer;

import org.apache.kafka.common.serialization.StringSerializer;

/**
 * @author Vengatesan Nagarajan
 */
public class GKafkaKeySerializer<K> {

    private K classType;

    public GKafkaKeySerializer(K key) {
        this.classType = key;
    }

    public String getSerializerBasedOnKey() {
        String serializerName = null;
        if (this.classType instanceof String) {
            serializerName = StringSerializer.class.getName();
        }
        if (serializerName == null) {
            throw new ClassCastException("####### Type of the Producer KeyClass is not Matching");
        }
        return serializerName;
    }
}
