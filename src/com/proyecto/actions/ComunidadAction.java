package com.proyecto.actions;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.*;


import javax.servlet.http.HttpServletRequest;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import com.proyecto.util.displaytag.Paginate;
import com.proyecto.util.encriptado.StringEncrypter;
import com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares;
import com.proyecto.util.hibernate.HibernateUtil;
import com.proyecto.util.listas.ActividadComparator;
import com.proyecto.util.listas.ListHashMapUtil;
import com.proyecto.util.listas.PlataformaComparator;
import com.proyecto.util.properties.GlobalResourceUtil;
import com.proyecto.util.properties.PropertiesUtil;
import com.proyecto.dao.ComunidadDAO;
import com.proyecto.dao.ComunidadDAOImpl;
import com.proyecto.dao.EquipoComunidadDAO;
import com.proyecto.dao.EquipoComunidadDAOImpl;
import com.proyecto.dao.EquipoDAO;
import com.proyecto.dao.EquipoDAOImpl;
import com.proyecto.dao.NoticiaDAO;
import com.proyecto.dao.NoticiaDAOImpl;
import com.proyecto.dao.UserComunidadDAO;
import com.proyecto.dao.UserComunidadDAOImpl;
import com.proyecto.dao.UserDAO;
import com.proyecto.dao.UserDAOImpl;
import com.proyecto.dominio.Actividad;
import com.proyecto.dominio.CategoriaNoticia;
import com.proyecto.dominio.Comunidad;
import com.proyecto.dominio.Equipo;
import com.proyecto.dominio.EquipoComunidad;
import com.proyecto.dominio.EquipoComunidadPK;
import com.proyecto.dominio.IntegerInteger;
import com.proyecto.dominio.Noticia;
import com.proyecto.dominio.Plataforma;
import com.proyecto.dominio.Torneo;
import com.proyecto.dominio.User;
import com.proyecto.dominio.UserComunidad;
import com.proyecto.dominio.UserComunidadPK;
import com.proyecto.dominio.UserEquipo;
import com.proyecto.dominio.UserTorneo;

/**
* Esta clase implementa el Action asociado a la
* comunidad.
* @author Lucas Sánchez López
* @version 3.0
*/

public class ComunidadAction extends ActionSupport implements ModelDriven<Comunidad>, Preparable, SessionAware {
	private static final long serialVersionUID = 1L;
	private Comunidad comunidad = new Comunidad();
	private User user = new User();
	private String tab;
	private String subTab;
	private List<Comunidad> comunidadList;
	private List<Torneo> torneoList;
	private List<User> userList;
	private List<Equipo> equipoList;
	private List<Actividad> actividadList;
	private List<Plataforma> plataformaList;
	private List<UserComunidad> userComunidadList;
	private List<EquipoComunidad> equipoComunidadList;
	private List<Integer> defaultActividades;
	private List<Integer> defaultPlataformas;
	private Paginate comunidadPaginatedList;
	private Paginate torneoPaginatedList;
	private Paginate userPaginatedList;
	private Paginate noticiaPaginatedList;
	private ComunidadDAO comunidadDAO;
	private ListHashMapUtil listHashMapUtil;
	private UserComunidadDAO userComunidadDAO;
	private EquipoComunidadDAO equipoComunidadDAO;
	private UserDAO userDAO;
	private EquipoDAO equipoDAO;
	private NoticiaDAO noticiaDAO;
	private List<Integer> deportes;
	private List<Integer> cartas;
	private List<Integer> juegos;
	private List<Integer> juegosdemesa;
	private List<Integer> plataformasdejuegos;
	private Integer deporte;
	private Integer carta;
	private Integer juego;
	private Integer juegodemesa;
	private Integer plataforma;
	private LinkedHashMap<Integer,String> listaDeportes = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaCartas= new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaJuegos= new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaJuegosdemesa= new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaPlataformas= new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Boolean,String> listaBoolean = new LinkedHashMap<Boolean,String>();
	private LinkedHashMap<Integer,String> listaBooleanInteger = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaRangos = new LinkedHashMap<Integer,String>();
	private List<IntegerInteger> listTorneosNoComenzados = new ArrayList<IntegerInteger>();
	private List<IntegerInteger> listTorneosEnJuego = new ArrayList<IntegerInteger>();
	private List<IntegerInteger> listTorneosFinalizados = new ArrayList<IntegerInteger>();
	private List<IntegerInteger> listUsuarios = new ArrayList<IntegerInteger>();
	private List<IntegerInteger> listEquipos = new ArrayList<IntegerInteger>();
	private Map sesion;
	private String rolcomunidad;
	private Integer rolnivelComunidad = FuncionesAuxiliares.NUMERO_NO_VALIDO;
	private Integer iduser;
	private Integer idequipo;
	private Integer rangoCom;
	private Boolean requestPendiente;
	private Boolean equipoDentro;
	private String tipo;
	private String cadena;
	private Integer passProtected;
	private Integer regReq;
	
	
	public Integer getPassProtected() {
		return passProtected;
	}

	public void setPassProtected(Integer passProtected) {
		this.passProtected = passProtected;
	}

	public Integer getRegReq() {
		return regReq;
	}

	public void setRegReq(Integer regReq) {
		this.regReq = regReq;
	}

	SessionFactory sessionFactory = null;
	
	@SessionTarget
	Session session = null;

	@Override
	public Comunidad getModel() {
		// TODO Auto-generated method stub
		return comunidad;
	}
	
	public Comunidad getComunidad(){
		return comunidad;
	}
	
