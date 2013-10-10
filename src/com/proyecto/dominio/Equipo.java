package com.proyecto.dominio;

import java.util.Set;

public class Equipo {
	private int id;
	private String nombre;
	private String tag;
	private String homepage;
	private String joinPassword;
	private Boolean invitado;
	private Boolean expulsado;
	private boolean passwordProtected;
	private boolean regRequest;
	private Set<EquipoComunidad> equipoComunidades;
	private Set<EquipoTorneo> equipoTorneos;
	private Set<UserEquipo> userEquipos;
	private Set<PartidoEquiposVersus> partidosVSLocal;
	private Set<PartidoEquiposVersus> partidosVSVisitante;
	private Set<PartidoEquiposVersus> partidosVSGanados;
	private Set<PartidoEquiposVersus> partidosVSPerdidos;
	private Set<Plataforma> plataformas;
	private Set<Actividad> actividades;
	private Set<User> usuariosRequest;
	private Set<Comunidad> comunidadesRequest;
	private Set<Torneo> torneosRequest;
	private Set<Equipo> equiposRequest;
	private Set<Equipo> invitadosRequest;
	
	//Constructor público
	public Equipo(){}
	
	public Equipo(Integer id){
		this.id = id;
	}
	
	public Equipo(String nombre){
		this.nombre = nombre;
	}
	
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public Set<UserEquipo> getUserEquipos() {
		return userEquipos;
	}

	public void setUserEquipos(Set<UserEquipo> userEquipos) {
		this.userEquipos = userEquipos;
	}

	public Set<EquipoComunidad> getEquipoComunidades() {
		return equipoComunidades;
	}

	public void setEquipoComunidades(Set<EquipoComunidad> equipoComunidades) {
		this.equipoComunidades = equipoComunidades;
	}

	public Set<EquipoTorneo> getEquipoTorneos() {
		return equipoTorneos;
	}

	public void setEquipoTorneos(Set<EquipoTorneo> equipoTorneos) {
		this.equipoTorneos = equipoTorneos;
	}

	public Set<PartidoEquiposVersus> getPartidosVSLocal() {
		return partidosVSLocal;
	}

	public void setPartidosVSLocal(Set<PartidoEquiposVersus> partidosVSLocal) {
		this.partidosVSLocal = partidosVSLocal;
	}

	public Set<PartidoEquiposVersus> getPartidosVSVisitante() {
		return partidosVSVisitante;
	}

	public void setPartidosVSVisitante(Set<PartidoEquiposVersus> partidosVSVisitante) {
		this.partidosVSVisitante = partidosVSVisitante;
	}

	public Set<PartidoEquiposVersus> getPartidosVSGanados() {
		return partidosVSGanados;
	}

	public void setPartidosVSGanados(Set<PartidoEquiposVersus> partidosVSGanados) {
		this.partidosVSGanados = partidosVSGanados;
	}

	public Set<Plataforma> getPlataformas() {
		return plataformas;
	}

	public void setPlataformas(Set<Plataforma> plataformas) {
		this.plataformas = plataformas;
	}

	public Set<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(Set<Actividad> actividades) {
		this.actividades = actividades;
	}

	public Set<PartidoEquiposVersus> getPartidosVSPerdidos() {
		return partidosVSPerdidos;
	}

	public void setPartidosVSPerdidos(Set<PartidoEquiposVersus> partidosVSPerdidos) {
		this.partidosVSPerdidos = partidosVSPerdidos;
	}
	
	public String getJoinPassword() {
		return joinPassword;
	}
	public void setJoinPassword(String joinPassword) {
		this.joinPassword = joinPassword;
	}
	public boolean isPasswordProtected() {
		return passwordProtected;
	}
	public void setPasswordProtected(boolean passwordProtected) {
		this.passwordProtected = passwordProtected;
	}

	public boolean isRegRequest() {
		return regRequest;
	}

	public void setRegRequest(boolean regRequest) {
		this.regRequest = regRequest;
	}

	public Set<User> getUsuariosRequest() {
		return usuariosRequest;
	}

	public void setUsuariosRequest(Set<User> usuariosRequest) {
		this.usuariosRequest = usuariosRequest;
	}

	public Set<Comunidad> getComunidadesRequest() {
		return comunidadesRequest;
	}

	public void setComunidadesRequest(Set<Comunidad> comunidadesRequest) {
		this.comunidadesRequest = comunidadesRequest;
	}

	public Set<Torneo> getTorneosRequest() {
		return torneosRequest;
	}

	public void setTorneosRequest(Set<Torneo> torneosRequest) {
		this.torneosRequest = torneosRequest;
	}

	public Set<Equipo> getEquiposRequest() {
		return equiposRequest;
	}

	public void setEquiposRequest(Set<Equipo> equiposRequest) {
		this.equiposRequest = equiposRequest;
	}

	public Set<Equipo> getInvitadosRequest() {
		return invitadosRequest;
	}

	public void setInvitadosRequest(Set<Equipo> invitadosRequest) {
		this.invitadosRequest = invitadosRequest;
	}

	public Boolean getInvitado() {
		return invitado;
	}

	public void setInvitado(Boolean invitado) {
		this.invitado = invitado;
	}

	public Boolean getExpulsado() {
		return expulsado;
	}

	public void setExpulsado(Boolean expulsado) {
		this.expulsado = expulsado;
	}
	
	
}
