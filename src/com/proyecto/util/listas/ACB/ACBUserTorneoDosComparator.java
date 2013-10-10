package com.proyecto.util.listas.ACB;
import java.util.Comparator;
import com.proyecto.dominio.UserTorneo;
import com.proyecto.util.listas.AverageGlobalUserTorneo;

// Hay que tener en cuenta que ordena de menor a mayor por lo que para ordenarlo de mayor a menor tenemos que poner ut2 antes

// Está hecho, pero solo para 2 equipos, no se como se hace para más (de momento).

/**
 * Clase Comparator que compara dos usuarios antes de haber acabado la competición
 * según las reglas ACB
 * @author Lucas Sánchez López
 * @version 1.0
 */
public class ACBUserTorneoDosComparator implements Comparator<UserTorneo>{

	public int compare(UserTorneo ut1, UserTorneo ut2){
			int resultado = AverageGlobalUserTorneo.NumeroDePartidosJugados(ut2, ut1);
			
			if(resultado != 0){
				return resultado;
			}
			else{ // Si han jugado el mismo número de partidos
					return AverageGlobalUserTorneo.AverageFavorGlobal(ut1,ut2); // AverageFavorCocienteGlobal
			}
	}
}
	
