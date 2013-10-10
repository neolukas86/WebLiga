package com.proyecto.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.proyecto.dao.PartidoDAO;
import com.proyecto.dao.PartidoDAOImpl;
import com.proyecto.dao.TorneoDAO;
import com.proyecto.dao.TorneoDAOImpl;
import com.proyecto.dao.UserTorneoDAO;
import com.proyecto.dao.UserTorneoDAOImpl;
import com.proyecto.dominio.Actividad;
import com.proyecto.dominio.PartidoEquiposVersus;
import com.proyecto.dominio.PartidoIndividualVersus;
import com.proyecto.dominio.Torneo;
import com.proyecto.dominio.User;
import com.proyecto.dominio.UserTorneo;
import com.proyecto.dominio.UserTorneoPK;
import com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares;
import com.proyecto.util.hibernate.HibernateUtil;
import com.proyecto.util.listas.AverageGlobalUserTorneo;
import com.proyecto.util.listas.CocienteUserTorneoComparator;
import com.proyecto.util.listas.PosicionUserTorneoComparator;
import com.proyecto.util.listas.ACB.ACBUserTorneoDosComparator;
import com.proyecto.util.listas.ACB.ACBUserTorneoDosFinalComparator;
import com.proyecto.util.listas.ACB.ACBUserTorneoMultipleFinalComparator;
import com.proyecto.util.listas.EPL.EPLUserTorneoDosComparator;
import com.proyecto.util.listas.EPL.EPLUserTorneoDosFinalComparator;
import com.proyecto.util.listas.EPL.EPLUserTorneoSubComparator;
import com.proyecto.util.listas.LFP.LFPUserTorneoDosComparator;
import com.proyecto.util.listas.LFP.LFPUserTorneoDosFinalComparator;
import com.proyecto.util.listas.LFP.LFPUserTorneoSubComparator;

/**
* Esta clase implementa el Action asociado a los
* partidos individuales.
* @author Lucas Sánchez López
* @version 3.0
*/

public class PartidoIndividualAction extends ActionSupport implements ModelDriven<PartidoIndividualVersus>,SessionAware {
	private static final long serialVersionUID = 1L;
	private PartidoIndividualVersus partido = new PartidoIndividualVersus();
	private PartidoDAO partidoDAO;
	private UserTorneoDAO userTorneoDAO;
	private TorneoDAO torneoDAO;
	private int idtorneo;
	private Map sesion;
	
	//--------------------
	
	public int getIdtorneo() {
		return idtorneo;
	}

	public void setIdtorneo(int idtorneo) {
		this.idtorneo = idtorneo;
	}

	SessionFactory sessionFactory = null;
	
	@SessionTarget
	Session session = null;

	@Override
	public PartidoIndividualVersus getModel() {
		// TODO Auto-generated method stub
		return partido;
	}
	
	public PartidoIndividualVersus getPartido() {
		return partido;
	}

	public void setPartido(PartidoIndividualVersus partido) {
		this.partido = partido;
	}

