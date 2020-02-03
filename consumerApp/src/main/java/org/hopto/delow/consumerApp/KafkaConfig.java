package org.hopto.delow.consumerApp;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.support.converter.BytesJsonMessageConverter;
import org.springframework.kafka.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.converter.Jackson2JavaTypeMapper;
import org.springframework.kafka.support.converter.RecordMessageConverter;

@Configuration
@EnableKafka
public class KafkaConfig {

//    We need to setup type converter to use with kafka, specifically, we need to set message converter to json and
//    set trusted packages
    @Bean
    public RecordMessageConverter converter() {
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);
        typeMapper.addTrustedPackages("*");

        BytesJsonMessageConverter byteMessageConverter = new BytesJsonMessageConverter();
        byteMessageConverter.setTypeMapper(typeMapper);
        return byteMessageConverter;
    }

//    this will create topic if there isn't one
    @Bean
    public NewTopic myTopic() {
        return new NewTopic("myTopic", 1, (short) 1);
    }

}
