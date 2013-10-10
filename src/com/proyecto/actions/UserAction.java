package com.proyecto.actions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import com.proyecto.dao.PartidoDAO;
import com.proyecto.dao.PartidoDAOImpl;
import com.proyecto.dao.UserDAO;
import com.proyecto.dao.UserDAOImpl;
import com.proyecto.dao.UserTorneoDAO;
import com.proyecto.dao.UserTorneoDAOImpl;
import com.proyecto.dominio.Comunidad;
import com.proyecto.dominio.IntegerInteger;
import com.proyecto.dominio.Municipio;
import com.proyecto.dominio.Pais;
import com.proyecto.dominio.PartidoIndividualVersus;
import com.proyecto.dominio.Torneo;
import com.proyecto.dominio.User;
import com.proyecto.dominio.UserComunidad;
import com.proyecto.dominio.UserEquipo;
import com.proyecto.dominio.UserTorneo;
import com.proyecto.dominio.UserTorneoPK;
import com.proyecto.util.displaytag.Paginate;
import com.proyecto.util.encriptado.StringEncrypter;
import com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares;
import com.proyecto.util.hibernate.HibernateUtil;
import com.proyecto.util.listas.ListHashMapUtil;
import com.proyecto.util.properties.GlobalResourceUtil;
import com.proyecto.util.properties.PropertiesUtil;

/**
* Esta clase implementa el Action asociado al
* usuario.
* @author Lucas Sánchez López
* @version 3.0
*/

public class UserAction extends ActionSupport implements ModelDriven<User>, Preparable, SessionAware {
	private static final long serialVersionUID = 1L;
	private User user = new User();
	private Comunidad comunidad = new Comunidad();
	private List<User> userList;
//	private List<Provincia> provinciaList;
//	private List<Municipio> municipioList;
	private Paginate userPaginatedList;
	private UserDAO userDAO;
	private UserTorneoDAO userTorneoDAO;
	private PartidoDAO partidoDAO;
	private String pw=null;
	private String prueba;
	private Integer genero;
	private Integer pais;
	private Integer provincia;
	private Integer municipio;
	private Integer region;
	private Integer idtorneo;
	private Integer entero;
	private LinkedHashMap<Integer,String> listaSexo = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaPaises = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaProvincias = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaMunicipios = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<String,String> listaLenguajes = new LinkedHashMap<String,String>();
	private List<IntegerInteger> listTorneosNoComenzados = new ArrayList<IntegerInteger>();
	private List<IntegerInteger> listTorneosEnJuego = new ArrayList<IntegerInteger>();
	private List<IntegerInteger> listTorneosFinalizados = new ArrayList<IntegerInteger>();
	private List<IntegerInteger> listComunidades = new ArrayList<IntegerInteger>();
	private List<IntegerInteger> listEquipos = new ArrayList<IntegerInteger>();
	private ListHashMapUtil listHashMapUtil;
	private Map sesion;
	
	SessionFactory sessionFactory = null;
	
	@SessionTarget
	Session session = null;
	
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	////////////////////////////////////////
	public LinkedHashMap<Integer, String> getListaSexo() {
		return listaSexo;
	}

	public void setListaSexo(LinkedHashMap<Integer, String> listaSexo) {
		this.listaSexo = listaSexo;
	}
	
	public LinkedHashMap<Integer, String> getListaPaises() {
		return listaPaises;
	}

	public void setListaPaises(LinkedHashMap<Integer, String> listaPaises) {
		this.listaPaises = listaPaises;
	}
	public LinkedHashMap<Integer, String> getListaProvincias() {
		return listaProvincias;
	}

	public void setListaProvincias(LinkedHashMap<Integer, String> listaProvincias) {
		this.listaProvincias = listaProvincias;
	}

	public LinkedHashMap<Integer, String> getListaMunicipios() {
		return listaMunicipios;
	}

	public void setListaMunicipios(LinkedHashMap<Integer, String> listaMunicipios) {
		this.listaMunicipios = listaMunicipios;
	}	
	
	public LinkedHashMap<String, String> getListaLenguajes() {
		return listaLenguajes;
	}

	public void setListaLenguajes(LinkedHashMap<String, String> listaLenguajes) {
		this.listaLenguajes = listaLenguajes;
	}
	
	
	
////////////////////////////////////////////





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

