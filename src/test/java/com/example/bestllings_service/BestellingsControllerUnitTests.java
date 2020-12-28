package com.example.bestllings_service;


import com.example.bestllings_service.model.Bestelling;
import com.example.bestllings_service.repository.BestellingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

public class BestellingsControllerUnitTests {

    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BestellingRepository bestellingRepository;

    //Singele get by leverancierbon
    @Test
    public void givenBestelling_whenGetBestellingByLeverancierBonNummer_thenReturnJsonBestelling() throws Exception {
        Bestelling bestelling1 = new Bestelling("0d5qdq1dq3", "Test@hotmail.com", LocalDateTime.now(), 50, 20);

        given(bestellingRepository.findBestellingByLeverancierBonNummer("0d5qdq1dq3")).willReturn(bestelling1);

        mockMvc.perform(get("/bestelling/{leverancierBonNummer}", "0d5qdq1dq3"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.leverancierBonNummer", is("0d5qdq1dq3")))
                .andExpect(jsonPath("$.email", is("Test@hotmail.com")))
                .andExpect(jsonPath("$.voorschot", is(20)))
                .andExpect(jsonPath("$.prijs", is(50)));
    }

    // multiple get by email
    @Test
    public void givenBestellingen_whenGetBestellingenByEmail_thenReturnJsonBestellingen() throws Exception {
        Bestelling bestelling1 = new Bestelling("0d5qdq1dq3", "Test@hotmail.com", LocalDateTime.now(), 50, 20);
        Bestelling bestelling2 = new Bestelling("test2", "Test@hotmail.com", LocalDateTime.now(), 1000, 300);

        List<Bestelling> bestellingList = new ArrayList<>();
        bestellingList.add(bestelling1);
        bestellingList.add(bestelling2);

        given(bestellingRepository.findBestellingByEmailContaining("Test@hotmail.com")).willReturn(bestellingList);

        mockMvc.perform(get("/bestellingen/email/{email}", "Test@hotmail.com"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].leverancierBonNummer", is("0d5qdq1dq3")))
                .andExpect(jsonPath("$[0].email", is("Test@hotmail.com")))
                .andExpect(jsonPath("$[0].voorschot", is(20)))
                .andExpect(jsonPath("$[0].prijs", is(50)))
                .andExpect(jsonPath("$[1].leverancierBonNummer", is("test2")))
                .andExpect(jsonPath("$[1].email", is("Test@hotmail.com")))
                .andExpect(jsonPath("$[1].voorschot", is(300)))
                .andExpect(jsonPath("$[1].prijs", is(1000)));
    }

    // Multiple get by onderdeelnummer
    @Test
    public void givenBestellingen_whenGetBestelligenByOnderdeelSeriNummer_thenReturnJsonBestelligen() throws Exception {
        Bestelling bestelling1 = new Bestelling("test1", "Test@hotmail.com", LocalDateTime.now(), 50, 20);
        Bestelling bestelling2 = new Bestelling("test2", "Test@hotmail.com", LocalDateTime.now(), 1000, 300);

        bestelling1.setOnderdeelSerienummer("test");
        bestelling2.setOnderdeelSerienummer("test");

        List<Bestelling> bestellingList = new ArrayList<>();
        bestellingList.add(bestelling1);
        bestellingList.add(bestelling2);

        given(bestellingRepository.findBestellingByOnderdeelSerienummerContaining("test")).willReturn(bestellingList);

        mockMvc.perform(get("/bestellingen/onderdeel/{onderdeelSerienummer}", "test"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].leverancierBonNummer", is("test1")))
                .andExpect(jsonPath("$[0].onderdeelSerienummer", is("test")))
                .andExpect(jsonPath("$[0].email", is("Test@hotmail.com")))
                .andExpect(jsonPath("$[0].voorschot", is(20)))
                .andExpect(jsonPath("$[0].prijs", is(50)))
                .andExpect(jsonPath("$[1].leverancierBonNummer", is("test2")))
                .andExpect(jsonPath("$[1].onderdeelSerienummer", is("test")))
                .andExpect(jsonPath("$[1].email", is("Test@hotmail.com")))
                .andExpect(jsonPath("$[1].voorschot", is(300)))
                .andExpect(jsonPath("$[1].prijs", is(1000)));
    }

    // Multiple get by fietsserienummer
    @Test
    public void givenBestellingen_whenGetBestelligenByFietsSeriNummer_thenReturnJsonBestelligen() throws Exception {
        Bestelling bestelling1 = new Bestelling("test1", "Test@hotmail.com", LocalDateTime.now(), 50, 20);
        Bestelling bestelling2 = new Bestelling("test2", "Test@hotmail.com", LocalDateTime.now(), 1000, 300);

        bestelling1.setFietsSerienummer("test");
        bestelling2.setFietsSerienummer("test");

        List<Bestelling> bestellingList = new ArrayList<>();
        bestellingList.add(bestelling1);
        bestellingList.add(bestelling2);

        given(bestellingRepository.findBestellingByFietsSerienummerContaining("test")).willReturn(bestellingList);

        mockMvc.perform(get("/bestellingen/fiets/{fietsSerienummer}", "test"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].leverancierBonNummer", is("test1")))
                .andExpect(jsonPath("$[0].fietsSerienummer", is("test")))
                .andExpect(jsonPath("$[0].email", is("Test@hotmail.com")))
                .andExpect(jsonPath("$[0].voorschot", is(20)))
                .andExpect(jsonPath("$[0].prijs", is(50)))
                .andExpect(jsonPath("$[1].leverancierBonNummer", is("test2")))
                .andExpect(jsonPath("$[1].fietsSerienummer", is("test")))
                .andExpect(jsonPath("$[1].email", is("Test@hotmail.com")))
                .andExpect(jsonPath("$[1].voorschot", is(300)))
                .andExpect(jsonPath("$[1].prijs", is(1000)));
    }
}
