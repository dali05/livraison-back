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
@CrossOrigin("http://localhost:4200")
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
