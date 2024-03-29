package com.proyecto.util.listas.EPL;
import java.util.Comparator;
import com.proyecto.dominio.UserTorneo;
import com.proyecto.util.listas.AverageGlobalUserTorneo;

// Hay que tener en cuenta que ordena de menor a mayor por lo que para ordenarlo de mayor a menor tenemos que poner ut2 antes

// Se supone que previamente se han comparado a los puntos, y tienen los mismos

/**
 * Clase Comparator que compara dos usuarios antes de haber acabado la competici�n
 * seg�n las reglas EPL (English Premier League)
 * @author Lucas S�nchez L�pez
 * @version 1.0
 */
public class EPLUserTorneoDosComparator implements Comparator<UserTorneo>{

	public int compare(UserTorneo ut1, UserTorneo ut2){
		int retorno = AverageGlobalUserTorneo.AverageFavorGlobal(ut1,ut2);
		
		if(retorno != 0){
			return retorno;
		}
		else{
			return AverageGlobalUserTorneo.NumeroDePartidosJugados(ut2, ut1);
		}
	}

}
