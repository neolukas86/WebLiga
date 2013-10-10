package com.proyecto.dao;

import java.util.List;

import com.proyecto.dominio.Regla;
import com.proyecto.util.dao.DAOUtil;

/**
* Esta clase implementa la interfaz
* ReglaDAO
* @author Lucas Sánchez López
* @version 2.0
*/
public class ReglaDAOImpl implements ReglaDAO{
	
	/**
	 * Usado para listar todas las reglas
	 * en la base de datos
	 * @return Lista de objetos Regla
	 */
	@SuppressWarnings("unchecked")
	public List<Regla> listRegla(){
		DAOUtil daoUtil = new DAOUtil();
		
		return (List<Regla>) daoUtil.listFromCacheable("Regla"); // El select all sobre Regla, y cacheable a true.
	}
	
	/**
	 * Usado para listar todas las reglas
	 * que tengan puntuación
	 * en la base de datos
	 * @return Lista de objetos Regla
	 */
	@SuppressWarnings("unchecked")
	public List<Regla> listReglaConPuntos(){
		DAOUtil daoUtil = new DAOUtil();
		
		String where = "where puntuacion=true";
		return (List<Regla>) daoUtil.listQuery("from Regla "+where);
	}
	
	/**
	 * Usado para listar todas las reglas
	 * que no tengan puntuación
	 * en la base de datos
	 * @return Lista de objetos Regla
	 */
	@SuppressWarnings("unchecked")
	public List<Regla> listReglaSinPuntos(){
		DAOUtil daoUtil = new DAOUtil();
		
		String where = "where puntuacion=false";
		return (List<Regla>) daoUtil.listQuery("from Regla "+where);
	}
}
