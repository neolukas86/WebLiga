package com.proyecto.dominio;

public class UserPlataforma {
	private Boolean seguido;
	private Boolean favorito;
	private UserPlataformaPK id;
	
	// Constructor Público
	public UserPlataforma(){}

	// Getters y Setters
	public UserPlataformaPK getId() {
		return id;
	}

	public void setId(UserPlataformaPK id) {
		this.id = id;
	}
	
	public Boolean getSeguido() {
		return seguido;
	}

	public void setSeguido(Boolean seguido) {
		this.seguido = seguido;
	}

	public Boolean getFavorito() {
		return favorito;
	}

	public void setFavorito(Boolean favorito) {
		this.favorito = favorito;
	}

}
