package com.proyecto.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.proyecto.dominio.Noticia;
import com.proyecto.dominio.CategoriaNoticia;
import com.proyecto.util.dao.DAOUtil;
import com.proyecto.util.displaytag.Paginate;

/**
* Esta clase implementa la interfaz
* NoticiaDAO
* @author Lucas Sánchez López
* @version 2.0
*/
public class NoticiaDAOImpl implements NoticiaDAO{
//	// CategoriaNoticia
//	@Override
//	public CategoriaNoticia mergeCategoriaNoticia(CategoriaNoticia categoriaNoticia) {
//		DAOUtil daoUtil = new DAOUtil();
//		return (CategoriaNoticia) daoUtil.mergeObject(categoriaNoticia);
//	}

	/**
	 * Usado para insertar o actualizar una noticia
	 * en la base de datos
	 * @param noticia Objeto Noticia a insertar o actualizar
	 * @return Objeto Noticia insertado o actualizado
	 */
	// Noticia
	@Override
	public Noticia mergeNoticia(Noticia noticia) {
		DAOUtil daoUtil = new DAOUtil();
		return (Noticia) daoUtil.mergeObject(noticia);
	}
	
//	// CategoriaNoticia
//	@Override
//	public void deleteCategoriaNoticia(CategoriaNoticia categoriaNoticia) {
//		DAOUtil daoUtil = new DAOUtil();
//		daoUtil.deleteObject(categoriaNoticia);
//	}

	/**
	 * Usado para eliminar una noticia
	 * en la base de datos
	 * @param noticia Objeto Noticia a eliminar
	 */
	// Noticia
	@Override
	public void deleteNoticia(Noticia noticia) {
		DAOUtil daoUtil = new DAOUtil();
		daoUtil.deleteObject(noticia);
	}
	

	/**
	 * Usado para listar categorias de noticia
	 * según consulta a la base de datos
	 * @param aliasTable Cadena de caracteres con el alias 
	 * de la tabla de categoria de noticia
	 * @param where Cadena de caracteres con la parte principal de la sentencia
	 * @param tablasAux Cadena de caracteres con los nombres de las tablas auxiliares para la consulta
	 * @return Lista de objetos CategoriaNoticia
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoriaNoticia> listCategoriaNoticia(String aliasTable,String where,String tablasAux) {

		DAOUtil daoUtil = new DAOUtil();

		return  (List<CategoriaNoticia>) daoUtil.listQuery("select "+aliasTable+" from CategoriaNoticia as "+aliasTable+tablasAux+where); 
	}

	/**
	 * Usado para listar noticias
	 * según consulta a la base de datos
	 * @param request Objeto HttpServletRequest
	 * @param aliasTable Cadena de caracteres con el alias 
	 * de la tabla de noticia
	 * @param where Cadena de caracteres con la parte principal de la sentencia
	 * @param tablasAux Cadena de caracteres con los nombres de las tablas auxiliares para la consulta
	 * @return Objeto Paginate de la lista de noticias
	 * @deprecated
	 */
	// Noticia
	@Override
	public Paginate listNoticia(HttpServletRequest request,String aliasTable,String where,String tablasAux) {

		DAOUtil daoUtil = new DAOUtil();

		return  daoUtil.listFromPaginableQuery("Noticia",request,aliasTable,where,tablasAux,null); 
	}
	
	/**
	 * Usado para listar noticias
	 * según consulta a la base de datos
	 * @param aliasTable Cadena de caracteres con el alias 
	 * de la tabla de noticia
	 * @param where Cadena de caracteres con la parte principal de la sentencia
	 * @param tablasAux Cadena de caracteres con los nombres de las tablas auxiliares para la consulta
	 * @return Lista de objetos Noticia
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Noticia> listNoticia(String aliasTable,String where,String tablasAux) {

		DAOUtil daoUtil = new DAOUtil();

		return  (List<Noticia>) daoUtil.listQuery("select "+aliasTable+" from Noticia as "+aliasTable+tablasAux+where); 
	}

}
