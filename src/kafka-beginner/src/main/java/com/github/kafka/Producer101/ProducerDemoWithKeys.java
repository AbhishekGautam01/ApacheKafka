package com.github.kafka.Producer101;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerDemoWithKeys {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Logger logger = Logger.getLogger(ProducerDemoWithKeys.class.getName());
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
        for(int i = 0; i < 10; i++){
            String topic = "first_topic";
            String value = "hello world" + Integer.toString(i);
            String key = "id_" + Integer.toString(i);

            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, value);

            logger.info("Key:" + key);
            //send data
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    //executes everytime the record is successfully sent or an exception is thrown
                    if(e != null){
                        logger.info("Received new metadata \n" + "Topic:" + recordMetadata.topic() + "\n" +
                                        "Partition:" + recordMetadata.partition() + "\n" +
                                        "Offset:" + recordMetadata.offset() + "\n" +
                                "Timestamp:" + recordMetadata.timestamp() + "\n");
                    } else {
                        logger.log(Level.ALL, "Error while producing", e);
                    }
                }
            }).get();  //block the .send() to make it synchronous - don't do this in production!
        }


        producer.flush(); //flush all data
        producer.close(); //flush and close the producer
    }
}
