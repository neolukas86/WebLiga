package com.proyecto.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.proyecto.dominio.Equipo;
import com.proyecto.dominio.EquipoTorneo;
import com.proyecto.dominio.PartidoEquiposVersus;
import com.proyecto.dominio.PartidoIndividualVersus;
import com.proyecto.dominio.Torneo;
import com.proyecto.dominio.User;
import com.proyecto.dominio.UserTorneo;
import com.proyecto.util.displaytag.Paginate;

/**
* Interfaz DAO para los partidos
* @author Lucas Sánchez López
* @version 2.0
*/
public interface PartidoDAO {
	
	public PartidoEquiposVersus mergePartidoEquiposVersus(PartidoEquiposVersus partidoEquiposVersus);
	public Paginate listPartidoEquiposVersus(HttpServletRequest request);
	public List<PartidoEquiposVersus> listPartidoEquiposVersus();
	public void deletePartidoEquiposVersus(PartidoEquiposVersus partidoEquiposVersus);
	public List<PartidoEquiposVersus> mergePartidoEquiposVersusList(List<PartidoEquiposVersus> partidoEquiposVersusSet);
	
	public PartidoIndividualVersus mergePartidoIndividualVersus(PartidoIndividualVersus partidoIndividualVersus);
	public Paginate listPartidoIndividualVersus(HttpServletRequest request);
	public List<PartidoIndividualVersus> listPartidoIndividualVersus();
	public void deletePartidoIndividualVersus(PartidoIndividualVersus partidoIndividualVersus);
	public List<PartidoIndividualVersus> mergePartidoIndividualVersusList(List<PartidoIndividualVersus> partidoIndividualVersusSet);
	
	public Paginate listPartidoIndividualVersusQuery(HttpServletRequest request,String aliasTable,String where,String tablasAux,Integer ObjsPerPage);
	public List<PartidoIndividualVersus> listPartidoIndividualVersusQuery(String aliasTable,String where,String tablasAux);
	public Paginate listPartidoIndividualVersusByTorneo(HttpServletRequest request, Torneo torneo, Integer tam);
	public List<PartidoIndividualVersus> listPartidoIndividualVersusByTorneo(Torneo torneo);
	
	public Paginate listPartidoEquiposVersusQuery(HttpServletRequest request,String aliasTable,String where,String tablasAux,Integer ObjsPerPage);
	public List<PartidoEquiposVersus> listPartidoEquiposVersusQuery(String aliasTable,String where,String tablasAux);
	public Paginate listPartidoEquiposVersusByTorneo(HttpServletRequest request, Torneo torneo, Integer tam);
	public List<PartidoEquiposVersus> listPartidoEquiposVersusByTorneo(Torneo torneo);
	
	public Integer numPartidosJugadosUsuario(Torneo tor, User us);
	public Integer numPartidosNoJugadosUsuario(Torneo tor, User us);
	
	public List<PartidoIndividualVersus> PartidosUsuarioTorneo(Torneo tor, User us);
	public List<PartidoIndividualVersus> PartidosUsuarioTorneoJugables(Torneo tor, User us);
	public List<PartidoIndividualVersus> PartidosUsuarioTorneoInvitados(Torneo tor);
	public List<PartidoIndividualVersus> PartidosUsuarioTorneoInvitadosJugables(Torneo tor);
	public List<PartidoIndividualVersus> PartidosUsuariosTorneo(UserTorneo local,UserTorneo visitante);
	public List<PartidoEquiposVersus> PartidosEquipoTorneo(Torneo tor, Equipo eq);
	public List<PartidoEquiposVersus> PartidosEquipoTorneoJugables(Torneo tor,Equipo eq);
	public List<PartidoEquiposVersus> PartidosEquipoTorneoInvitados(Torneo tor);
	public List<PartidoEquiposVersus> PartidosEquipoTorneoInvitadosJugables(Torneo tor);
	public List<PartidoEquiposVersus> PartidosEquiposTorneo(EquipoTorneo local,EquipoTorneo visitante);
	public Boolean ComprobarPartidosJornada(Torneo tor);
	public List<PartidoEquiposVersus> PartidosRechazadosEquipos(Torneo tor);
	public List<PartidoIndividualVersus> PartidosRechazadosIndividual(Torneo tor);
	public List<PartidoIndividualVersus> PartidosNoConfirmadosIndividual(Torneo tor);
	public List<PartidoEquiposVersus> PartidosNoConfirmadosEquipos(Torneo tor);
	public List<PartidoIndividualVersus> PartidosByJornadaIndividual(Torneo tor,Integer jornada);
	public List<PartidoEquiposVersus> PartidosByJornadaEquipos(Torneo tor,Integer jornada);
}
