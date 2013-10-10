package com.proyecto.dao;

import java.util.List;

import com.proyecto.dominio.Torneo;
import com.proyecto.dominio.UserTorneo;

/**
* Interfaz DAO para la relación de los usuarios
* con las equipos
* @author Lucas Sánchez López
* @version 2.0
*/
public interface UserTorneoDAO {
	public UserTorneo mergeUserTorneo(UserTorneo userTorneo);
//	public List<Comunidad> listComunidad();
//	public List<Comunidad> listComunidadByIdUser(int iduser);
	public void deleteUserTorneo(UserTorneo userTorneo);
	public List<UserTorneo> UserTorneoParticipante(Torneo tor);
	public List<UserTorneo> UserTorneoEliminados(Torneo tor);
	public List<UserTorneo> UserTorneoNoEliminados(Torneo tor);
}
