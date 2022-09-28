package com.estudos.kafka.springbootkafka.producer.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.properties.bootstrap.servers}")
    private String bootstrapServerUrl;


    @Bean
    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServerUrl);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(JsonSerializer.TYPE_MAPPINGS, "Employee:com.estudos.kafka.springbootkafka.producer.dto.Employee");

//         confluent connection properties
//        props.put("sasl.mechanism", "PLAIN");
//        props.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule   required username='<apikey>'   password='<secret>';");
//        props.put("security.protocol", "SASL_SSL");


        return props;
    }

    @Bean
    public ProducerFactory<Integer, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }


    @Bean
    public KafkaTemplate<Integer, Object> kafkaTemplate(ProducerFactory<Integer, Object> producerFactory) {
        return new KafkaTemplate<Integer, Object>(producerFactory);
    }

}
