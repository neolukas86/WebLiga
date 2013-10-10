package com.proyecto.dao;

import java.util.List;

import com.proyecto.dominio.Regla;

/**
* Interfaz DAO para las reglas
* @author Lucas S�nchez L�pez
* @version 2.0
*/
public interface ReglaDAO {
	public List<Regla> listRegla();
	public List<Regla> listReglaConPuntos();
	public List<Regla> listReglaSinPuntos();
}
