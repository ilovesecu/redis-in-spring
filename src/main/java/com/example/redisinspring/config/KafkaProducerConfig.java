package com.example.redisinspring.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.RoundRobinPartitioner;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    //https://velog.io/@ytytyt0427/Apache-Kafka-docker-compose%EB%A5%BC-%ED%86%B5%ED%95%9C-kafka-%EA%B5%AC%EC%B6%95-%EA%B3%BC-%ED%81%AC%EB%A1%A4%EB%A7%81-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EC%A0%84%EC%86%A1
    //https://khdscor.tistory.com/127#%EB%AA%A9%EC%B0%A81
    private static final String BOOTSTRAP_SERVER = "ilovepc.duckdns.org:9953, ilovepc.duckdns.org:9954, ilovepc.duckdns.org:9955";

    @Bean
    public ProducerFactory<String,String> producerFactory(){
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER); //Broker 주소 입력
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // 파티셔닝 전략 설정
        configProps.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, RoundRobinPartitioner.class.getName()); //메시지 분배전략 (https://khdscor.tistory.com/126)
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String,String> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
