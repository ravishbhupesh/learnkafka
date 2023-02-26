# Kafka Samples

This project contains the sample application for Apache Kafka.

This samples run against a local cluster of Kafka with 3 brokers. Following are the settings for the local Kafka Cluster.

Kafka Version : kafka_2.13-3.4.0

To run local, just extract the above archive file and run the following commands.

1. Run ZooKeeper -> `bin\windows\zookeeper-server-start.bat config\zookeeper.properties`
2. Run Server 0 -> `bin\windows\kafka-server-start.bat config\server-0.properties`
3. Run Server 1 -> `bin\windows\kafka-server-start.bat config\server-1.properties`
4. Run Server 2 -> `bin\windows\kafka-server-start.bat config\server-2.properties`

  _ server-X.properties are the copies of the default server.properties file _
  
  _ values of the "broker.id" and "listeners" are changed accordingly _
  
  _ broker.id has the value 0/1/2 for server-0/1/2.properties files _
  
  _ listeners values have been changed to different port 9092/9192/9292 for server-0/1/2.properties files _

** Versions **

Spring Boot 2.7.9
Java 8
Kafka Clients managed as part of Spring Boot

- kafka-samples-producer
- kafka-samples-consumer

## kafka-samples-producer

Sample application to send messages to a topic

## kafka-samples-consumer