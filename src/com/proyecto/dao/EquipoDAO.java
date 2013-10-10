package com.proyecto.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.proyecto.dominio.Comunidad;
import com.proyecto.dominio.Equipo;
import com.proyecto.dominio.Torneo;
import com.proyecto.util.displaytag.Paginate;

/**
* Interfaz DAO para los equipos
* @author Lucas Sánchez López
* @version 2.0
*/

public interface EquipoDAO {
	public Equipo mergeEquipo(Equipo equipo);
	public Paginate listEquipoQuery(HttpServletRequest request,String aliasTable,String where,String tablasAux);
	public List<Equipo> listEquipoQuery(String aliasTable,String where,String tablasAux);	
//	public Paginate listEquipoByTorneo(HttpServletRequest request, Torneo torneo);
//	public List<Equipo> listEquipoByTorneo(Torneo torneo);
	public Paginate listEquipoByIdUser(HttpServletRequest request, int iduser);
	public List<Equipo> listEquipoByIdUser(int iduser);
	
	public void deleteEquipo(Equipo equipo);
	public List<Equipo> listEquipoByComunidad(Comunidad com);
	public List<Equipo> listInvitadosByTorneo(Torneo tor);
	public List<Equipo> listEquipoByTorneo(Torneo tor);
	public List<Equipo> listEquipo();

}
