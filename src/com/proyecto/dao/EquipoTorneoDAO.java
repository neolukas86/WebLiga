package com.proyecto.dao;

import java.util.List;

import com.proyecto.dominio.EquipoTorneo;
import com.proyecto.dominio.Torneo;

/**
* Interfaz DAO para la relación de los equipos
* con los torneos
* @author Lucas Sánchez López
* @version 2.0
*/
public interface EquipoTorneoDAO {
	public EquipoTorneo mergeEquipoTorneo(EquipoTorneo equipoTorneo);
//	public List<Comunidad> listComunidad();
//	public List<Comunidad> listComunidadByIdEquipo(int idequipo);
	public void deleteEquipoTorneo(EquipoTorneo equipoTorneo);
	public List<EquipoTorneo> EquipoTorneoParticipante(Torneo tor);
	public List<EquipoTorneo> EquipoTorneoNoEliminados(Torneo tor);
	public List<EquipoTorneo> EquipoTorneoEliminados(Torneo tor);
}
