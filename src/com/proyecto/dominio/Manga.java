package com.proyecto.dominio;

public class Manga {
	private int id;
	private int juegosLocal;
	private int juegosVisitante;
	private PartidoIndividualVersus partidoIndVersus;
	private PartidoEquiposVersus partidoEqVersus;
	private int numero;

	// Constructor Público
	public Manga(){	
	}
	
	public Manga(PartidoIndividualVersus partidoVS, int num){
		partidoIndVersus = partidoVS;
		partidoEqVersus = null;
		numero = num;
	}
	
	public Manga(PartidoEquiposVersus partidoVS, int num){
		partidoEqVersus = partidoVS;
		partidoIndVersus = null;
		numero = num;
	}
	
	public Manga(PartidoIndividualVersus partidoVS, int num,int juegosLoc,int juegosVis){
		partidoIndVersus = partidoVS;
		partidoEqVersus = null;
		numero = num;
		juegosLocal = juegosLoc;
		juegosVisitante = juegosVis;
	}
	
	public Manga(PartidoEquiposVersus partidoVS, int num,int juegosLoc,int juegosVis){
		partidoEqVersus = partidoVS;
		partidoIndVersus = null;
		numero = num;
		juegosLocal = juegosLoc;
		juegosVisitante = juegosVis;
	}
	// Métodos getters y setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getJuegosLocal() {
		return juegosLocal;
	}
	public void setJuegosLocal(int juegosLocal) {
		this.juegosLocal = juegosLocal;
	}

	public int getJuegosVisitante() {
		return juegosVisitante;
	}

	public void setJuegosVisitante(int juegosVisitante) {
		this.juegosVisitante = juegosVisitante;
	}

	public PartidoIndividualVersus getPartidoIndVersus() {
		return partidoIndVersus;
	}

	public void setPartidoIndVersus(PartidoIndividualVersus partidoIndVersus) {
		this.partidoIndVersus = partidoIndVersus;
	}

	public PartidoEquiposVersus getPartidoEqVersus() {
		return partidoEqVersus;
	}

	public void setPartidoEqVersus(PartidoEquiposVersus partidoEqVersus) {
		this.partidoEqVersus = partidoEqVersus;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}

}
