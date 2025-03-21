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
@CrossOrigin("https://a1df7b5c0ee534e2294f4291f4537f8e-304100752.eu-north-1.elb.amazonaws.com")
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
