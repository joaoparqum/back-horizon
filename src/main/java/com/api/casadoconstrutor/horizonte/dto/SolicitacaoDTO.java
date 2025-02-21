package com.api.casadoconstrutor.horizonte.dto;

import com.api.casadoconstrutor.horizonte.model.Comprovante;
import com.api.casadoconstrutor.horizonte.model.Solicitacao;
import com.api.casadoconstrutor.horizonte.user.User;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SolicitacaoDTO(
        Long id,
        @NotNull LocalDateTime data,
        @NotNull String motivo,
        int horasSolicitadas,
        @NotNull String status,
        @NotNull String userLogin,
        @NotNull Comprovante comprovante
) {

    public static SolicitacaoDTO fromEntity(Solicitacao solicitacao) {
        return new SolicitacaoDTO(
                solicitacao.getId(),
                solicitacao.getData(),
                solicitacao.getMotivo(),
                solicitacao.getHorasSolicitadas(),
                solicitacao.getStatus().name(),
                solicitacao.getUser().getLogin(),
                solicitacao.getComprovante()
        );
    }

    public static Solicitacao toEntity(SolicitacaoDTO dto, User user) {
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setId(dto.id());
        solicitacao.setData(dto.data());
        solicitacao.setMotivo(dto.motivo());
        solicitacao.setHorasSolicitadas(dto.horasSolicitadas());
        //solicitacao.setStatus(StatusSolicitacao.valueOf(dto.status())); // Converte a String de volta para enum
        solicitacao.setUser(user);
        solicitacao.setComprovante(dto.comprovante());
        return solicitacao;
    }

}
