# Ex. 3 - Multi Broker Setup

## Clone and Configure Server Properties
File Names:
```
server-0.properties
server-1.properties
server-2.properties
```

Set the following parameters:
```
broker.id=[0|1|2]
log.dirs=kafka-[0|1|2]-logs
listeners=PLAINTEXT://:[9092|9093|9094]
```

## Start Zookeeper and the three Kafka Servers
Start Zookeeper and the three configured Kafka Servers

Windows:
```
> bin\windows\zookeeper-server-start.bat config\zookeeper.properties
> bin\windows\kafka-server-start.bat config\server-0.properties
> bin\windows\kafka-server-start.bat config\server-1.properties
> bin\windows\kafka-server-start.bat config\server-2.properties
```

WLS / Unix:
```
> bin/zookeeper-server-start.sh config/zookeeper.properties
> bin/kafka-server-start.sh config/server-0.properties
> bin/kafka-server-start.sh config/server-1.properties
> bin/kafka-server-start.sh config/server-2.properties
```

## Check Broker Topics

### List topics
Windows:
```
> bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092
> bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9093
> bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9094
```

WSL / Unix:
```
> bin/kafka-topics.sh --list --bootstrap-server localhost:9092
> bin/kafka-topics.sh --list --bootstrap-server localhost:9093
> bin/kafka-topics.sh --list --bootstrap-server localhost:9094
```

### Describe topics
Windows
```
> bin\windows\kafka-topics.bat --describe --topic XXX --bootstrap-server localhost:9092
```

WSL / Unix:
```
> bin/kafka-topics.sh --describe --topic XXX --bootstrap-server localhost:9092
```

## Create a new 'multi-broker' Topic

### Create Topics with replication factor > 1
Windows
```
> bin\windows\kafka-topics.bat --create --topic multi-broker-topic --bootstrap-server localhost:9092 --replication-factor 3 --partitions 3
```

WSL / Unix:
```
> bin/kafka-topics.sh --create --topic multi-broker-topic --bootstrap-server localhost:9092 --replication-factor 3 --partitions 3
```

### Describe new topic:
Windows
```
> bin\windows\kafka-topics.bat --describe --topic multi-broker-topic --bootstrap-server localhost:9092
```

WSL / Unix:
```
> bin/kafka-topics.sh --describe --topic multi-broker-topic --bootstrap-server localhost:9092
```