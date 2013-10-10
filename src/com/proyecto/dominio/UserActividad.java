package com.proyecto.dominio;

public class UserActividad {
	private Boolean seguido;
	private Boolean favorito;
	private UserActividadPK id;
	
	// Constructor Público
	public UserActividad(){}

	// Getters y Setters
	public UserActividadPK getId() {
		return id;
	}

	public void setId(UserActividadPK id) {
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
