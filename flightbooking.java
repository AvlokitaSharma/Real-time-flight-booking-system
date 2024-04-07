package com.flightbooking.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

public class FlightBookingProducer {
    private KafkaProducer<String, String> producer;
    
    public FlightBookingProducer(String bootstrapServers) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        
        producer = new KafkaProducer<>(properties);
    }
    
    public void sendBookingRequest(String topic, String bookingDetails) {
        producer.send(new ProducerRecord<>(topic, bookingDetails));
    }
}
