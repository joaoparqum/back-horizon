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

    private String ano24_junhoJulho;
    private String ano24_agosto;
    private String ano24_setembroOutubro;
    private String ano24_novembro;
    private String ano24_dezembro;
    private String ano25_janeiro;
    private String ano25_fevereiro;
    private String ano25_marco;
    private String ano25_abril;
    private String ano25_maio;
    private String ano25_junho;
    private String ano25_julho;
    private String ano25_agosto;
    private String ano25_setembro;
    private String ano25_outubro;
    private String ano25_novembro;
    private String ano25_dezembro;
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

    public String getAno24_junhoJulho() {
        return ano24_junhoJulho;
    }

    public void setAno24_junhoJulho(String ano24_junhoJulho) {
        this.ano24_junhoJulho = ano24_junhoJulho;
    }

    public String getAno24_agosto() {
        return ano24_agosto;
    }

    public void setAno24_agosto(String ano24_agosto) {
        this.ano24_agosto = ano24_agosto;
    }

    public String getAno24_setembroOutubro() {
        return ano24_setembroOutubro;
    }

    public void setAno24_setembroOutubro(String ano24_setembroOutubro) {
        this.ano24_setembroOutubro = ano24_setembroOutubro;
    }

    public String getAno24_novembro() {
        return ano24_novembro;
    }

    public void setAno24_novembro(String ano24_novembro) {
        this.ano24_novembro = ano24_novembro;
    }

    public String getAno24_dezembro() {
        return ano24_dezembro;
    }

    public void setAno24_dezembro(String ano24_dezembro) {
        this.ano24_dezembro = ano24_dezembro;
    }

    public String getAno25_janeiro() {
        return ano25_janeiro;
    }

    public void setAno25_janeiro(String ano25_janeiro) {
        this.ano25_janeiro = ano25_janeiro;
    }

    public String getAno25_fevereiro() {
        return ano25_fevereiro;
    }

    public void setAno25_fevereiro(String ano25_fevereiro) {
        this.ano25_fevereiro = ano25_fevereiro;
    }

    public String getAno25_marco() {
        return ano25_marco;
    }

    public void setAno25_marco(String ano25_marco) {
        this.ano25_marco = ano25_marco;
    }

    public String getAno25_abril() {
        return ano25_abril;
    }

    public void setAno25_abril(String ano25_abril) {
        this.ano25_abril = ano25_abril;
    }

    public String getAno25_maio() {
        return ano25_maio;
    }

    public void setAno25_maio(String ano25_maio) {
        this.ano25_maio = ano25_maio;
    }

    public String getAno25_junho() {
        return ano25_junho;
    }

    public void setAno25_junho(String ano25_junho) {
        this.ano25_junho = ano25_junho;
    }

    public String getAno25_julho() {
        return ano25_julho;
    }

    public void setAno25_julho(String ano25_julho) {
        this.ano25_julho = ano25_julho;
    }

    public String getAno25_setembro() {
        return ano25_setembro;
    }

    public void setAno25_setembro(String ano25_setembro) {
        this.ano25_setembro = ano25_setembro;
    }

    public String getAno25_outubro() {
        return ano25_outubro;
    }

    public void setAno25_outubro(String ano25_outubro) {
        this.ano25_outubro = ano25_outubro;
    }

    public String getAno25_novembro() {
        return ano25_novembro;
    }

    public void setAno25_novembro(String ano25_novembro) {
        this.ano25_novembro = ano25_novembro;
    }

    public String getAno25_dezembro() {
        return ano25_dezembro;
    }

    public void setAno25_dezembro(String ano25_dezembro) {
        this.ano25_dezembro = ano25_dezembro;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAno25_agosto() {
        return ano25_agosto;
    }

    public void setAno25_agosto(String ano25_agosto) {
        this.ano25_agosto = ano25_agosto;
    }
}
