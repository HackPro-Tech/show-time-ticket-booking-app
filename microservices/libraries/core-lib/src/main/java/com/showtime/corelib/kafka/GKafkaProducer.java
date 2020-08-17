package com.showtime.corelib.kafka;

import com.showtime.corelib.constants.KafkaConstants;
import com.showtime.corelib.kafka.serializer.GKafkaKeySerializer;
import com.showtime.corelib.kafka.serializer.GKafkaValueSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * @author Vengatesan Nagarajan
 */
@Slf4j
public class GKafkaProducer<K, V> {

    private KafkaProducer<K, V> kafkaProducer;
    private ProducerRecord<K, V> producerRecord;

    // kafka properties
    private Properties setKafkaProperties(Properties kafkaProperties, K key) {
        GKafkaKeySerializer<K> gKafkaKeySerializer = new GKafkaKeySerializer<>(key);
        GKafkaValueSerializer<V> gKafkaValueSerializer = new GKafkaValueSerializer<>();
        kafkaProperties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getProperty(KafkaConstants.BOOTSTRAP_SERVERS.toString()));
        kafkaProperties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, gKafkaKeySerializer.getSerializerBasedOnKey());
        kafkaProperties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, gKafkaValueSerializer.getClass().getName());
        return kafkaProperties;
    }

    // create kafka setup
    private void createKafkaSetup(Properties properties, K key, V value) {
        kafkaProducer = new KafkaProducer<>(properties);
        producerRecord = new ProducerRecord<>(properties.getProperty(KafkaConstants.TOPIC_NAME.toString()), key, value);
    }

    // publish kafka messages
    public void publishMessage(Properties properties, K key, V value) {
        // set kafka properties
        Properties kafkaProperties = this.setKafkaProperties(properties, key);

        // create kafka setup
        this.createKafkaSetup(kafkaProperties, key, value);

        // publish message
        kafkaProducer.send(producerRecord, new Callback() {
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    log.info("{ " + "\n" + "Topic -> " + recordMetadata.topic() + "\n" +
                            "Partition -> " + recordMetadata.partition() + "\n" +
                            "Offset -> " + recordMetadata.offset() + "\n" +
                            "Timestamp -> " + recordMetadata.timestamp() + "\n" + " }");
                } else {
                    log.error("Error while publishing message in to kafka", e);
                }
                kafkaProducer.close();
            }
        });
    }

    // close kafka connection
    private void closeConnection() {
        kafkaProducer.close();
    }
}