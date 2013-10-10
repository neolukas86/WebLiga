package com.proyecto.util.listas.LFP;
import java.util.Comparator;
import com.proyecto.dominio.EquipoTorneo;
import com.proyecto.util.listas.AverageGlobalEquipoTorneo;

// Hay que tener en cuenta que ordena de menor a mayor por lo que para ordenarlo de mayor a menor tenemos que poner ut2 antes

// Est� hecho, pero solo para 2 equipos, no se como se hace para m�s (de momento).

/**
 * Clase Comparator que compara dos equipos habiendo acabado la competici�n
 * seg�n las reglas LFP (Liga de F�tbol Profesional)
 * @author Lucas S�nchez L�pez
 * @version 1.0
 */
public class LFPEquipoTorneoDosFinalComparator implements Comparator<EquipoTorneo>{

	public int compare(EquipoTorneo ut1, EquipoTorneo ut2){
		if(ut2.getSubPartidosGanados() - ut1.getSubPartidosGanados() != 0){ // Enfrentamiento Directo
			return ut2.getSubPartidosGanados() - ut1.getSubPartidosGanados();
		}
		else if(ut2.getSubTantosFavor() - ut1.getSubTantosFavor() != 0){ // Average Directo (Entre 2 solo hace falta tantos a favor)
			return ut2.getSubTantosFavor() - ut1.getSubTantosFavor();
			} 
			else{
				int retorno = AverageGlobalEquipoTorneo.AverageFavorVictoriasGlobal(ut1,ut2);
					
				if(retorno != 0){
					return retorno;
				}
				else{
					return AverageGlobalEquipoTorneo.NumeroDePartidosJugados(ut2, ut1);
				}
			}			
	}
}
