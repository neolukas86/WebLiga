package com.proyecto.dominio;

import java.util.Set;

public class CategoriaNoticia {
	private int id;
	private String nombre;
	private String titulo_CA;
	private String titulo_ES;
	private String titulo_EN;
	private String mensaje_CA;
	private String mensaje_ES;
	private String mensaje_EN;
	private Boolean ppioMensaje;
	private Boolean finalMensaje;
	private Set<Noticia> noticias;
	
	public CategoriaNoticia(){		
	}

	public CategoriaNoticia(String nombre){
		this.nombre = nombre;
	}

	// Métodos getter y setter
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

	public Set<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(Set<Noticia> noticias) {
		this.noticias = noticias;
	}

	public String getTitulo_CA() {
		return titulo_CA;
	}

	public void setTitulo_CA(String titulo_CA) {
		this.titulo_CA = titulo_CA;
	}

	public String getTitulo_ES() {
		return titulo_ES;
	}

	public void setTitulo_ES(String titulo_ES) {
		this.titulo_ES = titulo_ES;
	}

	public String getTitulo_EN() {
		return titulo_EN;
	}

	public void setTitulo_EN(String titulo_EN) {
		this.titulo_EN = titulo_EN;
	}

	public String getMensaje_CA() {
		return mensaje_CA;
	}

	public void setMensaje_CA(String mensaje_CA) {
		this.mensaje_CA = mensaje_CA;
	}

	public String getMensaje_ES() {
		return mensaje_ES;
	}

	public void setMensaje_ES(String mensaje_ES) {
		this.mensaje_ES = mensaje_ES;
	}

	public String getMensaje_EN() {
		return mensaje_EN;
	}

	public void setMensaje_EN(String mensaje_EN) {
		this.mensaje_EN = mensaje_EN;
	}
	
	public Boolean getPpioMensaje() {
		return ppioMensaje;
	}
	public void setPpioMensaje(Boolean ppioMensaje) {
		this.ppioMensaje = ppioMensaje;
	}
	public Boolean getFinalMensaje() {
		return finalMensaje;
	}
	public void setFinalMensaje(Boolean finalMensaje) {
		this.finalMensaje = finalMensaje;
	}
	
}
