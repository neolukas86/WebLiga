package com.proyecto.util.listas;

import com.proyecto.dominio.UserTorneo;

/**
 * Clase que define funciones de comparacion de los usuarios en un torneo
 * @author Lucas S�nchez L�pez
 * @version 1.0
 */
public class AverageGlobalUserTorneo {

	/**
	 * Compara Averaje global, y despu�s tantos a favor global
	 * de dos usuarios en un torneo
	 * @param ut1 Usuario en Torneo a comparar
	 * @param ut2 Usuario en Torneo a comparar
	 * @return un negativo si ut1 es menor, cero si son iguales, o positivo si u1 es mayor que ut2.
	 */
	public static int AverageFavorGlobal(UserTorneo ut1,UserTorneo ut2){
		if((ut2.getTantosFavor() - ut2.getTantosContra()) - (ut1.getTantosFavor()-ut1.getTantosContra())!= 0){ // Average global
			return (ut2.getTantosFavor() - ut2.getTantosContra()) - (ut1.getTantosFavor()-ut1.getTantosContra());
		}
		else {// Tantos a Favor global
			return ut2.getTantosFavor() - ut1.getTantosFavor();
		}
	}
	
	/**
	 * Compara averaje global, despu�s tantos a favor global, y despu�s victorias
	 * de dos usuarios en un torneo
	 * @param ut1 Usuario en Torneo a comparar
	 * @param ut2 Usuario en Torneo a comparar
	 * @return un negativo si ut1 es menor, cero si son iguales, o positivo si u1 es mayor que ut2.
	 */
	public static int AverageFavorVictoriasGlobal(UserTorneo ut1,UserTorneo ut2){
		if((ut2.getTantosFavor() - ut2.getTantosContra()) - (ut1.getTantosFavor()-ut1.getTantosContra())!= 0){ // Average global
			return (ut2.getTantosFavor() - ut2.getTantosContra()) - (ut1.getTantosFavor()-ut1.getTantosContra());
		}
		else if(ut2.getTantosFavor() - ut1.getTantosFavor() != 0){ // Tantos a Favor global
			return ut2.getTantosFavor() - ut1.getTantosFavor();
		}
			else {// Partidos ganados
					return ut2.getPartidosGanados() - ut1.getPartidosGanados();
			}		
	}
	
	/**
	 * Compara averaje subglobal, despu�s averaje global, despu�s tantos a favor global
	 * de dos usuarios en un torneo
	 * @param ut1 Usuario en Torneo a comparar
	 * @param ut2 Usuario en Torneo a comparar
	 * @return un negativo si ut1 es menor, cero si son iguales, o positivo si u1 es mayor que ut2.
	 */
	public static int SubAverageAverageFavorGlobal(UserTorneo ut1,UserTorneo ut2){
		if((ut2.getSubTantosFavor() - ut2.getSubTantosContra()) - (ut1.getSubTantosFavor()-ut1.getSubTantosContra())!= 0){ 
			// Average subglobal
			return (ut2.getSubTantosFavor() - ut2.getSubTantosContra()) - (ut1.getSubTantosFavor()-ut1.getSubTantosContra());
		}
		else if((ut2.getTantosFavor() - ut2.getTantosContra()) - (ut1.getTantosFavor()-ut1.getTantosContra())!= 0){ 
			// Average global
			return (ut2.getTantosFavor() - ut2.getTantosContra()) - (ut1.getTantosFavor()-ut1.getTantosContra());
		}
			else {// Tantos a Favor global
				return ut2.getTantosFavor() - ut1.getTantosFavor();
			}		
	}
	
