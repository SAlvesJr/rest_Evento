package com.SAlvesjr.rest_eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SAlvesjr.rest_eventos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
}
