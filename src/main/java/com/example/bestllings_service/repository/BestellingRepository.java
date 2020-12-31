package com.example.bestllings_service.repository;

import com.example.bestllings_service.model.Bestelling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BestellingRepository extends JpaRepository<Bestelling, Integer> {
    Bestelling findBestellingByLeverancierBonNummer(String leverancierBonNummer);

    List<Bestelling> findBestellingByEmailContaining(String email);

    List<Bestelling> findBestellingByFietsMerkContaining(String fietsMerk);

    List<Bestelling> findBestellingByFietsModelContaining(String fietsModel);

    List<Bestelling> findBestellingByOnderdeelNaamContaining(String onderdeelNaam);

    List<Bestelling> findBestellingByOnderdeelMerkContaining(String onderdeelMerk);

}