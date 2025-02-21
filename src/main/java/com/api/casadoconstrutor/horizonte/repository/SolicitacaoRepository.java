package com.api.casadoconstrutor.horizonte.repository;

import com.api.casadoconstrutor.horizonte.model.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {

    @Query("SELECT s FROM Solicitacao s WHERE s.user.id = :userId")
    List<Solicitacao> findByUserId(@Param("userId") String userId);

    List<Solicitacao> findAll();

    @Query("SELECT s FROM Solicitacao s JOIN FETCH s.user")
    List<Solicitacao> findAllWithUser();

    @Query("SELECT s FROM Solicitacao s WHERE s.visto = false")
    List<Solicitacao> findNaoVistas();

    List<Solicitacao> findByVistoFalse();

    @Query("SELECT s FROM Solicitacao s WHERE s.user.login = :login")
    List<Solicitacao> findByUserLogin(@Param("login") String login);

    List<Solicitacao> findByMotivoContaining(String motivo);

    void deleteByDataCriacaoBefore(LocalDateTime data);

}
