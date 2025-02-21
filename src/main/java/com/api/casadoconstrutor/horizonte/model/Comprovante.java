package com.api.casadoconstrutor.horizonte.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table (name = "tb_comprovante")
@Data
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

}
