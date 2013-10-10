package com.proyecto.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.proyecto.dominio.Comunidad;
import com.proyecto.dominio.Equipo;
import com.proyecto.dominio.Torneo;
import com.proyecto.dominio.User;
import com.proyecto.util.dao.DAOUtil;
import com.proyecto.util.displaytag.Paginate;
import com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares;

/**
* Esta clase implementa la interfaz
* UserDAO
* @author Lucas Sánchez López
* @version 2.0
*/
public class UserDAOImpl implements UserDAO {
		
	/**
	 * Usado para insertar o actualizar un usuario
	 * en la base de datos
	 * @param user Objeto User a insertar o actualizar
	 * @return Objeto User insertado o actualizado
	 */
		@Override
		public User mergeUser(User user) {
			DAOUtil daoUtil = new DAOUtil();
			return (User) daoUtil.mergeObject(user);
		}

		/**
		 * Usado para eliminar un usuario
		 * en la base de datos
		 * @param user Objeto User a eliminar
		 */
		@Override
		public void deleteUser(User user) {
			DAOUtil daoUtil = new DAOUtil();
			daoUtil.deleteObject(user); 
		}
		
		/**
		 * Usado para listar todos los usuarios
		 * en la base de datos
		 */
		@SuppressWarnings("unchecked")
		@Override
		public List<User> listUser() {
			DAOUtil daoUtil = new DAOUtil();

			return (List<User>) daoUtil.listQuery("from User");
		}
		
		
		/**
		 * Usado para listar usuarios según consulta a la base de datos
		 * @param request Objeto HttpServletRequest
		 * @param aliasTable Cadena de caracteres con el alias de la tabla de usuario
		 * @param where Cadena de caracteres con la parte principal de la sentencia
		 * @param tablasAux Cadena de caracteres con los nombres de las tablas auxiliares para la consulta 
		 * @return Objeto Paginate de la lista de equipos
		 * @deprecated
		 * 
		 */
		@Override
		public Paginate listUserQuery(HttpServletRequest request,String aliasTable,String where,String tablasAux) {			
			DAOUtil daoUtil = new DAOUtil();
			
			return daoUtil.listFromPaginableQuery("User",request,aliasTable,where,tablasAux,null);
		}
		
//		@SuppressWarnings("unchecked")
//		@Override
//		public List<User> listUserQuery(String aliasTable,String where,String tablasAux) {			
//			DAOUtil daoUtil = new DAOUtil();
//			
//			return (List<User>) daoUtil.listQuery("from User as "+aliasTable+tablasAux+where);
//		}
		
		/**
		 * Usado para listar usuarios según consulta a la base de datos
		 * @param where Cadena de caracteres con la parte principal de la sentencia
		 * @return Lista de objetos User
		 * 
		 */
		@SuppressWarnings("unchecked")
		@Override
		public List<User> listUserQuery(String where) {			
			DAOUtil daoUtil = new DAOUtil();
			
			return (List<User>) daoUtil.listQuery("from User "+where);
		}
		
		/**
		 * Usado para listar usuarios de una comunidad
		 * @param request Objeto HttpServletRequest
		 * @param com Comunidad a consultar 
		 * @return Objeto Paginate de la lista de usuarios
		 * @deprecated
		 * 
		 */
		@Override
		public Paginate listUserByComunidad(HttpServletRequest request, Comunidad com) {			
			DAOUtil daoUtil = new DAOUtil();
			
			String where = " where id in (select id.parentUser from UserComunidad where id.parentComunidad="+com.getId()+" and" +
					" rango <="+FuncionesAuxiliares.NORMALUSER+")";
			
			return daoUtil.listFromPaginableQuery("User",request,where,null);
		}
		
		/**
		 * Usado para listar usuarios de una comunidad
		 * @param com Comunidad a consultar 
		 * @return Lista de objetos User
		 * 
		 */
		@SuppressWarnings("unchecked")
		@Override
		public List<User> listUserByComunidad(Comunidad com) {			
			DAOUtil daoUtil = new DAOUtil();
			
			String where = " where id in (select id.parentUser from UserComunidad where id.parentComunidad="+com.getId()+" and" +
					" rango <="+FuncionesAuxiliares.NORMALUSER+")";
			
			return (List<User>) daoUtil.listQuery("from User"+where);
		}
		
		/**
		 * Usado para listar usuarios de un torneo
		 * @param request Objeto HttpServletRequest
		 * @param tor Torneo a consultar 
		 * @return Objeto Paginate de la lista de usuarios
		 * @deprecated
		 * 
		 */
		@Override
		public Paginate listUserByTorneo(HttpServletRequest request, Torneo tor) {			
			DAOUtil daoUtil = new DAOUtil();
			
			String where = " where id in (select id.parentUser from UserTorneo where id.parentTorneo="+tor.getId()+" and" +
					" rango <="+FuncionesAuxiliares.NORMALUSER+")";
			
			return daoUtil.listFromPaginableQuery("User",request,where,null);
		}
		
		/**
		 * Usado para listar usuarios de un torneo
		 * @param tor Torneo a consultar 
		 * @return Lista de objetos User
		 * 
		 */
		@SuppressWarnings("unchecked")
		@Override
		public List<User> listUserByTorneo(Torneo tor) {			
			DAOUtil daoUtil = new DAOUtil();
			
			String where = " where id in (select id.parentUser from UserTorneo where id.parentTorneo="+tor.getId()+" and" +
					" rango <="+FuncionesAuxiliares.NORMALUSER+")";
			
			return (List<User>) daoUtil.listQuery("from User"+where);
		}
		
		/**
		 * Usado para listar usuarios de un equipo
		 * @param request Objeto HttpServletRequest
		 * @param eq Equipo a consultar 
		 * @return Objeto Paginate de la lista de usuarios
		 * @deprecated
		 * 
		 */
		@Override
		public Paginate listUserByEquipo(HttpServletRequest request, Equipo eq) {			
			DAOUtil daoUtil = new DAOUtil();
			
			String where = " where id in (select id.parentUser from UserEquipo where id.parentEquipo="+eq.getId()+" and" +
					" rango <="+FuncionesAuxiliares.NORMALUSER+")";
			
			return daoUtil.listFromPaginableQuery("User",request,where,null);
		}
		
		/**
		 * Usado para listar usuarios de un equipo
		 * @param eq Equipo a consultar 
		 * @return Lista de objetos User
		 * 
		 */
		@SuppressWarnings("unchecked")
		@Override
		public List<User> listUserByEquipo(Equipo eq) {			
			DAOUtil daoUtil = new DAOUtil();
			
			String where = " where id in (select id.parentUser from UserEquipo where id.parentEquipo="+eq.getId()+" and" +
					" rango <="+FuncionesAuxiliares.NORMALUSER+")";
			
			return (List<User>) daoUtil.listQuery("from User"+where);
		}
		
		/**
		 * Usado para listar usuarios invitados de un torneo
		 * @param tor Torneo a consultar 
		 * @return Lista de objetos User
		 * 
		 */
		@SuppressWarnings("unchecked")
		@Override
		public List<User> listInvitadosByTorneo(Torneo tor) {			
			DAOUtil daoUtil = new DAOUtil();
			
			String where = " where id in (select id.parentUser from UserTorneo where id.parentTorneo="+tor.getId()+" and" +
					" rango <="+FuncionesAuxiliares.NORMALUSER+" and invitado=true)";
			
			return (List<User>) daoUtil.listQuery("from User"+where);
		}
	
	}