	/**
	 * Introduce el resultado de un partido individual
	 * @return SUCCESS en caso de éxito, INPUT en caso de no completarse, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String IntroducirResultadoIndividual(){
		boolean error=false;
		boolean exito=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				System.out.println("---- Dentro de IntroducirResultado");
				PartidoIndividualVersus par = (PartidoIndividualVersus)session.load(PartidoIndividualVersus.class,partido.getId());
				
				par.setPuntosLocal(partido.getPuntosLocal());
				par.setPuntosVisitante(partido.getPuntosVisitante());
				
				if(par.getPuntosLocal() > par.getPuntosVisitante()){
					par.setParentGanador(par.getParentLocal());
					par.setParentPerdedor(par.getParentVisitante());
				}
				else if(par.getPuntosLocal() < par.getPuntosVisitante()){
						par.setParentGanador(par.getParentVisitante());
						par.setParentPerdedor(par.getParentLocal());
					}
					else{
						par.setParentGanador(null);
						par.setParentPerdedor(null);
					}
				
				sesion = getSession();
				
				User us = (User)session.load(User.class,(Integer)sesion.get("id"));
				par.setParentIntroductorResultado(us);
				
//				System.out.println("-------- Introducir Resultado,  Local -> "+par.getParentLocal().getAlias()+
//						"      Visitante -> "+par.getParentVisitante().getAlias()+",  "+par.getPuntosLocal()+" - "+par.getPuntosVisitante());
				
				partidoDAO = new PartidoDAOImpl();
				
				// Actualizamos el partido
				partido = partidoDAO.mergePartidoIndividualVersus(par);
				
				exito= true;
				
			}
			catch(Exception e){
				error = true;
				e.printStackTrace();
			}
		}
		catch(HibernateException he){
			error = true;
			he.printStackTrace();
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(final HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito){
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}	
	}
	

	/**
	 * Rechaza el resultado de un partido individual
	 * @return SUCCESS en caso de éxito, INPUT en caso de no completarse, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String RechazarResultado(){
		boolean error=false;
		boolean exito=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
//				System.out.println("La id del partido es --> "+partido.getId());
				PartidoIndividualVersus par = (PartidoIndividualVersus)session.load(PartidoIndividualVersus.class,partido.getId());

				par.setRechazado(true);
					
				partidoDAO = new PartidoDAOImpl();
					
				// Actualizamos el partido
				partidoDAO.mergePartidoIndividualVersus(par);
				
				idtorneo = par.getParentTorneo().getId();
				
				exito= true;				
			}
			catch(Exception e){
				error = true;
				e.printStackTrace();
			}
		}
		catch(HibernateException he){
			error = true;
			he.printStackTrace();
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(final HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito){
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}	
	}
	
	/**
	 * Confirma el resultado de un partido individual
	 * @return SUCCESS en caso de éxito, INPUT en caso de no completarse, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String ConfirmarResultado(){
		boolean error=false;
		boolean exito=false;
		boolean finalizado=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
//				System.out.println("La id del partido es --> "+partido.getId());
				PartidoIndividualVersus par = (PartidoIndividualVersus)session.load(PartidoIndividualVersus.class,partido.getId());

				par.setConfirmado(true);
				par.setRechazado(false); // Por si era un partido rechazado
					
				partidoDAO = new PartidoDAOImpl();
				
					
				// Actualizamos el partido
				partidoDAO.mergePartidoIndividualVersus(par);	
				
				System.out.println("Partido ya confirmado pasando a actualizarse en la Clasificación...");
				if(par.getParentGanador() != null){
					System.out.println("Ganador -> "+par.getParentGanador().getAlias()+
							",  Perdedor -> "+par.getParentPerdedor().getAlias());
				}
				System.out.println(" Local "+par.getPuntosLocal()+" - "+par.getPuntosVisitante()+" Visitante");
				
				Torneo tor = par.getParentTorneo();
				idtorneo = tor.getId();
				
				if(tor.getLiga()){
					// Actualizamos la clasificacion
					ActualizarClasificacion(par);
					
					List<PartidoIndividualVersus> lista = partidoDAO.PartidosNoConfirmadosIndividual(tor);
					
					if(lista.isEmpty()){
						tor.setEstado(FuncionesAuxiliares.FINALIZADO);
						// Damos el torneo por finalizado
						
						torneoDAO = new TorneoDAOImpl();
						torneoDAO.mergeTorneo(tor);
						
						finalizado=true;
					}
				}
				else{ // Ponemos el flag de eliminado al perdedor
					UserTorneo usTo = (UserTorneo)session.load(UserTorneo.class,
							new UserTorneoPK(par.getParentPerdedor(),tor));
					
					UserTorneo usTo2 = (UserTorneo)session.load(UserTorneo.class,
							new UserTorneoPK(par.getParentGanador(),tor));
					
					if(usTo.equals(par.getParentLocal())){
						usTo.setTantosFavor(usTo.getTantosFavor() + par.getPuntosLocal());
						usTo.setTantosContra(usTo.getTantosContra() + par.getPuntosVisitante());
						
						usTo2.setTantosContra(usTo2.getTantosContra() + par.getPuntosLocal());
						usTo2.setTantosFavor(usTo2.getTantosFavor() + par.getPuntosVisitante());
					}
					else{
						usTo2.setTantosFavor(usTo.getTantosFavor() + par.getPuntosLocal());
						usTo2.setTantosContra(usTo.getTantosContra() + par.getPuntosVisitante());
						
						usTo.setTantosContra(usTo2.getTantosContra() + par.getPuntosLocal());
						usTo.setTantosFavor(usTo2.getTantosFavor() + par.getPuntosVisitante());
					}
					
					
					usTo.setEliminado(true);
					
					userTorneoDAO = new UserTorneoDAOImpl();
					userTorneoDAO.mergeUserTorneo(usTo);
					userTorneoDAO.mergeUserTorneo(usTo2);
					
					List<UserTorneo> usToList = userTorneoDAO.UserTorneoNoEliminados(tor);
					
					if(usToList.size() == 1){
						tor.setEstado(FuncionesAuxiliares.FINALIZADO);
						// Damos el torneo por finalizado
						
						torneoDAO = new TorneoDAOImpl();
						torneoDAO.mergeTorneo(tor);
						
						finalizado=true;
					}
				}
				
				exito= true;
				
			}
			catch(Exception e){
				error = true;
				e.printStackTrace();
			}
		}
		catch(HibernateException he){
			error = true;
			he.printStackTrace();
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(final HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito){
					if(finalizado){
						return "finalizado";
					}
					else{
						return SUCCESS;	
					}					
				}
				else{
					return INPUT;
				}
			}
		}	
	}
	


	/**
	 * Actualiza la clasificación tras la confirmación de un partido individual
	 * @param par Partido individual confirmado
	 */
	public void ActualizarClasificacion(PartidoIndividualVersus par){
		// -------------- Intentando actualizar UserTorneo para la tabla de clasificación ---------
		User usGanador = par.getParentGanador();
		User usPerdedor = par.getParentPerdedor();
		UserTorneo usTo = null;
		UserTorneo usTo2 = null;				
		Torneo tor = par.getParentTorneo();
		idtorneo = tor.getId();
//		Set<UserTorneo> userTorneos = tor.getUserTorneos();
		Actividad act = tor.getParentActividad();
		Integer ptosVictoria = act.getPtosVictoria();
		
		userTorneoDAO = new UserTorneoDAOImpl();
		
		if(usGanador != null){ // Si no hay empate deberia haber un ganador
			
			usTo = (UserTorneo)session.load(UserTorneo.class,
					new UserTorneoPK(usGanador,tor));
			
			System.out.println("Ganador: "+usTo.getId().getParentUser().getAlias()+", posicion "+usTo.getPosicion());
			
			List<UserTorneo> userTorneos = new ArrayList<UserTorneo>(tor.getUserTorneos());
			userTorneos.remove(usTo); // Sacamos del grupo a usTo
			
			Iterator<UserTorneo> it = userTorneos.iterator();
			while(it.hasNext()){
				UserTorneo userTo = it.next();
				
				if(!userTo.isParticipante() || 
						FuncionesAuxiliares.ComprobarPermisos(userTo.getRango(), FuncionesAuxiliares.NORMALUSER) == ERROR){
					userTorneos.remove(userTo);
				}
			}
			
			usTo.setPartidosGanados(usTo.getPartidosGanados()+1);

			if(ptosVictoria != null){
				usTo.setPuntos(usTo.getPuntos() + ptosVictoria);
			}
			
			if(usGanador.equals(par.getParentLocal())){
				usTo.setTantosFavor(usTo.getTantosFavor() + par.getPuntosLocal());
				usTo.setTantosContra(usTo.getTantosContra() + par.getPuntosVisitante());
			}
			else{ // Si no es local, será visitante, ATENCIÓN, CUIDADO NO FALLE AQUI!!!
				usTo.setTantosFavor(usTo.getTantosFavor() + par.getPuntosVisitante());
				usTo.setTantosContra(usTo.getTantosContra() + par.getPuntosLocal());
			}
			
			usTo = ColocarEnClasificacion(usTo, userTorneos,tor,act,true);
		}
		
		
		if(usPerdedor != null){ // Si no hay empate deberia haber un perdedor
			usTo2 = (UserTorneo)session.load(UserTorneo.class,
					new UserTorneoPK(usPerdedor,tor));
			
			System.out.println("Perdedor: "+usTo2.getId().getParentUser().getAlias()+", posicion "+usTo2.getPosicion());
			
			List<UserTorneo> userTorneos = new ArrayList<UserTorneo>(tor.getUserTorneos());
			userTorneos.remove(usTo2); // Sacamos del grupo a usTo2
			
			Iterator<UserTorneo> it = userTorneos.iterator();
			while(it.hasNext()){
				UserTorneo userTo = it.next();
				
				if(!userTo.isParticipante() || 
						FuncionesAuxiliares.ComprobarPermisos(userTo.getRango(), FuncionesAuxiliares.NORMALUSER) == ERROR){
					userTorneos.remove(userTo);
				}
			}
			
			usTo2.setPartidosPerdidos(usTo2.getPartidosPerdidos()+1);
			
			if(usPerdedor.equals(par.getParentLocal())){
				usTo2.setTantosFavor(usTo2.getTantosFavor() + par.getPuntosLocal());
				usTo2.setTantosContra(usTo2.getTantosContra() + par.getPuntosVisitante());
			}
			else{ // Si no es local, será visitante, ATENCIÓN, CUIDADO NO FALLE AQUI!!!
				usTo2.setTantosFavor(usTo2.getTantosFavor() + par.getPuntosVisitante());
				usTo2.setTantosContra(usTo2.getTantosContra() + par.getPuntosLocal());
			}
			
			usTo2 = ColocarEnClasificacion(usTo2, userTorneos,tor,act,false);
					
		}
		
		if(usGanador == null && usPerdedor == null){ // EMPATE, Si uno fuera null el otro tb debería pero por asegurar
			usTo = (UserTorneo)session.load(UserTorneo.class,
					new UserTorneoPK(par.getParentLocal(),(Torneo)session.load(Torneo.class,idtorneo)));
			
			List<UserTorneo> userTorneos = userTorneoDAO.UserTorneoParticipante(tor);
			userTorneos.remove(usTo); // Sacamos del grupo a usTo
			
			usTo2 = (UserTorneo)session.load(UserTorneo.class,
					new UserTorneoPK(par.getParentVisitante(),(Torneo)session.load(Torneo.class,idtorneo)));
			UserTorneo visitante = usTo2;
				
			usTo.setPartidosEmpatados(usTo.getPartidosEmpatados()+1);
			usTo2.setPartidosEmpatados(usTo2.getPartidosEmpatados()+1);
			
			Integer ptos = par.getPuntosLocal(); // Al ser empate tendrán los mismos puntos local y visitante en contra y a favor.
			
			usTo.setTantosFavor(usTo.getTantosFavor()+ ptos); 
			usTo.setTantosContra(usTo.getTantosContra()+ ptos);

			usTo2.setTantosFavor(usTo2.getTantosFavor()+ ptos); 
			usTo2.setTantosContra(usTo2.getTantosContra()+ ptos);

			if(ptosVictoria != null){
				usTo.setPuntos(usTo.getPuntos() + 1); // Siempre será un punto por empatar
				usTo2.setPuntos(usTo2.getPuntos() + 1);
			}

			usTo = ColocarEnClasificacion(usTo, userTorneos,tor,act,true);
						
			userTorneos = userTorneoDAO.UserTorneoParticipante(tor);
			userTorneos.remove(visitante); // Sacamos del grupo a usTo2 antes de ser modificado
			
			usTo2 = ColocarEnClasificacion(usTo2, userTorneos,tor,act,true);						
		}
	}
	
