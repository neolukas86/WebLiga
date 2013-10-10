package com.proyecto.util.listas.ACB;
import java.util.Comparator;
import com.proyecto.dominio.EquipoTorneo;
import com.proyecto.util.listas.AverageGlobalEquipoTorneo;

// Hay que tener en cuenta que ordena de menor a mayor por lo que para ordenarlo de mayor a menor tenemos que poner ut2 antes

// Está hecho, pero solo para 2 equipos, no se como se hace para más (de momento).

/**
 * Clase Comparator que compara dos equipos antes de haber acabado la competición
 * según las reglas ACB
 * @author Lucas Sánchez López
 * @version 1.0
 */
public class ACBEquipoTorneoDosComparator implements Comparator<EquipoTorneo>{

	public int compare(EquipoTorneo ut1, EquipoTorneo ut2){
			int resultado = AverageGlobalEquipoTorneo.NumeroDePartidosJugados(ut2, ut1);
			
			if(resultado != 0){
				return resultado;
			}
			else{ // Si han jugado el mismo número de partidos
					return AverageGlobalEquipoTorneo.AverageFavorGlobal(ut1,ut2); // AverageFavorCocienteGlobal
			}
	}
}
	
