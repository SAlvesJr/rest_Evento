package com.SAlvesjr.rest_eventos.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.SAlvesjr.rest_eventos.model.Inscricao;
import com.SAlvesjr.rest_eventos.model.Usuario;
import com.SAlvesjr.rest_eventos.repository.EventoRepository;
import com.SAlvesjr.rest_eventos.repository.InscricaoRepository;
import com.SAlvesjr.rest_eventos.repository.UsuarioRepository;

@RestController
@RequestMapping({ "/usuarios" })
public class UsuarioController {

	private InscricaoRepository inscRepository;

	private EventoRepository eventRepository;

	private UsuarioRepository userRepository;

	public UsuarioController(InscricaoRepository inscRepository, EventoRepository eventRepository,
			UsuarioRepository userRepository) {
		this.inscRepository = inscRepository;
		this.eventRepository = eventRepository;
		this.userRepository = userRepository;
	}

	@GetMapping
	public List findAll() {
		return userRepository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity findById(@PathVariable long id) {
		return userRepository.findById(id).map(record -> {
			return ResponseEntity.ok().body(record);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Usuario create(@Valid @RequestBody Usuario user) {
		return userRepository.save(user);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity update(@PathVariable("id") long id, @Valid @RequestBody Usuario user) {
		return userRepository.findById(id).map(record -> {
			record.setNome(user.getNome());
			Usuario updated = userRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable long id) {
		return userRepository.findById(id).map(record -> {
			userRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(path = { "/{id}/listInsc" })
	public ResponseEntity<List<String>> findByInsc(@PathVariable long id) {
		return userRepository.findById(id).map(record -> {
			List<String> nameEventUser = new ArrayList<>();

			record.getInscUser().forEach(insc -> {
				nameEventUser.add("nome evento: " + eventRepository.findById(insc.getIdEvent()).get().getNomeEvento());
			});

			return ResponseEntity.ok().body(nameEventUser);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(value = "/inscricao")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario createInsc(@Valid @RequestBody Inscricao insc) {
		long idEvent = insc.getIdEvent();
		long idUser = insc.getIdUser();

		if (eventRepository.findById(idEvent).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id do evento não encontrado!");
		}

		if (userRepository.findById(idUser).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id do usuario não encontrado!");
		}

		eventRepository.findById(idEvent).get().getInscEvent().addAll(Arrays.asList(insc));

		userRepository.findById(idUser).get().getInscUser().addAll(Arrays.asList(insc));

		inscRepository.save(insc);

		return userRepository.findById(idUser).get();
	}

	@DeleteMapping(path = { "/{id}/delInsc/{insc_id}" })	
	public ResponseEntity<?> deleteInsc(@PathVariable("id") long id, @PathVariable("insc_id") Long insc_id) {		
		return userRepository.findById(id).map(record -> {
			record.getInscUser().removeIf(x -> (x.getId() == insc_id));

			long event_id = inscRepository.findById(insc_id).get().getIdEvent();

			eventRepository.findById(event_id).get().getInscEvent().removeIf(x -> (x.getId() == insc_id));

			inscRepository.deleteById(insc_id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}