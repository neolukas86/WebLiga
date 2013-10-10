package com.proyecto.dominio;

import java.util.Set;

public class TipoActividad {
	private int id;
	private String nombre;
	private Set<Actividad> actividades;
	
	// Constructor Público
	public TipoActividad(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(Set<Actividad> actividades) {
		this.actividades = actividades;
	}

	
	
}
