package com.proyecto.util.listas;

import java.util.Comparator;

import com.proyecto.dominio.Actividad;

/**
 * Clase Comparator que compara los nombres en catalán de las actividades
 * @author Lucas Sánchez López
 * @version 1.0
 */

public class ActividadNombreCAComparator implements Comparator<Actividad>{
	public int compare(Actividad act1, Actividad act2){
		if(act1.getNombre_CA()!=null && act2.getNombre_CA()!=null){
			return act1.getNombre_CA().compareTo(act2.getNombre_CA());	
		}
		else if(act1.getNombre_CA()!=null){
			return act1.getNombre_CA().compareTo(act2.getNombre());
		}
			else if(act2.getNombre_CA()!=null){
				return act1.getNombre().compareTo(act2.getNombre_CA());
			}
				else{
					return act1.getNombre().compareTo(act2.getNombre());
			}		
	}
}