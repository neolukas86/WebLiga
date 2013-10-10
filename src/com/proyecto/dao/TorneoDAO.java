package com.proyecto.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.proyecto.dominio.Comunidad;
import com.proyecto.dominio.Torneo;
import com.proyecto.dominio.User;
import com.proyecto.util.displaytag.Paginate;

/**
* Interfaz DAO para los torneos
* @author Lucas Sánchez López
* @version 2.0
*/
public interface TorneoDAO {
	public Torneo mergeTorneo(Torneo torneo);
	public Paginate listTorneo(HttpServletRequest request);
	public List<Torneo> listTorneo();
//	public List<Torneo> listTorneoByIdUser(int iduser);
	public Paginate listTorneoByComunidad(HttpServletRequest request, Comunidad com);
	public List<Torneo> listTorneoByComunidad(Comunidad com);
	public void deleteTorneo(Torneo torneo);
	public Paginate listTorneoByUser(HttpServletRequest request, User us);
	public List<Torneo> listTorneoByUser(User us);
	public Paginate listTorneoQuery(HttpServletRequest request,
			String aliasTable, String where, String tablasAux);
	public List<Torneo> listTorneoQuery(String aliasTable,String where,String tablasAux);
	public List<Torneo> listTorneoByIdUser(int iduser);
	public List<Torneo> listTorneoByIdEquipo(int idequipo);
}
