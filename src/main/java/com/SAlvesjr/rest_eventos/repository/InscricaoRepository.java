package com.SAlvesjr.rest_eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SAlvesjr.rest_eventos.model.Inscricao;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
	
}
