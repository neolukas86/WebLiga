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

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.ServletActionContext;
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
import com.proyecto.dao.TorneoDAO;
import com.proyecto.dao.TorneoDAOImpl;
import com.proyecto.dao.UserDAO;
import com.proyecto.dao.UserDAOImpl;
import com.proyecto.dao.UserTorneoDAO;
import com.proyecto.dao.UserTorneoDAOImpl;

import com.proyecto.dominio.Actividad;
import com.proyecto.dominio.CategoriaNoticia;
import com.proyecto.dominio.Comunidad;
import com.proyecto.dominio.Equipo;
import com.proyecto.dominio.EquipoComunidad;
import com.proyecto.dominio.EquipoComunidadPK;
import com.proyecto.dominio.EquipoInvitado;
import com.proyecto.dominio.EquipoTorneo;
import com.proyecto.dominio.EquipoTorneoPK;
import com.proyecto.dominio.IntegerInteger;
import com.proyecto.dominio.Noticia;
import com.proyecto.dominio.PartidoEquiposVersus;
import com.proyecto.dominio.PartidoIndividualVersus;
import com.proyecto.dominio.Plataforma;
import com.proyecto.dominio.Regla;
import com.proyecto.dominio.Torneo;
import com.proyecto.dominio.User;
import com.proyecto.dominio.UserComunidad;
import com.proyecto.dominio.UserComunidadPK;
import com.proyecto.dominio.UserInvitado;
import com.proyecto.dominio.UserTorneo;
import com.proyecto.dominio.UserTorneoPK;

import com.proyecto.util.displaytag.Paginate;
import com.proyecto.util.encriptado.StringEncrypter;
import com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares;
import com.proyecto.util.hibernate.HibernateUtil;
import com.proyecto.util.listas.JornadaListaTantosComparator;
import com.proyecto.util.listas.ListHashMapUtil;
import com.proyecto.util.listas.PosicionEquipoTorneoComparator;
import com.proyecto.util.listas.PosicionUserTorneoComparator;
import com.proyecto.util.properties.GlobalResourceUtil;
import com.proyecto.util.properties.PropertiesUtil;
import com.proyecto.util.sorteo.SorteoUtil;

/**
* Esta clase implementa el Action asociado al
* torneo.
* @author Lucas Sánchez López
* @version 3.0
*/

public class TorneoAction extends ActionSupport implements ModelDriven<Torneo>, Preparable, SessionAware{
	private static final long serialVersionUID = 1L;
	private Torneo torneo = new Torneo();
	private User user = new User();
	private User campeon = new User();
	private Equipo equipo = new Equipo();
	private Comunidad comunidad = new Comunidad();
	private List<Torneo> torneoList;
	private List<User> userList;
	private List<UserInvitado> userInvitadoList;
	private List<EquipoInvitado> equipoInvitadoList;
	private List<Equipo> equipoList;
	private List<Noticia> noticiaList;
	private List<UserTorneo> userTorneoList;
	private List<EquipoTorneo> equipoTorneoList;
	private List<Actividad> actividadList;
	private List<PartidoIndividualVersus> partidoIndList;
	private List<PartidoEquiposVersus> partidoEqList;
	private List<PartidoEquiposVersus> partidosRechazadosEquiposList;
	private List<PartidoIndividualVersus> partidosRechazadosIndividualList;
	private Paginate torneoPaginatedList;
	private Paginate userPaginatedList;
	private Paginate equipoPaginatedList;
	private Paginate partidoPaginatedList;
	private Paginate noticiaPaginatedList;
	private ListHashMapUtil listHashMapUtil;
//	private UserDAO userDAO = new UserDAOImpl();
	private TorneoDAO torneoDAO;
	private UserTorneoDAO userTorneoDAO;
	private UserDAO userDAO;
	private EquipoDAO equipoDAO;
	private PartidoDAO partidoDAO;
	private NoticiaDAO noticiaDAO;
	private EquipoTorneoDAO equipoTorneoDAO;
	private int actividad=0;
	private int rondasExtras;
	private Boolean participante;
	private Boolean equipoDentro;
	private Boolean equipoDentroComunidad;
	private boolean joinedComunidad;
	private LinkedHashMap<Boolean,String> listaBooleanOnline = new LinkedHashMap<Boolean,String>();
	private LinkedHashMap<Boolean,String> listaBoolean = new LinkedHashMap<Boolean,String>();
	private LinkedHashMap<Integer,String> listaBooleanInteger = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Boolean,String> listaTipoTorneoBoolean = new LinkedHashMap<Boolean,String>();
	private LinkedHashMap<Integer,String> listaTipoTorneoInteger = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Boolean,String> listaTipoParticipanteBoolean = new LinkedHashMap<Boolean,String>();
	private LinkedHashMap<Integer,String> listaTipoParticipanteInteger = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaRondas = new LinkedHashMap<Integer,String>();
//	private LinkedHashMap<Integer,String> listaActividades = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaEstados = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaDeportes = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaCartas= new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaJuegos= new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaJuegosdemesa= new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaPlataformas= new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaRangos = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaParticipante = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<String,String> listaEstadisticas = new LinkedHashMap<String,String>();
	private LinkedHashMap<String,Integer> IntegerFromMap = new LinkedHashMap<String,Integer>();
	private LinkedHashMap<Integer,String> listaReglas = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaInvitados = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaJornadasJugables = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaJornadas = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,Integer> MapTantosFavor = new LinkedHashMap<Integer,Integer>();
	private LinkedHashMap<Integer,Integer> MapTantosContra = new LinkedHashMap<Integer,Integer>();
	private List<IntegerInteger> listaTantosFavor = new ArrayList<IntegerInteger>();
	private List<IntegerInteger> listaTantosContra = new ArrayList<IntegerInteger>();
	private List<IntegerInteger> listUsuarios = new ArrayList<IntegerInteger>();
	private List<IntegerInteger> listEquipos = new ArrayList<IntegerInteger>();
	private Map sesion;
	private final static int REG_CERRADOS = 0;
	private final static int REG_ABIERTOS = 1;
	private final static int EN_JUEGO = 2;
	private final static int FINALIZADO = 3;
	private String roltorneo;
	private Integer rolnivelTorneo = FuncionesAuxiliares.NUMERO_NO_VALIDO;
	private String estadistica;
	private Integer participanteTorneo;
	private int idcom;
	private Integer VarOnline;
	private Integer VarPorEquipos;
	private Integer VarLiga;
	private Integer deporte;
	private Integer carta;
	private Integer juego;
	private Integer juegodemesa;
	private Integer plataforma;
	private Integer iduser;
	private Integer idequipo;
	private Integer rangoTor;
	private String tab;
	private String subTab;
	private Integer numPartidosConfirmados = 0;
	private Integer numPartidosNoConfirmados = 0;
	private Integer tantosFavor = 0;
	private Integer tantosContra = 0;
	private Integer entero;
	private Boolean requestPendiente;
	private String tipo;
	private String cadena;
	private Boolean empieza;
	private String auto;
	private Integer passProtected;
	private Integer regReq;
	
	
	SessionFactory sessionFactory = null;
	
	@SessionTarget
	Session session = null;
	
	@Override
	public Torneo getModel() {
		// TODO Auto-generated method stub
		return torneo;
	}
	
	
	

	public User getCampeon() {
		return campeon;
	}




	public void setCampeon(User campeon) {
		this.campeon = campeon;
	}

	public LinkedHashMap<Integer, Integer> getMapTantosFavor() {
		return MapTantosFavor;
	}




	public void setMapTantosFavor(LinkedHashMap<Integer, Integer> mapTantosFavor) {
		MapTantosFavor = mapTantosFavor;
	}




	public LinkedHashMap<Integer, Integer> getMapTantosContra() {
		return MapTantosContra;
	}




