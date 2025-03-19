package com.example.livraison.domain.port.inbound;

import com.example.livraison.domain.model.CreneauHoraire;
import com.example.livraison.domain.model.Livraison;

import java.util.List;
import java.util.Optional;

public interface LivraisonServicePort {
    Livraison createLivraison(List<String> produits);
    Livraison choisirModeLivraison(Long id, String modeLivraison);
    Livraison choisirCreneauHoraire(Long id, CreneauHoraire creneauHoraire);
    List<Livraison> getAllLivraisons();
    Optional<Livraison> getLivraisonById(Long id);
}
