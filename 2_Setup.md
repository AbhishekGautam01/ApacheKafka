# Setup 

## Windows 
> ### Download Kafka and PATH Setup
> 1. JDK8 is required for this 
> 2. Download Apache Kafka (https://www.apache.org/dyn/closer.cgi?path=/kafka/3.0.0/kafka_2.12-3.0.0.tgz) and extract the archieve. Copy this directory and place it inside root(C:)
> 3. At the root level of your kafka bundle, create a new folder named *data* and inside this create 2 new folders named: *data* and *zookeeper*
> 4. Go to config folder(present inside kafka bundle) and edit zookeeper.properties and change the path to the path for zookeeper folder created in previous step. dont change / to \ in the path. 
> 5. __RUN Command__: zookeeper-server-start.bat config\zookeeper.properties ; If everything works well you get binding at **2181**
> 6. Inside kafka directory, go to config folder, edit server.properties logs.dir to point to drive:\kafka_2.13-3.0.0\data\kafka
> 7. Then open a new cmd and __RUN Commnad__: kafka-server-start.bat config\server.properties 

## UI TOOL 

Use this tool: https://www.conduktor.io/ 
If you are using this docker compose file with this project then you can add a new cluster as localhost:29092

## FAQS
> Zookeeper - java.net.BindException: Address already in use

Something is already occupying your port 2181. Figure out which application it is and stop it

> Kafka - org.apache.kafka.common.KafkaException: Socket server failed to bind to 0.0.0.0:9092: Address already in use.

Something is already occupying your port 9092. Figure out what it is and stop it.
Otherwise, if you really insist, you can change the Kafka port by adding the following line to server.properties

**example for port 9093**
listeners=PLAINTEXT://:9093
> My topics are losing their data after a while

This is how Kafka works. Data is only retained for 7 days.

> The topics list is disappearing

Make sure you have changed the Zookeeper dataDir=/path/to/data/zookeeper  , and Kafka log.dirs=/path/to/data/kafka

> I have launched Kafka in a VM or in the Cloud, and I can't produce to Kafka

If you can't produce to Kafka, it's possible you are using a VM and this can break the Kafka behaviour. Please look at the annex lectures for solutions of how to deal with that. I strongly recommend doing this tutorial using the Kafka binaries and localhost

