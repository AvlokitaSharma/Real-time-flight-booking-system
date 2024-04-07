package com.flightbooking.consumer;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.util.Collections;
import java.util.Properties;

public class FlightBookingConsumer {
    private KafkaConsumer<String, String> consumer;
    
    public FlightBookingConsumer(String bootstrapServers, String groupId) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        
        consumer = new KafkaConsumer<>(properties);
    }
    
    public void consumeBookingRequests(String topic) {
        consumer.subscribe(Collections.singletonList(topic));
        
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            records.forEach(record -> {
                // Here, you would parse the booking details from record.value()
                // and insert them into the DB2 database.
                System.out.println("Received booking request: " + record.value());
                // Insert DB interaction logic here
            });
        }
    }
}
