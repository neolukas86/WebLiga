package com.proyecto.dao;

import java.util.List;

import com.proyecto.dominio.Actividad;
import com.proyecto.dominio.TipoActividad;
import com.proyecto.util.dao.DAOUtil;

/**
* Esta clase implementa la interfaz
* ActividadDAO
* @author Lucas Sánchez López
* @version 2.0
*/

public class ActividadDAOImpl implements ActividadDAO{

//	// TipoActividad
//	public TipoActividad mergeTipoActividad(TipoActividad tipoActividad) {
//		DAOUtil daoUtil = new DAOUtil();
//		return (TipoActividad) daoUtil.mergeObject(tipoActividad);
//	}
//
//	// Actividad
//	public Actividad mergeActividad(Actividad actividad) {
//		DAOUtil daoUtil = new DAOUtil();
//		return (Actividad) daoUtil.mergeObject(actividad);
//	}
	
	/**
	 * Usado para eliminar un tipo de actividad
	 * @param tipoActividad El tipo de actividad a eliminar
	 */
	// TipoActividad
	public void deleteTipoActividad(TipoActividad tipoActividad) {
		DAOUtil daoUtil = new DAOUtil();
		daoUtil.deleteObject(tipoActividad);
	}
	
	/**
	 * Usado para eliminar una actividad
	 * @param actividad La actividad a eliminar
	 */
	// Actividad
	public void deleteActividad(Actividad actividad) {
		DAOUtil daoUtil = new DAOUtil();
		daoUtil.deleteObject(actividad);
	}
	
	/**
	 * Usado para listar todos los tipos de actividad
	 * @return Lista de objetos TipoActividad
	 */
	// TipoActividad
	@SuppressWarnings("unchecked")
	public List<TipoActividad> listTipoActividad() {

		DAOUtil daoUtil = new DAOUtil();

		return (List<TipoActividad>) daoUtil.listFromCacheable("TipoActividad"); // El select all sobre TipoActividad, y cacheable a true.
	}

	/**
	 * Usado para listar todas las actividades
	 * @return Lista de objetos Actividad
	 */
	// Actividad
	@SuppressWarnings("unchecked")
	public List<Actividad> listActividad() {

		DAOUtil daoUtil = new DAOUtil();
		
		return (List<Actividad>) daoUtil.listFromCacheable("Actividad"); // El select all sobre Actividad, y cacheable a true.
	}
	
	/**
	 * Usado para listar las actividades por el tipo de actividad
	 * @param tipo El tipo de actividad sobre el que listar sus actividades
	 * @return Lista de objetos Actividad
	 */
	@SuppressWarnings("unchecked")
	public List<Actividad> listActividadByTipo(TipoActividad tipo) {

		DAOUtil daoUtil = new DAOUtil();
		
		String where = " where parentTipo="+tipo.getId();
		
		return (List<Actividad>) daoUtil.listQuery("from Actividad"+where);
	}
		
}

