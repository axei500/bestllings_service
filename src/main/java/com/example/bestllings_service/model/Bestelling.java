package com.example.bestllings_service.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Bestelling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private Date bestel_datum;

    private String fiets_serienummer;
    //eventuel voor appart onderdelen te bestellen
    private String onderdeel_serienummer;

    public Bestelling() {

    }

    public Bestelling(String email,Date bestel_datum, String fiets_serienummer) {
        this.email = email;
        this.bestel_datum = bestel_datum;
        this.fiets_serienummer = fiets_serienummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBestel_datum() {
        return bestel_datum;
    }

    public void setBestel_datum(Date bestel_datum) {
        this.bestel_datum = bestel_datum;
    }

    public String getFiets_serienummer() {
        return fiets_serienummer;
    }

    public void setFiets_serienummer(String fiets_serienummer) {
        this.fiets_serienummer = fiets_serienummer;
    }

    public String getOnderdeel_serienummer() {
        return onderdeel_serienummer;
    }

    public void setOnderdeel_serienummer(String onderdeel_serienummer) {
        this.onderdeel_serienummer = onderdeel_serienummer;
    }
}
