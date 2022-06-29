package com.msg.demo.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessagePojoConsumer {

    @KafkaListener(topics = "message-topic", groupId = "group_1", containerFactory = "messageKafkaListenerContainerFactory")
    public void listenToObjectByRecord(ConsumerRecord record) {
        System.out.println("Topic: " + record.topic() + ", Offset: " + record.offset() + " value: " + record.value());
    }
}
