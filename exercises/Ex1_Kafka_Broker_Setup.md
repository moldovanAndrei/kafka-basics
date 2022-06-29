# Ex. 1 - Local Kafka Broker Setup

## Download Kafka

Official Kafka binaries can be found here:

https://kafka.apache.org/downloads

For todays excercises we can use Kafka 2.x.x provided in this github account:

https://github.com/moldovanAndrei/kafka-basics/tree/main/lib

## Configure Log Directories for Kafka and Zookeeper

Set the dataDir Zookeeper property in kafka_2.12-2.6.0/config/zookeeper.properties:
```
dataDir=zookeeper-logs
```

Set the log.dirs Kafka server property in kafka_2.12-2.6.0/config/server.properties:
```
log.dirs=kafka-logs
```

## Start Zookeeper and Kafka
Using two terminal instances of your choice, run the following commands from your kafka installation folder (../kafka_2.12-2.6.9)

Use .bat files for Windows:
```
> bin\windows\zookeeper-server-start.bat config\zookeeper.properties
> bin\windows\kafka-server-start.bat config\server.properties
```

Use .sh scripts for WLS / Unix:
```
> bin/zookeeper-server-start.sh config/zookeeper.properties
> bin/kafka-server-start.sh config/server.properties
```

## Create Topics
Open a new terminat and check that there are no topics present:

Windows:
```
> bin\windows\kafka-topics.bat --list --bootsrap-server localhost:9092
```

WSL / Unix:
```
> bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```
---
Let's create a new topic called test-topic, first with replication factor 3, then with replication factor  1. We will assign 2 partitions for this topic:

Windows:
```
> bin\windows\kafka-topics.sh --create --topic test-topic --partitions 2 --replication-factor 3 --bootstrap-server localhost:9092
> bin\windows\kafka-topics.sh --create --topic test-topic --partitions 2 --replication-factor 1 --bootstrap-server localhost:9092
```

WSL / Unix
```
> bin/kafka-topics.sh --create --topic test-topic --partitions 2 --replication-factor 3 --bootstrap-server localhost:9092
> bin/kafka-topics.sh --create --topic test-topic --partitions 2 --replication-factor 1 --bootstrap-server localhost:9092

```
---
Let's check our newly created topic:

Windows:
```
> bin\windows\kafka-topics.sh --list --bootstrap-server localhost:9092
> bin\windows\kafka-topics.sh --describe --topic test-topic --bootstrap-server localhost:9092
```

WSL / Unix:
```
> bin/kafka-topics.sh --list --bootstrap-server localhost:9092
> bin/kafka-topics.sh --describe --topic test-topic --bootstrap-server localhost:9092
```

## Produce and Consume Messages
And finally, let's produce and consume some simple string messages using two different terminals:

Windows:
```
> bin\windows\kafka-console-consumer.sh --topic test-topic --bootstrap-server localhost:9092
> bin\windows\kafka-console-producer.sh --topic test-topic --bootstrap-server localhost:9092
```

WSL / Unix:
```
> bin/kafka-console-consumer.sh --topic test-topic --bootstrap-server localhost:9092
> bin/kafka-console-producer.sh --topic test-topic --bootstrap-server localhost:9092
```