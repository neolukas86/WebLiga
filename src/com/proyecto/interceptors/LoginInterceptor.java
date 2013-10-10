package com.proyecto.interceptors;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpSession;  
  
import org.apache.commons.lang3.StringUtils; 
import org.apache.commons.logging.Log;  
import org.apache.commons.logging.LogFactory;  
import org.apache.struts2.StrutsStatics;  
  
import com.opensymphony.xwork2.ActionContext;  
import com.opensymphony.xwork2.ActionInvocation;  
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;  
  
/**
* Esta clase es el interceptor del inicio de sesión
* @author Lucas Sánchez López
* @version 2.0
*/
public class LoginInterceptor extends AbstractInterceptor implements  StrutsStatics {  
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginInterceptor.class);  
	private static final String USER_HANDLE = "alias";  // Notese que lo he cambiado por alias por comodidad
	private static final String LOGIN_ATTEMPT = "loginAttempt"; // Lo he puesto hidden en el form del login 
	private static final String REGISTER_ATTEMPT = "registerAttempt";
	private static final String RECOVER_ATTEMPT = "recoverAttempt";
	private static final String INTER_ATTEMPT = "internacionalizarAttempt";
	
  
	public void init() {  
		log.info("Initializing LoginInterceptor");  
	}  
	  
	public void destroy() {  
	}  
	
	/**
	 * Función principal del interceptor
	 * si la acción a ejecutar es permitida se invoca,
	 * si no lo es se retorna "login" y se actúa en consecuencia
	 */
	public String intercept(ActionInvocation invocation) throws Exception {  
		final ActionContext context = invocation.getInvocationContext(); 
		
		HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);  
		HttpSession session = request.getSession(true);  
	  
		// Is there a "user" object stored in the user's HttpSession?  
		Object user = session.getAttribute(USER_HANDLE);  
		if (user == null) {  
			// The user has not logged in yet.
//			System.out.println("********* -- Dentro del interceptor, en user == null -- *********");
	  
			// Is the user attempting to log in right now?  
			String loginAttempt = request.getParameter(LOGIN_ATTEMPT);
			
			// Estás intentando entrar al registro o registrando un usuario?
			String registerAttempt = request.getParameter(REGISTER_ATTEMPT);
			
			// Estás intentando entrar al recordatorio de usuario o recordando un usuario?
			String recoverAttempt = request.getParameter(RECOVER_ATTEMPT);
			
			// Estás intentando internacionalizar.
			String internacionalizarAttempt = request.getParameter(INTER_ATTEMPT);
	  
			/* The user is attempting to log in. */  
			if (!StringUtils.isBlank(loginAttempt)) {  
				return invocation.invoke();  
			}
			// El usuario está intentado registrarse o entrar al registro
			if (!StringUtils.isBlank(registerAttempt)) {  
				return invocation.invoke();  
			}
			// El usuario está intentado recordar su usuario o entrar al recordatorio
			if (!StringUtils.isBlank(recoverAttempt)) {  
				return invocation.invoke();  
			}	
			// El usuario está intentado internacionalizar la web
			if (!StringUtils.isBlank(internacionalizarAttempt)) {  
				return invocation.invoke();  
			}			
		
			System.out.println("--------- Action Prohibido ! -------------");
			return "login";  
		} 
		else {
//			System.out.println("********* -- Dentro del interceptor, en user !=null -- *********");
			// Habría que ver si conviene prohibir aqui el registro y recordatorio!!
			return invocation.invoke();  
		}  
	}  
  
}  
