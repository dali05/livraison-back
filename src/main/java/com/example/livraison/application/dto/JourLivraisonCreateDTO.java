package com.example.livraison.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor@AllArgsConstructor
public class JourLivraisonCreateDTO {
    private String date;
    private String modeLivraison;
    private List<String> creneaux;
}
