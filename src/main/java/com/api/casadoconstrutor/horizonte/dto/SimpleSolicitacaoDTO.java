package com.api.casadoconstrutor.horizonte.dto;

import com.api.casadoconstrutor.horizonte.model.Solicitacao;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SimpleSolicitacaoDTO(
        Long id,
        @NotNull LocalDateTime data,
        @NotNull String motivo,
        int horasSolicitadas
) {
    public static SimpleSolicitacaoDTO fromEntity(Solicitacao solicitacao) {
        return new SimpleSolicitacaoDTO(
                solicitacao.getId(),
                solicitacao.getData(),
                solicitacao.getMotivo(),
                solicitacao.getHorasSolicitadas()
        );
    }
}
