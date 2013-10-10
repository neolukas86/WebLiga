package com.proyecto.util.listas;
import java.util.Comparator;
import com.proyecto.dominio.EquipoTorneo;


/**
 * Clase Comparator que compara la posicion de los equipos en un torneo
 * @author Lucas S�nchez L�pez
 * @version 1.0
 */
public class PosicionEquipoTorneoComparator implements Comparator<EquipoTorneo>{

	public int compare(EquipoTorneo eq2, EquipoTorneo eq1){
			return eq2.getPosicion() - eq1.getPosicion();
	}
}

