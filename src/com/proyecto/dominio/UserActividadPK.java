package com.proyecto.dominio;

import java.io.Serializable;

public class UserActividadPK implements Serializable {
	private static final long serialVersionUID = 1L;
	private User parentUser;
	private Actividad parentActividad;
	
	// Constructor Público
	public UserActividadPK(){
	}

	// Métodos getter y setter
	public User getParentUser() {
		return parentUser;
	}

	public void setParentUser(User parentUser) {
		this.parentUser = parentUser;
	}

	public Actividad getParentActividad() {
		return parentActividad;
	}

	public void setParentActividad(Actividad parentActividad) {
		this.parentActividad = parentActividad;
	}
	
	
	public boolean equals(Object key) {
		   boolean result = true;
		   
		   if (!(key instanceof UserActividadPK)) {
			   return false;
		   }
		   
		   User otherParentUser = ((UserActividadPK)key).getParentUser();
		   Actividad otherParentActividad = ((UserActividadPK)key).getParentActividad();
		   
		   if (parentUser == null || otherParentUser == null) {
		      result = false;
		   }
		   else 
		   {
		      result = parentUser.equals(otherParentUser);
		   }
		   if (parentActividad == null || otherParentActividad == null) 
		   {
		      result = false;
		   }
		   else 
		   {
			   result = parentActividad.equals(otherParentActividad);
		   }
		   return result;
	}

	public int hashCode() {
		   int code = 0;
		    
		   if (parentUser!=null) {code +=parentUser.getId();}
		   if (parentActividad!=null) {code +=parentActividad.getId();}
		    
		   return code;
	}	

}
