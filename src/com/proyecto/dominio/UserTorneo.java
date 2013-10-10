package com.proyecto.dominio;


public class UserTorneo {
	private Integer rango;
	private boolean participante;
	private Integer posicion;
	private Integer puntos;
	private Integer partidosGanados;
	private Integer partidosEmpatados;
	private Integer partidosPerdidos;
	private Integer tantosFavor;
	private Integer tantosContra;
	private Float Cociente;
	private Boolean eliminado;
	// ----------------------------
	// ------- Auxiliares Sub -----
	private Integer subPuntos;
	private Integer subPartidosGanados;
	private Integer subTantosFavor;
	private Integer subTantosContra;
//	private Float subCociente;
//	private Integer subNumeroEquipos;
	// ----------------------------
	private UserTorneoPK id;

	
	// Constructor Público
	public UserTorneo(){
		this.puntos = 0;
		this.partidosGanados = 0;
		this.partidosEmpatados = 0;
		this.partidosPerdidos = 0;
		this.tantosFavor = 0;
		this.tantosContra = 0;
		
		this.subPuntos = 0;
		this.subPartidosGanados = 0;
		this.subTantosFavor = 0;
		this.subTantosContra = 0;
		this.eliminado = false;
//		this.subNumeroEquipos = 1;
	}
	
	public UserTorneo(Integer rango){
		this.rango = rango;
		this.puntos = 0;
		this.partidosGanados = 0;
		this.partidosEmpatados = 0;
		this.partidosPerdidos = 0;
		this.tantosFavor = 0;
		this.tantosContra = 0;
		
		this.subPuntos = 0;
		this.subPartidosGanados = 0;
		this.subTantosFavor = 0;
		this.subTantosContra = 0;
		this.eliminado = false;
//		this.subNumeroEquipos = 1;
	}
	
	public UserTorneo(Integer rango,Boolean participante){
		this.rango = rango;
		this.participante = participante;
		this.puntos = 0;
		this.partidosGanados = 0;
		this.partidosEmpatados = 0;
		this.partidosPerdidos = 0;
		this.tantosFavor = 0;
		this.tantosContra = 0;
		
		this.subPuntos = 0;
		this.subPartidosGanados = 0;
		this.subTantosFavor = 0;
		this.subTantosContra = 0;
		this.eliminado = false;
//		this.subNumeroEquipos = 1;
	}	
	
	// Getters y Setters

	public boolean isParticipante() {
		return participante;
	}

	public Integer getRango() {
		return rango;
	}

	public void setRango(Integer rango) {
		this.rango = rango;
	}

	public void setParticipante(boolean participante) {
		this.participante = participante;
	}

	public UserTorneoPK getId() {
		return id;
	}

	public void setId(UserTorneoPK id) {
		this.id = id;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public Integer getPartidosGanados() {
		return partidosGanados;
	}

	public void setPartidosGanados(Integer partidosGanados) {
		this.partidosGanados = partidosGanados;
	}

	public Integer getPartidosEmpatados() {
		return partidosEmpatados;
	}

	public void setPartidosEmpatados(Integer partidosEmpatados) {
		this.partidosEmpatados = partidosEmpatados;
	}

	public Integer getPartidosPerdidos() {
		return partidosPerdidos;
	}

	public void setPartidosPerdidos(Integer partidosPerdidos) {
		this.partidosPerdidos = partidosPerdidos;
	}

	public Integer getTantosFavor() {
		return tantosFavor;
	}

	public void setTantosFavor(Integer tantosFavor) {
		this.tantosFavor = tantosFavor;
	}

	public Integer getTantosContra() {
		return tantosContra;
	}

	public void setTantosContra(Integer tantosContra) {
		this.tantosContra = tantosContra;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}
	
	public Float getCociente() {
		return Cociente;
	}

	public void setCociente(Float cociente) {
		Cociente = cociente;
	}
	
	
	
	
	// -------- Auxiliares ------------

	public Integer getSubPartidosGanados() {
		return subPartidosGanados;
	}

	public void setSubPartidosGanados(Integer subPartidosGanados) {
		this.subPartidosGanados = subPartidosGanados;
	}

	public Integer getSubTantosFavor() {
		return subTantosFavor;
	}

	public void setSubTantosFavor(Integer subTantosFavor) {
		this.subTantosFavor = subTantosFavor;
	}

	public Integer getSubTantosContra() {
		return subTantosContra;
	}

	public void setSubTantosContra(Integer subTantosContra) {
		this.subTantosContra = subTantosContra;
	}

	public Integer getSubPuntos() {
		return subPuntos;
	}

	public void setSubPuntos(Integer subPuntos) {
		this.subPuntos = subPuntos;
	}

	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}
	
	

//	public Float getSubCociente() {
//		return subCociente;
//	}
//
//	public void setSubCociente(Float subCociente) {
//		this.subCociente = subCociente;
//	}
	
	
}