	public void setMapTantosContra(LinkedHashMap<Integer, Integer> mapTantosContra) {
		MapTantosContra = mapTantosContra;
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




	public List<IntegerInteger> getListaTantosFavor() {
		return listaTantosFavor;
	}




	public void setListaTantosFavor(List<IntegerInteger> listaTantosFavor) {
		this.listaTantosFavor = listaTantosFavor;
	}




	public List<IntegerInteger> getListaTantosContra() {
		return listaTantosContra;
	}




	public void setListaTantosContra(List<IntegerInteger> listaTantosContra) {
		this.listaTantosContra = listaTantosContra;
	}




	//////////////////////////////////////
	public LinkedHashMap<Boolean, String> getListaBoolean() {
		return listaBoolean;
	}

	public void setListaBoolean(LinkedHashMap<Boolean, String> listaBoolean) {
		this.listaBoolean = listaBoolean;
	}

	public LinkedHashMap<Integer, String> getListaBooleanInteger() {
		return listaBooleanInteger;
	}

	public void setListaBooleanInteger(LinkedHashMap<Integer, String> listaBooleanInteger) {
		this.listaBooleanInteger = listaBooleanInteger;
	}

	public LinkedHashMap<Boolean, String> getListaTipoTorneoBoolean() {
		return listaTipoTorneoBoolean;
	}

	public void setListaTipoTorneoBoolean(LinkedHashMap<Boolean, String> listaTipoTorneoBoolean) {
		this.listaTipoTorneoBoolean = listaTipoTorneoBoolean;
	}
	
	public LinkedHashMap<Integer, String> getListaTipoTorneoInteger() {
		return listaTipoTorneoInteger;
	}

	public void setListaTipoTorneoInteger(
			LinkedHashMap<Integer, String> listaTipoTorneoInteger) {
		this.listaTipoTorneoInteger = listaTipoTorneoInteger;
	}

	public LinkedHashMap<Boolean, String> getListaTipoParticipanteBoolean() {
		return listaTipoParticipanteBoolean;
	}

	public LinkedHashMap<Integer, String> getListaReglas() {
		return listaReglas;
	}

	
	public void setListaReglas(LinkedHashMap<Integer, String> listaReglas) {
		this.listaReglas = listaReglas;
	}

	public Boolean getEmpieza() {
		return empieza;
	}

	public void setEmpieza(Boolean empieza) {
		this.empieza = empieza;
	}

	public LinkedHashMap<Boolean, String> getListaBooleanOnline() {
		return listaBooleanOnline;
	}
	
	
	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	public Boolean getEquipoDentro() {
		return equipoDentro;
	}

	public void setEquipoDentro(Boolean equipoDentro) {
		this.equipoDentro = equipoDentro;
	}

	public Boolean getEquipoDentroComunidad() {
		return equipoDentroComunidad;
	}

	public void setEquipoDentroComunidad(Boolean equipoDentroComunidad) {
		this.equipoDentroComunidad = equipoDentroComunidad;
	}

	public void setListaBooleanOnline(LinkedHashMap<Boolean, String> listaBooleanOnline) {
		this.listaBooleanOnline = listaBooleanOnline;
	}

	public void setListaTipoParticipanteBoolean(
			LinkedHashMap<Boolean, String> listaTipoParticipanteBoolean) {
		this.listaTipoParticipanteBoolean = listaTipoParticipanteBoolean;
	}
	
	public LinkedHashMap<Integer, String> getListaTipoParticipanteInteger() {
		return listaTipoParticipanteInteger;
	}

	public LinkedHashMap<Integer, String> getListaParticipante() {
		return listaParticipante;
	}	

	public List<EquipoInvitado> getEquipoInvitadoList() {
		return equipoInvitadoList;
	}

	public void setEquipoInvitadoList(List<EquipoInvitado> equipoInvitadoList) {
		this.equipoInvitadoList = equipoInvitadoList;
	}

	public void setListaParticipante(LinkedHashMap<Integer, String> listaParticipante) {
		this.listaParticipante = listaParticipante;
	}

	public void setListaTipoParticipanteInteger(
			LinkedHashMap<Integer, String> listaTipoParticipanteInteger) {
		this.listaTipoParticipanteInteger = listaTipoParticipanteInteger;
	}

	public LinkedHashMap<Integer, String> getListaRondas() {
		return listaRondas;
	}

	public void setListaRondas(LinkedHashMap<Integer, String> listaRondas) {
		this.listaRondas = listaRondas;
	}
	
	public LinkedHashMap<Integer, String> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(LinkedHashMap<Integer, String> listaEstados) {
		this.listaEstados = listaEstados;
	}	
	
	public LinkedHashMap<String, String> getListaEstadisticas() {
		return listaEstadisticas;
	}

	public void setListaEstadisticas(LinkedHashMap<String, String> listaEstadisticas) {
		this.listaEstadisticas = listaEstadisticas;
	}	
	
	public List<Equipo> getEquipoList() {
		return equipoList;
	}

	public void setEquipoList(List<Equipo> equipoList) {
		this.equipoList = equipoList;
	}

	public List<Noticia> getNoticiaList() {
		return noticiaList;
	}

	public void setNoticiaList(List<Noticia> noticiaList) {
		this.noticiaList = noticiaList;
	}

	public Paginate getNoticiaPaginatedList() {
		return noticiaPaginatedList;
	}

	public void setNoticiaPaginatedList(Paginate noticiaPaginatedList) {
		this.noticiaPaginatedList = noticiaPaginatedList;
	}	
	
	
	
	
	
	
	//////////////////////////////////////




	public String getAuto() {
		return auto;
	}

	public void setAuto(String auto) {
		this.auto = auto;
	}

	public LinkedHashMap<Integer, String> getListaJornadasJugables() {
		return listaJornadasJugables;
	}

	public List<PartidoEquiposVersus> getPartidosRechazadosEquiposList() {
		return partidosRechazadosEquiposList;
	}

	public void setPartidosRechazadosEquiposList(
			List<PartidoEquiposVersus> partidosRechazadosEquiposList) {
		this.partidosRechazadosEquiposList = partidosRechazadosEquiposList;
	}

	public List<PartidoIndividualVersus> getPartidosRechazadosIndividualList() {
		return partidosRechazadosIndividualList;
	}

	public void setPartidosRechazadosIndividualList(
			List<PartidoIndividualVersus> partidosRechazadosIndividualList) {
		this.partidosRechazadosIndividualList = partidosRechazadosIndividualList;
	}

	public void setListaJornadasJugables(
			LinkedHashMap<Integer, String> listaJornadasJugables) {
		this.listaJornadasJugables = listaJornadasJugables;
	}

	public String getSubTab() {
		return subTab;
	}

	public void setSubTab(String subTab) {
		this.subTab = subTab;
	}

	public Integer getIdequipo() {
		return idequipo;
	}

	public void setIdequipo(Integer idequipo) {
		this.idequipo = idequipo;
	}

	public LinkedHashMap<Integer, String> getListaInvitados() {
		return listaInvitados;
	}

	public void setListaInvitados(LinkedHashMap<Integer, String> listaInvitados) {
		this.listaInvitados = listaInvitados;
	}

	public Torneo getTorneo() {
		return torneo;
	}
	public Boolean getRequestPendiente() {
		return requestPendiente;
	}

	public void setRequestPendiente(Boolean requestPendiente) {
		this.requestPendiente = requestPendiente;
	}

	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LinkedHashMap<Integer, String> getListaRangos() {
		return listaRangos;
	}

	public void setListaRangos(LinkedHashMap<Integer, String> listaRangos) {
		this.listaRangos = listaRangos;
	}

	public Integer getRangoTor() {
		return rangoTor;
	}

	public void setRangoTor(Integer rangoTor) {
		this.rangoTor = rangoTor;
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

	public Integer getRolnivelTorneo() {
		return rolnivelTorneo;
	}

	public void setRolnivelTorneo(Integer rolnivelTorneo) {
		this.rolnivelTorneo = rolnivelTorneo;
	}

	public Integer getTantosFavor() {
		return tantosFavor;
	}

	public void setTantosFavor(Integer tantosFavor) {
		this.tantosFavor = tantosFavor;
	}

	public Integer getTantosContra() {
		return tantosContra;
	}

	public void setTantosContra(Integer tantosContra) {
		this.tantosContra = tantosContra;
	}

	public Integer getIduser() {
		return iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}

	public List<Actividad> getActividadList() {
		return actividadList;
	}

	public void setActividadList(List<Actividad> actividadList) {
		this.actividadList = actividadList;
	}

	public int getIdcom() {
		return idcom;
	}

	public void setIdcom(int idcom) {
		this.idcom = idcom;
	}
	
	public int getActividad() {
		return actividad;
	}

	public void setActividad(int actividad) {
		this.actividad = actividad;
	}

	public Boolean getParticipante() {
		return participante;
	}

	public void setParticipante(Boolean participante) {
		this.participante = participante;
	}
	
	public boolean isJoinedComunidad() {
		return joinedComunidad;
	}

	public void setJoinedComunidad(boolean joinedComunidad) {
		this.joinedComunidad = joinedComunidad;
	}

	public int getRondasExtras() {
		return rondasExtras;
	}

	public void setRondasExtras(int rondasExtras) {
		this.rondasExtras = rondasExtras;
	}

	public Paginate getTorneoPaginatedList() {
		return torneoPaginatedList;
	}

	public void setTorneoPaginatedList(Paginate torneoPaginatedList) {
		this.torneoPaginatedList = torneoPaginatedList;
	}
	
	

//	public LinkedHashMap<Integer, String> getListaActividades() {
//		return listaActividades;
//	}
//
//	public void setListaActividades(LinkedHashMap<Integer, String> listaActividades) {
//		this.listaActividades = listaActividades;
//	}

	
	
	public Integer getEntero() {
		return entero;
	}

	public void setEntero(Integer entero) {
		this.entero = entero;
	}

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public List<UserTorneo> getUserTorneoList() {
		return userTorneoList;
	}

	public Comunidad getComunidad() {
		return comunidad;
	}

	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}

	public LinkedHashMap<String, Integer> getIntegerFromMap() {
		return IntegerFromMap;
	}

	public void setIntegerFromMap(LinkedHashMap<String, Integer> integerFromMap) {
		IntegerFromMap = integerFromMap;
	}


	public void setUserTorneoList(List<UserTorneo> userTorneoList) {
		this.userTorneoList = userTorneoList;
	}	

	public String getRoltorneo() {
		return roltorneo;
	}

	public void setRoltorneo(String roltorneo) {
		this.roltorneo = roltorneo;
	}

	public String getEstadistica() {
		return estadistica;
	}

	public void setEstadistica(String estadistica) {
		this.estadistica = estadistica;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	public Integer getParticipanteTorneo() {
		return participanteTorneo;
	}

	public void setParticipanteTorneo(Integer participanteTorneo) {
		this.participanteTorneo = participanteTorneo;
	}

	public Paginate getUserPaginatedList() {
		return userPaginatedList;
	}

	public void setUserPaginatedList(Paginate userPaginatedList) {
		this.userPaginatedList = userPaginatedList;
	}

	public Paginate getEquipoPaginatedList() {
		return equipoPaginatedList;
	}

	public void setEquipoPaginatedList(Paginate equipoPaginatedList) {
		this.equipoPaginatedList = equipoPaginatedList;
	}

	public Integer getVarOnline() {
		return VarOnline;
	}

	public void setVarOnline(Integer varOnline) {
		VarOnline = varOnline;
	}

	public Integer getVarPorEquipos() {
		return VarPorEquipos;
	}

	public void setVarPorEquipos(Integer varPorEquipos) {
		VarPorEquipos = varPorEquipos;
	}

	public Integer getVarLiga() {
		return VarLiga;
	}

	public void setVarLiga(Integer varLiga) {
		VarLiga = varLiga;
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

	public LinkedHashMap<Integer, String> getListaJornadas() {
		return listaJornadas;
	}

	public void setListaJornadas(LinkedHashMap<Integer, String> listaJornadas) {
		this.listaJornadas = listaJornadas;
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

	public List<PartidoIndividualVersus> getPartidoIndList() {
		return partidoIndList;
	}

	public void setPartidoIndList(List<PartidoIndividualVersus> partidoIndList) {
		this.partidoIndList = partidoIndList;
	}

	public List<PartidoEquiposVersus> getPartidoEqList() {
		return partidoEqList;
	}

	public void setPartidoEqList(List<PartidoEquiposVersus> partidoEqList) {
		this.partidoEqList = partidoEqList;
	}

	public Paginate getPartidoPaginatedList() {
		return partidoPaginatedList;
	}

	public void setPartidoPaginatedList(Paginate partidoPaginatedList) {
		this.partidoPaginatedList = partidoPaginatedList;
	}
	
	public Integer getNumPartidosConfirmados() {
		return numPartidosConfirmados;
	}

	public void setNumPartidosConfirmados(Integer numPartidosConfirmados) {
		this.numPartidosConfirmados = numPartidosConfirmados;
	}

	public Integer getNumPartidosNoConfirmados() {
		return numPartidosNoConfirmados;
	}

	public void setNumPartidosNoConfirmados(Integer numPartidosNoConfirmados) {
		this.numPartidosNoConfirmados = numPartidosNoConfirmados;
	}	
	
	

	//-------------------------------------------------------------------------
	


	public List<EquipoTorneo> getEquipoTorneoList() {
		return equipoTorneoList;
	}

	public void setEquipoTorneoList(List<EquipoTorneo> equipoTorneoList) {
		this.equipoTorneoList = equipoTorneoList;
	}

	public List<UserInvitado> getUserInvitadoList() {
		return userInvitadoList;
	}

	public void setUserInvitadoList(List<UserInvitado> userInvitadoList) {
		this.userInvitadoList = userInvitadoList;
	}

	/**
	 * Método execute del TorneoAction
	 */
	public String execute(){
		return SetearTorneo(RecuperarTorneoDetallado(null));
	}
	
	/**
	 * Recupera todos los datos del torneo
	 * @param tor Torneo del que recuperar todos los datos
	 * @return Torneo recuperada
	 */
	@SuppressWarnings("finally")
	public Torneo RecuperarTorneoDetallado(Torneo tor)
	{
		joinedComunidad=false;
		
		try{ 
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();

			if(tor == null){
				tor = (Torneo)session.load(Torneo.class,(Serializable)torneo.getId());
			}
			
			if((Integer) sesion.get("equipo") == 0){
				user = (User)session.load(User.class,(Integer)sesion.get("id"));
				
				UserTorneo usTo = (UserTorneo)session.get(UserTorneo.class,
						new UserTorneoPK(user,tor));
				
				if(usTo != null){
					rolnivelTorneo = usTo.getRango();
					roltorneo = FuncionesAuxiliares.StringRango(rolnivelTorneo);
					
					joinedComunidad = true;
				}
				else{
					UserComunidad usCo = (UserComunidad)session.get(UserComunidad.class,
							new UserComunidadPK(user,tor.getParentComunidad()));
					if(usCo != null){
						joinedComunidad = true;
					}
				}	
			}
			else{
				equipo = (Equipo)session.load(Equipo.class, (Integer)sesion.get("equipo"));
				
				EquipoTorneo eqTo = (EquipoTorneo)session.get(EquipoTorneo.class,
						new EquipoTorneoPK(equipo,tor));
				
				if(eqTo != null){
					rolnivelTorneo = eqTo.getRango();
					roltorneo = FuncionesAuxiliares.StringRango(rolnivelTorneo);
					
					joinedComunidad = true;
				}
				else{
					EquipoComunidad eqCo = (EquipoComunidad)session.get(EquipoComunidad.class,
							new EquipoComunidadPK(equipo,tor.getParentComunidad()));
					if(eqCo != null){
						joinedComunidad = true;
					}
				}
			}
						
		}
		catch(Exception e){
			e.printStackTrace();
			session.close();
		}
		finally{
			return tor;
		}
	}
	
	/**
	 * Setea la variable de Torneo del valuestack
	 * @param tor Torneo para el seteo
	 * @return Devuelve SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String SetearTorneo(Torneo tor){
		if(tor != null){
			torneo.setId(tor.getId());
			torneo.setNombre(tor.getNombre());
			torneo.setFechaCreacion(tor.getFechaCreacion());
			torneo.setFechaComienzo(tor.getFechaComienzo());
			torneo.setFechaFinal(tor.getFechaFinal());
			torneo.setOnline(tor.getOnline());
			torneo.setPorEquipos(tor.getPorEquipos());
			torneo.setLiga(tor.getLiga());
			torneo.setParentComunidad(tor.getParentComunidad());
			torneo.setParentActividad(tor.getParentActividad());
			torneo.setParentPlataforma(tor.getParentPlataforma());
			torneo.setRondas(tor.getRondas());
			torneo.setSorteado(tor.getSorteado());
			torneo.setEstado(tor.getEstado());
			torneo.setJornadaActual(tor.getJornadaActual());
			torneo.setJornadasJugables(tor.getJornadasJugables());
			
			torneo.setUserTorneos(tor.getUserTorneos());
			torneo.setEquipoTorneos(tor.getEquipoTorneos());
			torneo.setPartidosIndVersus(tor.getPartidosIndVersus());
			torneo.setPartidosEqVersus(tor.getPartidosEqVersus());
			
			torneo.setJoinPassword(tor.getJoinPassword());
			torneo.setPasswordProtected(tor.isPasswordProtected());
			torneo.setRegRequest(tor.isRegRequest());
			torneo.setUsuariosRequest(tor.getUsuariosRequest());
			torneo.setEquiposRequest(tor.getEquiposRequest());
			
			return SUCCESS;
		}
		else{
			return ERROR;
		}
		
	}
	
	/**
	 * Nos lleva a la zona de solicitudes de entrada al torneo
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoRequests(){
		if(SUCCESS == SetearTorneo(RecuperarTorneoDetallado(null))){
			if(SUCCESS == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.MOD)){ // Hay que ser al menos MOD
				
				userList = new ArrayList<User>(torneo.getUsuariosRequest());
				userInvitadoList = new ArrayList<UserInvitado>();
				
				userDAO = new UserDAOImpl();
				
				List<User> invitados = userDAO.listInvitadosByTorneo(torneo);
				
				Iterator<User> it = invitados.iterator();
				while(it.hasNext()){
					User invi = it.next();
					
					Set<User> setSolic = invi.getUsuariosRequest();
					Iterator<User> itS = setSolic.iterator();
					while(itS.hasNext()){
						User solicitante = itS.next();
						
						UserInvitado usIn = new UserInvitado();
						
						usIn.setSolicitado(invi);
						usIn.setSolicitante(solicitante);
						
//						System.out.println("*-*-*-*-*   Request... invi -> "+usIn.getSolicitado().getAlias()+", solicitante -> "+usIn.getSolicitante().getAlias());
						
						userInvitadoList.add(new UserInvitado(invi,solicitante));
					}
				}
				
				if(torneo.getPorEquipos()){ // Si es por equipos tb puede haber request de equipos
					equipoList = new ArrayList<Equipo>(torneo.getEquiposRequest());
					equipoInvitadoList = new ArrayList<EquipoInvitado>();
					
					equipoDAO = new EquipoDAOImpl();
					
					List<Equipo> invitadosEq = equipoDAO.listInvitadosByTorneo(torneo);
					
					Iterator<Equipo> itEq = invitadosEq.iterator();
					while(itEq.hasNext()){
						Equipo inviEq = itEq.next();
						
						Set<Equipo> setSolicEq = inviEq.getEquiposRequest();
						Iterator<Equipo> itSEq = setSolicEq.iterator();
						while(itSEq.hasNext()){
							Equipo solicitanteEq = itSEq.next();
							
							EquipoInvitado eqIn = new EquipoInvitado();
							
							eqIn.setSolicitado(inviEq);
							eqIn.setSolicitante(solicitanteEq);
														
							
							equipoInvitadoList.add(new EquipoInvitado(inviEq,solicitanteEq));
						}
					}	
				}
				
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
	 * Nos lleva al calendario del campeonato
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoCalendario(){
		if(SUCCESS == SetearTorneo(RecuperarTorneoDetallado(null))){
			if(torneo.getLiga()){
				return PartidoListByTorneo();
			}
			else{
				Integer participantes;
				
				partidoDAO = new PartidoDAOImpl();
				if(torneo.getPorEquipos()){
					partidoEqList = partidoDAO.PartidosByJornadaEquipos(torneo, torneo.getJornadaActual());
					
					equipoTorneoDAO = new EquipoTorneoDAOImpl();
					participantes = equipoTorneoDAO.EquipoTorneoParticipante(torneo).size();
					
					List<EquipoTorneo> equipoTorneoLista = equipoTorneoDAO.EquipoTorneoNoEliminados(torneo);
					
					if(equipoTorneoLista.size() == 1){						
						try{ 
							sessionFactory = HibernateUtil.getSessionFactory();
							session = sessionFactory.openSession();

							equipo = (Equipo) session.load(Equipo.class, 
											(Serializable)equipoTorneoLista.get(0).getId().getParentEquipo().getId());

						}
						catch(Exception e){
							e.printStackTrace();
							session.close();
						}
					}
				}
				else{
					partidoIndList = partidoDAO.PartidosByJornadaIndividual(torneo, torneo.getJornadaActual());	
					
					userTorneoDAO = new UserTorneoDAOImpl();
					participantes = userTorneoDAO.UserTorneoParticipante(torneo).size();
					
					List<UserTorneo> userTorneoLista = userTorneoDAO.UserTorneoNoEliminados(torneo);
					
					if(userTorneoLista.size() == 1){						
						try{ 
							sessionFactory = HibernateUtil.getSessionFactory();
							session = sessionFactory.openSession();

							campeon = (User) session.load(User.class, (Serializable)userTorneoLista.get(0).getId().getParentUser().getId());

						}
						catch(Exception e){
							e.printStackTrace();
							session.close();
						}
						
					}
				}				
				
				
				
				listHashMapUtil = new ListHashMapUtil();
				listHashMapUtil.SacarNumeros(sesion, listaJornadas, participantes, torneo.getJornadaActual());
				
				
				return SUCCESS;
			}
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la jornada deseada del calendario
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String IrAJornada(){
		if(SUCCESS == SetearTorneo(RecuperarTorneoDetallado(null))){
			partidoDAO = new PartidoDAOImpl();
			if(torneo.getPorEquipos()){
				partidoEqList = partidoDAO.PartidosByJornadaEquipos(torneo, entero);
				
				equipoTorneoDAO = new EquipoTorneoDAOImpl();
				
				List<EquipoTorneo> equipoTorneoLista = equipoTorneoDAO.EquipoTorneoNoEliminados(torneo);
				
				if(equipoTorneoLista.size() == 1){						
					try{ 
						sessionFactory = HibernateUtil.getSessionFactory();
						session = sessionFactory.openSession();

						equipo = (Equipo) session.load(Equipo.class, (Serializable)equipoTorneoLista.get(0).getId().getParentEquipo().getId());

					}
					catch(Exception e){
						e.printStackTrace();
						session.close();
					}
					
				}				
			}
			else{
				partidoIndList = partidoDAO.PartidosByJornadaIndividual(torneo, entero);	
				
				userTorneoDAO = new UserTorneoDAOImpl();
				
				List<UserTorneo> userTorneoLista = userTorneoDAO.UserTorneoNoEliminados(torneo);
				
				if(userTorneoLista.size() == 1){						
					try{ 
						sessionFactory = HibernateUtil.getSessionFactory();
						session = sessionFactory.openSession();

						campeon = (User) session.load(User.class, (Serializable)userTorneoLista.get(0).getId().getParentUser().getId());

					}
					catch(Exception e){
						e.printStackTrace();
						session.close();
					}
					
				}				
			}
			
			return SUCCESS;
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la zona de abandono del torneo
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoAbandonar(){
		if(SUCCESS == SetearTorneo(RecuperarTorneoDetallado(null))){ 
			return FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.NORMALUSER);// Hay que ser al menos NORMALUSER
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la zona de estadísticas del torneo
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoEstadisticas(){
		return SetearTorneo(RecuperarTorneoDetallado(null));
	}
	
	/**
	 * Nos lleva a la zona de estadísticas generales del torneo
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoEstadisticasTorneo(){
		return SetearTorneo(RecuperarTorneoDetallado(null));
	}
	
	/**
	 * Nos lleva a la zona de estadísticas del usuario en el torneo
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoEstadisticasUsuario(){
		if(SUCCESS == SetearTorneo(RecuperarTorneoDetallado(null))){
			Set<UserTorneo> setUsTor = torneo.getUserTorneos();
			Iterator<UserTorneo> it = setUsTor.iterator();
				
			while(it.hasNext()){
				User us = it.next().getId().getParentUser();
					
				listaParticipante.put(us.getId(), us.getAlias());
			}
			return SUCCESS;
		}		
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la zona de estadísticas del equipo en el torneo
	 * @return SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoEstadisticasEquipo(){
		if(SUCCESS == SetearTorneo(RecuperarTorneoDetallado(null))){
			Set<EquipoTorneo> setEqTo = torneo.getEquipoTorneos();
			Iterator<EquipoTorneo> it = setEqTo.iterator();
			
			while(it.hasNext()){
				Equipo eq = it.next().getId().getParentEquipo();
				
				listaParticipante.put(eq.getId(), eq.getNombre());
			}
			return SUCCESS;
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Mete en el valuestack los partidos confirmados y no confirmados del torneo
	 * para mostrarlos en las estadísticas
	 */
	public void PartidosConfirmadosTorneo(){
		
		if (!torneo.getPorEquipos()){ // Si es individual
			Set<PartidoIndividualVersus> set = torneo.getPartidosIndVersus();
			
			Iterator<PartidoIndividualVersus> it = set.iterator();
			while(it.hasNext()){
				if(it.next().getConfirmado()){
					numPartidosConfirmados++;
				}
				else{
					numPartidosNoConfirmados++;
				}
			}
		}
		else{ // Si es por equipos
			Set<PartidoEquiposVersus> set = torneo.getPartidosEqVersus();
			
			Iterator<PartidoEquiposVersus> it = set.iterator();
			while(it.hasNext()){
				if(it.next().getConfirmado()){
					numPartidosConfirmados++;
				}
				else{
					numPartidosNoConfirmados++;
				}
			}			
		}
	}
	
	/**
	 * Mete en el valuestack los partidos confirmados y no confirmados del usuario
	 * en el torneo
	 * para mostrarlos en las estadísticas
	 */
	public void PartidosConfirmadosUsuarioTorneo(User us){
		partidoDAO = new PartidoDAOImpl();
		
		numPartidosConfirmados = (Integer) partidoDAO.numPartidosJugadosUsuario(torneo, us);
		
		numPartidosNoConfirmados = (Integer) partidoDAO.numPartidosNoJugadosUsuario(torneo, us);
		
	}
	
	/**
	 * Almacena en un HashMap los tantos anotados por cada participante en el torneo
	 */
	public void TantosTorneo(){
		if(SUCCESS == SetearTorneo(RecuperarTorneoDetallado(null))){				
				if(!torneo.getPorEquipos()){ // Si es individual
					userTorneoDAO = new UserTorneoDAOImpl();				
					Set<UserTorneo> utSet = torneo.getUserTorneos();
					
					Iterator<UserTorneo> it = utSet.iterator();
					while(it.hasNext()){
						UserTorneo ut = it.next();
						
						if(ut.isParticipante() &&
								FuncionesAuxiliares.ComprobarPermisos(ut.getRango(),FuncionesAuxiliares.NORMALUSER) == SUCCESS){
							
							IntegerFromMap.put(ut.getId().getParentUser().getAlias(), ut.getTantosFavor());							
						}
					}	
				}
				else{ // Si es por equipos
					equipoTorneoDAO = new EquipoTorneoDAOImpl();				
					Set<EquipoTorneo> etSet = torneo.getEquipoTorneos();
					
					Iterator<EquipoTorneo> it = etSet.iterator();
					while(it.hasNext()){
						EquipoTorneo et = it.next();
						
						if(FuncionesAuxiliares.ComprobarPermisos(et.getRango(),FuncionesAuxiliares.NORMALUSER) == SUCCESS){
							IntegerFromMap.put(et.getId().getParentEquipo().getNombre(), et.getTantosFavor());	
						}
					}
				}
		}
	}
	
	/**
	 * Muestra las estadísticas seleccionadas de un torneo
	 * @return SUCCESS en caso de éxito, y ERROR en caso de que ocurra algún fallo
	 */
	public String VerEstadisticasTorneo(){
		if(SUCCESS == SetearTorneo(RecuperarTorneoDetallado(null))){
			if(estadistica.equals("partidosConfirmados")){
				PartidosConfirmadosTorneo();
				return estadistica;
			}
			else if(estadistica.equals("tantos")){
				TantosTorneo();
				return estadistica;
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
	 * Muestra las estadísticas seleccionadas de un usuario en un torneo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR en caso de que ocurra algún fallo
	 */
	@SuppressWarnings("finally")
	public String VerEstadisticasUsuarioTorneo(){
		boolean error = false;
		boolean exito = false;
		
		try{
			if(ERROR == SetearTorneo(RecuperarTorneoDetallado(null))){
				return ERROR;
			}
			
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			
			try{
				User us = (User)session.get(User.class,participanteTorneo);
				
				System.out.println("Estadistica ---> "+estadistica+"   Alias ---> "+us.getAlias());
				
				
				if(estadistica.equals("partidos")){
					PartidosConfirmadosUsuarioTorneo(us);
				}
				else if(estadistica.equals("tantos")){
//					UserTorneo usTo = (UserTorneo)session.get(UserTorneo.class,new UserTorneoPK(us,torneo));
					
					partidoDAO = new PartidoDAOImpl();
					
					List<PartidoIndividualVersus> listInd = partidoDAO.PartidosUsuarioTorneo(torneo, us);
					
					Iterator<PartidoIndividualVersus> it = listInd.iterator();
					while(it.hasNext()){
						PartidoIndividualVersus pa = it.next();
						
						System.out.println("Local -> "+pa.getParentLocal().getAlias());
						if(pa.getParentLocal().getId() == us.getId()){
							System.out.println("Es el LOCAL! jornada -->"+pa.getJornada());
							listaTantosFavor.add(new IntegerInteger(pa.getJornada(), pa.getPuntosLocal()));
							listaTantosContra.add(new IntegerInteger(pa.getJornada(), pa.getPuntosVisitante()));
						}
						else{
							System.out.println("Es el VISITANTE! jornada -->"+pa.getJornada());
							listaTantosFavor.add(new IntegerInteger(pa.getJornada(), pa.getPuntosVisitante()));
							listaTantosContra.add(new IntegerInteger(pa.getJornada(), pa.getPuntosLocal()));							
						}
						
						Collections.sort(listaTantosFavor, new JornadaListaTantosComparator());
						Collections.sort(listaTantosContra, new JornadaListaTantosComparator());
					}
				}
				else{
					return ERROR;
				}
				
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
				System.out.println("Retorna ---> "+estadistica);
				return estadistica;
			}
			else{
				return INPUT;
			}
		}
	}
	}
		
	/**
	 * Nos lleva a la sección de partidos del torneo
	 * @return Devuelve SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoPartidos(){
		if(SUCCESS == SetearTorneo(RecuperarTorneoDetallado(null))){
			if(torneo.getJornadaActual() == 0){
				return SUCCESS;
			}
			
			partidoDAO = new PartidoDAOImpl();
			// Si el partido es entre dos invitados también se mostrará a los mods para que pueda introducir el resultado
			Integer jorJug = torneo.getJornadasJugables();
			
			if(jorJug != null){
				if(!torneo.getPorEquipos()){
					System.out.println("!torneo.getPorEquipo()");
					partidoIndList = new ArrayList<PartidoIndividualVersus>();
					partidoIndList = partidoDAO.PartidosUsuarioTorneoJugables(torneo, user);
					
					if((Integer)sesion.get("equipo") == 0 &&
							FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo,FuncionesAuxiliares.MOD) == SUCCESS){
						System.out.println("-- Individual MOD --");
						
						partidoIndList.addAll(partidoDAO.PartidosUsuarioTorneoInvitadosJugables(torneo));
					}	
				}
				else {
					partidoEqList = new ArrayList<PartidoEquiposVersus>();
					
					if((Integer)sesion.get("equipo") != 0){
						System.out.println("-- Por equipos (Integer)sesion.get('equipo') != 0");						
						
						partidoEqList = partidoDAO.PartidosEquipoTorneoJugables(torneo, equipo);	
					}
					else{
						if(FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo,FuncionesAuxiliares.MOD) == SUCCESS){
							System.out.println("-- Por Equipos MOD --");
									
							partidoEqList.addAll(partidoDAO.PartidosEquipoTorneoInvitadosJugables(torneo));
						}	
					}	
				}
			}
			
			System.out.println("--- Antes de meterse en los rechazados!");
			
			if((Integer)sesion.get("equipo") == 0 && 
					SUCCESS == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.ADMIN)){ 
				// Hay que ser al menos ADMIN
				
				if(torneo.getPorEquipos()){
					partidosRechazadosEquiposList = partidoDAO.PartidosRechazadosEquipos(torneo);
				}
				else{
					partidosRechazadosIndividualList = partidoDAO.PartidosRechazadosIndividual(torneo);	
				}
			}
			
			System.out.println("--- Debería retornar EXITO");
				
			return SUCCESS;
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la clasificación del campeonato
	 * @return Devuelve SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoClasificacion(){
		if(SUCCESS == SetearTorneo(RecuperarTorneoDetallado(null))){
			if(!torneo.getPorEquipos()){ // Si es individual
				
				Set<UserTorneo> utSet = torneo.getUserTorneos();
				userTorneoList = new ArrayList<UserTorneo>(utSet);
				
				Iterator<UserTorneo> it = utSet.iterator();
				while(it.hasNext()){
					UserTorneo ut = it.next();
					if(!ut.isParticipante() || ut.getRango() > FuncionesAuxiliares.NORMALUSER){
						userTorneoList.remove(ut);
					}
				}
//				userTorneoDAO = new UserTorneoDAOImpl();
//				userTorneoList = userTorneoDAO.UserTorneoParticipante(torneo);
				
				Collections.sort(userTorneoList,new PosicionUserTorneoComparator());	
			}
			else{ // Si es por equipos
				
				Set<EquipoTorneo> etSet = torneo.getEquipoTorneos();
				equipoTorneoList = new ArrayList<EquipoTorneo>(etSet);
				
				Iterator<EquipoTorneo> it = etSet.iterator();
				while(it.hasNext()){
					EquipoTorneo et = it.next();
					if(et.getRango() > FuncionesAuxiliares.NORMALUSER){
						equipoTorneoList.remove(et);
					}
				}
//				equipoTorneoDAO = new EquipoTorneoDAOImpl();
//				equipoTorneoList = equipoTorneoDAO.EquipoTorneoParticipante(torneo);
				
				Collections.sort(equipoTorneoList,new PosicionEquipoTorneoComparator());
			}
			

			return SUCCESS;
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la zona de información del torneo
	 * @return Devuelve SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoInfoTorneo(){
		if(SUCCESS == SetearTorneo(RecuperarTorneoDetallado(null))){
			if(torneo.getPorEquipos()){
				if(SUCCESS == EquipoListByTorneo()){
					return UserListByTorneo();
				}
				else{
					return ERROR;
				}
			}
			else{
				return UserListByTorneo();
			}
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a las noticias del torneo
	 * @return Devuelve SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoNoticiasTorneo(){
		if( SUCCESS == SetearTorneo(RecuperarTorneoDetallado(null))){
			String aliasTable = "noti";
			
			String where = " where "+aliasTable+".parentTorneo="+torneo.getId();
			String tablasAux = ", CategoriaNoticia as cat ";
			where += " and cat.nombre IN ";
			where += "('AltaUsuarioTorneo','BajaUsuarioTorneo','AltaEquipoTorneo','BajaEquipoTorneo'," +
					"'BaneadoUsuarioTorneo','ExpulsadoUsuarioTorneo','BaneadoEquipoTorneo','ExpulsadoEquipoTorneo'," +
					"'RegistrosAbiertosTorneo','RegistrosCerradosTorneo','PasswordTorneoCambiado'," +
					"'RangoUsuarioTorneoCambiado','TorneoCreado','TorneoEliminado','TorneoEnJuego'," +
					"'TorneoFinalizado','TorneoSorteado'," +
					"'ConfirmacionRegistroTorneoOn','ConfirmacionRegistroTorneoOff'";
			
			if(SUCCESS == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.MOD)){
				where += ",'AceptadoRequestUsuarioTorneo','DenegadoRequestUsuarioTorneo','RequestUsuarioTorneo'," +
						"'AceptadoRequestEquipoTorneo','DenegadoRequestEquipoTorneo','RequestEquipoTorneo'";
			}
			
			where += ")";
			where += " and "+aliasTable+" in elements(cat.noticias) order by noti.fechaPublicacion desc";
			
			
			
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			
			noticiaDAO= new NoticiaDAOImpl();
			noticiaPaginatedList = noticiaDAO.listNoticia(request, aliasTable, where, tablasAux);
			
			tipo = "torneo";
			
			return SUCCESS;
		}		
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la zona de administración del torneo
	 * @return Devuelve SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoAdminTorneo(){
		if(SUCCESS == SetearTorneo(RecuperarTorneoDetallado(null))){ 
			return FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.MOD);// Hay que ser al menos MOD
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la zona de sorteo de calendario del torneo
	 * @return Devuelve SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoSorteoTorneo(){
		if(SUCCESS == SetearTorneo(RecuperarTorneoDetallado(null))){ 
			if(SUCCESS == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.MOD)){
				// Hay que ser al menos MOD
				sesion = getSession();
				listHashMapUtil = new ListHashMapUtil();
				listHashMapUtil.SacarReglasTorneo(sesion, listaReglas, torneo.getParentActividad());
				
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
	 * Nos lleva a la zona de configuración del torneo
	 * @return Devuelve SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoSettings(){
		if(SUCCESS == SetearTorneo(RecuperarTorneoDetallado(null))){ 
			return FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.ADMIN);// Hay que ser al menos ADMIN
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la zona de uniser al torneo
	 * @return Devuelve SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoUnirseTorneo(){
		if(SUCCESS == SetearTorneo(RecuperarTorneoDetallado(null))){
			if(rolnivelTorneo == FuncionesAuxiliares.NUMERO_NO_VALIDO || rolnivelTorneo == FuncionesAuxiliares.EXMIEMBRO){

				listHashMapUtil = new ListHashMapUtil();
				listHashMapUtil.SacarInvitadosTorneo(sesion,listaInvitados,torneo);
				
				if(listaInvitados.isEmpty()){
					listaInvitados = null;
				}
				
				if(torneo.isRegRequest()){
					Set<Torneo> setT = null;
					if(!torneo.getPorEquipos()){
						User us = (User)session.load(User.class,(Serializable) sesion.get("id"));
						setT = us.getTorneosRequest();	
					}
					else{
						Equipo eq = (Equipo)session.load(Equipo.class,(Serializable) sesion.get("equipo"));
						setT = eq.getTorneosRequest();
					}
					
					if((setT != null && !setT.isEmpty()) && setT.contains(torneo)){ // Si ya tiene una request pendiente
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
	 * Nos lleva a la zona de administración de usuarios en el torneo
	 * @return Devuelve SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoAdminUsuariosTorneo(){
		if(SUCCESS == SetearTorneo(RecuperarTorneoDetallado(null))){
			if( SUCCESS == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo,FuncionesAuxiliares.MOD)){ // Necesitas ser al menos MOD		
				sesion = getSession();
				
				listHashMapUtil = new ListHashMapUtil();
				listHashMapUtil.SacarRangosComunidad(sesion,roltorneo,listaRangos);
				
				return UserListByTorneo();
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
	 * Nos lleva a la zona de seguridad del torneo
	 * @return Devuelve SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoPasswordTorneo(){
		if(SUCCESS == SetearTorneo(RecuperarTorneoDetallado(null))){ 
			return FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.ADMIN);// Hay que ser al menos ADMIN
		}
		else{
			return ERROR;
		}
	}
	
	/**
	 * Nos lleva a la zona de administración de equipos en el torneo
	 * @return Devuelve SUCCESS en caso de éxito y ERROR en caso de fallo
	 */
	public String GoAdminEquiposTorneo(){
		if(SUCCESS == SetearTorneo(RecuperarTorneoDetallado(null))){
			if(SUCCESS == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.MOD)){ // Hay que ser al menos MOD
				return EquipoListByTorneo();
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
	 * Une a un equipo o usuario al torneo
	 * @param tor Torneo al que unirse
	 * @param participa Indica si formará parte de la competición o no
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo 
	 */
	@SuppressWarnings("finally")
	public String UnirTorneo(Torneo tor, Boolean participa){
		boolean error = false;
		boolean exito = false;
		int rango = FuncionesAuxiliares.NORMALUSER;
		
		try{ 
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{					
					if(tor == null){ // Si no existe el torneo y te quieres unir  
						Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
								new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
								"global/error/action/erroraction");	

						addActionError(propsError.getProperty("torneo.join.inexistente"));
					}
				
					else{ // Debería serlo si no ha habido errores	
						Noticia noticia = new Noticia();						
						
						if((Integer)sesion.get("equipo") == 0){ // Si es perfil de usuario
							User us =(User)session.load(User.class,(Serializable) sesion.get("id"));
							
							UserTorneo userTorneo = new UserTorneo(rango,participa);
							
							if(participa){
								userTorneoDAO = new UserTorneoDAOImpl();
								List<UserTorneo> listUT = userTorneoDAO.UserTorneoParticipante(tor);
//								List<UserTorneo> listUT = new ArrayList<UserTorneo>(tor.getUserTorneos());
								
								userTorneo.setPosicion(FuncionesAuxiliares.SacarPosicionMasBajaUserTorneo(listUT));	
							}
														
							UserTorneoPK userTorPK = new UserTorneoPK(us,tor);
								
							userTorneo.setId(userTorPK); // Seteamos la PK del userTorneo
							
							userTorneoDAO = new UserTorneoDAOImpl();
							userTorneoDAO.mergeUserTorneo(userTorneo);
							
							noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
															"where nombre='AltaUsuarioTorneo'").uniqueResult());
							noticia.setParentUsuario(us);
						}
						else{
							Equipo eq =(Equipo)session.load(Equipo.class,(Serializable) sesion.get("equipo"));
							
							EquipoTorneo equipoTorneo = new EquipoTorneo();
							
							equipoTorneoDAO = new EquipoTorneoDAOImpl();
							List<EquipoTorneo> listET = equipoTorneoDAO.EquipoTorneoParticipante(tor);
//							List<EquipoTorneo> listUT = (List<EquipoTorneo>) tor.getEquipoTorneos();
							
							equipoTorneo.setPosicion(FuncionesAuxiliares.SacarPosicionMasBajaEquipoTorneo(listET));
							
							EquipoTorneoPK equipoTorPK = new EquipoTorneoPK(eq,tor);
								
							equipoTorneo.setId(equipoTorPK); // Seteamos la PK del equipoTorneo
							
							equipoTorneoDAO = new EquipoTorneoDAOImpl();
							equipoTorneoDAO.mergeEquipoTorneo(equipoTorneo);
							
							noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
															"where nombre='AltaEquipoTorneo'").uniqueResult());
							noticia.setParentEquipo(eq);
						}
														
						noticia.setParentTorneo(tor);
						
						
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
	 * Crear Torneo
	 * @param idcomuni Id de la comunidad
	 * @param participa Indica si formará parte de la competición o no
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo 
	 */
	@SuppressWarnings("finally")
	public String CrearTorneo( int idcomuni, Boolean participa){
		boolean error = false;
		boolean exito = false;
		int rango = FuncionesAuxiliares.CREADOR;
		
		try{ 
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
					rango = FuncionesAuxiliares.CREADOR;
					torneo.setNombre(torneo.getNombre().trim());
					
					Torneo tor = (Torneo)session.createQuery("from Torneo tor where tor.nombre=?")
															.setString(0,torneo.getNombre())
															.uniqueResult();
				
					if (tor != null){ // Si quieres crear un torneo ya existente
						Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
								new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
								"global/error/action/erroraction");	

						addActionError(propsError.getProperty("torneo.nombre.existente"));
					}
					else{
						Comunidad com =(Comunidad)session.load(Comunidad.class,(Serializable) idcomuni);
						
						System.out.println("------------     Despues Comunidad.session.load !!");
						
						torneo.setParentComunidad(com);
						
						Actividad act = null;
						
						if(deporte != null && deporte != 0){ // Si hay una actividad deportiva elegida en el form
							act = (Actividad)session.load(Actividad.class,deporte);
							torneo.setParentActividad(act);
						}
						
						if(juegodemesa  != null && juegodemesa != 0){ // Si hay una actividad de mesa elegida en el form
							act = (Actividad)session.load(Actividad.class,juegodemesa);
							torneo.setParentActividad(act);
						}
						
						if(carta != null && carta != 0){ // Si hay una actividad de cartas elegida en el form
							act = (Actividad)session.load(Actividad.class,carta);
							torneo.setParentActividad(act);
						}
						
						if(juego != null &&juego != 0){ // Si hay una actividad de videojuego elegida en el form
							act = (Actividad)session.load(Actividad.class,juego);
							torneo.setParentActividad(act);
							
							if(plataforma != null && plataforma != 0){ // Si hay una plataforma elegida
								Plataforma plat = (Plataforma)session.load(Plataforma.class,plataforma);
								torneo.setParentPlataforma(plat);
							}
							else{
								Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
										new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
										"global/error/action/erroraction");	

								addActionError(propsError.getProperty("juego.elegir.plataforma"));
							}
						}
						else{
							if(plataforma != null && plataforma != 0){ // Si hay una plataforma elegida
								Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
										new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
										"global/error/action/erroraction");	

								addActionError(propsError.getProperty("plataforma.elegir.juego"));
							}
						}
						torneo.setParentRegla(act.getParentReglaPorDefecto()); // Ponemos regla por defecto, después se podrá cambiar
						torneo.setSorteado(false); // Ponemos que aún no ha sido sorteado
						torneo.setModoEnfrentamiento(false); // Esto habrá que mirarlo en adelante
						if(torneo.getLiga()){
							if(torneo.getRondas() == -1){
								torneo.setRondas(getRondasExtras());
							}
						}
						else{ // Para las copas solo habrá una ronda!
							torneo.setRondas(1);
						}
						
						torneo.setJornadaActual(1); // Ponemos que la jornada actual es la 1.
						
						//Primero guardamos el torneo en la BBDD, para que no haya referencias a transient values
						torneoDAO = new TorneoDAOImpl();
						tor = torneoDAO.mergeTorneo(torneo);
						torneo.setId(tor.getId());
				
						sesion = getSession();
			
						User us =(User)session.load(User.class,(Serializable) sesion.get("id"));
							
						UserTorneo userTorneo = new UserTorneo(rango,participa);
						if(participa){
							userTorneo.setPosicion(1);
						}
						// else userTorneo.setPosicion(0); //??
						UserTorneoPK userTorPK = new UserTorneoPK(us,tor);
							
						userTorneo.setId(userTorPK); // Seteamos la PK del userTorneo
						
						userTorneoDAO = new UserTorneoDAOImpl();
						userTorneoDAO.mergeUserTorneo(userTorneo);

						Noticia noticia = new Noticia();
						noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																				"where nombre='TorneoCreado'").uniqueResult());	
						noticia.setParentTorneo(tor);
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
	 * Une a un equipo o usuario al torneo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo 
	 */
	public String UnirseTorneo()
	{  
		Torneo tor = RecuperarTorneoDetallado(null);
		
		if(SUCCESS == SetearTorneo(tor)){	
			if(rolnivelTorneo == FuncionesAuxiliares.NUMERO_NO_VALIDO){
				return UnirTorneo(tor,comprobarParticipante());	// Unirse a Torneo
			}
			else if(rolnivelTorneo == FuncionesAuxiliares.EXMIEMBRO){
				return ReJoinTorneo(tor,comprobarParticipante());
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
	 * Crea un nuevo torneo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo 
	 */
	public String merge()
	{
		return CrearTorneo(getIdcom(),comprobarParticipante());	// Crear Torneo
	}
	
	/**
	 * Une a un equipo o usuario al torneo con contraseña
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo 
	 */
	@SuppressWarnings("finally")
	public String UnirseTorneoPassword()
	{
		boolean error = false;
		boolean exito = false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);

			if( rolnivelTorneo != FuncionesAuxiliares.NUMERO_NO_VALIDO && 
					rolnivelTorneo != FuncionesAuxiliares.EXMIEMBRO){
				return ERROR;					
			}
			
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			
			try{				
				String pw = tor.getJoinPassword();
				
				if(pw == null){ // Esto no debería ocurrir pero ocurre si fallo en algún sitio
					exito = true;
				}
				else if(tor != null){
						// Si existe el torneo, cargamos el encriptador
						Properties props = PropertiesUtil.loadProperties("encriptado/encriptado.properties"); 
						StringEncrypter stringEncrypter = new StringEncrypter(props.getProperty("passPhrase"));
						
						System.out.println("contraseña almacenada -----> "+stringEncrypter.decrypt(tor.getJoinPassword()));
						System.out.println("contraseña introducida -----> "+torneo.getJoinPassword().trim());
						
						if(torneo.getJoinPassword().trim().equals(stringEncrypter.decrypt(tor.getJoinPassword()))){
							// Si es correcta la contraseña
							if( SUCCESS == UnirTorneo(tor,comprobarParticipante())){
								exito = true;	
							}
							else{
								error = true;
							}
							
						}
						else{
							Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
									new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
									"global/error/action/erroraction");	

							addActionError(propsError.getProperty("password.incorrecto"));
						}
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
	 * Modifica la configuración del torneo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo 
	 */
	@SuppressWarnings("finally")
	public String ModificarSettings()
	{
		boolean error=false;
		boolean exito=false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			
			if(tor != null ){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo,FuncionesAuxiliares.ADMIN)){ // Necesitas ser al menos ADMIN
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{				
				if(torneo.getRondas() == null){
					torneo.setRondas(rondasExtras);
				}
				
				if(torneo.getLiga() != tor.getLiga() || torneo.getPorEquipos() != tor.getPorEquipos() ||
						torneo.getOnline() != tor.getOnline() || torneo.getRondas() != tor.getRondas() ||
						torneo.getFechaComienzo() != tor.getFechaComienzo() || torneo.getFechaFinal() != tor.getFechaFinal())
				{
					torneoDAO = new TorneoDAOImpl();
					torneoDAO.mergeTorneo(torneo);
					
					// Habria que vigilar que no se pueden cambiar ciertas cosas una vez sorteado
					// Habria que crear una noticia cuando se cambien cosas !!
					
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
	 * Abre los registros para entrar al torneo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo
	 */
	public String AbrirRegistros(){ // La comprobacion de permisos la hago en ModificarEstadoRegistros
		return ModificarEstadoRegistros(REG_ABIERTOS);
	}
	
	/**
	 * Cierra los registros para entrar al torneo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo
	 */
	public String CerrarRegistros(){
		return ModificarEstadoRegistros(REG_CERRADOS);
	}
	
	/**
	 * Modifica los registros del torneo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String ModificarEstadoRegistros(int estado){
		boolean error = false;
		boolean exito = false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			if(tor != null){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.MOD)){ // Hay que ser al menos MOD
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{				
					if(estado != tor.getEstado()){ // Si es el mismo que no haga nada
						tor.setEstado(estado);
						
						// Guardamos el torneo en la BBDD
						torneoDAO = new TorneoDAOImpl();
						tor = torneoDAO.mergeTorneo(tor);
						
						SetearTorneo(tor);
						
						Noticia noticia = null;
						
						switch(estado){
						case REG_ABIERTOS:
							noticia = new Noticia();
							noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																			"where nombre='RegistrosAbiertosTorneo'").uniqueResult());
							break;
						case REG_CERRADOS:
							noticia = new Noticia();
							noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																			"where nombre='RegistrosCerradosTorneo'").uniqueResult());
							break;
						case EN_JUEGO:
							noticia = new Noticia();
							noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																			"where nombre='TorneoEnJuego'").uniqueResult());
							break;
						case FINALIZADO:
							noticia = new Noticia();
							noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																			"where nombre='TorneoFinalizado'").uniqueResult());
							break;
						default:
							error = true;
							break;
						}
						
						noticia.setParentTorneo(tor);
						
						noticiaDAO = new NoticiaDAOImpl();
						noticiaDAO.mergeNoticia(noticia);
					}
					
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
	 * Abandona el torneo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String QuitarseTorneo()
	{
		boolean error=false;
		boolean exito=false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			
			if(SUCCESS == SetearTorneo(tor)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.NORMALUSER)){ // Hay que ser al menos NORMALUSER
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				sesion = getSession();
				Noticia noticia = new Noticia();
				
				if((Integer) sesion.get("equipo") != 0){ // Si está usando el perfil de equipo
					Equipo eq =(Equipo)session.load(Equipo.class,(Serializable) sesion.get("equipo"));
					
					EquipoTorneoPK eqToPK = new EquipoTorneoPK(eq,tor);
					EquipoTorneo eqTo = (EquipoTorneo)session.get(EquipoTorneo.class,(Serializable)eqToPK);
					
					eqTo.setRango(FuncionesAuxiliares.EXMIEMBRO);
					
					equipoTorneoDAO = new EquipoTorneoDAOImpl();
					equipoTorneoDAO.mergeEquipoTorneo(eqTo);					
					
					noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																			"where nombre='BajaEquipoTorneo'").uniqueResult());
					noticia.setParentEquipo(eq);
				}
				else{
					User us =(User)session.load(User.class,(Serializable) sesion.get("id"));
					
					UserTorneoPK usToPK = new UserTorneoPK(us,tor);
					UserTorneo usTo = (UserTorneo)session.get(UserTorneo.class,(Serializable)usToPK);
					
					usTo.setRango(FuncionesAuxiliares.EXMIEMBRO);
					
					userTorneoDAO = new UserTorneoDAOImpl();
					userTorneoDAO.mergeUserTorneo(usTo);					
					
					noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																			"where nombre='BajaUsuarioTorneo'").uniqueResult());
					noticia.setParentUsuario(us);
				}
				
				noticia.setParentTorneo(tor);
								
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
	 * Une a un equipo o usuario al torneo, al que había pertenecido
	 * @param tor Torneo al que unirse
	 * @param participa Indica si formará parte de la competición o no
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo 
	 */
	@SuppressWarnings("finally")
	public String ReJoinTorneo(Torneo tor, Boolean participa){
		boolean error = false;
		boolean exito = false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				Noticia noticia = new Noticia();
				
				if((Integer) sesion.get("equipo") == 0){ // Si está usando el perfil de usuario
					User us = (User)session.get(User.class,(Serializable)sesion.get("id"));
					
					UserTorneo usTo = (UserTorneo)session.get(UserTorneo.class,
							new UserTorneoPK(us,tor));
					
					usTo.setRango(FuncionesAuxiliares.NORMALUSER);
					usTo.setParticipante(participa);
							
					userTorneoDAO = new UserTorneoDAOImpl();				
					userTorneoDAO.mergeUserTorneo(usTo);
					
					
					noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																			"where nombre='AltaUsuarioTorneo'").uniqueResult());
					noticia.setParentUsuario(us);
				}
				else{
					Equipo eq = (Equipo)session.get(Equipo.class,(Serializable)sesion.get("equipo"));
					
					EquipoTorneo eqTo = (EquipoTorneo)session.get(EquipoTorneo.class,
							new EquipoTorneoPK(eq,tor));
					
					eqTo.setRango(FuncionesAuxiliares.NORMALUSER);
							
					equipoTorneoDAO = new EquipoTorneoDAOImpl();				
					equipoTorneoDAO.mergeEquipoTorneo(eqTo);
					
					
					noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																			"where nombre='AltaEquipoTorneo'").uniqueResult());
					noticia.setParentEquipo(eq);
				}
				

				noticia.setParentTorneo(tor);				
				
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
	 * Realiza petición de entra al torneo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo 
	 */
	@SuppressWarnings("finally")
	public String RequestTorneo()
	{
		boolean error = false;
		boolean exito = false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			
			if(SUCCESS == SetearTorneo(tor)){
				if(rolnivelTorneo != FuncionesAuxiliares.NUMERO_NO_VALIDO && rolnivelTorneo != FuncionesAuxiliares.EXMIEMBRO){
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{	
				if(tor != null){
					Noticia noticia = new Noticia();
					
					if((Integer) sesion.get("equipo") == 0){ // Si está usando el perfil de usuario
						User us = (User)session.load(User.class,(Serializable) sesion.get("id"));
						
						Set<Torneo> setT = us.getTorneosRequest();
						setT.add(tor);
								
						us.setTorneosRequest(setT);
								
						userDAO = new UserDAOImpl();
						userDAO.mergeUser(us);
						
						
						noticia.setParentCategoriaNoticia((CategoriaNoticia) session.createQuery("from CategoriaNoticia " +
																				"where nombre='RequestUsuarioTorneo'").uniqueResult());						
						noticia.setParentUsuario(us);
					}
					else{ // Perfil de Equipo
						Equipo eq = (Equipo)session.load(Equipo.class,(Serializable) sesion.get("equipo"));
						
						Set<Torneo> setT = eq.getTorneosRequest();
						setT.add(tor);
								
						eq.setTorneosRequest(setT);
								
						equipoDAO = new EquipoDAOImpl();
						equipoDAO.mergeEquipo(eq);
						
						
						noticia.setParentCategoriaNoticia((CategoriaNoticia) session.createQuery("from CategoriaNoticia " +
																				"where nombre='RequestEquipoTorneo'").uniqueResult());						
						noticia.setParentEquipo(eq);
					}
					
					noticia.setParentTorneo(tor);
					
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
	 * Realiza petición de entra al torneo con contraseña
	 * @return SUCCESS en caso de éxito, INPUT si no se completa y ERROR si ocurre algún fallo 
	 */
	@SuppressWarnings("finally")
	public String RequestTorneoPassword()
	{
		boolean error = false;
		boolean exito = false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			if(SUCCESS == SetearTorneo(tor)){
				if(rolnivelTorneo != FuncionesAuxiliares.NUMERO_NO_VALIDO && rolnivelTorneo != FuncionesAuxiliares.EXMIEMBRO){
					return ERROR;
				}
			}
			else{
				return ERROR;
			}			
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
						// Si existe la comunidad, cargamos el encriptador
						Properties props = PropertiesUtil.loadProperties("encriptado/encriptado.properties"); 
						StringEncrypter stringEncrypter = new StringEncrypter(props.getProperty("passPhrase"));
						
						if(torneo.getJoinPassword().trim().equals(stringEncrypter.decrypt(tor.getJoinPassword()))){
							Noticia noticia = new Noticia();
							
							if((Integer) sesion.get("equipo") == 0){ // Si está usando el perfil de usuario
								User us = (User)session.load(User.class,(Serializable) sesion.get("id"));
								
								Set<Torneo> setT = us.getTorneosRequest();
								setT.add(tor);
										
								us.setTorneosRequest(setT);
										
								userDAO = new UserDAOImpl();
								userDAO.mergeUser(us);
								
								
								noticia.setParentCategoriaNoticia((CategoriaNoticia) session.createQuery("from CategoriaNoticia " +
																						"where nombre='RequestUsuarioTorneo'").uniqueResult());						
								noticia.setParentUsuario(us);
							}
							else{ // Perfil de Equipo
								Equipo eq = (Equipo)session.load(Equipo.class,(Serializable) sesion.get("equipo"));
								
								Set<Torneo> setT = eq.getTorneosRequest();
								setT.add(tor);
										
								eq.setTorneosRequest(setT);
										
								equipoDAO = new EquipoDAOImpl();
								equipoDAO.mergeEquipo(eq);
								
								
								noticia.setParentCategoriaNoticia((CategoriaNoticia) session.createQuery("from CategoriaNoticia " +
																						"where nombre='RequestEquipoTorneo'").uniqueResult());						
								noticia.setParentEquipo(eq);
							}
							
							noticia.setParentTorneo(tor);
							
							noticiaDAO = new NoticiaDAOImpl();
							noticiaDAO.mergeNoticia(noticia);
									
							exito = true;
						}
						else{
							Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
									new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
									"global/error/action/erroraction");	

							addActionError(propsError.getProperty("password.incorrecto"));
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
	 * Crea usuario invitado para el torneo
	 * @return SUCCESS en caso de éxito, y ERROR en caso de que ocurra algún fallo
	 */
	// No hacemos noticia de esto
	public String CrearInvitadoUser(){
		Torneo tor = RecuperarTorneoDetallado(null);
		if(SUCCESS == SetearTorneo(tor)){
			System.out.println("--------------- Dentro de CrearInvitadoUser() --> "+torneo.getId()+", "+torneo.getNombre());
			return FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.ADMIN); // Hay que ser al menos ADMIN
		}
		else{
			return ERROR;
		}						
	}
	
	/**
	 * Crea equipo invitado para el torneo
	 * @return SUCCESS en caso de éxito, y ERROR en caso de que ocurra algún fallo
	 */
	// No hacemos noticia de esto
	public String CrearInvitadoEquipo(){
		Torneo tor = RecuperarTorneoDetallado(null);
		if(SUCCESS == SetearTorneo(tor)){
			System.out.println("--------------- Dentro de CrearInvitadoEquipo() --> "+torneo.getId()+", "+torneo.getNombre());
			return FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.ADMIN); // Hay que ser al menos ADMIN
		}
		else{
			return ERROR;
		}						
	}
	
	/**
	 * Une usuario invitado al torneo
	 * @return SUCCESS en caso de éxito, INPUT en caso de no completarse, y ERROR en caso de que ocurra algún fallo
	 */
	@SuppressWarnings("finally")
	public String UnirInvitadoUser(){
		boolean error = false;
		boolean exito = false;
		
		try{
//			System.out.println("--------------- Dentro de UnirInvitadoUser()");
//			System.out.println("--------------- iduser--> "+iduser+"    id --> "+torneo.getId());
			
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			
			try{			
				userTorneoDAO = new UserTorneoDAOImpl();
				
				UserTorneo ut = new UserTorneo(FuncionesAuxiliares.NORMALUSER,true);
				
				Torneo tor = (Torneo)session.load(Torneo.class,torneo.getId());
				User us = (User) session.load(User.class, iduser);
				
				UserTorneoPK userTorPK = new UserTorneoPK(us,tor);
				
				ut.setId(userTorPK); // Seteamos la PK del userTorneo
				
				Set<UserTorneo> setU = tor.getUserTorneos();
				List<UserTorneo> listUT = new ArrayList<UserTorneo>(setU);
				
				ut.setPosicion(FuncionesAuxiliares.SacarPosicionMasBajaUserTorneo(listUT));
				
				userTorneoDAO = new UserTorneoDAOImpl();
				userTorneoDAO.mergeUserTorneo(ut);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																"where nombre='AltaUsuarioTorneo'").uniqueResult());
				noticia.setParentUsuario(us);
				noticia.setParentTorneo(tor);
				
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
	 * Ordena las posiciones de los usuarios previamente al sorteo de calendario
	 * @param tor Torneo 
	 * @return SUCCESS en caso de éxito, INPUT en caso de no completarse, y ERROR en caso de que ocurra algún fallo
	 */
	@SuppressWarnings("finally")
	public String OrdenarPosicionesUserTorneo(Torneo tor){
		boolean error = false;
		boolean exito = false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			
			try{
//				Torneo tor = (Torneo)session.load(Torneo.class,torneo.getId());
				
//				List<UserTorneo> listUT = new ArrayList<UserTorneo>(tor.getUserTorneos());
//				List<UserTorneo> listaParticipantes = new ArrayList<UserTorneo>();
//				
//				Iterator<UserTorneo> it = listUT.iterator();
//				while(it.hasNext()){
//					UserTorneo ut = it.next();
//					
//					if(ut.getRango() <= FuncionesAuxiliares.NORMALUSER && ut.isParticipante()){
//						listaParticipantes.add(ut);
//					}
//				}
				userTorneoDAO = new UserTorneoDAOImpl();
				
				List<UserTorneo> listaParticipantes = userTorneoDAO.UserTorneoParticipante(tor);
				
				Collections.sort(listaParticipantes, new PosicionUserTorneoComparator());
				
				List<Integer> posicionesVacias = new ArrayList<Integer>();
				
//				Integer tamano = 0;
				Iterator<UserTorneo> it = listaParticipantes.iterator();				
				
				Integer i = 1;
				while(it.hasNext()){
					UserTorneo ut = it.next();
						
					while(ut.getPosicion() != i){
						posicionesVacias.add(i);
						System.out.println("Posicion vacia --> "+i);
						i++;
					}
					
					i++;
				}
				
				
				if(!posicionesVacias.isEmpty()){
					for(i=0;i < posicionesVacias.size();i++){					
						UserTorneo usTo = listaParticipantes.get(listaParticipantes.size()-1-i);
						
						if(usTo.getPosicion() > posicionesVacias.get(i)){
							System.out.println(usTo.getId().getParentUser().getAlias()+
									" pasa a la Posicion vacia --> "+posicionesVacias.get(i));
							usTo.setPosicion(posicionesVacias.get(i));
							userTorneoDAO.mergeUserTorneo(usTo);
						}
						else{
							break;
						}
					}
				}
				
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
	 * Ordena las posiciones de los equipos previamente al sorteo de calendario
	 * @param tor Torneo 
	 * @return SUCCESS en caso de éxito, INPUT en caso de no completarse, y ERROR en caso de que ocurra algún fallo
	 */
	@SuppressWarnings("finally")
	public String OrdenarPosicionesEquipoTorneo(Torneo tor){
		boolean error = false;
		boolean exito = false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			
			try{
//				Torneo tor = (Torneo)session.load(Torneo.class,torneo.getId());
				
//				List<EquipoTorneo> listET = new ArrayList<EquipoTorneo>(tor.getEquipoTorneos());
//				List<EquipoTorneo> listaParticipantes = new ArrayList<EquipoTorneo>();
//				
//				Iterator<EquipoTorneo> it = listET.iterator();
//				while(it.hasNext()){
//					EquipoTorneo et = it.next();
//					
//					if(et.getRango() <= FuncionesAuxiliares.NORMALUSER){
//						listaParticipantes.add(et);
//					}
//				}
				
				equipoTorneoDAO = new EquipoTorneoDAOImpl();
				
				List<EquipoTorneo> listaParticipantes = equipoTorneoDAO.EquipoTorneoParticipante(tor);
								
				Collections.sort(listaParticipantes, new PosicionEquipoTorneoComparator());								
				
				List<Integer> posicionesVacias = new ArrayList<Integer>();
				
//				Integer tamano = 0;
				Iterator<EquipoTorneo> it = listaParticipantes.iterator();				
				
				Integer i = 1;
				while(it.hasNext()){
					EquipoTorneo et = it.next();
						
					while(et.getPosicion() != i){
						posicionesVacias.add(i);
						System.out.println("Posicion vacia --> "+i);
						i++;
					}
					
					i++;
				}
				
				
				if(!posicionesVacias.isEmpty()){		
					for(i=0;i < posicionesVacias.size();i++){					
						EquipoTorneo eqTo = listaParticipantes.get(listaParticipantes.size()-1-i);
						
						if(eqTo.getPosicion() > posicionesVacias.get(i)){
//							System.out.println(eqTo.getId().getParentEquipo().getNombre()+
//									" pasa a la Posicion vacia --> "+posicionesVacias.get(i));
							eqTo.setPosicion(posicionesVacias.get(i));
							equipoTorneoDAO.mergeEquipoTorneo(eqTo);
						}
						else{
							break;
						}
					}
				}
				
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
	 * Une equipo invitado al torneo
	 * @return SUCCESS en caso de éxito, INPUT en caso de no completarse, y ERROR en caso de que ocurra algún fallo
	 */
	@SuppressWarnings("finally")
	public String UnirInvitadoEquipo(){
		boolean error = false;
		boolean exito = false;
		
		try{
//			System.out.println("--------------- Dentro de UnirInvitadoUser()");
//			System.out.println("--------------- iduser--> "+iduser+"    id --> "+torneo.getId());
			
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			
			try{			
				equipoTorneoDAO = new EquipoTorneoDAOImpl();
				
				EquipoTorneo et = new EquipoTorneo();
				
				Torneo tor = (Torneo)session.load(Torneo.class,torneo.getId());
				Equipo eq = (Equipo) session.load(Equipo.class, idequipo);
				
				EquipoTorneoPK eqTorPK = new EquipoTorneoPK(eq,tor);
				
				et.setId(eqTorPK); // Seteamos la PK del equipoTorneo
				
				Set<EquipoTorneo> setE = tor.getEquipoTorneos();
				List<EquipoTorneo> listET = new ArrayList<EquipoTorneo>(setE);
				
				et.setPosicion(FuncionesAuxiliares.SacarPosicionMasBajaEquipoTorneo(listET));
				
				equipoTorneoDAO = new EquipoTorneoDAOImpl();
				equipoTorneoDAO.mergeEquipoTorneo(et);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																"where nombre='AltaEquipoTorneo'").uniqueResult());
				noticia.setParentEquipo(eq);
				noticia.setParentTorneo(tor);
				
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
	 * Acepta la solicitud de entrada al torneo del usuario
	 * @return SUCCESS en caso de éxito, INPUT en caso de no completarse, y ERROR en caso de que ocurra algún fallo
	 */
	@SuppressWarnings("finally")
	public String AceptarUserRequest(){
		boolean error = false;
		boolean exito = false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			if(SUCCESS == SetearTorneo(tor)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.MOD)){ // Hay que ser al menos MOD
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
				
				UserTorneo userTorneo = (UserTorneo)session.get(UserTorneo.class,
						new UserTorneoPK(us,tor));
				
				if(userTorneo != null){
					if(userTorneo.getRango() != FuncionesAuxiliares.NORMALUSER){
						return ERROR;
					}					
				}
				else{
					userTorneo = new UserTorneo(FuncionesAuxiliares.NORMALUSER);
					userTorneo.setId(new UserTorneoPK(
									us,
									tor)); 
					// Seteamos la PK del userComunidad	
				}
				
						
				userTorneoDAO = new UserTorneoDAOImpl();
				userTorneoDAO.mergeUserTorneo(userTorneo);
				
				// Borramos el request de la tabla intermedia.
				BorrarSolicitud(us);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																		"where nombre='AceptadoRequestUsuarioTorneo'").uniqueResult());
				noticia.setParentTorneo(tor);
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
	 * Deniega la solicitud de entrada al torneo del usuario
	 * @return SUCCESS en caso de éxito, INPUT en caso de no completarse, y ERROR en caso de que ocurra algún fallo
	 */
	@SuppressWarnings("finally")
	public String DenegarUserRequest(){
		boolean error = false;
		boolean exito = false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			
			if(SUCCESS == SetearTorneo(tor)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.MOD)){ // Hay que ser al menos MOD
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
																		"where nombre='DenegadoRequestUsuarioTorneo'").uniqueResult());
				noticia.setParentTorneo(tor);
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
	 * Acepta la solicitud de entrada al torneo del equipo
	 * @return SUCCESS en caso de éxito, INPUT en caso de no completarse, y ERROR en caso de que ocurra algún fallo
	 */
	@SuppressWarnings( "finally" )
	public String AceptarEquipoRequest(){
		boolean error = false;
		boolean exito = false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			if(SUCCESS == SetearTorneo(tor)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.MOD)){ // Hay que ser al menos MOD
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
				
				EquipoTorneo equipoTorneo = (EquipoTorneo)session.get(EquipoTorneo.class,
						new EquipoTorneoPK(eq,tor));
				
				
				
				if(equipoTorneo != null){
					if(equipoTorneo.getRango() == FuncionesAuxiliares.EXMIEMBRO){
						equipoTorneo.setRango(FuncionesAuxiliares.NORMALUSER);
					}					
				}
				else{
					equipoTorneo = new EquipoTorneo();
					equipoTorneo.setId(new EquipoTorneoPK(
									eq,
									tor));
					// Seteamos la PK del equipoTorneo

				}
				
				List<EquipoTorneo> listUT = new ArrayList<EquipoTorneo>(tor.getEquipoTorneos());
				
				equipoTorneo.setPosicion(FuncionesAuxiliares.SacarPosicionMasBajaEquipoTorneo(listUT));
						
				equipoTorneoDAO = new EquipoTorneoDAOImpl();
				equipoTorneoDAO.mergeEquipoTorneo(equipoTorneo);
				
				// Borramos el request de la tabla intermedia.
				BorrarSolicitudEquipo(eq);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																		"where nombre='AceptadoRequestEquipoTorneo'").uniqueResult());
				noticia.setParentTorneo(tor);
				noticia.setParentEquipo(eq);
				
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
	 * Deniega la solicitud de entrada al torneo del equipo
	 * @return SUCCESS en caso de éxito, INPUT en caso de no completarse, y ERROR en caso de que ocurra algún fallo
	 */
	@SuppressWarnings("finally")
	public String DenegarEquipoRequest(){
		boolean error = false;
		boolean exito = false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			
			if(SUCCESS == SetearTorneo(tor)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.MOD)){ // Hay que ser al menos MOD
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
				Equipo eq = (Equipo)session.get(Equipo.class,(Serializable)idequipo);
								
				BorrarSolicitudEquipo(eq);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																		"where nombre='DenegadoRequestEquipoTorneo'").uniqueResult());
				noticia.setParentTorneo(tor);
				noticia.setParentEquipo(eq);
				
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
	 * Borra la solicitud de entrada de un usuario al torneo
	 * @param us Usuario del que borrar la solicitud 
	 */
	public void BorrarSolicitud(User us){
			Set<Torneo> setT = us.getTorneosRequest();
			
			Iterator<Torneo> it = setT.iterator();
			while(it.hasNext()){
				Torneo torn = it.next();
				if(torn.getId() == torneo.getId()){
					setT.remove(torn);
				}
			}
			
			us.setTorneosRequest(setT);
			
			userDAO = new UserDAOImpl();
			userDAO.mergeUser(us);
		}
	
	/**
	 * Borra la solicitud de entrada de un equipo al torneo
	 * @param eq Equipo del que borrar la solicitud 
	 */
	public void BorrarSolicitudEquipo(Equipo eq){
		Set<Torneo> setT = eq.getTorneosRequest();
		
		Iterator<Torneo> it = setT.iterator();
		while(it.hasNext()){
			Torneo torn = it.next();
			if(torn.getId() == torneo.getId()){
				setT.remove(torn);
			}
		}
		
		eq.setTorneosRequest(setT);
		
		equipoDAO = new EquipoDAOImpl();
		equipoDAO.mergeEquipo(eq);
	}
	
	/**
	 * Cambiar la confirmación de registro del torneo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String CambiarConfirmacion()
	{
		boolean error=false;
		boolean exito=false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			if(tor != null){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo,FuncionesAuxiliares.ADMIN)){ // Necesitas ser al menos ADMIN
					return ERROR;
				}			
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{				
				if(tor.isRegRequest() == torneo.isRegRequest()){
					exito = true;
				}
				else{
					tor.setRegRequest(torneo.isRegRequest());
					
					// Guardamos la comunidad en la BBDD
					torneoDAO = new TorneoDAOImpl();
					tor = torneoDAO.mergeTorneo(tor);
					
					Noticia noticia=null;
					
					if(tor.isRegRequest()){
						noticia = new Noticia();
						noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
								"where nombre='ConfirmacionRegistroTorneoOn'").uniqueResult());
					}
					else{
						noticia = new Noticia();
						noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
								"where nombre='ConfirmacionRegistroTorneoOff'").uniqueResult());
					}
					
					noticia.setParentTorneo(tor);
					
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
	 * Cambiar la contraseña del torneo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String CambiarPassword()
	{
		boolean error=false;
		boolean exito=false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			if(tor != null){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo,FuncionesAuxiliares.ADMIN)){ // Necesitas ser al menos ADMIN
					return ERROR;
				}			
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{				
				if(tor.isPasswordProtected() == false && torneo.isPasswordProtected() == false){
					exito = true;
				}
				else{
					tor.setPasswordProtected(torneo.isPasswordProtected());
					
					if(tor.isPasswordProtected()){	
						Properties props = PropertiesUtil.loadProperties("encriptado/encriptado.properties"); 
						StringEncrypter stringEncrypter = new StringEncrypter(props.getProperty("passPhrase"));
						
						tor.setJoinPassword(stringEncrypter.encrypt(torneo.getJoinPassword().trim())); // Encriptamos la contraseña
					}
					else{
						tor.setJoinPassword(null);
					}
					
					// Guardamos el torneo en la BBDD
					torneoDAO = new TorneoDAOImpl();
					tor = torneoDAO.mergeTorneo(tor);
					
					Noticia noticia = new Noticia();
					noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																			"where nombre='PasswordTorneoCambiado'").uniqueResult());
					noticia.setParentTorneo(tor);
					
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
	 * Banea al usuario del torneo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String BanearUsuario()
	{
		boolean error=false;
		boolean exito=false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			
			if(SUCCESS == SetearTorneo(tor)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.MOD)){ // Hay que ser al menos MOD
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				System.out.println("iduser --> "+iduser);
				
				User us = (User)session.get(User.class,(Serializable)iduser);
				
				UserTorneo usTo = (UserTorneo)session.get(UserTorneo.class,new UserTorneoPK(us,tor));
						
				usTo.setRango(FuncionesAuxiliares.BANEADO);
				usTo.setPosicion(null);
				
				userTorneoDAO = new UserTorneoDAOImpl();				
				userTorneoDAO.mergeUserTorneo(usTo);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																		"where nombre='BaneadoUsuarioTorneo'").uniqueResult());
				noticia.setParentTorneo(tor);
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
	 * Banea al equipo del torneo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String BanearEquipo()
	{
		boolean error=false;
		boolean exito=false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			
			if(SUCCESS == SetearTorneo(tor)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.MOD)){ // Hay que ser al menos MOD
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				System.out.println("iduser --> "+iduser);
				
				Equipo eq = (Equipo)session.get(Equipo.class,(Serializable)idequipo);
				
				EquipoTorneo eqTo = (EquipoTorneo)session.get(EquipoTorneo.class,new EquipoTorneoPK(eq,tor));
						
				eqTo.setRango(FuncionesAuxiliares.BANEADO);
				eqTo.setPosicion(null);
				
				equipoTorneoDAO = new EquipoTorneoDAOImpl();				
				equipoTorneoDAO.mergeEquipoTorneo(eqTo);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																		"where nombre='BaneadoEquipoTorneo'").uniqueResult());
				noticia.setParentTorneo(tor);
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
	 * Expulsar al usuario del torneo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String ExpulsarUsuario()
	{
		boolean error=false;
		boolean exito=false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			if(SUCCESS == SetearTorneo(tor)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.MOD)){ // Hay que ser al menos MOD
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
				
				UserTorneo usTo = (UserTorneo)session.get(UserTorneo.class,new UserTorneoPK(us,tor));
						
				usTo.setRango(FuncionesAuxiliares.EXMIEMBRO);
				usTo.setPosicion(null);
				
				userTorneoDAO = new UserTorneoDAOImpl();				
				userTorneoDAO.mergeUserTorneo(usTo);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																		"where nombre='ExpulsadoUsuarioTorneo'").uniqueResult());
				noticia.setParentTorneo(tor);
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
	 * Expulsa al equipo del torneo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String ExpulsarEquipo()
	{
		boolean error=false;
		boolean exito=false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			if(SUCCESS == SetearTorneo(tor)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.MOD)){ // Hay que ser al menos MOD
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
				
				EquipoTorneo eqTo = (EquipoTorneo)session.get(EquipoTorneo.class,new EquipoTorneoPK(eq,tor));
						
				eqTo.setRango(FuncionesAuxiliares.EXMIEMBRO);
				eqTo.setPosicion(null);
				
				equipoTorneoDAO = new EquipoTorneoDAOImpl();				
				equipoTorneoDAO.mergeEquipoTorneo(eqTo);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																		"where nombre='ExpulsadoEquipoTorneo'").uniqueResult());
				noticia.setParentTorneo(tor);
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
	 * Modificar rango de usuario dentro del torneo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String EditarRango(){
		boolean error=false;
		boolean exito=false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			if(SUCCESS == SetearTorneo(tor)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.MOD)){ // Hay que ser al menos MOD
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
			
				UserTorneo usTo = (UserTorneo)session.get(UserTorneo.class,new UserTorneoPK(us,tor));
				
//				String rangoUs = FuncionesAuxiliares.StringRango(usTo.getRango());
				
				if(rangoTor != usTo.getRango()){
					usTo.setRango(rangoTor);
							
					userTorneoDAO = new UserTorneoDAOImpl();				
					userTorneoDAO.mergeUserTorneo(usTo);
					
					Noticia noticia = new Noticia();
					noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																			"where nombre='RangoUsuarioTorneoCambiado'").uniqueResult());
					noticia.setNuevoRango(rangoTor);
					noticia.setParentTorneo(tor);
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
	 * Sortear calendario del torneo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings({ "finally", "unchecked" })
	public String Sortear(){
		boolean error = false;	
		boolean exito = false;
		
		try{
			System.out.println("Estamos dentro de Sortear, el id del torneo es -> "+torneo.getId());
			Torneo tor = RecuperarTorneoDetallado(null);
			
			if(!auto.equals("si")){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.MOD)){ // Hay que ser al menos MOD
					return ERROR;
				}	
			}
			
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{				
				List<Object> listEquipos = null;
				
				partidoDAO = new PartidoDAOImpl();
				
				if(tor.getLiga()){					
					tor.setJornadasJugables(torneo.getJornadasJugables());					
				}
				
				else{					
					tor.setJornadasJugables(1);
				}
				
				if(empieza != null){
					if(empieza){
						tor.setJornadaActual(1);
					}
					else{
						tor.setJornadaActual(0);
					}
				}
				
				else{
					tor.setJornadaActual(tor.getJornadaActual()+1);
				}
						
				
				
				if(tor.getPorEquipos()){ // Si el torneo es por equipos ( no es 1vs1 )
					Set<EquipoTorneo> setEquipoTorneo = tor.getEquipoTorneos();
					
					
					Iterator<EquipoTorneo> itET = setEquipoTorneo.iterator();
					
					listEquipos = new ArrayList<Object>();
					
					while(itET.hasNext()){
						EquipoTorneo et = itET.next();

						if(FuncionesAuxiliares.ComprobarPermisos(et.getRango(),FuncionesAuxiliares.NORMALUSER)==SUCCESS &&
								et.getEliminado() == false){ 
							// Si no es un expulsado o baneado y no ha sido eliminado, lo añadimos en la lista.
							listEquipos.add(et.getId().getParentEquipo());							
						}
					}
					
					partidoEqList = new ArrayList<PartidoEquiposVersus>();
					
					if(tor.getLiga()){ // Si es liga		
						partidoEqList = (List<PartidoEquiposVersus>) SorteoUtil.sorteoLiga(listEquipos,tor);
					}
					else{ // Si es copa
						partidoEqList = (List<PartidoEquiposVersus>) SorteoUtil.sorteoCopa(listEquipos, tor);
					}
	
					partidoEqList = partidoDAO.mergePartidoEquiposVersusList(partidoEqList);
					
					Set<PartidoEquiposVersus> setP = new HashSet<PartidoEquiposVersus>(partidoEqList);
					
					tor.setPartidosEqVersus(setP);
					
				}
			
				else{ // Si el torneo es individual
					Set<UserTorneo> setUserTorneo = tor.getUserTorneos();
					
					
					Iterator<UserTorneo> itUT = setUserTorneo.iterator();
					
					listEquipos = new ArrayList<Object>();
					
					while(itUT.hasNext()){
						UserTorneo ut = itUT.next();
						System.out.println("UserTorneo -----> "+ut.getId().getParentUser().getAlias());
						if(ut.isParticipante() && 
								FuncionesAuxiliares.ComprobarPermisos(ut.getRango(),FuncionesAuxiliares.NORMALUSER)==SUCCESS &&
								ut.getEliminado() == false){ 
							// Si participa, no es un expulsado o baneado, y no ha sido eliminado lo añadimos en la lista.
							listEquipos.add(ut.getId().getParentUser());
							System.out.println("Participante ---------------> "+ut.getId().getParentUser().getAlias());
						}
					}
					
					partidoIndList = new ArrayList<PartidoIndividualVersus>();
					
					if(tor.getLiga()){ // Si es liga
						partidoIndList = (ArrayList<PartidoIndividualVersus>) SorteoUtil.sorteoLiga(listEquipos,tor);
					}
					else{ // Si es copa
						partidoIndList = (ArrayList<PartidoIndividualVersus>) SorteoUtil.sorteoCopa(listEquipos,tor);
					}
					
//					Iterator<PartidoIndividualVersus> it = partidoIndList.iterator();
//					
//					while(it.hasNext()){
//						PartidoIndividualVersus pa = it.next();
//						System.out.println("Jornada "+pa.getJornada()+"  "+pa.getParentLocal().getAlias()+" - "+pa.getParentVisitante().getAlias());
//					}
					
					partidoIndList = partidoDAO.mergePartidoIndividualVersusList(partidoIndList);
					
					Set<PartidoIndividualVersus> setP = new HashSet<PartidoIndividualVersus>(partidoIndList);
					
					tor.setPartidosIndVersus(setP);

				}
				
				tor.setSorteado(true);
				tor.setEstado(EN_JUEGO);
				
				tor.setModoEnfrentamiento(false); // VS
				
				if(tor.getLiga()){
					Regla reg = (Regla) session.load(Regla.class, entero);
					
					tor.setParentRegla(reg);	
				}
				
				// Actualizamos el torneo.
				torneoDAO = new TorneoDAOImpl();
				torneoDAO.mergeTorneo(tor);
				
				Noticia noticia = new Noticia();
				noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
																		"where nombre='TorneoSorteado'").uniqueResult());
				noticia.setParentTorneo(tor);
				
				noticiaDAO = new NoticiaDAOImpl();
				noticiaDAO.mergeNoticia(noticia);
				
				if(tor.getPorEquipos()){
					OrdenarPosicionesEquipoTorneo(tor);
				}
				else{
					OrdenarPosicionesUserTorneo(tor);
				}
				
				SetearTorneo(tor);
				
				exito = true;
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
				if(session.isOpen()){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
//						session.close();
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
	 * Cambiar número de jornadas jugables al mismo tiempo del torneo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String CambiarJornadasJugables(){
		boolean error = false;	
		boolean exito = false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			
			if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.MOD)){ // Hay que ser al menos MOD
				return ERROR;
			}

			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{				
				tor.setJornadasJugables(torneo.getJornadasJugables());
				
				// Actualizamos el torneo.
				torneoDAO = new TorneoDAOImpl();
				torneoDAO.mergeTorneo(tor);
				
				SetearTorneo(tor);
				
				exito = true;
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
				if(session.isOpen()){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
//						session.close();
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
	 * Comprueba el permiso de borrado dentro del torneo
	 * @return SUCCESS en caso de éxito, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String ComprobarPermisoBorrado(){
		boolean error = false;	
		
		try{
			System.out.println("Entramos a ComprobarPermisosBorrado...");
			Torneo tor = RecuperarTorneoDetallado(null);
			if(SUCCESS == SetearTorneo(tor)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.ADMIN)){ // Hay que ser al menos ADMIN
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
	 * Elimina todos los usuarios invitados y equipos invitados del torneo
	 * @return SUCCESS en caso de éxito, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String EliminarInvitados()
	{				
		boolean error=false;
		
		try{
			System.out.println(" Eliminar Invitados !!");
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
						
			String hql = "delete from User where invitado= :invitado and id IN (select id.parentUser from UserTorneo where id.parentTorneo= :parentTorneo)";
			session.createQuery(hql).setBoolean("invitado", true).setInteger("parentTorneo",torneo.getId()).executeUpdate();
			
			System.out.println(" HQL ---> "+hql);
			
			hql = "delete from Equipo where invitado= :invitado and id IN (select id.parentEquipo from EquipoTorneo where id.parentTorneo= :parentTorneo)";
			session.createQuery(hql).setBoolean("invitado", true).setInteger("parentTorneo",torneo.getId()).executeUpdate();
			
			System.out.println(" HQL ---> "+hql);
		}
		catch(HibernateException he){
			he.printStackTrace();
			error = true;
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
	 * Comprueba que todos los partidos de la jornada del torneo hayan sido confirmados
	 * @return SUCCESS en caso de éxito, "incompleto" si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String ComprobarPartidosJornadaConfirmados(){
		boolean error=false;
		boolean exito=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
//				System.out.println("La id del partido es --> "+partido.getId());
				Torneo tor = (Torneo)session.load(Torneo.class,torneo.getId());
				
				System.out.println("--- ComprobarPartidos... torneo -> "+tor.getNombre()+
						"   id -> "+tor.getId());

				partidoDAO = new PartidoDAOImpl();
				
				exito = partidoDAO.ComprobarPartidosJornada(tor);
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
					return "incompleto";
				}
			}
		}	
	}
	
	/**
	 * Eliminar el torneo
	 * @return SUCCESS
	 */
	public String delete()
	{
		System.out.println("Dentro de delete (torneoAction) !!");
		Torneo tor = new Torneo(torneo.getId());
			
		torneoDAO = new TorneoDAOImpl();
		torneoDAO.deleteTorneo(tor);
				
//			Noticia noticia = new Noticia();
//			noticia.setParentCategoriaNoticia((CategoriaNoticia)session.createQuery("from CategoriaNoticia " +
//																		"where nombre='TorneoEliminado'").uniqueResult());
//			noticia.setParentTorneo(tor);
//				
//			noticiaDAO = new NoticiaDAOImpl();
//			noticiaDAO.mergeNoticia(noticia);
				
		return SUCCESS;
		
	}	

	/**
	 * Buscador de torneos
	 * @return SUCCESS
	 */
	public String BuscarTorneo(){
		String where="";
		String aliasTable="tor";

		// ------------------- SearchWord -----------------------------
		if(torneo.getNombre() != null && !torneo.getNombre().trim().equals("")){
			where = " where "+aliasTable+".nombre like '%"+torneo.getNombre()+"%'";
		}			
		
		//------------------ CondicionWhere ---------------------------
		
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
		
		if(VarOnline!=null){
			where += FuncionesAuxiliares.WhereOrAnd(where);
			
			if(VarOnline == 1){ // Online
				where +=" online=1";
			}
			else{
				where +=" online=0";
			}
		}
		if(VarLiga != null){
			where += FuncionesAuxiliares.WhereOrAnd(where);			
			if(VarLiga == 1){ // Liga
				where +=" liga=1";
			}
			else{ // Copa
				where +=" liga=0";
			}
		}
		if(VarPorEquipos != null){
			where += FuncionesAuxiliares.WhereOrAnd(where);			
			if(VarPorEquipos == 1){ // por equipos
				where += " porEquipos=1";
			}
			else{ // individual
				where += " porEquipos=0";
			}
		}
		
		Integer rounds = torneo.getRondas();
		if(rounds != null){
			where += FuncionesAuxiliares.WhereOrAnd(where);
			
			if(rounds == 0){
				rounds = getRondasExtras();
			}
			where += " rondas="+rounds;
		}
		
		if(torneo.getFechaComienzo()!=null){
			where += FuncionesAuxiliares.WhereOrAnd(where);
			
			where += " fechaComienzo="+torneo.getFechaComienzo();
		}
		
		if(torneo.getFechaFinal()!=null){
			where += FuncionesAuxiliares.WhereOrAnd(where);
			
			where += " fechaFinal="+torneo.getFechaFinal();
		}		
			
		//------------------ Extra ------------------------------------
		String tablasAux="";
		if(plataforma!=null && plataforma != 0){
			where += FuncionesAuxiliares.WhereOrAnd(where);	
			
			tablasAux = ",Plataforma as pl ";
			where += " pl.id="+plataforma+" and "+aliasTable+" in elements(pl.torneos)";
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
			where += " act.id="+acti+" and "+aliasTable+" in elements(act.torneos)";			
		}		
		
//		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		
		torneoDAO = new TorneoDAOImpl();
		torneoList = torneoDAO.listTorneoQuery(aliasTable,where,tablasAux);
		
		return SUCCESS;
	}

	/**
	 * Comenzar la competición, hacer introducible los primeros resultados de partidos
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String EmpezarTorneo(){
		boolean error=false;
		boolean exito=false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			if(SUCCESS == SetearTorneo(tor)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.ADMIN)){ // Hay que ser al menos ADMIN
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{		
				tor.setJornadaActual(1);
				
				torneoDAO = new TorneoDAOImpl();
				torneoDAO.mergeTorneo(tor);
				
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
	 * Pasar a las siguientes jornadas jugables
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, ERROR si ocurre algún fallo,
	 * y "copa" si se trata de un torneo de formato copa
	 */
	@SuppressWarnings("finally")
	public String PasarJornadas(){
		boolean error=false;
		boolean exito=false;
		boolean copa = false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			
			if(!tor.getLiga()){
				System.out.println("--- Dentro de PasarJornadas, es una copa! ---");
				copa=true;
				return "copa";
			}
			
			if(!auto.equals("si")){
				if(SUCCESS == SetearTorneo(tor)){
					if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.ADMIN)){ // Hay que ser al menos ADMIN
						error =true;
						return ERROR;
					}
				}
				else{
					error = true;
					return ERROR;
				}	
			}
			
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
//				System.out.println("Jornada Actual --> "+torneo.getJornadaActual()+
//						",  Jornadas Jugables --> "+torneo.getJornadasJugables());
				
				tor.setJornadaActual(tor.getJornadaActual()+tor.getJornadasJugables());
				
//				System.out.println("Nueva Jornada Actual --> "+torneo.getJornadaActual());
				
				torneoDAO = new TorneoDAOImpl();
				torneoDAO.mergeTorneo(tor);
				
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
					if(copa){
						return "copa";
					}
					else{
						return INPUT;	
					}					
				}
			}
		}
	}
	
	/**
	 * Pasar jornadas automáticamente
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String PasarJornadasAuto(){
		boolean error=false;
		boolean exito=false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
//				System.out.println("Jornada Actual --> "+torneo.getJornadaActual()+
//						",  Jornadas Jugables --> "+torneo.getJornadasJugables());
				
				tor.setJornadaActual(tor.getJornadaActual()+tor.getJornadasJugables());
				
//				System.out.println("Nueva Jornada Actual --> "+torneo.getJornadaActual());
				
				torneoDAO = new TorneoDAOImpl();
				torneoDAO.mergeTorneo(tor);
				
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
	 * Recupera los partidos rechazados del torneo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String PartidosRechazados(){
		boolean error=false;
		boolean exito=false;
		
		try{
			Torneo tor = RecuperarTorneoDetallado(null);
			if(SUCCESS == SetearTorneo(tor)){
				if(ERROR == FuncionesAuxiliares.ComprobarPermisos(rolnivelTorneo, FuncionesAuxiliares.ADMIN)){ // Hay que ser al menos ADMIN
					return ERROR;
				}
			}
			else{
				return ERROR;
			}
				
			partidoDAO = new PartidoDAOImpl();
			if(tor.getPorEquipos()){
				partidosRechazadosEquiposList = partidoDAO.PartidosRechazadosEquipos(torneo);
			}
			else{
				partidosRechazadosIndividualList = partidoDAO.PartidosRechazadosIndividual(tor);	
			}
			
			exito=true;
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
	 * Nos lleva al registro de un nuevo torneo
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings({ "finally" })
	public String GoRegister(){
		boolean error = false;	
		boolean exito = false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				Comunidad com = (Comunidad) session.load(Comunidad.class, torneo.getId()); 
				// Ese torneo.getId() en realidad es la id de la comunidad
				
				actividadList = new ArrayList<Actividad>(com.getActividades());
				
				sesion = getSession();
				listHashMapUtil = new ListHashMapUtil();
				listHashMapUtil.SacarActividadesFull(sesion, " ", listaDeportes, listaCartas, listaJuegosdemesa, listaJuegos,actividadList);
				if(!listaJuegos.isEmpty()){
					List<Plataforma> plataformaList = new ArrayList<Plataforma>(com.getPlataformas());
					
					listHashMapUtil.SacarPlataformasFull(sesion, " ", listaPlataformas,plataformaList);
					listHashMapUtil.SacarBool(sesion,listaBooleanOnline);
				}
				else{
					listHashMapUtil.SacarBoolean(sesion, listaBooleanOnline, null, "no");
				}
				
				exito=true;
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
				
				UserTorneo usTo = (UserTorneo)session.get(UserTorneo.class,
						new UserTorneoPK((User)session.get(User.class,(Serializable)iduser),
											(Torneo)session.get(Torneo.class, torneo.getId())));
				
				cadena = FuncionesAuxiliares.StringRango(usTo.getRango());
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
	 * Nos lleva a la zona de administración de todos los torneos de la aplicación
	 * @return SUCCESS
	 */
	public String GoAdminTorneos(){
		return BuscarTorneo();
	}
	
	/**
	 * Mete al valuestack los valores para las estadísticas del torneo
	 * @return SUCCESS en caso de éxito, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String EstadisticasTorneo(){		
		boolean error=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{	
				Torneo tor = (Torneo)session.load(Torneo.class,torneo.getId());
				
				SetearTorneo(tor);
				
				Integer tam = 0;
				Set<UserTorneo> setUT = tor.getUserTorneos();
				Iterator<UserTorneo> itUT = setUT.iterator();
				
				while(itUT.hasNext()){
					if(SUCCESS == FuncionesAuxiliares.ComprobarPermisos(itUT.next().getRango(), 
													FuncionesAuxiliares.NORMALUSER)){
						tam++;
					}
				}
				
				listUsuarios.add(new IntegerInteger(1,tam));
				
				
				tam = 0;
				Set<EquipoTorneo> setET = tor.getEquipoTorneos();
				Iterator<EquipoTorneo> itET = setET.iterator();
				
				while(itET.hasNext()){
					if(SUCCESS == FuncionesAuxiliares.ComprobarPermisos(itET.next().getRango(), 
													FuncionesAuxiliares.NORMALUSER)){
						tam++;
					}
				}
				
				
				listEquipos.add(new IntegerInteger(2,tam));
				
				PartidosConfirmadosTorneo();
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
	 * Nos lleva al buscador de torneos
	 * @return SUCCESS
	 */
	public String GoSearch(){
		return SUCCESS;
	}
	
	
	public void prepare() throws Exception{ // Hago esto porque si no no encuentra la id de torneo, no se pq
		System.out.println("****** Prepare *******");		
	}
	
	public void prepareGoRegister(){
		System.out.println("****** PrepareGoRegister *******");
		
		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
//		listHashMapUtil.SacarBool(sesion,null,listaBooleanInteger);
		listHashMapUtil.SacarBool(sesion,listaBoolean);
		listHashMapUtil.SacarTipoTorneo(sesion, listaTipoTorneoBoolean);
		listHashMapUtil.SacarTipoParticipante(sesion, listaTipoParticipanteBoolean);
		listHashMapUtil.SacarRondas(sesion, null,listaRondas);
		listHashMapUtil.SacarEstados(sesion,null,listaEstados);
		
		//////////////////////////////
//		Iterator<Entry<Integer, String>> it = listaDeportes.entrySet().iterator();
//		
//		
//		while(it.hasNext()){
//			Entry<Integer, String> e = it.next();
//			
//			System.out.println("e.getKey() --> "+e.getKey()+"    e.getValue() --> "+e.getValue());
//		}
	}
	
	public void prepareMerge(){
		System.out.println("****** PrepareMerge *******");

		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
//		listHashMapUtil.SacarActividades(sesion, " ",listaDeportes, listaCartas, listaJuegosdemesa, listaJuegos);
//		listHashMapUtil.SacarPlataformas(sesion, " ", listaPlataformas);
//		GoRegister(); // Para sacar las actividades y plataformas que sean necesarias
//		listHashMapUtil.SacarBool(sesion,null,listaBooleanInteger);
		listHashMapUtil.SacarBool(sesion,listaBoolean);
		listHashMapUtil.SacarTipoTorneo(sesion, listaTipoTorneoBoolean);
		listHashMapUtil.SacarTipoParticipante(sesion, listaTipoParticipanteBoolean);
		listHashMapUtil.SacarRondas(sesion, null,listaRondas);
		listHashMapUtil.SacarEstados(sesion,null,listaEstados);
	}
	
	public void prepareGoSettings(){
		System.out.println("****** PrepareGoSettings *******");
		
		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarBool(sesion,listaBooleanOnline);
		listHashMapUtil.SacarBool(sesion,listaBoolean);
		listHashMapUtil.SacarTipoTorneo(sesion, listaTipoTorneoBoolean);
		listHashMapUtil.SacarTipoParticipante(sesion, listaTipoParticipanteBoolean);
		listHashMapUtil.SacarRondas(sesion, null,listaRondas);
		listHashMapUtil.SacarEstados(sesion,null,listaEstados);
		listHashMapUtil.SacarJornadasJugables(sesion,"todas",listaJornadasJugables,10);
	}
	
	public void prepareGoSearch(){
		System.out.println("------- prepareGoSearch() -----------");
		
		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarActividades(sesion, "todos", listaDeportes, listaCartas, listaJuegosdemesa, listaJuegos);
		listHashMapUtil.SacarPlataformas(sesion, "todas", listaPlataformas);
		listHashMapUtil.SacarBool(sesion,"todos",listaBooleanInteger);
		listHashMapUtil.SacarTipoTorneo(sesion,"todos", listaTipoTorneoInteger);
		listHashMapUtil.SacarTipoParticipante(sesion,"todos" ,listaTipoParticipanteInteger);
		listHashMapUtil.SacarRondas(sesion, "todas",listaRondas);
		listHashMapUtil.SacarEstados(sesion,"todos",listaEstados);		
	}
	
	public void prepareBuscarTorneo(){
		System.out.println("------- prepareBuscarTorneo() -----------");
		
		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarActividades(sesion, "todos", listaDeportes, listaCartas, listaJuegosdemesa, listaJuegos);
		listHashMapUtil.SacarPlataformas(sesion, "todas", listaPlataformas);
		listHashMapUtil.SacarBool(sesion,"todos",listaBooleanInteger);
		listHashMapUtil.SacarTipoTorneo(sesion,"todos", listaTipoTorneoInteger);
		listHashMapUtil.SacarTipoParticipante(sesion,"todos" ,listaTipoParticipanteInteger);
		listHashMapUtil.SacarRondas(sesion, "todas",listaRondas);
		listHashMapUtil.SacarEstados(sesion,"todos",listaEstados);		
	}
	
	public void prepareGoSorteoTorneo(){
		System.out.println("------- prepareGoSorteoTorneo() -----------");

		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarJornadasJugables(sesion,"todas",listaJornadasJugables,10);
		listHashMapUtil.SacarBool(sesion, listaBoolean);
	}
	
	public void prepareSorteoTorneo(){
		System.out.println("------- prepareSorteoTorneo() -----------");

		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarJornadasJugables(sesion,"todas",listaJornadasJugables,10);
		listHashMapUtil.SacarBool(sesion, listaBoolean);
	}
	
	
	public void prepareCambiarJornadasJugables(){
		System.out.println("------- prepareCambiarJornadasJugables() -----------");

		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarJornadasJugables(sesion,"todas",listaJornadasJugables,10);
	}	
	
	public void prepareGoPasswordTorneo(){
		System.out.println("------- prepareGoPasswordTorneo() -----------");
		
		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarBool(sesion, listaBoolean);
	}
	
	public void prepareCambiarPassword(){
		System.out.println("------- prepareCambiarPassword() -----------");
		
		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarBool(sesion, listaBoolean);
	}	
	
	public void prepareEditarRango(){
		System.out.println("--------- prepareEditarRango() -------------");
		
		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarRangosTorneo(sesion,roltorneo,listaRangos);
		
	}
	
	public void prepareGoEstadisticasTorneo(){
		System.out.println("------- prepareGoEstadisticasTorneo() -----------");
		
		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarEstadisticasTorneo(sesion,listaEstadisticas);
	}
	
	public void prepareGoEstadisticasUsuario(){
		System.out.println("------- prepareGoEstadisticasUsuario() -----------");
		
		sesion = getSession();
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarEstadisticasUsuarioTorneo(sesion,listaEstadisticas);
	}
	
	

	/**
	 * Lista todos los torneos
	 * @return SUCCESS
	 */
	public String TorneoList(){
//		torneoList = new ArrayList<Torneo>();
		
		torneoDAO = new TorneoDAOImpl();
//		torneoList = torneoDAO.listTorneo();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		
		torneoPaginatedList = torneoDAO.listTorneo(request);
		
		return SUCCESS;
	}
	
	/**
	 * Lista los torneos de un equipo o de un usuario
	 * @return SUCCESS
	 */
	public String TorneoListPropios(){
		if((Integer)sesion.get("equipo") == 0){
			return TorneoListByUser();
		}
		else{
			return TorneoListByEquipo();
		}
	}
	
	/**
	 * Lista los torneos de un equipo
	 * @return SUCCESS
	 */
	public String TorneoListByEquipo() {
		torneoDAO = new TorneoDAOImpl();
		
		torneoList = torneoDAO.listTorneoByIdEquipo((Integer)sesion.get("equipo"));
		
		return SUCCESS;			
	}
	
	/**
	 * Lista los torneos de un usuario
	 * @return SUCCESS
	 */
	public String TorneoListByUser() {
		torneoDAO = new TorneoDAOImpl();
		
		torneoList = torneoDAO.listTorneoByIdUser((Integer)sesion.get("id"));
		
		return SUCCESS;			
	}
	
	/**
	 * Lista los usuarios de un torneo
	 * @return SUCCESS
	 */
	public String UserListByTorneo(){
//		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		
		userDAO = new UserDAOImpl();

//		userPaginatedList = userDAO.listUserByTorneo(request,torneo);
		
		userList = userDAO.listUserByTorneo(torneo);
		
		return SUCCESS;
	}	
	
	/**
	 * Lista los equipos de un torneo
	 * @return SUCCESS
	 */
	public String EquipoListByTorneo(){
//		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
//		
		equipoDAO = new EquipoDAOImpl();
//
//		equipoPaginatedList = equipoDAO.listEquipoByTorneo(request,torneo);
		
		equipoList = equipoDAO.listEquipoByTorneo(torneo);
		
		return SUCCESS;
	}
	
	/**
	 * Lista los partidos de un torneo
	 * @return SUCCESS
	 */
	public String PartidoListByTorneo(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		partidoDAO = new PartidoDAOImpl();
		
		if(torneo.getPorEquipos()){
			equipoTorneoDAO = new EquipoTorneoDAOImpl();
			
			partidoPaginatedList = partidoDAO.listPartidoEquiposVersusByTorneo(request, torneo,
					equipoTorneoDAO.EquipoTorneoParticipante(torneo).size());
		}
		else{
			userTorneoDAO = new UserTorneoDAOImpl();
			
			partidoPaginatedList = partidoDAO.listPartidoIndividualVersusByTorneo(request, torneo,
					userTorneoDAO.UserTorneoParticipante(torneo).size());
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * Comprueba si el usuario es participante
	 * @return SUCCESS
	 */
	public Boolean comprobarParticipante(){
		Boolean participante = getParticipante();
		
		if(participante == null){ // Por defecto será participante true 
			return true;
		}
		
		else{
			return participante;
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
