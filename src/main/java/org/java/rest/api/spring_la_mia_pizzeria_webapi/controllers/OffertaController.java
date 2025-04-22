package org.java.rest.api.spring_la_mia_pizzeria_webapi.controllers;

import java.util.Optional;

import org.java.rest.api.spring_la_mia_pizzeria_webapi.models.Offerta;
import org.java.rest.api.spring_la_mia_pizzeria_webapi.models.Pizza;
import org.java.rest.api.spring_la_mia_pizzeria_webapi.repositories.OffertaRepository;
import org.java.rest.api.spring_la_mia_pizzeria_webapi.services.OffertaService;
import org.java.rest.api.spring_la_mia_pizzeria_webapi.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offerte")
public class OffertaController {

    private final OffertaRepository offertaRepository;

    @Autowired
    private OffertaService service;

    @Autowired
    PizzaService pizzaService;

    OffertaController(OffertaRepository offertaRepository) {
        this.offertaRepository = offertaRepository;
    }

    // Store
    @PostMapping("/create/{id}")
    public ResponseEntity<Offerta> store(@RequestBody Offerta offerta, @PathVariable Integer id) {

        // Pizza con quell'id non trovata quindi non possiamo farci un'offerta
        Optional<Pizza> pizza = pizzaService.findPizzaById(id);
        if (pizza.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Associa l'offerta alla pizza
        offerta.setPizza(pizza.get());

        // Creo nuova offerta
        Offerta nuovaOfferta = service.create(offerta);

        return ResponseEntity.ok(nuovaOfferta);
    }

    // Update
    @PutMapping("/update/{id}")
    public ResponseEntity<Offerta> update(@RequestBody Offerta offerta, @PathVariable Integer id) {

        // Verifica se l'offerta con l'id esiste
        Optional<Offerta> offertaEsistente = service.findByIdOfferta(id);
        if (offertaEsistente.isEmpty()) {
            return ResponseEntity.notFound().build(); // Offerta non trovata
        }

        // Aggiorna i dettagli dell'offerta esistente
        Offerta offertaAggiornata = offertaEsistente.get();
        offertaAggiornata.setNomeOfferta(offerta.getNomeOfferta());
        offertaAggiornata.setDataInizioOfferta(offerta.getDataInizioOfferta());
        offertaAggiornata.setDataFineOfferta(offerta.getDataFineOfferta());

        // Salva l'offerta aggiornata nel database
        Offerta offertaFinale = service.update(offertaAggiornata);

        // Restituisci la risposta
        return ResponseEntity.ok(offertaFinale);
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Offerta> delete(@PathVariable Integer id) {

        if (service.findByIdOfferta(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
