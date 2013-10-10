package com.proyecto.util.listas.ACB;

import java.util.Comparator;
import com.proyecto.dominio.EquipoTorneo;
import com.proyecto.util.listas.AverageGlobalEquipoTorneo;

/**
 * Clase Comparator que compara más de dos equipos habiendo acabado la competición
 * según las reglas ACB
 * @author Lucas Sánchez López
 * @version 1.0
 */
public class ACBEquipoTorneoMultipleFinalComparator implements Comparator<EquipoTorneo>{

	public int compare(EquipoTorneo ut1, EquipoTorneo ut2){
					
		if(ut2.getSubPartidosGanados() - ut1.getSubPartidosGanados() != 0){ // Enfrentamiento Directo
			return ut2.getSubPartidosGanados() - ut1.getSubPartidosGanados();
		}
		else if((ut2.getSubTantosFavor() - ut2.getSubTantosContra()) - 
				(ut1.getSubTantosFavor() - ut1.getSubTantosContra()) != 0){ // Average directo
			
			return (ut2.getSubTantosFavor() - ut2.getSubTantosContra()) - 
					(ut1.getSubTantosFavor() - ut1.getSubTantosContra());
		}
			else if(ut2.getSubTantosFavor() - ut1.getSubTantosFavor() != 0){  // A favor directo
				return ut2.getSubTantosFavor() - ut1.getSubTantosFavor();
			}
				else{
					return AverageGlobalEquipoTorneo.AverageFavorCocienteGlobal(ut1,ut2); // AverageFavorCocienteGlobal
				}			
	}
}

	
