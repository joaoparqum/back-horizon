package com.api.casadoconstrutor.horizonte.controller;

import com.api.casadoconstrutor.horizonte.dto.HorasDTO;
import com.api.casadoconstrutor.horizonte.enuns.Filial;
import com.api.casadoconstrutor.horizonte.model.HorasValidas;
import com.api.casadoconstrutor.horizonte.service.HorasService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/horizonte/horas")
public class HorasController {

    final HorasService horasService;

    public HorasController(HorasService horasService) {
        this.horasService = horasService;
    }

    @PostMapping
    public ResponseEntity<Object> saveHoras(@RequestBody @Valid HorasDTO horasDTO) {
        HorasValidas horasValidas = new HorasValidas();
        BeanUtils.copyProperties(horasDTO, horasValidas);

        String total = horasService.calcularTotal(horasValidas);
        horasValidas.setTotal(total);

        return ResponseEntity.status(HttpStatus.CREATED).body(horasService.save(horasValidas));
    }

    @GetMapping("/nome/{nomeColaborador}")
    public ResponseEntity<Object> getHorasByNome(@PathVariable(value = "nomeColaborador") String nomeColaborador) {
        List<HorasValidas> horasValidas = horasService.findByNomeColaborador(nomeColaborador);

        if (horasValidas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hora válida não encontrada!!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(horasValidas);
    }

    @GetMapping
    public ResponseEntity<List<HorasValidas>> getAllHoras() {
        return ResponseEntity.status(HttpStatus.CREATED).body(horasService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneHoras(@PathVariable (value = "id") Long id) {
        Optional<HorasValidas> horasValidas = horasService.findById(id);

        if(!horasValidas.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hora Válida não encontrada!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(horasValidas.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteHoras(@PathVariable (value = "id") Long id) {
        Optional<HorasValidas> horasValidas = horasService.findById(id);

        if(!horasValidas.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hora Válida não encontrada!!");
        }

        horasService.delete(horasValidas.get());
        return ResponseEntity.status(HttpStatus.OK).body("Hora Válida deletada com sucesso!!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateHoras(@PathVariable (value = "id") Long id,
                                              @RequestBody @Valid HorasDTO horasDTO) {

        Optional<HorasValidas> horasValidasOptional = horasService.findById(id);
        if (!horasValidasOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hora Válida não encontrada!!");
        }

        HorasValidas horasValidas = new HorasValidas();
        BeanUtils.copyProperties(horasDTO, horasValidas);
        horasValidas.setId(horasValidasOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(horasService.save(horasValidas));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HorasDTO> alterarHoras(@PathVariable("id") Long id,
                                                 @RequestBody Map<String, String> params) {

        Optional<HorasValidas> horasValidasOptional = horasService.findById(id);
        if (horasValidasOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        HorasValidas horasValidas = horasValidasOptional.get();

        params.forEach((key, value) -> {
            switch (key) {
                case "nomeColaborador":
                    horasValidas.setNomeColaborador(value);
                    break;
                case "filial":
                    horasValidas.setFilial(Filial.valueOf(value));
                    break;
                case "ano24_junhoJulho":
                    horasValidas.setAno24_junhoJulho(value);
                    break;
                case "ano24_agosto":
                    horasValidas.setAno24_agosto(value);
                    break;
                case "ano24_setembroOutubro":
                    horasValidas.setAno24_setembroOutubro(value);
                    break;
                case "ano24_novembro":
                    horasValidas.setAno24_novembro(value);
                    break;
                case "ano24_dezembro":
                    horasValidas.setAno24_dezembro(value);
                    break;
                case "ano25_janeiro":
                    horasValidas.setAno25_janeiro(value);
                    break;
                case "ano25_fevereiro":
                    horasValidas.setAno25_fevereiro(value);
                    break;
                case "ano25_marco":
                    horasValidas.setAno25_marco(value);
                    break;
                case "ano25_abril":
                    horasValidas.setAno25_abril(value);
                    break;
                case "ano25_maio":
                    horasValidas.setAno25_maio(value);
                    break;
                case "ano25_junho":
                    horasValidas.setAno25_junho(value);
                    break;
                case "ano25_julho":
                    horasValidas.setAno25_julho(value);
                    break;
                case "ano25_agosto":
                    horasValidas.setAno25_agosto(value);
                    break;
                case "ano25_setembro":
                    horasValidas.setAno25_setembro(value);
                    break;
                case "ano25_outubro":
                    horasValidas.setAno25_outubro(value);
                    break;
                case "ano25_novembro":
                    horasValidas.setAno25_novembro(value);
                    break;
                case "ano25_dezembro":
                    horasValidas.setAno25_dezembro(value);
                    break;
                default:
                    break;
            }
        });

        String total = horasService.calcularTotal(horasValidas);
        horasValidas.setTotal(total);

        HorasValidas horasAtualizadas = horasService.save(horasValidas);
        return ResponseEntity.status(HttpStatus.OK).body(HorasDTO.fromEntity(horasAtualizadas));
    }
}
