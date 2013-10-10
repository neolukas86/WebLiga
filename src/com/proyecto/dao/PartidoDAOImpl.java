package com.proyecto.dao;

import javax.servlet.http.HttpServletRequest;

import com.proyecto.dominio.Equipo;
import com.proyecto.dominio.EquipoTorneo;
import com.proyecto.dominio.PartidoEquiposVersus;
import com.proyecto.dominio.PartidoIndividualVersus;
import com.proyecto.dominio.Torneo;
import com.proyecto.dominio.User;
import com.proyecto.dominio.UserTorneo;
import com.proyecto.util.dao.DAOUtil;
import com.proyecto.util.displaytag.Paginate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
* Esta clase implementa la interfaz
* PartidoDAO
* @author Lucas Sánchez López
* @version 2.0
*/
public class PartidoDAOImpl implements PartidoDAO{
	/**
	 * Usado para insertar o actualizar un partido por equipos
	 * en la base de datos
	 * @param partidoEquiposVersus Objeto PartidoEquiposVersus a insertar o actualizar
	 * @return Objeto PartidoEquiposVersus insertado o actualizado
	 */
	// PartidoEquiposVersus
	@Override
	public PartidoEquiposVersus mergePartidoEquiposVersus(PartidoEquiposVersus partidoEquiposVersus) {
		DAOUtil daoUtil = new DAOUtil();
		return (PartidoEquiposVersus) daoUtil.mergeObject(partidoEquiposVersus);
	}
	
	/**
	 * Usado para insertar o actualizar un partido individual
	 * en la base de datos
	 * @param partidoIndividualVersus Objeto PartidoIndividualVersus a insertar o actualizar
	 * @return Objeto PartidoIndividualVersus insertado o actualizado
	 */
	// PartidoIndividualVersus
	@Override
	public PartidoIndividualVersus mergePartidoIndividualVersus(PartidoIndividualVersus partidoIndividualVersus) {
		DAOUtil daoUtil = new DAOUtil();
		return (PartidoIndividualVersus) daoUtil.mergeObject(partidoIndividualVersus);
	}
	
	/**
	 * Usado para insertar o actualizar una lista de 
	 * partidos por equipos
	 * en la base de datos
	 * @param partidoEquiposVersusSet Lista de objetos PartidoEquiposVersus a insertar o actualizar
	 * @return Lista de objetos PartidoEquiposVersus insertados o actualizados
	 */
	// PartidoEquiposVersus
	@Override
	public List<PartidoEquiposVersus> mergePartidoEquiposVersusList(List<PartidoEquiposVersus> partidoEquiposVersusSet) {
		DAOUtil daoUtil = new DAOUtil();
		
		Iterator<PartidoEquiposVersus> itPartidos = partidoEquiposVersusSet.iterator();
		
		List<PartidoEquiposVersus> list = new ArrayList<PartidoEquiposVersus>();
		while(itPartidos.hasNext()){
			list.add((PartidoEquiposVersus) daoUtil.mergeObject(itPartidos.next()));
		}
		return list;
	}
	
	/**
	 * Usado para insertar o actualizar una lista de 
	 * partidos individuales
	 * en la base de datos
	 * @param partidoIndividualVersusSet Lista de objetos PartidoIndividualVersus a insertar o actualizar
	 * @return Lista de objetos PartidoIndividualVersus insertados o actualizados
	 */
	// PartidoIndividualVersus
	@Override
	public List<PartidoIndividualVersus> mergePartidoIndividualVersusList(List<PartidoIndividualVersus> partidoIndividualVersusSet) {
		DAOUtil daoUtil = new DAOUtil();
		
		Iterator<PartidoIndividualVersus> itPartidos = partidoIndividualVersusSet.iterator();
		
		List<PartidoIndividualVersus> list = new ArrayList<PartidoIndividualVersus>();
		while(itPartidos.hasNext()){
			list.add((PartidoIndividualVersus) daoUtil.mergeObject(itPartidos.next()));
		}
		return list;
	}	
	
	/**
	 * Usado para eliminar un partido por equipos
	 * en la base de datos
	 * @param partidoEquiposVersus Objeto PartidoEquiposVersus a eliminar
	 */
	// PartidoEquiposVersus
	@Override
	public void deletePartidoEquiposVersus(PartidoEquiposVersus partidoEquiposVersus) {
		DAOUtil daoUtil = new DAOUtil();
		daoUtil.deleteObject(partidoEquiposVersus);
	}
	
