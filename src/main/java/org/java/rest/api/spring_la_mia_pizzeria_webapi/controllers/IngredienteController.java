package org.java.rest.api.spring_la_mia_pizzeria_webapi.controllers;

import java.util.List;
import java.util.Optional;

import org.java.rest.api.spring_la_mia_pizzeria_webapi.models.Ingrediente;
import org.java.rest.api.spring_la_mia_pizzeria_webapi.repositories.IngredienteRepository;
import org.java.rest.api.spring_la_mia_pizzeria_webapi.services.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ingredienti")
public class IngredienteController {

    private final IngredienteRepository ingredienteRepository;

    @Autowired
    private IngredienteService ingredienteService;

    IngredienteController(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    @GetMapping
    public ResponseEntity<List<Ingrediente>> index() {

        List<Ingrediente> ingredienti = ingredienteService.findAllIngredients();

        if (ingredienti.isEmpty()) {
            return new ResponseEntity<List<Ingrediente>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Ingrediente>>(ingredienti, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingrediente> show(@PathVariable Integer id) {

        Optional<Ingrediente> ingrediente = ingredienteService.findByIdIngredient(id);

        if (ingrediente.isEmpty()) {
            return new ResponseEntity<Ingrediente>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Ingrediente>(ingrediente.get(), HttpStatus.OK);

    }

    @PostMapping("/create")
    public ResponseEntity<Ingrediente> store(@Valid @RequestBody Ingrediente ingrediente) {

        return new ResponseEntity(ingredienteService.createIngredient(ingrediente), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Ingrediente> update(@Valid @RequestBody Ingrediente ingrediente, @PathVariable Integer id) {
        Optional<Ingrediente> ingredienteToUpdate = ingredienteService.findByIdIngredient(id);

        if (ingredienteToUpdate.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ingrediente.setId(id);

        ingredienteService.updateIngredient(ingrediente);

        return ResponseEntity.ok(ingrediente);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Ingrediente> delete(@PathVariable Integer id) {

        Optional<Ingrediente> ingredienteToDelete = ingredienteService.findByIdIngredient(id);

        if (ingredienteToDelete.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ingredienteService.deleteByIdIngredient(id);
 
        return ResponseEntity.ok().build();

    }
}