	public List<IntegerInteger> getListComunidades() {
		return listComunidades;
	}

	public void setListComunidades(List<IntegerInteger> listComunidades) {
		this.listComunidades = listComunidades;
	}

	public List<IntegerInteger> getListEquipos() {
		return listEquipos;
	}

	public void setListEquipos(List<IntegerInteger> listEquipos) {
		this.listEquipos = listEquipos;
	}

	public User getUser(){
		return user;
	}

	public void setUser(User user){
		this.user = user;
	}
	
	public Comunidad getComunidad() {
		return comunidad;
	}

	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public Integer getGenero() {
		return genero;
	}

	public void setGenero(Integer genero) {
		this.genero = genero;
	}
	
	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public Integer getRegion() {
		return region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	public String getPrueba() {
		return prueba;
	}
	
	public Integer getPais() {
		return pais;
	}

	public void setPais(Integer pais) {
		this.pais = pais;
	}

	public void setPrueba(String prueba) {
		this.prueba = prueba;
	}	

	public Paginate getUserPaginatedList() {
		return userPaginatedList;
	}

	public void setUserPaginatedList(Paginate userPaginatedList) {
		this.userPaginatedList = userPaginatedList;
	}
	
	public Integer getProvincia() {
		return provincia;
	}

	public void setProvincia(Integer provincia) {
		this.provincia = provincia;
	}

	public Integer getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Integer municipio) {
		this.municipio = municipio;
	}
	
	public Integer getIdtorneo() {
		return idtorneo;
	}

	public void setIdtorneo(Integer idtorneo) {
		this.idtorneo = idtorneo;
	}
	
	public Integer getEntero() {
		return entero;
	}

	public void setEntero(Integer entero) {
		this.entero = entero;
	}
	
	
	//----------------------------------------------
	



	/**
	 * Setea la variable de User del valuestack
	 * @param us Usuario para el seteo
	 */
	public void SetearUsuario(User us){
		user.setId(us.getId());
		user.setAlias(us.getAlias());
		user.setPassword(us.getPassword());
		user.setNombre(us.getNombre());
		user.setApellido(us.getApellido());
		user.setHomepage(us.getHomepage());
		user.setLenguaje(us.getLenguaje());
		user.setEmail(us.getEmail());
		user.setSexo(us.getSexo());
		user.setNacimiento(us.getNacimiento());
		user.setFechaRegistro(us.getFechaRegistro());
		user.setInvitado(us.getInvitado());
		user.setExpulsado(us.getExpulsado());
		
		user.setParentPais(us.getParentPais());
		user.setParentMunicipio(us.getParentMunicipio());
		
		user.setUserComunidades(us.getUserComunidades());
		user.setUserTorneos(us.getUserTorneos());
		user.setUserEquipos(us.getUserEquipos());
		user.setUserActividades(us.getUserActividades());
		user.setUserPlataformas(us.getUserPlataformas());
		
		user.setPartidosVSGanados(us.getPartidosVSGanados());
		user.setPartidosVSLocal(us.getPartidosVSLocal());
		user.setPartidosVSPerdidos(us.getPartidosVSPerdidos());
		user.setPartidosVSVisitante(us.getPartidosVSVisitante());
	}
	
