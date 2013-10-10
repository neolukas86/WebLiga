package com.proyecto.util.listas.ACB;

import java.util.Comparator;
import com.proyecto.dominio.UserTorneo;
import com.proyecto.util.listas.AverageGlobalUserTorneo;


// Hay que tener en cuenta que ordena de menor a mayor por lo que para ordenarlo de mayor a menor tenemos que poner ut2 antes

// Está hecho, pero solo para 2 equipos, no se como se hace para más (de momento).

/**
 * Clase Comparator que compara dos usuarios habiendo acabado la competición
 * según las reglas ACB
 * @author Lucas Sánchez López
 * @version 1.0
 */
public class ACBUserTorneoDosFinalComparator implements Comparator<UserTorneo>{

	public int compare(UserTorneo ut1, UserTorneo ut2){
					
		if(ut2.getSubPartidosGanados() - ut1.getSubPartidosGanados() != 0){ // Enfrentamiento Directo
			return ut2.getSubPartidosGanados() - ut1.getSubPartidosGanados();
		}
		else if(ut2.getSubTantosFavor() - ut1.getSubTantosFavor() != 0){ // Average Directo (Entre 2 solo hace falta tantos a favor)
			return ut2.getSubTantosFavor() - ut1.getSubTantosFavor();
		} 
			else{
				return AverageGlobalUserTorneo.AverageFavorCocienteGlobal(ut1,ut2); // AverageFavorCocienteGlobal
			}			
	}
}

	
