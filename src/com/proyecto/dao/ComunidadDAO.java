package com.proyecto.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.proyecto.dominio.Comunidad;
import com.proyecto.util.displaytag.Paginate;

/**
* Interfaz DAO para las comunidades
* @author Lucas Sánchez López
* @version 2.0
*/
public interface ComunidadDAO {
	public Comunidad mergeComunidad(Comunidad comunidad);
	public Paginate listComunidad(HttpServletRequest request);
	public List<Comunidad> listComunidad();
	public Paginate listComunidadQuery(HttpServletRequest request,String aliasTable,String where,String tablasAux);
	public List<Comunidad> listComunidadQuery(String aliasTable,String where,String tablasAux);
	public Paginate listComunidadByIdUser(HttpServletRequest request, int iduser);
	public List<Comunidad> listComunidadByIdUser(int iduser);
	public void deleteComunidad(Comunidad comunidad);
	public List<Comunidad> listComunidadByIdEquipo(int idequipo);
}
