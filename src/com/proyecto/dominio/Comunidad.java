package com.proyecto.dominio;

import java.sql.Date;
import java.util.*;

public class Comunidad {
	private int id;
	private String nombre;
	private Date fechaCreacion;
	private int usuariosMax;
	private int torneosActivosMax;
	private int torneosEnRegistroMax;
	private int torneosMax;
	private String joinPassword;
	private boolean passwordProtected;
	private boolean regRequest;
//	private List<UserComunidad> userComunidades = new ArrayList<UserComunidad>();
	private Set<UserComunidad> userComunidades;
	private Set<EquipoComunidad> equipoComunidades;
	private Set<Torneo> torneos;
	private Set<Plataforma> plataformas;
	private Set<Actividad> actividades;
	private Set<User> usuariosRequest;
	private Set<Equipo> equiposRequest;
	
	// Constructores
	public Comunidad(){
	}
	public Comunidad(Integer id) {
		this.id = id;
	}
	public Comunidad(String nombre){
		this.nombre = nombre;
	}
	public Comunidad(String nombre,
				int usuariosMax,int torneosActivosMax,int torneosEnRegistroMax,int torneosMax){
		this.nombre = nombre;
		this.usuariosMax = usuariosMax;
		this.torneosActivosMax = torneosActivosMax;
		this.torneosEnRegistroMax = torneosEnRegistroMax;
		this.torneosMax = torneosMax;
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
	public int getUsuariosMax() {
		return usuariosMax;
	}
	public void setUsuariosMax(int usuariosMax) {
		this.usuariosMax = usuariosMax;
	}
	public int getTorneosActivosMax() {
		return torneosActivosMax;
	}
	public void setTorneosActivosMax(int torneosActivosMax) {
		this.torneosActivosMax = torneosActivosMax;
	}
	public int getTorneosEnRegistroMax() {
		return torneosEnRegistroMax;
	}
	public void setTorneosEnRegistroMax(int torneosEnRegistroMax) {
		this.torneosEnRegistroMax = torneosEnRegistroMax;
	}
	public int getTorneosMax() {
		return torneosMax;
	}
	public void setTorneosMax(int torneosMax) {
		this.torneosMax = torneosMax;
	}
	public Set<UserComunidad> getUserComunidades() {
		return userComunidades;
	}
	public void setUserComunidades(Set<UserComunidad> userComunidades) {
		this.userComunidades = userComunidades;
	}
	public Set<Torneo> getTorneos() {
		return torneos;
	}
	public void setTorneos(Set<Torneo> torneos) {
		this.torneos = torneos;
	}

	public Set<EquipoComunidad> getEquipoComunidades() {
		return equipoComunidades;
	}
	public void setEquipoComunidades(Set<EquipoComunidad> equipoComunidades) {
		this.equipoComunidades = equipoComunidades;
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
	public Set<Equipo> getEquiposRequest() {
		return equiposRequest;
	}
	public void setEquiposRequest(Set<Equipo> equiposRequest) {
		this.equiposRequest = equiposRequest;
	}
	
	
	
}
