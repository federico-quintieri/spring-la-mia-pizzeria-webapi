package org.java.rest.api.spring_la_mia_pizzeria_webapi.controllers;

import java.util.List;

import org.java.rest.api.spring_la_mia_pizzeria_webapi.models.Pizza;
import org.java.rest.api.spring_la_mia_pizzeria_webapi.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pizze")
public class PizzaController {

    // Autowired dei services
    @Autowired
    private PizzaService service;

    // Definizione endpoints
    @GetMapping
    public ResponseEntity<List<Pizza>> index() {

        List<Pizza> pizze = service.findAllPizze();

        if (pizze.isEmpty()) {
            // HTTP 204 No Content
            return ResponseEntity.noContent().build();
        }

        // HTTP 200 OK con body
        return ResponseEntity.ok(pizze);
    }
}