	/**
	 * Crear nuevo usuario
	 * @return SUCCESS en caso de éxito, INPUT en caso de no completarse, y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String merge()
	{  	
		boolean exito = false;
		boolean error = false;
		
		// Hacemos un Logout previo ;) (Por si acaso hay una sesion por ahi rulando)
		sesion = getSession();

		if(sesion !=null){
			sesion.remove("alias");
			sesion.remove("id");
		}
		// ---------------------------
		
		user.setAlias(user.getAlias().trim()); //trim a Alias
		user.setEmail(user.getEmail().trim()); //trim a Email
		user.setHomepage(user.getHomepage().trim()); //trim a Email
		
		if(user.getHomepage().equals("http://")){
			user.setHomepage(null);
		}
		
//		System.out.println("****^^^^^----------->>>>> "+user.getSexo()+" <<<<<<---------------^^^^^^^^*******");
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{							
				User usuario = (User) session.createQuery("from User us where us.alias=:apodo")
																.setString("apodo",user.getAlias())
																.uniqueResult();				
				if (usuario != null){
					Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
							new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
							"global/error/action/erroraction");	

					addActionError(propsError.getProperty("usuario.nombre.existente"));
				}
				else{
					usuario = (User) session.createQuery("from User us where us.email=:correo")
							.setString("correo",user.getEmail())
							.uniqueResult();
					
					if (usuario != null){
						Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
								new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
								"global/error/action/erroraction");	

						addActionError(propsError.getProperty("usuario.email.existente"));
					}
					else{
						if(pais != null && pais != 0){ // Si hay un pais elegido en el form
							Pais pa = (Pais)session.load(Pais.class,pais);
							user.setParentPais(pa);
						}
						else{
							user.setParentPais(null);
						}
						
						if(municipio != null && municipio !=0 ){
							Municipio mu = (Municipio)session.load(Municipio.class, municipio);
							user.setParentMunicipio(mu);
						}
						else{
							user.setParentMunicipio(null);
						}
						if(genero!=null){
							if(genero == 1){
								user.setSexo(true);
							}
							else{
								user.setSexo(false);
							}
						}
						else{
							user.setSexo(null);
						}
						
						user.setNombre(user.getNombre().trim());// trim a lo demás
						user.setApellido(user.getApellido().trim());
						
						// Cargamos el encriptador
						Properties props = PropertiesUtil.loadProperties("encriptado/encriptado.properties"); 
						StringEncrypter stringEncrypter = new StringEncrypter(props.getProperty("passPhrase"));
						
						user.setPassword(stringEncrypter.encrypt(user.getPassword().trim())); // Encriptamos la contraseña
						
						user.setInvitado(false); // NO ES UN INVITADO !!
						user.setSuperadministrador(false); // NO ES EL SUPERADMINISTRADOR !!
						
	//					System.out.println("el sexo del usuario es -> "+user.getSexo());					
						
						userDAO = new UserDAOImpl();
						
						usuario = userDAO.mergeUser(user); // Metemos al usuario en la BBDD
						try{									// Metemos sus "datos principales" en la sesion
							sesion.put("logged",true);
							sesion.put("alias",usuario.getAlias());
							
							sesion.put("id",usuario.getId());
							sesion.put("lenguaje", usuario.getLenguaje());
							sesion.put("equipo", 0);
						}
						catch(Exception e){
							e.printStackTrace();
							error=true;
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
	 * Crea un usuario invitado para la disputa de un torneo
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String CrearInvitado()
	{  	
		boolean exito = false;
		boolean error = false;
		
		System.out.println("--------------- Dentro de CrearInvitado()");
		System.out.println("--------------- Alias --> "+user.getAlias()+"     idtorneo --> "+idtorneo);
		
		user.setAlias("inv_"+user.getAlias().trim()); //trim a Alias
				
		try{
			
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{							
				User usuario = (User) session.createQuery("from User us where us.alias=?")
																.setString(0,user.getAlias())
																.uniqueResult();				
				if (usuario != null){
					Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
							new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
							"global/error/action/erroraction");	

					addActionError(propsError.getProperty("usuario.nombre.existente"));
				}
				else{
						user.setPassword(FuncionesAuxiliares.contra); // Sin encriptar, ponemos una cualquiera ya q no vamos a acceder
						// con esta cuenta de usuario
						
						user.setInvitado(true); // ES UN INVITADO !!
						user.setExpulsado(false);
						user.setSuperadministrador(false); // NO ES UN SUPERADMINISTRADOR !! 
						user.setLenguaje("es");
						
						userDAO = new UserDAOImpl();
						
						usuario = userDAO.mergeUser(user); // Metemos al usuario en la BBDD
						
						user.setId(usuario.getId());
						
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
	 * Cambia la contraseña del equipo
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String CambiarPassword(){
		boolean error=false;
		boolean exito=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				sesion = getSession();
				
				// Cargamos el encriptador
				Properties props = PropertiesUtil.loadProperties("encriptado/encriptado.properties"); 
				StringEncrypter stringEncrypter = new StringEncrypter(props.getProperty("passPhrase"));

				// Vemos si nuestra cuenta de usuario tiene esa contraseña
				User usuario = (User) session.createQuery("FROM User us WHERE us.id=? AND us.password=?")
														.setInteger(0,(Integer) sesion.get("id"))
														.setString(1,stringEncrypter.encrypt(pw.trim()))
														.uniqueResult();
				if(usuario!=null){
		
					usuario.setPassword(stringEncrypter.encrypt(user.getPassword().trim()));
					
					userDAO = new UserDAOImpl();
					userDAO.mergeUser(usuario); // Actualizamos al usuario en la BBDD
					
					exito=true;
				}
				else{
					Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
							new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
							"global/error/action/erroraction");	

					addActionError(propsError.getProperty("password.incorrecto"));
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
	 * Cambia las propiedades personales de la cuenta de usuario
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String CambiarPropiedades(){
		boolean error=false;
		boolean exito=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
//				if(pais != 0){ // Si hay un pais elegido en el form
//					Pais pa = (Pais)session.load(Pais.class,pais);
//					user.setParentPais(pa);
//				}
//				else{
//					user.setParentPais(null);
//				}
				
				sesion = getSession();
				
				User usuario = (User)session.load(User.class,(Serializable) sesion.get("id"));
				
				usuario.setNombre(user.getNombre().trim());
				usuario.setApellido(user.getApellido().trim());
				usuario.setEmail(user.getEmail().trim());
				usuario.setHomepage(user.getHomepage().trim());
				// No hay que hacer trim a nada mas
				
				if(pais !=null){
					usuario.setParentPais((Pais)session.load(Pais.class,pais));
				}
				else{
					usuario.setParentPais(null);
				}
				
				System.out.println("Dentro de CambiarPropiedades .... municipio --> "+municipio);
				
				if(municipio != null && municipio !=0 ){
					Municipio mu = (Municipio)session.load(Municipio.class, municipio);
					usuario.setParentMunicipio(mu);
				}
				else{
					usuario.setParentMunicipio(null);
				}				
				if(genero!=null){
					if(genero == 1){
						usuario.setSexo(true);
					}
					else{
						usuario.setSexo(false);
					}
				}
				else{
					usuario.setSexo(null);
				}
				
				usuario.setNacimiento(user.getNacimiento());
				
				userDAO = new UserDAOImpl();
				userDAO.mergeUser(usuario); // Actualizamos al usuario en la BBDD
				
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
	 * Cambia las propiedades sobre la web de la cuenta de usuario
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String CambiarPropiedadesWeb(){
		boolean error=false;
		boolean exito=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				sesion = getSession();
				
				User usuario = (User)session.load(User.class,(Serializable)sesion.get("id"));
				
				usuario.setLenguaje(user.getLenguaje());
				
				userDAO = new UserDAOImpl();
				userDAO.mergeUser(usuario); // Actualizamos al usuario en la BBDD
				
				sesion = getSession();

				sesion.remove("lenguaje");
				sesion.put("lenguaje", usuario.getLenguaje());
				
				user.setLenguaje(usuario.getLenguaje());
				
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
	 * Realiza solicitud de asociar los datos de un usuario invitado a los suyos
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
				User usuario = (User)session.load(User.class,(Serializable)sesion.get("id"));
				User invitado = (User)session.load(User.class,user.getId());
				
				Set<User> setInv = usuario.getInvitadosRequest();
				setInv.add(invitado);
				
				usuario.setInvitadosRequest(setInv);
				
				userDAO = new UserDAOImpl();
				userDAO.mergeUser(usuario);
				
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
	 * Acepta la solicitud de asociación al usuario invitado
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
				User usuario = (User)session.load(User.class,user.getId());
				User invitado = (User)session.load(User.class,entero);

				Set<PartidoIndividualVersus> setP = invitado.getPartidosVSGanados();
				
				partidoDAO = new PartidoDAOImpl();
				
				Iterator<PartidoIndividualVersus> it = setP.iterator();
				while(it.hasNext()){
					PartidoIndividualVersus pa = it.next();
					pa.setParentGanador(usuario);
					
					partidoDAO.mergePartidoIndividualVersus(pa);
				}
				
				invitado.setPartidosVSGanados(null);
				
				// Partidos Perdidos
				setP = invitado.getPartidosVSPerdidos();
				
				it = setP.iterator();
				while(it.hasNext()){
					PartidoIndividualVersus pa = it.next();
					pa.setParentPerdedor(usuario);
					
					partidoDAO.mergePartidoIndividualVersus(pa);
				}
				
				invitado.setPartidosVSPerdidos(null);
				
				// Partidos Local
				setP = invitado.getPartidosVSLocal();
				
				it = setP.iterator();
				while(it.hasNext()){
					PartidoIndividualVersus pa = it.next();
					pa.setParentLocal(usuario);
					
					partidoDAO.mergePartidoIndividualVersus(pa);
				}
				
				invitado.setPartidosVSLocal(null);
				
				// Partidos Visitante			
				setP = invitado.getPartidosVSVisitante();
				
				it = setP.iterator();
				while(it.hasNext()){
					PartidoIndividualVersus pa = it.next();
					pa.setParentVisitante(usuario);
					
					partidoDAO.mergePartidoIndividualVersus(pa);
				}
				
				invitado.setPartidosVSVisitante(null);
				
				// User Torneo
				Torneo tor = (Torneo)session.get(Torneo.class, idtorneo);
				
				UserTorneo userTorneo = (UserTorneo)session.get(UserTorneo.class,
						new UserTorneoPK(invitado,tor));
								
				
				UserTorneoPK utPK = new UserTorneoPK(usuario,tor);
				
				userTorneo.setId(utPK);
				
				userTorneoDAO = new UserTorneoDAOImpl();
				userTorneoDAO.mergeUserTorneo(userTorneo);
				
				// Borramos los requests al invitado de la tabla intermedia.
				BorrarSolicitudes(invitado);
				
//				userTorneo = (UserTorneo)session.get(UserTorneo.class,
//						new UserTorneoPK(invitado,tor));
				

				
//				userTorneoDAO.deleteUserTorneo(userTorneo);
				
//				userDAO = new UserDAOImpl();
//				userDAO.deleteUser(invitado);
				
//				session.delete(userTorneo);
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
	 * Deniega la solicitud de asociación al usuario invitado
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
				User usuario = (User)session.load(User.class,user.getId());
				User invitado = (User)session.load(User.class,entero);
												
				// Borramos el request del usuario al invitado de la tabla intermedia.
				BorrarSolicitud(usuario,invitado);				
				
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
	 * Borra las solicitudes de asociación sobre el usuario invitado
	 * @param invitado Usuario invitado
	 */
	public void BorrarSolicitudes(User invitado){
		invitado.setUsuariosRequest(null);
		
		userDAO = new UserDAOImpl();
		userDAO.mergeUser(invitado);
		
	}
	
