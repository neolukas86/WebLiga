package com.proyecto.util.listas;
import java.util.Comparator;
import com.proyecto.dominio.UserTorneo;


/**
 * Clase Comparator que compara la posicion de los usuarios en un torneo
 * @author Lucas Sánchez López
 * @version 1.0
 */
public class PosicionUserTorneoComparator implements Comparator<UserTorneo>{

	public int compare(UserTorneo ut2, UserTorneo ut1){
			return ut2.getPosicion() - ut1.getPosicion();
	}
}
