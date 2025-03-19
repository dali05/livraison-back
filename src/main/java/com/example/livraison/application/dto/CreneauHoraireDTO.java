package com.example.livraison.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreneauHoraireDTO {
    private Long id;
    private String heure;
    private boolean disponible;
}