	/**
	 * Compara averaje subglobal, despu�s tantos a favor subglobal, averaje global, y tantos a favor global
	 * de dos usuarios en un torneo
	 * @param ut1 Usuario en Torneo a comparar
	 * @param ut2 Usuario en Torneo a comparar
	 * @return un negativo si ut1 es menor, cero si son iguales, o positivo si u1 es mayor que ut2.
	 */
	public static int SubAverageSubFavorAverageFavorGlobal(UserTorneo ut1,UserTorneo ut2){
		if((ut2.getSubTantosFavor() - ut2.getSubTantosContra()) - (ut1.getSubTantosFavor()-ut1.getSubTantosContra())!= 0){ 
			// Average subglobal
			return (ut2.getSubTantosFavor() - ut2.getSubTantosContra()) - (ut1.getSubTantosFavor()-ut1.getSubTantosContra());
		}
		else if(ut2.getSubTantosFavor() - ut1.getSubTantosFavor() != 0){ 
			// Tantos subglobal
			return ut2.getTantosFavor() - ut1.getTantosFavor();
		}
		else if((ut2.getTantosFavor() - ut2.getTantosContra()) - (ut1.getTantosFavor()-ut1.getTantosContra())!= 0){ 
			// Average global
			return (ut2.getTantosFavor() - ut2.getTantosContra()) - (ut1.getTantosFavor()-ut1.getTantosContra());
		}
			else {// Tantos a Favor global
				return ut2.getTantosFavor() - ut1.getTantosFavor();
			}		
	}
	
	/**
	 * Compara averaje global, despu�s tantos a favor global, y cociente de dos usuarios en un torneo
	 * @param ut1 Usuario en Torneo a comparar
	 * @param ut2 Usuario en Torneo a comparar
	 * @return un negativo si ut1 es menor, cero si son iguales, o positivo si u1 es mayor que ut2.
	 */
	public static int AverageFavorCocienteGlobal(UserTorneo ut1,UserTorneo ut2){
		int resultado = (ut2.getTantosFavor() - ut2.getTantosContra()) - (ut1.getTantosFavor()-ut1.getTantosContra()); // Average global
		
		if(resultado != 0){
			return resultado;
		}
		else{ // Tantos a Favor global
			resultado = ut2.getTantosFavor() - ut1.getTantosFavor();
			
			if(resultado != 0){
				return resultado;
			}
			else{
				return Cociente(ut1.getTantosFavor(),ut1.getTantosContra(),ut2.getTantosFavor(),ut2.getTantosContra());				
			}
		}	
	}
	
	/**
	 * Compara el n�mero de partidos jugados entre dos usuarios en un torneo 
	 * @param ut1 Usuario en Torneo a comparar
	 * @param ut2 Usuario en Torneo a comparar
	 * @return un negativo si ut1 es menor, cero si son iguales, o positivo si u1 es mayor que ut2.
	 */
	public static int NumeroDePartidosJugados(UserTorneo ut1,UserTorneo ut2){
		return (ut1.getPartidosEmpatados()+ut1.getPartidosGanados()+ut1.getPartidosPerdidos()) -
				(ut2.getPartidosEmpatados()+ut2.getPartidosGanados()+ut2.getPartidosPerdidos());
	}
	
	/**
	 * Compara el cociente entre dos usuarios en un torneo
	 * @param ut1Favor Tantos a favor del primer usuario
	 * @param ut1Contra Tantos en contra del primer usuario
	 * @param ut2Favor Tantos a favor del segundo usuario
	 * @param ut2Contra Tantos en contra del segundo usuario
	 * @return un negativo si el primero es menor, cero si son iguales,
	 *  o positivo si el primero es mayor que el segundo.
	 */
	public static int Cociente(int ut1Favor,int ut1Contra, int ut2Favor, int ut2Contra){
		if(ut2Contra == 0){ // Todo esto para evitar que divida por cero :D
			if(ut1Contra == 0){
				return 0;
			}
			else{
				return Integer.MAX_VALUE;
			}
		}
		else if(ut1Contra == 0){
				return -Integer.MAX_VALUE;
			}
			else{ // Cociente
				return new Float(ut2Favor/ut2Contra).compareTo(new Float(ut1Favor/ut1Contra));
			}
	}

}

