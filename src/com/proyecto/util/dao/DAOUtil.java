package com.proyecto.util.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.displaytag.properties.SortOrderEnum;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.proyecto.util.displaytag.Paginate;
import com.proyecto.util.displaytag.PaginateUtilAlternativo;
import com.proyecto.util.hibernate.HibernateUtil;

public class DAOUtil {	
	SessionFactory sessionFactory = null;
	@SessionTarget
	Session session = null;
	
	@TransactionTarget
	Transaction transaction = null;
	
	/**
	 * Usado para insertar o actualizar un objeto
	 * en la base de datos
	 * @param objeto Objeto a insertar o actualizar
	 * @return Objeto insertado o actualizado
	 */
	public Object mergeObject(Object objeto) {
		Object ob = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
//			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			boolean error = false;
			
			try {
				ob = session.merge(objeto);
			}
			catch(Exception e){
				error = true;
				// Informar del error
				e.printStackTrace();
			}
			if(error && transaction != null){
				try{
					transaction.rollback();
				}
				catch(HibernateException he){
					// Informar de error haciendo rollback
					he.printStackTrace();
					transaction.rollback();
				}
			}
			else if(transaction != null){
				try{
					transaction.commit();
				}
				catch(HibernateException he){
					// Informar de error haciendo commit
					he.printStackTrace();
					transaction.commit();		
				}
			}
		} catch (HibernateException he) {
			// Informar del error cerrando sesion
			he.printStackTrace();
		}
		finally{ // No estoy seguro de si es conveniente cerrar session, pero lo dejo asi de momento 
			if(session != null){
				try{
					session.close();
				}
				catch(HibernateException he){
					// Informar de error haciendo cierre de sesion
					he.printStackTrace();
					session.close();
				}
			}
		}
		return ob;
	}
	
	/**
	 * Usado para eliminar un objeto
	 * en la base de datos
	 * @param objeto Objeto a eliminar
	 */
	public void deleteObject(Object objeto) {
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			
			transaction = session.beginTransaction();
			boolean error = false;
			
			try {
//				System.out.println("------------- Dentro de deleteObject !");
				session.delete(objeto);
//				System.out.println("------------- Después de 'hacer' el delete");
			}
			catch(Exception e){
				error = true;
				// Informar del error
				e.printStackTrace();
			}
			if(error && transaction != null){
				try{
					System.out.println("***** Rollback !! *****");
					transaction.rollback();
				}
				catch(HibernateException he){
					// Informar de error haciendo rollback
					he.printStackTrace();
					transaction.rollback();
				}
			}
			else if(transaction != null){
				try{
					transaction.commit();
				}
				catch(HibernateException he){
					// Informar de error haciendo commit
					he.printStackTrace();
					transaction.commit();		
				}
			}
		} catch (HibernateException he) {
			// Informar del error cerrando sesion
			he.printStackTrace();
		}
		finally{ // No estoy seguro de si es conveniente cerrar session, pero lo dejo asi de momento 
			if(session != null){
				try{
					session.close();
				}
				catch(HibernateException he){
					// Informar de error haciendo cierre de sesion
					he.printStackTrace();
					session.close();
				}
			}
		}
	}
	
	
	
	/**
	 * Usado para listar, usando caché,
	 * todos los objetos de una tabla
	 * en la base de datos
	 * @param entidad Nombre de la tabla
	 * @return Lista de objetos
	 */
	@SuppressWarnings("finally") // Para los cacheables no haremos paginación pq queremos la lista al completo
	public List<?> listFromCacheable(String entidad) {

		@SuppressWarnings("rawtypes")
		List lista = new ArrayList();

		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				lista = session.createQuery("from "+entidad)
											.setCacheable(true)
											.list();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		catch(HibernateException he){
			he.printStackTrace();
		}
		finally{
			if(session != null){
				try{
					session.close();
				}
				catch(HibernateException he){
					// Informar de error haciendo cierre de sesion
					he.printStackTrace();
					session.close();
				}
			}
			return lista;
		}
	}
	
