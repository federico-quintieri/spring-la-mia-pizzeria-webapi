package org.java.rest.api.spring_la_mia_pizzeria_webapi.services;

import java.util.List;

import org.java.rest.api.spring_la_mia_pizzeria_webapi.models.Pizza;
import org.java.rest.api.spring_la_mia_pizzeria_webapi.repositories.OffertaRepository;
import org.java.rest.api.spring_la_mia_pizzeria_webapi.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    // Faccio gli autowired necessari
    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OffertaRepository offertaRepository;


    // Metodi di accesso al DB
    public List<Pizza> findAllPizze() {
        return pizzaRepository.findAll();
    }
}
