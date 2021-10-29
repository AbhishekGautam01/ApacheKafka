package com.github.kafka.Producer101;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerDemo {
    public static void main(String[] args) {
        String bootStrapServers = "127.0.0.1:29092";

        // create producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);

        // below two will let kafka know what kind of data we are sending to our kafka so
        // it can appropriately convert it to byte array
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //create the producer; Key to be a string and value is expected to a string as well
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        //create a producer record

        ProducerRecord<String, String> record = new ProducerRecord<String, String>("first_topic", "Hello World!");

        //send data
        producer.send(record);

        producer.flush(); //flush all data
        producer.close(); //flush and close the producer
    }
}
