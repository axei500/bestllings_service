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
            bestellingRepository.save(new Bestelling("Test@hotmail.com", LocalDateTime.now(), "0d5qdq1dq3"));
            bestellingRepository.save(new Bestelling("Een@een.be", LocalDateTime.now(), "0d5qdq1dq3"));
        }
        System.out.println(bestellingRepository.findBestellingByEmailContaining("Test@hotmail.com"));
    }

    @GetMapping("/Bestellingen/email/{email}")
    public List<Bestelling> getBooksByTitle(@PathVariable String email) {
        return bestellingRepository.findBestellingByEmailContaining(email);
    }

    @GetMapping("/Bestellingen/{fietsSerienummer}")
    public List<Bestelling> getBookByISBN(@PathVariable String fietsSerienummer) {
        return bestellingRepository.findBestellingByFietsSerienummerContaining(fietsSerienummer);
    }
}