	/**
	 * Usado para eliminar un partido individual
	 * en la base de datos
	 * @param partidoIndividualVersus Objeto PartidoIndividualVersus a eliminar
	 */
	// PartidoIndividualVersus
	@Override
	public void deletePartidoIndividualVersus(PartidoIndividualVersus partidoIndividualVersus) {
		DAOUtil daoUtil = new DAOUtil();
		daoUtil.deleteObject(partidoIndividualVersus);
	}
	
	/**
	 * Usado para listar todos los partidos por equipos
	 * @param request Objeto HttpServletRequest
	 * @return Objeto Paginate de la lista de partidos por equipos
	 */
	// PartidoEquiposVersus
	@Override
	public Paginate listPartidoEquiposVersus(HttpServletRequest request) {
		DAOUtil daoUtil = new DAOUtil();
		
		return daoUtil.listFromPaginable("PartidoEquiposVersus",request,null,null,null,null);
	}
	
	/**
	 * Usado para listar todos los partidos por equipos
	 * @return Lista de objetos PartidoEquiposVersus
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PartidoEquiposVersus> listPartidoEquiposVersus() {
		DAOUtil daoUtil = new DAOUtil();
		
		return (List<PartidoEquiposVersus>) daoUtil.listQuery("from PartidoEquiposVersus");
	}
	
	/**
	 * Usado para listar todos los partidos individuales
	 * @param request Objeto HttpServletRequest
	 * @return Objeto Paginate de la lista de partidos individuales
	 */
	// PartidoIndividualVersus
//	@SuppressWarnings("unchecked")
	@Override
	public Paginate listPartidoIndividualVersus(HttpServletRequest request) {
		DAOUtil daoUtil = new DAOUtil();
		
		return daoUtil.listFromPaginable("PartidoIndividualVersus",request,null,null,null,null);
	}
	
	/**
	 * Usado para listar todos los partidos individuales
	 * @return Lista de objetos PartidoIndividualVersus
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PartidoIndividualVersus> listPartidoIndividualVersus() {
		DAOUtil daoUtil = new DAOUtil();
		
		return (List<PartidoIndividualVersus>) daoUtil.listQuery("from PartidoIndividualVersus");
	}
	
	/**
	 * Usado para listar partidos individuales según consulta a la base de datos
	 * @param request Objeto HttpServletRequest
	 * @param aliasTable Cadena de caracteres con el alias de la tabla de partidos individuales
	 * @param where Cadena de caracteres con la parte principal de la sentencia
	 * @param tablasAux Cadena de caracteres con los nombres de las tablas auxiliares para la consulta 
	 * @return Objeto Paginate de la lista de partidos individuales
	 * 
	 */
	/////////////////////////
	// List query partido individual vs
	@Override
	public Paginate listPartidoIndividualVersusQuery(HttpServletRequest request,
														String aliasTable,String where,String tablasAux, 
														Integer ObjsPerPage) {			
		DAOUtil daoUtil = new DAOUtil();
		
		return daoUtil.listFromPaginableQuery("PartidoIndividualVersus",request,aliasTable,where,tablasAux,ObjsPerPage);
	}
	
	/**
	 * Usado para listar partidos individuales según consulta a la base de datos
	 * @param aliasTable Cadena de caracteres con el alias de la tabla de partidos individuales
	 * @param where Cadena de caracteres con la parte principal de la sentencia
	 * @param tablasAux Cadena de caracteres con los nombres de las tablas auxiliares para la consulta 
	 * @return Lista de objetos PartidoIndividualVersus
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PartidoIndividualVersus> listPartidoIndividualVersusQuery(String aliasTable,
														String where,String tablasAux) {			
		DAOUtil daoUtil = new DAOUtil();
		
		return (List<PartidoIndividualVersus>) daoUtil.listQuery("from PartidoIndividualVersus as "+aliasTable+tablasAux+where);
	}
	
	/**
	 * Usado para listar partidos individuales de un torneo
	 * @param request Objeto HttpServletRequest
	 * @param torneo Torneo sobre el que se realiza la consulta
	 * @param tam  Número de usuarios participantes del torneo
	 * @return Objeto Paginate de la lista de partidos individuales
	 * 
	 */
	@Override
	public Paginate listPartidoIndividualVersusByTorneo(HttpServletRequest request, Torneo torneo, Integer tam){	
		String aliasTable = "piv ";
		String tablasAux = "";
		String where = "where id.parentTorneo="+torneo.getId()+" order by jornada,idlocal";
//		Integer ObjsPerPage = 0;
//		
//		Set<UserTorneo> ut = torneo.getUserTorneos();
//		
//		Iterator<UserTorneo> it = ut.iterator();
//		
//		while(it.hasNext()){
//			if(it.next().isParticipante()){
//				ObjsPerPage++;
//			}
//		}
//		
//		System.out.println("-------- ObjsPerPage ---- "+ObjsPerPage);
//		ObjsPerPage = ObjsPerPage / 2;
//		System.out.println("-------- ObjsPerPage después de dividir por 2 ---- "+ObjsPerPage);
		
		
		return listPartidoIndividualVersusQuery(request,aliasTable,where,tablasAux,tam/2);
	}
	
