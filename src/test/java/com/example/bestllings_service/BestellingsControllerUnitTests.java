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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        Bestelling bestelling1 = new Bestelling("testU1", "Test@hotmail.com", LocalDateTime.now(), 50, 20);
        Bestelling bestelling2 = new Bestelling("testU2", "Test@hotmail.com", LocalDateTime.now(), 1000, 300);

        List<Bestelling> bestellingList = new ArrayList<>();
        bestellingList.add(bestelling1);
        bestellingList.add(bestelling2);

        given(bestellingRepository.findBestellingByEmailContaining("Test@hotmail.com")).willReturn(bestellingList);

        mockMvc.perform(get("/bestellingen/email/{email}", "Test@hotmail.com"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].leverancierBonNummer", is("testU1")))
                .andExpect(jsonPath("$[0].email", is("Test@hotmail.com")))
                .andExpect(jsonPath("$[0].voorschot", is(20)))
                .andExpect(jsonPath("$[0].prijs", is(50)))
                .andExpect(jsonPath("$[1].leverancierBonNummer", is("testU2")))
                .andExpect(jsonPath("$[1].email", is("Test@hotmail.com")))
                .andExpect(jsonPath("$[1].voorschot", is(300)))
                .andExpect(jsonPath("$[1].prijs", is(1000)));
    }

    // Multiple get by onderdeelnummer
    @Test
    public void givenBestellingen_whenGetBestelligenByOnderdeelSeriNummer_thenReturnJsonBestelligen() throws Exception {
        Bestelling bestelling1 = new Bestelling("testU3", "Test@hotmail.com", LocalDateTime.now(), 50, 20);
        Bestelling bestelling2 = new Bestelling("testU4", "Test@hotmail.com", LocalDateTime.now(), 1000, 300);

        bestelling1.setOnderdeelNaam("test");
        bestelling1.setOnderdeelMerk("vav");
        bestelling2.setOnderdeelMerk("test");
        bestelling2.setOnderdeelMerk("vav");

        List<Bestelling> bestellingList = new ArrayList<>();
        bestellingList.add(bestelling1);
        bestellingList.add(bestelling2);

        given(bestellingRepository.findBestellingByOnderdeelSerienummerContaining("test")).willReturn(bestellingList);

        mockMvc.perform(get("/bestellingen/onderdeel/{onderdeelSerienummer}", "test"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].leverancierBonNummer", is("testU3")))
                .andExpect(jsonPath("$[0].onderdeelNaam", is("test")))
                .andExpect(jsonPath("$[0].onderdeelMerk", is("vav")))
                .andExpect(jsonPath("$[0].email", is("Test@hotmail.com")))
                .andExpect(jsonPath("$[0].voorschot", is(20)))
                .andExpect(jsonPath("$[0].prijs", is(50)))
                .andExpect(jsonPath("$[1].leverancierBonNummer", is("testU4")))
                .andExpect(jsonPath("$[1].onderdeelNaam", is("test")))
                .andExpect(jsonPath("$[1].onderdeelMerk", is("vav")))
                .andExpect(jsonPath("$[1].email", is("Test@hotmail.com")))
                .andExpect(jsonPath("$[1].voorschot", is(300)))
                .andExpect(jsonPath("$[1].prijs", is(1000)));
    }

    // Multiple get by fietsserienummer
    @Test
    public void givenBestellingen_whenGetBestelligenByFietsSeriNummer_thenReturnJsonBestelligen() throws Exception {
        Bestelling bestelling1 = new Bestelling("testU5", "Test@hotmail.com", LocalDateTime.now(), 50, 20);
        Bestelling bestelling2 = new Bestelling("testU6", "Test@hotmail.com", LocalDateTime.now(), 1000, 300);

        bestelling1.setFietsMerk("test");
        bestelling1.setFietsModel("vav");
        bestelling2.setFietsMerk("test");
        bestelling2.setFietsModel("vav");

        List<Bestelling> bestellingList = new ArrayList<>();
        bestellingList.add(bestelling1);
        bestellingList.add(bestelling2);

        given(bestellingRepository.findBestellingByFietsSerienummerContaining("test")).willReturn(bestellingList);

        mockMvc.perform(get("/bestellingen/fiets/{fietsSerienummer}", "test"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].leverancierBonNummer", is("testU5")))
                .andExpect(jsonPath("$[0].fietsMerk", is("test")))
                .andExpect(jsonPath("$[0].fietsMerk", is("vav")))
                .andExpect(jsonPath("$[0].email", is("Test@hotmail.com")))
                .andExpect(jsonPath("$[0].voorschot", is(20)))
                .andExpect(jsonPath("$[0].prijs", is(50)))
                .andExpect(jsonPath("$[1].leverancierBonNummer", is("testU6")))
                .andExpect(jsonPath("$[1].fietsMerk", is("test")))
                .andExpect(jsonPath("$[1].fietsModel", is("vav")))
                .andExpect(jsonPath("$[1].email", is("Test@hotmail.com")))
                .andExpect(jsonPath("$[1].voorschot", is(300)))
                .andExpect(jsonPath("$[1].prijs", is(1000)));
    }

    // Post bestteling
    @Test
    public void whenPostBestteling_thenReturnJsonBestelling() throws Exception {
        Bestelling bestelling = new Bestelling("testU7", "Test@hotmail.com", LocalDateTime.now(), 50, 20);
        bestelling.setFietsModel("testPost");
        bestelling.setFietsMerk("testPostMerk");

        mockMvc.perform(post("/bestellingen")
                .content(mapper.writeValueAsString(bestelling))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.leverancierBonNummer", is("testU7")))
                .andExpect(jsonPath("$.fietsModel", is("testPost")))
                .andExpect(jsonPath("$.fietsMerk", is("testPostMerk")))
                .andExpect(jsonPath("$.email", is("Test@hotmail.com")))
                .andExpect(jsonPath("$.voorschot", is(20)))
                .andExpect(jsonPath("$.prijs", is(50)));
    }

    // put
    @Test
    public void givenBestelling_whenPutBestelling_thenReturnJsonBestelling() throws Exception {
        Bestelling bestelling = new Bestelling("testU8", "Test@hotmail.com", LocalDateTime.now(), 50, 20);
        bestelling.setFietsMerk("testPut");
        bestelling.setFietsModel("testPut");

        given(bestellingRepository.findBestellingByLeverancierBonNummer("testU8")).willReturn(bestelling);

        Bestelling updatedBestelling = new Bestelling("testU8", "Test@hotmail.com", LocalDateTime.now(), 50, 30);
        updatedBestelling.setFietsMerk(null);
        updatedBestelling.setFietsModel(null);
        updatedBestelling.setOnderdeelMerk("testPut");
        updatedBestelling.setOnderdeelNaam("testPut");

        mockMvc.perform(put("/bestellingen")
                .content(mapper.writeValueAsString(updatedBestelling))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.leverancierBonNummer", is("testU8")))
                //.andExpect(jsonPath("$.fietsSerienummer", is(null)))
                .andExpect(jsonPath("$.onderdeelNaam", is("testPut")))
                .andExpect(jsonPath("$.onderdeelMerk", is("testPut")))
                .andExpect(jsonPath("$.email", is("Test@hotmail.com")))
                .andExpect(jsonPath("$.voorschot", is(30)))
                .andExpect(jsonPath("$.prijs", is(50)));
    }

    @Test
    public void givenBestelling_whenDeleteBestelling_thenStatusOk() throws Exception {
        Bestelling bestelling = new Bestelling("testD", "Test@hotmail.com", LocalDateTime.now(), 50, 20);

        given(bestellingRepository.findBestellingByLeverancierBonNummer("testD")).willReturn(bestelling);

        mockMvc.perform(delete("/bestelling/{leverancierBonNummer}", "testD")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoBestelling_whenDeleteBestelling_thenStatusNotFound() throws Exception {
        given(bestellingRepository.findBestellingByLeverancierBonNummer("testD1")).willReturn(null);

        mockMvc.perform(delete("/bestelling/{leverancierBonNummer}", "testD1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
