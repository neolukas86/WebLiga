package com.proyecto.util.funciones.auxiliares;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import com.opensymphony.xwork2.Action;
import com.proyecto.dominio.EquipoTorneo;
import com.proyecto.dominio.UserTorneo;
import com.proyecto.util.properties.PropertiesUtil;

/**
* Clase con las funciones auxiliares
* @author Lucas Sánchez López
* @version 3.0
*/
public class FuncionesAuxiliares {
	public static final int CREADOR = 0;
	public static final int ADMIN = 1;
	public static final int MOD = 2;
	public static final int POWERUSER = 3;
	public static final int NORMALUSER = 4;
	public static final int EXMIEMBRO = 5;
	public static final int BANEADO = 6;
	public static final int NUMERO_NO_VALIDO = 9999;
	public final static int REG_CERRADOS = 0;
	public final static int REG_ABIERTOS = 1;
	public final static int EN_JUEGO = 2;
	public final static int FINALIZADO = 3;
	public static final String contra = "invliga86";
	
	/**
	 * Usado para comprobar los permisos 
	 * @param rol Permisos necesarios
	 * @param nivel Permisos actuales
	 * @return SUCCESS en caso de éxito y ERROR en caso de error
	 */
	public static String ComprobarPermisos(Integer rol, Integer nivel){
		if(rol <= nivel) return Action.SUCCESS;
		
		else 			 return Action.ERROR;
	}
	
	/**
	 * Usado para comprobar si hay que construir el
	 * siguiente trozo de la sentencia con where o and
	 * @param where Cadena con la sentencia a consultar
	 * @return Cadena "where" o "and"
	 */
	public static String WhereOrAnd(String where){
		String cadena;
		
		if(where != null && !where.equals("")){
			cadena = " and"; 
		}
		else{
			cadena = " where";
		}		
		return cadena;
	}
	
	/**
	 * Transforma en cadena el rango pasado por entero
	 * @param rango Entero del rango
	 * @return Cadena con el rango
	 */
	public static String StringRango(int rango){
		
		switch(rango){
			case CREADOR:
				return "creador";
			case ADMIN:
				return "admin";
			case MOD:
				return "mod";
			case POWERUSER:
				return "poweruser";
			case NORMALUSER:
				return "normaluser";
			case EXMIEMBRO:
				return "exmiembro";
			case BANEADO:
				return "baneado";
			default:
				return " ";
		}

	}
	
	/**
	 * Carga el archivo de propiedas correspondiente
	 * al idioma en uso 
	 * @param locale Objeto que determina el idioma en uso
	 * @param ruta Ruta del archivo de propiedades
	 * @return El archivo de propiedades
	 */
	public static Properties ArchivoPropiedades(Locale locale,String ruta){
		Properties propsError = new Properties();
		
		if(locale.equals(Locale.ENGLISH)){
			propsError = PropertiesUtil.loadProperties(ruta+"_en.properties");
		}
		else if(locale.getLanguage().equals("ca")){
			propsError = PropertiesUtil.loadProperties(ruta+"_ca.properties");
		}
		else {
			propsError = PropertiesUtil.loadProperties(ruta+"_es.properties");
		}
		
		return propsError;
	}
	
	/**
	 * Comprueba la posición más alta de los usuarios en el torneo
	 * @param utIguales Lista de objetos UserTorneo a comparar
	 * @return Número de la posición más alta
	 */
	public static Integer SacarPosicionMasAltaUserTorneo(List<UserTorneo> utIguales){
		Integer posicionMasAlta = Integer.MAX_VALUE;
		
		Iterator<UserTorneo> it = utIguales.iterator();
		
		while(it.hasNext()){
			UserTorneo ut = it.next();
			
			if(ut.getPosicion() < posicionMasAlta){ // Para sacar el equipo que está mas arriba
				posicionMasAlta = ut.getPosicion();
			}
		}
		
		return posicionMasAlta;
	}
	
	/**
	 * Comprueba la posición más alta de los equipos en el torneo
	 * @param etIguales Lista de objetos EquipoTorneo a comparar
	 * @return Número de la posición más alta
	 */
	public static Integer SacarPosicionMasAltaEquipoTorneo(List<EquipoTorneo> etIguales){
		Integer posicionMasAlta = Integer.MAX_VALUE;
		
		Iterator<EquipoTorneo> it = etIguales.iterator();
		
		while(it.hasNext()){
			EquipoTorneo et = it.next();
			
			if(et.getPosicion() < posicionMasAlta){ // Para sacar el equipo que está mas arriba
				posicionMasAlta = et.getPosicion();
			}
		}
		
		return posicionMasAlta;
	}	
	
	/**
	 * Comprueba la posición más baja de los usuarios en el torneo
	 * @param utIguales Lista de objetos UserTorneo a comparar
	 * @return Número de la posición más baja
	 */
	public static Integer SacarPosicionMasBajaUserTorneo(List<UserTorneo> utIguales){ // Será solo para ordenar toda la lista
		Integer posicionMasBaja;
		
		List<Integer> array = new ArrayList<Integer>(); 
		
		Iterator<UserTorneo> it = utIguales.iterator();
		
		while(it.hasNext()){
			UserTorneo ut = it.next();
			
			array.add(ut.getPosicion());
		}
		
		posicionMasBaja = 1;
		while(array.contains(posicionMasBaja)){
			posicionMasBaja++;
		}
		
		// Hago esto pq puede ser expulsado un equipo con una posicion intermedia, 
		//  entonces quedaria ese puesto vacio

		System.out.println("Retorna --> "+posicionMasBaja);
		
		return posicionMasBaja;
	}
	
	/**
	 * Comprueba la posición más baja de los equipos en el torneo
	 * @param etIguales Lista de objetos EquipoTorneo a comparar
	 * @return Número de la posición más baja
	 */
	public static Integer SacarPosicionMasBajaEquipoTorneo(List<EquipoTorneo> etIguales){ // Será solo para ordenar toda la lista
		Integer posicionMasBaja;
		
		List<Integer> array = new ArrayList<Integer>(); 
		
//		System.out.println("Tamaño de la lista de EquipoTorneo --> "+etIguales.size());
		
		Iterator<EquipoTorneo> it = etIguales.iterator();
		
		while(it.hasNext()){
			EquipoTorneo et = it.next();
			
			array.add(et.getPosicion());
		}
		
		posicionMasBaja = 1;
		while(array.contains(posicionMasBaja)){
			posicionMasBaja++;
		}
		
		// Hago esto pq puede ser expulsado un equipo con una posicion intermedia, 
		//  entonces quedaria ese puesto vacio

		System.out.println("Retorna --> "+posicionMasBaja);
		
		return posicionMasBaja;
	}
	
	public static int getCreador() {
		return CREADOR;
	}
	public static int getAdmin() {
		return ADMIN;
	}
	public static int getMod() {
		return MOD;
	}
	public static int getPoweruser() {
		return POWERUSER;
	}
	public static int getNormaluser() {
		return NORMALUSER;
	}
	public static int getExmiembro() {
		return EXMIEMBRO;
	}
	public static int getBaneado() {
		return BANEADO;
	}
	public static int getNumeroNoValido() {
		return NUMERO_NO_VALIDO;
	}
	
}