	/**
	 * Usado para listar partidos individuales de un torneo
	 * @param torneo Torneo sobre el que se realiza la consulta
	 * @return Lista de objetos PartidoIndividualVersus
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PartidoIndividualVersus> listPartidoIndividualVersusByTorneo(Torneo torneo){	
		String where = "where id.parentTorneo="+torneo.getId()+" order by jornada,idlocal";
		
//		Set<UserTorneo> ut = torneo.getUserTorneos();
//		
//		Iterator<UserTorneo> it = ut.iterator();
//		
//		while(it.hasNext()){
//			if(it.next().isParticipante()){
//				ObjsPerPage++;
//			}
//		}
//		ObjsPerPage = ObjsPerPage / 2;
		
		DAOUtil daoUtil = new DAOUtil();
		
		return (List<PartidoIndividualVersus>) daoUtil.listQuery("from PartidoIndividualVersus "+where);
	}
	
	/**
	 * Usado para listar partidos por equipos según consulta a la base de datos
	 * @param request Objeto HttpServletRequest
	 * @param aliasTable Cadena de caracteres con el alias de la tabla de partidos por equipos
	 * @param where Cadena de caracteres con la parte principal de la sentencia
	 * @param tablasAux Cadena de caracteres con los nombres de las tablas auxiliares para la consulta 
	 * @return Objeto Paginate de la lista de partidos por equipos
	 * 
	 */
	// List query partido equipos vs	
	@Override
	public Paginate listPartidoEquiposVersusQuery(HttpServletRequest request,
													String aliasTable,String where,String tablasAux,
													Integer ObjsPerPage) {			
		DAOUtil daoUtil = new DAOUtil();
		
		return daoUtil.listFromPaginableQuery("PartidoEquiposVersus",request,aliasTable,where,tablasAux,ObjsPerPage);
	}
	
	/**
	 * Usado para listar partidos por equipos según consulta a la base de datos
	 * @param aliasTable Cadena de caracteres con el alias de la tabla de partidos por equipos
	 * @param where Cadena de caracteres con la parte principal de la sentencia
	 * @param tablasAux Cadena de caracteres con los nombres de las tablas auxiliares para la consulta 
	 * @return Lista de objetos PartidoIndividualVersus
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PartidoEquiposVersus> listPartidoEquiposVersusQuery(String aliasTable,String where,String tablasAux) {			
		DAOUtil daoUtil = new DAOUtil();
		
		return (List<PartidoEquiposVersus>) daoUtil.listQuery("from PartidoEquiposVersus as "+aliasTable+tablasAux+where);
	}
	
	/**
	 * Usado para listar partidos por equipos de un torneo
	 * @param request Objeto HttpServletRequest
	 * @param torneo Torneo sobre el que se realiza la consulta
	 * @param tam  Número de equipos participantes del torneo
	 * @return Objeto Paginate de la lista de partidos por equipos
	 * 
	 */
	@Override
	public Paginate listPartidoEquiposVersusByTorneo(HttpServletRequest request, Torneo torneo, Integer tam){	
		String aliasTable = "pev ";
		String tablasAux = "";
		String where = "where id.parentTorneo="+torneo.getId()+" order by jornada,idLocal";
//		Integer ObjsPerPage = 0;
//		
//		ObjsPerPage = torneo.getEquipoTorneos().size();
//		ObjsPerPage = ObjsPerPage/2;
		
		return listPartidoEquiposVersusQuery(request,aliasTable,where,tablasAux,tam/2);
	}
	
