package com.example.bestllings_service;

import com.example.bestllings_service.model.Bestelling;
import com.example.bestllings_service.repository.BestellingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

public class BestellingsControllerIntegrationTests {

    private final Bestelling bestelling1 = new Bestelling("testB1", "User1@hotmail.com", LocalDateTime.now(), 200, 50);
    private final Bestelling bestelling2 = new Bestelling("testB2", "User1@hotmail.com", LocalDateTime.now(), 1000, 500);
    private final Bestelling bestelling3 = new Bestelling("testB3", "User2@hotmail.com", LocalDateTime.now(), 500, 100);
    private final Bestelling bestelling5 = new Bestelling("testB4", "User2@hotmail.com", LocalDateTime.now(), 500, 100);
    private final Bestelling bestellingToBeDeleted = new Bestelling("testDelete", "UserD@hotmail.com", LocalDateTime.now(), 200, 50);
    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BestellingRepository bestellingRepository;

    @BeforeEach
    public void beforeAllTests() {
        bestelling1.setOnderdeelMerk("test");
        bestelling1.setOnderdeelNaam("test");
        bestelling2.setOnderdeelMerk("test");
        bestelling2.setOnderdeelNaam("test");
        bestelling3.setFietsModel("test");
        bestelling3.setFietsMerk("test");
        bestelling5.setFietsModel("test");
        bestelling5.setFietsMerk("test");
        bestellingRepository.deleteAll();
        bestellingRepository.save(bestelling1);
        bestellingRepository.save(bestelling2);
        bestellingRepository.save(bestelling3);
        bestellingRepository.save(bestelling5);
        bestellingRepository.save(bestellingToBeDeleted);
    }

    @AfterEach
    public void afterAllTests() {
        //Watch out with deleteAll() methods when you have other data in the test database!
        bestellingRepository.deleteAll();
    }

    @Test
    public void givenBestelling_whenGetBestellingByLeverancierBonNummer_thenReturnJsonBestelling() throws Exception {

        mockMvc.perform(get("/bestelling/{leverancierBonNummer}", "testB1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.leverancierBonNummer", is("testB1")))
                .andExpect(jsonPath("$.email", is("User1@hotmail.com")))
                .andExpect(jsonPath("$.voorschot", is(50)))
                .andExpect(jsonPath("$.prijs", is(200)));
    }

