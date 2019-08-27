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

import com.SAlvesjr.rest_eventos.model.Inscricao;
import com.SAlvesjr.rest_eventos.model.Usuario;
import com.SAlvesjr.rest_eventos.repository.EventoRepository;
import com.SAlvesjr.rest_eventos.repository.InscricaoRepository;
import com.SAlvesjr.rest_eventos.repository.UsuarioRepository;

@RestController
@RequestMapping({"/usuarios"})
public class UsuarioController {
	
	private InscricaoRepository inscRepository;

	private EventoRepository eventRepository;

	private UsuarioRepository userRepository;

	public UsuarioController(InscricaoRepository inscRepository, EventoRepository eventos,
			UsuarioRepository userRepository) {
		this.inscRepository = inscRepository;
		this.eventRepository = eventos;
		this.userRepository = userRepository;
	}
	
	@GetMapping
	public List findAll() {
		return userRepository.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity findById(@PathVariable long id) {
		return userRepository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Usuario create(@RequestBody Usuario user) {
		return userRepository.save(user);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity update(@PathVariable("id") long id, @RequestBody Usuario user) {
	   return userRepository.findById(id)
	           .map(record -> {
	               record.setNome(user.getNome());
	               Usuario updated = userRepository.save(record);
	               return ResponseEntity.ok().body(updated);
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable long id) {
	   return userRepository.findById(id)
	           .map(record -> {
	        	   userRepository.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping(value = "/inscricao")
	public Inscricao createInsc(@RequestBody Inscricao insc) {
		eventRepository.findById(insc.getIdEvent())
		.get().getInscEvent().addAll(Arrays.asList(insc));
		
		userRepository.findById(insc.getIdUser())
		.get().getInscUser().addAll(Arrays.asList(insc));
		
		return inscRepository.save(insc);
	}
	
	@DeleteMapping(path = { "/{id}/delIsnc/{insc_id}" })
	public ResponseEntity<?> deleteInsc(@PathVariable("id") long id, 
			@PathVariable("insc_id") Long insc_id) {
		return userRepository.findById(id).map(record -> {
			record.getInscUser().removeIf( x -> (x.getId() == insc_id ));			
			userRepository.save(record);
			
			eventRepository.findById( 
			inscRepository.findById(insc_id).get()
			.getIdEvent()).get().getInscEvent().removeIf(x -> (x.getId() == insc_id ));
			
			inscRepository.deleteById(insc_id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
