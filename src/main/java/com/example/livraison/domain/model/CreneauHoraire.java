package com.example.livraison.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreneauHoraire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime heure;
    private boolean disponible = true;

    @ManyToOne
    @JoinColumn(name = "jour_livraison_id")
    @JsonIgnore
    private JourLivraison jourLivraison;
}
