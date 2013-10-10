package com.proyecto.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.proyecto.dao.TorneoDAO;
import com.proyecto.dominio.Comunidad;
import com.proyecto.dominio.Torneo;
import com.proyecto.dominio.User;
import com.proyecto.util.dao.DAOUtil;
import com.proyecto.util.displaytag.Paginate;
import com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares;

/**
* Esta clase implementa la interfaz
* TorneoDAO
* @author Lucas Sánchez López
* @version 2.0
*/
public class TorneoDAOImpl implements TorneoDAO {
	/**
	 * Usado para insertar o actualizar un torneo en la base de datos
	 * @param torneo El torneo a insertar o actualizar en la base de datos
	 * @return Objeto Torneo insertado o actualizado
	 */
		@Override
		public Torneo mergeTorneo(Torneo torneo) {
			DAOUtil daoUtil = new DAOUtil();
			return (Torneo) daoUtil.mergeObject(torneo);
		}

		/**
		 * Usado para eliminar un torneo en la base de datos
		 * @param torneo El torneo a eliminar en la base de datos
		 */
		@Override
		public void deleteTorneo(Torneo torneo) {
			DAOUtil daoUtil = new DAOUtil();
			daoUtil.deleteObject(torneo);
		}
		
		/**
		 * Usado para listar todos los torneos
		 *  en la base de datos
		 * @param request Objeto HttpServletRequest
		 * @return Objeto Paginate de la lista de torneos
		 * @deprecated
		 */
		@Override
		public Paginate listTorneo(HttpServletRequest request) {

//			List<Torneo> torneoList = new ArrayList<Torneo>();

			DAOUtil daoUtil = new DAOUtil();
//			torneoList = (List<Torneo>) daoUtil.listFrom("Torneo",false); // El select all sobre Torneo, y cacheable a false.
			
			return daoUtil.listFromPaginable("Torneo",request,null,null,null,null);
		}
		
		/**
		 * Usado para listar todos los torneos
		 *  en la base de datos
		 * @return Lista de objetos Torneo
		 */
		@SuppressWarnings("unchecked")
		@Override
		public List<Torneo> listTorneo() {
			DAOUtil daoUtil = new DAOUtil();

			return (List<Torneo>) daoUtil.listQuery("from Torneo");
		}
		
		/**
		 * Usado para listar torneos según consulta a la base de datos
		 * @param request Objeto HttpServletRequest
		 * @param aliasTable Cadena de caracteres con el alias de la tabla de torneo
		 * @param where Cadena de caracteres con la parte principal de la sentencia
		 * @param tablasAux Cadena de caracteres con los nombres de las tablas auxiliares para la consulta 
		 * @return Objeto Paginate de la lista de torneos
		 * @deprecated
		 * 
		 */
		@Override
		public Paginate listTorneoQuery(HttpServletRequest request,String aliasTable,String where,String tablasAux) {			
			DAOUtil daoUtil = new DAOUtil();
			
			return daoUtil.listFromPaginableQuery("Torneo",request,aliasTable,where,tablasAux,null);
		}
		
		/**
		 * Usado para listar torneos según consulta a la base de datos
		 * @param aliasTable Cadena de caracteres con el alias de la tabla de torneo
		 * @param where Cadena de caracteres con la parte principal de la sentencia
		 * @param tablasAux Cadena de caracteres con los nombres de las tablas auxiliares para la consulta 
		 * @return Lista de objetos Torneo
		 * 
		 */
		@SuppressWarnings("unchecked")
		@Override
		public List<Torneo> listTorneoQuery(String aliasTable,String where,String tablasAux) {			
			DAOUtil daoUtil = new DAOUtil();
			
			return (List<Torneo>) daoUtil.listQuery("select "+aliasTable+" from Torneo as "+aliasTable+tablasAux+where);
		}

		/**
		 * Usado para listar torneos a los que pertenezca el usuario
		 * @param request Objeto HttpServletRequest 
		 * @param us Usuario sobre el que realizar la consulta
		 * @return Objeto Paginate de la lista de torneos
		 * @deprecated
		 * 
		 */
		@Override
		public Paginate listTorneoByUser(HttpServletRequest request,User us) {
			DAOUtil daoUtil = new DAOUtil();
			
			String where = " where id in (select id.parentTorneo from UserTorneo where id.parentUser="+us.getId()+" and" +
					" rango <="+FuncionesAuxiliares.NORMALUSER+")";
			
			return daoUtil.listFromPaginableQuery("Torneo",request,where,null);
		}

		/**
		 * Usado para listar torneos a los que pertenezca el usuario 
		 * @param us Usuario sobre el que realizar la consulta
		 * @return Lista de objetos Torneo
		 * 
		 */		
		@SuppressWarnings("unchecked")
		@Override
		public List<Torneo> listTorneoByUser(User us) {
			DAOUtil daoUtil = new DAOUtil();
			
			String where = " where id in (select id.parentTorneo from UserTorneo where id.parentUser="+us.getId()+" and" +
					" rango <="+FuncionesAuxiliares.NORMALUSER+")";
			
			return (List<Torneo>) daoUtil.listQuery("from Torneo"+where);
		}

		/**
		 * Usado para listar torneos de una comunidad
		 * @param request Objeto HttpServletRequest 
		 * @param com Comunidad sobre el que realizar la consulta
		 * @return Objeto Paginate de la lista de torneos
		 * @deprecated
		 * 
		 */
		@Override
		public Paginate listTorneoByComunidad(HttpServletRequest request, Comunidad com) {			
			String where = " where id.parentComunidad="+com.getId();
			
			DAOUtil daoUtil = new DAOUtil();
			
			return daoUtil.listFromPaginableQuery("Torneo",request,where,null);
		}
		
		/**
		 * Usado para listar torneos de una comunidad 
		 * @param com Comunidad sobre el que realizar la consulta
		 * @return Lista de objetos Torneo
		 * 
		 */	
		@SuppressWarnings("unchecked")
		@Override
		public List<Torneo> listTorneoByComunidad(Comunidad com) {			
			String where = " where id.parentComunidad="+com.getId();
			
			DAOUtil daoUtil = new DAOUtil();
			
			return (List<Torneo>) daoUtil.listQuery("from Torneo"+where);
		}
		
		/**
		 * Usado para listar torneos de un usuario por su id 
		 * @param iduser Id del usuario
		 * @return Lista de objetos Torneo
		 * 
		 */	
		@SuppressWarnings("unchecked")
		@Override
		public List<Torneo> listTorneoByIdUser(int iduser) {
			DAOUtil daoUtil = new DAOUtil();
			
			String where = " where id in (select id.parentTorneo from UserTorneo where id.parentUser="+iduser+" and" +
					" rango <="+FuncionesAuxiliares.NORMALUSER+")";
			
			return (List<Torneo>) daoUtil.listQuery("from Torneo"+where);
		}
		
		/**
		 * Usado para listar torneos de un equipo por su id 
		 * @param idequipo Id del equipo
		 * @return Lista de objetos Torneo
		 * 
		 */	
		@SuppressWarnings("unchecked")
		@Override
		public List<Torneo> listTorneoByIdEquipo(int idequipo) {
			DAOUtil daoUtil = new DAOUtil();
			
			String where = " where id in (select id.parentTorneo from EquipoTorneo where id.parentEquipo="+idequipo+" and" +
					" rango <="+FuncionesAuxiliares.NORMALUSER+")";
			
			return (List<Torneo>) daoUtil.listQuery("from Torneo"+where);
		}
}


