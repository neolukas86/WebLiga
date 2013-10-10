package com.proyecto.dominio;

import java.util.Set;

public class PartidoEquiposVersus{
	private Integer id;
	private Integer jornada;
	private Boolean confirmado;
	private Boolean rechazado;
	private Integer puntosLocal;
	private Integer puntosVisitante;
	private Equipo parentLocal;
	private Equipo parentVisitante;
	private Equipo parentGanador;
	private Equipo parentPerdedor;
	private Equipo parentIntroductorResultado;
	private Set<Manga> mangas;
	private Torneo parentTorneo;
	
	// Constructor Público
	public PartidoEquiposVersus(){		
	}
	
	public PartidoEquiposVersus(Equipo equipoLocal,Equipo equipoVisitante,Torneo torneo){
		parentLocal = equipoLocal;
		parentVisitante = equipoVisitante;
		parentTorneo = torneo;
		confirmado = false;
		rechazado = false;
	}
	
	public PartidoEquiposVersus(Integer jor,Equipo equipoLocal,Equipo equipoVisitante,Torneo torneo){
		jornada = jor;
		parentLocal = equipoLocal;
		parentVisitante = equipoVisitante;
		parentTorneo = torneo;
		confirmado = false;
		rechazado = false;
	}
	
	// Métodos getters y setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getJornada() {
		return jornada;
	}

	public void setJornada(Integer jornada) {
		this.jornada = jornada;
	}

	public Equipo getParentLocal() {
		return parentLocal;
	}
	public void setParentLocal(Equipo parentLocal) {
		this.parentLocal = parentLocal;
	}

	public Equipo getParentVisitante() {
		return parentVisitante;
	}

	public void setParentVisitante(Equipo parentVisitante) {
		this.parentVisitante = parentVisitante;
	}

	public Equipo getParentGanador() {
		return parentGanador;
	}

	public void setParentGanador(Equipo parentGanador) {
		this.parentGanador = parentGanador;
	}

	public Boolean getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(Boolean confirmado) {
		this.confirmado = confirmado;
	}

	public Integer getPuntosLocal() {
		return puntosLocal;
	}

	public void setPuntosLocal(Integer puntosLocal) {
		this.puntosLocal = puntosLocal;
	}

	public Integer getPuntosVisitante() {
		return puntosVisitante;
	}

	public void setPuntosVisitante(Integer puntosVisitante) {
		this.puntosVisitante = puntosVisitante;
	}

	public Set<Manga> getMangas() {
		return mangas;
	}

	public void setMangas(Set<Manga> mangas) {
		this.mangas = mangas;
	}

	public Torneo getParentTorneo() {
		return parentTorneo;
	}

	public void setParentTorneo(Torneo parentTorneo) {
		this.parentTorneo = parentTorneo;
	}
	
	public Equipo getParentPerdedor() {
		return parentPerdedor;
	}

	public void setParentPerdedor(Equipo parentPerdedor) {
		this.parentPerdedor = parentPerdedor;
	}

	public Equipo getParentIntroductorResultado() {
		return parentIntroductorResultado;
	}

	public void setParentIntroductorResultado(Equipo parentIntroductorResultado) {
		this.parentIntroductorResultado = parentIntroductorResultado;
	}

	public Boolean getRechazado() {
		return rechazado;
	}

	public void setRechazado(Boolean rechazado) {
		this.rechazado = rechazado;
	}

}

