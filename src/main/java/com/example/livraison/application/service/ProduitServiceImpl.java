package com.example.livraison.application.service;

import com.example.livraison.domain.model.Produit;
import com.example.livraison.domain.port.inbound.ProduitServicePort;
import com.example.livraison.infrastructure.persistence.ProduitRepository;

import java.util.List;

public class ProduitServiceImpl implements ProduitServicePort {

    private final ProduitRepository produitRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }


    @Override
    public List<Produit> findAllProduits() {
        return produitRepository.findAll();
    }
}
