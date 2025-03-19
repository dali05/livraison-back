package com.example.livraison.infrastructure.kafka;

import com.example.livraison.domain.event.LivraisonCreneauEvent;
import com.example.livraison.domain.event.LivraisonModeEvent;
import com.example.livraison.domain.model.Livraison;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.verify;

class KafkaProducerAdapterTest {

    @Mock
    private KafkaTemplate<String, Livraison> livraisonKafkaTemplate;

    @Mock
    private KafkaTemplate<String, LivraisonModeEvent> modeKafkaTemplate;

    @Mock
    private KafkaTemplate<String, LivraisonCreneauEvent> creneauKafkaTemplate;

    private KafkaProducerAdapter kafkaProducerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        kafkaProducerAdapter = new KafkaProducerAdapter(livraisonKafkaTemplate, modeKafkaTemplate, creneauKafkaTemplate);
    }

    @Test
    void sendLivraisonEvent_ShouldSendMessageToKafka() {
        Livraison livraison = new Livraison();
        kafkaProducerAdapter.sendLivraisonEvent(livraison);
        verify(livraisonKafkaTemplate).send("livraison-events", livraison);
    }

    @Test
    void sendModeLivraisonEvent_ShouldSendMessageToKafka() {
        LivraisonModeEvent event = new LivraisonModeEvent();
        kafkaProducerAdapter.sendModeLivraisonEvent(event);
        verify(modeKafkaTemplate).send("mode-livraison-events", event);
    }

    @Test
    void sendCreneauReservationEvent_ShouldSendMessageToKafka() {
        LivraisonCreneauEvent event = new LivraisonCreneauEvent();
        kafkaProducerAdapter.sendCreneauReservationEvent(event);
        verify(creneauKafkaTemplate).send("creneau-reservation-events", event);
    }
}
