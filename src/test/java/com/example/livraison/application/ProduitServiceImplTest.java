package com.example.livraison.application;

import com.example.livraison.application.service.ProduitServiceImpl;
import com.example.livraison.domain.model.Produit;
import com.example.livraison.infrastructure.persistence.ProduitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProduitServiceImplTest {

    @Mock private ProduitRepository produitRepository;
    @InjectMocks private ProduitServiceImpl produitService;

    private Produit produit;

    @BeforeEach
    void setUp() {
        produit = new Produit(1L, "ProduitTest");
    }

    @Test
    void testFindAllProduits() {
        when(produitRepository.findAll()).thenReturn(List.of(produit));

        List<Produit> result = produitService.findAllProduits();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("ProduitTest", result.get(0).getNom());
        verify(produitRepository, times(1)).findAll();
    }
}
