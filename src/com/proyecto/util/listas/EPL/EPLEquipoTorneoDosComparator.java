package com.proyecto.util.listas.EPL;
import java.util.Comparator;
import com.proyecto.dominio.EquipoTorneo;
import com.proyecto.util.listas.AverageGlobalEquipoTorneo;

// Hay que tener en cuenta que ordena de menor a mayor por lo que para ordenarlo de mayor a menor tenemos que poner ut2 antes

// Se supone que previamente se han comparado a los puntos, y tienen los mismos

/**
 * Clase Comparator que compara dos equipos antes de haber acabado la competición
 * según las reglas EPL (English Premier League)
 * @author Lucas Sánchez López
 * @version 1.0
 */
public class EPLEquipoTorneoDosComparator implements Comparator<EquipoTorneo>{

	public int compare(EquipoTorneo ut1, EquipoTorneo ut2){
		int retorno = AverageGlobalEquipoTorneo.AverageFavorGlobal(ut1,ut2);
		
		if(retorno != 0){
			return retorno;
		}
		else{
			return AverageGlobalEquipoTorneo.NumeroDePartidosJugados(ut2, ut1);
		}
	}

}
