package com.example.livraison.infrastructure.web;

import com.example.livraison.application.service.ProduitServiceImpl;
import com.example.livraison.domain.model.Produit;
import com.example.livraison.interfaceweb.ProduitController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProduitControllerTest {

    @Mock private ProduitServiceImpl produitService;
    @InjectMocks private ProduitController produitController;

    @Test
    void testGetAllProduits() {
        when(produitService.findAllProduits()).thenReturn(List.of(new Produit(1L, "ProduitTest")));

        List<Produit> result = produitController.getAllProduits();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("ProduitTest", result.get(0).getNom());
        verify(produitService, times(1)).findAllProduits();
    }
}
