package com.example.livraison.application;

import com.example.livraison.application.service.LivraisonServiceImpl;
import com.example.livraison.domain.event.LivraisonCreneauEvent;
import com.example.livraison.domain.event.LivraisonModeEvent;
import com.example.livraison.domain.model.*;
import com.example.livraison.domain.port.outbound.KafkaProducerPort;
import com.example.livraison.infrastructure.persistence.CreneauHoraireRepository;
import com.example.livraison.infrastructure.persistence.LivraisonRepository;
import com.example.livraison.infrastructure.persistence.ProduitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivraisonServiceImplTest {

    @Mock private LivraisonRepository livraisonRepository;
    @Mock private ProduitRepository produitRepository;
    @Mock private CreneauHoraireRepository creneauHoraireRepository;
    @Mock private KafkaProducerPort kafkaProducerPort;

    @InjectMocks private LivraisonServiceImpl livraisonService;

    private Livraison livraison;
    private Produit produit;
    private CreneauHoraire creneauHoraire;

    @BeforeEach
    void setUp() {
        produit = new Produit(1L, "ProduitTest");
        livraison = new Livraison();
        livraison.setId(1L);
        creneauHoraire = new CreneauHoraire(1L, java.time.LocalTime.of(10, 0), true, null);
    }

    @Test
    void testCreateLivraison() {
        List<Produit> produitsList = List.of(produit);

        when(produitRepository.findAllByNomIn(List.of("ProduitTest"))).thenReturn(produitsList);

        Livraison savedLivraison = new Livraison();
        savedLivraison.setId(1L);
        savedLivraison.setProduits(produitsList);

        when(livraisonRepository.save(any(Livraison.class))).thenReturn(savedLivraison);

        Livraison result = livraisonService.createLivraison(List.of("ProduitTest"));

        assertNotNull(result);
        assertNotNull(result.getProduits());
        assertEquals(1, result.getProduits().size());
        verify(kafkaProducerPort, times(1)).sendLivraisonEvent(result);
    }


    @Test
    void testChoisirModeLivraison() {
        when(livraisonRepository.findById(1L)).thenReturn(Optional.of(livraison));
        when(livraisonRepository.save(any(Livraison.class))).thenReturn(livraison);

        Livraison result = livraisonService.choisirModeLivraison(1L, "DELIVERY");

        assertNotNull(result);
        assertEquals(ModeLivraison.DELIVERY, result.getModeLivraison());
        verify(kafkaProducerPort, times(1)).sendModeLivraisonEvent(any(LivraisonModeEvent.class));
    }

    @Test
    void testChoisirCreneauHoraire() {
        when(livraisonRepository.findById(1L)).thenReturn(Optional.of(livraison));
        when(creneauHoraireRepository.findById(1L)).thenReturn(Optional.of(creneauHoraire));
        when(livraisonRepository.save(any(Livraison.class))).thenReturn(livraison);

        Livraison result = livraisonService.choisirCreneauHoraire(1L, creneauHoraire);

        assertNotNull(result);
        assertFalse(creneauHoraire.isDisponible());
        verify(kafkaProducerPort, times(1)).sendCreneauReservationEvent(any(LivraisonCreneauEvent.class));
    }
}
