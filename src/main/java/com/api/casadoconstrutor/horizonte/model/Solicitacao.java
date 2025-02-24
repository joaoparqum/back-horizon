package com.api.casadoconstrutor.horizonte.model;

import com.api.casadoconstrutor.horizonte.enuns.StatusSolicitacao;
import com.api.casadoconstrutor.horizonte.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_solicitacao")
public class Solicitacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;
    private String motivo;
    private int horasSolicitadas;
    private StatusSolicitacao status;
    private boolean visto = false;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @OneToOne(cascade = CascadeType.ALL)
    private Comprovante comprovante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getHorasSolicitadas() {
        return horasSolicitadas;
    }

    public void setHorasSolicitadas(int horasSolicitadas) {
        this.horasSolicitadas = horasSolicitadas;
    }

    public StatusSolicitacao getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacao status) {
        this.status = status;
    }

    public boolean isVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Comprovante getComprovante() {
        return comprovante;
    }

    public void setComprovante(Comprovante comprovante) {
        this.comprovante = comprovante;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
