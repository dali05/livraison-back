package com.example.livraison.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JourLivraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private ModeLivraison modeLivraison;

    @OneToMany(mappedBy = "jourLivraison", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CreneauHoraire> creneaux;

}
