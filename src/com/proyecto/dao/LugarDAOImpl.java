package com.proyecto.dao;

import java.util.ArrayList;
import java.util.List;

import com.proyecto.dominio.Municipio;
import com.proyecto.dominio.Pais;
import com.proyecto.dominio.Provincia;
import com.proyecto.dominio.Region;
import com.proyecto.util.dao.DAOUtil;

/**
* Esta clase implementa la interfaz
* LugarDAO
* @author Lucas Sánchez López
* @version 2.0
*/
public class LugarDAOImpl implements LugarDAO{

//	// Continente
//	public Continente mergeContinente(Continente continente) {
//		DAOUtil daoUtil = new DAOUtil();
//		return (Continente) daoUtil.mergeObject(continente);
//	}
//	// Pais
//	public Pais mergePais(Pais pais) {
//		DAOUtil daoUtil = new DAOUtil();
//		return (Pais) daoUtil.mergeObject(pais);
//	}
//	// Region
//	public Region mergeRegion(Region region) {
//		DAOUtil daoUtil = new DAOUtil();
//		return (Region) daoUtil.mergeObject(region);
//	}
//	// Provincia
//	public Provincia mergeProvincia(Provincia provincia) {
//		DAOUtil daoUtil = new DAOUtil();
//		return (Provincia) daoUtil.mergeObject(provincia);
//	}
//	// Municipio
//	public Municipio mergeMunicipio(Municipio municipio) {
//		DAOUtil daoUtil = new DAOUtil();
//		return (Municipio) daoUtil.mergeObject(municipio);
//	}
	
	
	// Continente
//	public void deleteContinente(Continente continente) {
//		DAOUtil daoUtil = new DAOUtil();
//		daoUtil.deleteObject(continente);
//	}
	

	// Pais
//	public void deletePais(Pais pais) {
//		DAOUtil daoUtil = new DAOUtil();
//		daoUtil.deleteObject(pais);
//	}
//	
//
//	// Region
//	public void deleteRegion(Region region) {
//		DAOUtil daoUtil = new DAOUtil();
//		daoUtil.deleteObject(region);
//	}
//	// Provincia
//	public void deleteProvincia(Provincia provincia) {
//		DAOUtil daoUtil = new DAOUtil();
//		daoUtil.deleteObject(provincia);
//	}
//	// Municipio
//	public void deleteMunicipio(Municipio municipio) {
//		DAOUtil daoUtil = new DAOUtil();
//		daoUtil.deleteObject(municipio);
//	}
	

	// Continente
//	@SuppressWarnings("unchecked")
//	public List<Continente> listContinente() {
//
//		List<Continente> continenteList = new ArrayList<Continente>();
//
//		DAOUtil daoUtil = new DAOUtil();
//		continenteList = (List<Continente>) daoUtil.listFromCacheable("Continente"); // El select all sobre Continente, y cacheable a true.
//
//		return continenteList;
//	}
	
	/**
	 * Usado para listar todos los paises
	 * en la base de datos
	 * @return Lista de objetos Pais
	 */
	// Pais
	@SuppressWarnings("unchecked")
	public List<Pais> listPais() {

		List<Pais> paisList = new ArrayList<Pais>();

		DAOUtil daoUtil = new DAOUtil();
		paisList = (List<Pais>) daoUtil.listFromCacheable("Pais"); // El select all sobre Pais, y cacheable a true.

		return paisList;
	}
	
	/**
	 * Usado para listar todas la regiones
	 * en la base de datos
	 * @return Lista de objetos Region
	 */
	// Region
	@SuppressWarnings("unchecked")
	public List<Region> listRegion() {

		List<Region> regionList = new ArrayList<Region>();

		DAOUtil daoUtil = new DAOUtil();
		regionList = (List<Region>) daoUtil.listFromCacheable("Region"); // El select all sobre Region, y cacheable a true.

		return regionList;
	}
	
	/**
	 * Usado para listar todas las provincias
	 * en la base de datos
	 * @return Lista de objetos Provincia
	 */
	// Provincia
	@SuppressWarnings("unchecked")
	public List<Provincia> listProvincia() {

		List<Provincia> provinciaList = new ArrayList<Provincia>();

		DAOUtil daoUtil = new DAOUtil();
		provinciaList = (List<Provincia>) daoUtil.listFromCacheable("Provincia"); // El select all sobre Provincia, y cacheable a true.

		return provinciaList;
	}
	
	/**
	 * Usado para listar todos los municipios
	 * en la base de datos
	 * @return Lista de objetos Municipio
	 */
	// Municipio
	@SuppressWarnings("unchecked")
	public List<Municipio> listMunicipio() {

		List<Municipio> municipioList = new ArrayList<Municipio>();

		DAOUtil daoUtil = new DAOUtil();
		municipioList = (List<Municipio>) daoUtil.listFromCacheable("Municipio"); // El select all sobre Municipio, y cacheable a true.

		return municipioList;
	}
}
	
