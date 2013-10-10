package com.proyecto.dominio;

public class IntegerString {
	private Integer entero;
	private String cadena;
	
	public IntegerString(){
	}
	
	public IntegerString(Integer entero, String cadena){
		this.entero = entero;
		this.cadena = cadena;
	}

	public Integer getEntero() {
		return entero;
	}

	public void setEntero(Integer entero) {
		this.entero = entero;
	}

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}
	
	

}
