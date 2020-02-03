package org.hopto.delow.producerApp;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@EnableKafka
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaConfig {

    private final KafkaProperties properties;

    public KafkaConfig(KafkaProperties properties) {
        this.properties = properties;
    }

//    By default, spring kafka uses string serializer to serialize message payload. This method changes that.
    @Bean
    public ProducerFactory<?, ?> kafkaProducerFactory() {
        DefaultKafkaProducerFactory<?, ?> factory = new DefaultKafkaProducerFactory<>(
                this.properties.buildProducerProperties(),
                StringSerializer::new,
                JsonSerializer::new); // <- set different serializer
        String transactionIdPrefix = this.properties.getProducer().getTransactionIdPrefix();
        if (transactionIdPrefix != null) {
            factory.setTransactionIdPrefix(transactionIdPrefix);
        }
        return factory;
    }

//    this will create topic if there isn't one
    @Bean
    public NewTopic myTopic() {
        return new NewTopic("myTopic", 1, (short) 1);
    }

}
