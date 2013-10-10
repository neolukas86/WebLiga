package com.proyecto.dao;

import java.util.List;

import com.proyecto.dominio.Plataforma;
import com.proyecto.util.dao.DAOUtil;

/**
* Esta clase implementa la interfaz
* PlataformaDAO
* @author Lucas Sánchez López
* @version 2.0
*/
public class PlataformaDAOImpl implements PlataformaDAO {
//	public Plataforma mergePlataforma(Plataforma plataforma){
//		DAOUtil daoUtil = new DAOUtil();
//		return (Plataforma) daoUtil.mergeObject(plataforma);
//	}

	/**
	 * Usado para eliminar una plataforma
	 * en la base de datos
	 * @param plataforma Objeto Plataforma a eliminar
	 */
	public void deletePlataforma(Plataforma plataforma){
		DAOUtil daoUtil = new DAOUtil();
		daoUtil.deleteObject(plataforma);		
	}
	
	/**
	 * Usado para listar todas las plataformas
	 * en la base de datos
	 * @return Lista de objetos Plataforma
	 */
	@SuppressWarnings("unchecked")
	public List<Plataforma> listPlataforma(){
		DAOUtil daoUtil = new DAOUtil();

		return (List<Plataforma>) daoUtil.listFromCacheable("Plataforma"); // El select all sobre Plataforma, y cacheable a true.		
		
	}	
}
