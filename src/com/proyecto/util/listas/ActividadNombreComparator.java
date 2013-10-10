package com.proyecto.util.listas;

import java.util.Comparator;

import com.proyecto.dominio.Actividad;

/**
 * Clase Comparator que compara los nombres en espa�ol de las actividades
 * @author Lucas S�nchez L�pez
 * @version 1.0
 */
public class ActividadNombreComparator implements Comparator<Actividad>{
	public int compare(Actividad act1, Actividad act2){		
		return act1.getNombre().compareTo(act2.getNombre());
	}
}
