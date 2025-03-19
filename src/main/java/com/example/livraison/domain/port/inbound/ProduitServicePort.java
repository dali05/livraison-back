package com.example.livraison.domain.port.inbound;

import com.example.livraison.domain.model.Produit;

import java.util.List;

public interface ProduitServicePort {
    List<Produit> findAllProduits();
}
