package com.example.livraison.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreneauHoraireEvent {
    private Long creneauId;
    private boolean disponible;
    private String message;
}
