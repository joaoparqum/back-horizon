package com.api.casadoconstrutor.horizonte.controller;

import com.api.casadoconstrutor.horizonte.dto.SimpleSolicitacaoDTO;
import com.api.casadoconstrutor.horizonte.dto.SolicitacaoDTO;
import com.api.casadoconstrutor.horizonte.enuns.StatusSolicitacao;
import com.api.casadoconstrutor.horizonte.model.Comprovante;
import com.api.casadoconstrutor.horizonte.model.Solicitacao;
import com.api.casadoconstrutor.horizonte.service.ArquivoService;
import com.api.casadoconstrutor.horizonte.service.SolicitacaoService;
import com.api.casadoconstrutor.horizonte.user.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/horizonte/solicitacoes")
public class SolicitacaoController {

    private final SolicitacaoService solicitacaoService;
    private final ArquivoService arquivoService;

    public SolicitacaoController(SolicitacaoService solicitacaoService, ArquivoService arquivoService) {
        this.solicitacaoService = solicitacaoService;
        this.arquivoService = arquivoService;
    }

    @GetMapping("/user")
    public List<SolicitacaoDTO> listarSolicitacoes(@AuthenticationPrincipal User user) {
        return solicitacaoService.getSolicitacoesByUser(user)
                .stream()
                .map(SolicitacaoDTO::fromEntity)
                .toList();
    }

    @GetMapping
    public ResponseEntity<List<SolicitacaoDTO>> getAllSolicitacoes() {
        List<SolicitacaoDTO> solicitacoes = solicitacaoService.findAllSolicitacoesWithUsers();
        return ResponseEntity.ok(solicitacoes);
    }

    @GetMapping("/nome/{login}")
    public ResponseEntity<Object> getSolicitacaoByNome(@PathVariable(value = "login") String login) {
        List<Solicitacao> solicitacoes = solicitacaoService.findByUserLogin(login);
        if (solicitacoes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Solicitação não encontrada!");
        }

        List<SolicitacaoDTO> solicitacaoDTOs = solicitacoes.stream()
                .map(SolicitacaoDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(solicitacaoDTOs);
    }

    @GetMapping("/motivo/{motivo}")
    public ResponseEntity<Object> getSolicitacaoByMotivo(@PathVariable(value = "motivo") String motivo) {
        List<Solicitacao> solicitacao = solicitacaoService.findByMotivo(motivo);
        if(solicitacao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Solicitação não encontrada!!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(solicitacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneSolicitacao(@PathVariable(value = "id") Long id) {
        Optional<Solicitacao> solicitacao = solicitacaoService.findById(id);
        if(!solicitacao.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Solicitação não encontrada!!");
        }
        return ResponseEntity.ok(SimpleSolicitacaoDTO.fromEntity(solicitacao.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSolicitacao(@PathVariable(value = "id") Long id, @RequestBody
    @Valid SimpleSolicitacaoDTO solicitacaoDto) {
        Optional<Solicitacao> solicitacaoOptional = solicitacaoService.findById(id);
        if(!solicitacaoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Solicitação não encontrada!!");
        }
        Solicitacao solicitacao = solicitacaoOptional.get();

        solicitacao.setData(solicitacaoDto.data());
        solicitacao.setMotivo(solicitacaoDto.motivo());
        solicitacao.setHorasSolicitadas(solicitacaoDto.horasSolicitadas());

        solicitacaoService.save(solicitacao);

        return ResponseEntity.status(HttpStatus.OK).body(SimpleSolicitacaoDTO.fromEntity(solicitacao));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SolicitacaoDTO> criarSolicitacao(
            @AuthenticationPrincipal User user,
            @RequestParam("data") String data,
            @RequestParam("motivo") String motivo,
            @RequestParam("horasSolicitadas") int horasSolicitadas,
            @RequestParam(value = "comprovante", required = false) MultipartFile comprovante
    ) {
        try {
            // Criação do comprovante se o arquivo for enviado
            Comprovante documento = null;
            if (comprovante != null && !comprovante.isEmpty()) {
                // Salva o arquivo no sistema
                String filePath = arquivoService.saveFile(comprovante);

                // Preenche as informações do comprovante
                documento = new Comprovante();
                documento.setNomeComprovante(comprovante.getOriginalFilename());
                documento.setTipoComprovante(comprovante.getContentType());
                documento.setTamanhoComprovante(comprovante.getSize());
            }

            // Converte os dados para a entidade Solicitação
            Solicitacao solicitacao = new Solicitacao();
            solicitacao.setData(LocalDateTime.parse(data));
            solicitacao.setMotivo(motivo);
            solicitacao.setHorasSolicitadas(horasSolicitadas);
            solicitacao.setUser(user);

            // Relaciona o comprovante à solicitação, se presente
            if (documento != null) {
                documento.setSolicitacao(solicitacao);
                solicitacao.setComprovante(documento);
            }

            // Salva a solicitação
            Solicitacao novaSolicitacao = solicitacaoService.criarSolicitacao(solicitacao, user);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(SolicitacaoDTO.fromEntity(novaSolicitacao));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<SolicitacaoDTO> alterarStatus(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long id,
            @RequestParam("status") StatusSolicitacao status) throws AccessDeniedException {
        Solicitacao solicitacao = solicitacaoService.aprovarOuRejeitarSolicitacao(id, status, user);
        return ResponseEntity.ok(SolicitacaoDTO.fromEntity(solicitacao));
    }

    @PutMapping("/marcar-vistas")
    public ResponseEntity<Void> marcarVistas() {
        solicitacaoService.marcarTodasComoVistas();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/nao-vistas")
    public ResponseEntity<List<SolicitacaoDTO>> getNaoVistas() {
        List<SolicitacaoDTO> naoVistas = solicitacaoService.buscarNaoVistas();
        return ResponseEntity.ok(naoVistas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTarefa(@PathVariable(value = "id") Long id){
        Optional<Solicitacao> solOptional = solicitacaoService.findById(id);
        if (!solOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Solicitação não encontrada!");
        }
        solicitacaoService.delete(solOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Solicitação deletada com sucesso!");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SolicitacaoDTO> alterarSolicitacao(@PathVariable("id") Long id,
                                                             @RequestBody Map<String, String> params) {

        Optional<Solicitacao> solicitacaoOptional = solicitacaoService.findById(id);
        if (solicitacaoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Solicitacao solicitacao = solicitacaoOptional.get();

        params.forEach((key, value) -> {
            switch (key) {
                case "data":
                    solicitacao.setData(LocalDateTime.parse(value));
                    break;
                case "motivo":
                    solicitacao.setMotivo(value);
                    break;
                case "horasSolicitadas":
                    solicitacao.setHorasSolicitadas(Integer.parseInt(value));
                    break;
                default:
                    break;
            }
        });

        Solicitacao solicitacoesAtualizadas = solicitacaoService.save(solicitacao);
        return ResponseEntity.status(HttpStatus.OK).body(SolicitacaoDTO.fromEntity(solicitacoesAtualizadas));
    }
}
