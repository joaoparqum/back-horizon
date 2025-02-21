package com.api.casadoconstrutor.horizonte.repository;

import com.api.casadoconstrutor.horizonte.model.HorasValidas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorasRepository extends JpaRepository<HorasValidas, Long> {

    List<HorasValidas> findByNomeColaboradorContaining(String nomeColaborador);
}
