package com.msg.demo.kafka;


import com.msg.demo.kafka.consumer.AckingKafkaConsumer;
import com.msg.demo.kafka.consumer.BatchKafkaConsumer;
import com.msg.demo.kafka.consumer.MessagePojoConsumer;
import com.msg.demo.kafka.consumer.SimpleKafkaConsumer;
import com.msg.demo.kafka.data.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
public class KafkaProducerTest {

    private static final String STRING_TOPIC = "string-topic";
    private static final String MESSAGE_TOPIC = "message-topic";

    /**
     * Mock Consumer beans so they don't get wired while the tests are running.
     */
    @MockBean
    private SimpleKafkaConsumer mockSimpleConsumer;
    @MockBean
    private BatchKafkaConsumer mockBatchConsumer;
    @MockBean
    private AckingKafkaConsumer mockAckingConsumer;
    @MockBean
    private MessagePojoConsumer mockMessageConsumer;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Test
    public void sendOneStringMessage() {
        kafkaTemplate.send(STRING_TOPIC, "message");
    }

    @Test
    public void sendTenStringMessages() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            kafkaTemplate.send(STRING_TOPIC, "message_" + i);
        }
    }

    @Test
    public void sendJsonMessages() throws InterruptedException {
        Message m = new Message();
        for (int i = 0; i < 20; i++) {
            m.setMessage("JsonMessage_" + i);
            kafkaTemplate.send(MESSAGE_TOPIC, m);
            Thread.sleep(1000);
        }
    }
}
