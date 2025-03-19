package com.example.livraison.infrastructure.persistence;

import com.example.livraison.domain.model.CreneauHoraire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreneauHoraireRepository extends JpaRepository<CreneauHoraire, Long> {
}
