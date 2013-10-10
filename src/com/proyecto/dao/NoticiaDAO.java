package com.proyecto.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.proyecto.dominio.Noticia;
import com.proyecto.dominio.CategoriaNoticia;
import com.proyecto.util.displaytag.Paginate;

/**
* Interfaz DAO para las noticias
* @author Lucas Sánchez López
* @version 2.0
*/
public interface NoticiaDAO {
//	public CategoriaNoticia mergeCategoriaNoticia(CategoriaNoticia categoriaNoticia);
//	public Paginate listCategoriaNoticia(HttpServletRequest request,String aliasTable,String where,String tablasAux);
	public List<CategoriaNoticia> listCategoriaNoticia(String aliasTable,String where,String tablasAux);
//	public void deleteCategoriaNoticia(CategoriaNoticia categoriaNoticia);

	public Noticia mergeNoticia(Noticia noticia);
	public Paginate listNoticia(HttpServletRequest request,String aliasTable,String where,String tablasAux);
	public List<Noticia> listNoticia(String aliasTable,String where,String tablasAux);
	public void deleteNoticia(Noticia noticia);
}
