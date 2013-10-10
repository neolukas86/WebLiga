package com.proyecto.dao;

import com.proyecto.dominio.UserEquipo;

/**
* Interfaz DAO para la relaci�n de los usuarios
* con las equipos
* @author Lucas S�nchez L�pez
* @version 2.0
*/
public interface UserEquipoDAO {
	public UserEquipo mergeUserEquipo(UserEquipo userEquipo);
	public void deleteUserEquipo(UserEquipo userEquipo);
}
