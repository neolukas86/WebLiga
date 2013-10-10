package com.proyecto.util.listas;

import java.util.Comparator;

import com.proyecto.dominio.Plataforma;

/**
 * Clase Comparator que compara los nombres de las plataformas
 * @author Lucas Sánchez López
 * @version 1.0
 */
public class PlataformaComparator implements Comparator<Plataforma>{
	
	public int compare(Plataforma plat1, Plataforma plat2){
		
		return plat1.getNombre().compareTo(plat2.getNombre());
	}

}