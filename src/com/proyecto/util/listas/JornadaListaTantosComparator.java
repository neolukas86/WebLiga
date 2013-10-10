package com.proyecto.util.listas;

import java.util.Comparator;
import com.proyecto.dominio.IntegerInteger;

/**
 * Clase Comparator que compara los tantos de cada jornada en un torneo
 * @author Lucas Sánchez López
 * @version 1.0
 */
public class JornadaListaTantosComparator implements Comparator<IntegerInteger>{
	
	public int compare(IntegerInteger pev1, IntegerInteger pev2){
		
		return pev1.getEntero() - pev2.getEntero();
	}

}
