package com.example.livraison.infrastructure.web;

import com.example.livraison.application.dto.JourLivraisonDTO;
import com.example.livraison.domain.model.CreneauHoraire;
import com.example.livraison.domain.model.JourLivraison;
import com.example.livraison.domain.model.ModeLivraison;

import com.example.livraison.infrastructure.persistence.JourLivraisonRepository;
import com.example.livraison.interfaceweb.CreneauHoraireController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreneauHoraireControllerTest {

    @Mock private JourLivraisonRepository jourLivraisonRepository;
    @InjectMocks private CreneauHoraireController creneauHoraireController;

    @Test
    void testGetDatesDisponibles() {
        JourLivraison jour = new JourLivraison(1L, LocalDate.now().plusDays(3), ModeLivraison.DRIVE, List.of(
                new CreneauHoraire(1L, LocalTime.of(10, 0), true, null),
                new CreneauHoraire(2L, LocalTime.of(12, 0), true, null)
        ));

        when(jourLivraisonRepository.findByModeLivraisonAndDateAfter(eq(ModeLivraison.DRIVE), any()))
                .thenReturn(List.of(jour));

        List<JourLivraisonDTO> result = creneauHoraireController.getDatesDisponibles("DRIVE");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(2, result.get(0).getCreneaux().size());
        verify(jourLivraisonRepository, times(1)).findByModeLivraisonAndDateAfter(eq(ModeLivraison.DRIVE), any());
    }

    @Test
    void testGetDatesDisponibles_NoResults() {
        when(jourLivraisonRepository.findByModeLivraisonAndDateAfter(eq(ModeLivraison.DRIVE), any()))
                .thenReturn(List.of());

        List<JourLivraisonDTO> result = creneauHoraireController.getDatesDisponibles("DRIVE");

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(jourLivraisonRepository, times(1)).findByModeLivraisonAndDateAfter(eq(ModeLivraison.DRIVE), any());
    }
}