    @Test
    public void givenBestellingen_whenGetBestellingenByEmail_thenReturnJsonBestellingen() throws Exception {

        List<Bestelling> bestellingList = new ArrayList<>();
        bestellingList.add(bestelling1);
        bestellingList.add(bestelling2);

        mockMvc.perform(get("/bestellingen/email/{email}", "User1@hotmail.com"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].leverancierBonNummer", is("testB1")))
                .andExpect(jsonPath("$[0].email", is("User1@hotmail.com")))
                .andExpect(jsonPath("$[0].voorschot", is(50)))
                .andExpect(jsonPath("$[0].prijs", is(200)))
                .andExpect(jsonPath("$[1].leverancierBonNummer", is("testB2")))
                .andExpect(jsonPath("$[1].email", is("User1@hotmail.com")))
                .andExpect(jsonPath("$[1].voorschot", is(500)))
                .andExpect(jsonPath("$[1].prijs", is(1000)));
    }

    @Test
    public void givenBestellingen_whenGetBestelligenByOnderdeelNaam_thenReturnJsonBestelligen() throws Exception {

        List<Bestelling> bestellingList = new ArrayList<>();
        bestellingList.add(bestelling1);
        bestellingList.add(bestelling2);

        mockMvc.perform(get("/bestellingen/onderdeel/{onderdeelNaam}", "test"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].leverancierBonNummer", is("testB1")))
                .andExpect(jsonPath("$[0].onderdeelMerk", is("test")))
                .andExpect(jsonPath("$[0].onderdeelNaam", is("test")))
                .andExpect(jsonPath("$[0].email", is("User1@hotmail.com")))
                .andExpect(jsonPath("$[0].voorschot", is(50)))
                .andExpect(jsonPath("$[0].prijs", is(200)))
                .andExpect(jsonPath("$[1].leverancierBonNummer", is("testB2")))
                .andExpect(jsonPath("$[1].onderdeelMerk", is("test")))
                .andExpect(jsonPath("$[1].onderdeelNaam", is("test")))
                .andExpect(jsonPath("$[1].email", is("User1@hotmail.com")))
                .andExpect(jsonPath("$[1].voorschot", is(500)))
                .andExpect(jsonPath("$[1].prijs", is(1000)));
    }

    @Test
    public void givenBestellingen_whenGetBestelligenByOnderdeelMerk_thenReturnJsonBestelligen() throws Exception {

        List<Bestelling> bestellingList = new ArrayList<>();
        bestellingList.add(bestelling1);
        bestellingList.add(bestelling2);

        mockMvc.perform(get("/bestellingen/onderdeelMerk/{onderdeelMerk}", "test"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].leverancierBonNummer", is("testB1")))
                .andExpect(jsonPath("$[0].onderdeelMerk", is("test")))
                .andExpect(jsonPath("$[0].onderdeelNaam", is("test")))
                .andExpect(jsonPath("$[0].email", is("User1@hotmail.com")))
                .andExpect(jsonPath("$[0].voorschot", is(50)))
                .andExpect(jsonPath("$[0].prijs", is(200)))
                .andExpect(jsonPath("$[1].leverancierBonNummer", is("testB2")))
                .andExpect(jsonPath("$[1].onderdeelMerk", is("test")))
                .andExpect(jsonPath("$[1].onderdeelNaam", is("test")))
                .andExpect(jsonPath("$[1].email", is("User1@hotmail.com")))
                .andExpect(jsonPath("$[1].voorschot", is(500)))
                .andExpect(jsonPath("$[1].prijs", is(1000)));
    }

    @Test
    public void givenBestellingen_whenGetBestelligenByFietsSeriNummer_thenReturnJsonBestelligen() throws Exception {

        List<Bestelling> bestellingList = new ArrayList<>();
        bestellingList.add(bestelling3);
        bestellingList.add(bestelling5);

        mockMvc.perform(get("/bestellingen/fiets/{fietsSerienummer}", "test"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].leverancierBonNummer", is("testB3")))
                .andExpect(jsonPath("$[0].fietsMerk", is("test")))
                .andExpect(jsonPath("$[0].fietsModel", is("test")))
                .andExpect(jsonPath("$[0].email", is("User2@hotmail.com")))
                .andExpect(jsonPath("$[0].voorschot", is(100)))
                .andExpect(jsonPath("$[0].prijs", is(500)))
                .andExpect(jsonPath("$[1].leverancierBonNummer", is("testB4")))
                .andExpect(jsonPath("$[0].fietsMerk", is("test")))
                .andExpect(jsonPath("$[0].fietsModel", is("test")))
                .andExpect(jsonPath("$[1].email", is("User2@hotmail.com")))
                .andExpect(jsonPath("$[1].voorschot", is(100)))
                .andExpect(jsonPath("$[1].prijs", is(500)));
    }

    @Test
    public void whenPostBestteling_thenReturnJsonBestelling() throws Exception {
        Bestelling bestellingPost = new Bestelling("test3", "Test@hotmail.com", LocalDateTime.now(), 50, 20);
        bestellingPost.setFietsMerk("testPost");
        bestellingPost.setFietsModel("testPost");

        mockMvc.perform(post("/bestellingen")
                .content(mapper.writeValueAsString(bestellingPost))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.leverancierBonNummer", is("test3")))
                .andExpect(jsonPath("$.fietsMerk", is("testPost")))
                .andExpect(jsonPath("$.fietsModel", is("testPost")))
                .andExpect(jsonPath("$.email", is("Test@hotmail.com")))
                .andExpect(jsonPath("$.voorschot", is(20)))
                .andExpect(jsonPath("$.prijs", is(50)));
    }

    @Test
    public void givenBestelling_whenPutBestelling_thenReturnJsonBestelling() throws Exception {

        Bestelling bestellingPut = new Bestelling("testB1", "Test@hotmail.com", LocalDateTime.now(), 50, 20);
        bestellingPut.setOnderdeelMerk("testPost");
        bestellingPut.setOnderdeelNaam("testPost");
        bestellingPut.setFietsModel(null);
        bestellingPut.setFietsMerk(null);

        mockMvc.perform(put("/bestellingen")
                .content(mapper.writeValueAsString(bestellingPut))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.leverancierBonNummer", is("testB1")))
                .andExpect(jsonPath("$.onderdeelMerk", is("testPost")))
                .andExpect(jsonPath("$.onderdeelNaam", is("testPost")))
                .andExpect(jsonPath("$.email", is("Test@hotmail.com")))
                .andExpect(jsonPath("$.voorschot", is(20)))
                .andExpect(jsonPath("$.prijs", is(50)));
    }

    @Test
    public void givenBestelling_whenDeleteBestelling_thenStatusOk() throws Exception {

        mockMvc.perform(delete("/bestelling/{leverancierBonNummer}", "testDelete")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    
    @Test
    public void givenNoBestelling_whenDeleteBestelling_thenStatusNotFound() throws Exception {

        mockMvc.perform(delete("/bestelling/{leverancierBonNummer}", "testnotclear")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
