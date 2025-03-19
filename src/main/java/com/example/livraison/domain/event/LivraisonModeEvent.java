package com.example.livraison.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivraisonModeEvent {
    private Long livraisonId;
    private String modeLivraison;
}
