package com.proyecto.dao;

import com.proyecto.dominio.UserEquipo;
import com.proyecto.util.dao.DAOUtil;

/**
* Esta clase implementa la interfaz
* UserEquipoDAO
* @author Lucas Sánchez López
* @version 2.0
*/
public class UserEquipoDAOImpl implements UserEquipoDAO{

	/**
	 * Usado para insertar o actualizar una relacion
	 * entre un usuario y un equipo
	 * @param userEquipo Objeto UserEquipo a insertar o actualizar
	 * @return Objeto UserEquipo insertado o actualizado
	 */
	@Override
	public UserEquipo mergeUserEquipo(UserEquipo userEquipo) {
		DAOUtil daoUtil = new DAOUtil();
		return (UserEquipo) daoUtil.mergeObject(userEquipo);
	}

	/**
	 * Usado para eliminar una relacion
	 * entre un usuario y un equipo
	 * @param userEquipo Objeto UserEquipo a elminar
	 */
	@Override
	public void deleteUserEquipo(UserEquipo userEquipo) {
		DAOUtil daoUtil = new DAOUtil();
		daoUtil.deleteObject(userEquipo);
		
	}

}
