package com.example.bestllings_service.controller;

import com.example.bestllings_service.model.Bestelling;
import com.example.bestllings_service.repository.BestellingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            bestellingRepository.save(new Bestelling("gegvens1", "Test@hotmail.com", LocalDateTime.now(), 50, 20));
            bestellingRepository.save(new Bestelling("gegvens2", "Een@een.be", LocalDateTime.now(), 50, 10));
        }
        System.out.println(bestellingRepository.findBestellingByEmailContaining("Test@hotmail.com"));
    }

    @GetMapping("/bestellingen/email/{email}")
    public List<Bestelling> getBestelligenByEmail(@PathVariable String email) {
        return bestellingRepository.findBestellingByEmailContaining(email);
    }

    @GetMapping("/bestellingen/onderdeel/{onderdeelNaam}")
    public List<Bestelling> getBestellingenByOnderdeelNaam(@PathVariable String onderdeelNaam) {
        return bestellingRepository.findBestellingByOnderdeelNaamContaining(onderdeelNaam);
    }

    @GetMapping("/bestellingen/onderdeelMerk/{onderdeelMerk}")
    public List<Bestelling> getBestellingenByOnderdeelMerk(@PathVariable String onderdeelMerk) {
        return bestellingRepository.findBestellingByOnderdeelMerkContaining(onderdeelMerk);
    }

    @GetMapping("/bestellingen/fiets/{fietsMerk}")
    public List<Bestelling> getBestellingenByFietsMerk(@PathVariable String fietsMerk) {
        return bestellingRepository.findBestellingByFietsMerkContaining(fietsMerk);
    }

    @GetMapping("/bestellingen/fietsModel/{fietsModel}")
    public List<Bestelling> getBestellingenByFietsModel(@PathVariable String fietsModel) {
        return bestellingRepository.findBestellingByFietsModelContaining(fietsModel);
    }

    @GetMapping("/bestelling/{leverancierBonNummer}")
    public Bestelling getBestellingByleverancierBonNummer(@PathVariable String leverancierBonNummer) {
        return bestellingRepository.findBestellingByLeverancierBonNummer(leverancierBonNummer);
    }

    @PostMapping("/bestellingen")
    public Bestelling addBestelling(@RequestBody Bestelling bestelling) {

        bestellingRepository.save(bestelling);

        return bestelling;
    }

    @PutMapping("/bestellingen")
    public Bestelling updateBestelling(@RequestBody Bestelling updatedBestelling) {
        Bestelling retrievedBestelling = bestellingRepository.findBestellingByLeverancierBonNummer(updatedBestelling.getLeverancierBonNummer());

        //retrievedBestelling.setLeverancierBonNummer(updatedBestelling.getLeverancierBonNummer());
        retrievedBestelling.setFietsMerk(updatedBestelling.getFietsMerk());
        retrievedBestelling.setFietsModel(updatedBestelling.getFietsModel());
        retrievedBestelling.setOnderdeelMerk(updatedBestelling.getOnderdeelMerk());
        retrievedBestelling.setOnderdeelNaam(updatedBestelling.getOnderdeelNaam());
        //retrievedBestelling.setBestelDatum(updatedBestelling.getBestelDatum());
        retrievedBestelling.setVoorschot(updatedBestelling.getVoorschot());
        retrievedBestelling.setPrijs(updatedBestelling.getPrijs());
        retrievedBestelling.setEmail(updatedBestelling.getEmail());

        bestellingRepository.save(retrievedBestelling);

        return retrievedBestelling;
    }

    @DeleteMapping("/bestelling/{leverancierBonNummer}")
    public ResponseEntity deleteBestelling(@PathVariable String leverancierBonNummer) {
        Bestelling bestelling = bestellingRepository.findBestellingByLeverancierBonNummer(leverancierBonNummer);
        if (bestelling != null) {
            bestellingRepository.delete(bestelling);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
