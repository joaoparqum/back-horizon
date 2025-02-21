package com.api.casadoconstrutor.horizonte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SolicitacaoTask {

    @Autowired
    SolicitacaoService solicitacaoService;

    @Scheduled(fixedRate = 3600000)
    public void limparSolicitacoesExpiradas() {
        solicitacaoService.limparSolicitacoes();
    }

}
