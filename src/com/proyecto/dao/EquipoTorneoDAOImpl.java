package com.proyecto.dao;

import java.util.List;

import com.proyecto.dominio.EquipoTorneo;
import com.proyecto.dominio.Torneo;
import com.proyecto.dao.EquipoTorneoDAO;
import com.proyecto.util.dao.DAOUtil;
import com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares;

/**
* Esta clase implementa la interfaz
* EquipoTorneoDAO
* @author Lucas Sánchez López
* @version 2.0
*/
public class EquipoTorneoDAOImpl implements EquipoTorneoDAO {

	/**
	 * Usado para insertar o actualizar una relacion
	 * entre un equipo y un torneo
	 * @param equipoTorneo Objeto EquipoTorneo a insertar o actualizar
	 * @return Objeto EquipoTorneo insertado o actualizado
	 */
	public EquipoTorneo mergeEquipoTorneo(EquipoTorneo equipoTorneo){
		DAOUtil daoUtil = new DAOUtil();
		return (EquipoTorneo) daoUtil.mergeObject(equipoTorneo);
	}
	
	/**
	 * Usado para eliminar una relacion
	 * entre un equipo y un torneo
	 * @param equipoTorneo Objeto EquipoTorneo a eliminar
	 */
	@Override
	public void deleteEquipoTorneo(EquipoTorneo equipoTorneo) {
		DAOUtil daoUtil = new DAOUtil();
		daoUtil.deleteObject(equipoTorneo);
	}
	
	/**
	 * Usado para las relaciones
	 * entre un equipo y un torneo
	 * donde el equipo es un participante
	 * @param tor Torneo sobre el que realizar la consulta
	 * @return Lista de objetos EquipoTorneo
	 */
	@SuppressWarnings("unchecked")
	public List<EquipoTorneo> EquipoTorneoParticipante(Torneo tor){
		DAOUtil daoUtil = new DAOUtil();
		
		return (List<EquipoTorneo>) daoUtil.listQuery("from EquipoTorneo" +
						" where id.parentTorneo="+tor.getId()+
						" and rango <="+FuncionesAuxiliares.NORMALUSER);
	}
	
	/**
	 * Usado para las relaciones
	 * entre un equipo y un torneo
	 * donde el equipo ha sido eliminado del campeonato
	 * @param tor Torneo sobre el que realizar la consulta
	 * @return Lista de objetos EquipoTorneo
	 */
	@SuppressWarnings("unchecked")
	public List<EquipoTorneo> EquipoTorneoEliminados(Torneo tor){
		DAOUtil daoUtil = new DAOUtil();
		
		return (List<EquipoTorneo>) daoUtil.listQuery("from EquipoTorneo" +
						" where id.parentTorneo="+tor.getId()+
						" and participante = true"+
						" and eliminado = true");
	}
	
	/**
	 * Usado para las relaciones
	 * entre un equipo y un torneo
	 * donde el equipo no ha sido eliminado del campeonato
	 * @param tor Torneo sobre el que realizar la consulta
	 * @return Lista de objetos EquipoTorneo
	 */
	@SuppressWarnings("unchecked")
	public List<EquipoTorneo> EquipoTorneoNoEliminados(Torneo tor){
		DAOUtil daoUtil = new DAOUtil();
		
		return (List<EquipoTorneo>) daoUtil.listQuery("from EquipoTorneo" +
						" where id.parentTorneo="+tor.getId()+
						" and participante = true"+
						" and eliminado = false");
	}	
}