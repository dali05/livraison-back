package com.example.livraison.application.config;

import com.example.livraison.application.service.LivraisonServiceImpl;
import com.example.livraison.application.service.ProduitServiceImpl;
import com.example.livraison.domain.port.inbound.LivraisonServicePort;
import com.example.livraison.domain.port.inbound.ProduitServicePort;
import com.example.livraison.domain.port.outbound.KafkaProducerPort;
import com.example.livraison.infrastructure.persistence.LivraisonRepository;
import com.example.livraison.infrastructure.persistence.ProduitRepository;
import com.example.livraison.infrastructure.persistence.CreneauHoraireRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public LivraisonServicePort livraisonServicePort(LivraisonRepository repo,
                                                     KafkaProducerPort kafka,
                                                     ProduitRepository produitRepo,
                                                     CreneauHoraireRepository creneauRepo) {
        return new LivraisonServiceImpl(repo, kafka, produitRepo, creneauRepo);
    }

    @Bean
    public ProduitServicePort produitServicePort(ProduitRepository repo) {
        return new ProduitServiceImpl(repo);
    }
}
