package com.proyecto.dominio;

public class UserComunidad {
	private Integer rango;
	private UserComunidadPK id;
	
	// Constructor Público
	public UserComunidad(){}
	
	public UserComunidad(Integer rango){
		this.rango = rango;
	}
	// Getters y Setters

	public UserComunidadPK getId() {
		return id;
	}

	public void setId(UserComunidadPK id) {
		this.id = id;
	}

	public Integer getRango() {
		return rango;
	}

	public void setRango(Integer rango) {
		this.rango = rango;
	}
	
}
