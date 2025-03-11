package com.api.casadoconstrutor.horizonte.dto;

import com.api.casadoconstrutor.horizonte.enuns.Filial;
import com.api.casadoconstrutor.horizonte.model.HorasValidas;
import jakarta.validation.constraints.NotNull;

public record HorasDTO(
        Long id,
        @NotNull String nomeColaborador,
        @NotNull Filial filial,
        @NotNull String ano24_junhoJulho,
        @NotNull String ano24_agosto,
        @NotNull String ano24_setembroOutubro,
        @NotNull String ano24_novembro,
        @NotNull String ano24_dezembro,
        @NotNull String ano25_janeiro,
        @NotNull String ano25_fevereiro,
        @NotNull String ano25_marco,
        @NotNull String ano25_abril,
        @NotNull String ano25_maio,
        @NotNull String ano25_junho,
        @NotNull String ano25_julho,
        @NotNull String ano25_agosto,
        @NotNull String ano25_setembro,
        @NotNull String ano25_outubro,
        @NotNull String ano25_novembro,
        @NotNull String ano25_dezembro
) {

    public static HorasDTO fromEntity(HorasValidas horasValidas) {
        return new HorasDTO(
                horasValidas.getId(),
                horasValidas.getNomeColaborador(),
                horasValidas.getFilial(),
                horasValidas.getAno24_junhoJulho(),
                horasValidas.getAno24_agosto(),
                horasValidas.getAno24_setembroOutubro(),
                horasValidas.getAno24_novembro(),
                horasValidas.getAno24_dezembro(),
                horasValidas.getAno25_janeiro(),
                horasValidas.getAno25_fevereiro(),
                horasValidas.getAno25_marco(),
                horasValidas.getAno25_abril(),
                horasValidas.getAno25_maio(),
                horasValidas.getAno25_junho(),
                horasValidas.getAno25_julho(),
                horasValidas.getAno25_agosto(),
                horasValidas.getAno25_setembro(),
                horasValidas.getAno25_outubro(),
                horasValidas.getAno25_novembro(),
                horasValidas.getAno25_dezembro()
        );
    }

}
