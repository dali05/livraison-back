package com.example.livraison.application.service;

import com.example.livraison.domain.event.LivraisonCreneauEvent;
import com.example.livraison.domain.event.LivraisonModeEvent;
import com.example.livraison.domain.model.*;
import com.example.livraison.domain.port.inbound.LivraisonServicePort;
import com.example.livraison.domain.port.outbound.KafkaProducerPort;
import com.example.livraison.infrastructure.persistence.CreneauHoraireRepository;
import com.example.livraison.infrastructure.persistence.LivraisonRepository;
import com.example.livraison.infrastructure.persistence.ProduitRepository;

import java.util.List;
import java.util.Optional;

public class LivraisonServiceImpl implements LivraisonServicePort {

    private final LivraisonRepository livraisonRepository;
    private final KafkaProducerPort kafkaProducerPort;
    private final ProduitRepository produitRepository;
    private final CreneauHoraireRepository creneauHoraireRepository;

    public LivraisonServiceImpl(LivraisonRepository livraisonRepository,
                                KafkaProducerPort kafkaProducerPort,
                                ProduitRepository produitRepository,
                                CreneauHoraireRepository creneauHoraireRepository) {
        this.livraisonRepository = livraisonRepository;
        this.kafkaProducerPort = kafkaProducerPort;
        this.produitRepository = produitRepository;
        this.creneauHoraireRepository = creneauHoraireRepository;
    }

    @Override
    public Livraison createLivraison(List<String> produits) {
        List<Produit> produitEntities = produitRepository.findAllByNomIn(produits);
        Livraison livraison = new Livraison();
        livraison.setProduits(produitEntities);
        Livraison saved = livraisonRepository.save(livraison);
        kafkaProducerPort.sendLivraisonEvent(saved);
        return saved;
    }

    @Override
    public Livraison choisirModeLivraison(Long id, String modeLivraison) {
        Livraison livraison = livraisonRepository.findById(id).orElseThrow(() -> new RuntimeException("Livraison non trouvée"));
        livraison.setModeLivraison(ModeLivraison.valueOf(modeLivraison));
        Livraison updated = livraisonRepository.save(livraison);

        kafkaProducerPort.sendModeLivraisonEvent(new LivraisonModeEvent(updated.getId(), modeLivraison));

        return updated;
    }

    @Override
    public Livraison choisirCreneauHoraire(Long id, CreneauHoraire creneauHoraire) {
        Livraison livraison = livraisonRepository.findById(id).orElseThrow(() -> new RuntimeException("Livraison non trouvée"));
        CreneauHoraire creneau = creneauHoraireRepository.findById(creneauHoraire.getId()).orElseThrow(() -> new RuntimeException("Créneau non trouvé"));

        creneau.setDisponible(false);
        creneauHoraireRepository.save(creneau);

        livraison.setCreneauHoraire(creneau);
        Livraison updated = livraisonRepository.save(livraison);

        kafkaProducerPort.sendCreneauReservationEvent(new LivraisonCreneauEvent(updated.getId(), creneau.getId()));

        return updated;
    }

    @Override
    public List<Livraison> getAllLivraisons() {
        return livraisonRepository.findAll();
    }

    @Override
    public Optional<Livraison> getLivraisonById(Long id) {
        return livraisonRepository.findById(id);
    }
}
