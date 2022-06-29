package com.msg.demo.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

@Component
public class SimpleKafkaConsumer {

    //    @KafkaListener(topics = "string-topic", groupId = "group_1")
    public void listenStringTopicByMessage(String message) {
        System.out.println("Received Message: " + message);
    }

    //    @KafkaListener(topics = "string-topic", groupId = "group_6")
    public void listenStringTopicByMessage(ConsumerRecord record) {
        System.out.println("Topic: " + record.topic() + ", Offset: " + record.offset() + " value: " + record.value());
    }
}
