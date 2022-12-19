package com.msg.demo.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.stereotype.Component;

@Component
public class BatchKafkaConsumer {

//    @KafkaListener(topics = "string-topic", groupId = "batch_group_1")
    public void listenStringTopicByList(ConsumerRecords<String, String> records) {
        System.out.println("Found " + records.count() + " records");
        records.forEach(record -> {
            System.out.println("Topic: " + record.topic() + ", Offset: " + record.offset() + " value: " + record.value());
        });
    }
}
