package com.SAlvesjr.rest_eventos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SAlvesjr.rest_eventos.repository.EventoRepository;
import com.SAlvesjr.rest_eventos.repository.UsuarioRepository;

@RestController
@RequestMapping({ "/incricao" })
public class InscricaoController {
	
	private EventoRepository repository;

	private UsuarioRepository userRepository;

	public InscricaoController(EventoRepository eventos, UsuarioRepository userRepository) {
		this.repository = eventos;
		this.userRepository = userRepository;
	}	
	
}