	/**
	 * Borra la solicitud de asociacion del usuario sobre el usuario invitado
	 * @param solicitante Usuario solicitante
	 * @param invitado Usuario invitado
	 */
	public void BorrarSolicitud(User solicitante, User invitado){
		Set<User> setU = solicitante.getInvitadosRequest();		
		setU.remove(invitado);
		
		solicitante.setInvitadosRequest(setU);
				
		userDAO = new UserDAOImpl();
		userDAO.mergeUser(solicitante);
		
	}
	
	/**
	 * Nos lleva al registro de usuario
	 * @return SUCCESS
	 */
	public String GoRegister(){
		
		// Hacemos un Logout previo ;)
		sesion = getSession();

		if(sesion !=null){
			sesion.remove("alias");
			sesion.remove("id");
		}
		
		return SUCCESS;
	}	
	
	/**
	 * Nos lleva a la modificación de las propiedades personales de usuario
	 * @return SUCCESS
	 */
	public String GoModify(){
		RecuperarUsuario((Integer) sesion.get("id"));
		return SUCCESS;
	}

	/**
	 * Nos lleva a la modificación de las propiedades sobre la web del usuario
	 * @return SUCCESS
	 */
	public String GoModifyWeb(){
		RecuperarUsuario((Integer) sesion.get("id"));
		return SUCCESS;
	}
	
