package com.proyecto.dao;

import com.proyecto.dominio.UserComunidad;
import com.proyecto.dao.UserComunidadDAO;
import com.proyecto.util.dao.DAOUtil;

/**
* Esta clase implementa la interfaz
* UserComunidadDAO
* @author Lucas Sánchez López
* @version 2.0
*/
public class UserComunidadDAOImpl implements UserComunidadDAO {

	/**
	 * Usado para insertar o actualizar una relacion
	 * entre un usuario y una comunidad
	 * @param userComunidad Objeto UserComunidad a insertar o actualizar
	 * @return Objeto UserComunidad insertado o actualizado
	 */
	public UserComunidad mergeUserComunidad(UserComunidad userComunidad){
		DAOUtil daoUtil = new DAOUtil();
		return (UserComunidad) daoUtil.mergeObject(userComunidad);
	}
	
	/**
	 * Usado para eliminiar una relacion
	 * entre un usuario y una comunidad
	 * @param userComunidad Objeto UserComunidad a eliminar
	 */
	@Override
	public void deleteUserComunidad(UserComunidad userComunidad) {
		DAOUtil daoUtil = new DAOUtil();
		daoUtil.deleteObject(userComunidad);
	}
}
