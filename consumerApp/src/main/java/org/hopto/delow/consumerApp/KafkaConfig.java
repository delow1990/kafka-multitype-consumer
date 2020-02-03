package org.hopto.delow.consumerApp;

import org.apache.kafka.clients.admin.NewTopic;
import org.hopto.delow.kafkatest.domain.CreateMessage;
import org.hopto.delow.kafkatest.domain.UpdateMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.converter.Jackson2JavaTypeMapper;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

//    We need to setup type converter to use with kafka
    @Bean
    public RecordMessageConverter converter() {
        StringJsonMessageConverter converter = new StringJsonMessageConverter();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);
        typeMapper.addTrustedPackages("org.hopto.delow");
        Map<String, Class<?>> mappings = getMappings();
        typeMapper.setIdClassMapping(mappings);
        converter.setTypeMapper(typeMapper);
        return converter;
    }

//    a little magic here. When spring sends messages via kafka, it saves value type information in kafka message
//    headers on producer side, so we need to tell consumer about these mappings
    private Map<String, Class<?>> getMappings() {
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("createMessage", CreateMessage.class);
        mappings.put("updateMessage", UpdateMessage.class);
        return mappings;
    }

//    this will create topic if there isn't one
    @Bean
    public NewTopic myTopic() {
        return new NewTopic("myTopic", 1, (short) 1);
    }

}