	/**
	 * Nos lleva al perfil del usuario
	 * @return SUCCESS
	 */
	public String GoPerfil(){
		RecuperarUsuario(user.getId());
		return SUCCESS;
	}
	
	/**
	 * Nos lleva al buscador de usuarios
	 * @return SUCCESS
	 */
	public String GoSearch(){
		return SUCCESS;
	}
	
	/**
	 * Buscador de usuarios
	 * @return SUCCESS
	 */
	public String BuscarJugador(){
		String where=" where invitado=false and superadministrador=false and expulsado=false";
		
		if(user.getAlias() != null && !user.getAlias().trim().equals("")){
			where = " and alias like '%"+user.getAlias()+"%'";
		}
		if(user.getNombre() != null && !user.getNombre().trim().equals("")){
//			where += FuncionesAuxiliares.WhereOrAnd(where);
			
			where += " and nombre like '%"+user.getNombre()+"%'";
		}
		if(user.getApellido() != null && !user.getApellido().trim().equals("")){
//			where += FuncionesAuxiliares.WhereOrAnd(where);
			
			where += " and apellido like '%"+user.getApellido()+"%'";
		}
		if(user.getEmail() != null && !user.getEmail().trim().equals("")){
//			where += FuncionesAuxiliares.WhereOrAnd(where);
			
			where += " and email like '%"+user.getEmail()+"%'";
		}
		
		//------------------ CondicionWhere ---------------------------
		if(genero!=null){
			if(genero == 1){ // Hombre
//				condicionWhere.put("sexo", "= 1");
				
//				where += FuncionesAuxiliares.WhereOrAnd(where);
				
				where += " and sexo=true";
			}
			else{ // Mujer
//				condicionWhere.put("sexo", "= 0");
				
//				where += FuncionesAuxiliares.WhereOrAnd(where);
				
				where += " and sexo=false";
			}		
		}
		if(municipio != null && municipio != 0){
//			where += FuncionesAuxiliares.WhereOrAnd(where);
			
			where += " and idmunicipio='"+String.valueOf(municipio)+"'";
		}
		else if(provincia != null && provincia != 0){
//			where += FuncionesAuxiliares.WhereOrAnd(where);
			
			where += " and idmunicipio IN (select id from Municipio where idprovincia='"+String.valueOf(provincia)+"')";
		}
			else if(region != null && region != 0){
//				where += FuncionesAuxiliares.WhereOrAnd(where);
				
				where += " and idmunicipio IN (select id from Municipio where idprovincia IN " +
						"(select id from Provincia where idregion='"+String.valueOf(region)+"'))";
			}
		
				else if(pais != null && pais != 0){
//					where += FuncionesAuxiliares.WhereOrAnd(where);
					
					where += " and idpais='"+String.valueOf(pais)+"'";
				}
		
		// Faltan las fechas	
		
//		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		
		userDAO = new UserDAOImpl();
//		userPaginatedList = userDAO.listUser(request,searchWord,condicionWhere,"alias");
		
		userList = userDAO.listUserQuery(where);
		
		return SUCCESS;
	}
	
