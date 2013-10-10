package com.proyecto.dominio;

public class UserInvitado {
	private User solicitado;
	private User solicitante;
	
	public UserInvitado() {
		// TODO Auto-generated constructor stub
	}
	
	public UserInvitado(User solicitado, User solicitante){
		this.solicitado = solicitado;
		this.solicitante = solicitante;
	}

	public User getSolicitado() {
		return solicitado;
	}
	public void setSolicitado(User solicitado) {
		this.solicitado = solicitado;
	}
	public User getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(User solicitante) {
		this.solicitante = solicitante;
	}
}
