package com.proyecto.dao;

import java.util.List;

import com.proyecto.dominio.Torneo;
import com.proyecto.dominio.UserTorneo;
import com.proyecto.dao.UserTorneoDAO;
import com.proyecto.util.dao.DAOUtil;
import com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares;

/**
* Esta clase implementa la interfaz
* UserTorneoDAO
* @author Lucas Sánchez López
* @version 2.0
*/
public class UserTorneoDAOImpl implements UserTorneoDAO {

	/**
	 * Usado para insertar o actualizar una relacion
	 * entre un usuario y un torneo
	 * @param userTorneo Objeto UserTorneo a insertar o actualizar
	 * @return Objeto UserTorneo insertado o actualizado
	 */
	public UserTorneo mergeUserTorneo(UserTorneo userTorneo){
		DAOUtil daoUtil = new DAOUtil();
		return (UserTorneo) daoUtil.mergeObject(userTorneo);
	}
	
	/**
	 * Usado para eliminar una relacion
	 * entre un usuario y un torneo
	 * @param userTorneo Objeto UserTorneo a eliminar
	 */
	@Override
	public void deleteUserTorneo(UserTorneo userTorneo) {
		DAOUtil daoUtil = new DAOUtil();
		daoUtil.deleteObject(userTorneo);
	}
	
	/**
	 * Usado para listar las relaciones
	 * entre usuarios participantes en el torneo
	 * y el torneo
	 * @param tor Torneo sobre el que realizar la consulta
	 * @return Lista de objetos UserTorneo
	 */
	@SuppressWarnings("unchecked")
	public List<UserTorneo> UserTorneoParticipante(Torneo tor){
		DAOUtil daoUtil = new DAOUtil();
		
		return (List<UserTorneo>) daoUtil.listQuery("from UserTorneo" +
						" where id.parentTorneo="+tor.getId()+
						" and rango <="+FuncionesAuxiliares.NORMALUSER+
						" and participante = true");
	}
	
	/**
	 * Usado para listar las relaciones
	 * entre usuarios eliminados en el torneo
	 * y el torneo
	 * @param tor Torneo sobre el que realizar la consulta
	 * @return Lista de objetos UserTorneo
	 */
	@SuppressWarnings("unchecked")
	public List<UserTorneo> UserTorneoEliminados(Torneo tor){
		DAOUtil daoUtil = new DAOUtil();
		
		return (List<UserTorneo>) daoUtil.listQuery("from UserTorneo" +
						" where id.parentTorneo="+tor.getId()+
						" and participante = true"+
						" and eliminado = true");
	}
	
	/**
	 * Usado para listar las relaciones
	 * entre usuarios no eliminados en el torneo
	 * y el torneo
	 * @param tor Torneo sobre el que realizar la consulta
	 * @return Lista de objetos UserTorneo
	 */
	@SuppressWarnings("unchecked")
	public List<UserTorneo> UserTorneoNoEliminados(Torneo tor){
		DAOUtil daoUtil = new DAOUtil();
		
		return (List<UserTorneo>) daoUtil.listQuery("from UserTorneo" +
						" where id.parentTorneo="+tor.getId()+
						" and participante = true"+
						" and eliminado = false");
	}
}