package com.example.livraison.infrastructure.kafka;

import com.example.livraison.domain.event.CreneauHoraireEvent;
import com.example.livraison.domain.event.LivraisonCreneauEvent;
import com.example.livraison.domain.event.LivraisonModeEvent;
import com.example.livraison.domain.model.Livraison;
import com.example.livraison.domain.port.outbound.KafkaProducerPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerAdapter implements KafkaProducerPort {

    private final KafkaTemplate<String, Livraison> livraisonKafkaTemplate;
    private final KafkaTemplate<String, LivraisonModeEvent> modeKafkaTemplate;
    private final KafkaTemplate<String, LivraisonCreneauEvent> creneauKafkaTemplate;

    public KafkaProducerAdapter(
            KafkaTemplate<String, Livraison> livraisonKafkaTemplate,
            KafkaTemplate<String, LivraisonModeEvent> modeKafkaTemplate,
            KafkaTemplate<String, LivraisonCreneauEvent> creneauKafkaTemplate) {
        this.livraisonKafkaTemplate = livraisonKafkaTemplate;
        this.modeKafkaTemplate = modeKafkaTemplate;
        this.creneauKafkaTemplate = creneauKafkaTemplate;
    }

    @Override
    public void sendLivraisonEvent(Livraison livraison) {
        livraisonKafkaTemplate.send("livraison-events", livraison);
    }

    @Override
    public void sendModeLivraisonEvent(LivraisonModeEvent event) {
        modeKafkaTemplate.send("mode-livraison-events", event);
    }

    @Override
    public void sendCreneauReservationEvent(LivraisonCreneauEvent event) {
        creneauKafkaTemplate.send("creneau-reservation-events", event);
    }
}
