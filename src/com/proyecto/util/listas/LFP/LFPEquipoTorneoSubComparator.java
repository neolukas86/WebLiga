package com.proyecto.util.listas.LFP;
import java.util.Comparator;
import com.proyecto.dominio.EquipoTorneo;
import com.proyecto.util.listas.AverageGlobalEquipoTorneo;

// Hay que tener en cuenta que ordena de menor a mayor por lo que para ordenarlo de mayor a menor tenemos que poner ut2 antes

// Est� hecho, pero solo para 2 equipos, no se como se hace para m�s (de momento).

/**
 * Clase Comparator que compara equipos entre varios empatados
 * seg�n las reglas LFP (Liga de F�tbol Profesional)
 * @author Lucas S�nchez L�pez
 * @version 1.0
 */
public class LFPEquipoTorneoSubComparator implements Comparator<EquipoTorneo>{

	public int compare(EquipoTorneo ut1, EquipoTorneo ut2){
		if(ut2.getSubPuntos() - ut1.getSubPuntos() != 0){
			return ut2.getSubPuntos() - ut1.getSubPuntos();
		}		
		else{
			return AverageGlobalEquipoTorneo.SubAverageAverageFavorGlobal(ut1, ut2);
		}			
	}

}
