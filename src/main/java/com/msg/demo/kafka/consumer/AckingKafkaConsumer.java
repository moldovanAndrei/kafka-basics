package com.msg.demo.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class AckingKafkaConsumer {

//    @KafkaListener(topics = "string-topic", groupId = "ack_group_2")
    public void listenStringTopicByMessage(ConsumerRecord record, Acknowledgment ack) {
        System.out.println("Topic: " + record.topic() + ", Offset: " + record.offset() + " value: " + record.value());
        ack.acknowledge();
    }
}
