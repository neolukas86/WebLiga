package com.proyecto.dominio;

import java.util.Set;
import java.util.Date;

public class Torneo {
	private int id;
	private String nombre;
	private Date fechaCreacion;
	private Date fechaComienzo;
	private Date fechaFinal;
	private Boolean online;
	private Boolean porEquipos;
	private Boolean liga;
	private Boolean sorteado;
	private Integer rondas;
	private Integer estado;
	private Integer jornadasJugables;
	private Integer jornadaActual;
	private Boolean modoEnfrentamiento; //false para VS, y 1 para TipoCarrera
	private boolean passwordProtected;
	private String joinPassword;
	private boolean regRequest;
	private Comunidad parentComunidad;
	private Plataforma parentPlataforma;
	private Set<UserTorneo> userTorneos;
	private Set<EquipoTorneo> equipoTorneos;
	private Set<PartidoIndividualVersus> partidosIndVersus;
	private Set<PartidoEquiposVersus> partidosEqVersus;
	private Actividad parentActividad;
	private Set<User> usuariosRequest;
	private Set<Equipo> equiposRequest;
	private Regla parentRegla;

	// Constructor Público
	public Torneo(){		
	}
	public Torneo(Integer id){
		this.id=id;
	}
	public Torneo(String nombre){
		this.nombre=nombre;
	}
	public Torneo(String nombre,Date fechaComienzo,Date fechaFinal,boolean online){
		this.nombre=nombre;
		this.fechaComienzo=fechaComienzo;
		this.fechaFinal=fechaFinal;
		this.online=online;
	}
	
	
	// Métodos getters y setters
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
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaComienzo() {
		return fechaComienzo;
	}
	public void setFechaComienzo(Date value) {
		fechaComienzo = value;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date value) {
		fechaFinal = value;
	}
	public Boolean getOnline() {
		return online;
	}
	public void setOnline(Boolean online) {
		this.online = online;
	}	
	public Boolean getPorEquipos() {
		return porEquipos;
	}
	public void setPorEquipos(Boolean porEquipos) {
		this.porEquipos = porEquipos;
	}
	public Boolean getLiga() {
		return liga;
	}
	public void setLiga(Boolean liga) {
		this.liga = liga;
	}
	public Integer getRondas() {
		return rondas;
	}
	public void setRondas(Integer rondas) {
		this.rondas = rondas;
	}
	public Boolean getSorteado() {
		return sorteado;
	}
	public void setSorteado(Boolean sorteado) {
		this.sorteado = sorteado;
	}

	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public Boolean getModoEnfrentamiento() {
		return modoEnfrentamiento;
	}
	public void setModoEnfrentamiento(Boolean modoEnfrentamiento) {
		this.modoEnfrentamiento = modoEnfrentamiento;
	}
	public Comunidad getParentComunidad() {
		return parentComunidad;
	}
	public void setParentComunidad(Comunidad parentComunidad) {
		this.parentComunidad = parentComunidad;
	}

	public Set<UserTorneo> getUserTorneos() {
		return userTorneos;
	}

	public void setUserTorneos(Set<UserTorneo> userTorneos) {
		this.userTorneos = userTorneos;
	}
	
	public Set<EquipoTorneo> getEquipoTorneos() {
		return equipoTorneos;
	}
	public void setEquipoTorneos(Set<EquipoTorneo> equipoTorneos) {
		this.equipoTorneos = equipoTorneos;
	}
	public Actividad getParentActividad() {
		return parentActividad;
	}
	public void setParentActividad(Actividad parentActividad) {
		this.parentActividad = parentActividad;
	}
	public Plataforma getParentPlataforma() {
		return parentPlataforma;
	}
	public void setParentPlataforma(Plataforma parentPlataforma) {
		this.parentPlataforma = parentPlataforma;
	}
	public boolean isPasswordProtected() {
		return passwordProtected;
	}
	public void setPasswordProtected(boolean passwordProtected) {
		this.passwordProtected = passwordProtected;
	}
	public String getJoinPassword() {
		return joinPassword;
	}
	public void setJoinPassword(String joinPassword) {
		this.joinPassword = joinPassword;
	}
	public Set<PartidoIndividualVersus> getPartidosIndVersus() {
		return partidosIndVersus;
	}
	public void setPartidosIndVersus(Set<PartidoIndividualVersus> partidosIndVersus) {
		this.partidosIndVersus = partidosIndVersus;
	}

	
	public Set<PartidoEquiposVersus> getPartidosEqVersus() {
		return partidosEqVersus;
	}
	public void setPartidosEqVersus(Set<PartidoEquiposVersus> partidosEqVersus) {
		this.partidosEqVersus = partidosEqVersus;
	}
	public Integer getJornadasJugables() {
		return jornadasJugables;
	}
	public void setJornadasJugables(Integer jornadasJugables) {
		this.jornadasJugables = jornadasJugables;
	}
	public Integer getJornadaActual() {
		return jornadaActual;
	}
	public void setJornadaActual(Integer jornadaActual) {
		this.jornadaActual = jornadaActual;
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
	public Regla getParentRegla() {
		return parentRegla;
	}
	public void setParentRegla(Regla parentRegla) {
		this.parentRegla = parentRegla;
	}
	public Set<Equipo> getEquiposRequest() {
		return equiposRequest;
	}
	public void setEquiposRequest(Set<Equipo> equiposRequest) {
		this.equiposRequest = equiposRequest;
	}
	
	
	
}
