package org.java.rest.api.spring_la_mia_pizzeria_webapi.models;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizze")
public class Pizza {
    // Campi entità
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Nome pizza non può essere vuoto")
    private String nome;

    @Lob
    @NotBlank(message = "Descrizione non può essere vuota")
    @Size(min = 8, message = "Minimo di 8 caratteri")
    private String descrizione;

    @NotBlank(message = "Url non può essere vuoto")
    @Size(min = 10, max = 100, message = "Url deve essere di almeno 10 caratteri")
    @Column(name = "imageurl") // Vero colonna nome nel database
    private String imageUrl;

    @Min(value = 0, message = "Prezzo minimo deve partire da zero")
    private BigDecimal prezzo;

    // Relazioni entità
    @OneToMany(mappedBy = "pizza")
    private List<Offerta> offerte;

    @ManyToMany
    @JoinTable(name = "ingrediente_pizza", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingrediente_id"))
    private List<Ingrediente> ingredienti;

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

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrezzo() {
        return this.prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public List<Offerta> getOfferte() {
        return this.offerte;
    }

    public void setOfferte(List<Offerta> offerte) {
        this.offerte = offerte;
    }

    public List<Ingrediente> getIngredienti() {
        return this.ingredienti;
    }

    public void setIngredienti(List<Ingrediente> ingredienti) {
        this.ingredienti = ingredienti;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    // Override ToString
    @Override
    public String toString() {
        return String.format("Nome pizza : %s \n Prezzo: %s", this.nome, this.prezzo.toString());

    }
}
