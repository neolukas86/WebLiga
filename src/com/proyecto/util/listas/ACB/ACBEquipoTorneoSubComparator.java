package com.proyecto.util.listas.ACB;
import java.util.Comparator;
import com.proyecto.dominio.EquipoTorneo;
import com.proyecto.util.listas.AverageGlobalEquipoTorneo;

// Hay que tener en cuenta que ordena de menor a mayor por lo que para ordenarlo de mayor a menor tenemos que poner ut2 antes

// Está hecho, pero solo para 2 equipos, no se como se hace para más (de momento).

/**
 * Clase Comparator que compara equipos entre varios empatados
 * según las reglas ACB
 * @author Lucas Sánchez López
 * @version 1.0
 */
public class ACBEquipoTorneoSubComparator implements Comparator<EquipoTorneo>{

	public int compare(EquipoTorneo ut1, EquipoTorneo ut2){
		if(ut2.getSubPartidosGanados() - ut1.getSubPartidosGanados() != 0){
			return ut2.getSubPartidosGanados() - ut1.getSubPartidosGanados();
		}		
		else{
			return AverageGlobalEquipoTorneo.SubAverageAverageFavorGlobal(ut1, ut2);
		}			
	}

}