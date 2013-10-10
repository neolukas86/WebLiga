package com.proyecto.dominio;

import java.util.Set;

public class Continente {
	private int id;
	private String rutabandera;
	private String nombre;
	private Set<Pais> paises;
	
	// Constructor Público
	public Continente(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRutabandera() {
		return rutabandera;
	}
	public void setRutabandera(String rutabandera) {
		this.rutabandera = rutabandera;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Set<Pais> getPaises() {
		return paises;
	}
	public void setPaises(Set<Pais> paises) {
		this.paises = paises;
	}

}
