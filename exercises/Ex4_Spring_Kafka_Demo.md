# Ex. 3 - Spring Kafka Demo
First, let's have a look at the source code for the spring kafka application provided in this repository.

## Produce and Consume some simple messages
Run the following test classes to produce some messages:
```
KafkaProducerTest#sendOneStringMessage
KafkaProducerTest#sendTenStringMessages
```
---
Run the simple consumer
- start consumer: what happens?
- change consumer group
- run and then restart the application
- produce some more messages
- change consumer group
- start application

---
Change the consumer auto offset reset configuration to earliest in the KafkaConsumerConfig class.
```
props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
```
---
Change the consumer group and restart the application

## Consumer Poll Batching

Enable consumer batching by adding the following code lines to the KafkaConsumerConfig class
```
props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);
factory.setBatchListener(true);
```
---
Run the application using the batching consumer

On the second run, let's try to add a short timeout after the producer test sends the first 5 messages. What happens?

*Hint: ConsumerConfig.MAX_POLL_RECORDS_CONGIG = 10*
```
  if(i == 5) {
     Thread.sleep(2_000);
  }
```
## Acknowledgements
First let's answer the following question: How did acks happen so far?

In the consumer config, set ack mode to manual

```
props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
```

Run the AckingKafkaConsumer with same consumer group a few times

---
Ack every nth message by adding the following code to the acking kafka listener:
```
  if(record.offset() % 10 == 0) {
    System.out.println("Acking offset " + record.offset());
    ack.acknowledge();
  }
```

## Object Serialization using JSON
Change the serializer in the KafkaProducerConfig class:
```
configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
```

The consumer will use the messageKafkaListenerContainerFactory (check it out)

---		
Run the application using the MessagePojoConsumer Kafka listener and produce some messages using the following test class:
```
KafkaProducerTest#sendJsonMessages
```
