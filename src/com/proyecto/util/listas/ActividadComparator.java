package com.proyecto.util.listas;

import java.util.Comparator;

import com.proyecto.dominio.Actividad;

/**
 * Clase Comparator que compara los id's de las actividades
 * @author Lucas Sánchez López
 * @version 1.0
 */
public class ActividadComparator implements Comparator<Actividad>{
	
	public int compare(Actividad act1, Actividad act2){
		
		return act1.getId() - act2.getId();
	}

}