//	@SuppressWarnings("finally") // Para los cacheables no haremos paginación pq queremos la lista al completo
//	public List<?> listFromParent(String entidad, String parentTipo, int idParent) {
//
//		@SuppressWarnings("rawtypes")
//		List lista = new ArrayList();
//
//		try{
//			sessionFactory = HibernateUtil.getSessionFactory();
//			session = sessionFactory.openSession();
//			try{
//				lista = session.createQuery("from "+entidad+" where "+parentTipo+"=?")
//											.setInteger(0,idParent)
//											.list();
//			}
//			catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//		catch(HibernateException he){
//			he.printStackTrace();
//		}
//		finally{
//			if(session != null){
//				try{
//					session.close();
//				}
//				catch(HibernateException he){
//					// Informar de error haciendo cierre de sesion
//					he.printStackTrace();
//					session.close();
//				}
//			}
//			return lista;
//		}
//	}	
	
	/**
	 * Usado para listar según una consulta
	 * los objetos de una tabla
	 * en la base de datos
	 * @param tableName Nombre de la tabla
	 * @param request Objeto HttpServletRequest
	 * @param searchWord HashMap con la palabra de búsqueda
	 * @param condicion HashMap con las condiciones de búsqueda
	 * @param orderBy Cadena del campo 
	 * sobre el que se realizará el orden de muestra
	 * @param objsPerPage Número de objetos por página
	 * @return Objeto Paginate de la lista de objetos
	 * @deprecated
	 */
	public Paginate listFromPaginable(String tableName,HttpServletRequest request,
			HashMap<String,String> searchWord,HashMap<String,String> condicion, String orderBy,Integer objsPerPage){
		
		System.out.println("+++ -- Entramos en listFromPaginable -- +++");
		
		Paginate paginate = new Paginate(tableName,request,SortOrderEnum.ASCENDING,objsPerPage);
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
//				System.out.println("+++ -- Vamos a entrar en el getPaginate -- +++");
				paginate = PaginateUtilAlternativo.getPaginate(session, tableName,paginate, searchWord, condicion,orderBy);
//				System.out.println("+++ -- Hemos salido del getPaginate -- +++");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		catch(HibernateException he){
			he.printStackTrace();
		}
		
		return paginate;
	}
	
	/**
	 * Usado para listar según una consulta
	 * los objetos de una tabla
	 * en la base de datos
	 * @param tableName Nombre de la tabla
	 * @param request Objeto HttpServletRequest
	 * @param aliasTable Cadena de caracteres con el alias de la tabla
	 * @param where Cadena de caracteres con la parte principal de la sentencia
	 * @param tablasAux Cadena de caracteres con los nombres de las tablas auxiliares para la consulta
	 * @param objsPerPage Número de objetos por página
	 * @return Objeto Paginate de la lista de objetos
	 */
	public Paginate listFromPaginableQuery(String tableName,HttpServletRequest request,
			String aliasTable, String where, String tablasAux,Integer objsPerPage){
		
		System.out.println("+++ -- Entramos en listFromPaginable -- +++");
		
		Paginate paginate = new Paginate(tableName,request,SortOrderEnum.ASCENDING,objsPerPage);
		
		if(objsPerPage != null){
			paginate.setObjectsPerPage(objsPerPage);
		}
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				paginate.setFullListSize(PaginateUtilAlternativo.fullListSize(session, paginate, tableName, aliasTable, where, tablasAux).intValue());
				
				paginate = PaginateUtilAlternativo.getPaginate(session, paginate,
																"select "+aliasTable+" from "+tableName+" as "+aliasTable+tablasAux+where);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		catch(HibernateException he){
			he.printStackTrace();
		}
		
		return paginate;
	}	
	
	/**
	 * Usado para listar según una consulta
	 * los objetos de una tabla
	 * en la base de datos
	 * @param tableName Nombre de la tabla
	 * @param request Objeto HttpServletRequest
	 * @param where Cadena de caracteres con la parte principal de la sentencia
	 * @param objsPerPage Número de objetos por página
	 * @return Objeto Paginate de la lista de objetos
	 */
	public Paginate listFromPaginableQuery(String tableName,HttpServletRequest request,String where,Integer objsPerPage){
		
		System.out.println("+++ -- Entramos en listFromPaginableQuery -- +++");
		
		Paginate paginate = new Paginate(tableName,request,SortOrderEnum.ASCENDING,objsPerPage);
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				System.out.println("+++ -- Vamos a entrar en el getPaginate -- +++");
				System.out.println("where -> "+where);
				
				paginate.setFullListSize(PaginateUtilAlternativo.fullListSize(session, paginate, tableName, where).intValue());
				
				System.out.println("Tamaño del full list --> "+paginate.getFullListSize());
				
				paginate = PaginateUtilAlternativo.getPaginate(session, paginate,
																" from "+tableName+where);
//				System.out.println("+++ -- Hemos salido del getPaginate -- +++");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		catch(HibernateException he){
			he.printStackTrace();
		}
		
		return paginate;
	}
	
	/**
	 * Usado para listar según una consulta
	 * en la base de datos
	 * @param query Cadena de caracteres con la parte principal de la sentencia
	 * @return Lista de objetos
	 */
	@SuppressWarnings("finally")
	public List<?> listQuery(String query){
		List<?> lista=null;
		
		System.out.println("+++ -- Entramos en listQuery -- +++");
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				lista = session.createQuery(query)
										.list();
				
				
//				Iterator<PartidoIndividualVersus> it = (Iterator<PartidoIndividualVersus>) lista.iterator();
//				while(it.hasNext()){
//					PartidoIndividualVersus piv = it.next();
//					
//					System.out.println("Jornada -> "+piv.getJornada()+
//							"  Local -> "+piv.getParentLocal().getAlias()+
//							"  Visitante -> "+piv.getParentVisitante().getAlias());					
//				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		catch(HibernateException he){
			he.printStackTrace();
		}
		finally{
			if(session != null){
				try{
					session.close();
				}
				catch(HibernateException he){
					// Informar de error haciendo cierre de sesion
					he.printStackTrace();
					session.close();
				}
			}
			return lista;
		}
	}
	
	/**
	 * Usado para realizar la cuenta de tuplas de
	 * una consulta en la base de datos
	 * @param query Cadena de caracteres con la parte principal de la sentencia
	 * @return Número de tuplas
	 */
	@SuppressWarnings("finally")
	public Integer CountListQuery(String query){
		Integer filas=null;
		
		System.out.println("+++ -- Entramos en listQuery -- +++");
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				filas = ((Number) session.createQuery("select count(*) "+query)
										.uniqueResult())
										.intValue();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		catch(HibernateException he){
			he.printStackTrace();
		}
		finally{
			if(session != null){
				try{
					session.close();
				}
				catch(HibernateException he){
					// Informar de error haciendo cierre de sesion
					he.printStackTrace();
					session.close();
				}
			}
			return filas;
		}
	}

	
	
}
