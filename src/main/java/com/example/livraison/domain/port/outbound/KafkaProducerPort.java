package com.example.livraison.domain.port.outbound;

import com.example.livraison.domain.event.CreneauHoraireEvent;
import com.example.livraison.domain.event.LivraisonCreneauEvent;
import com.example.livraison.domain.event.LivraisonModeEvent;
import com.example.livraison.domain.model.Livraison;

public interface KafkaProducerPort {
    void sendLivraisonEvent(Livraison livraison);
    void sendModeLivraisonEvent(LivraisonModeEvent event);
    void sendCreneauReservationEvent(LivraisonCreneauEvent event);
}
