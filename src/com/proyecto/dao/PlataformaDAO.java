package com.proyecto.dao;

import java.util.List;

import com.proyecto.dominio.Plataforma;

/**
* Interfaz DAO para las plataformas
* @author Lucas S�nchez L�pez
* @version 2.0
*/
public interface PlataformaDAO {
//	public Plataforma mergePlataforma(Plataforma plataforma);
	public List<Plataforma> listPlataforma();
	public void deletePlataforma(Plataforma plataforma);
}