	public void setComunidad(Comunidad comunidad){
		this.comunidad = comunidad;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getRangoCom() {
		return rangoCom;
	}

	public void setRangoCom(Integer rangoCom) {
		this.rangoCom = rangoCom;
	}

	public List<Comunidad> getComunidadList() {
		return comunidadList;
	}

	public void setComunidadList(List<Comunidad> comunidadList) {
		this.comunidadList = comunidadList;
	}

	public List<Torneo> getTorneoList() {
		return torneoList;
	}

	public void setTorneoList(List<Torneo> torneoList) {
		this.torneoList = torneoList;
	}
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
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

	public List<IntegerInteger> getListEquipos() {
		return listEquipos;
	}

	public void setListEquipos(List<IntegerInteger> listEquipos) {
		this.listEquipos = listEquipos;
	}

	public String getRolcomunidad() {
		return rolcomunidad;
	}

	public List<Plataforma> getPlataformaList() {
		return plataformaList;
	}

	public void setPlataformaList(List<Plataforma> plataformaList) {
		this.plataformaList = plataformaList;
	}

	public Paginate getUserPaginatedList() {
		return userPaginatedList;
	}

	public void setUserPaginatedList(Paginate userPaginatedList) {
		this.userPaginatedList = userPaginatedList;
	}

	public void setRolcomunidad(String rolcomunidad) {
		this.rolcomunidad = rolcomunidad;
	}

	public Paginate getComunidadPaginatedList() {
		return comunidadPaginatedList;
	}

	public void setComunidadPaginatedList(Paginate comunidadPaginatedList) {
		this.comunidadPaginatedList = comunidadPaginatedList;
	}
	
	public List<Actividad> getActividadList() {
		return actividadList;
	}

	public void setActividadList(List<Actividad> actividadList) {
		this.actividadList = actividadList;
	}

	public Paginate getTorneoPaginatedList() {
		return torneoPaginatedList;
	}

	public void setTorneoPaginatedList(Paginate torneoPaginatedList) {
		this.torneoPaginatedList = torneoPaginatedList;
	}

	public Boolean getEquipoDentro() {
		return equipoDentro;
	}

	public void setEquipoDentro(Boolean equipoDentro) {
		this.equipoDentro = equipoDentro;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	
	
	public List<Equipo> getEquipoList() {
		return equipoList;
	}

	public void setEquipoList(List<Equipo> equipoList) {
		this.equipoList = equipoList;
	}

	public Integer getIdequipo() {
		return idequipo;
	}

	public void setIdequipo(Integer idequipo) {
		this.idequipo = idequipo;
	}

	public LinkedHashMap<Integer, String> getListaBooleanInteger() {
		return listaBooleanInteger;
	}

	public void setListaBooleanInteger(
			LinkedHashMap<Integer, String> listaBooleanInteger) {
		this.listaBooleanInteger = listaBooleanInteger;
	}

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public List<Integer> getDefaultActividades() {
		Set<Actividad> setA = comunidad.getActividades();		
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
		Set<Plataforma> setP = comunidad.getPlataformas();
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

	public Integer getDeporte() {
		return deporte;
	}

	public void setDeporte(Integer deporte) {
		this.deporte = deporte;
	}

	public Integer getCarta() {
		return carta;
	}

	public void setCarta(Integer carta) {
		this.carta = carta;
	}

	public Integer getRolnivelComunidad() {
		return rolnivelComunidad;
	}

	public void setRolnivelComunidad(Integer rolnivelComunidad) {
		this.rolnivelComunidad = rolnivelComunidad;
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

	public Paginate getNoticiaPaginatedList() {
		return noticiaPaginatedList;
	}

	public void setNoticiaPaginatedList(Paginate noticiaPaginatedList) {
		this.noticiaPaginatedList = noticiaPaginatedList;
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

	public LinkedHashMap<Integer, String> getListaPlataformas() {
		return listaPlataformas;
	}

	public void setListaPlataformas(LinkedHashMap<Integer, String> listaPlataformas) {
		this.listaPlataformas = listaPlataformas;
	}
		
	public List<Integer> getDeportes() {
		return deportes;
	}

	public void setDeportes(List<Integer> deportes) {
		this.deportes = deportes;
	}

	public List<Integer> getPlataformasdejuegos() {
		return plataformasdejuegos;
	}

	public void setPlataformasdejuegos(List<Integer> plataformasdejuegos) {
		this.plataformasdejuegos = plataformasdejuegos;
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

	public void setJuegosdemesa(List<Integer> juegosdemesa) {
		this.juegosdemesa = juegosdemesa;
	}
	
	public LinkedHashMap<Boolean, String> getListaBoolean() {
		return listaBoolean;
	}

	public void setListaBoolean(LinkedHashMap<Boolean, String> listaBoolean) {
		this.listaBoolean = listaBoolean;
	}
	public Integer getIduser() {
		return iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}
	
	public LinkedHashMap<Integer, String> getListaRangos() {
		return listaRangos;
	}

	public void setListaRangos(LinkedHashMap<Integer, String> listaRangos) {
		this.listaRangos = listaRangos;
	}	
	
	public Boolean getRequestPendiente() {
		return requestPendiente;
	}

	public void setRequestPendiente(Boolean requestPendiente) {
		this.requestPendiente = requestPendiente;
	}	
	
	
	
	
//  ------------------------------------------



	public List<UserComunidad> getUserComunidadList() {
		return userComunidadList;
	}

	public void setUserComunidadList(List<UserComunidad> userComunidadList) {
		this.userComunidadList = userComunidadList;
	}

	public List<EquipoComunidad> getEquipoComunidadList() {
		return equipoComunidadList;
	}

	public void setEquipoComunidadList(List<EquipoComunidad> equipoComunidadList) {
		this.equipoComunidadList = equipoComunidadList;
	}

	
	/**
	 * Método execute del ComunidadAction
	 */
	public String execute(){ // Probando para el tabbedPanel
		return SetearComunidad(RecuperarComunidadDetallada(null));
	}

	/**
	 * Recupera todos los datos de la comunidad
	 * @param com Comunidad de la que recuperar todos los datos
	 * @return Comunidad recuperada
	 */
	@SuppressWarnings("finally")
	public Comunidad RecuperarComunidadDetallada(Comunidad com)
	{
		try{ 
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();

			// -------------------------------------------
			if(com == null){
				com = (Comunidad)session.load(Comunidad.class,(Serializable)comunidad.getId());
			}
			
			if((Integer) sesion.get("equipo") == 0){
				UserComunidad usCo = (UserComunidad)session.get(UserComunidad.class,
						new UserComunidadPK((User)session.load(User.class,(Integer)sesion.get("id")),com));
				
				if(usCo != null){
					rolnivelComunidad = usCo.getRango();
					rolcomunidad = FuncionesAuxiliares.StringRango(rolnivelComunidad);	
				}		
			}
			else{
				
				EquipoComunidad eqCo = (EquipoComunidad)session.get(EquipoComunidad.class,
						new EquipoComunidadPK((Equipo)session.load(Equipo.class,(Integer)sesion.get("equipo")),com));
				
				if(eqCo != null){
					rolnivelComunidad = eqCo.getRango();
					rolcomunidad = FuncionesAuxiliares.StringRango(rolnivelComunidad);	
				}
			}
			

		}
		catch(Exception e){
			e.printStackTrace();
			session.close();
		}
		finally{
			return com;
		}
	}
	
	/**
	 * Setea la variable de Comunidad del valuestack
	 * @param com Comunidad para el seteo
	 * @return Devuelve SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String SetearComunidad(Comunidad com){
		if(com != null){
			// ------ Seteando el valuestack de comunidad ----------
		
			comunidad.setId(com.getId());
			comunidad.setNombre(com.getNombre());
			comunidad.setFechaCreacion(com.getFechaCreacion());
			comunidad.setUsuariosMax(com.getUsuariosMax());
			comunidad.setTorneosActivosMax(com.getTorneosActivosMax());
			comunidad.setTorneosEnRegistroMax(com.getTorneosEnRegistroMax());
			comunidad.setTorneosMax(com.getTorneosMax());
			
			comunidad.setPasswordProtected(com.isPasswordProtected());
			comunidad.setJoinPassword(com.getJoinPassword());
			comunidad.setRegRequest(com.isRegRequest());
			comunidad.setUsuariosRequest(com.getUsuariosRequest());
			comunidad.setEquiposRequest(com.getEquiposRequest());
			
			comunidad.setUserComunidades(com.getUserComunidades());
			comunidad.setTorneos(com.getTorneos());
			comunidad.setActividades(com.getActividades());
			comunidad.setPlataformas(com.getPlataformas());
			comunidad.setEquipoComunidades(com.getEquipoComunidades());
			
			return SUCCESS;
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva al registro de una comunidad
	 * cambiando a perfil de usuario si fuera necesario
	 * @return SUCCESS
	 */
	public String GoRegister(){
		getSession().put("equipo", 0);
		
		return SUCCESS;
	}
	
	/**
	 * Nos lleva a las noticias de la comunidad
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoNoticiasComunidad(){
		if(SUCCESS == SetearComunidad(RecuperarComunidadDetallada(null))){
			String aliasTable = "noti";
			
			String where = " where "+aliasTable+".parentComunidad="+comunidad.getId();
			String tablasAux = ", CategoriaNoticia as cat ";
			where += " and cat.nombre IN ";
			where += "('AltaUsuarioComunidad','BajaUsuarioComunidad','AltaEquipoComunidad','BajaEquipoComunidad'," +
					"'BaneadoUsuarioComunidad','ExpulsadoUsuarioComunidad','BaneadoEquipoComunidad','ExpulsadoEquipoComunidad'," +
					"'RegistrosAbiertosComunidad','RegistrosCerradosComunidad','PasswordComunidadCambiado'," +
					"'RangoUsuarioComunidadCambiado','ComunidadCreada','ComunidadEliminada'," +
					"'ConfirmacionRegistroComunidadOn','ConfirmacionRegistroComunidadOff'";
			
			if(SUCCESS == FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad, FuncionesAuxiliares.MOD)){
				where += ",'AceptadoRequestUsuarioComunidad','DenegadoRequestUsuarioComunidad','RequestUsuarioComunidad'," +
						"'AceptadoRequestEquipoComunidad','DenegadoRequestEquipoComunidad','RequestEquipoComunidad'";
			}
			
			where += ")";
			where += " and "+aliasTable+" in elements(cat.noticias) order by noti.fechaPublicacion desc";
			
			
			
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			
			noticiaDAO = new NoticiaDAOImpl();
			noticiaPaginatedList = noticiaDAO.listNoticia(request, aliasTable, where, tablasAux);
			
			tipo = "comunidad";
			
			return SUCCESS;
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la zona de administración de la comunidad
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoAdminComunidad(){
		if( SUCCESS == SetearComunidad(RecuperarComunidadDetallada(null))){
			return FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.MOD); // Necesitas ser al menos MOD
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la configuración de la comunidad
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoSettings(){
		if( SUCCESS == SetearComunidad(RecuperarComunidadDetallada(null))){		
			return FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.ADMIN); // Necesitas ser al menos ADMIN
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la zona de eliminación de la comunidad
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoEliminarComunidad(){
		if(SUCCESS == SetearComunidad(RecuperarComunidadDetallada(null))){
			return FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.ADMIN); // Necesitas ser al menos ADMIN
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la zona de seguridad de la comunidad
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoPasswordComunidad(){
		if( SUCCESS == SetearComunidad(RecuperarComunidadDetallada(null))){
			return FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.ADMIN); // Necesitas ser al menos ADMIN
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a las solicitudes de la comunidad
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoRequestsComunidad(){
		if(SUCCESS == SetearComunidad(RecuperarComunidadDetallada(null))){
			if( SUCCESS == FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.MOD)){ // Necesitas ser al menos MOD
				userList = new ArrayList<User>(comunidad.getUsuariosRequest());
				equipoList = new ArrayList<Equipo>(comunidad.getEquiposRequest());
			
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
	 * Nos lleva  a la zona de abandono de la comunidad
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoAbandonarComunidad(){
		if( SUCCESS == SetearComunidad(RecuperarComunidadDetallada(null))){
			return FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.NORMALUSER); // Necesitas ser al menos NORMALUSER
		}
		else{
			return ERROR;
		}
	}	
	
	/**
	 * Nos lleva a la zona de información de la comunidad
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoInfoComunidad(){
		if(SUCCESS == SetearComunidad(RecuperarComunidadDetallada(null))){
			actividadList = new ArrayList<Actividad>(comunidad.getActividades());
			
			Collections.sort(actividadList,new ActividadComparator());
			
			plataformaList = new ArrayList<Plataforma>(comunidad.getPlataformas());
			
			Collections.sort(plataformaList,new PlataformaComparator());
			
			if(SUCCESS == UserListByComunidad()){
				return EquipoListByComunidad();
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
	 * Nos lleva a la zona de administración de usuarios de la comunidad
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoAdminUsuariosComunidad(){
		if(SUCCESS == SetearComunidad(RecuperarComunidadDetallada(null))){
			if( SUCCESS == FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.MOD)){ // Necesitas ser al menos MOD		
				sesion = getSession();
				
				listHashMapUtil = new ListHashMapUtil();
				listHashMapUtil.SacarRangosComunidad(sesion,rolcomunidad,listaRangos);
				
				return UserListByComunidad();
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
	 * Nos lleva a la zona de administración de equipos de la comunidad
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoAdminEquiposComunidad(){
		if(SUCCESS == SetearComunidad(RecuperarComunidadDetallada(null))){
			if( SUCCESS == FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.MOD)){ // Necesitas ser al menos MOD				
				return EquipoListByComunidad();
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
	 * Nos lleva a la zona de torneos de la comunidad
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoTorneosComunidad(){
		if(SUCCESS == SetearComunidad(RecuperarComunidadDetallada(null))){
			return TorneoListByComunidad();
		}
		else{
			return ERROR;
		}
	}	
	
	/**
	 * Nos lleva a la zona de registro de torneo en la comunidad
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoRegisterTorneoComunidad(){
		if( SUCCESS == SetearComunidad(RecuperarComunidadDetallada(null))){
			return FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.POWERUSER); // Necesitas ser al menos POWERUSER
		}
		else{
			return ERROR;
		}
	}	
	
	/**
	 * Nos lleva a la zona de unirse a la comunidad
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoUnirseComunidad(){
		if(SUCCESS == SetearComunidad(RecuperarComunidadDetallada(null))){			 
			if( rolnivelComunidad == FuncionesAuxiliares.NUMERO_NO_VALIDO || rolnivelComunidad == FuncionesAuxiliares.EXMIEMBRO){
				if(comunidad.isRegRequest()){
					Set<Comunidad> setC=null;
					
					if((Integer) sesion.get("equipo") == 0){ // Si está usando el perfil de usuario
						User us = (User)session.load(User.class,(Serializable) sesion.get("id"));
						setC = us.getComunidadesRequest();	
					}
					else{ // Si está usando el perfil de equipo
						Equipo eq = (Equipo)session.load(Equipo.class,(Serializable) sesion.get("equipo"));
						setC = eq.getComunidadesRequest();
					}
					
					requestPendiente = false;
					
					Iterator<Comunidad> it = setC.iterator();
					while(it.hasNext()){
						if(it.next().getId() == comunidad.getId()){
							requestPendiente = true;
							break;
						}
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
	 * Crea una nueva comunidad
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo 
	 */
	@SuppressWarnings("finally")
	public String merge(){
		boolean error = false;	
		boolean exito = false;
		int rango = FuncionesAuxiliares.CREADOR;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{				
				comunidad.setNombre(comunidad.getNombre().trim());
//				System.out.println(" ---- Intentando guardar comunidad "+comunidad.getNombre());
				Comunidad com = (Comunidad) session.createQuery("from Comunidad com where com.nombre= :nom")
																	.setString("nom", comunidad.getNombre())
																	.uniqueResult();
				
				if (com != null){ // Si quieres crear una comunidad ya existente
					Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
							new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
							"global/error/action/erroraction");	

					addActionError(propsError.getProperty("comunidad.nombre.existente"));
				}
				else{
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
	//					setActividad.addAll(RecogerActividades(juegos,session)); // Ya está dentro del Set
						setActividad.addAll(RecogerActividades(juegosdemesa,session));
								
						if(setActividad.isEmpty()){
							Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
									new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
									"global/error/action/erroraction");
								
							addActionError(propsError.getProperty("elegir.actividad"));
						}
						else{
							comunidad.setActividades(setActividad);
							comunidad.setPlataformas(setPlataforma);
								
							if(comunidad.getJoinPassword() != null){
								Properties props = PropertiesUtil.loadProperties("encriptado/encriptado.properties"); 
								StringEncrypter stringEncrypter = new StringEncrypter(props.getProperty("passPhrase"));
									
								comunidad.setJoinPassword(stringEncrypter.encrypt(comunidad.getJoinPassword().trim())); // Encriptamos la contraseña
							}
	
	
							//Primero guardamos la comunidad en la BBDD, para que no haya referencias a transient values
							comunidadDAO = new ComunidadDAOImpl();
							com = comunidadDAO.mergeComunidad(comunidad);
							
							User us = (User)session.load(User.class,(Serializable) sesion.get("id"));
							
							UserComunidad userComunidad = new UserComunidad(rango); 
							userComunidad.setId(new UserComunidadPK(
											us,
											com)); 
							// Seteamos la PK del userComunidad
									
							userComunidadDAO = new UserComunidadDAOImpl();
							userComunidadDAO.mergeUserComunidad(userComunidad);
											
							// --------------------------------------------------------
							// -----  Seteamos el objeto del ValueStack comunidad -----
							// --------------------------------------------------------
							SetearComunidad(com);
							// -------------------------------------------------------
							
							Noticia noticia = new Noticia();
							noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																					"where nombre='ComunidadCreada'").uniqueResult());	

							noticia.setParentComunidad(com);
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
				session.close();
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
	 * Une a un equipo o usuario a la comunidad
	 * @param com Comunidad a la que unirse
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo 
	 */
	@SuppressWarnings("finally")
	public String UnirComunidad(Comunidad com){
		boolean error = false;	
		boolean exito = false;
		int rango = FuncionesAuxiliares.NORMALUSER;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
					if(com == null){ // Si no existe la comunidad y te quieres unir
						Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
								new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
								"global/error/action/erroraction");	
						
						addActionError(propsError.getProperty("comunidad.join.inexistente"));
					}

					else{
						Noticia	noticia = new Noticia();
						
						if((Integer) sesion.get("equipo") == 0){ // Si está usando el perfil de usuario
							User us = (User)session.load(User.class,(Serializable) sesion.get("id"));
							
							UserComunidad userComunidad = new UserComunidad(rango); 
							userComunidad.setId(new UserComunidadPK(
											us,
											com)); 
							// Seteamos la PK del userComunidad
									
							userComunidadDAO = new UserComunidadDAOImpl();
							userComunidadDAO.mergeUserComunidad(userComunidad);				
		
							noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																					"where nombre='AltaUsuarioComunidad'").uniqueResult());
							noticia.setParentUsuario(us);
						}
						else{ // Si está usando el perfil de equipo
							Equipo eq = (Equipo)session.load(Equipo.class,(Serializable) sesion.get("equipo"));
							
							EquipoComunidad equipoComunidad = new EquipoComunidad(rango); 
							equipoComunidad.setId(new EquipoComunidadPK(
											eq,
											com)); 
							// Seteamos la PK del equipoComunidad
									
							equipoComunidadDAO = new EquipoComunidadDAOImpl();
							equipoComunidadDAO.mergeEquipoComunidad(equipoComunidad);				
		
							noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																					"where nombre='AltaEquipoComunidad'").uniqueResult());
							noticia.setParentEquipo(eq);
						}
						
						noticia.setParentComunidad(com);						
						
						noticiaDAO = new NoticiaDAOImpl();
						noticiaDAO.mergeNoticia(noticia);
								
						exito=true;
					}
			}	
			catch(Exception e){
				e.printStackTrace();
				session.close();
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
	 * Une a un equipo o usuario a la comunidad
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo 
	 */
	public String UnirseComunidad()
	{  
		Comunidad com = RecuperarComunidadDetallada(null);
		
		if(SUCCESS == SetearComunidad(com)){
			if( rolnivelComunidad == FuncionesAuxiliares.NUMERO_NO_VALIDO){
				return UnirComunidad(com);
			}
			else if(rolnivelComunidad == FuncionesAuxiliares.EXMIEMBRO){
				return ReJoinComunidad(com);
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
	 * Une a un equipo o usuario a la comunidad con contraseña
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo 
	 */
	@SuppressWarnings("finally")
	public String UnirseComunidadPassword()
	{
		Comunidad com = null;
		boolean error = false;
		boolean exito = false;
		
		try{
			com = RecuperarComunidadDetallada(null);
			
			if( rolnivelComunidad != FuncionesAuxiliares.NUMERO_NO_VALIDO && 
					rolnivelComunidad != FuncionesAuxiliares.EXMIEMBRO){
				return ERROR;					
			}


			
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			
			try{
				if(com != null){
						// Si existe la comunidad, cargamos el encriptador
						Properties props = PropertiesUtil.loadProperties("encriptado/encriptado.properties"); 
						StringEncrypter stringEncrypter = new StringEncrypter(props.getProperty("passPhrase"));
						
						if(comunidad.getJoinPassword().trim().equals(stringEncrypter.decrypt(com.getJoinPassword()))){
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
					
					addActionError(propsError.getProperty("comunidad.join.inexistente"));
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
	 * Vuelve a unir a un equipo o usuario a la comunidad, a la que antes pertenecía
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo 
	 */
	@SuppressWarnings("finally")
	public String ReJoinComunidad(Comunidad com){
		boolean error = false;
		boolean exito = false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				Noticia noticia = new Noticia();
				
				if((Integer) sesion.get("equipo") == 0){ // Si está usando el perfil de usuario
					User us = (User)session.get(User.class,(Serializable)sesion.get("id"));
					
					UserComunidad usCo = (UserComunidad)session.get(UserComunidad.class,
							new UserComunidadPK(us,com));
					
					usCo.setRango(FuncionesAuxiliares.NORMALUSER);
							
					userComunidadDAO = new UserComunidadDAOImpl();				
					userComunidadDAO.mergeUserComunidad(usCo);
					
					
					noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																			"where nombre='AltaUsuarioComunidad'").uniqueResult());
					noticia.setParentUsuario(us);
				}
				else{ // Si está usando el perfil de equipo
					Equipo eq = (Equipo)session.get(Equipo.class,(Serializable)sesion.get("equipo"));
					
					EquipoComunidad eqCo = (EquipoComunidad)session.get(EquipoComunidad.class,
							new EquipoComunidadPK(eq,com));
					
					eqCo.setRango(FuncionesAuxiliares.NORMALUSER);
							
					equipoComunidadDAO = new EquipoComunidadDAOImpl();				
					equipoComunidadDAO.mergeEquipoComunidad(eqCo);
					
					
					noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																			"where nombre='AltaEquipoComunidad'").uniqueResult());
					noticia.setParentEquipo(eq);
				}
				
				noticia.setParentComunidad(com);
								
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
	 * Solicita entrada a la comunidad
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo 
	 */
	@SuppressWarnings("finally")
	public String RequestComunidad()
	{
		boolean error = false;
		boolean exito = false;
		
		try{
			Comunidad com = RecuperarComunidadDetallada(null);
			
			if(SUCCESS == SetearComunidad(com)){
				if( rolnivelComunidad != FuncionesAuxiliares.NUMERO_NO_VALIDO &&
						rolnivelComunidad != FuncionesAuxiliares.EXMIEMBRO){
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			
			try{
				if(com != null){		
					Noticia noticia = new Noticia();
					
					if((Integer) sesion.get("equipo") == 0){ // Si está usando el perfil de usuario
						User us = (User)session.load(User.class,(Serializable) sesion.get("id"));
								
						Set<Comunidad> setC = us.getComunidadesRequest();
						setC.add(com);
								
						us.setComunidadesRequest(setC);
								
						userDAO = new UserDAOImpl();
						userDAO.mergeUser(us);
						
						noticia.setParentCategoriaNoticia((CategoriaNoticia) session.createQuery("from CategoriaNoticia " +
																	"where nombre='RequestUsuarioComunidad'").uniqueResult());	
						noticia.setParentUsuario(us);					
					}
					else { // Si está usando el perfil de equipo
						Equipo eq = (Equipo)session.load(Equipo.class,(Serializable) sesion.get("equipo"));
								
						Set<Comunidad> setC = eq.getComunidadesRequest();
						setC.add(com);
								
						eq.setComunidadesRequest(setC);
								
						equipoDAO = new EquipoDAOImpl();
						equipoDAO.mergeEquipo(eq);
						
						noticia.setParentCategoriaNoticia((CategoriaNoticia) session.createQuery("from CategoriaNoticia " +
																	"where nombre='RequestEquipoComunidad'").uniqueResult());	
						noticia.setParentEquipo(eq);					
					}
					
					noticia.setParentComunidad(com);					
					
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
	 * Solicita entrada a la comunidad con contraseña
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo 
	 */
	@SuppressWarnings("finally")
	public String RequestComunidadPassword()
	{
		boolean error = false;
		boolean exito = false;
		
		try{
			Comunidad com = RecuperarComunidadDetallada(null);
			
			if(SUCCESS == SetearComunidad(com)){
				if( rolnivelComunidad != FuncionesAuxiliares.NUMERO_NO_VALIDO &&
						rolnivelComunidad != FuncionesAuxiliares.EXMIEMBRO){
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				if(com != null){
						// Si existe la comunidad, cargamos el encriptador
						Properties props = PropertiesUtil.loadProperties("encriptado/encriptado.properties"); 
						StringEncrypter stringEncrypter = new StringEncrypter(props.getProperty("passPhrase"));
						
						if(comunidad.getJoinPassword().trim().equals(stringEncrypter.decrypt(com.getJoinPassword()))){
							Noticia noticia = new Noticia();
							
							if((Integer) sesion.get("equipo") == 0){ // Si está usando el perfil de usuario
								User us = (User)session.load(User.class,(Serializable) sesion.get("id"));
								
//								Set<User> setU = com.getUsuariosRequest();
//								setU.add(us);
//								
//								com.setUsuariosRequest(setU);
								
								Set<Comunidad> setC = us.getComunidadesRequest();
								setC.add(com);
								
								us.setComunidadesRequest(setC);
								
//								comunidadDAO = new ComunidadDAOImpl();
//								comunidadDAO.mergeComunidad(com);
								
								userDAO = new UserDAOImpl();
								userDAO.mergeUser(us);
								
								noticia.setParentCategoriaNoticia((CategoriaNoticia) session.createQuery("from CategoriaNoticia " +
										"where nombre='RequestUsuarioComunidad'").uniqueResult());
								noticia.setParentUsuario(us);
							}
							else { // Si está usando el perfil de equipo
								Equipo eq = (Equipo)session.load(Equipo.class,(Serializable) sesion.get("equipo"));
								
//								Set<User> setU = com.getUsuariosRequest();
//								setU.add(us);
//								
//								com.setUsuariosRequest(setU);
								
								Set<Comunidad> setC = eq.getComunidadesRequest();
								setC.add(com);
								
								eq.setComunidadesRequest(setC);
								
//								comunidadDAO = new ComunidadDAOImpl();
//								comunidadDAO.mergeComunidad(com);
								
								equipoDAO = new EquipoDAOImpl();
								equipoDAO.mergeEquipo(eq);
								
								noticia.setParentCategoriaNoticia((CategoriaNoticia) session.createQuery("from CategoriaNoticia " +
										"where nombre='RequestEquipoComunidad'").uniqueResult());
								noticia.setParentEquipo(eq);
							}							
							
							noticia.setParentComunidad(com);							
							
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
					
					addActionError(propsError.getProperty("comunidad.join.inexistente"));
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
	 * Acepta la petición de entrada a la comunidad de un equipo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo 
	 */
	@SuppressWarnings("finally")
	public String AceptarRequestEquipo(){
		boolean error = false;
		boolean exito = false;
		
		try{
			Comunidad com = RecuperarComunidadDetallada(null);
			
			if(SUCCESS == SetearComunidad(com)){			
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.MOD)){ // Necesitas ser al menos MOD
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				Equipo eq = (Equipo)session.get(Equipo.class,(Serializable)idequipo);				
					
				EquipoComunidad equipoComunidad = (EquipoComunidad)session.get(EquipoComunidad.class,
						new EquipoComunidadPK(eq,com));
					
				if(equipoComunidad != null){ // Si es un exmiembro
					if(equipoComunidad.getRango() == FuncionesAuxiliares.EXMIEMBRO){
						equipoComunidad.setRango(FuncionesAuxiliares.NORMALUSER);
					}
					else{
						return ERROR;
					}
				}
				else{
					equipoComunidad = new EquipoComunidad(FuncionesAuxiliares.NORMALUSER);
					equipoComunidad.setId(new EquipoComunidadPK(
									eq,
									com)); 
					// Seteamos la PK del equipoComunidad	
				}
					
							
				equipoComunidadDAO = new EquipoComunidadDAOImpl();
				equipoComunidadDAO.mergeEquipoComunidad(equipoComunidad);		
				
				// Borramos el request de la tabla intermedia.
				BorrarSolicitudEquipo(eq);
				
				Noticia noticia = new Noticia();	
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
									"where nombre='AceptadoRequestEquipoComunidad'").uniqueResult());
				noticia.setParentEquipo(eq);
				noticia.setParentComunidad(com);				
				
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
	 * Deniega la petición de entrada a la comunidad de un equipo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo 
	 */
	@SuppressWarnings("finally")
	public String DenegarRequestEquipo(){
		boolean error = false;
		boolean exito = false;
		
		try{
			Comunidad com = RecuperarComunidadDetallada(null);
			
			if(SUCCESS == SetearComunidad(com)){			
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.MOD)){ // Necesitas ser al menos MOD
					return ERROR;
				}			
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{	
				Equipo eq = (Equipo)session.get(Equipo.class,(Serializable)idequipo);
					
				// Borramos el request de la tabla intermedia.
				BorrarSolicitudEquipo(eq);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																"where nombre='DenegadoRequestEquipoComunidad'").uniqueResult());
				noticia.setParentEquipo(eq);							
				noticia.setParentComunidad(com);
								
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
	 * Acepta la petición de entrada a la comunidad de un usuario
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo 
	 */
	@SuppressWarnings("finally")
	public String AceptarRequestUsuario(){
		boolean error = false;
		boolean exito = false;
		
		try{
			Comunidad com = RecuperarComunidadDetallada(null);
			
			if(SUCCESS == SetearComunidad(com)){			
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.MOD)){ // Necesitas ser al menos MOD
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
					
				UserComunidad userComunidad = (UserComunidad)session.get(UserComunidad.class,
						new UserComunidadPK(us,com));
					
				if(userComunidad != null){ // Si es un exmiembro
					if(userComunidad.getRango() == FuncionesAuxiliares.EXMIEMBRO){
						userComunidad.setRango(FuncionesAuxiliares.NORMALUSER);
					}
					else{
						return ERROR;
					}
				}
				else{
					userComunidad = new UserComunidad(FuncionesAuxiliares.NORMALUSER);
					userComunidad.setId(new UserComunidadPK(
									us,
									com)); 
					// Seteamos la PK del userComunidad	
				}
								
				userComunidadDAO = new UserComunidadDAOImpl();
				userComunidadDAO.mergeUserComunidad(userComunidad);
				
				// Borramos el request de la tabla intermedia.
				BorrarSolicitudUser(us);
					
				Noticia noticia = new Noticia();
					
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
									"where nombre='AceptadoRequestUsuarioComunidad'").uniqueResult());
				noticia.setParentUsuario(us);
				noticia.setParentComunidad(com);				
				
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
	 * Deniega la petición de entrada a la comunidad de un usuario
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo 
	 */
	@SuppressWarnings("finally")
	public String DenegarRequestUsuario(){
		boolean error = false;
		boolean exito = false;
		
		try{
			Comunidad com = RecuperarComunidadDetallada(null);
			
			if(SUCCESS == SetearComunidad(com)){			
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.MOD)){ // Necesitas ser al menos MOD
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
	
				// Borramos el request de la tabla intermedia.
				BorrarSolicitudUser(us);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
															"where nombre='DenegadoRequestUsuarioComunidad'").uniqueResult());
				noticia.setParentUsuario(us);				
				noticia.setParentComunidad(com);
								
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
	 * Borra la solicitud de entrada de un usuario a la comunidad
	 * @param us Usuario del que borrar la solicitud 
	 */
	public void BorrarSolicitudUser(User us){
		Set<Comunidad> setC = us.getComunidadesRequest();
		
		Iterator<Comunidad> it = setC.iterator();
		while(it.hasNext()){
			Comunidad comu = it.next();
			if(comu.getId() == comunidad.getId()){
				setC.remove(comu);
			}
		}
		
		us.setComunidadesRequest(setC);
		
		userDAO = new UserDAOImpl();
		userDAO.mergeUser(us);
	}
	
	/**
	 * Borra la solicitud de entrada de un equipo a la comunidad
	 * @param eq Equipo del que borrar la solicitud 
	 */	
	public void BorrarSolicitudEquipo(Equipo eq){
		Set<Comunidad> setC = eq.getComunidadesRequest();
		
		Iterator<Comunidad> it = setC.iterator();
		while(it.hasNext()){
			Comunidad comu = it.next();
			if(comu.getId() == comunidad.getId()){
				setC.remove(comu);
			}
		}
		
		eq.setComunidadesRequest(setC);
		
		equipoDAO = new EquipoDAOImpl();
		equipoDAO.mergeEquipo(eq);
	}

	/**
	 * Cambiar la confirmación de registro de la comunidad
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String CambiarConfirmacion()
	{
		boolean error=false;
		boolean exito=false;
		
		try{
			Comunidad com = RecuperarComunidadDetallada(null);
			
			if(com != null){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.ADMIN)){ // Necesitas ser al menos ADMIN
					return ERROR;
				}			
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				System.out.println("***----  Dentro de CambiarConfirmacion, comunidad.regRequest es "+comunidad.isRegRequest());
				
				if(com.isRegRequest() == comunidad.isRegRequest()){
					exito = true;
				}
				else{
					com.setRegRequest(comunidad.isRegRequest());
					
					// Guardamos la comunidad en la BBDD
					comunidadDAO = new ComunidadDAOImpl();
					com = comunidadDAO.mergeComunidad(com);
					
					Noticia noticia=null;
					
					if(com.isRegRequest()){
						noticia = new Noticia();
						noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
								"where nombre='ConfirmacionRegistroComunidadOn'").uniqueResult());
					}
					else{
						noticia = new Noticia();
						noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
								"where nombre='ConfirmacionRegistroComunidadOff'").uniqueResult());
					}
					
					noticia.setParentComunidad(com);
					
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
	 * Cambiar la contraseña de la comunidad
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String CambiarPassword()
	{
		boolean error=false;
		boolean exito=false;
		
		try{
			Comunidad com = RecuperarComunidadDetallada(null);
			
			if(com != null){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.ADMIN)){ // Necesitas ser al menos ADMIN
					return ERROR;
				}			
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				if(com.isPasswordProtected() == false && comunidad.isPasswordProtected() == false){
					exito = true;
				}
				else{
					com.setPasswordProtected(comunidad.isPasswordProtected());
					
					if(com.isPasswordProtected()){	
						Properties props = PropertiesUtil.loadProperties("encriptado/encriptado.properties"); 
						StringEncrypter stringEncrypter = new StringEncrypter(props.getProperty("passPhrase"));
						
						com.setJoinPassword(stringEncrypter.encrypt(comunidad.getJoinPassword().trim())); // Encriptamos la contraseña
					}
					else{
						com.setJoinPassword(null);
					}
					
					// Guardamos la comunidad en la BBDD
					comunidadDAO = new ComunidadDAOImpl();
					com = comunidadDAO.mergeComunidad(com);
					
					Noticia noticia = new Noticia();
					noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																			"where nombre='PasswordComunidadCambiado'").uniqueResult());
					noticia.setParentComunidad(com);
					
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
	 * Banear usuario de la comunidad
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String BanearUsuario()
	{
		boolean error=false;
		boolean exito=false;
		
		try{
			Comunidad com = RecuperarComunidadDetallada(null);

			if(SUCCESS == SetearComunidad(com)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.MOD)){ // Necesitas ser al menos MOD
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
				
				UserComunidad usCo = (UserComunidad)session.get(UserComunidad.class,
						new UserComunidadPK(us,com));
						
				usCo.setRango(FuncionesAuxiliares.BANEADO); // Simplemente cambiamos su rango a baneado,
				// Internamente seguirá perteneciendo a la comunidad para poder acceder a sus datos relativos a la comunidad.
				
				userComunidadDAO = new UserComunidadDAOImpl();				
				userComunidadDAO.mergeUserComunidad(usCo);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																		"where nombre='BaneadoUsuarioComunidad'").uniqueResult());
				noticia.setParentComunidad(com);
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
	 * Banear equipo de la comunidad
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String BanearEquipo()
	{
		boolean error=false;
		boolean exito=false;
		
		try{
			Comunidad com = RecuperarComunidadDetallada(null);

			if(SUCCESS == SetearComunidad(com)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.MOD)){ // Necesitas ser al menos MOD
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				Equipo eq = (Equipo)session.get(Equipo.class,(Serializable)idequipo);
				
				EquipoComunidad eqCo = (EquipoComunidad)session.get(EquipoComunidad.class,
						new EquipoComunidadPK(eq,com));
						
				eqCo.setRango(FuncionesAuxiliares.BANEADO); // Simplemente cambiamos su rango a baneado,
				// Internamente seguirá perteneciendo a la comunidad para poder acceder a sus datos relativos a la comunidad.
				
				equipoComunidadDAO = new EquipoComunidadDAOImpl();				
				equipoComunidadDAO.mergeEquipoComunidad(eqCo);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																		"where nombre='BaneadoEquipoComunidad'").uniqueResult());
				noticia.setParentComunidad(com);
				noticia.setParentEquipo(eq);
				
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
	 * Expulsar usuario de la comunidad
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String ExpulsarUsuario()
	{
		boolean error=false;
		boolean exito=false;
		
		try{
			Comunidad com = RecuperarComunidadDetallada(null);
			if(SUCCESS == SetearComunidad(com)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.MOD)){ // Necesitas ser al menos MOD
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
				
				UserComunidad usCo = (UserComunidad)session.get(UserComunidad.class,
						new UserComunidadPK(us,com));
						
				usCo.setRango(FuncionesAuxiliares.EXMIEMBRO); // Simplemente cambiamos su rango a baneado,
				// Internamente seguirá perteneciendo a la comunidad para poder acceder a sus datos relativos a la comunidad.
				
				userComunidadDAO = new UserComunidadDAOImpl();				
				userComunidadDAO.mergeUserComunidad(usCo);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																		"where nombre='ExpulsadoUsuarioComunidad'").uniqueResult());
				noticia.setParentComunidad(com);
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
	 * Expulsar equipo de la comunidad
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String ExpulsarEquipo()
	{
		boolean error=false;
		boolean exito=false;
		
		try{
			Comunidad com = RecuperarComunidadDetallada(null);
			if(SUCCESS == SetearComunidad(com)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.MOD)){ // Necesitas ser al menos MOD
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				Equipo eq = (Equipo)session.get(Equipo.class,(Serializable)idequipo);				
				
				EquipoComunidad eqCo = (EquipoComunidad)session.get(EquipoComunidad.class,
						new EquipoComunidadPK(eq,com));
						
				eqCo.setRango(FuncionesAuxiliares.EXMIEMBRO); // Simplemente cambiamos su rango a baneado,
				// Internamente seguirá perteneciendo a la comunidad para poder acceder a sus datos relativos a la comunidad.
				
				equipoComunidadDAO = new EquipoComunidadDAOImpl();				
				equipoComunidadDAO.mergeEquipoComunidad(eqCo);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																		"where nombre='ExpulsadoEquipoComunidad'").uniqueResult());
				noticia.setParentComunidad(com);
				noticia.setParentEquipo(eq);
				
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
	 * Abandonar la comunidad
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String QuitarseComunidad()
	{
		boolean error=false;
		boolean exito=false;
		
		try{
			Comunidad com = RecuperarComunidadDetallada(null);
			
			if(SUCCESS == SetearComunidad(com)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.NORMALUSER)){ // Necesitas ser al menos NORMALUSER
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				Noticia noticia = new Noticia();
				
				if((Integer) sesion.get("equipo") == 0){ // Si está usando el perfil de usuario
					User us = (User)session.get(User.class,(Serializable) sesion.get("id"));
					
					UserComunidad usCo = (UserComunidad)session.get(UserComunidad.class,
							new UserComunidadPK(us,com));
					
					usCo.setRango(FuncionesAuxiliares.EXMIEMBRO);
					
					userComunidadDAO = new UserComunidadDAOImpl();
					userComunidadDAO.mergeUserComunidad(usCo);
										
					noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																			"where nombre='BajaUsuarioComunidad'").uniqueResult());
					noticia.setParentUsuario(us);
				}
				else{ // Si está usando el perfil de equipo
					Equipo eq = (Equipo)session.get(Equipo.class,(Serializable) sesion.get("equipo"));
					
					EquipoComunidad eqCo = (EquipoComunidad)session.get(EquipoComunidad.class,
							new EquipoComunidadPK(eq,com));
					
					eqCo.setRango(FuncionesAuxiliares.EXMIEMBRO);
					
					equipoComunidadDAO = new EquipoComunidadDAOImpl();
					equipoComunidadDAO.mergeEquipoComunidad(eqCo);
										
					noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																			"where nombre='BajaEquipoComunidad'").uniqueResult());
					noticia.setParentEquipo(eq);
				}
				
				noticia.setParentComunidad(com);				
				
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
	 * Modificar configuración de la comunidad
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String ModificarSettings()
	{
		boolean error=false;
		boolean exito=false;
		boolean distinto=false;
		
		try{
			Comunidad com = RecuperarComunidadDetallada(null);
			
			if(SUCCESS == SetearComunidad(com)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.ADMIN)){ // Necesitas ser al menos ADMIN
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
						 Set<Actividad> setA = com.getActividades();
						 Set<Plataforma> setP = com.getPlataformas();
						 
						 if(!setActividad.equals(setA)){
							 comunidad.setActividades(setActividad);
							 distinto=true;
						 }
						 if(!setPlataforma.equals(setP)){
							 comunidad.setPlataformas(setPlataforma);
							 distinto=true;
						 }
						 
						 if(distinto){
							// Si se ha hecho algún cambio
							
							comunidadDAO = new ComunidadDAOImpl();
							comunidadDAO.mergeComunidad(comunidad);
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
	 * Modificar rango de usuario dentro de la comunidad
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String EditarRangoUser(){
		boolean error=false;
		boolean exito=false;
		
		try{
			Comunidad com = RecuperarComunidadDetallada(null);
			
			if(SUCCESS == SetearComunidad(com)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad,FuncionesAuxiliares.MOD)){ // Necesitas ser al menos MOD
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
				
				UserComunidad usCo = (UserComunidad)session.get(UserComunidad.class,
						new UserComunidadPK(us,com));
				
//				String rangoUs = FuncionesAuxiliares.StringRango(usCo.getRango());
				
				if(rangoCom != usCo.getRango()){
					usCo.setRango(rangoCom);
							
					userComunidadDAO = new UserComunidadDAOImpl();				
					userComunidadDAO.mergeUserComunidad(usCo);
					
					Noticia noticia = new Noticia();
					noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																			"where nombre='RangoUsuarioComunidadCambiado'").uniqueResult());
					noticia.setNuevoRango(rangoCom);
					noticia.setParentComunidad(com);
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
	 * Buscador de comunidades
	 * @return SUCCESS
	 */
	public String BuscarComunidad(){
		String where="";
		String aliasTable="com";

		// ------------------- SearchWord -----------------------------
		if(comunidad.getNombre() != null && !comunidad.getNombre().trim().equals("")){
			where = " where "+aliasTable+".nombre like '%"+comunidad.getNombre()+"%'";
		}			
		
		//------------------ CondicionWhere ---------------------------
		///////////////////////////////////////////////////////////////
		//------------------ Extra ------------------------------------

		String tablasAux="";
		
		if(passProtected != null && passProtected != 0){
			where += FuncionesAuxiliares.WhereOrAnd(where);
			
			if(passProtected == 1){
				where += " passwordProtected=true";	
			}
			else if(passProtected == 2){
				where += " passwordProtected=false";
			}			
		}
		
		if(regReq != null && regReq != 0){
			where += FuncionesAuxiliares.WhereOrAnd(where);
			
			if(regReq == 1){
				where += " regRequest=true";	
			}
			else if(regReq == 2){
				where += " regRequest=false";
			}			
		}
		
		if(plataforma!=null && plataforma != 0){
			where += FuncionesAuxiliares.WhereOrAnd(where);
			
			tablasAux = ",Plataforma as pl ";
			where += " pl.id="+plataforma+" and "+aliasTable+" in elements(pl.comunidades)";
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
			where += FuncionesAuxiliares.WhereOrAnd(where);
			
			tablasAux += ",Actividad as act ";
			where += " act.id="+acti+" and "+aliasTable+" in elements(act.comunidades)";			
		}
				
		comunidadDAO = new ComunidadDAOImpl();
//		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
//		comunidadPaginatedList = comunidadDAO.listComunidadQuery(request, aliasTable, where, tablasAux);
		
		comunidadList = comunidadDAO.listComunidadQuery(aliasTable, where, tablasAux);

		return SUCCESS;
	}
	
	/**
	 * Comprobar permiso de borrado en la comunidad
	 * @return SUCCESS en caso de éxito, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String ComprobarPermisoBorrado(){
		boolean error = false;	
		
		try{
			System.out.println("Entramos a ComprobarPermisosBorrado...");
			Comunidad com = RecuperarComunidadDetallada(null);
			if(SUCCESS == SetearComunidad(com)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelComunidad, FuncionesAuxiliares.ADMIN)){ // Hay que ser al menos ADMIN
					error =true;
				}
			}
			else{
				error =true;
			}
		}
		catch(HibernateException he){
			he.printStackTrace();
			error = true;
		}
		
		finally{
			System.out.println("Salimos de ComprobarPermisosBorrado...");
			
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
			if(error){
				return ERROR;
			}
			else{
				return SUCCESS;
			}	
		}
	}
	
	/**
	 * Eliminar la comunidad
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String delete()
	{	
		boolean error = false;	
		boolean exito = false;
		
		try{			
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			
			try{
				
				System.out.println("Dentro de delete(), después de comprobar permisos");
	
	//			sessionFactory = HibernateUtil.getSessionFactory();
	//			session = sessionFactory.openSession();
				Comunidad com = new Comunidad(comunidad.getId());
				
				String hql = "delete from User where invitado= :invitado and id IN " +
						"(select id.parentUser from UserTorneo where id.parentTorneo IN (select id from Torneo where id.parentComunidad= :comuni))";
				session.createQuery(hql).setBoolean("invitado", true).setInteger("parentComunidad",com.getId()).executeUpdate();
				
				System.out.println(" HQL ---> "+hql);
				
				hql = "delete from Equipo where invitado= :invitado and id IN " +
						"(select id.parentEquipo from EquipoTorneo where id.parentTorneo IN (select id from Torneo where id.parentComunidad= :comuni))";
				session.createQuery(hql).setBoolean("invitado", true).setInteger("parentComunidad",com.getId()).executeUpdate();
				
				System.out.println(" HQL ---> "+hql);
				
				comunidadDAO = new ComunidadDAOImpl();
				comunidadDAO.deleteComunidad(com);
					
	//			session.delete(comunidad);
	//				Noticia noticia = new Noticia("Comunidad Eliminada","La comunidad ha sido eliminado");
	//				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
	//																		"where nombre='ComunidadEliminada'").uniqueResult());
	////				noticia.setParentComunidad(com);
	//				
	//				noticiaDAO = new NoticiaDAOImpl();
	//				noticiaDAO.mergeNoticia(noticia);
					
					exito=true;			
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
//				if(session != null){
//					try{
//						session.close();
//					}
//					catch(HibernateException he){
//						// Informar de error haciendo cierre de sesion
//						he.printStackTrace();
//						session.close();
//						return ERROR;
//					}
//				}
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
				System.out.println("********^^^^^^ iduser --> "+iduser);
				
				UserComunidad usCo = (UserComunidad)session.get(UserComunidad.class,
						new UserComunidadPK((User)session.get(User.class,(Serializable)iduser),
											(Comunidad)session.get(Comunidad.class, comunidad.getId())));
				
				cadena = FuncionesAuxiliares.StringRango(usCo.getRango());
				
				System.out.println("********^^^^^^ cadena --> "+cadena);
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
	 * Lista todas las comunidades
	 * @return SUCCESS
	 */
	public String ComunidadList(){
//		System.out.println("Dentro de ComunidadList");
		//		comunidadList = new ArrayList<Comunidad>();
		comunidadDAO = new ComunidadDAOImpl();
		
//		comunidadList = comunidadDAO.listComunidad();
//		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		
//		comunidadPaginatedList = comunidadDAO.listComunidad(request);
		
		comunidadList = comunidadDAO.listComunidad();
		
		return SUCCESS;
	}
	
	/**
	 * Lista los usuarios de la comunidad
	 * @return SUCCESS
	 */
	public String UserListByComunidad(){
//		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		
		userDAO = new UserDAOImpl();

//		userPaginatedList = userDAO.listUserByComunidad(request,comunidad);
		
		userList = userDAO.listUserByComunidad(comunidad);
		
		return SUCCESS;
	}
	
	/**
	 * Lista los equipos de la comunidad
	 * @return SUCCESS
	 */
	public String EquipoListByComunidad(){
		
		equipoDAO = new EquipoDAOImpl();
		
		equipoList = equipoDAO.listEquipoByComunidad(comunidad);
		
		return SUCCESS;
	}
		
	/**
	 * Lista las comunidades propias del usuario o equipo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String ComunidadListPropias(){
		boolean error=false;
		boolean exito=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				if((Integer)sesion.get("equipo") == 0){
					User us = (User)session.get(User.class,(Serializable) sesion.get("id"));
					
					userComunidadList = new ArrayList<UserComunidad>(us.getUserComunidades());
					
					exito = true;
				}
				else{
					Equipo eq = (Equipo)session.get(Equipo.class,(Serializable) sesion.get("equipo"));
					
					equipoComunidadList = new ArrayList<EquipoComunidad>(eq.getEquipoComunidades());
					
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
	 * Nos lleva a la zona de administración de todas las comunidades de la aplicación
	 * @return SUCCESS
	 */
	public String GoAdminComunidades(){
		return BuscarComunidad();
	}
	
	/**
	 * Mete al valuestack los valores para las estadísticas de la comunidad
	 * @return SUCCESS en caso de éxito, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String EstadisticasComunidad(){
		Integer noComenzados = 0;
		Integer finalizados = 0;
		Integer enJuego = 0;
		
		boolean error=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{	
				Comunidad com = (Comunidad)session.load(Comunidad.class,comunidad.getId());
				
				SetearComunidad(com);

				
				Set<Torneo> setT = com.getTorneos();
				Iterator<Torneo> it = setT.iterator();
				
				while(it.hasNext()){
					Integer estado = it.next().getEstado();
					
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
				
//				System.out.println("---- Torneos -> no comenzados: "+noComenzados+"  en juego: "+enJuego+"  finalizados: "+finalizados);
			
				listTorneosNoComenzados.add(new IntegerInteger(1,noComenzados));
				listTorneosEnJuego.add(new IntegerInteger(1,enJuego));
				listTorneosFinalizados.add(new IntegerInteger(1,finalizados));
				
				Integer tam = 0;
				Set<UserComunidad> setUC = com.getUserComunidades();
				Iterator<UserComunidad> itUC = setUC.iterator();
				
				while(itUC.hasNext()){
					if(SUCCESS == FuncionesAuxiliares.ComprobarPermisos(itUC.next().getRango(), 
													FuncionesAuxiliares.NORMALUSER)){
						tam++;
					}
				}
				
				listUsuarios.add(new IntegerInteger(2,tam));
				
				
				tam = 0;
				Set<EquipoComunidad> setEC = com.getEquipoComunidades();
				Iterator<EquipoComunidad> itEC = setEC.iterator();
				
				while(itEC.hasNext()){
					if(SUCCESS == FuncionesAuxiliares.ComprobarPermisos(itEC.next().getRango(), 
													FuncionesAuxiliares.NORMALUSER)){
						tam++;
					}
				}
				
				
				listEquipos.add(new IntegerInteger(3,tam));
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
	 * Comprueba que ningún torneo de la comunidad esté en juego
	 * @return SUCCESS en caso de éxito, y ERROR si hay algún torneo en juego
	 * u ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String ComprobarEstadoTorneos(){
		boolean error=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{	
				Comunidad com = (Comunidad)session.load(Comunidad.class,comunidad.getId());
				
				Set<Torneo> setT = com.getTorneos();
				Iterator<Torneo> it = setT.iterator();
				
				while(it.hasNext() && error == false){
					if(it.next().getEstado() == FuncionesAuxiliares.EN_JUEGO){
						error = true;
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
	 * Lista las comunidades del equipo
	 * @return SUCCESS
	 */
	public String ComunidadListByEquipo(){
		comunidadDAO = new ComunidadDAOImpl();
		
		comunidadList = comunidadDAO.listComunidadByIdEquipo((Integer)sesion.get("equipo"));
		
		return SUCCESS;
	}
	
	/**
	 * Lista las comunidades del usuario
	 * @return SUCCESS
	 */
	public String ComunidadListByUser() {
		comunidadDAO = new ComunidadDAOImpl();
		
		comunidadList = comunidadDAO.listComunidadByIdUser((Integer)sesion.get("id"));
		
		return SUCCESS;
	}
	
	/**
	 * Lista los torneos de la comunidad
	 * @return SUCCESS
	 */
	public String TorneoListByComunidad(){
//		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		
//		torneoDAO = new TorneoDAOImpl();

//		torneoPaginatedList = torneoDAO.listTorneoByComunidad(request,comunidad);
		
		torneoList =  new ArrayList<Torneo>(comunidad.getTorneos());
		
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
	
	public void prepareGoSettings(){
		System.out.println("------- prepareGoSettings() -----------");
		
		sesion = getSession();
		
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarActividades(sesion, null, listaDeportes, listaCartas, listaJuegosdemesa, listaJuegos);
		
		listHashMapUtil.SacarPlataformas(sesion, null, listaPlataformas);
	}
	
//	public void prepareGoAdminUsuariosComunidad(){
//		System.out.println("-------- prepareGoUsuariosComunidad()  -------------");
//	}
	
//	public void prepareModificarSettings(){
//		System.out.println("------- prepareModificarSettingsComunidad() -----------");
//		
//		sesion = getSession();
//		
//		listHashMapUtil = new ListHashMapUtil();
//		listHashMapUtil.SacarActividades(sesion, null, listaDeportes, listaCartas, listaJuegosdemesa, listaJuegos);
//		
//		listHashMapUtil.SacarPlataformas(sesion, null, listaPlataformas);
//	}
	
	public void prepareGoSearch(){
		System.out.println("------- prepareGoSearch() -----------");
		
		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarActividades(sesion, "todas", listaDeportes, listaCartas, listaJuegosdemesa, listaJuegos);
		listHashMapUtil.SacarPlataformas(sesion, "todas", listaPlataformas);
		listHashMapUtil.SacarBool(sesion,"todas",listaBooleanInteger);
	}
	
	public void prepareBuscarComunidad(){
		System.out.println("------- prepareBuscarComunidad() -----------");
		
		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarActividades(sesion, "todas", listaDeportes, listaCartas, listaJuegosdemesa, listaJuegos);
		listHashMapUtil.SacarPlataformas(sesion, "todas", listaPlataformas);
		listHashMapUtil.SacarBool(sesion,"todas",listaBooleanInteger);
	}
	
	public void prepareGoPasswordComunidad(){
		System.out.println("------- prepareGoPasswordComunidad() -----------");
		
		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarBool(sesion, listaBoolean);
	}	
	
	public void prepareEditarRango(){
		System.out.println("--------- prepareEditarRango() -------------");
		
		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarRangosComunidad(sesion,rolcomunidad,listaRangos);
		
	}

	/**
	 * Nos lleva a la zona de búsqueda de comunidades
	 * @return SUCCESS
	 */
	public String GoSearch(){
		return SUCCESS;
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
					System.out.println("RecogerPlataformas...   plataforma --> "+plataforma.getNombre());
					setPlataforma.add(plataforma);
				}
			}
		}
		
		return setPlataforma;
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