	/**
	 * Usado para listar partidos por equipos de un torneo
	 * @param torneo Torneo sobre el que se realiza la consulta
	 * @return Lista de objetos PartidoEquiposVersus
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PartidoEquiposVersus> listPartidoEquiposVersusByTorneo(Torneo torneo){	
		String where = "where id.parentTorneo="+torneo.getId()+" order by jornada,idLocal";
		
//		ObjsPerPage = torneo.getEquipos().size();
//		ObjsPerPage = ObjsPerPage/2;
		
		DAOUtil daoUtil = new DAOUtil();
		
		return (List<PartidoEquiposVersus>) daoUtil.listQuery("from PartidoEquiposVersus "+where);
	}
	
	/**
	 * Usado para calcular el numero de partidos jugados
	 * por un usuario en un torneo
	 * @param tor Torneo sobre el que se realiza la consulta
	 * @param us Usuario sobre el que se realiza la consulta
	 * @return Número de partidos jugados en el torneo por el usuario
	 * 
	 */
	// Contar numero de partidos jugados
	@Override
	public Integer numPartidosJugadosUsuario(Torneo tor, User us){	
		DAOUtil daoUtil = new DAOUtil();
		
		return daoUtil.CountListQuery("from PartidoIndividualVersus" +
						" where parentTorneo="+tor.getId()+
						" and (parentLocal="+us.getId()+" or parentVisitante="+us.getId()+")" +
						" and confirmado=true");
	}
	
	/**
	 * Usado para calcular el numero de partidos no jugados
	 * por un usuario en un torneo
	 * @param tor Torneo sobre el que se realiza la consulta
	 * @param us Usuario sobre el que se realiza la consulta
	 * @return Número de partidos no jugados en el torneo por el usuario
	 * 
	 */
	// Contar numero de partidos nojugados
	@Override
	public Integer numPartidosNoJugadosUsuario(Torneo tor, User us){
		DAOUtil daoUtil = new DAOUtil();
		
		return daoUtil.CountListQuery("from PartidoIndividualVersus" +
						" where parentTorneo="+tor.getId()+
						" and (parentLocal="+us.getId()+" or parentVisitante="+us.getId()+")" +
						" and confirmado=false");
	}
	
	/**
	 * Usado para los partidos de un usuario en un torneo
	 * @param tor Torneo sobre el que se realiza la consulta
	 * @param us Usuario sobre el que se realiza la consulta
	 * @return Lista de objetos PartidoIndividualVersus
	 * 
	 */
	// Listar partidos de un jugador en un torneo
	@SuppressWarnings("unchecked")
	@Override
	public List<PartidoIndividualVersus> PartidosUsuarioTorneo(Torneo tor, User us){
		DAOUtil daoUtil = new DAOUtil();
		
		return (List<PartidoIndividualVersus>) daoUtil.listQuery("from PartidoIndividualVersus" +
						" where parentTorneo="+tor.getId()+
						" and (parentLocal="+us.getId()+" or parentVisitante="+us.getId()+")");
	}
	
	/**
	 * Usado para listar partidos jugables 
	 * por un usuario en un torneo
	 * @param tor Torneo sobre el que se realiza la consulta
	 * @param us Usuario sobre el que se realiza la consulta
	 * @return Lista de objetos PartidoIndividualVersus
	 * 
	 */
	// Listar partidos a jugar de un jugador en un torneo
		@SuppressWarnings("unchecked")
		@Override
		public List<PartidoIndividualVersus> PartidosUsuarioTorneoJugables(Torneo tor, User us){
			DAOUtil daoUtil = new DAOUtil();
			
			String query = "from PartidoIndividualVersus where parentTorneo="+tor.getId()+
					" and (parentLocal="+us.getId()+" or parentVisitante="+us.getId()+")";
			
			if(tor.getJornadasJugables() > 0){
				query += " and ((jornada between "+tor.getJornadaActual()+
						" and "+(tor.getJornadaActual()-1 +tor.getJornadasJugables())+") or"+
						" (jornada < "+tor.getJornadaActual()+" and confirmado=false))";
			}
			
			return (List<PartidoIndividualVersus>) daoUtil.listQuery(query);
		}
		