	/**
	 * Cambia al perfil de usuario
	 * @return SUCCESS
	 */
	public String CambiarPerfilUsuario(){
		getSession().put("equipo",0);
		
		return SUCCESS;
	}
	
	/**
	 * Nos lleva a la zona de administración de todos los usuarios de la aplicación
	 * @return SUCCESS
	 */
	public String GoAdminUsuarios(){
		return BuscarJugador();
	}
	
	/**
	 * Mete al valuestack los valores para las estadísticas del usuario
	 * @return SUCCESS en caso de éxito, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String EstadisticasUsuario(){
		Integer noComenzados = 0;
		Integer finalizados = 0;
		Integer enJuego = 0;
		
		boolean error=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{	
				User usuario = (User)session.load(User.class,user.getId());
				
				SetearUsuario(usuario);
				
//				System.out.println("Sacando las estadisticas de "+usuario.getAlias());
				
				Integer tam = 0;
				Set<UserComunidad> setUC = usuario.getUserComunidades();
				Iterator<UserComunidad> itUC = setUC.iterator();
				
				while(itUC.hasNext()){
					if(SUCCESS == FuncionesAuxiliares.ComprobarPermisos(itUC.next().getRango(), 
													FuncionesAuxiliares.NORMALUSER)){
						tam++;
					}
				}
		
				listComunidades.add(new IntegerInteger(1,tam));
				
				Set<UserTorneo> setUT = usuario.getUserTorneos();
				Iterator<UserTorneo> it = setUT.iterator();
				
				while(it.hasNext()){
					UserTorneo ut = it.next();
					
					
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
				
				
				tam = 0;
				Set<UserEquipo> setUE = usuario.getUserEquipos();
				Iterator<UserEquipo> itUE = setUE.iterator();
				
				while(itUE.hasNext()){
					if(SUCCESS == FuncionesAuxiliares.ComprobarPermisos(itUE.next().getRango(), 
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

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("------ Prepare ---------");	
	}
	

	/**
	 * Recupera todos los datos del usuario
	 * @param iduser Id del usuario del que recuperar todos los datos
	 */
	public void RecuperarUsuario(Integer iduser){
		try{
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			try{				
				User us =(User)session.load(User.class,iduser);
				
				SetearUsuario(us);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void prepareGoModify() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("------ PrepareGoModify ------------");
		
		listHashMapUtil = new ListHashMapUtil();
//		listHashMapUtil.SacarPaises(sesion, " ", listaPaises);
		listHashMapUtil.SacarSexos(sesion, null, listaSexo);
		
//		preparacionModify();
	}
	
	public void prepareGoRegister() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("------ PrepareGoRegister ------------");
		
		listHashMapUtil = new ListHashMapUtil();
//		listHashMapUtil.SacarPaises(sesion, " ", listaPaises);
		listHashMapUtil.SacarSexos(sesion, null, listaSexo);
		listHashMapUtil.SacarLenguajes(sesion, listaLenguajes);
	}
	
	public void prepareMerge() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("------ PrepareMerge ------------");
		
		listHashMapUtil = new ListHashMapUtil();
//		listHashMapUtil.SacarPaises(sesion, " ", listaPaises);
		listHashMapUtil.SacarSexos(sesion, null, listaSexo);
		listHashMapUtil.SacarLenguajes(sesion, listaLenguajes);
	}
	public void prepareGoSearch() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("------ PrepareGoSearch ------------");
		
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarPaises(sesion, " ", listaPaises);
		listHashMapUtil.SacarSexos(sesion, "todos", listaSexo);
	}
	public void prepareBuscarJugador() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("------ PrepareBuscarJugador ------------");
		
		listHashMapUtil = new ListHashMapUtil();
		listHashMapUtil.SacarPaises(sesion, " ", listaPaises);
		listHashMapUtil.SacarSexos(sesion, "todos", listaSexo);
	}	
	
	public void prepareCambiarPropiedades() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("------ PrepareCambiarPropiedades ------------");
		
		listHashMapUtil = new ListHashMapUtil();
