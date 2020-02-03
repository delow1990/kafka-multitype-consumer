package org.hopto.delow.consumerApp;

import lombok.extern.slf4j.Slf4j;
import org.hopto.delow.kafkatest.domain.CreateMessage;
import org.hopto.delow.kafkatest.domain.UpdateMessage;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
@KafkaListener(id = "myGroup", topics = { "myTopic"})
@Slf4j
public class Controller {

    @KafkaHandler
    public void foo(@Payload CreateMessage createMessage, @Headers Map<String, Object> headers) {
        log.info("Received: " + createMessage + "; with type header: " + getType(headers));
    }

    @KafkaHandler
    public void bar(@Payload UpdateMessage updateMessage, @Headers Map<String, Object> headers) {
        log.info("Received: " + updateMessage + "; with type header: " + getType(headers));
    }

    private String getType(Map<String, Object> headers) {
        Object typeId__ = headers.get("__TypeId__");
        String s = "";
        if (typeId__ instanceof byte[]) {
            byte[] byteArray = (byte[]) typeId__;
            s = new String(byteArray, StandardCharsets.UTF_8);
        }
        return s;
    }

}
