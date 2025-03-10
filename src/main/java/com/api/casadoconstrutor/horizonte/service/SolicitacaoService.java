package com.api.casadoconstrutor.horizonte.service;

import com.api.casadoconstrutor.horizonte.dto.SolicitacaoDTO;
import com.api.casadoconstrutor.horizonte.enuns.StatusSolicitacao;
import com.api.casadoconstrutor.horizonte.model.Solicitacao;
import com.api.casadoconstrutor.horizonte.repository.SolicitacaoRepository;
import com.api.casadoconstrutor.horizonte.user.User;
import com.api.casadoconstrutor.horizonte.user.UserRole;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SolicitacaoService {

    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    public Solicitacao save(Solicitacao solicitacao) {
        return solicitacaoRepository.save(solicitacao);
    }

    public List<Solicitacao> getSolicitacoesByUser(User user) {
        return solicitacaoRepository.findByUserId(user.getId());
    }

    @Transactional
    public void limparSolicitacoes() {
        LocalDateTime limite = LocalDateTime.now().minusHours(24);
        solicitacaoRepository.deleteByDataCriacaoBefore(limite);
    }

    public Solicitacao criarSolicitacao(Solicitacao solicitacao, User user) {
        solicitacao.setUser(user);
        solicitacao.setStatus(StatusSolicitacao.PENDENTE);
        return solicitacaoRepository.save(solicitacao);
    }

    public Solicitacao aprovarOuRejeitarSolicitacao(Long solicitacaoId, StatusSolicitacao novoStatus, User user) throws AccessDeniedException {
        if(user.getRole() != UserRole.ADMIN_SGHT) {
            throw new AccessDeniedException("Apenas usuários ADMIN podem alterar o status das solicitacoes");
        }

        Solicitacao solicitacao = solicitacaoRepository.findById(solicitacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Solicitação não encontrada!"));
        solicitacao.setStatus(novoStatus);

        return solicitacaoRepository.save(solicitacao);
    }

    public List<Solicitacao> findAll() {
        return solicitacaoRepository.findAll();
    }

    public List<SolicitacaoDTO> findAllSolicitacoesWithUsers() {
        List<Solicitacao> solicitacoes = solicitacaoRepository.findAllWithUser();
        return solicitacoes.stream()
                .map(SolicitacaoDTO::fromEntity).toList();
    }

    public Optional<Solicitacao> findById(Long id) {
        return solicitacaoRepository.findById(id);
    }

    public void delete(Solicitacao solicitacao) {
        solicitacaoRepository.delete(solicitacao);
    }

    public void marcarTodasComoVistas() {
        List<Solicitacao> naoVistas = solicitacaoRepository.findNaoVistas();
        naoVistas.forEach(s -> s.setVisto(true));
        solicitacaoRepository.saveAll(naoVistas);
    }

    public List<SolicitacaoDTO> buscarNaoVistas() {
        List<Solicitacao> naoVistas = solicitacaoRepository.findByVistoFalse();
        return naoVistas.stream()
                .map(SolicitacaoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<Solicitacao> findByUserLogin(String login) {
        return solicitacaoRepository.findByUserLogin(login);
    }

    public List<Solicitacao> findByMotivo(String motivo) {
        return solicitacaoRepository.findByMotivoContaining(motivo);
    }




}
