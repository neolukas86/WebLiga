package com.proyecto.actions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.proyecto.dao.EquipoDAO;
import com.proyecto.dao.EquipoDAOImpl;
import com.proyecto.dao.EquipoTorneoDAO;
import com.proyecto.dao.EquipoTorneoDAOImpl;
import com.proyecto.dao.NoticiaDAO;
import com.proyecto.dao.NoticiaDAOImpl;
import com.proyecto.dao.PartidoDAO;
import com.proyecto.dao.PartidoDAOImpl;
import com.proyecto.dao.UserDAO;
import com.proyecto.dao.UserDAOImpl;
import com.proyecto.dao.UserEquipoDAO;
import com.proyecto.dao.UserEquipoDAOImpl;
import com.proyecto.dominio.Actividad;
import com.proyecto.dominio.CategoriaNoticia;
import com.proyecto.dominio.Comunidad;
import com.proyecto.dominio.Equipo;
import com.proyecto.dominio.EquipoComunidad;
import com.proyecto.dominio.EquipoTorneo;
import com.proyecto.dominio.EquipoTorneoPK;
import com.proyecto.dominio.IntegerInteger;
import com.proyecto.dominio.Noticia;
import com.proyecto.dominio.PartidoEquiposVersus;
import com.proyecto.dominio.Plataforma;
import com.proyecto.dominio.Torneo;
import com.proyecto.dominio.User;
import com.proyecto.dominio.UserComunidad;
import com.proyecto.dominio.UserEquipo;
import com.proyecto.dominio.UserEquipoPK;
import com.proyecto.dominio.UserTorneo;
import com.proyecto.util.displaytag.Paginate;
import com.proyecto.util.encriptado.StringEncrypter;
import com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares;
import com.proyecto.util.hibernate.HibernateUtil;
import com.proyecto.util.listas.ActividadComparator;
import com.proyecto.util.listas.ListHashMapUtil;
import com.proyecto.util.listas.PlataformaComparator;
import com.proyecto.util.properties.GlobalResourceUtil;
import com.proyecto.util.properties.PropertiesUtil;

/**
* Esta clase implementa el Action asociado al
* equipo.
* @author Lucas Sánchez López
* @version 3.0
*/

public class EquipoAction extends ActionSupport implements ModelDriven<Equipo>, Preparable, SessionAware{
	private static final long serialVersionUID = 1L;
	private Equipo equipo = new Equipo();
	private User user = new User();
	private List<Integer> deportes;
	private List<Integer> cartas;
	private List<Integer> juegos;
	private List<Integer> juegosdemesa;
	private List<Integer> plataformasdejuegos;
	private List<Actividad> actividadList;
	private List<Plataforma> plataformaList;
	private List<Integer> defaultActividades;
	private List<Integer> defaultPlataformas;
	private Integer deporte;
	private Integer carta;
	private Integer juego;
	private Integer juegodemesa;
	private Integer plataforma;
	private ListHashMapUtil listHashMapUtil;
	private EquipoDAO equipoDAO;
	private UserEquipoDAO userEquipoDAO;
	private UserDAO userDAO;
	private NoticiaDAO noticiaDAO;
	private PartidoDAO partidoDAO;
	private EquipoTorneoDAO equipoTorneoDAO;
	private List<Equipo> equipoList;
	private List<User> userList;
	private List<UserEquipo> userEquipoList;
	private LinkedHashMap<Integer,String> listaDeportes = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaCartas = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaJuegos = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaJuegosdemesa = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaPlataformas = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Boolean,String> listaBoolean = new LinkedHashMap<Boolean,String>();
	private LinkedHashMap<Integer,String> listaBooleanInteger = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaRangos = new LinkedHashMap<Integer,String>();
	private List<IntegerInteger> listTorneosNoComenzados = new ArrayList<IntegerInteger>();
	private List<IntegerInteger> listTorneosEnJuego = new ArrayList<IntegerInteger>();
	private List<IntegerInteger> listTorneosFinalizados = new ArrayList<IntegerInteger>();
	private List<IntegerInteger> listUsuarios = new ArrayList<IntegerInteger>();
	private List<IntegerInteger> listComunidades = new ArrayList<IntegerInteger>();
	private Paginate equipoPaginatedList;
	private Paginate userPaginatedList;
	private Paginate noticiaPaginatedList;
	private Map sesion;
	private String rolequipo;
	private Integer rolnivelEquipo = FuncionesAuxiliares.NUMERO_NO_VALIDO;
	private Boolean requestPendiente;
	private Integer iduser;
	private Integer rangoEq;
	private Integer entero;
	private Integer idtorneo;
	private String tipo;
	private String cadena;
	private String tab;
	private String subTab;
	
	
	SessionFactory sessionFactory = null;
	
	@SessionTarget
	Session session = null;

