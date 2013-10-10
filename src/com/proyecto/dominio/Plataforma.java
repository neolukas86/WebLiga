package com.proyecto.dominio;

import java.util.Set;

public class Plataforma {
	private int id;
	private String nombre;
	private String rutaimagen;
	private Set<Torneo> torneos;
	private Set<Equipo> equipos;
	private Set<Comunidad> comunidades;
	private Set<UserPlataforma> userPlataformas;
	
	// Constructor Público
	public Plataforma(){	
	}

	// Métodos getter y setter
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

	public String getRutaimagen() {
		return rutaimagen;
	}

	public void setRutaimagen(String rutaimagen) {
		this.rutaimagen = rutaimagen;
	}

	public Set<Torneo> getTorneos() {
		return torneos;
	}

	public void setTorneos(Set<Torneo> torneos) {
		this.torneos = torneos;
	}

	public Set<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}

	public Set<Comunidad> getComunidades() {
		return comunidades;
	}

	public void setComunidades(Set<Comunidad> comunidades) {
		this.comunidades = comunidades;
	}

	public Set<UserPlataforma> getUserPlataformas() {
		return userPlataformas;
	}

	public void setUserPlataformas(Set<UserPlataforma> userPlataformas) {
		this.userPlataformas = userPlataformas;
	}
	
}
