package com.example.bestllings_service.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bestelling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String leverancierBonNummer;

    private String email;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime bestelDatum;
    private int voorschot;
    private int prijs;

    private String fietsSerienummer;
    //eventuel voor appart onderdelen te bestellen
    private String onderdeelSerienummer;

    public Bestelling() {

    }

    public Bestelling(String LeverancierBonNummer, String email, LocalDateTime bestelDatum, int prijs, int voorschot) {
        setLeverancierBonNummer(LeverancierBonNummer);
        setEmail(email);
        setBestelDatum(bestelDatum);
        setPrijs(prijs);
        setVoorschot(voorschot);
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

    public String getLeverancierBonNummer() {
        return leverancierBonNummer;
    }

    public void setLeverancierBonNummer(String leverancierBonNummer) {
        this.leverancierBonNummer = leverancierBonNummer;
    }
}
