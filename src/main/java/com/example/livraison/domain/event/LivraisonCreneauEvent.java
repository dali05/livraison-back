package com.example.livraison.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivraisonCreneauEvent {
    private Long livraisonId;
    private Long creneauId;

}
