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
import com.proyecto.dao.EquipoTorneoDAO;
import com.proyecto.dao.PartidoDAO;
import com.proyecto.dao.PartidoDAOImpl;
import com.proyecto.dao.TorneoDAO;
import com.proyecto.dao.TorneoDAOImpl;
import com.proyecto.dao.EquipoTorneoDAOImpl;
import com.proyecto.dao.EquipoTorneoDAOImpl;
import com.proyecto.dao.UserTorneoDAOImpl;
import com.proyecto.dominio.Actividad;
import com.proyecto.dominio.Equipo;
import com.proyecto.dominio.EquipoTorneo;
import com.proyecto.dominio.EquipoTorneoPK;
import com.proyecto.dominio.PartidoEquiposVersus;
import com.proyecto.dominio.PartidoIndividualVersus;
import com.proyecto.dominio.Torneo;
import com.proyecto.dominio.User;
import com.proyecto.dominio.EquipoTorneo;
import com.proyecto.dominio.EquipoTorneoPK;
import com.proyecto.dominio.EquipoTorneo;
import com.proyecto.dominio.EquipoTorneo;
import com.proyecto.dominio.EquipoTorneo;
import com.proyecto.dominio.EquipoTorneo;
import com.proyecto.dominio.UserTorneo;
import com.proyecto.dominio.UserTorneoPK;
import com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares;
import com.proyecto.util.hibernate.HibernateUtil;
import com.proyecto.util.listas.AverageGlobalEquipoTorneo;
import com.proyecto.util.listas.CocienteEquipoTorneoComparator;
import com.proyecto.util.listas.PosicionEquipoTorneoComparator;
import com.proyecto.util.listas.ACB.ACBEquipoTorneoDosComparator;
import com.proyecto.util.listas.ACB.ACBEquipoTorneoDosFinalComparator;
import com.proyecto.util.listas.ACB.ACBEquipoTorneoMultipleFinalComparator;
import com.proyecto.util.listas.EPL.EPLEquipoTorneoDosComparator;
import com.proyecto.util.listas.EPL.EPLEquipoTorneoDosFinalComparator;
import com.proyecto.util.listas.EPL.EPLEquipoTorneoSubComparator;
import com.proyecto.util.listas.LFP.LFPEquipoTorneoDosComparator;
import com.proyecto.util.listas.LFP.LFPEquipoTorneoDosFinalComparator;
import com.proyecto.util.listas.LFP.LFPEquipoTorneoSubComparator;

/**
* Esta clase implementa el Action asociado a los
* partidos por equipos.
* @author Lucas Sánchez López
* @version 3.0
*/

public class PartidoEquiposAction extends ActionSupport implements ModelDriven<PartidoEquiposVersus>,SessionAware {
	private static final long serialVersionUID = 1L;
	private PartidoEquiposVersus partido = new PartidoEquiposVersus();
	private PartidoDAO partidoDAO;
	private TorneoDAO torneoDAO;
	private EquipoTorneoDAO equipoTorneoDAO;
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
	public PartidoEquiposVersus getModel() {
		// TODO Auto-generated method stub
		return partido;
	}
	
	public PartidoEquiposVersus getPartido() {
		return partido;
	}

	public void setPartido(PartidoEquiposVersus partido) {
		this.partido = partido;
	}

