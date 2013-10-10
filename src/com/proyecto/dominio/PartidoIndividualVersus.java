package com.proyecto.dominio;

import java.util.Set;

public class PartidoIndividualVersus{
	private Integer id;
	private Integer jornada;
	private Boolean confirmado;
	private Boolean rechazado;
	private Integer puntosLocal;
	private Integer puntosVisitante;
	private User parentLocal;
	private User parentVisitante;
	private User parentGanador;
	private User parentPerdedor;
	private User parentIntroductorResultado;
	private Set<Manga> mangas;
	private Torneo parentTorneo;
	
	
	// Constructor Público
	public PartidoIndividualVersus(){		
	}
	
	public PartidoIndividualVersus(User userLocal,User userVisitante,Torneo torneo){
		parentLocal = userLocal;
		parentVisitante = userVisitante;
		parentTorneo = torneo;
		confirmado = false;
		rechazado = false;
	}
	
	public PartidoIndividualVersus(Integer jor,User userLocal,User userVisitante,Torneo torneo){
		jornada = jor;
		parentLocal = userLocal;
		parentVisitante = userVisitante;
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

	public User getParentLocal() {
		return parentLocal;
	}

	public void setParentLocal(User parentLocal) {
		this.parentLocal = parentLocal;
	}

	public User getParentVisitante() {
		return parentVisitante;
	}

	public void setParentVisitante(User parentVisitante) {
		this.parentVisitante = parentVisitante;
	}

	public User getParentGanador() {
		return parentGanador;
	}

	public void setParentGanador(User parentGanador) {
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

	public User getParentIntroductorResultado() {
		return parentIntroductorResultado;
	}

	public void setParentIntroductorResultado(User parentIntroductorResultado) {
		this.parentIntroductorResultado = parentIntroductorResultado;
	}

	public User getParentPerdedor() {
		return parentPerdedor;
	}

	public void setParentPerdedor(User parentPerdedor) {
		this.parentPerdedor = parentPerdedor;
	}

	public Boolean getRechazado() {
		return rechazado;
	}

	public void setRechazado(Boolean rechazado) {
		this.rechazado = rechazado;
	}
	
	
}
