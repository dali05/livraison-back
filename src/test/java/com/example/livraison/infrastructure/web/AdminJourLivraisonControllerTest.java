package com.example.livraison.infrastructure.web;

import com.example.livraison.application.dto.JourLivraisonCreateDTO;
import com.example.livraison.infrastructure.persistence.CreneauHoraireRepository;
import com.example.livraison.infrastructure.persistence.JourLivraisonRepository;
import com.example.livraison.interfaceweb.AdminJourLivraisonController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminJourLivraisonControllerTest {

    @Mock private JourLivraisonRepository jourLivraisonRepository;
    @Mock private CreneauHoraireRepository creneauHoraireRepository;

    @InjectMocks private AdminJourLivraisonController adminJourLivraisonController;

    @Test
    void testCreateJourLivraison() {
        JourLivraisonCreateDTO dto = new JourLivraisonCreateDTO("2025-04-01", "DELIVERY", List.of("10:00", "12:00"));

        when(jourLivraisonRepository.existsByDateAndModeLivraison(any(), any())).thenReturn(false);

        String response = adminJourLivraisonController.createJourLivraison(dto);

        assertNotNull(response);
        assertEquals("Jour et créneaux ajoutés avec succès !", response);
        verify(jourLivraisonRepository, times(1)).save(any());
        verify(creneauHoraireRepository, times(2)).save(any());
    }

    @Test
    void testCreateJourLivraison_AlreadyExists() {
        JourLivraisonCreateDTO dto = new JourLivraisonCreateDTO("2025-04-01", "DELIVERY", List.of("10:00", "12:00"));

        when(jourLivraisonRepository.existsByDateAndModeLivraison(any(), any())).thenReturn(true);

        Exception exception = assertThrows(RuntimeException.class, () -> adminJourLivraisonController.createJourLivraison(dto));
        assertEquals("Jour déjà existant !", exception.getMessage());

        verify(jourLivraisonRepository, never()).save(any());
        verify(creneauHoraireRepository, never()).save(any());
    }
}