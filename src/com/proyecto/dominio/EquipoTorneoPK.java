package com.proyecto.dominio;

import java.io.Serializable;

public class EquipoTorneoPK implements Serializable{
	private static final long serialVersionUID = 1L;
	private Equipo parentEquipo;
	private Torneo parentTorneo;
	
	// Constructor Público
	public EquipoTorneoPK(){}
	
	public EquipoTorneoPK(Equipo parentE,Torneo parentT){
		parentEquipo= parentE;
		parentTorneo= parentT;
	}
	// Getters y Setters

	public Equipo getParentEquipo() {
		return parentEquipo;
	}

	public void setParentEquipo(Equipo parentEquipo) {
		this.parentEquipo = parentEquipo;
	}

	public Torneo getParentTorneo() {
		return parentTorneo;
	}

	public void setParentTorneo(Torneo parentTorneo) {
		this.parentTorneo = parentTorneo;
	}
	
	public boolean equals(Object key) {
		   boolean result = true;
		   
		   if (!(key instanceof EquipoTorneoPK)) {
			   return false;
		   }
		   
		   Equipo otherParentEquipo = ((EquipoComunidadPK)key).getParentEquipo();
		   Torneo otherParentTorneo = ((EquipoTorneoPK)key).getParentTorneo();
		   
		   if (parentEquipo == null || otherParentEquipo == null) {
		      result = false;
		   }
		   else 
		   {
		      result = parentEquipo.equals(otherParentEquipo);
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
		    
		    if (parentEquipo!=null) {code +=parentEquipo.getId();}
		    if (parentTorneo!=null) {code +=parentTorneo.getId();}
		    
		    return code;
		  }
}
