package com.example.livraison.interfaceweb;

import com.example.livraison.domain.model.Produit;
import com.example.livraison.domain.port.inbound.ProduitServicePort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produits")
@CrossOrigin("http://localhost:4200")
public class ProduitController {

    private final ProduitServicePort service;

    public ProduitController(ProduitServicePort service) {
        this.service = service;
    }

    @GetMapping
    public List<Produit> getAllProduits() {
        return service.findAllProduits();
    }
}
