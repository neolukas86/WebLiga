package com.proyecto.util.listas.LFP;
import java.util.Comparator;
import com.proyecto.dominio.UserTorneo;
import com.proyecto.util.listas.AverageGlobalUserTorneo;

// Hay que tener en cuenta que ordena de menor a mayor por lo que para ordenarlo de mayor a menor tenemos que poner ut2 antes

// Está hecho, pero solo para 2 equipos, no se como se hace para más (de momento).

/**
 * Clase Comparator que compara usuarios entre varios empatados
 * según las reglas LFP (Liga de Fútbol Profesional)
 * @author Lucas Sánchez López
 * @version 1.0
 */
public class LFPUserTorneoSubComparator implements Comparator<UserTorneo>{

	public int compare(UserTorneo ut1, UserTorneo ut2){
		if(ut2.getSubPuntos() - ut1.getSubPuntos() != 0){
			return ut2.getSubPuntos() - ut1.getSubPuntos();
		}		
		else{
			return AverageGlobalUserTorneo.SubAverageAverageFavorGlobal(ut1, ut2);
		}			
	}

}
