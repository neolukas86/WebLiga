package com.proyecto.dao;

import com.proyecto.dominio.EquipoComunidad;
import com.proyecto.dao.EquipoComunidadDAO;
import com.proyecto.util.dao.DAOUtil;

/**
* Esta clase implementa la interfaz
* EquipoComunidadDAO
* @author Lucas Sánchez López
* @version 2.0
*/
public class EquipoComunidadDAOImpl implements EquipoComunidadDAO {

	/**
	 * Usado para insertar o actualizar la relación
	 * entre un equipo y una comunidad
	 * @param equipoComunidad Objeto EquipoComunidad a insertar o actualizar
	 * @return Objeto EquipoComunidad insertado o actualizado
	 */
	public EquipoComunidad mergeEquipoComunidad(EquipoComunidad equipoComunidad){
		DAOUtil daoUtil = new DAOUtil();
		return (EquipoComunidad) daoUtil.mergeObject(equipoComunidad);
	}
	
	/**
	 * Usado para eliminar la relación
	 * entre un equipo y una comunidad
	 * @param equipoComunidad Objeto EquipoComunidad a eliminar
	 */
	@Override
	public void deleteEquipoComunidad(EquipoComunidad equipoComunidad) {
		DAOUtil daoUtil = new DAOUtil();
		daoUtil.deleteObject(equipoComunidad);
	}
}
