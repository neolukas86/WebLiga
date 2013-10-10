package com.proyecto.util.listas;

import java.util.Comparator;

import com.proyecto.dominio.PartidoEquiposVersus;

/**
 * Clase Comparator que compara la jornada de los partidos de equipos
 * @author Lucas Sánchez López
 * @version 1.0
 */
public class JornadaPartidoEquiposVSComparator implements Comparator<PartidoEquiposVersus>{
	
	public int compare(PartidoEquiposVersus pev1, PartidoEquiposVersus pev2){
		
		return pev1.getJornada() - pev2.getJornada();
	}

}
