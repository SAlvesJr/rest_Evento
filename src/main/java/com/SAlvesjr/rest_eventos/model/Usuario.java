package com.SAlvesjr.rest_eventos.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@ManyToOne
	private Evento eventos;
	
	public Usuario() {
	}

	public Usuario(Long id, String nome, Evento eventos) {
		this.id = id;
		this.nome = nome;
		this.setEventos(eventos);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Evento getEventos() {
		return eventos;
	}

	public void setEventos(Evento eventos) {
		this.eventos = eventos;
	}	
}
