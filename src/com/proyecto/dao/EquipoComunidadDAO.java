package com.proyecto.dao;

import com.proyecto.dominio.EquipoComunidad;

/**
* Interfaz DAO para la relación de equipos y comunidades
* @author Lucas Sánchez López
* @version 2.0
*/
public interface EquipoComunidadDAO {
	public EquipoComunidad mergeEquipoComunidad(EquipoComunidad equipoComunidad);
//	public List<Comunidad> listComunidad();
//	public List<Comunidad> listComunidadByIdUser(int iduser);
	public void deleteEquipoComunidad(EquipoComunidad equipoComunidad);
}
