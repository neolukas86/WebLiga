package com.proyecto.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.proyecto.dominio.Comunidad;
import com.proyecto.dominio.Equipo;
import com.proyecto.dominio.Torneo;
import com.proyecto.dominio.User;
import com.proyecto.util.displaytag.Paginate;

/**
* Interfaz DAO para los usuarios
* @author Lucas Sánchez López
* @version 2.0
*/
public interface UserDAO {
	public User mergeUser(User user);
//	public Paginate listUser(HttpServletRequest request);
	//public User listUserById(Long userId);
	public void deleteUser(User user);
	public List<User> listUser();
	public Paginate listUserQuery(HttpServletRequest request, String aliasTable,
			String where, String tablasAux);
//	public List<User> listUserQuery(String aliasTable,String where,String tablasAux);
	public List<User> listUserQuery(String where);
	public Paginate listUserByComunidad(HttpServletRequest request, Comunidad com);
	public List<User> listUserByComunidad(Comunidad com);
	public Paginate listUserByTorneo(HttpServletRequest request, Torneo torneo);
	public List<User> listUserByTorneo(Torneo tor);
	public Paginate listUserByEquipo(HttpServletRequest request, Equipo eq);
	public List<User> listUserByEquipo(Equipo eq);
	public List<User> listInvitadosByTorneo(Torneo tor);

}
