package com.SAlvesjr.rest_eventos.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Size(min = 3, max = 50 , message = "Nome deve contem 3 a 50 caracteries ")
	@NotEmpty(message = "Nome não pode ser vazio")
	private String nome;

	@ManyToMany
	private List<Inscricao> inscUser = new ArrayList<>();

	public Usuario() {
	}

	public Usuario(Long id, String nome) {
		this.id = id;
		this.nome = nome;
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

	public List<Inscricao> getInscUser() {
		return inscUser;
	}
	
	public void addInscUser(Inscricao insc) {
		inscUser.add(insc);
	}
	
	public void removeInscUser(Inscricao insc) {
		inscUser.remove(insc);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + "]";
	}
}
