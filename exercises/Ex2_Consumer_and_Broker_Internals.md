# Ex. 2 - Consumer and Broker Internals
## Cleanup
Let's delete our kafka and zookeeper logs
```
> rm -r kafka_2.12-2.6.0/kafka-logs
> rm -r kafka_2.12-2.6.0/zookeeper-logs
```
And add a new configuration to our config/server.properties file:
```
offsets.topic.num.partitions=24
```

Recreate the test topic:
```
> bin/kafka-topics.sh --create --topic test-topic --partitions 2 --replication-factor 1 --bootstrap-server localhost:9092
```
List and describe the test topic:
```
> bin/kafka-topics.sh --list --bootstrap-server localhost:9092
> bin/kafka-topics.sh --describe --topic test-topic --bootstrap-server localhost:9092
```

Check kafka log directory
- test-topic-0
- test-topic-1

## Produce some messages similar to Excercise 1
Start two new terminal windows and write some data into kafka
```
> bin/kafka-console-consumer.sh --topic test-topic --bootstrap-server localhost:9092
> bin/kafka-console-producer.sh --topic test-topic --bootstrap-server localhost:9092
```

## Play with the console consumer:
Kill Console Consumer
```
> ctrl + c
```
---
Restart Console Consumer:
```
> bin/kafka-console-consumer.sh --topic test-topic --bootstrap-server localhost:9092
```
---
Kill and restart console consumer with --from-beginning flag:
```
> bin/kafka-console-consumer.sh --topic test-topic --bootstrap-server localhost:9092 --from-beginning
```

## Check Kafka Log Directories:
Check kafka log dirs:
- Notice some new folders?
- Remember the offsets.topic.num.partitions parameter?

Check kafka topic logs:
```
> cd test-topic-0
> cat 000000...0.log

> cd test-topic-1
> cat 000000...0.log
```

Explore kafka topic logs using DumpLogSegments utility class:
```
> cd KAFKA_DIRECTORY
> bin/kafka-run-class.sh kafka.tools.DumpLogSegments --print-data-log --files kafka-logs/test-topic-0/00000000000000000000.log
> bin/kafka-run-class.sh kafka.tools.DumpLogSegments --print-data-log --files kafka-logs/test-topic-0/00000000000000000000.log | grep payload
```	