//		listHashMapUtil.SacarPaises(sesion, " ", listaPaises);
		listHashMapUtil.SacarSexos(sesion, null, listaSexo);
		
//		preparacionModify();
	}
	
	public void prepareGoModifyWeb() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("------ PrepareGoModifyWeb ------------");
		
		listHashMapUtil = new ListHashMapUtil();

		listHashMapUtil.SacarLenguajes(sesion, listaLenguajes);
		
//		preparacionModify();
	}	

	public void prepareCambiarPropiedadesWeb() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("------ PrepareCambiarPropiedadesWeb ------------");
		
		listHashMapUtil = new ListHashMapUtil();

		listHashMapUtil.SacarLenguajes(sesion, listaLenguajes);
	}	
	
	

	/**
	 * Eliminar usuario
	 * @return Devuelve SUCCESS en caso de éxito, INPUT en caso de completarse y ERROR en caso de fallo
	 */
	@SuppressWarnings("finally")
	public String delete(){
		boolean error=false;
		boolean exito=false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			
			try{
				User invitado = new User(user.getId());
				
				System.out.println("Invitado a borrar --> "+invitado.getAlias());

//				// GUARDAMOS
				userDAO = new UserDAOImpl();
				userDAO.deleteUser(invitado);
								
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
	 * Lista todos los usuarios
	 * @return SUCCESS
	 */
	public String UserList(){
//		userList = new ArrayList<User>();
//		userList = userDAO.listUser();
		
//		System.out.println("+++ -- Entramos en UserList del UserAction -- +++");
		
		userDAO = new UserDAOImpl();
		
//		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
		
//		userPaginatedList = new Paginate();
//		userPaginatedList = userDAO.listUser(request,null,null,null);
		
		userList = userDAO.listUser();
		
//		System.out.println("+++ -- Salimos con éxito del UserList del UserAction -- +++");
		
		return SUCCESS;
	}
		
	
//	public String UserListComunidad(){
//		userList = new ArrayList<User>();
//		userDAO = new UserDAOImpl();
//		userList = userDAO.listUserByIdComunidad(comunidad.getId());
//		
//		return SUCCESS;
//	}
	
	//Agregado al implementar SessionAware
	@Override
	public void setSession(Map s) {
	sesion = s;
	}
	
	public Map getSession() {

	return sesion;

	}
}