	/**
	 * Introduce el resultado de un partido por equipos
	 * @return SUCCESS en caso de éxito, INPUT en caso de no completarse, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String IntroducirResultadoEquipo(){
		boolean error=false;
		boolean exito=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				System.out.println("---- Dentro de IntroducirResultado");
				PartidoEquiposVersus par = (PartidoEquiposVersus)session.load(PartidoEquiposVersus.class,partido.getId());
				
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
				
				if((Integer)sesion.get("equipo") != 0){
					Equipo eq = (Equipo)session.load(Equipo.class,(Integer)sesion.get("equipo"));
					par.setParentIntroductorResultado(eq);	
				}

				partidoDAO = new PartidoDAOImpl();
				
				// Actualizamos el partido
				partido = partidoDAO.mergePartidoEquiposVersus(par);
				
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
	 * Rechaza el resultado de un partido por equipos
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
				PartidoEquiposVersus par = (PartidoEquiposVersus)session.load(PartidoEquiposVersus.class,partido.getId());

				par.setRechazado(true);
					
				partidoDAO = new PartidoDAOImpl();
					
				// Actualizamos el partido
				partidoDAO.mergePartidoEquiposVersus(par);
				
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
	 * Confirma el resultado de un partido por equipos
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
				PartidoEquiposVersus par = (PartidoEquiposVersus)session.load(PartidoEquiposVersus.class,partido.getId());

				par.setConfirmado(true);
				par.setRechazado(false); // Por si era un partido rechazado
					
				partidoDAO = new PartidoDAOImpl();
				
					
				// Actualizamos el partido
				partidoDAO.mergePartidoEquiposVersus(par);	
				
				System.out.println("Partido ya confirmado pasando a actualizarse en la Clasificación...");
				if(par.getParentGanador() != null){
					System.out.println("Ganador -> "+par.getParentGanador().getNombre()+
							",  Perdedor -> "+par.getParentPerdedor().getNombre());
				}
				System.out.println(" Local "+par.getPuntosLocal()+" - "+par.getPuntosVisitante()+" Visitante");
				
				Torneo tor = par.getParentTorneo();
				idtorneo = tor.getId();
				
				if(tor.getLiga()){
					// Actualizamos la clasificacion
					ActualizarClasificacion(par);
					
					List<PartidoEquiposVersus> lista = partidoDAO.PartidosNoConfirmadosEquipos(tor);
					
					if(lista.isEmpty()){
						tor.setEstado(FuncionesAuxiliares.FINALIZADO);
						// Damos el torneo por finalizado
						
						torneoDAO = new TorneoDAOImpl();
						torneoDAO.mergeTorneo(tor);
						
						finalizado=true;
					}
				}
				else{ // Ponemos el flag de eliminado al perdedor
					EquipoTorneo eqTo = (EquipoTorneo)session.load(EquipoTorneo.class,
							new EquipoTorneoPK(par.getParentPerdedor(),tor));
					
					eqTo.setEliminado(true);
					
					equipoTorneoDAO = new EquipoTorneoDAOImpl();
					equipoTorneoDAO.mergeEquipoTorneo(eqTo);
					
					List<EquipoTorneo> eqToList = equipoTorneoDAO.EquipoTorneoNoEliminados(tor);
					
					if(eqToList.size() == 1){
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
	 * Actualiza la clasificación tras la confirmación de un partido por equipos
	 * @param par Partido por equipos confirmado
	 */
	public void ActualizarClasificacion(PartidoEquiposVersus par){
		// -------------- Intentando actualizar EquipoTorneo para la tabla de clasificación ---------
		Equipo eqGanador = par.getParentGanador();
		Equipo eqPerdedor = par.getParentPerdedor();
		EquipoTorneo eqTo = null;
		EquipoTorneo eqTo2 = null;				
		Torneo tor = par.getParentTorneo();
		idtorneo = tor.getId();
//		Set<EquipoTorneo> equipoTorneos = tor.getEquipoTorneos();
		Actividad act = tor.getParentActividad();
		Integer ptosVictoria = act.getPtosVictoria();
		
		equipoTorneoDAO = new EquipoTorneoDAOImpl();
		
		if(eqGanador != null){ // Si no hay empate deberia haber un ganador
			
			eqTo = (EquipoTorneo)session.load(EquipoTorneo.class,
					new EquipoTorneoPK(eqGanador,tor));
			
			List<EquipoTorneo> equipoTorneos = equipoTorneoDAO.EquipoTorneoParticipante(tor);			
			equipoTorneos.remove(eqTo); // Sacamos del grupo a eqTo
			
			eqTo.setPartidosGanados(eqTo.getPartidosGanados()+1);

			if(ptosVictoria != null){
				eqTo.setPuntos(eqTo.getPuntos() + ptosVictoria);
			}
			
			if(eqGanador.equals(par.getParentLocal())){
				eqTo.setTantosFavor(eqTo.getTantosFavor() + par.getPuntosLocal());
				eqTo.setTantosContra(eqTo.getTantosContra() + par.getPuntosVisitante());
			}
			else{ // Si no es local, será visitante, ATENCIÓN, CUIDADO NO FALLE AQUI!!!
				eqTo.setTantosFavor(eqTo.getTantosFavor() + par.getPuntosVisitante());
				eqTo.setTantosContra(eqTo.getTantosContra() + par.getPuntosLocal());
			}
			
			eqTo = ColocarEnClasificacion(eqTo, equipoTorneos,tor,act,true);
		}
		
		
		if(eqPerdedor != null){ // Si no hay empate deberia haber un perdedor
			eqTo2 = (EquipoTorneo)session.load(EquipoTorneo.class,
					new EquipoTorneoPK(eqPerdedor,tor));
			
			List<EquipoTorneo> equipoTorneos = equipoTorneoDAO.EquipoTorneoParticipante(tor);
			equipoTorneos.remove(eqTo2); // Sacamos del grupo a eqTo2
			
			eqTo2.setPartidosPerdidos(eqTo2.getPartidosPerdidos()+1);
			
			if(eqPerdedor.equals(par.getParentLocal())){
				eqTo2.setTantosFavor(eqTo2.getTantosFavor() + par.getPuntosLocal());
				eqTo2.setTantosContra(eqTo2.getTantosContra() + par.getPuntosVisitante());
			}
			else{ // Si no es local, será visitante, ATENCIÓN, CUIDADO NO FALLE AQUI!!!
				eqTo2.setTantosFavor(eqTo2.getTantosFavor() + par.getPuntosVisitante());
				eqTo2.setTantosContra(eqTo2.getTantosContra() + par.getPuntosLocal());
			}
			
			eqTo2 = ColocarEnClasificacion(eqTo2, equipoTorneos,tor,act,false);
					
		}
		
		if(eqGanador == null && eqPerdedor == null){ // EMPATE, Si uno fuera null el otro tb debería pero por asegurar
			eqTo = (EquipoTorneo)session.load(EquipoTorneo.class,
					new EquipoTorneoPK(par.getParentLocal(),(Torneo)session.load(Torneo.class,idtorneo)));
			
			List<EquipoTorneo> equipoTorneos = equipoTorneoDAO.EquipoTorneoParticipante(tor);
			equipoTorneos.remove(eqTo); // Sacamos del grupo a eqTo
			
			eqTo2 = (EquipoTorneo)session.load(EquipoTorneo.class,
					new EquipoTorneoPK(par.getParentVisitante(),(Torneo)session.load(Torneo.class,idtorneo)));
			EquipoTorneo visitante = eqTo2;
				
			eqTo.setPartidosEmpatados(eqTo.getPartidosEmpatados()+1);
			eqTo2.setPartidosEmpatados(eqTo2.getPartidosEmpatados()+1);
			
			Integer ptos = par.getPuntosLocal(); // Al ser empate tendrán los mismos puntos local y visitante en contra y a favor.
			
			eqTo.setTantosFavor(eqTo.getTantosFavor()+ ptos); 
			eqTo.setTantosContra(eqTo.getTantosContra()+ ptos);

			eqTo2.setTantosFavor(eqTo2.getTantosFavor()+ ptos); 
			eqTo2.setTantosContra(eqTo2.getTantosContra()+ ptos);

			if(ptosVictoria != null){
				eqTo.setPuntos(eqTo.getPuntos() + 1); // Siempre será un punto por empatar
				eqTo2.setPuntos(eqTo2.getPuntos() + 1);
			}

			eqTo = ColocarEnClasificacion(eqTo, equipoTorneos,tor,act,true);
						
			equipoTorneos = equipoTorneoDAO.EquipoTorneoParticipante(tor);
			equipoTorneos.remove(visitante); // Sacamos del grupo a eqTo2 antes de ser modificado
			
			eqTo2 = ColocarEnClasificacion(eqTo2, equipoTorneos,tor,act,true);						
		}
	}
	
