package org.java.rest.api.spring_la_mia_pizzeria_webapi.repositories;

import org.java.rest.api.spring_la_mia_pizzeria_webapi.models.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
}