		/**
		 * Usado para listar partidos en los que 
		 * los dos usuarios son invitados
		 * @param tor Torneo sobre el que se realiza la consulta
		 * @return Lista de objetos PartidoIndividualVersus
		 * 
		 */
		// Listar partidos en los que los dos usuarios son invitados
		@SuppressWarnings("unchecked")
		@Override
		public List<PartidoIndividualVersus> PartidosUsuarioTorneoInvitados(Torneo tor){
			DAOUtil daoUtil = new DAOUtil();
			
			return (List<PartidoIndividualVersus>) daoUtil.listQuery("from PartidoIndividualVersus" +
							" where parentTorneo="+tor.getId()+
							" and parentLocal.invitado=true and" +
							" parentVisitante.invitado=true");
		}
		
		/**
		 * Usado para listar partidos jugables en los que 
		 * los dos usuarios son invitados
		 * @param tor Torneo sobre el que se realiza la consulta
		 * @return Lista de objetos PartidoIndividualVersus
		 * 
		 */
		// Listar partidos a jugar en los que los dos usuarios son invitados
			@SuppressWarnings("unchecked")
			@Override
			public List<PartidoIndividualVersus> PartidosUsuarioTorneoInvitadosJugables(Torneo tor){
				DAOUtil daoUtil = new DAOUtil();
				
				String query = "from PartidoIndividualVersus where parentTorneo="+tor.getId()+
						" and parentLocal.invitado=true and" +
						" parentVisitante.invitado=true";
				
				if(tor.getJornadasJugables() > 0){
					query += " and ((jornada between "+tor.getJornadaActual()+
							" and "+(tor.getJornadaActual()-1 +tor.getJornadasJugables())+") or"+
							" (jornada < "+tor.getJornadaActual()+" and confirmado=false))";
				}
				return (List<PartidoIndividualVersus>) daoUtil.listQuery(query);
						
			}

			/**
			 * Usado para listar partidos en los que 
			 * el primer usuario sea local y
			 * el segundo visitante
			 * @param local Relación del objeto User que ponemos como
			 *  local con el torneo
			 * @param visitante Relación del objeto User que ponemos como
			 *  visitante con el torneo
			 * @return Lista de objetos PartidoIndividualVersus 
			 */
		@SuppressWarnings("unchecked")
		@Override
		public List<PartidoIndividualVersus> PartidosUsuariosTorneo(UserTorneo local, UserTorneo visitante){
			DAOUtil daoUtil = new DAOUtil();
			
			return (List<PartidoIndividualVersus>) daoUtil.listQuery("from PartidoIndividualVersus" +
							" where parentLocal="+local.getId().getParentUser().getId()+
							" and parentVisitante="+visitante.getId().getParentUser().getId()+
							" and confirmado=true");
		}
		
		
		////////////////////////////////////////////////////////////////////////////
		
		/**
		 * Usado para listar partidos de un equipo en un torneo
		 * @param tor Torneo sobre el que se realiza la consulta
		 * @param eq Equipo sobre el que se realiza la consulta
		 * @return Lista de objetos PartidoEquiposVersus
		 * 
		 */
		// Listar partidos de un equipo en un torneo
		@SuppressWarnings("unchecked")
		@Override
		public List<PartidoEquiposVersus> PartidosEquipoTorneo(Torneo tor, Equipo eq){
			DAOUtil daoUtil = new DAOUtil();
			
			return (List<PartidoEquiposVersus>) daoUtil.listQuery("from PartidoEquiposVersus" +
							" where parentTorneo="+tor.getId()+
							" and (parentLocal="+eq.getId()+" or parentVisitante="+eq.getId()+")");
		}
		
		/**
		 * Usado para listar partidos jugables 
		 * por un equipo en un torneo
		 * @param tor Torneo sobre el que se realiza la consulta
		 * @param eq Equipo sobre el que se realiza la consulta
		 * @return Lista de objetos PartidoEquiposVersus
		 * 
		 */
		// Listar partidos a jugar de un equipo en un torneo
			@SuppressWarnings("unchecked")
			@Override
			public List<PartidoEquiposVersus> PartidosEquipoTorneoJugables(Torneo tor, Equipo eq){
				DAOUtil daoUtil = new DAOUtil();
				
				String query = "from PartidoEquiposVersus where parentTorneo="+tor.getId()+
						" and (parentLocal="+eq.getId()+" or parentVisitante="+eq.getId()+")";
				
				if(tor.getJornadasJugables() > 0){
					query += " and ((jornada between "+tor.getJornadaActual()+
							" and "+(tor.getJornadaActual()-1 +tor.getJornadasJugables())+") or"+
							" (jornada < "+tor.getJornadaActual()+" and confirmado=false))";
				}
				
				return (List<PartidoEquiposVersus>) daoUtil.listQuery(query);
			}
			
