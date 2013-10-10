package com.proyecto.dominio;

import java.util.Set;

public class Pais {
	private int id;
	private String rutabandera;
	private String nombre;
	private String nombre_EN;
	private String nombre_CA;
	private Continente parentContinente;
	private Set<Region> regiones;
	private Set<User> usuarios; // Esto es necesario pq puedes tener pais pero municipio a NULL, si no se podría eliminar.
	
	// Constructor Público
	public Pais(){}
	
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
	public Continente getParentContinente() {
		return parentContinente;
	}
	public void setParentContinente(Continente parentContinente) {
		this.parentContinente = parentContinente;
	}
	public Set<Region> getRegiones() {
		return regiones;
	}
	public void setRegiones(Set<Region> regiones) {
		this.regiones = regiones;
	}
	public Set<User> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Set<User> usuarios) {
		this.usuarios = usuarios;
	}

	public String getNombre_EN() {
		return nombre_EN;
	}

	public void setNombre_EN(String nombre_EN) {
		this.nombre_EN = nombre_EN;
	}

	public String getNombre_CA() {
		return nombre_CA;
	}

	public void setNombre_CA(String nombre_CA) {
		this.nombre_CA = nombre_CA;
	}
	
}
