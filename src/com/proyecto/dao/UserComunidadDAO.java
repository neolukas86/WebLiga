package com.proyecto.dao;

import com.proyecto.dominio.UserComunidad;

/**
* Interfaz DAO para la relaci�n de los usuarios
* con las comunidades
* @author Lucas S�nchez L�pez
* @version 2.0
*/
public interface UserComunidadDAO {
	public UserComunidad mergeUserComunidad(UserComunidad userComunidad);
//	public List<Comunidad> listComunidad();
//	public List<Comunidad> listComunidadByIdUser(int iduser);
	public void deleteUserComunidad(UserComunidad userComunidad);
}
