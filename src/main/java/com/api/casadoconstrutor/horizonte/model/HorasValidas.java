package com.api.casadoconstrutor.horizonte.model;

import com.api.casadoconstrutor.horizonte.enuns.Filial;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table (name = "tb_horas")
public class HorasValidas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeColaborador;

    @Enumerated(EnumType.STRING)
    private Filial filial;

    private String junhoJulho;
    private String agosto;
    private String setembroOutubro;
    private String novembro;
    private String dezembro;
    private String janeiro;
    private String fevereiro;
    private String marco;
    private String abril;
    private String maio;
    private String junho;
    private String total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeColaborador() {
        return nomeColaborador;
    }

    public void setNomeColaborador(String nomeColaborador) {
        this.nomeColaborador = nomeColaborador;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public String getJunhoJulho() {
        return junhoJulho;
    }

    public void setJunhoJulho(String junhoJulho) {
        this.junhoJulho = junhoJulho;
    }

    public String getAgosto() {
        return agosto;
    }

    public void setAgosto(String agosto) {
        this.agosto = agosto;
    }

    public String getSetembroOutubro() {
        return setembroOutubro;
    }

    public void setSetembroOutubro(String setembroOutubro) {
        this.setembroOutubro = setembroOutubro;
    }

    public String getNovembro() {
        return novembro;
    }

    public void setNovembro(String novembro) {
        this.novembro = novembro;
    }

    public String getDezembro() {
        return dezembro;
    }

    public void setDezembro(String dezembro) {
        this.dezembro = dezembro;
    }

    public String getJaneiro() {
        return janeiro;
    }

    public void setJaneiro(String janeiro) {
        this.janeiro = janeiro;
    }

    public String getFevereiro() {
        return fevereiro;
    }

    public void setFevereiro(String fevereiro) {
        this.fevereiro = fevereiro;
    }

    public String getMarco() {
        return marco;
    }

    public void setMarco(String marco) {
        this.marco = marco;
    }

    public String getAbril() {
        return abril;
    }

    public void setAbril(String abril) {
        this.abril = abril;
    }

    public String getMaio() {
        return maio;
    }

    public void setMaio(String maio) {
        this.maio = maio;
    }

    public String getJunho() {
        return junho;
    }

    public void setJunho(String junho) {
        this.junho = junho;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
