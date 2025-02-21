package com.api.casadoconstrutor.horizonte.dto;

import jakarta.validation.constraints.NotNull;

public record DocumentoDTO(
        Long id,
        @NotNull String nomeVista,
        @NotNull String tipoVista,
        Long tamanhoVista
) {
}
