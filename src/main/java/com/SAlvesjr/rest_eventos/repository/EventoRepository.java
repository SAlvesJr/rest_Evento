package com.SAlvesjr.rest_eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SAlvesjr.rest_eventos.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
	
}
