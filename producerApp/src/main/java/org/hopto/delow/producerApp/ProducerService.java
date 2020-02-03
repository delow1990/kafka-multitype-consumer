package org.hopto.delow.producerApp;

import lombok.extern.slf4j.Slf4j;
import org.hopto.delow.kafkatest.domain.CreateMessage;
import org.hopto.delow.kafkatest.domain.UpdateMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerService {

    private final KafkaTemplate<Object, Object> template;
    private final String topic = "myTopic";

    public ProducerService(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") KafkaTemplate<Object, Object> template) {
        this.template = template;
    }

    public void sendCreateMessage(String text) {
        log.info("Sending message: {}", text);
        template.send(topic, new CreateMessage(text));
    }

    public void sendUpdateMessage(String text) {
        template.send(topic, new UpdateMessage(text));
    }
}
