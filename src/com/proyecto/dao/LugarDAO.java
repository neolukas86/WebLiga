package com.proyecto.dao;

import java.util.List;

//import com.proyecto.dominio.Continente;
import com.proyecto.dominio.Pais;
import com.proyecto.dominio.Region;
import com.proyecto.dominio.Provincia;
import com.proyecto.dominio.Municipio;

/**
* Interfaz DAO para los paises, regiones,
* provincias y municipios
* @author Lucas Sánchez López
* @version 2.0
*/
public interface LugarDAO {
//	public Continente mergeContinente(Continente continente);
//	public List<Continente> listContinente();
//	public void deleteContinente(Continente continente);
	
//	public Pais mergePais(Pais pais);
	public List<Pais> listPais();
//	public void deletePais(Pais pais);

//	public Region mergeRegion(Region region);
	public List<Region> listRegion();
//	public void deleteRegion(Region region);

//	public Provincia mergeProvincia(Provincia provincia);
	public List<Provincia> listProvincia();
//	public void deleteProvincia(Provincia provincia);

//	public Municipio mergeMunicipio(Municipio municipio);
	public List<Municipio> listMunicipio();
//	public void deleteMunicipio(Municipio municipio);

}
