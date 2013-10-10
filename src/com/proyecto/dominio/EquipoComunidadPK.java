package com.proyecto.dominio;

import java.io.Serializable;

public class EquipoComunidadPK implements Serializable{
	private static final long serialVersionUID = 1L;
	private Equipo parentEquipo;
	private Comunidad parentComunidad;
	
	// Constructor Público
	public EquipoComunidadPK(){}
	
	public EquipoComunidadPK(Equipo parentE,Comunidad parentC){
		parentEquipo= parentE;
		parentComunidad= parentC;
	}
	// Getters y Setters

	public Equipo getParentEquipo() {
		return parentEquipo;
	}

	public void setParentEquipo(Equipo parentEquipo) {
		this.parentEquipo = parentEquipo;
	}

	public Comunidad getParentComunidad() {
		return parentComunidad;
	}

	public void setParentComunidad(Comunidad parentComunidad) {
		this.parentComunidad = parentComunidad;
	}
	
	public boolean equals(Object key) {
		   boolean result = true;
		   
		   if (!(key instanceof EquipoComunidadPK)) {
			   return false;
		   }
		   
		   Equipo otherParentEquipo = ((EquipoComunidadPK)key).getParentEquipo();
		   Comunidad otherParentComunidad = ((EquipoComunidadPK)key).getParentComunidad();
		   
		   if (parentEquipo == null || otherParentEquipo == null) {
		      result = false;
		   }
		   else 
		   {
		      result = parentEquipo.equals(otherParentEquipo);
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
		    
		   if (parentEquipo!=null) {code +=parentEquipo.getId();}
		   if (parentComunidad!=null) {code +=parentComunidad.getId();}
		    
		   return code;
	}
}


