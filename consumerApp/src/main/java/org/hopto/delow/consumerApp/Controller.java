package org.hopto.delow.consumerApp;

import lombok.extern.slf4j.Slf4j;
import org.hopto.delow.kafkatest.domain.CreateMessage;
import org.hopto.delow.kafkatest.domain.UpdateMessage;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id = "myGroup", topics = { "myTopic"})
@Slf4j
public class Controller {

    @KafkaHandler
    public void foo(CreateMessage createMessage) {
        log.info("Received: " + createMessage);
    }

    @KafkaHandler
    public void bar(UpdateMessage updateMessage) {
        log.info("Received: " + updateMessage);
    }

}
