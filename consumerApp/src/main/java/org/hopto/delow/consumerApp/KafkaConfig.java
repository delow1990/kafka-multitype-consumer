package org.hopto.delow.consumerApp;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
public class KafkaConfig {

//    this will create topic if there isn't one
    @Bean
    public NewTopic myTopic() {
        return new NewTopic("myTopic", 1, (short) 1);
    }

}
