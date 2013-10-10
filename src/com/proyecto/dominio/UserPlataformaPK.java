package com.proyecto.dominio;

import java.io.Serializable;

public class UserPlataformaPK implements Serializable {
	private static final long serialVersionUID = 1L;
	private User parentUser;
	private Plataforma parentPlataforma;
	
	// Constructor Público
	public UserPlataformaPK(){
	}

	// Métodos getter y setter
	public User getParentUser() {
		return parentUser;
	}

	public void setParentUser(User parentUser) {
		this.parentUser = parentUser;
	}

	public Plataforma getParentPlataforma() {
		return parentPlataforma;
	}

	public void setParentPlataforma(Plataforma parentPlataforma) {
		this.parentPlataforma = parentPlataforma;
	}

	public boolean equals(Object key) {
		   boolean result = true;
		   
		   if (!(key instanceof UserPlataformaPK)) {
			   return false;
		   }
		   
		   User otherParentUser = ((UserPlataformaPK)key).getParentUser();
		   Plataforma otherParentPlataforma = ((UserPlataformaPK)key).getParentPlataforma();
		   
		   if (parentUser == null || otherParentUser == null) {
		      result = false;
		   }
		   else 
		   {
		      result = parentUser.equals(otherParentUser);
		   }
		   if (parentPlataforma == null || otherParentPlataforma == null) 
		   {
		      result = false;
		   }
		   else 
		   {
			   result = parentPlataforma.equals(otherParentPlataforma);
		   }
		   return result;
	}

	public int hashCode() {
		   int code = 0;
		    
		   if (parentUser!=null) {code +=parentUser.getId();}
		   if (parentPlataforma!=null) {code +=parentPlataforma.getId();}
		    
		   return code;
	}
	
}

