package com.proyecto.util.listas;

import java.util.Comparator;

import com.proyecto.dominio.Actividad;

/**
 * Clase Comparator que compara los nombres en inglés de las actividades
 * @author Lucas Sánchez López
 * @version 1.0
 */
public class ActividadNombreENComparator implements Comparator<Actividad>{
	public int compare(Actividad act1, Actividad act2){
		if(act1.getNombre_EN()!=null && act2.getNombre_EN()!=null){
			return act1.getNombre_EN().compareTo(act2.getNombre_EN());	
		}
		else if(act1.getNombre_EN()!=null){
			return act1.getNombre_EN().compareTo(act2.getNombre());
		}
			else if(act2.getNombre_EN()!=null){
				return act1.getNombre().compareTo(act2.getNombre_EN());
			}
				else{
					return act1.getNombre().compareTo(act2.getNombre());
			}		
	}
}