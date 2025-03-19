package com.example.livraison.infrastructure.web;

import com.example.livraison.application.service.LivraisonServiceImpl;
import com.example.livraison.domain.model.Livraison;
import com.example.livraison.interfaceweb.LivraisonController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivraisonControllerTest {

    @Mock private LivraisonServiceImpl livraisonService;
    @InjectMocks private LivraisonController livraisonController;

    @Test
    void testGetAllLivraisons() {
        when(livraisonService.getAllLivraisons()).thenReturn(List.of(new Livraison()));

        List<Livraison> result = livraisonController.getAllLivraisons();

        assertFalse(result.isEmpty());
        verify(livraisonService, times(1)).getAllLivraisons();
    }
}
