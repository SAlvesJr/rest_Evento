package com.SAlvesjr.rest_eventos.model;

import javax.persistence.Entity;

@Entity
public class Inscricao {
	
	private Long id;
	private Long idEvent;
	private Long idUser;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(Long idEvent) {
		this.idEvent = idEvent;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
}
