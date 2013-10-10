package com.proyecto.actions;

import java.util.Map;
import java.util.Properties;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import com.proyecto.dominio.User;
import com.proyecto.util.encriptado.StringEncrypter;
import com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares;
import com.proyecto.util.hibernate.HibernateUtil;
import com.proyecto.util.properties.GlobalResourceUtil;
import com.proyecto.util.properties.PropertiesUtil;

/**
* Esta clase implementa el Action asociado al
* inicio de sesión.
* @author Lucas Sánchez López
* @version 3.0
*/

public class LoginAction extends ActionSupport implements ModelDriven<User>, SessionAware{
	private static final long serialVersionUID = 1L;
	private User user = new User();
	private Map sesion;

	SessionFactory sessionFactory = null;
	
	@SessionTarget
	Session session = null;
	
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	public User getUser(){
		return user;
	}
	public void setUser(User user){
		this.user = user;
	}
	
	
	/**
	 * Método execute del LoginAction, que se encargará de realizar
	 * el inicio de sesión
	 * @return SUCCESS en caso de éxito, INPUT en caso de no completarse, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String execute(){
		boolean error = false;
		boolean exito = false;
		boolean superadmin = false;
		
		try{
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			try{
				User usuario = (User)session.createQuery("from User us where us.alias= :username")
																.setString("username", user.getAlias().trim())
																.uniqueResult();// Quitamos los espacios en blanco de inicio y fin
				
				if(usuario != null){
						// Si existe el usuario, cargamos el encriptador
						Properties props = PropertiesUtil.loadProperties("encriptado/encriptado.properties"); 
						StringEncrypter stringEncrypter = new StringEncrypter(props.getProperty("passPhrase"));

						
						if(user.getPassword().trim().equals(stringEncrypter.decrypt(usuario.getPassword()))){
							// Si es correcta la contraseña	
		//						SessionMap mapSession = (SessionMap) ActionContext.getContext().getSession();
		//						mapSession.put("logged-in","true");
		//						mapSession.put("alias",usuario.getAlias());
		//						
		//						mapSession.put("id",usuario.getId());
								getSession().put("alias",usuario.getAlias());
								getSession().put("id",usuario.getId());
								getSession().put("logged", true);
								getSession().put("equipo", 0);
//								getSession().put("lenguaje", usuario.getLenguaje());
								
								user.setLenguaje(usuario.getLenguaje());
								
								exito = true;
								if(usuario.getSuperadministrador()){
									superadmin = true;
									getSession().put("superadministrador", true);
								}
								else{
									getSession().put("superadministrador", false);
								}
						}
				}
				
				else{ // Login incorrecto
					Properties propsError =FuncionesAuxiliares.ArchivoPropiedades(
							new GlobalResourceUtil().loadResourceBundle(sesion).getLocale(),
							"global/error/action/erroraction");
					
					addActionError(propsError.getProperty("login.incorrecto"));
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
					if(superadmin){
						return "superadministrador";
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
	
	
	//Agregado al implementar SessionAware
	@Override
	public void setSession(Map s) {
	sesion = s;
	}
	
	public Map getSession() {

	return sesion;

	}
	
}
