package com.github.kafka.Producer101;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerDemoWithCallBack {
    public static void main(String[] args) {

        Logger logger = Logger.getLogger(ProducerDemoWithCallBack.class.getName());
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
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                //executes everytime the record is successfully sent or an exception is throw
                if(e !=null){
                    logger.info("Received new metadata \n" + "Topic:" + recordMetadata.topic() + "\n" +
                                    "Partition:" + recordMetadata.partition() + "\n" +
                                    "Offset:" + recordMetadata.offset() + "\n" +
                                    "Timestamp:" + recordMetadata.timestamp() + "\n");
                } else {
                    logger.log(Level.ALL, "Error while producing", e);
                }
            }
        });

        producer.flush(); //flush all data
        producer.close(); //flush and close the producer
    }
}
