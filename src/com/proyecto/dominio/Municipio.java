package com.proyecto.dominio;

import java.util.Set;

public class Municipio {
	private int id;
	private String nombre;
	private Provincia parentProvincia;
	private Set<User> usuarios;
	
	//Constructor Público
	public Municipio(){}
	
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

	public Provincia getParentProvincia() {
		return parentProvincia;
	}

	public void setParentProvincia(Provincia parentProvincia) {
		this.parentProvincia = parentProvincia;
	}

	public Set<User> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<User> usuarios) {
		this.usuarios = usuarios;
	}

}
