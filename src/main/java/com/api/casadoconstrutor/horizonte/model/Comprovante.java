package com.api.casadoconstrutor.horizonte.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table (name = "tb_comprovante")
public class Comprovante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeComprovante;
    private String tipoComprovante;
    private Long tamanhoComprovante;

    @OneToOne(mappedBy = "comprovante", cascade = CascadeType.ALL)
    @JsonIgnore
    private Solicitacao solicitacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeComprovante() {
        return nomeComprovante;
    }

    public void setNomeComprovante(String nomeComprovante) {
        this.nomeComprovante = nomeComprovante;
    }

    public String getTipoComprovante() {
        return tipoComprovante;
    }

    public void setTipoComprovante(String tipoComprovante) {
        this.tipoComprovante = tipoComprovante;
    }

    public Long getTamanhoComprovante() {
        return tamanhoComprovante;
    }

    public void setTamanhoComprovante(Long tamanhoComprovante) {
        this.tamanhoComprovante = tamanhoComprovante;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }
}
