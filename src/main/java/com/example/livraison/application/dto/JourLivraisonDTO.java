package com.example.livraison.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JourLivraisonDTO {
    private String date;
    private List<CreneauHoraireDTO> creneaux;

    }
