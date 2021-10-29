# Kafka CLI 

This can be used to creating topics, putting data and retrieving data. Before proceeding ahead make sure, Zoopkeeper and Kafka is running, instructions for same can be found in 2_Setup.md 

## Kafka Topics CLI 
* If you press **kafka-topics.bat** you will get list of operation you can do. 
*  You need to reference zookeeper as to create a topic we need to reference zookeeper. 
    > Create Topic Command: **kafka-topics.bat --bootstrap-server localhost:9092 --topic first_topic --create --partitions 3 --replication-factor 1**
    
    _NOTE_ you cannot create replication factor than the number of available brokers. 

    > List Partition Commnad: **kafka-topics.bat --zookeeper 127.0.0.1:2181 --list** 


