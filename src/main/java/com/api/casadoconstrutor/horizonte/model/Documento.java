package com.api.casadoconstrutor.horizonte.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "tb_vistas")
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeVista;
    private String tipoVista;
    private Long tamanhoVista;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeVista() {
        return nomeVista;
    }

    public void setNomeVista(String nomeVista) {
        this.nomeVista = nomeVista;
    }

    public String getTipoVista() {
        return tipoVista;
    }

    public void setTipoVista(String tipoVista) {
        this.tipoVista = tipoVista;
    }

    public Long getTamanhoVista() {
        return tamanhoVista;
    }

    public void setTamanhoVista(Long tamanhoVista) {
        this.tamanhoVista = tamanhoVista;
    }
}
