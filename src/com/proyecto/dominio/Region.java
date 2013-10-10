package com.proyecto.dominio;

import java.util.Set;

public class Region {
	private int id;
	private String nombre;
	private Pais parentPais;
	private Set<Provincia> provincias;
	
	//Constructor Público
	public Region(){}
	
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

	public Pais getParentPais() {
		return parentPais;
	}

	public void setParentPais(Pais parentPais) {
		this.parentPais = parentPais;
	}

	public Set<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(Set<Provincia> provincias) {
		this.provincias = provincias;
	}
}
