package com.example.livraison.interfaceweb;

import com.example.livraison.domain.model.CreneauHoraire;
import com.example.livraison.domain.model.JourLivraison;
import com.example.livraison.domain.model.ModeLivraison;
import com.example.livraison.application.dto.JourLivraisonCreateDTO;
import com.example.livraison.infrastructure.persistence.JourLivraisonRepository;
import com.example.livraison.infrastructure.persistence.CreneauHoraireRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/admin/jours")
@CrossOrigin("https://a1df7b5c0ee534e2294f4291f4537f8e-304100752.eu-north-1.elb.amazonaws.com")
public class AdminJourLivraisonController {

    private final JourLivraisonRepository jourRepo;
    private final CreneauHoraireRepository creneauRepo;

    public AdminJourLivraisonController(JourLivraisonRepository jourRepo, CreneauHoraireRepository creneauRepo) {
        this.jourRepo = jourRepo;
        this.creneauRepo = creneauRepo;
    }

    @PostMapping
    public String createJourLivraison(@RequestBody JourLivraisonCreateDTO dto) {
        LocalDate date = LocalDate.parse(dto.getDate());
        ModeLivraison mode = ModeLivraison.valueOf(dto.getModeLivraison());

        if (jourRepo.existsByDateAndModeLivraison(date, mode)) {
            throw new RuntimeException("Jour déjà existant !");
        }

        JourLivraison jour = new JourLivraison();
        jour.setDate(date);
        jour.setModeLivraison(mode);
        jour = jourRepo.save(jour);

        for (String h : dto.getCreneaux()) {
            creneauRepo.save(new CreneauHoraire(null, LocalTime.parse(h), true, jour));
        }

        return "Jour et créneaux ajoutés avec succès !";
    }
}
