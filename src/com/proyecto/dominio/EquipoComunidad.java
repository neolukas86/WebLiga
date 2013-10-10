package com.proyecto.dominio;

public class EquipoComunidad {
	private Integer rango;
	private EquipoComunidadPK id;
	
	// Constructor Público
	public EquipoComunidad(){}
	
	public EquipoComunidad(Integer rango){
		this.rango = rango;
	}
	// Getters y Setters

	public EquipoComunidadPK getId() {
		return id;
	}

	public void setId(EquipoComunidadPK id) {
		this.id = id;
	}

	public Integer getRango() {
		return rango;
	}

	public void setRango(Integer rango) {
		this.rango = rango;
	}
	
}
