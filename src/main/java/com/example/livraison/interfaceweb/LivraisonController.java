package com.example.livraison.interfaceweb;

import com.example.livraison.domain.model.CreneauHoraire;
import com.example.livraison.domain.model.Livraison;
import com.example.livraison.domain.port.inbound.LivraisonServicePort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livraisons")
@CrossOrigin("https://a1df7b5c0ee534e2294f4291f4537f8e-304100752.eu-north-1.elb.amazonaws.com")
public class LivraisonController {

    private final LivraisonServicePort service;

    public LivraisonController(LivraisonServicePort service) {
        this.service = service;
    }

    @PostMapping
    public Livraison createLivraison(@RequestBody List<String> produits) {
        return service.createLivraison(produits);
    }

    @PostMapping("/{id}/mode-livraison")
    public Livraison choisirModeLivraison(@PathVariable Long id, @RequestParam String modeLivraison) {
        return service.choisirModeLivraison(id, modeLivraison);
    }

    @PostMapping("/{id}/creneau-horaire")
    public Livraison choisirCreneauHoraire(@PathVariable Long id, @RequestBody CreneauHoraire creneau) {
        return service.choisirCreneauHoraire(id, creneau);
    }

    @GetMapping
    public List<Livraison> getAllLivraisons() {
        return service.getAllLivraisons();
    }


    @GetMapping("/{id}")
    public Optional<Livraison> getLivraisonById(@PathVariable Long id) {
        return service.getLivraisonById(id);
    }
}
