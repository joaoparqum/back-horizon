package com.api.casadoconstrutor.horizonte.repository;

import com.api.casadoconstrutor.horizonte.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    List<Documento> findByNomeVistaContaining(String nomeVista);
}