	@Override
	public Equipo getModel() {
		// TODO Auto-generated method stub
		return equipo;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	
	public List<Equipo> getEquipoList() {
		return equipoList;
	}

	public void setEquipoList(List<Equipo> equipoList) {
		this.equipoList = equipoList;
	}

//////////////////////////////////////////////////////////
	
	
	public List<User> getUserList() {
		return userList;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	public String getSubTab() {
		return subTab;
	}

	public void setSubTab(String subTab) {
		this.subTab = subTab;
	}

	public List<IntegerInteger> getListTorneosNoComenzados() {
		return listTorneosNoComenzados;
	}

	public void setListTorneosNoComenzados(
			List<IntegerInteger> listTorneosNoComenzados) {
		this.listTorneosNoComenzados = listTorneosNoComenzados;
	}

	public List<IntegerInteger> getListTorneosEnJuego() {
		return listTorneosEnJuego;
	}

	public void setListTorneosEnJuego(List<IntegerInteger> listTorneosEnJuego) {
		this.listTorneosEnJuego = listTorneosEnJuego;
	}

	public List<IntegerInteger> getListTorneosFinalizados() {
		return listTorneosFinalizados;
	}

	public void setListTorneosFinalizados(
			List<IntegerInteger> listTorneosFinalizados) {
		this.listTorneosFinalizados = listTorneosFinalizados;
	}

	public List<IntegerInteger> getListUsuarios() {
		return listUsuarios;
	}

	public void setListUsuarios(List<IntegerInteger> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}

	public List<IntegerInteger> getListComunidades() {
		return listComunidades;
	}

	public void setListComunidades(List<IntegerInteger> listComunidades) {
		this.listComunidades = listComunidades;
	}

	public List<Actividad> getActividadList() {
		return actividadList;
	}

	public void setActividadList(List<Actividad> actividadList) {
		this.actividadList = actividadList;
	}

	public List<Plataforma> getPlataformaList() {
		return plataformaList;
	}

	public void setPlataformaList(List<Plataforma> plataformaList) {
		this.plataformaList = plataformaList;
	}

	public Integer getRangoEq() {
		return rangoEq;
	}

	public void setRangoEq(Integer rangoEq) {
		this.rangoEq = rangoEq;
	}

	public LinkedHashMap<Integer, String> getListaRangos() {
		return listaRangos;
	}

	public void setListaRangos(LinkedHashMap<Integer, String> listaRangos) {
		this.listaRangos = listaRangos;
	}

	public LinkedHashMap<Integer, String> getListaBooleanInteger() {
		return listaBooleanInteger;
	}

	public void setListaBooleanInteger(
			LinkedHashMap<Integer, String> listaBooleanInteger) {
		this.listaBooleanInteger = listaBooleanInteger;
	}

	public Integer getIduser() {
		return iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Integer> getDeportes() {
		return deportes;
	}
	
	public void setDeportes(List<Integer> deportes) {
		this.deportes = deportes;
	}

	public List<Integer> getCartas() {
		return cartas;
	}

	public void setCartas(List<Integer> cartas) {
		this.cartas = cartas;
	}

	public List<Integer> getJuegos() {
		return juegos;
	}

	public void setJuegos(List<Integer> juegos) {
		this.juegos = juegos;
	}

	public List<Integer> getJuegosdemesa() {
		return juegosdemesa;
	}	

	public List<UserEquipo> getUserEquipoList() {
		return userEquipoList;
	}

	public void setUserEquipoList(List<UserEquipo> userEquipoList) {
		this.userEquipoList = userEquipoList;
	}

	public void setJuegosdemesa(List<Integer> juegosdemesa) {
		this.juegosdemesa = juegosdemesa;
	}

	public List<Integer> getPlataformasdejuegos() {
		return plataformasdejuegos;
	}

	public void setPlataformasdejuegos(List<Integer> plataformasdejuegos) {
		this.plataformasdejuegos = plataformasdejuegos;
	}
	
	public Paginate getNoticiaPaginatedList() {
		return noticiaPaginatedList;
	}

	public void setNoticiaPaginatedList(Paginate noticiaPaginatedList) {
		this.noticiaPaginatedList = noticiaPaginatedList;
	}

	public Integer getDeporte() {
		return deporte;
	}

	public void setDeporte(Integer deporte) {
		this.deporte = deporte;
	}

	public Integer getIdtorneo() {
		return idtorneo;
	}

	public void setIdtorneo(Integer idtorneo) {
		this.idtorneo = idtorneo;
	}

	
	
	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public Integer getCarta() {
		return carta;
	}

	public void setCarta(Integer carta) {
		this.carta = carta;
	}

	public Integer getJuego() {
		return juego;
	}

	public void setJuego(Integer juego) {
		this.juego = juego;
	}

	public Integer getJuegodemesa() {
		return juegodemesa;
	}

	public void setJuegodemesa(Integer juegodemesa) {
		this.juegodemesa = juegodemesa;
	}

	
	public Integer getRolnivelEquipo() {
		return rolnivelEquipo;
	}

	public void setRolnivelEquipo(Integer rolnivelEquipo) {
		this.rolnivelEquipo = rolnivelEquipo;
	}

	public Integer getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Integer plataforma) {
		this.plataforma = plataforma;
	}

	public LinkedHashMap<Integer, String> getListaDeportes() {
		return listaDeportes;
	}

	public void setListaDeportes(LinkedHashMap<Integer, String> listaDeportes) {
		this.listaDeportes = listaDeportes;
	}

	public LinkedHashMap<Integer, String> getListaCartas() {
		return listaCartas;
	}

	public void setListaCartas(LinkedHashMap<Integer, String> listaCartas) {
		this.listaCartas = listaCartas;
	}

	public LinkedHashMap<Integer, String> getListaJuegos() {
		return listaJuegos;
	}

	public void setListaJuegos(LinkedHashMap<Integer, String> listaJuegos) {
		this.listaJuegos = listaJuegos;
	}

	public LinkedHashMap<Integer, String> getListaJuegosdemesa() {
		return listaJuegosdemesa;
	}

	public void setListaJuegosdemesa(LinkedHashMap<Integer, String> listaJuegosdemesa) {
		this.listaJuegosdemesa = listaJuegosdemesa;
	}

	public LinkedHashMap<Integer, String> getListaPlataformas() {
		return listaPlataformas;
	}

	public void setListaPlataformas(LinkedHashMap<Integer, String> listaPlataformas) {
		this.listaPlataformas = listaPlataformas;
	}

	public Paginate getEquipoPaginatedList() {
		return equipoPaginatedList;
	}

	public void setEquipoPaginatedList(Paginate equipoPaginatedList) {
		this.equipoPaginatedList = equipoPaginatedList;
	}

	public String getRolequipo() {
		return rolequipo;
	}

	public void setRolequipo(String rolequipo) {
		this.rolequipo = rolequipo;
	}

	public Integer getEntero() {
		return entero;
	}

	public void setEntero(Integer entero) {
		this.entero = entero;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Paginate getUserPaginatedList() {
		return userPaginatedList;
	}

	public void setUserPaginatedList(Paginate userPaginatedList) {
		this.userPaginatedList = userPaginatedList;
	}

	public LinkedHashMap<Boolean, String> getListaBoolean() {
		return listaBoolean;
	}

	public void setListaBoolean(LinkedHashMap<Boolean, String> listaBoolean) {
		this.listaBoolean = listaBoolean;
	}

	public Boolean getRequestPendiente() {
		return requestPendiente;
	}

	public void setRequestPendiente(Boolean requestPendiente) {
		this.requestPendiente = requestPendiente;
	}
	
	public List<Integer> getDefaultActividades() {
		Set<Actividad> setA = equipo.getActividades();		
		List<Integer> lista = new ArrayList<Integer>();
		
		if(setA != null && !setA.isEmpty()){
			Iterator<Actividad> it = setA.iterator();
			while(it.hasNext()){
				lista.add(it.next().getId());
			}
		}
		
		return lista;
	}

	public void setDefaultActividades(List<Integer> defaultActividades) {
		this.defaultActividades = defaultActividades;
	}
	
	public List<Integer> getDefaultPlataformas() {
		Set<Plataforma> setP = equipo.getPlataformas();
		List<Integer> lista = new ArrayList<Integer>();
		
		if(setP != null && !setP.isEmpty()){
			Iterator<Plataforma> it = setP.iterator();
			while(it.hasNext()){
				lista.add(it.next().getId());
			}
		}
		
		return lista;
	}

	public void setDefaultPlataformas(List<Integer> defaultPlataformas) {
		this.defaultPlataformas = defaultPlataformas;
	}

	////////////////////////////////////////////////////////
	
	
	/**
	 * Nos lleva a la zona de busqueda de equipos
	 * @return SUCCESS
	 */
	public String GoSearch(){
		return SUCCESS;
	}
	
	/**
	 * Nos lleva al registro de un equipo
	 * cambiando a perfil de usuario si fuese necesario
	 * @return SUCCESS
	 */
	public String GoRegister(){
		getSession().put("equipo", 0);
		
		return SUCCESS;
	}
	
	/**
	 * Nos lleva a la zona de información del equipo
	 * @return SUCCESS en caso de éxito, y ERROR en caso de que ocurra algún fallo
	 */
	public String GoInfoEquipo(){
		if(SUCCESS == SetearEquipo(RecuperarEquipoDetallado(null))){
			actividadList = new ArrayList<Actividad>(equipo.getActividades());
			
			Collections.sort(actividadList,new ActividadComparator());
			
			plataformaList = new ArrayList<Plataforma>(equipo.getPlataformas());
			
			Collections.sort(plataformaList,new PlataformaComparator());
			
			return UserListByEquipo();
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la zona de noticias del equipo
	 * @return SUCCESS en caso de éxito, y ERROR en caso de que ocurra algún fallo
	 */
	public String GoNoticiasEquipo(){
		if( SUCCESS == SetearEquipo(RecuperarEquipoDetallado(null))){
			String aliasTable = "noti";
			
			String where = " where "+aliasTable+".parentEquipo="+equipo.getId();
			String tablasAux = ", CategoriaNoticia as cat ";
			where += " and cat.nombre IN ";
			where += "('AltaUsuarioEquipo','BajaUsuarioEquipo','BaneadoUsuarioEquipo','ExpulsadoUsuarioEquipo'," +
					"'RegistrosAbiertosEquipo','RegistrosCerradosEquipo','PasswordEquipoCambiado'," +
					"'RangoUsuarioEquipoCambiado','EquipoCreado','EquipoEliminado'," +
					"'ConfirmacionRegistroEquipoOn','ConfirmacionRegistroEquipoOff'," +
					"'AltaEquipoTorneo','AltaEquipoComunidad','BajaEquipoTorneo','BajaEquipoComunidad'," +
					"'BaneadoEquipoComunidad','BaneadoEquipoTorneo','ExpulsadoEquipoComunidad','ExpulsadoEquipoTorneo'";
			
			if(SUCCESS == FuncionesAuxiliares.ComprobarPermisos(rolnivelEquipo, FuncionesAuxiliares.MOD)){
				where += ",'AceptadoRequestUsuarioEquipo','DenegadoRequestUsuarioEquipo','RequestUsuarioEquipo'";
			}
			
			where += ")";
			where += " and "+aliasTable+" in elements(cat.noticias) order by noti.fechaPublicacion desc";
			
			
			
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			
			noticiaDAO = new NoticiaDAOImpl();
			noticiaPaginatedList = noticiaDAO.listNoticia(request, aliasTable, where, tablasAux);
			
			tipo = "equipo";
			
			return SUCCESS;
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la zona de de unirse al equipo
	 * @return SUCCESS en caso de éxito, y ERROR en caso de que ocurra algún fallo
	 */
	public String GoJoinEquipo(){
		if(SUCCESS == SetearEquipo(RecuperarEquipoDetallado(null))){
			if(rolnivelEquipo == null || rolnivelEquipo == FuncionesAuxiliares.EXMIEMBRO){
				if(equipo.isRegRequest()){
					User us = (User)session.load(User.class,(Serializable) sesion.get("id"));
					Set<Equipo> setE = us.getEquiposRequest();
							
					if((setE != null && !setE.isEmpty()) && setE.contains(equipo)){ // Si ya tiene una request pendiente
						requestPendiente = true;
					}
					else{
						requestPendiente = false;
					}
					
					return SUCCESS;
				}
				else{
					return SUCCESS;
				}
			}
			else{
				return ERROR;
			}
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la zona de abandono del equipo
	 * @return SUCCESS en caso de éxito, y ERROR en caso de que ocurra algún fallo
	 */
	public String GoAbandonarEquipo(){
		if(SUCCESS == SetearEquipo(RecuperarEquipoDetallado(null))){
			return FuncionesAuxiliares.ComprobarPermisos(rolnivelEquipo,FuncionesAuxiliares.NORMALUSER); // Necesitas ser al menos NORMALUSER
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la zona de administración del equipo
	 * @return SUCCESS en caso de éxito, y ERROR en caso de que ocurra algún fallo
	 */
	public String GoAdminEquipo(){
		if(SUCCESS == SetearEquipo(RecuperarEquipoDetallado(null))){
			return FuncionesAuxiliares.ComprobarPermisos(rolnivelEquipo,FuncionesAuxiliares.MOD); // Necesitas ser al menos MOD
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la zona de configuración del equipo
	 * @return SUCCESS en caso de éxito, y ERROR en caso de que ocurra algún fallo
	 */
	public String GoSettings(){
		if(SUCCESS == SetearEquipo(RecuperarEquipoDetallado(null))){
			return FuncionesAuxiliares.ComprobarPermisos(rolnivelEquipo,FuncionesAuxiliares.MOD); // Necesitas ser al menos MOD
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la zona de eliminación del equipo
	 * @return SUCCESS en caso de éxito, y ERROR en caso de que ocurra algún fallo
	 */
	public String GoEliminarEquipo(){
		if(SUCCESS == SetearEquipo(RecuperarEquipoDetallado(null))){
			return FuncionesAuxiliares.ComprobarPermisos(rolnivelEquipo,FuncionesAuxiliares.ADMIN); // Necesitas ser al menos ADMIN
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la zona de seguridad del equipo
	 * @return SUCCESS en caso de éxito, y ERROR en caso de que ocurra algún fallo
	 */
	public String GoPasswordEquipo(){
		if(SUCCESS == SetearEquipo(RecuperarEquipoDetallado(null))){
			return FuncionesAuxiliares.ComprobarPermisos(rolnivelEquipo,FuncionesAuxiliares.ADMIN); // Necesitas ser al menos ADMIN
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la zona de administración de usuarios del equipo
	 * @return SUCCESS en caso de éxito, y ERROR en caso de que ocurra algún fallo
	 */
	public String GoAdminUsuariosEquipo(){
		if(SUCCESS == SetearEquipo(RecuperarEquipoDetallado(null))){
			if( SUCCESS == FuncionesAuxiliares.ComprobarPermisos(rolnivelEquipo,FuncionesAuxiliares.MOD)){ // Necesitas ser al menos MOD		
				sesion = getSession();
				
				listHashMapUtil = new ListHashMapUtil();
				listHashMapUtil.SacarRangosComunidad(sesion,rolequipo,listaRangos);
				
				return UserListByEquipo();
			}
			else{
				return ERROR;
			}
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la zona de solicitudes de entrada al equipo
	 * @return SUCCESS en caso de éxito, y ERROR en caso de que ocurra algún fallo
	 */
	public String GoRequests(){
		if(SUCCESS == SetearEquipo(RecuperarEquipoDetallado(null))){
			if( SUCCESS == FuncionesAuxiliares.ComprobarPermisos(rolnivelEquipo,FuncionesAuxiliares.MOD)){ // Necesitas ser al menos MOD
				userList = new ArrayList<User>(equipo.getUsuariosRequest());
				
//				Iterator<User> it = userList.iterator();
//				while(it.hasNext()){
//					System.out.println("Alias --> "+it.next().getAlias());
//				}
				
				return SUCCESS;
			}
			else{
				return ERROR;
			}
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Método execute del EquipoAction
	 */
	public String execute(){ // Probando para el tabbedPanel
		return SetearEquipo(RecuperarEquipoDetallado(null));
	}
	
	/**
	 * Recupera todos los datos del equipo
	 * @param eq Equipo del que recuperar todos los datos
	 * @return Equipo recuperado
	 */
	@SuppressWarnings("finally")
	public Equipo RecuperarEquipoDetallado(Equipo eq)
	{	
		try{ 
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();

			if(eq == null){
				eq = (Equipo)session.load(Equipo.class,(Serializable)equipo.getId());
			}
			
//			SetearEquipo(eq);
			
			// -------------------------------------------
			
			User user = (User)session.load(User.class,(Integer)sesion.get("id"));
			
			UserEquipo usEq = (UserEquipo)session.get(UserEquipo.class,
					new UserEquipoPK(user,eq));
			
			if(usEq != null){
				rolnivelEquipo = usEq.getRango();
				rolequipo = FuncionesAuxiliares.StringRango(rolnivelEquipo);
			}			
		}
		catch(Exception e){
			e.printStackTrace();
			session.close();
		}
		finally{
			return eq;
		}
	}
	
	/**
	 * Setea la variable de Equipo del valuestack
	 * @param eq Equipo para el seteo
	 * @return Devuelve SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String SetearEquipo(Equipo eq){
		if(eq != null){
			equipo.setId(eq.getId());
			
			equipo.setInvitado(eq.getInvitado());
			equipo.setExpulsado(eq.getExpulsado());
		
			equipo.setNombre(eq.getNombre());
			equipo.setTag(eq.getTag());
			equipo.setHomepage(eq.getHomepage());
			
			equipo.setActividades(eq.getActividades());
			equipo.setPlataformas(eq.getPlataformas());
			equipo.setEquipoComunidades(eq.getEquipoComunidades());
			equipo.setPartidosVSGanados(eq.getPartidosVSGanados());
			equipo.setPartidosVSLocal(eq.getPartidosVSLocal());
			equipo.setPartidosVSPerdidos(eq.getPartidosVSPerdidos());
			equipo.setPartidosVSVisitante(eq.getPartidosVSVisitante());
			equipo.setEquipoTorneos(eq.getEquipoTorneos());
			equipo.setUserEquipos(eq.getUserEquipos());
			
	
			equipo.setPasswordProtected(eq.isPasswordProtected());
			equipo.setJoinPassword(eq.getJoinPassword());
			equipo.setRegRequest(eq.isRegRequest());
			equipo.setUsuariosRequest(eq.getUsuariosRequest());
			
			return SUCCESS;
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Buscar de equipos
	 * @return SUCCESS
	 */
	public String BuscarEquipo(){
		String where=" where invitado=false and expulsado=false";
		String aliasTable="eq";

		// ------------------- SearchWord -----------------------------
		if(equipo.getNombre() != null && !equipo.getNombre().trim().equals("")){
			where = " and "+aliasTable+".nombre like '%"+equipo.getNombre()+"%'";
		}
		if(equipo.getTag() != null && !equipo.getTag().trim().equals("")){			
			where += " and "+aliasTable+".tag like '%"+equipo.getTag()+"%'";
		}			
		
		//------------------ CondicionWhere ---------------------------
		///////////////////////////////////////////////////////////////
		//------------------ Extra ------------------------------------
		String tablasAux="";
		if(plataforma!=null && plataforma != 0){
			tablasAux = ",Plataforma as pl ";
			where += " and pl.id="+String.valueOf(plataforma)+" and "+aliasTable+" in elements(pl.equipos)";
		}
		
		Integer acti = null;
		if(deporte!=null && deporte!=0){
			acti = deporte;
		}
		else if(carta!=null && carta!=0){
				acti = carta;
			}
			else if(juego!=null && juego!=0){
					acti = juego;
				}
				else if(juegodemesa!=null && juegodemesa!=0){
						acti = juegodemesa;
					}
				
		if(acti != null && acti!=0){
			tablasAux += ",Actividad as act ";
			where += " and act.id="+String.valueOf(acti)+" and "+aliasTable+" in elements(act.equipos)";			
		}
				
		equipoDAO = new EquipoDAOImpl();
//		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
//		equipoPaginatedList = equipoDAO.listEquipoQuery(request, aliasTable, where, tablasAux);
		equipoList = equipoDAO.listEquipoQuery(aliasTable, where, tablasAux);
		
		return SUCCESS;
	}


	/**
	 * Une al usuario al equipo
	 * @param team Equipo al que unir al usuario
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String UnirEquipo(Equipo team){
		boolean exito = false;
		boolean error = false;
		int rango = FuncionesAuxiliares.NORMALUSER;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
					if(team == null){ // Si no existe el equipo y te quieres unir  
						Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
								new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
								"global/error/action/erroraction");
						
						addActionError(propsError.getProperty("equipo.join.inexistente"));
					}

					else{
						User us =(User)session.load(User.class,(Serializable) ActionContext.getContext().getSession().get("id"));
						
						UserEquipo userEquipo = new UserEquipo(rango); 
						UserEquipoPK userEqPK = new UserEquipoPK(us,team);
							
						userEquipo.setId(userEqPK); // Seteamos la PK del userTorneo
							
						userEquipoDAO = new UserEquipoDAOImpl();
						userEquipoDAO.mergeUserEquipo(userEquipo);

						Noticia noticia = new Noticia();
						noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																				"where nombre='AltaUsuarioEquipo'").uniqueResult());
						noticia.setParentEquipo(team);
						noticia.setParentUsuario(us);
						
						noticiaDAO = new NoticiaDAOImpl();
						noticiaDAO.mergeNoticia(noticia);
	
				
						exito=true;
					}
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(final HibernateException he){
							//Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}				
	}
	
	
	/**
	 * Crea un equipo
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String merge(){
		boolean exito = false;
		boolean error = false;
		int rango = FuncionesAuxiliares.CREADOR;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
					equipo.setNombre(equipo.getNombre().trim()); //trim al Nombre

					Equipo team = (Equipo) session.createQuery("from Equipo eq where eq.nombre=?")
								.setString(0,equipo.getNombre())
								.uniqueResult();
				
					if(team != null){
						Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
								new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
								"global/error/action/erroraction");
						
						addActionError(propsError.getProperty("equipo.nombre.existente"));
					}
					else{
						equipo.setTag(equipo.getTag().trim()); //trim al Tag
						equipo.setHomepage(equipo.getHomepage().trim()); //trim al Homepage
						
						Set<Actividad> setActividad = new HashSet<Actividad>(); // Probar si poner dentro del paréntesis
						setActividad.addAll(RecogerActividades(juegos,session));
						
						Set<Plataforma> setPlataforma = new HashSet<Plataforma>(RecogerPlataformas(plataformasdejuegos,session));
						
						if(!setActividad.isEmpty() && setPlataforma.isEmpty()){
							Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
									new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
									"global/error/action/erroraction");
							
							addActionError(propsError.getProperty("juego.elegir.plataforma"));
						}
						else{
							if(!setPlataforma.isEmpty()){
								equipo.setPlataformas(setPlataforma);	
							}
						
							setActividad.addAll(RecogerActividades(deportes,session));
							setActividad.addAll(RecogerActividades(cartas,session));							
							setActividad.addAll(RecogerActividades(juegosdemesa,session));
							
							if(setActividad.isEmpty()){
								Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
										new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
										"global/error/action/erroraction");
								
								addActionError(propsError.getProperty("elegir.actividad"));
							}
							else{
								equipo.setActividades(setActividad);
								equipo.setInvitado(false);
								equipo.setExpulsado(false);
									
								equipoDAO = new EquipoDAOImpl();
								team = equipoDAO.mergeEquipo(equipo);
								
								equipo.setId(team.getId());
								
								User us =(User)session.load(User.class,(Serializable) ActionContext.getContext().getSession().get("id"));
								
								UserEquipo userEquipo = new UserEquipo(rango); 
								UserEquipoPK userEqPK = new UserEquipoPK(us,team);
									
								userEquipo.setId(userEqPK); // Seteamos la PK del userTorneo
									
								userEquipoDAO = new UserEquipoDAOImpl();
								userEquipoDAO.mergeUserEquipo(userEquipo);
								

								Noticia noticia = new Noticia();
								noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																						"where nombre='EquipoCreado'").uniqueResult());									
								noticia.setParentEquipo(team);
								noticia.setParentUsuario(us);
								
								noticiaDAO = new NoticiaDAOImpl();
								noticiaDAO.mergeNoticia(noticia);

						
								exito=true;
							}
						}
					}
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(final HibernateException he){
							//Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}				
	}

	/**
	 * Une a un usuario al equipo
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	public String UnirseEquipo(){
		Equipo eq = RecuperarEquipoDetallado(null);
		
		if(SUCCESS == SetearEquipo(eq)){
			if( rolnivelEquipo == null){			
				return UnirEquipo(eq);
			}
			else if(rolnivelEquipo == FuncionesAuxiliares.EXMIEMBRO){
				return ReJoinEquipo(eq);
			}
				else{
					return ERROR;
				}
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Une a un usuario al equipo con contraseña
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String UnirseEquipoPassword()
	{
		Equipo eq = null;
		boolean error = false;
		boolean exito = false;
		
		try{
			eq = RecuperarEquipoDetallado(null);
			
			if(SUCCESS == SetearEquipo(eq)){
				if( rolnivelEquipo != FuncionesAuxiliares.NUMERO_NO_VALIDO && 
						rolnivelEquipo != FuncionesAuxiliares.EXMIEMBRO){
					return ERROR;					
				}
			}
			else{
				return ERROR;
			}
			
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			
			try{
				if(eq != null){
						// Si existe el equipo, cargamos el encriptador
						Properties props = PropertiesUtil.loadProperties("encriptado/encriptado.properties"); 
						StringEncrypter stringEncrypter = new StringEncrypter(props.getProperty("passPhrase"));
						
						if(equipo.getJoinPassword().trim().equals(stringEncrypter.decrypt(eq.getJoinPassword()))){
							// Si es correcta la contraseña
							exito = true;
						}
						else{
							Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
									new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
									"global/error/action/erroraction");	

							addActionError(propsError.getProperty("password.incorrecto"));
						}
				}
				
				else{ // Login incorrecto
					Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
							new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
							"global/error/action/erroraction");
					
					addActionError(propsError.getProperty("equipo.join.inexistente"));
				}
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
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){
					if( rolnivelEquipo == FuncionesAuxiliares.NUMERO_NO_VALIDO){
						return UnirEquipo(eq);	
					}
					else{
						return ReJoinEquipo(eq);
					}
					
				}
				else{
					return INPUT;
				}
			}
		}
	}
	

	/**
	 * Une a un usuario al equipo, al que había pertenecido
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */	
	@SuppressWarnings("finally")
	public String ReJoinEquipo(Equipo eq){
		boolean error = false;
		boolean exito = false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				User us = (User)session.get(User.class,(Serializable)iduser);
				
				UserEquipo usEq = (UserEquipo)session.get(UserEquipo.class,
						new UserEquipoPK(us,eq));
				
				usEq.setRango(FuncionesAuxiliares.NORMALUSER);
						
				userEquipoDAO = new UserEquipoDAOImpl();				
				userEquipoDAO.mergeUserEquipo(usEq);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																		"where nombre='AltaUsuarioEquipo'").uniqueResult());
				noticia.setParentEquipo(eq);
				noticia.setParentUsuario(us);
				
				noticiaDAO = new NoticiaDAOImpl();
				noticiaDAO.mergeNoticia(noticia);
				
				exito=true;
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
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}		
	}
	
	
	/**
	 * Crea un equipo invitado para la disputa de un torneo
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String CrearInvitado()
	{  	
		boolean exito = false;
		boolean error = false;
		
		System.out.println("--------------- Dentro de CrearInvitado()");
		System.out.println("--------------- Nombre --> "+equipo.getNombre()+"     idtorneo --> "+idtorneo);
		
		equipo.setNombre("inv_"+equipo.getNombre().trim()); //trim a Nombre
				
		try{
			
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{							
				Equipo eq = (Equipo) session.createQuery("from Equipo eq where eq.nombre=:nombreEq")
																.setString("nombreEq",equipo.getNombre())
																.uniqueResult();				
				if (eq != null){
					Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
							new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
							"global/error/action/erroraction");	

					addActionError(propsError.getProperty("equipo.nombre.existente"));
				}
				else{
						equipo.setInvitado(true); // ES UN INVITADO !!
						equipo.setExpulsado(false);
						
						equipoDAO = new EquipoDAOImpl();
						
						eq = equipoDAO.mergeEquipo(equipo); // Metemos al equipo en la BBDD
						
						equipo.setId(eq.getId());
						
						exito = true;
				}
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){					
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}		
	}
	
	
	/**
	 * Modifica el rango de un usuario dentro del equipo
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String EditarRango(){
		boolean error=false;
		boolean exito=false;
		
		try{ 
			Equipo eq = RecuperarEquipoDetallado(null);
			
			if(SUCCESS == SetearEquipo(eq)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelEquipo,FuncionesAuxiliares.MOD)){ // Necesitas ser al menos MOD
					return ERROR;
				}			
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				User us = (User)session.get(User.class,(Serializable)iduser);
				
				UserEquipo usEq = (UserEquipo)session.get(UserEquipo.class,
						new UserEquipoPK(us,eq));
				
				if(rangoEq != usEq.getRango()){				
					usEq.setRango(rangoEq);
							
					userEquipoDAO = new UserEquipoDAOImpl();				
					userEquipoDAO.mergeUserEquipo(usEq);
					
					Noticia noticia = new Noticia();
					noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																			"where nombre='RangoUsuarioEquipoCambiado'").uniqueResult());
					noticia.setNuevoRango(rangoEq);
					noticia.setParentEquipo(eq);
					noticia.setParentUsuario(us);
					
					noticiaDAO = new NoticiaDAOImpl();
					noticiaDAO.mergeNoticia(noticia);
					
					exito = true;
				}
				else{
					exito = true;
				}
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(HibernateException he){
			he.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}
	}
	
	/**
	 * Realiza una petición de entrada al equipo
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String RequestEquipo()
	{
		boolean error = false;
		boolean exito = false;
		
		try{
			Equipo eq = RecuperarEquipoDetallado(null);
			
			if(SUCCESS == SetearEquipo(eq)){
				if( rolnivelEquipo != null && rolnivelEquipo!=FuncionesAuxiliares.EXMIEMBRO){
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{							
				if(eq != null){						
					User us = (User)session.load(User.class,(Serializable) sesion.get("id"));
							
					Set<Equipo> setE = us.getEquiposRequest();
					setE.add(eq);
							
					us.setEquiposRequest(setE);					
							
					userDAO = new UserDAOImpl();
					userDAO.mergeUser(us);
					
					Noticia noticia = new Noticia();
					noticia.setParentCategoriaNoticia((CategoriaNoticia) session.createQuery("from CategoriaNoticia " +
																			"where nombre='RequestUsuarioEquipo'").uniqueResult());
					noticia.setParentEquipo(eq);
					noticia.setParentUsuario(us);
					
					noticiaDAO = new NoticiaDAOImpl();
					noticiaDAO.mergeNoticia(noticia);
							
					exito = true;
				}		
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
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}
	}
	
	/**
	 * Realiza una petición de entrada al equipo con contraseña
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String RequestEquipoPassword()
	{
		boolean error = false;
		boolean exito = false;
		
		try{
			Equipo eq = RecuperarEquipoDetallado(null);
			
			if(SUCCESS == SetearEquipo(eq)){
				if( rolnivelEquipo != null && rolnivelEquipo!= FuncionesAuxiliares.EXMIEMBRO){
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{	
				if(eq != null){
						// Si existe el equipo, cargamos el encriptador
						Properties props = PropertiesUtil.loadProperties("encriptado/encriptado.properties"); 
						StringEncrypter stringEncrypter = new StringEncrypter(props.getProperty("passPhrase"));
						
						if(equipo.getJoinPassword().trim().equals(stringEncrypter.decrypt(eq.getJoinPassword()))){
						
							User us = (User)session.load(User.class,(Serializable) sesion.get("id"));
							
							Set<User> setU = eq.getUsuariosRequest();
							setU.add(us);
							
							eq.setUsuariosRequest(setU);
							
							Set<Equipo> setE = us.getEquiposRequest();
							setE.add(eq);
							
							us.setEquiposRequest(setE);
							
							equipoDAO = new EquipoDAOImpl();
							equipoDAO.mergeEquipo(eq);
							
							userDAO = new UserDAOImpl();
							userDAO.mergeUser(us);
							
							Noticia noticia = new Noticia();
							noticia.setParentCategoriaNoticia((CategoriaNoticia) session.createQuery("from CategoriaNoticia " +
																					"where nombre='RequestUsuarioEquipo'").uniqueResult());
							noticia.setParentEquipo(eq);
							noticia.setParentUsuario(us);
							
							noticiaDAO = new NoticiaDAOImpl();
							noticiaDAO.mergeNoticia(noticia);
							
							// Si es correcta la contraseña
							exito = true;
						}
						else{
							Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
									new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
									"global/error/action/erroraction");	

							addActionError(propsError.getProperty("password.incorrecto"));
						}		
				}
				
				else{
					Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
							new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
							"global/error/action/erroraction");
					
					addActionError(propsError.getProperty("equipo.join.inexistente"));
				}
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
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}
	}
	
	/**
	 * Acepta la solicitud de entrada al equipo
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String AceptarRequest(){
		boolean error = false;
		boolean exito = false;
		
		try{
			Equipo eq = RecuperarEquipoDetallado(null);
			if(SUCCESS == SetearEquipo(eq)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelEquipo, FuncionesAuxiliares.MOD)){ // Hay que ser al menos MOD
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{

//				System.out.println("Iduser ---> "+iduser);
				User us = (User)session.get(User.class,(Serializable)iduser);

				UserEquipo userEquipo = (UserEquipo)session.get(UserEquipo.class,
						new UserEquipoPK(us,eq));
				
				if(userEquipo != null){
					if(userEquipo.getRango() == FuncionesAuxiliares.EXMIEMBRO){
						userEquipo.setRango(FuncionesAuxiliares.NORMALUSER);
					}
					else{
						return ERROR;
					}
				}
				else{
					userEquipo = new UserEquipo(FuncionesAuxiliares.NORMALUSER);
					userEquipo.setId(new UserEquipoPK(
									us,
									eq)); 
					// Seteamos la PK del userEquipo	
				}
				
				
				userEquipoDAO = new UserEquipoDAOImpl();
				userEquipoDAO.mergeUserEquipo(userEquipo);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																		"where nombre='AceptadoRequestUsuarioEquipo'").uniqueResult());
				noticia.setParentEquipo(eq);
				noticia.setParentUsuario(us);
				
				noticiaDAO = new NoticiaDAOImpl();
				noticiaDAO.mergeNoticia(noticia);
	
				// Borramos el request de la tabla intermedia.
				BorrarSolicitud(us);
				
				exito = true;
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
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}
	}
	
	/**
	 * Deniega la solicitud de entrada al equipo
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String DenegarRequest(){
		boolean error = false;
		boolean exito = false;
		
		try{
			Equipo eq = RecuperarEquipoDetallado(null);
			
			if(SUCCESS == SetearEquipo(eq)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelEquipo, FuncionesAuxiliares.MOD)){ // Hay que ser al menos MOD
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				// Borramos el request de la tabla intermedia.
				User us = (User)session.get(User.class,(Serializable)iduser);
				
				BorrarSolicitud(us);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																		"where nombre='DenegadoRequestUsuarioEquipo'").uniqueResult());
				noticia.setParentEquipo(eq);
				noticia.setParentUsuario(us);
				
				noticiaDAO = new NoticiaDAOImpl();
				noticiaDAO.mergeNoticia(noticia);
				
				exito = true;
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
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}
	}
	
	/**
	 * Banear usuario del equipo
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String BanearUsuario()
	{
		boolean error=false;
		boolean exito=false;
		
		try{
			Equipo eq = RecuperarEquipoDetallado(null);
			
			if(SUCCESS == SetearEquipo(eq)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelEquipo,FuncionesAuxiliares.MOD)){ // Necesitas ser al menos MOD
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				User us = (User)session.get(User.class,(Serializable)iduser);
	
				UserEquipo usEq = (UserEquipo)session.get(UserEquipo.class,
						new UserEquipoPK(us,eq));
						
				usEq.setRango(FuncionesAuxiliares.BANEADO); // Simplemente cambiamos su rango a baneado,
				// Internamente seguirá perteneciendo al equipo para poder acceder a sus datos relativos al equipo.
				
				userEquipoDAO = new UserEquipoDAOImpl();				
				userEquipoDAO.mergeUserEquipo(usEq);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																		"where nombre='BaneadoUsuarioEquipo'").uniqueResult());
				noticia.setParentEquipo(eq);
				noticia.setParentUsuario(us);
				
				noticiaDAO = new NoticiaDAOImpl();
				noticiaDAO.mergeNoticia(noticia);
				
				exito = true;
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(HibernateException he){
			he.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}
	}
	
	/**
	 * Expulsar usuario del equipo
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String ExpulsarUsuario()
	{
		boolean error=false;
		boolean exito=false;
		
		try{
			Equipo eq = RecuperarEquipoDetallado(null);
			
			if(SUCCESS == SetearEquipo(eq)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelEquipo,FuncionesAuxiliares.MOD)){ // Necesitas ser al menos MOD
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				User us = (User)session.get(User.class,(Serializable)iduser);
				
				UserEquipo usEq = (UserEquipo)session.get(UserEquipo.class,
						new UserEquipoPK(us,eq));
						
				usEq.setRango(FuncionesAuxiliares.EXMIEMBRO); // Simplemente cambiamos su rango a exmiembro,
				// Internamente seguirá perteneciendo al equipo para poder acceder a sus datos relativos al equipo.
				
				userEquipoDAO = new UserEquipoDAOImpl();				
				userEquipoDAO.mergeUserEquipo(usEq);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																		"where nombre='ExpulsadoUsuarioEquipo'").uniqueResult());
				noticia.setParentEquipo(eq);
				noticia.setParentUsuario(us);
				
				noticiaDAO = new NoticiaDAOImpl();
				noticiaDAO.mergeNoticia(noticia);
				
				exito = true;
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(HibernateException he){
			he.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}
	}
	
	/**
	 * Cambia la confirmación de registro del equipo
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String CambiarConfirmacion()
	{
		boolean error=false;
		boolean exito=false;
		
		try{
			Equipo eq = RecuperarEquipoDetallado(null);
			
			if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelEquipo,FuncionesAuxiliares.ADMIN)){ // Necesitas ser al menos ADMIN
				return ERROR;
			}			

			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			
			try{				
				if(eq.isRegRequest() == equipo.isRegRequest()){
					exito = true;
				}
				else{
					eq.setRegRequest(equipo.isRegRequest());
					
					// Guardamos la comunidad en la BBDD
					equipoDAO = new EquipoDAOImpl();
					eq = equipoDAO.mergeEquipo(eq);
					
					Noticia noticia=null;
					
					if(eq.isRegRequest()){
						noticia = new Noticia();
						noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
								"where nombre='ConfirmacionRegistroEquipoOn'").uniqueResult());
					}
					else{
						noticia = new Noticia();
						noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
								"where nombre='ConfirmacionRegistroEquipoOff'").uniqueResult());
					}
					
					noticia.setParentEquipo(eq);
					
					noticiaDAO = new NoticiaDAOImpl();
					noticiaDAO.mergeNoticia(noticia);
					
					exito = true;
				}
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(HibernateException he){
			he.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}
	}
	
	/**
	 * Cambia la contraseña del equipo
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String CambiarPassword()
	{
		boolean error=false;
		boolean exito=false;
		
		try{
			Equipo eq = RecuperarEquipoDetallado(null);
			
			if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelEquipo,FuncionesAuxiliares.ADMIN)){ // Necesitas ser al menos ADMIN
				return ERROR;
			}			

			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			
			try{
				if(eq.isPasswordProtected() == false && equipo.isPasswordProtected() == false){
					exito = true;
				}
				else{
					eq.setPasswordProtected(equipo.isPasswordProtected());
					
					if(eq.isPasswordProtected()){	
						Properties props = PropertiesUtil.loadProperties("encriptado/encriptado.properties"); 
						StringEncrypter stringEncrypter = new StringEncrypter(props.getProperty("passPhrase"));
						
						eq.setJoinPassword(stringEncrypter.encrypt(equipo.getJoinPassword().trim())); // Encriptamos la contraseña
					}
					else{
						eq.setJoinPassword(null);
					}
					
					// Guardamos el equipo en la BBDD
					equipoDAO = new EquipoDAOImpl();
					eq = equipoDAO.mergeEquipo(eq);
					
					Noticia noticia = new Noticia();
					noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																			"where nombre='PasswordEquipoCambiado'").uniqueResult());
					noticia.setParentEquipo(eq);
					
					noticiaDAO = new NoticiaDAOImpl();
					noticiaDAO.mergeNoticia(noticia);
					
					exito = true;
				}
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(HibernateException he){
			he.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}
	}
	
	/**
	 * Modifica la configuración del equipo
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String ModificarSettings()
	{
		boolean error=false;
		boolean exito=false;
		boolean distinto=false;
		
		try{
			Equipo eq = RecuperarEquipoDetallado(null);
			
			if(SUCCESS == SetearEquipo(eq)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelEquipo,FuncionesAuxiliares.ADMIN)){ // Necesitas ser al menos ADMIN
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			
			try{
				Set<Actividad> setActividad = new HashSet<Actividad>(RecogerActividades(juegos,session)); 
				Set<Plataforma> setPlataforma = new HashSet<Plataforma>(RecogerPlataformas(plataformasdejuegos,session));
				
				if(!setActividad.isEmpty() && setPlataforma.isEmpty()){ // Si se ha elegido algun juego debe elegirse también plataforma							
					Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
							new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
							"global/error/action/erroraction");
					
					addActionError(propsError.getProperty("juego.elegir.plataforma"));																				
				}
				else{							
					setActividad.addAll(RecogerActividades(deportes,session));
					setActividad.addAll(RecogerActividades(cartas,session));
//						setActividad.addAll(RecogerActividades(juegos,session)); // Ya está dentro del Set
					setActividad.addAll(RecogerActividades(juegosdemesa,session));
						
					if(setActividad.isEmpty()){
						Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
								new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
								"global/error/action/erroraction");
						
						addActionError(propsError.getProperty("elegir.actividad"));
					}
					else {
						 Set<Actividad> setA = eq.getActividades();
						 Set<Plataforma> setP = eq.getPlataformas();
						 
						 if(!setActividad.equals(setA)){
							 equipo.setActividades(setActividad);
							 distinto=true;
						 }
						 if(!setPlataforma.equals(setP)){
							 equipo.setPlataformas(setPlataforma);
							 distinto=true;
						 }
						 
						 if(distinto){
							// Si se ha hecho algún cambio
							
							equipoDAO = new EquipoDAOImpl();
							equipoDAO.mergeEquipo(equipo);
						 }
						 
						 exito = true;
					}
				}
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(HibernateException he){
			he.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}
	}
	
	/**
	 * Abandona Equipo
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String QuitarseEquipo()
	{
		boolean error=false;
		boolean exito=false;
		
		try{
			Equipo eq = RecuperarEquipoDetallado(null);
			
			if(SUCCESS == SetearEquipo(eq)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelEquipo,FuncionesAuxiliares.NORMALUSER)){ // Necesitas ser al menos NORMALUSER
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
//				sesion = getSession();
				User us = (User)session.get(User.class,(Serializable) sesion.get("id"));
				
				UserEquipo usEq = (UserEquipo)session.get(UserEquipo.class,
						new UserEquipoPK(us,eq));
				
				usEq.setRango(FuncionesAuxiliares.EXMIEMBRO);
				
				userEquipoDAO = new UserEquipoDAOImpl();
				userEquipoDAO.mergeUserEquipo(usEq);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																		"where nombre='BajaUsuarioEquipo'").uniqueResult());
				noticia.setParentEquipo(eq);
				noticia.setParentUsuario(us);
				
				noticiaDAO = new NoticiaDAOImpl();
				noticiaDAO.mergeNoticia(noticia);
				
				exito = true;
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(HibernateException he){
			he.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}
	}
	
	/**
	 * Realiza petición de asociación de datos de un equipo invitado a su cuenta de equipo
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String RequestAsociarInvitado(){
		boolean error=false;
		boolean exito=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{	
				Equipo eq = (Equipo)session.load(Equipo.class,(Serializable)sesion.get("equipo"));
				Equipo invitado = (Equipo)session.load(Equipo.class,equipo.getId());
				
				Set<Equipo> setInv = eq.getInvitadosRequest();
				setInv.add(invitado);
				
				eq.setInvitadosRequest(setInv);
				
				equipoDAO = new EquipoDAOImpl();
				equipoDAO.mergeEquipo(eq);
				
				exito=true;
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){					
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}	
	}
	
	/**
	 * Acepta la solicitud de asociación de los datos del equipo invitado
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String AceptarRequestInvitado(){
		boolean error=false;
		boolean exito=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				Equipo eq = (Equipo)session.load(Equipo.class,equipo.getId());
				Equipo invitado = (Equipo)session.load(Equipo.class,entero);

				Set<PartidoEquiposVersus> setP = invitado.getPartidosVSGanados();
				
				partidoDAO = new PartidoDAOImpl();
				
				Iterator<PartidoEquiposVersus> it = setP.iterator();
				while(it.hasNext()){
					PartidoEquiposVersus pa = it.next();
					pa.setParentGanador(eq);
					
					partidoDAO.mergePartidoEquiposVersus(pa);
				}
				
				invitado.setPartidosVSGanados(null);
				
				// Partidos Perdidos
				setP = invitado.getPartidosVSPerdidos();
				
				it = setP.iterator();
				while(it.hasNext()){
					PartidoEquiposVersus pa = it.next();
					pa.setParentPerdedor(eq);
					
					partidoDAO.mergePartidoEquiposVersus(pa);
				}
				
				invitado.setPartidosVSPerdidos(null);
				
				// Partidos Local
				setP = invitado.getPartidosVSLocal();
				
				it = setP.iterator();
				while(it.hasNext()){
					PartidoEquiposVersus pa = it.next();
					pa.setParentLocal(eq);
					
					partidoDAO.mergePartidoEquiposVersus(pa);
				}
				
				invitado.setPartidosVSLocal(null);
				
				// Partidos Visitante			
				setP = invitado.getPartidosVSVisitante();
				
				it = setP.iterator();
				while(it.hasNext()){
					PartidoEquiposVersus pa = it.next();
					pa.setParentVisitante(eq);
					
					partidoDAO.mergePartidoEquiposVersus(pa);
				}
				
				invitado.setPartidosVSVisitante(null);
				
				// Equipo Torneo
				Torneo tor = (Torneo)session.get(Torneo.class, idtorneo);
				
				EquipoTorneo equipoTorneo = (EquipoTorneo)session.get(EquipoTorneo.class,
						new EquipoTorneoPK(invitado,tor));
								
				
				EquipoTorneoPK utPK = new EquipoTorneoPK(eq,tor);
				
				equipoTorneo.setId(utPK);
				
				equipoTorneoDAO = new EquipoTorneoDAOImpl();
				equipoTorneoDAO.mergeEquipoTorneo(equipoTorneo);
				
				// Borramos los requests al invitado de la tabla intermedia.
				BorrarSolicitudesInvitado(invitado);
				
//				equipoTorneo = (EquipoTorneo)session.get(EquipoTorneo.class,
//						new EquipoTorneoPK(invitado,tor));
				

				
//				equipoTorneoDAO.deleteEquipoTorneo(equipoTorneo);
				
//				equipoDAO = new EquipoDAOImpl();
//				equipoDAO.deleteEquipo(invitado);
				
//				session.delete(equipoTorneo);
//				
//				session.delete(invitado);
				
				
				
				exito = true;
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){					
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}	
	}
	
	/**
	 * Deniega la solicitud de asociación de los datos del equipo invitado
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String RechazarRequestInvitado(){
		boolean error=false;
		boolean exito=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				Equipo eq = (Equipo)session.load(Equipo.class,equipo.getId());
				Equipo invitado = (Equipo)session.load(Equipo.class,entero);
												
				// Borramos el request del usuario al invitado de la tabla intermedia.
				BorrarSolicitudInvitado(eq,invitado);				
				
				exito = true;
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){					
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}	
	}
	
	/**
	 * Borra las solicitudes de asociación que tenga el equipo invitado
	 * @param invitado Equipo invitado
	 */
	public void BorrarSolicitudesInvitado(Equipo invitado){
		invitado.setEquiposRequest(null);
		
		equipoDAO = new EquipoDAOImpl();
		equipoDAO.mergeEquipo(invitado);
		
	}
	
	/**
	 * Borra la solicitud de asociación al equipo invitado que tenga el equipo solicitante
	 * @param solicitante Equipo solicitante
	 * @param invitado Equipo invitado
	 */
	public void BorrarSolicitudInvitado(Equipo solicitante, Equipo invitado){
		Set<Equipo> setE = solicitante.getInvitadosRequest();		
		setE.remove(invitado);
		
		solicitante.setInvitadosRequest(setE);
				
		equipoDAO = new EquipoDAOImpl();
		equipoDAO.mergeEquipo(solicitante);
		
	}
	
	/**
	 * Cambia al perfil del equipo
	 * @return SUCCESS
	 */
	public String CambiarPerfilEquipo(){
		getSession().put("equipo",equipo.getId());
		getSession().put("equipoNombre",equipo.getNombre());
		
		return SUCCESS;
	}
	
	/**
	 * Borrar solicitud de entrada al equipo del usuario
	 * @param us Usuario
	 */
	public void BorrarSolicitud(User us){
		Set<Equipo> setE = us.getEquiposRequest();
		
		Iterator<Equipo> it = setE.iterator();
		while(it.hasNext()){
			Equipo equi = it.next();
			if(equi.getId() == equipo.getId()){
				setE.remove(equi);
			}
		}
		
		us.setEquiposRequest(setE);
		
		userDAO = new UserDAOImpl();
		userDAO.mergeUser(us);
	}
	
	/**
	 * Nos lleva a la zona de administración de todos los equipos de la aplicación
	 * @return SUCCESS
	 */
	public String GoAdminEquipos(){
		return BuscarEquipo();
	}
	
	/**
	 * Mete al valuestack los valores para las estadísticas del equipo
	 * @return SUCCESS en caso de éxito, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String EstadisticasEquipo(){
		Integer noComenzados = 0;
		Integer finalizados = 0;
		Integer enJuego = 0;
		
		boolean error=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{	
				Equipo eq = (Equipo)session.load(Equipo.class,equipo.getId());
				
				SetearEquipo(eq);


				
				Set<EquipoTorneo> setET = eq.getEquipoTorneos();
				Iterator<EquipoTorneo> it = setET.iterator();
				
				while(it.hasNext()){
					EquipoTorneo ut = it.next();
					
					
					if(SUCCESS == FuncionesAuxiliares.ComprobarPermisos(ut.getRango(), 
							FuncionesAuxiliares.NORMALUSER)){
						
						Integer estado = ut.getId().getParentTorneo().getEstado();
					
						if(estado == FuncionesAuxiliares.REG_ABIERTOS ||
								estado == FuncionesAuxiliares.REG_CERRADOS){
							noComenzados++;
						}
						else if(estado == FuncionesAuxiliares.EN_JUEGO){
							enJuego++;
						}
						else if(estado == FuncionesAuxiliares.FINALIZADO){
							finalizados++;
						}
					}
				}
				
//				System.out.println("---- Torneos -> no comenzados: "+noComenzados+"  en juego: "+enJuego+"  finalizados: "+finalizados);
			
				listTorneosNoComenzados.add(new IntegerInteger(2,noComenzados));
				listTorneosEnJuego.add(new IntegerInteger(2,enJuego));
				listTorneosFinalizados.add(new IntegerInteger(2,finalizados));
				
				Integer tam = 0;
				Set<EquipoComunidad> setEC = eq.getEquipoComunidades();
				Iterator<EquipoComunidad> itEC = setEC.iterator();
				
				while(itEC.hasNext()){
					if(SUCCESS == FuncionesAuxiliares.ComprobarPermisos(itEC.next().getRango(), 
													FuncionesAuxiliares.NORMALUSER)){
						tam++;
					}
				}
				
				listComunidades.add(new IntegerInteger(1,tam));
				
				
				tam = 0;

				Set<UserEquipo> setUE = eq.getUserEquipos();
				Iterator<UserEquipo> itUE = setUE.iterator();
				
				while(itUE.hasNext()){
					if(SUCCESS == FuncionesAuxiliares.ComprobarPermisos(itUE.next().getRango(), 
													FuncionesAuxiliares.NORMALUSER)){
						tam++;
					}
				}
				
				
				listUsuarios.add(new IntegerInteger(3,tam));
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{				
				return SUCCESS;
			}
		}
	}
	
	/**
	 * Elimina el equipo
	 * @return SUCCESS en caso de éxito, INPUT en caso de completarse, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String delete(){
		boolean error=false;
		boolean exito=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			
			try{
				Equipo invitado = new Equipo(equipo.getId());
				
				System.out.println("Invitado a borrar --> "+invitado.getNombre());

//				// GUARDAMOS
				equipoDAO = new EquipoDAOImpl();
				equipoDAO.deleteEquipo(invitado);
								
				exito = true;
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){					
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}	
	}
	
	
	public Set<Actividad> RecogerActividades(List<Integer> list, Session session){
		Set<Actividad> setActividad = new HashSet<Actividad>();
		
		if(list!=null){
			Iterator<Integer> it = list.iterator();
			while(it.hasNext()){
				Actividad actividad = (Actividad) session.get(Actividad.class,(Serializable)it.next());
				if(actividad != null){
					setActividad.add(actividad);
				}
			}
		}
		
		return setActividad;
	}
	
	public Set<Plataforma> RecogerPlataformas(List<Integer> list, Session session){
		Set<Plataforma> setPlataforma = new HashSet<Plataforma>();
		
		if(list != null){
			Iterator<Integer> it = list.iterator();
			while(it.hasNext()){
				Plataforma plataforma = (Plataforma) session.get(Plataforma.class,(Serializable)it.next());
				if(plataforma != null){
					setPlataforma.add(plataforma);
				}
			}
		}
		
		return setPlataforma;
	}	
	
	/**
	 * Lista los equipos de un usuario
	 * @return SUCCESS en caso de éxito, INPUT en caso de completarse, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String EquipoListByUser() {
		
		boolean error=false;
		boolean exito=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				getSession().put("equipo", 0); // Ponemos el perfil de usuario
				
				User us = (User)session.get(User.class,(Serializable) sesion.get("id"));
				
				userEquipoList = new ArrayList<UserEquipo>(us.getUserEquipos());
				
				exito = true;
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(HibernateException he){
			he.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				if(exito == true){
					return SUCCESS;
				}
				else{
					return INPUT;
				}
			}
		}
	}
	
	/**
	 * Almacena en una cadena de caracteres del valuestack el rango del usuario
	 * @return SUCCESS en caso de éxito, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String SacarRangoUsuario(){
		boolean error=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				
				UserEquipo usEq = (UserEquipo)session.get(UserEquipo.class,
						new UserEquipoPK((User)session.get(User.class,(Serializable)iduser),
											(Equipo)session.get(Equipo.class, equipo.getId())));
				
				cadena = FuncionesAuxiliares.StringRango(usEq.getRango());
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(HibernateException he){
			he.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return ERROR;
					}
				}
				return ERROR;
			}
			else{
				return SUCCESS;
			}
		}
	}
	
	
	/**
	 * Lista los usuarios de un equipo
	 * @return SUCCESS
	 */
	public String UserListByEquipo(){
//		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		
		userDAO = new UserDAOImpl();

//		userPaginatedList = userDAO.listUserByEquipo(request,equipo);
		
		userList = userDAO.listUserByEquipo(equipo);
		
		return SUCCESS;
	}
	
	public void prepare() throws Exception {
		System.out.println("-------------  Prepare  --------------");
	}
	
	public void prepareMerge(){
		System.out.println("------- prepareMerge() -----------");
		
		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarActividades(sesion, null, listaDeportes, listaCartas, listaJuegosdemesa, listaJuegos);
		listHashMapUtil.SacarPlataformas(sesion, null, listaPlataformas);
		listHashMapUtil.SacarBool(sesion, listaBoolean);
	}
	
	public void prepareGoRegister(){
		System.out.println("------- prepareGoRegister() -----------");
		
		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarActividades(sesion, null, listaDeportes, listaCartas, listaJuegosdemesa, listaJuegos);
		listHashMapUtil.SacarPlataformas(sesion, null, listaPlataformas);
		listHashMapUtil.SacarBool(sesion, listaBoolean);
	}	
	
	public void prepareGoSearch(){
		System.out.println("------- prepareGoSearch() -----------");
		
		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarActividades(sesion, "todas", listaDeportes, listaCartas, listaJuegosdemesa, listaJuegos);
		listHashMapUtil.SacarPlataformas(sesion, "todas", listaPlataformas);
		listHashMapUtil.SacarBool(sesion,"todas",listaBooleanInteger);
	}
	
	public void prepareBuscarEquipo(){
		System.out.println("------- prepareBuscarEquipo() -----------");
		
		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarActividades(sesion, "todas", listaDeportes, listaCartas, listaJuegosdemesa, listaJuegos);
		listHashMapUtil.SacarPlataformas(sesion, "todas", listaPlataformas);
		listHashMapUtil.SacarBool(sesion,"todas",listaBooleanInteger);
	}
	
	public void prepareGoPasswordEquipo(){
		System.out.println("------- prepareGoPasswordEquipo() -----------");
		
		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarBool(sesion, listaBoolean);
	}
	
	public void prepareGoSettings(){
		System.out.println("------- prepareGoSettings() -----------");
		
		sesion = getSession();
		
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarActividades(sesion, null, listaDeportes, listaCartas, listaJuegosdemesa, listaJuegos);
		
		listHashMapUtil.SacarPlataformas(sesion, null, listaPlataformas);
	}
	
	public void prepareEditarRango(){
		System.out.println("--------- prepareEditarRango() -------------");
		
		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarRangosComunidad(sesion,rolequipo,listaRangos);
		
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
