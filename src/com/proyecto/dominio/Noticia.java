package com.proyecto.dominio;

import java.util.Date;



public class Noticia {
	private int id;
	private String mensaje;
	private String titulo;
	private Date fechaPublicacion;
	private Date fechaEdicion;
	private Integer nuevoRango;
	private Torneo parentTorneo;
	private Comunidad parentComunidad;
	private User parentUsuario;
	private User parentAutor;
	private Equipo parentEquipo;
	private CategoriaNoticia parentCategoriaNoticia;
	

	

	
	// Constructor público
	public Noticia(){
	}
	public Noticia(String titulo,String mensaje){
		this.titulo = titulo;
		this.mensaje = mensaje;
	}

	
	
	// Métodos getters y setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public Date getFechaEdicion() {
		return fechaEdicion;
	}
	public void setFechaEdicion(Date fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}
	public Torneo getParentTorneo() {
		return parentTorneo;
	}
	public void setParentTorneo(Torneo parentTorneo) {
		this.parentTorneo = parentTorneo;
	}
	public Comunidad getParentComunidad() {
		return parentComunidad;
	}
	public void setParentComunidad(Comunidad parentComunidad) {
		this.parentComunidad = parentComunidad;
	}
	public User getParentUsuario() {
		return parentUsuario;
	}
	public void setParentUsuario(User parentUsuario) {
		this.parentUsuario = parentUsuario;
	}
	public User getParentAutor() {
		return parentAutor;
	}
	public void setParentAutor(User parentAutor) {
		this.parentAutor = parentAutor;
	}
	public Equipo getParentEquipo() {
		return parentEquipo;
	}
	public void setParentEquipo(Equipo parentEquipo) {
		this.parentEquipo = parentEquipo;
	}
	public CategoriaNoticia getParentCategoriaNoticia() {
		return parentCategoriaNoticia;
	}
	public void setParentCategoriaNoticia(CategoriaNoticia parentCategoriaNoticia) {
		this.parentCategoriaNoticia = parentCategoriaNoticia;
	}
	public Integer getNuevoRango() {
		return nuevoRango;
	}
	public void setNuevoRango(Integer nuevoRango) {
		this.nuevoRango = nuevoRango;
	}

}
