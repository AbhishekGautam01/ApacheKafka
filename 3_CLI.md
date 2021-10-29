# Kafka CLI 

This can be used to creating topics, putting data and retrieving data. Before proceeding ahead make sure, Zoopkeeper and Kafka is running, instructions for same can be found in 2_Setup.md 

## Kafka Topics CLI 
* If you press **kafka-topics.bat** you will get list of operation you can do. 
*  You need to reference zookeeper as to create a topic we need to reference zookeeper. 
    > Create Topic Command: **kafka-topics.bat --bootstrap-server localhost:9092 --topic first_topic --create --partitions 3 --replication-factor 1**
    
    _NOTE_ you cannot create replication factor than the number of available brokers. 

    > List Partition Commnad: **kafka-topics.bat --zookeeper 127.0.0.1:2181 --list** 

#### Commands: 
    kafka-topics --zookeeper 127.0.0.1:2181 --list 
    
    kafka-topics --zookeeper 127.0.0.1:2181 --topic first_topic --create

    kafka-topics --zookeeper 127.0.0.1:2181 --topic first_topic --create --partitions 3

    kafka-topics --zookeeper 127.0.0.1:2181 --topic first_topic --create --partitions 3 --replication-factor 2

    kafka-topics --zookeeper 127.0.0.1:2181 --topic first_topic --create --partitions 3 --replication-factor 1

    kafka-topics --zookeeper 127.0.0.1:2181 --list 

    kafka-topics --zookeeper 127.0.0.1:2181 --topic first_topic --describe

    kafka-topics --zookeeper 127.0.0.1:2181 --topic first_topic --delete

## Kafka Console Producers CLI 

#### Commands

##### producing
     kafka-console-producer --broker-list 127.0.0.1:9092 --topic first_topic 
     hello abhishek
     awesome course
     learning kafka
     just another message

##### producing with properties
    kafka-console-producer --broker-list 127.0.0.1:9092 --topic first_topic --producer-property acks=all
    some message that is acked
    just for fun
    fun learning!

##### producing to a non existing topic
    kafka-console-producer --broker-list 127.0.0.1:9092 --topic new_topic
    hello world!

##### our new topic only has 1 partition
    kafka-topics --zookeeper 127.0.0.1:2181 --list
    kafka-topics --zookeeper 127.0.0.1:2181 --topic new_topic --describe

##### edit config/server.properties
##### num.partitions=3

##### produce against a non existing topic again
    kafka-console-producer --broker-list 127.0.0.1:9092 --topic new_topic_2
    hello again!

##### this time our topic has 3 partitions
    kafka-topics --zookeeper 127.0.0.1:2181 --list
    kafka-topics --zookeeper 127.0.0.1:2181 --topic new_topic_2 --describe

##### overall, please create topics before producing to them!

# kafka-console-consumer 
#### Commands
##### consuming
    kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic first_topic

##### other terminal
    kafka-console-producer --broker-list 127.0.0.1:9092 --topic first_topic

##### consuming from beginning
    kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic first_topic --from-beginning

##### start one consumer
    kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic first_topic --group my-first-application

##### start one producer and start producing
    kafka-console-producer --broker-list 127.0.0.1:9092 --topic first_topic

##### start another consumer part of the same group. See messages being spread
    kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic first_topic --group my-first-application

##### start another consumer part of a different group from beginning
    kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic first_topic --group my-second-application --from-beginning

# kafka-consumer-groups

##### list consumer groups
    kafka-consumer-groups --bootstrap-server localhost:9092 --list
 
##### describe one specific group
    kafka-consumer-groups --bootstrap-server localhost:9092 --describe --group my-second-application

##### describe another group
    kafka-consumer-groups --bootstrap-server localhost:9092 --describe --group my-first-application

##### start a consumer
    kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic first_topic --group my-first-application

##### describe the group now
    kafka-consumer-groups --bootstrap-server localhost:9092 --describe --group my-first-application

##### describe a console consumer group (change the end number)
    kafka-consumer-groups --bootstrap-server localhost:9092 --describe --group console-consumer-10592

##### start a console consumer
    kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic first_topic --group my-first-application

##### describe the group again
    kafka-consumer-groups --bootstrap-server localhost:9092 --describe --group my-first-application

##### reset the offsets to the beginning of each partition
    kafka-consumer-groups --bootstrap-server localhost:9092 --group my-first-application --reset-offsets --to-earliest

##### execute flag is needed
    kafka-consumer-groups --bootstrap-server localhost:9092 --group my-first-application --reset-offsets --to-earliest --execute

##### topic flag is also needed
    kafka-consumer-groups --bootstrap-server localhost:9092 --group my-first-application --reset-offsets --to-earliest --execute --topic first_topic

##### consume from where the offsets have been reset
    kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic first_topic --group my-first-application

##### describe the group again
    kafka-consumer-groups --bootstrap-server localhost:9092 --describe --group my-first-application

##### documentatior for more options
    kafka-consumer-groups

##### shift offsets by 2 (forward) as another strategy
    kafka-consumer-groups --bootstrap-server localhost:9092 --group my-first-application --reset-offsets --shift-by 2 --execute --topic first_topic

##### shift offsets by 2 (backward) as another strategy
    kafka-consumer-groups --bootstrap-server localhost:9092 --group my-first-application --reset-offsets --shift-by -2 --execute --topic first_topic

##### consume again
    kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic first_topic --group my-first-application