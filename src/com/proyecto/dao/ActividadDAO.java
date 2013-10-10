package com.proyecto.dao;

import java.util.List;

import com.proyecto.dominio.Actividad;
import com.proyecto.dominio.TipoActividad;

/**
* Interfaz DAO para las actividades
* @author Lucas Sánchez López
* @version 2.0
*/

public interface ActividadDAO {
//	public TipoActividad mergeTipoActividad(TipoActividad tipoActividad);
	public List<TipoActividad> listTipoActividad();
	public void deleteTipoActividad(TipoActividad tipoActividad);

//	public Actividad mergeActividad(Actividad actividad);
	public List<Actividad> listActividad();
	public void deleteActividad(Actividad actividad);
	
	public List<Actividad> listActividadByTipo(TipoActividad tipo);
	
}

