package com.proyecto.dominio;

public class EquipoInvitado {
	private Equipo solicitado;
	private Equipo solicitante;
	
	public EquipoInvitado() {
		// TODO Auto-generated constructor stub
	}
	
	public EquipoInvitado(Equipo solicitado, Equipo solicitante){
		this.solicitado = solicitado;
		this.solicitante = solicitante;
	}

	public Equipo getSolicitado() {
		return solicitado;
	}
	public void setSolicitado(Equipo solicitado) {
		this.solicitado = solicitado;
	}
	public Equipo getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(Equipo solicitante) {
		this.solicitante = solicitante;
	}
}