	/**
	 * Coloca al equipo en la tabla clasificatoria
	 * @param eqTo Objeto EquipoTorneo del equipo a colocar en la clasificación
	 * @param setET Equipos con los que comparara
	 * @param torn Torneo
	 * @param act Actividad del torneo
	 * @param puntua Indica si el equipo ha puntuado en el partido, si no ha perdido
	 * @return Objeto EquipoTorneo con la posición del equipo actualizada
	 */
	public EquipoTorneo ColocarEnClasificacion(EquipoTorneo eqTo, List<EquipoTorneo> setET, Torneo torn, Actividad act, boolean puntua){
//		int pos = eqTo.getPosicion();

		List<EquipoTorneo> listaET = new ArrayList<EquipoTorneo>();
		Iterator<EquipoTorneo> it = setET.iterator();
		
		System.out.println("---- Dentro de ColocarEnClasificacion ----");
//		int participantes = 0;
		Integer ptosVictoria = act.getPtosVictoria();
		
		while(it.hasNext()){
			EquipoTorneo et = it.next();
				
			if(ptosVictoria == null && 
					(puntua &&
						et.getPartidosGanados() <= eqTo.getPartidosGanados()  && 
						et.getPosicion() < eqTo.getPosicion() ||
					!puntua &&
						et.getPartidosGanados() == eqTo.getPartidosGanados() && et.getPosicion() < eqTo.getPosicion()) ||
				ptosVictoria != null && 
					(puntua && 
						et.getPuntos() <= eqTo.getPuntos() &&
						et.getPosicion() < eqTo.getPosicion()  ||
					!puntua &&							
						et.getPuntos() == eqTo.getPuntos() && et.getPosicion() > eqTo.getPosicion())){
					
				listaET.add(et);
//				System.out.println("---- listaET.add("+et.getId().getParentEquipo().getNombre()+") ----");				
			}
		}
		
		equipoTorneoDAO = new EquipoTorneoDAOImpl();
		
		
		if(!listaET.isEmpty()){
			if(!puntua){ // Hay que comprobar que hago el sort en el sentido correcto
				Collections.sort(listaET, new PosicionEquipoTorneoComparator());	
			}
			else{
				Comparator<EquipoTorneo> compa = Collections.reverseOrder(new PosicionEquipoTorneoComparator());
				
				Collections.sort(listaET, compa);
			}
				
			Iterator<EquipoTorneo> itList = listaET.iterator();
			List<EquipoTorneo> etIguales = new ArrayList<EquipoTorneo>(); 
						
			
			if(puntua){
				while(itList.hasNext()){
					EquipoTorneo et = itList.next();
					
//					System.out.println("----> Sort... equipo: "+et.getId().getParentEquipo().getNombre());
					
					if(ptosVictoria != null && eqTo.getPuntos() > et.getPuntos() ||
							ptosVictoria == null && eqTo.getPartidosGanados() > et.getPartidosGanados()){
						eqTo.setPosicion(et.getPosicion());
						et.setPosicion(et.getPosicion()+1);					
							
//						System.out.println("---- Puntua: "+eqTo.getId().getParentEquipo().getNombre()+" sube una posición ----");					
							
						equipoTorneoDAO.mergeEquipoTorneo(et);
					}							
					else if(ptosVictoria != null && eqTo.getPuntos() == et.getPuntos() ||
						ptosVictoria == null && eqTo.getPartidosGanados() == et.getPartidosGanados()){
						// Meter en otra lista para luego volver a compararlos si hay empate a puntos
						etIguales.add(et);
								
//						System.out.println("---- Metemos en otra lista para comparar: " 
//							+et.getId().getParentEquipo().getNombre()+" ----");
					}
				}
			}
			else{
				while(itList.hasNext()){
					EquipoTorneo et = itList.next();
					
					if(et.getPartidosEmpatados() + et.getPartidosGanados() + et.getPartidosPerdidos() > 0){
						etIguales.add(et);
					}
					else if(et.getPosicion() > eqTo.getPosicion()){
						eqTo.setPosicion(et.getPosicion());
						et.setPosicion(et.getPosicion()-1);
						
						equipoTorneoDAO.mergeEquipoTorneo(et);						
					}
				}
			}
			
			
			
			if(!etIguales.isEmpty()){
				partidoDAO = new PartidoDAOImpl();
				String reglas = torn.getParentRegla().getNombre();
				Comparator<EquipoTorneo> compara=null;
				
				if(etIguales.size() == 1){ // Empate entre dos
					EquipoTorneo et = etIguales.get(0);
					boolean enfrentadosTodos = false;
					int retorno=0;
					// Aqui deberiamos acceder a una propiedad del torneo que nos diga que reglas de comparación seguir

					if((partidoDAO.PartidosEquiposTorneo(eqTo, et).size() +
							partidoDAO.PartidosEquiposTorneo(et, eqTo).size()) < torn.getRondas()){ 
						// Si no se han enfrentado todas las veces	
						if(reglas.equals("ACB")){
							compara = new ACBEquipoTorneoDosComparator();	
						}
						else if(reglas.equals("LFP")){
							compara = new LFPEquipoTorneoDosComparator();	
						}
						else if(reglas.equals("EPL")){
							compara = new EPLEquipoTorneoDosComparator();
						}
																		
						System.out.println("---- Recomparando... No es la última vuelta ----");
						
						retorno = compara.compare(et, eqTo);
					}
					else{ // Si se han enfrentado todas las veces
						enfrentadosTodos = true;
						
						if(reglas.equals("ACB")){
							compara = new ACBEquipoTorneoDosFinalComparator();
						}
						else if(reglas.equals("LFP")){
							compara = new LFPEquipoTorneoDosFinalComparator();	
						}
						else if(reglas.equals("EPL")){
							compara = new EPLEquipoTorneoDosFinalComparator();
						}
												
						System.out.println("---- Recomparando... Es la última vuelta ----");
					}
					
					
					if(retorno == 0){ // Da 0 el compare de enfrentados == false ó enfrentados == true
						
						eqTo = SacarSubDatosDosEquipoTorneo(eqTo,etIguales, ptosVictoria);
						
						et = etIguales.get(0); 
						
						if(enfrentadosTodos){
							retorno = compara.compare(et, eqTo);	
						}
						else if(ptosVictoria == null){ // Serán los unicos que utilizarán cociente
							retorno = AverageGlobalEquipoTorneo.Cociente(et.getTantosFavor(), et.getTantosContra(),
																eqTo.getTantosFavor(), eqTo.getTantosContra());
						}
					}
												
					if((puntua && retorno > 0) || (!puntua && retorno < 0)){
						eqTo.setPosicion(et.getPosicion());
							
						if(puntua && retorno > 0){
							et.setPosicion(et.getPosicion()+1);	
//							System.out.println("---- Recomparando a dos... Puntua: "
//									+eqTo.getId().getParentEquipo().getNombre()+" sube una posición ----");
						}
						else{
							et.setPosicion(et.getPosicion()-1);
//							System.out.println("---- Recomparando a dos... No Puntua: "
//									+eqTo.getId().getParentEquipo().getNombre()+" baja una posición ----");
						}
																		
						equipoTorneoDAO.mergeEquipoTorneo(et);				
					}
					eqTo = equipoTorneoDAO.mergeEquipoTorneo(eqTo);
				}
				else{ // Triples (o más) empates
					
					///////////////////////////////////////////////////////////////////
					Iterator<EquipoTorneo> iteratorIguales = etIguales.iterator();
					boolean enfrentadosTodos=true;
					
					while(iteratorIguales.hasNext() && enfrentadosTodos){
						EquipoTorneo igual = iteratorIguales.next();
						
						if(partidoDAO.PartidosEquiposTorneo(eqTo, igual).size() +
								partidoDAO.PartidosEquiposTorneo(igual, eqTo).size() < torn.getRondas()){ 
							enfrentadosTodos = false;
							break;
						}
						
						Iterator<EquipoTorneo> iteratorCopia = iteratorIguales;
						while(iteratorCopia.hasNext()){
							EquipoTorneo igualCopia = iteratorIguales.next();
							
							if(partidoDAO.PartidosEquiposTorneo(igual, igualCopia).size() +
									partidoDAO.PartidosEquiposTorneo(igualCopia, igual).size() < torn.getRondas()){ 
								enfrentadosTodos = false;
								break;
							}
						}
					}
					//////////////////////////////////////////////////////////////////

					if(enfrentadosTodos == false ){ // Si no se han enfrentado entre todos
						iteratorIguales = etIguales.iterator();	
						List<EquipoTorneo> listaCociente = new ArrayList<EquipoTorneo>();
												
						if(reglas.equals("ACB")){
							compara = new ACBEquipoTorneoDosComparator();
						}
						else if(reglas.equals("LFP")){
							compara = new LFPEquipoTorneoDosComparator();
						}
						else if(reglas.equals("EPL")){
							compara = new EPLEquipoTorneoDosComparator();
						}
						
						while(iteratorIguales.hasNext()){
							EquipoTorneo igual = iteratorIguales.next();

								int retorno = compara.compare(igual, eqTo);
								
								if((puntua && retorno > 0) || (!puntua && retorno < 0)){
									eqTo.setPosicion(igual.getPosicion());
									
									if(puntua && retorno > 0){
										igual.setPosicion(igual.getPosicion()+1);
//										System.out.println("---- Recomparando triple o más... Puntua: "
//												+eqTo.getId().getParentEquipo().getNombre()+" sube una posición ----");
									}
									else{
										igual.setPosicion(igual.getPosicion()-1);
//										System.out.println("---- Recomparando triple o más... Puntua: "
//												+eqTo.getId().getParentEquipo().getNombre()+" baja una posición ----");
									}

									equipoTorneoDAO.mergeEquipoTorneo(igual);																
								}
								
								else if(retorno == 0 && ptosVictoria == null){
									// Lo hago para cualquier el cociente para los que no haya puntos.															
									// Añadir a una lista
									listaCociente.add(igual);
								}	
						}
						
						if(!listaCociente.isEmpty()){
							if(listaCociente.size() == 1){
								eqTo = SacarSubDatosDosEquipoTorneo(eqTo, listaCociente, ptosVictoria);
								
								EquipoTorneo et = listaCociente.get(0);
								
								int retorno = AverageGlobalEquipoTorneo.Cociente(eqTo.getTantosFavor(), eqTo.getTantosContra(),
																		et.getTantosFavor(), et.getTantosContra());
								
								if((puntua && retorno > 0) || (!puntua && retorno < 0)){
									eqTo.setPosicion(et.getPosicion());
									
									if(puntua && retorno > 0){
										et.setPosicion(et.getPosicion()+1);
//										System.out.println("---- Recomparando triple o más... Puntua: "
//												+eqTo.getId().getParentEquipo().getNombre()+" sube una posición ----");
									}
									else{
										et.setPosicion(et.getPosicion()-1);
//										System.out.println("---- Recomparando triple o más... Puntua: "
//												+eqTo.getId().getParentEquipo().getNombre()+" baja una posición ----");
									}

									equipoTorneoDAO.mergeEquipoTorneo(et);
								}
								
								eqTo = equipoTorneoDAO.mergeEquipoTorneo(eqTo);
							}
							else{
								eqTo = SacarSubDatosMultipleEquipoTorneo(eqTo, listaCociente, ptosVictoria);
								
								listaCociente.add(eqTo);
								
								Collections.sort(listaCociente, new CocienteEquipoTorneoComparator());
								
								int posi = FuncionesAuxiliares.SacarPosicionMasAltaEquipoTorneo(listaCociente);
								int i = 0;
						
								Iterator<EquipoTorneo> itCoc = listaCociente.iterator();
								
								while(itCoc.hasNext()){
									EquipoTorneo userT = itCoc.next();
									
									userT.setPosicion(posi + i);
									
									equipoTorneoDAO.mergeEquipoTorneo(userT);
								}
							}
						}
						
						eqTo = equipoTorneoDAO.mergeEquipoTorneo(eqTo);
					}
					
					else{ // Si se han enfrentado entre todos, AQUI ESTÁ LA CHICHA !!!!
						eqTo = SacarSubDatosMultipleEquipoTorneo(eqTo, etIguales, ptosVictoria);
						Comparator<EquipoTorneo> comparaDos = null;
						
						// Se supone que ya está bien creada la lista de los subvalores, toca ordenarla con un comparator
						etIguales.add(eqTo);
						// Seleccionamos el comparador y comparamos
						if(reglas.equals("ACB")){
							compara = new ACBEquipoTorneoMultipleFinalComparator();
							comparaDos = new ACBEquipoTorneoDosFinalComparator();
						}
						else if(reglas.equals("LFP")){
							compara = new LFPEquipoTorneoSubComparator();
							comparaDos = compara;
						}
						else if(reglas.equals("EPL")){
							compara = new EPLEquipoTorneoSubComparator();
						}
						
						ComparacionRecursiva(etIguales,compara,comparaDos,FuncionesAuxiliares.SacarPosicionMasAltaEquipoTorneo(etIguales),
								ptosVictoria);
					}
				}
			}
			eqTo = equipoTorneoDAO.mergeEquipoTorneo(eqTo);
		}
		
		else{
			eqTo = equipoTorneoDAO.mergeEquipoTorneo(eqTo);
		}
		
		return eqTo;
	}
	
	
	/**
	 * Realiza una comparación recursiva para varios equipos empatados
	 * @param utIguales Equipos a comparar
	 * @param compara Comparator para un número de equipos mayor a dos
	 * @param comparaDos Comparator para dos equipos
	 * @param posicionMasAlta Número de la posición más alta de los equipos implicados
	 * @param ptosVictoria Número de puntos otorgados por la victoria
	 */
	void ComparacionRecursiva (List<EquipoTorneo> utIguales, Comparator<EquipoTorneo> compara, Comparator<EquipoTorneo> comparaDos,
							Integer posicionMasAlta, Integer ptosVictoria){		
		Collections.sort(utIguales, compara); // Colocamos la lista
		
		Iterator<EquipoTorneo> it = utIguales.iterator();
		
		while(it.hasNext()){
			EquipoTorneo ut = it.next();
			
			List<EquipoTorneo> listaEmpatados = new ArrayList<EquipoTorneo>();
			
			Iterator<EquipoTorneo> itRep = it;
			
			while(itRep.hasNext()){
				EquipoTorneo utRep = itRep.next();
				
				if(compara.compare(ut, utRep) != 0){
					break;
				}
				else{
					it.next(); // Pasamos para q no lo vuelva a comparar fuera
					listaEmpatados.add(utRep);
				}
			}
			
			equipoTorneoDAO = new EquipoTorneoDAOImpl();
			
			if(listaEmpatados.isEmpty()){								
				ut.setPosicion(utIguales.indexOf(ut) + posicionMasAlta); // IndexOf suponiendo que el primero es 0
				
				equipoTorneoDAO.mergeEquipoTorneo(ut);
			}
			else{
				int posi = utIguales.indexOf(ut); // ut va antes de utRival, evidentemente.
				
				if(listaEmpatados.size() == 1){ // Si solo son dos los equipos...
					ut = SacarSubDatosDosEquipoTorneo(ut, listaEmpatados, ptosVictoria);
					
					EquipoTorneo utRival = listaEmpatados.get(0);	
					
					if(comparaDos.compare(ut, utRival) >= 0 && utRival.getPosicion() - ut.getPosicion() > 0){
						ut.setPosicion(posi + posicionMasAlta);
						utRival.setPosicion(posi + 1 + posicionMasAlta);						
					}
					
					else if(comparaDos.compare(ut, utRival) < 0 && utRival.getPosicion() - ut.getPosicion() < 0){
						utRival.setPosicion(posi + posicionMasAlta);
						ut.setPosicion(posi + 1 + posicionMasAlta);
					}

					equipoTorneoDAO.mergeEquipoTorneo(ut);
					equipoTorneoDAO.mergeEquipoTorneo(utRival);
				}
				else{
					ut = SacarSubDatosMultipleEquipoTorneo(ut, listaEmpatados, ptosVictoria);
					
					// Ahora a comparar... RECURSIVIDAD !!! Cuidadito con los bucles infinitos !!
					// Entiendo que haciendo la comparacion ya no habrá bucles infinitos
					listaEmpatados.add(ut);
					
					if(listaEmpatados.size() != utIguales.size()){
						ComparacionRecursiva(listaEmpatados,compara, comparaDos, FuncionesAuxiliares.SacarPosicionMasAltaEquipoTorneo(utIguales),
								ptosVictoria);	
					}
					else{
						Collections.sort(listaEmpatados,compara); // Colocamos
						
						Iterator<EquipoTorneo> itComp = listaEmpatados.iterator();
						int i=0;
						
						while(itComp.hasNext()){
							EquipoTorneo utComp = itComp.next();
							
							utComp.setPosicion(posi + posicionMasAlta + i);
							equipoTorneoDAO.mergeEquipoTorneo(utComp);
							
							i++;
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * Saca los datos entre dos equipos para realizar la comparación
	 * @param eqTo Objeto EquipoTorneo del equipo
	 * @param etIguales Equipos para comparar, solo será uno
	 * @param ptosVictoria Puntos otorgados por la victoria
	 * @return Objeto EquipoTorneo del equipo actualizado
	 */
	EquipoTorneo SacarSubDatosDosEquipoTorneo(EquipoTorneo eqTo, List<EquipoTorneo> etIguales, Integer ptosVictoria){
		partidoDAO = new PartidoDAOImpl();
		
		eqTo = LimpiarSubDatosEquipoTorneo(eqTo, etIguales); // Limpiamos los subdatos
		
		EquipoTorneo et = etIguales.get(0);
		
		List<PartidoEquiposVersus> partidosEqTo = partidoDAO.PartidosEquiposTorneo(eqTo, et);
		Iterator<PartidoEquiposVersus> itPEqTo = partidosEqTo.iterator();
		
		List<PartidoEquiposVersus> partidosEqRival = partidoDAO.PartidosEquiposTorneo(et, eqTo);
		Iterator<PartidoEquiposVersus> itPEtRival = partidosEqRival.iterator();
		
		while(itPEqTo.hasNext() || itPEtRival.hasNext()){
			PartidoEquiposVersus partEqTo;
			PartidoEquiposVersus partEtRival;
			
			if(itPEqTo.hasNext()){
				partEqTo = itPEqTo.next();
				
				eqTo.setSubTantosFavor(eqTo.getSubTantosFavor() + partEqTo.getPuntosLocal());
				eqTo.setSubTantosContra(eqTo.getSubTantosContra() + partEqTo.getPuntosVisitante());
				et.setSubTantosFavor(et.getSubTantosFavor() + partEqTo.getPuntosVisitante());
				et.setSubTantosContra(et.getSubTantosContra() + partEqTo.getPuntosLocal());
				
				
				
				if(partEqTo.getPuntosLocal() - partEqTo.getPuntosVisitante() > 0){ // Si gana eqTo
					eqTo.setSubPartidosGanados(eqTo.getSubPartidosGanados() + 1);
					if(ptosVictoria != null){
						eqTo.setSubPuntos(eqTo.getSubPuntos() + ptosVictoria);	
					}
				}
				else if(partEqTo.getPuntosLocal() - partEqTo.getPuntosVisitante() == 0){ // Empate
					if(ptosVictoria != null){
						eqTo.setSubPuntos(eqTo.getSubPuntos() + 1);
						et.setSubPuntos(et.getSubPuntos() + 1);
					}
				}
					else{ // Si pierde
						et.setSubPartidosGanados(et.getSubPartidosGanados() + 1);
						if(ptosVictoria != null){
							et.setSubPuntos(et.getSubPuntos() + ptosVictoria);	
						}
					}
			}
			
			if(itPEtRival.hasNext()){
				partEtRival = itPEtRival.next();

				et.setSubTantosFavor(et.getSubTantosFavor() + partEtRival.getPuntosLocal());
				et.setSubTantosContra(et.getSubTantosContra() + partEtRival.getPuntosVisitante());
				eqTo.setSubTantosFavor(eqTo.getSubTantosFavor() + partEtRival.getPuntosVisitante());
				eqTo.setSubTantosContra(eqTo.getSubTantosContra() + partEtRival.getPuntosLocal());
				
				if(partEtRival.getPuntosLocal() - partEtRival.getPuntosVisitante() > 0){ // Si gana eqTo
					et.setSubPartidosGanados(et.getSubPartidosGanados() + 1);
					if(ptosVictoria != null){
						et.setSubPuntos(et.getSubPuntos() + ptosVictoria);	
					}
				}
				else if(partEtRival.getPuntosLocal() - partEtRival.getPuntosVisitante() == 0){ // Empate
					if(ptosVictoria != null){
						eqTo.setSubPuntos(eqTo.getSubPuntos() + 1);
						et.setSubPuntos(et.getSubPuntos() + 1);
					}
				}
				else{ // Si pierde
					eqTo.setSubPartidosGanados(eqTo.getSubPartidosGanados() + 1);
					if(ptosVictoria != null){
						eqTo.setSubPuntos(eqTo.getSubPuntos() + ptosVictoria);	
					}
				}
			}
		}
		
		etIguales.set(0, et);
		
		return eqTo;
	}
	
	
	/**
	 * Saca los datos entre más de dos equipos para realizar la comparación
	 * @param eqTo Objeto EquipoTorneo del equipo
	 * @param etIguales Equipos para comparar
	 * @param ptosVictoria Puntos otorgados por la victoria
	 * @return Objeto EquipoTorneo del equipo actualizado
	 */
	EquipoTorneo SacarSubDatosMultipleEquipoTorneo(EquipoTorneo eqTo, List<EquipoTorneo> etIguales, Integer ptosVictoria){
		Integer index = null;
		
		partidoDAO = new PartidoDAOImpl();
		
		eqTo = LimpiarSubDatosEquipoTorneo(eqTo, etIguales); // Limpiamos los subdatos
		
		Iterator<EquipoTorneo> itUT = etIguales.iterator();
								
		while(itUT.hasNext()){
			EquipoTorneo etRival = itUT.next();
			
			index = etIguales.indexOf(etRival);
			
			List<PartidoEquiposVersus> partidosEqTo = partidoDAO.PartidosEquiposTorneo(eqTo, etRival);
			Iterator<PartidoEquiposVersus> itPEqTo = partidosEqTo.iterator();
			
			List<PartidoEquiposVersus> partidosEtRival = partidoDAO.PartidosEquiposTorneo(etRival, eqTo);
			Iterator<PartidoEquiposVersus> itPEtRival = partidosEtRival.iterator();
			
			while(itPEqTo.hasNext() || itPEtRival.hasNext()){
				PartidoEquiposVersus partEqTo;
				PartidoEquiposVersus partEtRival;
				
				if(itPEqTo.hasNext()){
					partEqTo = itPEqTo.next();
					
					eqTo.setSubTantosFavor(eqTo.getSubTantosFavor() + partEqTo.getPuntosLocal());
					eqTo.setSubTantosContra(eqTo.getSubTantosContra() + partEqTo.getPuntosVisitante());
					etRival.setSubTantosFavor(etRival.getSubTantosFavor() + partEqTo.getPuntosVisitante());
					etRival.setSubTantosContra(etRival.getSubTantosContra() + partEqTo.getPuntosLocal());
					
					if(partEqTo.getPuntosLocal() - partEqTo.getPuntosVisitante() > 0){ // Si gana eqTo
						eqTo.setSubPartidosGanados(eqTo.getSubPartidosGanados() + 1);
						if(ptosVictoria != null){
							eqTo.setSubPuntos(eqTo.getSubPuntos() + ptosVictoria);	
						}
						
					}
					else if(partEqTo.getPuntosLocal() - partEqTo.getPuntosVisitante() == 0){ // Empate
							if(ptosVictoria != null){
								eqTo.setSubPuntos(eqTo.getSubPuntos() + 1);
								etRival.setSubPuntos(etRival.getSubPuntos() + 1);
							}
					}
						else{ // Si pierde
							etRival.setSubPartidosGanados(etRival.getSubPartidosGanados() + 1);
							if(ptosVictoria != null){
								etRival.setSubPuntos(etRival.getSubPuntos() + ptosVictoria);	
							}
					}								
				}
				
				
				
				if(itPEtRival.hasNext()){
					partEtRival = itPEtRival.next();

					etRival.setSubTantosFavor(etRival.getSubTantosFavor() + partEtRival.getPuntosLocal());
					etRival.setSubTantosContra(etRival.getSubTantosContra() + partEtRival.getPuntosVisitante());
					eqTo.setSubTantosFavor(eqTo.getSubTantosFavor() + partEtRival.getPuntosVisitante());
					eqTo.setSubTantosContra(eqTo.getSubTantosContra() + partEtRival.getPuntosLocal());
					
					if(partEtRival.getPuntosLocal() - partEtRival.getPuntosVisitante() > 0){ // Si gana etRival
						etRival.setSubPartidosGanados(etRival.getSubPartidosGanados() + 1);
						if(ptosVictoria != null){
							etRival.setSubPuntos(etRival.getSubPuntos() + ptosVictoria);	
						}
						
					}
					else if(partEtRival.getPuntosLocal() - partEtRival.getPuntosVisitante() == 0){ // Empate
							if(ptosVictoria != null){
								etRival.setSubPuntos(etRival.getSubPuntos() + 1);
								eqTo.setSubPuntos(eqTo.getSubPuntos() + 1);
							}
					}
						else{ // Si pierde
							eqTo.setSubPartidosGanados(eqTo.getSubPartidosGanados() + 1);
							if(ptosVictoria != null){
								eqTo.setSubPuntos(eqTo.getSubPuntos() + ptosVictoria);	
							}
					}
				}
																												
												
				
				
				for(int i=index+1; etIguales.get(i) != null; i++){
					EquipoTorneo etContrincante = etIguales.get(i);
					
					List<PartidoEquiposVersus> partidosSubEtContrincante = partidoDAO.PartidosEquiposTorneo(etContrincante,
																										etRival);
					Iterator<PartidoEquiposVersus> itPSubCont = partidosSubEtContrincante.iterator();
					
					List<PartidoEquiposVersus> partidosSubEtRival = partidoDAO.PartidosEquiposTorneo(etRival,
																										etContrincante);
					Iterator<PartidoEquiposVersus> itPSubRival = partidosSubEtRival.iterator();
					
					while(itPSubCont.hasNext() || itPSubRival.hasNext()){
						PartidoEquiposVersus partCont;
						PartidoEquiposVersus partRival;
						
						if(itPSubCont.hasNext()){
							partCont = itPSubCont.next();
							
							etContrincante.setSubTantosFavor(etContrincante.getSubTantosFavor() +
															partCont.getPuntosLocal());
							etContrincante.setSubTantosContra(etContrincante.getSubTantosContra() + 
															partCont.getPuntosVisitante());
							etRival.setSubTantosFavor(etRival.getSubTantosFavor() + 
															partCont.getPuntosVisitante());
							etRival.setSubTantosContra(etRival.getSubTantosContra() + 
															partCont.getPuntosLocal());
							
							if(partCont.getPuntosLocal() - partCont.getPuntosVisitante() > 0){ // Si gana etContrincante
								etContrincante.setSubPartidosGanados(etContrincante.getSubPartidosGanados() + 1);
								if(ptosVictoria != null){
									etContrincante.setSubPuntos(etContrincante.getSubPuntos() + ptosVictoria);
								}												
							}
							else if(partCont.getPuntosLocal() - partCont.getPuntosVisitante() == 0){ // Empate
								if(ptosVictoria != null){
									etContrincante.setSubPuntos(etContrincante.getSubPuntos() + 1);
									etRival.setSubPuntos(etRival.getSubPuntos() + 1);
								}
							}
								else{ // Si pierde
									etRival.setSubPartidosGanados(etRival.getSubPartidosGanados() + 1);
									if(ptosVictoria != null){
										etRival.setSubPuntos(etRival.getSubPuntos() + ptosVictoria);
									}
								}
						}
						
						if(itPSubRival.hasNext()){
							partRival = itPSubRival.next();
							
							etRival.setSubTantosFavor(etRival.getSubTantosFavor() + 
															partRival.getPuntosLocal());
							etRival.setSubTantosContra(etRival.getSubTantosContra() + 
															partRival.getPuntosVisitante());
							etContrincante.setSubTantosFavor(etContrincante.getSubTantosFavor() + 
															partRival.getPuntosVisitante());
							etContrincante.setSubTantosContra(etContrincante.getSubTantosContra() + 
															partRival.getPuntosLocal());
							
							if(partRival.getPuntosLocal() - partRival.getPuntosVisitante() > 0){ // Si gana etRival
								etRival.setSubPartidosGanados(etRival.getSubPartidosGanados() + 1);
								if(ptosVictoria != null){
									etRival.setSubPuntos(etRival.getSubPuntos() + ptosVictoria);													
								}												
							}
							else if(partRival.getPuntosLocal() - partRival.getPuntosVisitante() == 0){ // Empate
								etRival.setSubPuntos(etRival.getSubPuntos() + 1);
								if(ptosVictoria != null){
									etRival.setSubPuntos(etRival.getSubPuntos() + 1);
									etContrincante.setSubPuntos(etContrincante.getSubPuntos() + 1);
								}
							}
								else{ // Si pierde
									etContrincante.setSubPartidosGanados(etContrincante.getSubPartidosGanados() + 1);
									if(ptosVictoria != null){
										etContrincante.setSubPuntos(etContrincante.getSubPuntos() + ptosVictoria);
									}
								}
						}	
					}
					
					etIguales.set(i, etContrincante); // Seteamos etContrincante en la lista
				}
			}
			
			etIguales.set(index, etRival); // Seteamos etRival en la lista
		}
		
		return eqTo;
	}
	
	/**
	 * Pone a cero todos los valores de subcomparación
	 * @param eqTo Objeto EquipoTorneo del equipo
	 * @param etIguales Equipos a comparar
	 * @return Objeto EquipoTorneo actualizado
	 */
	EquipoTorneo LimpiarSubDatosEquipoTorneo(EquipoTorneo eqTo, List<EquipoTorneo> etIguales){
		Iterator<EquipoTorneo> it = etIguales.iterator();
		int index=0;
		
		while(it.hasNext()){
			EquipoTorneo et = it.next();
			
			et.setSubPartidosGanados(0);
			et.setSubPuntos(0);
			et.setSubTantosFavor(0);
			et.setSubTantosContra(0);
			
			etIguales.set(index, et);
			
			index++;
		}
		
		eqTo.setSubPartidosGanados(0);
		eqTo.setSubPuntos(0);
		eqTo.setSubTantosFavor(0);
		eqTo.setSubTantosContra(0);
		
		return eqTo;
	}
	
	/**
	 * Saca el cociente de los tantos a favor y en contra
	 * en una competición de un equipo
	 * @param et Objeto EquipoTorneo del equipo
	 * @return Cociente resultante
	 */
	float SacarCociente(EquipoTorneo et){
		if(et.getTantosFavor() == 0){
			return (float) 0;
		}
		else if(et.getTantosContra() == 0){
			return Float.POSITIVE_INFINITY;
		}
		else{
			return (float) (et.getTantosFavor()/et.getTantosContra());
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
