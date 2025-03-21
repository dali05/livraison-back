package com.example.livraison.interfaceweb;

import com.example.livraison.domain.model.JourLivraison;
import com.example.livraison.domain.model.ModeLivraison;
import com.example.livraison.application.dto.CreneauHoraireDTO;
import com.example.livraison.application.dto.JourLivraisonDTO;
import com.example.livraison.infrastructure.persistence.JourLivraisonRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/creneaux")
@CrossOrigin("https://a1df7b5c0ee534e2294f4291f4537f8e-304100752.eu-north-1.elb.amazonaws.com")
public class CreneauHoraireController {

    private final JourLivraisonRepository jourRepo;

    public CreneauHoraireController(JourLivraisonRepository jourRepo) {
        this.jourRepo = jourRepo;
    }

    @GetMapping("/dates-disponibles")
    public List<JourLivraisonDTO> getDatesDisponibles(@RequestParam String modeLivraison) {
        ModeLivraison mode = ModeLivraison.valueOf(modeLivraison);
        LocalDate today = LocalDate.now();

        List<JourLivraison> jourLivraisons = jourRepo.findByModeLivraisonAndDateAfter(mode, today);

        List<JourLivraisonDTO> list = jourLivraisons.stream().map(jour ->
                new JourLivraisonDTO(
                        jour.getDate().toString(),
                        jour.getCreneaux().stream()
                                .map(c -> new CreneauHoraireDTO(c.getId(), c.getHeure().toString(), c.isDisponible()))
                                .toList()
                )
        ).toList();
        return list;
    }
}
