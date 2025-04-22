package org.java.rest.api.spring_la_mia_pizzeria_webapi.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ingredienti")
public class Ingrediente {

    // Campi entità
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Non può esistere senza un nome")
    @Size(min = 3, message = "Il nome deve essere almeno di 3 caratteri")
    private String nome;

    @Lob
    @Size(min = 8, message = "La descrizione deve essere di almeno 8 caratteri")
    private String descrizione;

    // Relazioni entità
    @ManyToMany(mappedBy = "ingredienti")
    private List<Pizza> pizze;

    // Getters and setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Pizza> getPizze() {
        return this.pizze;
    }

    public void setPizze(List<Pizza> pizze) {
        this.pizze = pizze;
    }

    // Override ToString
    @Override
    public String toString() {
        return String.format("Nome ingrediente : %s \n Descrizione: %d ", this.nome, this.descrizione);
    }
}