			/**
			 * Usado para listar partidos en los que 
			 * los dos equipos son invitados
			 * @param tor Torneo sobre el que se realiza la consulta
			 * @return Lista de objetos PartidoEquiposVersus
			 * 
			 */
			// Listar partidos en los que los dos equipos son invitados
			@SuppressWarnings("unchecked")
			@Override
			public List<PartidoEquiposVersus> PartidosEquipoTorneoInvitados(Torneo tor){
				DAOUtil daoUtil = new DAOUtil();
				
				return (List<PartidoEquiposVersus>) daoUtil.listQuery("from PartidoEquiposVersus" +
								" where parentTorneo="+tor.getId()+
								" and parentLocal.invitado=true and" +
								" parentVisitante.invitado=true");
			}
			
			/**
			 * Usado para listar partidos jugables en los que 
			 * los dos equipos son invitados
			 * @param tor Torneo sobre el que se realiza la consulta
			 * @return Lista de objetos PartidoEquiposVersus
			 * 
			 */
			// Listar partidos a jugar en los que los dos usuarios son invitados
				@SuppressWarnings("unchecked")
				@Override
				public List<PartidoEquiposVersus> PartidosEquipoTorneoInvitadosJugables(Torneo tor){
					DAOUtil daoUtil = new DAOUtil();
					
					String query = "from PartidoEquiposVersus where parentTorneo="+tor.getId()+
							" and parentLocal.invitado=true and" +
							" parentVisitante.invitado=true";
					
					if(tor.getJornadasJugables() > 0){
						query += " and ((jornada between "+tor.getJornadaActual()+
								" and "+(tor.getJornadaActual()-1 +tor.getJornadasJugables())+") or"+
								" (jornada < "+tor.getJornadaActual()+" and confirmado=false))";
					}
					return (List<PartidoEquiposVersus>) daoUtil.listQuery(query);
							
				}

			
				/**
				 * Usado para listar partidos en los que 
				 * el primer equipo sea local y
				 * el segundo visitante
				 * @param local Relación del objeto Equipo que ponemos como
				 *  local con el torneo
				 * @param visitante Relación del objeto Equipo que ponemos como
				 *  visitante con el torneo
				 * @return Lista de objetos PartidoEquiposVersus 
				 */
			@SuppressWarnings("unchecked")
			@Override
			public List<PartidoEquiposVersus> PartidosEquiposTorneo(EquipoTorneo local, EquipoTorneo visitante){
				DAOUtil daoUtil = new DAOUtil();
				
				return (List<PartidoEquiposVersus>) daoUtil.listQuery("from PartidoEquiposVersus" +
								" where parentLocal="+local.getId().getParentEquipo().getId()+
								" and parentVisitante="+visitante.getId().getParentEquipo().getId()+
								" and confirmado=true");
			}
			
			/**
			 * Usado para comprobar si se han jugado
			 * todos los partidos de la jornada
			 * @param tor Torneo sobre el que realizar la consulta
			 * @return Boolean
			 */
			@SuppressWarnings("unchecked")
			@Override
			public Boolean ComprobarPartidosJornada(Torneo tor){
				Boolean valor=false;
				DAOUtil daoUtil = new DAOUtil();
				
				if(tor.getPorEquipos()){
					List<PartidoEquiposVersus> lista =
							(List<PartidoEquiposVersus>) daoUtil.listQuery("from PartidoEquiposVersus" +
							" where parentTorneo="+tor.getId()+" and jornada between "
									+tor.getJornadaActual()+" and "+(tor.getJornadaActual()-1+tor.getJornadasJugables())+
							" and confirmado=false");
					
					valor= lista.isEmpty();
				}
				else{
					List<PartidoIndividualVersus> lista =
							(List<PartidoIndividualVersus>) daoUtil.listQuery("from PartidoIndividualVersus" +
							" where  parentTorneo="+tor.getId()+" and jornada between "
									+tor.getJornadaActual()+" and "+(tor.getJornadaActual()-1+tor.getJornadasJugables())+
							" and confirmado=false");
					
					valor= lista.isEmpty();
				}
				
				return valor;	 
			}
			
