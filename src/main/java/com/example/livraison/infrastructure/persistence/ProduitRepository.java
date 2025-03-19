package com.example.livraison.infrastructure.persistence;

import com.example.livraison.domain.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findAllByNomIn(List<String> noms);

}
