package com.automacao.buscacep.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "ceps")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cep")
    @NotBlank(message = "O CEP não pode estar em branco")
    @Pattern(regexp = "\\d{8}", message = "O CEP deve estar no formato 99999999")
    private String cep;

    @Column(name = "uf")
    @NotBlank(message = "O estado não pode estar em branco")
    @Pattern(regexp = "[A-Z]{2}", message = "O estado deve conter exatamente duas letras maiúsculas")
    private String state;

    @Column(name = "cidade")
    @NotBlank(message = "A cidade não pode estar em branco")
    private String city;

    @Column(name = "vizinhanca")
    private String neighborhood;

    @Column(name = "rua")
    private String street;

    public Endereco() {}

    public Endereco(String cep, String state, String city, String neighborhood, String street) {
        this.cep = cep;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
    }

    public void normalizarCep() {
        if (cep != null) {
            cep = cep.replaceAll("\\D", "");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Endereco endereco = (Endereco) o;

        return id != null ? id.equals(endereco.id) : endereco.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", cep='" + cep + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", street='" + street + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
