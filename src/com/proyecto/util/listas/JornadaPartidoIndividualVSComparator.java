package com.proyecto.util.listas;

import java.util.Comparator;

import com.proyecto.dominio.PartidoIndividualVersus;

/**
 * Clase Comparator que compara la jornada de los partidos individuales
 * @author Lucas Sánchez López
 * @version 1.0
 */
public class JornadaPartidoIndividualVSComparator implements Comparator<PartidoIndividualVersus>{
	
	public int compare(PartidoIndividualVersus piv1, PartidoIndividualVersus piv2){
		
		return piv1.getJornada() - piv2.getJornada();
	}

}
