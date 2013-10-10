package com.proyecto.dominio;

import java.io.Serializable;

public class UserComunidadPK implements Serializable{
	private static final long serialVersionUID = 1L;
	private User parentUser;
	private Comunidad parentComunidad;
	
	// Constructor Público
	public UserComunidadPK(){}
	
	public UserComunidadPK(User parentU,Comunidad parentC){
		parentUser= parentU;
		parentComunidad= parentC;
	}
	// Getters y Setters

	public User getParentUser() {
		return parentUser;
	}

	public void setParentUser(User parentUser) {
		this.parentUser = parentUser;
	}

	public Comunidad getParentComunidad() {
		return parentComunidad;
	}

	public void setParentComunidad(Comunidad parentComunidad) {
		this.parentComunidad = parentComunidad;
	}
	
	public boolean equals(Object key) {
		   boolean result = true;
		   
		   if (!(key instanceof UserComunidadPK)) {
			   return false;
		   }
		   
		   User otherParentUser = ((UserComunidadPK)key).getParentUser();
		   Comunidad otherParentComunidad = ((UserComunidadPK)key).getParentComunidad();
		   
		   if (parentUser == null || otherParentUser == null) {
		      result = false;
		   }
		   else 
		   {
		      result = parentUser.equals(otherParentUser);
		   }
		   if (parentComunidad == null || otherParentComunidad == null) 
		   {
		      result = false;
		   }
		   else 
		   {
			   result = parentComunidad.equals(otherParentComunidad);
		   }
		   return result;
	}

	public int hashCode() {
		   int code = 0;
		    
		   if (parentUser!=null) {code +=parentUser.getId();}
		   if (parentComunidad!=null) {code +=parentComunidad.getId();}
		    
		   return code;
	}
}


