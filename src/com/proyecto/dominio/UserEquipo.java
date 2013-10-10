package com.proyecto.dominio;

public class UserEquipo {
	private Integer rango;
	private UserEquipoPK id;
	
	// Constructor Público
	public UserEquipo(){}
	
	public UserEquipo(Integer rango){
		this.rango = rango;
	}
	
	
	// Getters y Setters

	public UserEquipoPK getId() {
		return id;
	}

	public Integer getRango() {
		return rango;
	}

	public void setRango(Integer rango) {
		this.rango = rango;
	}

	public void setId(UserEquipoPK id) {
		this.id = id;
	}

}
