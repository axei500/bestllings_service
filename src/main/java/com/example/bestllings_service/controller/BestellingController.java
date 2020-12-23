package com.example.bestllings_service.controller;

import com.example.bestllings_service.model.Bestelling;
import com.example.bestllings_service.repository.BestellingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class BestellingController {

    @Autowired
    private BestellingRepository bestellingRepository;

    @PostConstruct
    public void fillDB() {
        if (bestellingRepository.count() == 0) {
            bestellingRepository.save(new Bestelling("0d5qdq1dq3", "Test@hotmail.com", LocalDateTime.now()));
            bestellingRepository.save(new Bestelling("0d5qdq1dq3", "Een@een.be", LocalDateTime.now()));
        }
        System.out.println(bestellingRepository.findBestellingByEmailContaining("Test@hotmail.com"));
    }

    @GetMapping("/bestellingen/email/{email}")
    public List<Bestelling> getBestelligenByEmail(@PathVariable String email) {
        return bestellingRepository.findBestellingByEmailContaining(email);
    }

    @GetMapping("/bestellingen/onderdeel/{onderdeelSerienummer}")
    public List<Bestelling> getBestellingenByOnderdeelSerienummer(@PathVariable String onderdeelSerienummer) {
        return bestellingRepository.findBestellingByOnderdeelSerienummerContaining(onderdeelSerienummer);
    }

    @GetMapping("/bestellingen/fiets/{fietsSerienummer}")
    public List<Bestelling> getBestellingenByFietsNummer(@PathVariable String fietsSerienummer) {
        return bestellingRepository.findBestellingByFietsSerienummerContaining(fietsSerienummer);
    }

    @GetMapping("/bestelling/{leverancierBonNummer}")
    public Bestelling getBestellingByleverancierBonNummer(@PathVariable String leverancierBonNummer) {
        return bestellingRepository.findBestellingByLeverancierBonNummer(leverancierBonNummer);
    }
}
