package org.java.rest.api.spring_la_mia_pizzeria_webapi.repositories;

import org.java.rest.api.spring_la_mia_pizzeria_webapi.models.Offerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffertaRepository extends JpaRepository<Offerta, Integer> {
}
