package com.example.livraison.infrastructure.persistence;

import com.example.livraison.domain.model.JourLivraison;
import com.example.livraison.domain.model.ModeLivraison;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
@Registered
public interface JourLivraisonRepository extends JpaRepository<JourLivraison, Long> {
    boolean existsByDateAndModeLivraison(LocalDate date, ModeLivraison modeLivraison);
    List<JourLivraison> findByModeLivraisonAndDateAfter(ModeLivraison modeLivraison, LocalDate date);
}
