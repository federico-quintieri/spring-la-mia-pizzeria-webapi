package org.java.rest.api.spring_la_mia_pizzeria_webapi.services;

import java.util.Optional;

import org.java.rest.api.spring_la_mia_pizzeria_webapi.models.Offerta;
import org.java.rest.api.spring_la_mia_pizzeria_webapi.repositories.OffertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffertaService {

    @Autowired
    private OffertaRepository offertaRepository;

    public Optional<Offerta> findByIdOfferta(Integer id) {
        return offertaRepository.findById(id);
    }

    public Offerta create(Offerta offerta) {
        return offertaRepository.save(offerta);
    }

    public Offerta update(Offerta offerta) {
        return offertaRepository.save(offerta);
    }

    public void deleteById(Integer id) {
        Offerta offertaToDelete = offertaRepository.findById(id).get();
        offertaRepository.delete(offertaToDelete);
    }
}
