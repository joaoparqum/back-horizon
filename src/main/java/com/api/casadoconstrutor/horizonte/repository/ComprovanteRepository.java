package com.api.casadoconstrutor.horizonte.repository;

import com.api.casadoconstrutor.horizonte.model.Comprovante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComprovanteRepository extends JpaRepository<Comprovante, Long> {

    List<Comprovante> findByNomeComprovanteContaining(String nomeComprovante);

}
