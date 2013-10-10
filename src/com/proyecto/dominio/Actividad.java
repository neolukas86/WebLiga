package com.proyecto.dominio;

import java.util.Set;

public class Actividad {
	private int id;
	private String nombre;
	private String nombre_EN;
	private String nombre_CA;
	private String rutaimagen;
	private Integer ptosVictoria;
	private Boolean empate;
	private TipoActividad parentTipo;
	private Regla parentReglaPorDefecto;
	private Set<Torneo> torneos;
	private Set<Equipo> equipos;
	private Set<Comunidad> comunidades;
	private Set<UserActividad> userActividades;
	
	// Constructor Público
	public Actividad(){}
	
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

	public String getRutaimagen() {
		return rutaimagen;
	}
	public void setRutaimagen(String rutaimagen) {
		this.rutaimagen = rutaimagen;
	}

	public TipoActividad getParentTipo() {
		return parentTipo;
	}

	public void setParentTipo(TipoActividad parentTipo) {
		this.parentTipo = parentTipo;
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

	public Set<UserActividad> getUserActividades() {
		return userActividades;
	}

	public void setUserActividades(Set<UserActividad> userActividades) {
		this.userActividades = userActividades;
	}

	public Integer getPtosVictoria() {
		return ptosVictoria;
	}

	public void setPtosVictoria(Integer ptosVictoria) {
		this.ptosVictoria = ptosVictoria;
	}

	public Regla getParentReglaPorDefecto() {
		return parentReglaPorDefecto;
	}

	public void setParentReglaPorDefecto(Regla parentReglaPorDefecto) {
		this.parentReglaPorDefecto = parentReglaPorDefecto;
	}

	public Boolean getEmpate() {
		return empate;
	}

	public void setEmpate(Boolean empate) {
		this.empate = empate;
	}
		
	
}
