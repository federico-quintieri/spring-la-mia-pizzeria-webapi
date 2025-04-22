package org.java.rest.api.spring_la_mia_pizzeria_webapi.services;

import java.util.List;
import java.util.Optional;

import org.java.rest.api.spring_la_mia_pizzeria_webapi.models.Offerta;
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

    public Optional<Pizza> findPizzaById(Integer Id) {
        return pizzaRepository.findById(Id);
    }

    public List<Pizza> findByNome(String nome) {
        return pizzaRepository.findByNomeContaining(nome);
    }

    public Pizza create(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza update(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void delete(Pizza pizza) {
        // Ciclo tutte le offerte di UNA pizza e le cancello una per una
        for (Offerta offertaToDelete : pizza.getOfferte()) {
            offertaRepository.delete(offertaToDelete);
        }
        pizzaRepository.delete(pizza);
    }

    public void deleteById(Integer id) {
        Pizza pizza = pizzaRepository.findById(id).get();

        for (Offerta offertaToDelete : pizza.getOfferte()) {
            offertaRepository.delete(offertaToDelete);
        }
        pizzaRepository.delete(pizza);
    }

}
