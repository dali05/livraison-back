package com.example.livraison.infrastructure.config;

import com.example.livraison.domain.event.CreneauHoraireEvent;
import com.example.livraison.domain.event.LivraisonCreneauEvent;
import com.example.livraison.domain.event.LivraisonModeEvent;
import com.example.livraison.domain.model.Livraison;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
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
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers")
    private static String KAFKA_SERVER;

    @Bean
    public ProducerFactory<String, Livraison> livraisonProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Livraison> livraisonKafkaTemplate() {
        return new KafkaTemplate<>(livraisonProducerFactory());
    }

    @Bean
    public ProducerFactory<String, CreneauHoraireEvent> creneauProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, CreneauHoraireEvent> creneauKafkaTemplate() {
        return new KafkaTemplate<>(creneauProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, LivraisonModeEvent> modeLivraisonKafkaTemplate() {
        return new KafkaTemplate<>(producerFactoryForType(LivraisonModeEvent.class));
    }

    @Bean
    public KafkaTemplate<String, LivraisonCreneauEvent> creneauReservationKafkaTemplate() {
        return new KafkaTemplate<>(producerFactoryForType(LivraisonCreneauEvent.class));
    }

    private <T> ProducerFactory<String, T> producerFactoryForType(Class<T> type) {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
}
