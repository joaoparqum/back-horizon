package com.api.casadoconstrutor.horizonte.dto;

import com.api.casadoconstrutor.horizonte.enuns.Filial;
import com.api.casadoconstrutor.horizonte.model.HorasValidas;
import jakarta.validation.constraints.NotNull;

public record HorasDTO(
        Long id,
        @NotNull String nomeColaborador,
        @NotNull Filial filial,
        @NotNull String junhoJulho,
        @NotNull String agosto,
        @NotNull String setembroOutubro,
        @NotNull String novembro,
        @NotNull String dezembro,
        @NotNull String janeiro,
        @NotNull String fevereiro,
        @NotNull String marco,
        @NotNull String abril,
        @NotNull String maio,
        @NotNull String junho
) {

    public static HorasDTO fromEntity(HorasValidas horasValidas) {
        return new HorasDTO(
                horasValidas.getId(),
                horasValidas.getNomeColaborador(),
                horasValidas.getFilial(),
                horasValidas.getJunhoJulho(),
                horasValidas.getAgosto(),
                horasValidas.getSetembroOutubro(),
                horasValidas.getNovembro(),
                horasValidas.getDezembro(),
                horasValidas.getJaneiro(),
                horasValidas.getFevereiro(),
                horasValidas.getMarco(),
                horasValidas.getAbril(),
                horasValidas.getMaio(),
                horasValidas.getJunho()
        );
    }

}
