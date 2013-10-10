package com.proyecto.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;


import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.Transport;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.opensymphony.xwork2.ActionSupport;
import com.proyecto.dao.ActividadDAO;
import com.proyecto.dao.ActividadDAOImpl;
import com.proyecto.dao.PlataformaDAO;
import com.proyecto.dao.PlataformaDAOImpl;
import com.proyecto.dominio.Actividad;
import com.proyecto.dominio.IntegerInteger;
import com.proyecto.dominio.Plataforma;
import com.proyecto.dominio.TipoActividad;
import com.proyecto.dominio.Torneo;
import com.proyecto.dominio.User;
import com.proyecto.util.encriptado.StringEncrypter;
import com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares;
import com.proyecto.util.hibernate.HibernateUtil;
import com.proyecto.util.listas.ListHashMapUtil;
import com.proyecto.util.properties.GlobalResourceUtil;
import com.proyecto.util.properties.PropertiesUtil;;

/**
* Esta clase implementa el Action asociado a 
* distintas funciones de la web.
* @author Lucas Sánchez López
* @version 3.0
*/

public class UtilAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String email;
	private String alias;
	private String noticia;
	private String nombre;
	private List<Actividad> listaDeportes = new ArrayList<Actividad>();
	private List<Actividad> listaCartas= new ArrayList<Actividad>();
	private List<Actividad> listaJuegos= new ArrayList<Actividad>();
	private List<Actividad> listaJuegosdemesa= new ArrayList<Actividad>();
	private List<Plataforma> listaPlataformas = new ArrayList<Plataforma>();
	private List<IntegerInteger> listTorneosNoComenzados = new ArrayList<IntegerInteger>();
	private List<IntegerInteger> listTorneosEnJuego = new ArrayList<IntegerInteger>();
	private List<IntegerInteger> listTorneosFinalizados = new ArrayList<IntegerInteger>();
	private List<IntegerInteger> listComunidades = new ArrayList<IntegerInteger>();
