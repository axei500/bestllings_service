package com.example.bestllings_service.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Bestelling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private LocalDateTime bestelDatum;
    private int voorschot;
    private int prijs;

    private String fietsSerienummer;
    //eventuel voor appart onderdelen te bestellen
    private String onderdeelSerienummer;

    public Bestelling() {

    }

    public Bestelling(String email, LocalDateTime bestelDatum, String fietsSerienummer) {
        this.email = email;
        this.bestelDatum = bestelDatum;
        this.fietsSerienummer = fietsSerienummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getBestelDatum() {
        return bestelDatum;
    }

    public void setBestelDatum(LocalDateTime bestel_datum) {
        this.bestelDatum = bestel_datum;
    }

    public String getFietsSerienummer() {
        return fietsSerienummer;
    }

    public void setFietsSerienummer(String fiets_serienummer) {
        this.fietsSerienummer = fiets_serienummer;
    }

    public String getOnderdeelSerienummer() {
        return onderdeelSerienummer;
    }

    public void setOnderdeelSerienummer(String onderdeel_serienummer) {
        this.onderdeelSerienummer = onderdeel_serienummer;
    }

    public int getVoorschot() {
        return voorschot;
    }

    public void setVoorschot(int voorschot) {
        this.voorschot = voorschot;
    }

    public int getPrijs() {
        return prijs;
    }

    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }
}
