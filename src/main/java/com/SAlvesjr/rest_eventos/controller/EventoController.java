package com.SAlvesjr.rest_eventos.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SAlvesjr.rest_eventos.model.Evento;
import com.SAlvesjr.rest_eventos.repository.EventoRepository;
import com.SAlvesjr.rest_eventos.repository.UsuarioRepository;

@RestController
@RequestMapping({ "/eventos" })
public class EventoController {

	private EventoRepository repository;

	private UsuarioRepository userRepository;

	public EventoController(EventoRepository eventos, UsuarioRepository userRepository) {
		this.repository = eventos;
		this.userRepository = userRepository;
	}

	@GetMapping
	public List findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Evento create(@RequestBody Evento evento) {
		return repository.save(evento);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity update(@PathVariable("id") long id, @RequestBody Evento evento) {
		return repository.findById(id).map(record -> {
			record.setNomeEvento(evento.getNomeEvento());
			record.setVagas(evento.getVagas());
			Evento updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(path = { "/{id}/user" })
	public ResponseEntity findByUserEvent(@PathVariable long id) {
		return repository.findById(id).map(record -> {
			return ResponseEntity.ok().body(record.getUsuario());
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}/removeUser/{user_id}" })
	public ResponseEntity deleteUser(@PathVariable long id, @PathVariable long user_id) {
		return repository.findById(id).map(record -> {
			record.getUsuario().remove(0);
			Evento updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping(value = "/{id}/addUser/{user_id}")
	public ResponseEntity updateUserEvent(@PathVariable("id") long id, 
			@PathVariable("user_id") long user_id,
			@RequestBody Evento evento) {
		return repository.findById(id).map(record -> {
			record.getUsuario().addAll( Arrays.asList (userRepository.findById(user_id).get() ));
			Evento updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

}
