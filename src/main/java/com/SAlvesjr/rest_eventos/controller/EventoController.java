package com.SAlvesjr.rest_eventos.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SAlvesjr.rest_eventos.model.Evento;
import com.SAlvesjr.rest_eventos.repository.EventoRepository;
import com.SAlvesjr.rest_eventos.repository.UsuarioRepository;

@RestController
@RequestMapping({ "/eventos" })
public class EventoController {

	private EventoRepository eventRepository;

	private UsuarioRepository userRepository;

	public EventoController(EventoRepository eventRepository, UsuarioRepository userRepository) {
		this.eventRepository = eventRepository;
		this.userRepository = userRepository;
	}

	@GetMapping
	public List findAll() {
		return eventRepository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity findById(@PathVariable long id) {
		return eventRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Evento create(@Valid @RequestBody Evento evento) {
		return eventRepository.save(evento);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity update(@PathVariable("id") long id, @Valid @RequestBody Evento evento) {
		return eventRepository.findById(id).map(record -> {
			record.setNomeEvento(evento.getNomeEvento());
			record.setVagas(evento.getVagas());
			Evento updated = eventRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable long id) {
		return eventRepository.findById(id).map(record -> {
			eventRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(path = { "/{id}/listInsc" })
	public ResponseEntity<List<String>> findByIsnc(@PathVariable long id) {
		return eventRepository.findById(id).map(record -> {
			List<String> nameUserEvent = new ArrayList<>();

			record.getInscEvent().forEach(insc -> {
				nameUserEvent.add("nome usuario: "+ userRepository.findById(insc.getIdUser()).get().getNome());
			});

			return ResponseEntity.ok().body(nameUserEvent);
		}).orElse(ResponseEntity.notFound().build());

	}
}
