package com.proyecto.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.proyecto.dao.ComunidadDAO;
import com.proyecto.dominio.Comunidad;
import com.proyecto.util.dao.DAOUtil;
import com.proyecto.util.displaytag.Paginate;
import com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares;

/**
* Esta clase implementa la interfaz
* ComunidadDAO
* @author Lucas Sánchez López
* @version 2.0
*/

public class ComunidadDAOImpl implements ComunidadDAO {
		/**
		 * Usado para insertar o actualizar una comunidad en la base de datos
		 * @param comunidad La comunidad a insertar o actualizar en la base de datos
		 * @return  El objeto Comunidad insertado o actualizado
		 */
		@Override
		public Comunidad mergeComunidad(Comunidad comunidad) {
			DAOUtil daoUtil = new DAOUtil();
			return (Comunidad) daoUtil.mergeObject(comunidad);
		}

		/**
		 * Usado para eliminar una comunidad de la base de datos
		 * @param comunidad La comunidad a eliminar de la base de datos
		 */
		@Override
		public void deleteComunidad(Comunidad comunidad) {
			DAOUtil daoUtil = new DAOUtil();
			daoUtil.deleteObject(comunidad);
		}
		
		/**
		 * Usado para listar todas las comunidades
		 * @param request Objeto HttpServletRequest
		 * @return Objeto Paginate de la lista de comunidades
		 * @deprecated
		 */
		@Override
		public Paginate listComunidad(HttpServletRequest request) {
			
			DAOUtil daoUtil = new DAOUtil();
			
			return daoUtil.listFromPaginable("Comunidad",request,null,null,null,null);
		}
		
		/**
		 * Usado para listar todas las comunidades
		 * @return Lista de objetos Comunidad
		 * 
		 */	
		@SuppressWarnings("unchecked")
		@Override
		public List<Comunidad> listComunidad() {
			
			DAOUtil daoUtil = new DAOUtil();
			
			return (List<Comunidad>) daoUtil.listQuery("from Comunidad");
		}
		
		/**
		 * Usado para listar comunidades según consulta a la base de datos
		 * @param request Objeto HttpServletRequest
		 * @param aliasTable Cadena de caracteres con el alias de la tabla de comunidad
		 * @param where Cadena de caracteres con la parte principal de la sentencia
		 * @param tablasAux Cadena de caracteres con los nombres de las tablas auxiliares para la consulta 
		 * @return Objeto Paginate de la lista de comunidades
		 * @deprecated
		 * 
		 */
		@Override
		public Paginate listComunidadQuery(HttpServletRequest request,String aliasTable,String where,String tablasAux) {			
			DAOUtil daoUtil = new DAOUtil();
			
			return daoUtil.listFromPaginableQuery("Comunidad",request,aliasTable,where,tablasAux,null);
		}
		
		/**
		 * Usado para listar comunidades según consulta a la base de datos
		 * @param aliasTable Cadena de caracteres con el alias de la tabla de comunidad
		 * @param where Cadena de caracteres con la parte principal de la sentencia
		 * @param tablasAux Cadena de caracteres con los nombres de las tablas auxiliares para la consulta 
		 * @return Lista de objetos Comunidad
		 * 
		 */
		@SuppressWarnings("unchecked")
		@Override
		public List<Comunidad> listComunidadQuery(String aliasTable,String where,String tablasAux) {			
			DAOUtil daoUtil = new DAOUtil();
			
			return (List<Comunidad>) daoUtil.listQuery("select "+aliasTable+" from Comunidad as "+aliasTable+tablasAux+where);
		}

		/**
		 * Usado para listar comunidades a las que pertenezca el usuario
		 * según su id.
		 * @param request Objeto HttpServletRequest
		 * @param iduser Id del usuario
		 * @return Objeto Paginate de la lista de comunidades
		 * @deprecated
		 */
		@Override
		public Paginate listComunidadByIdUser(HttpServletRequest request, int iduser) {
			DAOUtil daoUtil = new DAOUtil();
			
			String where = " where id in (select id.parentComunidad from UserComunidad where id.parentUser="+iduser+" and" +
					" rango <="+FuncionesAuxiliares.NORMALUSER+")";
			
			return daoUtil.listFromPaginableQuery("Comunidad",request,where,null);
		}
		
		/**
		 * Usado para listar comunidades a las que pertenezca el usuario
		 * según su id.
		 * @param iduser Id del usuario
		 * @return Lista de objetos Comunidad
		 */
		@SuppressWarnings("unchecked")
		@Override
		public List<Comunidad> listComunidadByIdUser(int iduser) {
			DAOUtil daoUtil = new DAOUtil();
			
			String where = " where id in (select id.parentComunidad from UserComunidad where id.parentUser="+iduser+" and" +
					" rango <="+FuncionesAuxiliares.NORMALUSER+")";
			
			return (List<Comunidad>) daoUtil.listQuery("from Comunidad"+where);
		}
		
		/**
		 * Usado para listar comunidades a las que pertenezca el equipo
		 * según su id.
		 * @param idequipo Id del equipo
		 * @return Lista de objetos Comunidad
		 */
		@SuppressWarnings("unchecked")
		@Override
		public List<Comunidad> listComunidadByIdEquipo(int idequipo) {
			DAOUtil daoUtil = new DAOUtil();
			
			String where = " where id in (select id.parentComunidad from EquipoComunidad where id.parentEquipo="+idequipo+" and" +
					" rango <="+FuncionesAuxiliares.NORMALUSER+")";
			
			return (List<Comunidad>) daoUtil.listQuery("from Comunidad"+where);
		}
}

