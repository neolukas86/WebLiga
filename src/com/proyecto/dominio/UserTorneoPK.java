package com.proyecto.dominio;

import java.io.Serializable;

public class UserTorneoPK implements Serializable{
	private static final long serialVersionUID = 1L;
	private User parentUser;
	private Torneo parentTorneo;
	
	// Constructor Público
	public UserTorneoPK(){}
	
	public UserTorneoPK(User parentU,Torneo parentT){
		parentUser= parentU;
		parentTorneo= parentT;
	}
	// Getters y Setters

	public User getParentUser() {
		return parentUser;
	}

	public void setParentUser(User parentUser) {
		this.parentUser = parentUser;
	}

	public Torneo getParentTorneo() {
		return parentTorneo;
	}

	public void setParentTorneo(Torneo parentTorneo) {
		this.parentTorneo = parentTorneo;
	}
	
	public boolean equals(Object key) {
		   boolean result = true;
		   
		   if (!(key instanceof UserTorneoPK)) {
			   return false;
		   }
		   
		   User otherParentUser = ((UserComunidadPK)key).getParentUser();
		   Torneo otherParentTorneo = ((UserTorneoPK)key).getParentTorneo();
		   
		   if (parentUser == null || otherParentUser == null) {
		      result = false;
		   }
		   else 
		   {
		      result = parentUser.equals(otherParentUser);
		   }
		   if (parentTorneo == null || otherParentTorneo == null) 
		   {
		      result = false;
		   }
		   else 
		   {
			   result = parentTorneo.equals(otherParentTorneo);
		   }
		   return result;
	}

		  public int hashCode() {
		    int code = 0;
		    
		    if (parentUser!=null) {code +=parentUser.getId();}
		    if (parentTorneo!=null) {code +=parentTorneo.getId();}
		    
		    return code;
		  }
}
