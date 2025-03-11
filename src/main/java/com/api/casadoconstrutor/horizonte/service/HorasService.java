package com.api.casadoconstrutor.horizonte.service;

import com.api.casadoconstrutor.horizonte.model.HorasValidas;
import com.api.casadoconstrutor.horizonte.repository.HorasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class HorasService {

    @Autowired
    HorasRepository horasRepository;

    public String calcularTotal(HorasValidas horasValidas) {

        Duration totalDuration = Duration.ZERO;

        String[] campos = {
                horasValidas.getAno24_junhoJulho(), horasValidas.getAno24_agosto(), horasValidas.getAno24_setembroOutubro(),
                horasValidas.getAno24_novembro(), horasValidas.getAno24_dezembro(), horasValidas.getAno25_janeiro(),
                horasValidas.getAno25_fevereiro(), horasValidas.getAno25_marco(), horasValidas.getAno25_abril(),
                horasValidas.getAno25_maio(), horasValidas.getAno25_junho(), horasValidas.getAno25_julho(), horasValidas.getAno25_agosto(),
                horasValidas.getAno25_setembro(), horasValidas.getAno25_outubro(), horasValidas.getAno25_novembro(), horasValidas.getAno25_dezembro()
        };

        for (String campo : campos) {
            if (campo != null && !campo.isEmpty()) {
                totalDuration = totalDuration.plus(parseDuration(campo));
            }
        }

        long totalSeconds = totalDuration.getSeconds();
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private Duration parseDuration(String timeString) {

        boolean isNegative = timeString.startsWith("-");

        if(isNegative) {
            timeString = timeString.substring(1);
        }

        String[] parts = timeString.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);

        Duration duration = Duration.ofHours(hours)
                .plusMinutes(minutes)
                .plusSeconds(seconds);

        if (isNegative) {
            duration = duration.negated();
        }

        return duration;
    }

    public HorasValidas save(HorasValidas horasValidas) {
        return this.horasRepository.save(horasValidas);
    }

    public List<HorasValidas> findAll() {
        return this.horasRepository.findAll(Sort.by(Sort.Direction.ASC, "nomeColaborador"));
    }

    public Optional<HorasValidas> findById(Long id) {
        return this.horasRepository.findById(id);
    }

    public List<HorasValidas> findByNomeColaborador(String nomeColaborador) {
        return this.horasRepository.findByNomeColaboradorContaining(nomeColaborador);
    }

    public void delete(HorasValidas horasValidas) {
        this.horasRepository.delete(horasValidas);
    }


}
