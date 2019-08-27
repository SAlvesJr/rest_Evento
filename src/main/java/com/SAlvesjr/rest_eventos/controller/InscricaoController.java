package com.SAlvesjr.rest_eventos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SAlvesjr.rest_eventos.repository.InscricaoRepository;

@RestController
@RequestMapping({ "/inscricao" })
public class InscricaoController {

	private InscricaoRepository inscRepository;

	public InscricaoController(InscricaoRepository inscRepository) {
		this.inscRepository = inscRepository;
	}

	@GetMapping
	public List findAll() {
		return inscRepository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity findByInsc(@PathVariable long id) {
		return inscRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());		
	}
}