			/**
			 * Usado para listar los partidos por equipos
			 * rechazados de un torneo
			 * @param tor Torneo sobre el que realizar la consulta
			 * @return Lista de objetos PartidoEquiposVersus
			 */
			@SuppressWarnings("unchecked")
			@Override
			public List<PartidoEquiposVersus> PartidosRechazadosEquipos(Torneo tor){
				DAOUtil daoUtil = new DAOUtil();
				
				return (List<PartidoEquiposVersus>) daoUtil.listQuery("from PartidoEquiposVersus" +
								" where parentTorneo="+tor.getId()+" and rechazado=true");
			}
			
			/**
			 * Usado para listar los partidos individuales
			 * rechazados de un torneo
			 * @param tor Torneo sobre el que realizar la consulta
			 * @return Lista de objetos PartidoIndividualVersus
			 */
			@SuppressWarnings("unchecked")
			@Override
			public List<PartidoIndividualVersus> PartidosRechazadosIndividual(Torneo tor){
				DAOUtil daoUtil = new DAOUtil();
				
				return (List<PartidoIndividualVersus>) daoUtil.listQuery("from PartidoIndividualVersus" +
								" where parentTorneo="+tor.getId()+" and rechazado=true");
			}
			
			/**
			 * Usado para listar los partidos individuales
			 * no confirmados de un torneo
			 * @param tor Torneo sobre el que realizar la consulta
			 * @return Lista de objetos PartidoIndividualVersus
			 */
			@SuppressWarnings("unchecked")
			@Override
			public List<PartidoIndividualVersus> PartidosNoConfirmadosIndividual(Torneo tor){
				DAOUtil daoUtil = new DAOUtil();
				
				return (List<PartidoIndividualVersus>) daoUtil.listQuery("from PartidoIndividualVersus" +
								" where parentTorneo="+tor.getId()+" and confirmado=false");
			}
			
			/**
			 * Usado para listar los partidos por equipos
			 * no confirmados de un torneo
			 * @param tor Torneo sobre el que realizar la consulta
			 * @return Lista de objetos PartidoIndividualVersus
			 */
			@SuppressWarnings("unchecked")
			@Override
			public List<PartidoEquiposVersus> PartidosNoConfirmadosEquipos(Torneo tor){
				DAOUtil daoUtil = new DAOUtil();
				
				return (List<PartidoEquiposVersus>) daoUtil.listQuery("from PartidoEquiposVersus" +
								" where parentTorneo="+tor.getId()+" and confirmado=false");
			}
			
			/**
			 * Usado para listar los partidos individuales
			 * de una jornada de un torneo
			 * @param tor Torneo sobre el que realizar la consulta
			 * @param jornada Número de jornada sobre la que realizar la consulta
			 * @return Lista de objetos PartidoIndividualVersus
			 */
			@SuppressWarnings("unchecked")
			@Override
			public List<PartidoIndividualVersus> PartidosByJornadaIndividual(Torneo tor,Integer jornada){
				DAOUtil daoUtil = new DAOUtil();
				
				return (List<PartidoIndividualVersus>) daoUtil.listQuery("from PartidoIndividualVersus" +
								" where parentTorneo="+tor.getId()+" and jornada="+jornada);
			}
			
			/**
			 * Usado para listar los partidos por equipos
			 * de una jornada de un torneo
			 * @param tor Torneo sobre el que realizar la consulta
			 * @param jornada Número de jornada sobre la que realizar la consulta
			 * @return Lista de objetos PartidoEquiposVersus
			 */
			@SuppressWarnings("unchecked")
			@Override
			public List<PartidoEquiposVersus> PartidosByJornadaEquipos(Torneo tor,Integer jornada){
				DAOUtil daoUtil = new DAOUtil();
				
				return (List<PartidoEquiposVersus>) daoUtil.listQuery("from PartidoEquiposVersus" +
								" where parentTorneo="+tor.getId()+" and jornada="+jornada);
			}			
			
}
