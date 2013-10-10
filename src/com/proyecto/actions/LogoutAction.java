package com.proyecto.actions;

//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//
//import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.proyecto.dominio.User;
//import com.proyecto.util.HibernateUtil;

/**
* Esta clase implementa el Action asociado al
* cierre de sesión.
* @author Lucas Sánchez López
* @version 3.0
*/

public class LogoutAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private User user = new User();
//	SessionFactory sessionFactory = null;
//	
//	@SessionTarget
//	Session session = null;
	
	/**
	 * Método execute del LogoutAction, que se encargará de realizar
	 * el cierre de sesión
	 * @return SUCCESS en caso de éxito, y ERROR si ocurre algún fallo
	 */
	public String execute(){
		try{
//			java.util.Map<String, Object> mapSession = ActionContext.getContext().getSession();
			SessionMap mapSession = (SessionMap) ActionContext.getContext().getSession();
//			mapSession.remove("logged-in");
			mapSession.remove("alias");
			mapSession.remove("id");
			mapSession.remove("logged");
//			mapSession.invalidate();
			
			return SUCCESS;
		}
		catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public User getUser(){
		return user;
	}
	public void setUser(User user){
		this.user = user;
	}
}
