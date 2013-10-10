package com.proyecto.dominio;

import java.io.Serializable;

public class UserEquipoPK implements Serializable{
	private static final long serialVersionUID = 1L;
	private User parentUser;
	private Equipo parentEquipo;
	
	// Constructor Público
	public UserEquipoPK(){}
	
	public UserEquipoPK(User parentU,Equipo parentE){
		parentUser= parentU;
		parentEquipo= parentE;
	}
	// Getters y Setters

	public User getParentUser() {
		return parentUser;
	}

	public void setParentUser(User parentUser) {
		this.parentUser = parentUser;
	}

	public Equipo getParentEquipo() {
		return parentEquipo;
	}

	public void setParentEquipo(Equipo parentEquipo) {
		this.parentEquipo = parentEquipo;
	}
	
	public boolean equals(Object key) {
		   boolean result = true;
		   
		   if (!(key instanceof UserEquipoPK)) {
			   return false;
		   }
		   
		   User otherParentUser = ((UserEquipoPK)key).getParentUser();
		   Equipo otherParentEquipo = ((UserEquipoPK)key).getParentEquipo();
		   
		   if (parentUser == null || otherParentUser == null) {
		      result = false;
		   }
		   else 
		   {
		      result = parentUser.equals(otherParentUser);
		   }
		   if (parentEquipo == null || otherParentEquipo == null) 
		   {
		      result = false;
		   }
		   else 
		   {
			   result = parentEquipo.equals(otherParentEquipo);
		   }
		   return result;
	}

	public int hashCode() {
		   int code = 0;
		    
		   if (parentUser!=null) {code +=parentUser.getId();}
		   if (parentEquipo!=null) {code +=parentEquipo.getId();}
		    
		   return code;
	}
}
