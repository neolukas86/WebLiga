package com.proyecto.util.listas;
import java.util.Comparator;
import com.proyecto.dominio.EquipoTorneo;

// Hay que tener en cuenta que ordena de menor a mayor por lo que para ordenarlo de mayor a menor tenemos que poner ut2 antes

// Está hecho, pero solo para 2 equipos, no se como se hace para más (de momento).
/**
 * Clase Comparator que compara los cocientes de los equipos en un torneo
 * @author Lucas Sánchez López
 * @version 1.0
 */
public class CocienteEquipoTorneoComparator implements Comparator<EquipoTorneo>{

	public int compare(EquipoTorneo ut1, EquipoTorneo ut2){
			return ut2.getCociente().compareTo(ut1.getCociente());
	}
}
