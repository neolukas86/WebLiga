package com.proyecto.dao;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.proyecto.dominio.Comunidad;
import com.proyecto.dominio.Equipo;
import com.proyecto.dominio.Torneo;
import com.proyecto.util.dao.DAOUtil;
import com.proyecto.util.displaytag.Paginate;
import com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares;

/**
* Esta clase implementa la interfaz
* EquipoDAO
* @author Lucas Sánchez López
* @version 2.0
*/
public class EquipoDAOImpl implements EquipoDAO{

	/**
	 * Usado para insertar o actualizar un equipo
	 * en la base de datos
	 * @param equipo Objeto Equipo a insertar o actualizar
	 * @return Objeto Equipo insertado o actualizado
	 */
	@Override
	public Equipo mergeEquipo(Equipo equipo) {
		// TODO Auto-generated method stub
		DAOUtil daoUtil = new DAOUtil();
		return (Equipo)daoUtil.mergeObject(equipo);
	}

	/**
	 * Usado para eliminar un equipo
	 * en la base de datos
	 * @param equipo Objeto Equipo a eliminar
	 */
	@Override
	public void deleteEquipo(Equipo equipo) {
		// TODO Auto-generated method stub
		DAOUtil daoUtil = new DAOUtil();
		daoUtil.deleteObject(equipo);		
	}
	

	
	/**
	 * Usado para listar todos los equipos
	 * @return Lista de objetos Equipo
	 */	
	@SuppressWarnings("unchecked")
	@Override
	public List<Equipo> listEquipo() {
		DAOUtil daoUtil = new DAOUtil();

		return (List<Equipo>) daoUtil.listQuery("from Equipo");
	}
	
	 
	/**
	 * Usado para listar equipos según consulta a la base de datos
	 * @param request Objeto HttpServletRequest
	 * @param aliasTable Cadena de caracteres con el alias de la tabla de equipo
	 * @param where Cadena de caracteres con la parte principal de la sentencia
	 * @param tablasAux Cadena de caracteres con los nombres de las tablas auxiliares para la consulta 
	 * @return Objeto Paginate de la lista de equipos
	 * @deprecated
	 * 
	 */
	@Override
	public Paginate listEquipoQuery(HttpServletRequest request,String aliasTable,String where,String tablasAux) {			
		DAOUtil daoUtil = new DAOUtil();
		
		return daoUtil.listFromPaginableQuery("Equipo",request,aliasTable,where,tablasAux,null);
	}
	
	/**
	 * Usado para listar equipos según consulta a la base de datos
	 * @param aliasTable Cadena de caracteres con el alias de la tabla de equipo
	 * @param where Cadena de caracteres con la parte principal de la sentencia
	 * @param tablasAux Cadena de caracteres con los nombres de las tablas auxiliares para la consulta 
	 * @return Lista de objetos Equipo
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Equipo> listEquipoQuery(String aliasTable,String where,String tablasAux) {			
		DAOUtil daoUtil = new DAOUtil();
		
		return (List<Equipo>) daoUtil.listQuery("select "+aliasTable+" from Equipo as "+aliasTable+tablasAux+where);
	}
	
//	@Override
//	public Paginate listEquipoByTorneo(HttpServletRequest request, Torneo torneo){	
//		String aliasTable = "eq ";
//		String tablasAux = ",Torneo as tor ";
//		String where = "where id.parenTorneo";
//		
//		return listEquipoQuery(request,aliasTable,where,tablasAux);
//	}
//	
//	@Override
//	public List<Equipo> listEquipoByTorneo(Torneo torneo){	
//		String aliasTable = "eq ";
//		String tablasAux = ",Torneo as tor ";
//		String where = "where "+aliasTable+" in elements(tor.equipos)";
//		
//		return listEquipoQuery(aliasTable,where,tablasAux);
//	}
	
	/**
	 * Usado para listar equipos de un torneo
	 * @param tor Torneo del que consultar equipos 
	 * @return Lista de objetos Equipo
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Equipo> listEquipoByTorneo(Torneo tor) {			
		DAOUtil daoUtil = new DAOUtil();
		
		String where = " where id in (select id.parentEquipo from EquipoTorneo where id.parentTorneo="+tor.getId()+" and" +
				" rango <="+FuncionesAuxiliares.NORMALUSER+")";
		
		return (List<Equipo>) daoUtil.listQuery("from Equipo"+where);
	}
	
	/**
	 * Usado para listar equipos de un usuario por su id
	 * @param request Objeto HttpServletRequest
	 * @param iduser Id del usuario
	 * @return Objeto Paginate de la lista de equipos
	 * @deprecated
	 * 
	 */
	@Override
	public Paginate listEquipoByIdUser(HttpServletRequest request, int iduser) {
		DAOUtil daoUtil = new DAOUtil();
		
		String where = " where id in (select id.parentEquipo from UserEquipo where id.parentUser="+iduser+")";
		
		return daoUtil.listFromPaginableQuery("Equipo",request,where,null);
	}
	
	/**
	 * Usado para listar equipos de un usuario por su id
	 * @param iduser Id del usuario
	 * @return Lista de objetos Equipo
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Equipo> listEquipoByIdUser(int iduser) {
		DAOUtil daoUtil = new DAOUtil();
		
		String where = " where id in (select id.parentEquipo from UserEquipo where id.parentUser="+iduser+")";
		
		return (List<Equipo>) daoUtil.listQuery("from Equipo"+where);
	}
	
	
	/**
	 * Usado para listar equipos de una comunidad
	 * @param com Objeto Comunidad
	 * @return Lista de Objetos Equipo
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Equipo> listEquipoByComunidad(Comunidad com) {			
		DAOUtil daoUtil = new DAOUtil();
		
		String where = " where id in (select id.parentEquipo from EquipoComunidad where id.parentComunidad="+com.getId()+" and" +
				" rango <="+FuncionesAuxiliares.NORMALUSER+")";
		
		return (List<Equipo>) daoUtil.listQuery("from Equipo"+where);
	}
	
	/**
	 * Usado para listar equipos invitados de un torneo
	 * @param tor Objeto Torneo
	 * @return List de objetos Equipo
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Equipo> listInvitadosByTorneo(Torneo tor) {			
		DAOUtil daoUtil = new DAOUtil();
		
		String where = " where id in (select id.parentEquipo from EquipoTorneo where id.parentTorneo="+tor.getId()+" and" +
				" rango <="+FuncionesAuxiliares.NORMALUSER+" and invitado=true)";
		
		return (List<Equipo>) daoUtil.listQuery("from Equipo"+where);
	}

	
}
