package com.example.livraison;

import com.example.livraison.domain.model.*;
import com.example.livraison.infrastructure.persistence.CreneauHoraireRepository;
import com.example.livraison.infrastructure.persistence.JourLivraisonRepository;
import com.example.livraison.infrastructure.persistence.ProduitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
public class KataBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(KataBackendApplication.class, args);
    }
    @Bean
    CommandLineRunner initData(ProduitRepository produitRepo,
                               JourLivraisonRepository jourRepo,
                               CreneauHoraireRepository creneauRepo) {
        return args -> {

            if (produitRepo.count() == 0) {
                produitRepo.saveAll(List.of(
                        new Produit(null, "ProduitA"),
                        new Produit(null, "ProduitB"),
                        new Produit(null, "ProduitC"),
                        new Produit(null, "ProduitD")
                ));
            }




                JourLivraison driveDay = jourRepo.save(new JourLivraison(null, LocalDate.now().plusDays(8), ModeLivraison.DRIVE, null));
                creneauRepo.saveAll(List.of(
                        new CreneauHoraire(null, LocalTime.of(10, 0), true, driveDay),
                        new CreneauHoraire(null, LocalTime.of(12, 0), true, driveDay),
                        new CreneauHoraire(null, LocalTime.of(15, 0), true, driveDay)
                ));

                JourLivraison deliveryDay = jourRepo.save(new JourLivraison(null, LocalDate.now().plusDays(7), ModeLivraison.DELIVERY, null));
                creneauRepo.saveAll(List.of(
                        new CreneauHoraire(null, LocalTime.of(9, 0), true, deliveryDay),
                        new CreneauHoraire(null, LocalTime.of(14, 0), true, deliveryDay)
                ));

                JourLivraison deliveryToday = jourRepo.save(new JourLivraison(null, LocalDate.now().plusDays(6), ModeLivraison.DELIVERY_TODAY, null));
                creneauRepo.saveAll(List.of(
                        new CreneauHoraire(null, LocalTime.of(16, 0), true, deliveryToday),
                        new CreneauHoraire(null, LocalTime.of(17, 0), true, deliveryToday)
                ));

                JourLivraison deliveryAsap = jourRepo.save(new JourLivraison(null, LocalDate.now().plusDays(5), ModeLivraison.DELIVERY_ASAP, null));
                creneauRepo.saveAll(List.of(
                        new CreneauHoraire(null, LocalTime.of(18, 0), true, deliveryAsap),
                        new CreneauHoraire(null, LocalTime.of(19, 0), true, deliveryAsap)
                ));


        };
    }

}