//	private List<IntegerInteger> listUsuarios = new ArrayList<IntegerInteger>();
	private List<IntegerInteger> listEquipos = new ArrayList<IntegerInteger>();
	private ActividadDAO actividadDAO; 
	private PlataformaDAO plataformaDAO;
	private Map sesion;

	
	SessionFactory sessionFactory = null;
	
	@SessionTarget
	Session session = null;
	
	// Métodos getters y setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getNoticia() {
		return noticia;
	}

	public void setNoticia(String noticia) {
		this.noticia = noticia;
	}
	
	public List<Actividad> getListaDeportes() {
		return listaDeportes;
	}

	public void setListaDeportes(List<Actividad> listaDeportes) {
		this.listaDeportes = listaDeportes;
	}

	public List<Actividad> getListaCartas() {
		return listaCartas;
	}

	public void setListaCartas(List<Actividad> listaCartas) {
		this.listaCartas = listaCartas;
	}

	public List<Actividad> getListaJuegos() {
		return listaJuegos;
	}

	public void setListaJuegos(List<Actividad> listaJuegos) {
		this.listaJuegos = listaJuegos;
	}

	public List<Actividad> getListaJuegosdemesa() {
		return listaJuegosdemesa;
	}

	public void setListaJuegosdemesa(List<Actividad> listaJuegosdemesa) {
		this.listaJuegosdemesa = listaJuegosdemesa;
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

	public List<IntegerInteger> getListComunidades() {
		return listComunidades;
	}

	public void setListComunidades(List<IntegerInteger> listComunidades) {
		this.listComunidades = listComunidades;
	}

	
//	public List<IntegerInteger> getListUsuarios() {
//		return listUsuarios;
//	}
//
//	public void setListUsuarios(List<IntegerInteger> listUsuarios) {
//		this.listUsuarios = listUsuarios;
//	}

	public List<Plataforma> getListaPlataformas() {
		return listaPlataformas;
	}

	public void setListaPlataformas(List<Plataforma> listaPlataformas) {
		this.listaPlataformas = listaPlataformas;
	}

	public List<IntegerInteger> getListEquipos() {
		return listEquipos;
	}

	public void setListEquipos(List<IntegerInteger> listEquipos) {
		this.listEquipos = listEquipos;
	}
	
	/////////////////////////////
	


	/**
	 * Método execute del UtilAction
	 * @return SUCCESS
	 */
	public String execute(){		
		return SUCCESS;
	}
	
	/**
	 * Nos lleva al index, o al index de la zona de administración web
	 * dependiendo de nuestro rol
	 * @return SUCCESS en caso de éxito, "superadministrador" en caso de tener
	 * rango de superadministrador
	 */
	public String Volver(){
		if(getSession().containsKey("superadministrador")){
			Boolean bool = (Boolean) getSession().get("superadministrador");
			
			if(bool){
				return "superadministrador";
			}
			else{
				return SUCCESS;
			}
		}
		
		return SUCCESS;
	}
	
	/**
	 * Internacionaliza, cambia el lenguaje con el que se muestra la aplicación
	 * @return SUCCESS en caso de éxito, "superadministrador" en caso de éxito
	 * y que tengamos rango de superadministrador, y "login" si se produce un error
	 */
	public String Internacionalizar(){
		if(getSession().containsKey("logged")){
			Boolean bool = (Boolean) getSession().get("logged");
			
			if(bool!=null && bool){
				if(getSession().containsKey("superadministrador")){
					bool = (Boolean) getSession().get("superadministrador");
					
					if(bool){
						System.out.println("----------- Retorna Éxito(Superadministrador) --------------");
						return "superadministrador";
					}
					else{
						System.out.println("----------- Retorna Éxito --------------");
						return SUCCESS;
					}
				}				
				System.out.println("----------- Retorna Éxito --------------");
				return SUCCESS;
			}
			else{
				System.out.println("----------- Retorna Login --------------");
				return "login";
			}
		}
		else{
			System.out.println("----------- Retorna Login --------------");
			return "login";
		}
	}
	
	
	/**
	 * Envía al correo electrónico del usuario los datos de su cuenta
	 * @return SUCCESS en caso de éxito, INPUT si no se completa, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String RecuperarDatosEmail(){
		boolean exito = false;
		boolean error = false;
	
		email = email.trim();
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{							
				User usuario = (User) session.createQuery("from User us where us.email=?")
																.setString(0,email)
																.uniqueResult();			
				if (usuario == null){
					Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
							new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
							"global/error/action/erroraction");	

					addActionError(propsError.getProperty("email.usuario.no.coincidentes"));
				}
				else{
					
					Properties propsEmail = PropertiesUtil.loadProperties("email/email.properties");
					final Properties propsEmailConfig = PropertiesUtil.loadProperties("email/email_config.properties");
					Properties propsEncriptado = PropertiesUtil.loadProperties("encriptado/encriptado.properties");
					
//					System.out.println("Properties cargadas!");
					// Los properties del email son por TLS ;)
					
					javax.mail.Session sess = javax.mail.Session.getInstance(propsEmail,
							new javax.mail.Authenticator() {
								protected PasswordAuthentication getPasswordAuthentication() {
									return new PasswordAuthentication(propsEmailConfig.getProperty("email.remitente"),
																		propsEmailConfig.getProperty("email.password"));
								}
					});
					
//					System.out.println("MailSesssion iniciada!");
					
					MimeMessage message = new MimeMessage(sess);
					
					// Quien envía el mensaje
					message.setFrom(new InternetAddress(propsEmailConfig.getProperty("email.remitente")));
					
					// A quien va dirigido
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
					
					// Asunto
					message.setSubject(propsEmailConfig.getProperty("email.asunto"));
					
					// Cargamos el encriptador
					StringEncrypter stringEncrypter = new StringEncrypter(propsEncriptado.getProperty("passPhrase"));
					
					// Mensaje
					message.setText(propsEmailConfig.getProperty("email.texto.cuerpo")+
								propsEmailConfig.getProperty("email.texto.usuario")+usuario.getAlias()+
								propsEmailConfig.getProperty("email.texto.password")+stringEncrypter.decrypt(usuario.getPassword())+
								propsEmailConfig.getProperty("email.texto.footer"));
					
//					System.out.println("Construido el mensaje!");
					
					// Enviamos el mensaje
					Transport.send(message);
					
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
	 * Nos lleva a la zona de administración de todas las actividades de la aplicación
	 * @return SUCCESS
	 */
	public String GoAdminActividades(){
		actividadDAO = new ActividadDAOImpl();

		List<TipoActividad> listTipo = actividadDAO.listTipoActividad();
		
		Iterator<TipoActividad> it = listTipo.iterator();
		while(it.hasNext()){
			TipoActividad tipo = it.next();
			
			if(tipo.getNombre().equals("Deporte")){
				listaDeportes = actividadDAO.listActividadByTipo(tipo);
			}
			else if(tipo.getNombre().equals("Cartas")){
				listaCartas = actividadDAO.listActividadByTipo(tipo);
			}
			else if(tipo.getNombre().equals("Juego de mesa")){
				listaJuegosdemesa = actividadDAO.listActividadByTipo(tipo);
			}
			else if(tipo.getNombre().equals("Videojuego")){
				listaJuegos = actividadDAO.listActividadByTipo(tipo);
			}
		}
		return SUCCESS;
	}
	
	/**
	 * Mete al valuestack los valores para las estadísticas de la actividad
	 * @return SUCCESS en caso de éxito, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String EstadisticasActividad(){
		Integer noComenzados = 0;
		Integer finalizados = 0;
		Integer enJuego = 0;
		boolean error = false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				Actividad acti = (Actividad)session.load(Actividad.class, id);
				
				listComunidades.add(new IntegerInteger(1, acti.getComunidades().size()));
				
				Set<Torneo> setTor = acti.getTorneos();
				
				Iterator<Torneo> it = setTor.iterator();
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
				
				
				listTorneosNoComenzados.add(new IntegerInteger(2, noComenzados));
				listTorneosEnJuego.add(new IntegerInteger(2, enJuego));
				listTorneosFinalizados.add(new IntegerInteger(2, finalizados));
				
//				listUsuarios.add(new IntegerInteger(3, acti.getUserActividades().size()));
				listEquipos.add(new IntegerInteger(3, acti.getEquipos().size()));
				
				if(acti.getNombre_EN() == null && acti.getNombre_CA() == null){
					nombre = acti.getNombre();
				}
				else{
					ResourceBundle rb = new GlobalResourceUtil().loadResourceBundle(sesion);
					Locale locale = rb.getLocale();
		
					if(locale == null || locale.getLanguage().equals("es")){
						nombre = acti.getNombre();
					}
					else if(locale.getLanguage().equals("en")){
						if(acti.getNombre_EN() != null){
							nombre = acti.getNombre_EN();
						}
						else{
							nombre = acti.getNombre();
						}						
					}
					else if(locale.getLanguage().equals("ca")){
						if(acti.getNombre_CA() != null){
							nombre = acti.getNombre_CA();
						}
						else{
							nombre = acti.getNombre();
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
	 * Nos lleva a la zona de administración de todas las plataformas de la aplicación
	 * @return SUCCESS
	 */
	public String GoAdminPlataformas(){
		plataformaDAO = new PlataformaDAOImpl();

		listaPlataformas = plataformaDAO.listPlataforma();
		
		return SUCCESS;
	}
	
	/**
	 * Mete al valuestack los valores para las estadísticas de la plataforma
	 * @return SUCCESS en caso de éxito, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String EstadisticasPlataforma(){
		Integer noComenzados = 0;
		Integer finalizados = 0;
		Integer enJuego = 0;
		boolean error = false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				Plataforma pla = (Plataforma)session.load(Plataforma.class, id);
				
				listComunidades.add(new IntegerInteger(1, pla.getComunidades().size()));
				
				Set<Torneo> setTor = pla.getTorneos();
				
				Iterator<Torneo> it = setTor.iterator();
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
				
				
				listTorneosNoComenzados.add(new IntegerInteger(2, noComenzados));
				listTorneosEnJuego.add(new IntegerInteger(2, enJuego));
				listTorneosFinalizados.add(new IntegerInteger(2, finalizados));
				
//				listUsuarios.add(new IntegerInteger(3, acti.getUserActividades().size()));
				listEquipos.add(new IntegerInteger(3, pla.getEquipos().size()));
				
				nombre = pla.getNombre();
				
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

	//Agregado al implementar SessionAware
	@Override
	public void setSession(Map s) {
	sesion = s;
	}
	
	public Map getSession() {

	return sesion;

	}	
}
