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
* cierre de sesi�n.
* @author Lucas S�nchez L�pez
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
	 * M�todo execute del LogoutAction, que se encargar� de realizar
	 * el cierre de sesi�n
	 * @return SUCCESS en caso de �xito, y ERROR si ocurre alg�n fallo
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
