package com.example.bestllings_service.repository;

import com.example.bestllings_service.model.Bestelling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BestellingRepository extends JpaRepository<Bestelling, Integer> {
    Bestelling findBestellingByLeverancierBonNummer(String leverancierBonNummer);

    List<Bestelling> findBestellingByEmailContaining(String email);

    List<Bestelling> findBestellingByFietsSerienummerContaining(String fietsSerienummer);

    List<Bestelling> findBestellingByOnderdeelSerienummerContaining(String onderdeelSerienummer);

}
