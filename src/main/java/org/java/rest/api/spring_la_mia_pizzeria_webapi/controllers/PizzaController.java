package org.java.rest.api.spring_la_mia_pizzeria_webapi.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.java.rest.api.spring_la_mia_pizzeria_webapi.models.Pizza;
import org.java.rest.api.spring_la_mia_pizzeria_webapi.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pizze")
public class PizzaController {

    // Autowired dei services
    @Autowired
    private PizzaService service;

    // Definizione endpoints
    @GetMapping
    public ResponseEntity<List<Pizza>> index(@RequestParam(name = "nome", required = false) String nome) {

        List<Pizza> pizze;

        if (nome != null && !nome.isBlank()) {
            pizze = service.findByNome(nome);
        } else {
            pizze = service.findAllPizze();
        }

        if (pizze.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pizze);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show(@PathVariable Integer id) {
        Optional<Pizza> pizzaHandle = service.findPizzaById(id);

        if (pizzaHandle.isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Pizza>(pizzaHandle.get(), HttpStatus.OK);
    }

    @PostMapping("/crea")
    public ResponseEntity<Pizza> store(@RequestBody Pizza pizza) {
        Pizza nuovaPizza = service.create(pizza);

        return ResponseEntity.ok(nuovaPizza);
    }

    @PutMapping("/modifica/{id}")
    public ResponseEntity<Pizza> update(@RequestBody Pizza pizza, @PathVariable Integer id) {

        if (service.findPizzaById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        pizza.setId(id);

        return ResponseEntity.ok(service.update(pizza));
    }

    @DeleteMapping("/cancella/{id}")
    public ResponseEntity<Pizza> delete(@PathVariable Integer id) {

        if (service.findPizzaById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.deleteById(id);
        return new ResponseEntity<Pizza>(HttpStatus.OK);
    }

}