	/**
	 * Coloca al usuario en la tabla clasificatoria
	 * @param usTo Objeto UserTorneo del usuario a colocar en la clasificación
	 * @param setUT Usuarios con los que comparara
	 * @param torn Torneo
	 * @param act Actividad del torneo
	 * @param puntua Indica si el usuario ha puntuado en el partido, si no ha perdido
	 * @return Objeto UserTorneo con la posición del usuario actualizada
	 */
	public UserTorneo ColocarEnClasificacion(UserTorneo usTo, List<UserTorneo> setUT, Torneo torn, Actividad act, boolean puntua){
//		int pos = usTo.getPosicion();

		List<UserTorneo> listaUT = new ArrayList<UserTorneo>();
		Iterator<UserTorneo> it = setUT.iterator();
		
		System.out.println("---- Dentro de ColocarEnClasificacion ----");
//		int participantes = 0;
		Integer ptosVictoria = act.getPtosVictoria();
		
		while(it.hasNext()){
			UserTorneo ut = it.next();
				
			if(ptosVictoria == null && 
					(puntua &&
						ut.getPartidosGanados() <= usTo.getPartidosGanados()  && 
						ut.getPosicion() < usTo.getPosicion() ||
					!puntua &&
						ut.getPartidosGanados() == usTo.getPartidosGanados()) ||
				ptosVictoria != null && 
					(puntua && 
						ut.getPuntos() <= usTo.getPuntos() &&
						ut.getPosicion() < usTo.getPosicion()  ||
					!puntua &&							
						ut.getPuntos() == usTo.getPuntos())){
					
				listaUT.add(ut);
				System.out.println("---- listaUT.add("+ut.getId().getParentUser().getAlias()+") ----");
			}
		}
		
		userTorneoDAO = new UserTorneoDAOImpl();
		
		
		if(!listaUT.isEmpty()){
			if(!puntua){ // Hay que comprobar que hago el sort en el sentido correcto
				Collections.sort(listaUT, new PosicionUserTorneoComparator());	
			}
			else{
				Comparator<UserTorneo> compa = Collections.reverseOrder(new PosicionUserTorneoComparator());
				
				Collections.sort(listaUT, compa);
			}
				
			Iterator<UserTorneo> itList = listaUT.iterator();
			List<UserTorneo> utIguales = new ArrayList<UserTorneo>(); 
						
			
			if(puntua){
				while(itList.hasNext()){
					UserTorneo ut = itList.next();
					
//					System.out.println("----> Sort... jugador: "+ut.getId().getParentUser().getAlias());
					
					if(ptosVictoria != null && usTo.getPuntos() > ut.getPuntos() ||
							ptosVictoria == null && usTo.getPartidosGanados() > ut.getPartidosGanados()){
						usTo.setPosicion(ut.getPosicion());
						ut.setPosicion(ut.getPosicion()+1);					
							
						System.out.println("---- Puntua: "+usTo.getId().getParentUser().getAlias()+" sube una posición ----");					
							
						userTorneoDAO.mergeUserTorneo(ut);
					}							
					else if(ptosVictoria != null && usTo.getPuntos() == ut.getPuntos() ||
						ptosVictoria == null && usTo.getPartidosGanados() == ut.getPartidosGanados()){
						// Meter en otra lista para luego volver a compararlos si hay empate a puntos
						utIguales.add(ut);
								
//						System.out.println("---- Metemos en otra lista para comparar: " 
//							+ut.getId().getParentUser().getAlias()+" ----");
					}
				}
			}
			else{
				while(itList.hasNext()){
					UserTorneo ut = itList.next();
					
					if(ut.getPartidosEmpatados() + ut.getPartidosGanados() + ut.getPartidosPerdidos() > 0){
						utIguales.add(ut);
					}
					else if(ut.getPosicion() > usTo.getPosicion()){
						usTo.setPosicion(ut.getPosicion());
						ut.setPosicion(ut.getPosicion()-1);
						
						System.out.println("---- No Puntua: "+usTo.getId().getParentUser().getAlias()+" baja una posición y sube una "
								+ut.getId().getParentUser().getAlias()+"----");
						
						
						userTorneoDAO.mergeUserTorneo(ut);						
					}
				}
			}
			
			
			
			if(!utIguales.isEmpty()){
				partidoDAO = new PartidoDAOImpl();
				String reglas = torn.getParentRegla().getNombre();
				Comparator<UserTorneo> compara=null;
				
				if(utIguales.size() == 1){ // Empate entre dos
					UserTorneo ut = utIguales.get(0);
					boolean enfrentadosTodos = false;
					int retorno=0;
					// Aqui deberiamos acceder a una propiedad del torneo que nos diga que reglas de comparación seguir

					if((partidoDAO.PartidosUsuariosTorneo(usTo, ut).size() +
							partidoDAO.PartidosUsuariosTorneo(ut, usTo).size()) < torn.getRondas()){ 
						// Si no se han enfrentado todas las veces	
						if(reglas.equals("ACB")){
							compara = new ACBUserTorneoDosComparator();	
						}
						else if(reglas.equals("LFP")){
							compara = new LFPUserTorneoDosComparator();	
						}
						else if(reglas.equals("EPL")){
							compara = new EPLUserTorneoDosComparator();
						}
																		
						System.out.println("---- Recomparando... No es la última vuelta ----");
						
						retorno = compara.compare(ut, usTo);
					}
					else{ // Si se han enfrentado todas las veces
						enfrentadosTodos = true;
						
						if(reglas.equals("ACB")){
							compara = new ACBUserTorneoDosFinalComparator();
						}
						else if(reglas.equals("LFP")){
							compara = new LFPUserTorneoDosFinalComparator();	
						}
						else if(reglas.equals("EPL")){
							compara = new EPLUserTorneoDosFinalComparator();
						}
												
						System.out.println("---- Recomparando... Es la última vuelta ----");
					}
					
					
					if(retorno == 0){ // Da 0 el compare de enfrentados == false ó enfrentados == true
						
						usTo = SacarSubDatosDosUserTorneo(usTo,utIguales, ptosVictoria);
						
						ut = utIguales.get(0); 
						
						if(enfrentadosTodos){
							retorno = compara.compare(ut, usTo);	
						}
						else if(ptosVictoria == null){ // Serán los unicos que utilizarán cociente
							retorno = AverageGlobalUserTorneo.Cociente(ut.getTantosFavor(), ut.getTantosContra(),
																usTo.getTantosFavor(), usTo.getTantosContra());
						}
					}
												
					if((puntua && retorno > 0) || (!puntua && retorno < 0)){
						usTo.setPosicion(ut.getPosicion());
							
						if(puntua && retorno > 0){
							ut.setPosicion(ut.getPosicion()+1);	
							System.out.println("---- Recomparando a dos..."+usTo.getId().getParentUser().getAlias()+" Puntua: sube una posición ----");
							System.out.println("---- y baja: "+usTo.getId().getParentUser().getAlias()+" ---");
						}
						else{
							ut.setPosicion(ut.getPosicion()-1);
							System.out.println("---- Recomparando a dos..."+usTo.getId().getParentUser().getAlias()+" No Puntua: baja una posición ----");
							System.out.println("---- y sube: "+usTo.getId().getParentUser().getAlias()+" ---");
						}
																		
						userTorneoDAO.mergeUserTorneo(ut);				
					}
					usTo = userTorneoDAO.mergeUserTorneo(usTo);
				}
				else{ // Triples (o más) empates
					
					///////////////////////////////////////////////////////////////////
					Iterator<UserTorneo> iteratorIguales = utIguales.iterator();
					boolean enfrentadosTodos=true;
					
					while(iteratorIguales.hasNext() && enfrentadosTodos){
						UserTorneo igual = iteratorIguales.next();
						
						if(partidoDAO.PartidosUsuariosTorneo(usTo, igual).size() +
								partidoDAO.PartidosUsuariosTorneo(igual, usTo).size() < torn.getRondas()){ 
							enfrentadosTodos = false;
							break;
						}
						
						Iterator<UserTorneo> iteratorCopia = iteratorIguales;
						while(iteratorCopia.hasNext()){
							UserTorneo igualCopia = iteratorIguales.next();
							
							if(partidoDAO.PartidosUsuariosTorneo(igual, igualCopia).size() +
									partidoDAO.PartidosUsuariosTorneo(igualCopia, igual).size() < torn.getRondas()){ 
								enfrentadosTodos = false;
								break;
							}
						}
					}
					//////////////////////////////////////////////////////////////////

					if(enfrentadosTodos == false ){ // Si no se han enfrentado entre todos
						iteratorIguales = utIguales.iterator();	
						List<UserTorneo> listaCociente = new ArrayList<UserTorneo>();
												
						if(reglas.equals("ACB")){
							compara = new ACBUserTorneoDosComparator();
						}
						else if(reglas.equals("LFP")){
							compara = new LFPUserTorneoDosComparator();
						}
						else if(reglas.equals("EPL")){
							compara = new EPLUserTorneoDosComparator();
						}
						
						while(iteratorIguales.hasNext()){
							UserTorneo igual = iteratorIguales.next();

								int retorno = compara.compare(igual, usTo);
								
								if((puntua && retorno > 0) || (!puntua && retorno < 0)){
									usTo.setPosicion(igual.getPosicion());
									
									if(puntua && retorno > 0){
										igual.setPosicion(igual.getPosicion()+1);
//										System.out.println("---- Recomparando triple o más... Puntua: "
//												+usTo.getId().getParentUser().getAlias()+" sube una posición ----");
									}
									else{
										igual.setPosicion(igual.getPosicion()-1);
//										System.out.println("---- Recomparando triple o más... Puntua: "
//												+usTo.getId().getParentUser().getAlias()+" baja una posición ----");
									}

									userTorneoDAO.mergeUserTorneo(igual);																
								}
								
								else if(retorno == 0 && ptosVictoria == null){
									// Lo hago para cualquier el cociente para los que no haya puntos.															
									// Añadir a una lista
									listaCociente.add(igual);
								}	
						}
						
						if(!listaCociente.isEmpty()){
							if(listaCociente.size() == 1){
								usTo = SacarSubDatosDosUserTorneo(usTo, listaCociente, ptosVictoria);
								
								UserTorneo ut = listaCociente.get(0);
								
								int retorno = AverageGlobalUserTorneo.Cociente(usTo.getTantosFavor(), usTo.getTantosContra(),
																		ut.getTantosFavor(), ut.getTantosContra());
								
								if((puntua && retorno > 0) || (!puntua && retorno < 0)){
									usTo.setPosicion(ut.getPosicion());
									
									if(puntua && retorno > 0){
										ut.setPosicion(ut.getPosicion()+1);
//										System.out.println("---- Recomparando triple o más... Puntua: "
//												+usTo.getId().getParentUser().getAlias()+" sube una posición ----");
									}
									else{
										ut.setPosicion(ut.getPosicion()-1);
//										System.out.println("---- Recomparando triple o más... Puntua: "
//												+usTo.getId().getParentUser().getAlias()+" baja una posición ----");
									}

									userTorneoDAO.mergeUserTorneo(ut);
								}
								
								usTo = userTorneoDAO.mergeUserTorneo(usTo);
							}
							else{
								usTo = SacarSubDatosMultipleUserTorneo(usTo, listaCociente, ptosVictoria);
								
								listaCociente.add(usTo);
								
								Collections.sort(listaCociente, new CocienteUserTorneoComparator());
								
								int posi = FuncionesAuxiliares.SacarPosicionMasAltaUserTorneo(listaCociente);
								int i = 0;
						
								Iterator<UserTorneo> itCoc = listaCociente.iterator();
								
								while(itCoc.hasNext()){
									UserTorneo userT = itCoc.next();
									
									userT.setPosicion(posi + i);
									
									userTorneoDAO.mergeUserTorneo(userT);
								}
							}
						}
						
						usTo = userTorneoDAO.mergeUserTorneo(usTo);
					}
					
					else{ // Si se han enfrentado entre todos, AQUI ESTÁ LA CHICHA !!!!
						usTo = SacarSubDatosMultipleUserTorneo(usTo, utIguales, ptosVictoria);
						Comparator<UserTorneo> comparaDos = null;
						
						// Se supone que ya está bien creada la lista de los subvalores, toca ordenarla con un comparator
						utIguales.add(usTo);
						// Seleccionamos el comparador y comparamos
						if(reglas.equals("ACB")){
							compara = new ACBUserTorneoMultipleFinalComparator();
							comparaDos = new ACBUserTorneoDosFinalComparator();
						}
						else if(reglas.equals("LFP")){
							compara = new LFPUserTorneoSubComparator();
							comparaDos = compara;
						}
						else if(reglas.equals("EPL")){
							compara = new EPLUserTorneoSubComparator();
						}
						
						ComparacionRecursiva(utIguales,compara,comparaDos,FuncionesAuxiliares.SacarPosicionMasAltaUserTorneo(utIguales),
								ptosVictoria);
					}
				}
			}
			usTo = userTorneoDAO.mergeUserTorneo(usTo);
		}
		
		else{
			usTo = userTorneoDAO.mergeUserTorneo(usTo);
		}
		
		return usTo;
	}
	
	
	/**
	 * Realiza una comparación recursiva para varios usuarios empatados
	 * @param utIguales Usuarios a comparar
	 * @param compara Comparator para un número de usuarios mayor a dos
	 * @param comparaDos Comparator para dos usuarios
	 * @param posicionMasAlta Número de la posición más alta de los usuarios implicados
	 * @param ptosVictoria Número de puntos otorgados por la victoria
	 */
	void ComparacionRecursiva (List<UserTorneo> utIguales, Comparator<UserTorneo> compara, Comparator<UserTorneo> comparaDos,
							Integer posicionMasAlta, Integer ptosVictoria){		
		Collections.sort(utIguales, compara); // Colocamos la lista
		
		Iterator<UserTorneo> it = utIguales.iterator();
		
		while(it.hasNext()){
			UserTorneo ut = it.next();
			
			List<UserTorneo> listaEmpatados = new ArrayList<UserTorneo>();
			
			Iterator<UserTorneo> itRep = it;
			
			while(itRep.hasNext()){
				UserTorneo utRep = itRep.next();
				
				if(compara.compare(ut, utRep) != 0){
					break;
				}
				else{
					it.next(); // Pasamos para q no lo vuelva a comparar fuera
					listaEmpatados.add(utRep);
				}
			}
			
			userTorneoDAO = new UserTorneoDAOImpl();
			
			if(listaEmpatados.isEmpty()){								
				ut.setPosicion(utIguales.indexOf(ut) + posicionMasAlta); // IndexOf suponiendo que el primero es 0
				
				userTorneoDAO.mergeUserTorneo(ut);
			}
			else{
				int posi = utIguales.indexOf(ut); // ut va antes de utRival, evidentemente.
				
				if(listaEmpatados.size() == 1){ // Si solo son dos los equipos...
					ut = SacarSubDatosDosUserTorneo(ut, listaEmpatados, ptosVictoria);
					
					UserTorneo utRival = listaEmpatados.get(0);	
					
					if(comparaDos.compare(ut, utRival) >= 0 && utRival.getPosicion() - ut.getPosicion() > 0){
						ut.setPosicion(posi + posicionMasAlta);
						utRival.setPosicion(posi + 1 + posicionMasAlta);						
					}
					
					else if(comparaDos.compare(ut, utRival) < 0 && utRival.getPosicion() - ut.getPosicion() < 0){
						utRival.setPosicion(posi + posicionMasAlta);
						ut.setPosicion(posi + 1 + posicionMasAlta);
					}

					userTorneoDAO.mergeUserTorneo(ut);
					userTorneoDAO.mergeUserTorneo(utRival);
				}
				else{
					ut = SacarSubDatosMultipleUserTorneo(ut, listaEmpatados, ptosVictoria);
					
					// Ahora a comparar... RECURSIVIDAD !!! Cuidadito con los bucles infinitos !!
					// Entiendo que haciendo la comparacion ya no habrá bucles infinitos
					listaEmpatados.add(ut);
					
					if(listaEmpatados.size() != utIguales.size()){
						ComparacionRecursiva(listaEmpatados,compara, comparaDos, FuncionesAuxiliares.SacarPosicionMasAltaUserTorneo(utIguales),
								ptosVictoria);	
					}
					else{
						Collections.sort(listaEmpatados,compara); // Colocamos
						
						Iterator<UserTorneo> itComp = listaEmpatados.iterator();
						int i=0;
						
						while(itComp.hasNext()){
							UserTorneo utComp = itComp.next();
							
							utComp.setPosicion(posi + posicionMasAlta + i);
							userTorneoDAO.mergeUserTorneo(utComp);
							
							i++;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Saca los datos entre dos usuarios para realizar la comparación
	 * @param usTo Objeto UserTorneo del equipo
	 * @param utIguales Usuarios para comparar, solo será uno
	 * @param ptosVictoria Puntos otorgados por la victoria
	 * @return Objeto UserTorneo del usuario actualizado
	 */
	UserTorneo SacarSubDatosDosUserTorneo(UserTorneo usTo, List<UserTorneo> utIguales, Integer ptosVictoria){
		partidoDAO = new PartidoDAOImpl();
		
		usTo = LimpiarSubDatosUserTorneo(usTo, utIguales); // Limpiamos los subdatos
		
		UserTorneo ut = utIguales.get(0);
		
		List<PartidoIndividualVersus> partidosUsTo = partidoDAO.PartidosUsuariosTorneo(usTo, ut);
		Iterator<PartidoIndividualVersus> itPUsTo = partidosUsTo.iterator();
		
		List<PartidoIndividualVersus> partidosUtRival = partidoDAO.PartidosUsuariosTorneo(ut, usTo);
		Iterator<PartidoIndividualVersus> itPUtRival = partidosUtRival.iterator();
		
		while(itPUsTo.hasNext() || itPUtRival.hasNext()){
			PartidoIndividualVersus partUsTo;
			PartidoIndividualVersus partUtRival;
			
			if(itPUsTo.hasNext()){
				partUsTo = itPUsTo.next();
				
				usTo.setSubTantosFavor(usTo.getSubTantosFavor() + partUsTo.getPuntosLocal());
				usTo.setSubTantosContra(usTo.getSubTantosContra() + partUsTo.getPuntosVisitante());
				ut.setSubTantosFavor(ut.getSubTantosFavor() + partUsTo.getPuntosVisitante());
				ut.setSubTantosContra(ut.getSubTantosContra() + partUsTo.getPuntosLocal());
				
				
				
				if(partUsTo.getPuntosLocal() - partUsTo.getPuntosVisitante() > 0){ // Si gana usTo
					usTo.setSubPartidosGanados(usTo.getSubPartidosGanados() + 1);
					if(ptosVictoria != null){
						usTo.setSubPuntos(usTo.getSubPuntos() + ptosVictoria);	
					}
				}
				else if(partUsTo.getPuntosLocal() - partUsTo.getPuntosVisitante() == 0){ // Empate
					if(ptosVictoria != null){
						usTo.setSubPuntos(usTo.getSubPuntos() + 1);
						ut.setSubPuntos(ut.getSubPuntos() + 1);
					}
				}
					else{ // Si pierde
						ut.setSubPartidosGanados(ut.getSubPartidosGanados() + 1);
						if(ptosVictoria != null){
							ut.setSubPuntos(ut.getSubPuntos() + ptosVictoria);	
						}
					}
			}
			
			if(itPUtRival.hasNext()){
				partUtRival = itPUtRival.next();

				ut.setSubTantosFavor(ut.getSubTantosFavor() + partUtRival.getPuntosLocal());
				ut.setSubTantosContra(ut.getSubTantosContra() + partUtRival.getPuntosVisitante());
				usTo.setSubTantosFavor(usTo.getSubTantosFavor() + partUtRival.getPuntosVisitante());
				usTo.setSubTantosContra(usTo.getSubTantosContra() + partUtRival.getPuntosLocal());
				
				if(partUtRival.getPuntosLocal() - partUtRival.getPuntosVisitante() > 0){ // Si gana usTo
					ut.setSubPartidosGanados(ut.getSubPartidosGanados() + 1);
					if(ptosVictoria != null){
						ut.setSubPuntos(ut.getSubPuntos() + ptosVictoria);	
					}
				}
				else if(partUtRival.getPuntosLocal() - partUtRival.getPuntosVisitante() == 0){ // Empate
					if(ptosVictoria != null){
						usTo.setSubPuntos(usTo.getSubPuntos() + 1);
						ut.setSubPuntos(ut.getSubPuntos() + 1);
					}
				}
				else{ // Si pierde
					usTo.setSubPartidosGanados(usTo.getSubPartidosGanados() + 1);
					if(ptosVictoria != null){
						usTo.setSubPuntos(usTo.getSubPuntos() + ptosVictoria);	
					}
				}
			}
		}
		
		utIguales.set(0, ut);
		
		return usTo;
	}
	
	/**
	 * Saca los datos entre más de dos usuarios para realizar la comparación
	 * @param usTo Objeto UserTorneo del usuario
	 * @param utIguales Usuarios para comparar
	 * @param ptosVictoria Puntos otorgados por la victoria
	 * @return Objeto UserTorneo del usuario actualizado
	 */
	UserTorneo SacarSubDatosMultipleUserTorneo(UserTorneo usTo, List<UserTorneo> utIguales, Integer ptosVictoria){
		Integer index = null;
		
		partidoDAO = new PartidoDAOImpl();
		
		usTo = LimpiarSubDatosUserTorneo(usTo, utIguales); // Limpiamos los subdatos
		
		Iterator<UserTorneo> itUT = utIguales.iterator();
								
		while(itUT.hasNext()){
			UserTorneo utRival = itUT.next();
			
			index = utIguales.indexOf(utRival);
			
			List<PartidoIndividualVersus> partidosUsTo = partidoDAO.PartidosUsuariosTorneo(usTo, utRival);
			Iterator<PartidoIndividualVersus> itPUsTo = partidosUsTo.iterator();
			
			List<PartidoIndividualVersus> partidosUtRival = partidoDAO.PartidosUsuariosTorneo(utRival, usTo);
			Iterator<PartidoIndividualVersus> itPUtRival = partidosUtRival.iterator();
			
			while(itPUsTo.hasNext() || itPUtRival.hasNext()){
				PartidoIndividualVersus partUsTo;
				PartidoIndividualVersus partUtRival;
				
				if(itPUsTo.hasNext()){
					partUsTo = itPUsTo.next();
					
					usTo.setSubTantosFavor(usTo.getSubTantosFavor() + partUsTo.getPuntosLocal());
					usTo.setSubTantosContra(usTo.getSubTantosContra() + partUsTo.getPuntosVisitante());
					utRival.setSubTantosFavor(utRival.getSubTantosFavor() + partUsTo.getPuntosVisitante());
					utRival.setSubTantosContra(utRival.getSubTantosContra() + partUsTo.getPuntosLocal());
					
					if(partUsTo.getPuntosLocal() - partUsTo.getPuntosVisitante() > 0){ // Si gana usTo
						usTo.setSubPartidosGanados(usTo.getSubPartidosGanados() + 1);
						if(ptosVictoria != null){
							usTo.setSubPuntos(usTo.getSubPuntos() + ptosVictoria);	
						}
						
					}
					else if(partUsTo.getPuntosLocal() - partUsTo.getPuntosVisitante() == 0){ // Empate
							if(ptosVictoria != null){
								usTo.setSubPuntos(usTo.getSubPuntos() + 1);
								utRival.setSubPuntos(utRival.getSubPuntos() + 1);
							}
					}
						else{ // Si pierde
							utRival.setSubPartidosGanados(utRival.getSubPartidosGanados() + 1);
							if(ptosVictoria != null){
								utRival.setSubPuntos(utRival.getSubPuntos() + ptosVictoria);	
							}
					}								
				}
				
				
				
				if(itPUtRival.hasNext()){
					partUtRival = itPUtRival.next();

					utRival.setSubTantosFavor(utRival.getSubTantosFavor() + partUtRival.getPuntosLocal());
					utRival.setSubTantosContra(utRival.getSubTantosContra() + partUtRival.getPuntosVisitante());
					usTo.setSubTantosFavor(usTo.getSubTantosFavor() + partUtRival.getPuntosVisitante());
					usTo.setSubTantosContra(usTo.getSubTantosContra() + partUtRival.getPuntosLocal());
					
					if(partUtRival.getPuntosLocal() - partUtRival.getPuntosVisitante() > 0){ // Si gana utRival
						utRival.setSubPartidosGanados(utRival.getSubPartidosGanados() + 1);
						if(ptosVictoria != null){
							utRival.setSubPuntos(utRival.getSubPuntos() + ptosVictoria);	
						}
						
					}
					else if(partUtRival.getPuntosLocal() - partUtRival.getPuntosVisitante() == 0){ // Empate
							if(ptosVictoria != null){
								utRival.setSubPuntos(utRival.getSubPuntos() + 1);
								usTo.setSubPuntos(usTo.getSubPuntos() + 1);
							}
					}
						else{ // Si pierde
							usTo.setSubPartidosGanados(usTo.getSubPartidosGanados() + 1);
							if(ptosVictoria != null){
								usTo.setSubPuntos(usTo.getSubPuntos() + ptosVictoria);	
							}
					}
				}
																												
												
				
				
				for(int i=index+1; utIguales.get(i) != null; i++){
					UserTorneo utContrincante = utIguales.get(i);
					
					List<PartidoIndividualVersus> partidosSubUtContrincante = partidoDAO.PartidosUsuariosTorneo(utContrincante,
																										utRival);
					Iterator<PartidoIndividualVersus> itPSubCont = partidosSubUtContrincante.iterator();
					
					List<PartidoIndividualVersus> partidosSubUtRival = partidoDAO.PartidosUsuariosTorneo(utRival,
																										utContrincante);
					Iterator<PartidoIndividualVersus> itPSubRival = partidosSubUtRival.iterator();
					
					while(itPSubCont.hasNext() || itPSubRival.hasNext()){
						PartidoIndividualVersus partCont;
						PartidoIndividualVersus partRival;
						
						if(itPSubCont.hasNext()){
							partCont = itPSubCont.next();
							
							utContrincante.setSubTantosFavor(utContrincante.getSubTantosFavor() +
															partCont.getPuntosLocal());
							utContrincante.setSubTantosContra(utContrincante.getSubTantosContra() + 
															partCont.getPuntosVisitante());
							utRival.setSubTantosFavor(utRival.getSubTantosFavor() + 
															partCont.getPuntosVisitante());
							utRival.setSubTantosContra(utRival.getSubTantosContra() + 
															partCont.getPuntosLocal());
							
							if(partCont.getPuntosLocal() - partCont.getPuntosVisitante() > 0){ // Si gana utContrincante
								utContrincante.setSubPartidosGanados(utContrincante.getSubPartidosGanados() + 1);
								if(ptosVictoria != null){
									utContrincante.setSubPuntos(utContrincante.getSubPuntos() + ptosVictoria);
								}												
							}
							else if(partCont.getPuntosLocal() - partCont.getPuntosVisitante() == 0){ // Empate
								if(ptosVictoria != null){
									utContrincante.setSubPuntos(utContrincante.getSubPuntos() + 1);
									utRival.setSubPuntos(utRival.getSubPuntos() + 1);
								}
							}
								else{ // Si pierde
									utRival.setSubPartidosGanados(utRival.getSubPartidosGanados() + 1);
									if(ptosVictoria != null){
										utRival.setSubPuntos(utRival.getSubPuntos() + ptosVictoria);
									}
								}
						}
						
						if(itPSubRival.hasNext()){
							partRival = itPSubRival.next();
							
							utRival.setSubTantosFavor(utRival.getSubTantosFavor() + 
															partRival.getPuntosLocal());
							utRival.setSubTantosContra(utRival.getSubTantosContra() + 
															partRival.getPuntosVisitante());
							utContrincante.setSubTantosFavor(utContrincante.getSubTantosFavor() + 
															partRival.getPuntosVisitante());
							utContrincante.setSubTantosContra(utContrincante.getSubTantosContra() + 
															partRival.getPuntosLocal());
							
							if(partRival.getPuntosLocal() - partRival.getPuntosVisitante() > 0){ // Si gana utRival
								utRival.setSubPartidosGanados(utRival.getSubPartidosGanados() + 1);
								if(ptosVictoria != null){
									utRival.setSubPuntos(utRival.getSubPuntos() + ptosVictoria);													
								}												
							}
							else if(partRival.getPuntosLocal() - partRival.getPuntosVisitante() == 0){ // Empate
								utRival.setSubPuntos(utRival.getSubPuntos() + 1);
								if(ptosVictoria != null){
									utRival.setSubPuntos(utRival.getSubPuntos() + 1);
									utContrincante.setSubPuntos(utContrincante.getSubPuntos() + 1);
								}
							}
								else{ // Si pierde
									utContrincante.setSubPartidosGanados(utContrincante.getSubPartidosGanados() + 1);
									if(ptosVictoria != null){
										utContrincante.setSubPuntos(utContrincante.getSubPuntos() + ptosVictoria);
									}
								}
						}	
					}
					
					utIguales.set(i, utContrincante); // Seteamos utContrincante en la lista
				}
			}
			
			utIguales.set(index, utRival); // Seteamos utRival en la lista
		}
		
		return usTo;
	}

	/**
	 * Pone a cero todos los valores de subcomparación
	 * @param usTo Objeto UserTorneo del usuario
	 * @param utIguales Usuarios a comparar
	 * @return Objeto UserTorneo actualizado
	 */
	UserTorneo LimpiarSubDatosUserTorneo(UserTorneo usTo, List<UserTorneo> utIguales){
		Iterator<UserTorneo> it = utIguales.iterator();
		int index=0;
		
		while(it.hasNext()){
			UserTorneo ut = it.next();
			
			ut.setSubPartidosGanados(0);
			ut.setSubPuntos(0);
			ut.setSubTantosFavor(0);
			ut.setSubTantosContra(0);
			
			utIguales.set(index, ut);
			
			index++;
		}
		
		usTo.setSubPartidosGanados(0);
		usTo.setSubPuntos(0);
		usTo.setSubTantosFavor(0);
		usTo.setSubTantosContra(0);
		
		return usTo;
	}

	/**
	 * Saca el cociente de los tantos a favor y en contra
	 * en una competición de un usuario
	 * @param ut Objeto UserTorneo del usuario
	 * @return Cociente resultante
	 */
	float SacarCociente(UserTorneo ut){
		if(ut.getTantosFavor() == 0){
			return (float) 0;
		}
		else if(ut.getTantosContra() == 0){
			return Float.POSITIVE_INFINITY;
		}
		else{
			return (float) (ut.getTantosFavor()/ut.getTantosContra());
		}
	}
	
	
	//Agregado al implementar SessionAware
	@Override
	public void setSession(Map s) {
	sesion = s;
	}
	
	public Map getSession() {

	return sesion;

	}	

}
