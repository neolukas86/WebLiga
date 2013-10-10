package com.proyecto.actions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.opensymphony.xwork2.Action;
import com.proyecto.dao.ReglaDAO;
import com.proyecto.dominio.Comunidad;
import com.proyecto.dominio.Municipio;
import com.proyecto.dominio.Pais;
import com.proyecto.dominio.Provincia;
import com.proyecto.dominio.Region;
import com.proyecto.util.hibernate.HibernateUtil;
import com.proyecto.util.listas.ListHashMapUtil;

/**
* Esta clase implementa el Action para el
* uso de datos JSON.
* @author Lucas Sánchez López
* @version 3.0
*/

public class JSONDataAction  implements SessionAware { // No aceptan nulos en los map el JSONWriter
	private Comunidad comunidad = new Comunidad();
	private Integer pais;
	private Integer provincia;
	private Integer municipio;
	private Integer region;
	private Integer actividad;
	private Integer regla;
	private Integer com;
	private LinkedHashMap<Integer,String> listaActividades = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaReglas = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaPaises = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaRegiones = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaProvincias = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaMunicipios = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaDeportes = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaJuegos = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaCartas = new LinkedHashMap<Integer,String>();
	private LinkedHashMap<Integer,String> listaJuegosdemesa = new LinkedHashMap<Integer,String>();
	private ListHashMapUtil listHashMapUtil;
	private ReglaDAO reglaDAO;
	private Map sesion;
	
	SessionFactory sessionFactory = null;
	
	@SessionTarget
	Session session = null;
	
//	Métodos getters y setters.
//	--------------------------
	
	public Integer getPais() {
		return pais;
	}

	public void setPais(Integer pais) {
		this.pais = pais;
	}

	public Comunidad getComunidad() {
		return comunidad;
	}

	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}

	public Integer getRegion() {
		return region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	public Integer getProvincia() {
		return provincia;
	}

	public void setProvincia(Integer provincia) {
		this.provincia = provincia;
	}

	public Integer getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Integer municipio) {
		this.municipio = municipio;
	}
	
	public LinkedHashMap<Integer, String> getListaPaises() {
		return listaPaises;
	}

	public void setListaPaises(LinkedHashMap<Integer, String> listaPaises) {
		this.listaPaises = listaPaises;
	}


	public LinkedHashMap<Integer, String> getListaRegiones() {
		return listaRegiones;
	}

	public void setListaRegiones(LinkedHashMap<Integer, String> listaRegiones) {
		this.listaRegiones = listaRegiones;
	}

	public LinkedHashMap<Integer, String> getListaProvincias() {
		return listaProvincias;
	}

	public void setListaProvincias(LinkedHashMap<Integer, String> listaProvincias) {
		this.listaProvincias = listaProvincias;
	}

	public LinkedHashMap<Integer, String> getListaMunicipios() {
		return listaMunicipios;
	}

	public void setListaMunicipios(LinkedHashMap<Integer, String> listaMunicipios) {
		this.listaMunicipios = listaMunicipios;
	}
	
	public Integer getActividad() {
		return actividad;
	}

	public void setActividad(Integer actividad) {
		this.actividad = actividad;
	}

	public Integer getRegla() {
		return regla;
	}

	public void setRegla(Integer regla) {
		this.regla = regla;
	}

	public LinkedHashMap<Integer, String> getListaActividades() {
		return listaActividades;
	}

	public void setListaActividades(LinkedHashMap<Integer, String> listaActividades) {
		this.listaActividades = listaActividades;
	}

	public LinkedHashMap<Integer, String> getListaReglas() {
		return listaReglas;
	}

	public void setListaReglas(LinkedHashMap<Integer, String> listaReglas) {
		this.listaReglas = listaReglas;
	}
	
	public Integer getCom() {
		return com;
	}

	public void setCom(Integer com) {
		this.com = com;
	}
	
	public LinkedHashMap<Integer, String> getListaDeportes() {
		return listaDeportes;
	}

	public void setListaDeportes(LinkedHashMap<Integer, String> listaDeportes) {
		this.listaDeportes = listaDeportes;
	}

	public LinkedHashMap<Integer, String> getListaJuegos() {
		return listaJuegos;
	}

	public void setListaJuegos(LinkedHashMap<Integer, String> listaJuegos) {
		this.listaJuegos = listaJuegos;
	}

	public LinkedHashMap<Integer, String> getListaCartas() {
		return listaCartas;
	}

	public void setListaCartas(LinkedHashMap<Integer, String> listaCartas) {
		this.listaCartas = listaCartas;
	}

	public LinkedHashMap<Integer, String> getListaJuegosdemesa() {
		return listaJuegosdemesa;
	}

	public void setListaJuegosdemesa(
			LinkedHashMap<Integer, String> listaJuegosdemesa) {
		this.listaJuegosdemesa = listaJuegosdemesa;
	}
	
//	-------------------------------------------------
//	-------------------------------------------------



	/**
	 * Lista los paises  y los mete en un HashMap
	 * @return SUCCESS
	 */
	public String listarPaises(){
//		sesion = getSesion();
		listHashMapUtil = new ListHashMapUtil();
		listaPaises.put(0," ");
		listHashMapUtil.SacarPaises(sesion, null, listaPaises);
	
		return Action.SUCCESS;
	}
	
	/**
	 * Lista las regiones de un pais
	 * @return SUCCESS en caso de éxito, INPUT en caso de no completarse, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String listarRegionesByPais(){
		boolean error=false;
		boolean exito=false;

		try{
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			try{				
				
				
				System.out.println("(Dentro de listarRegiones) pais --> "+pais);
				
				if(pais != null && pais != 0){

					Pais pa =(Pais)session.load(Pais.class,(Serializable) pais);
					
					listaRegiones.put(0, " ");
					
					Set<Region> regionSet = pa.getRegiones();
					
					Iterator<Region> it = regionSet.iterator();
					while(it.hasNext()){
						Region reg = it.next();
						
						listaRegiones.put(reg.getId(),reg.getNombre());
					}				
				}
				
				exito=true;
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return Action.ERROR;
					}
				}
				return Action.ERROR;
			}
			else{
				if(exito == true){					
					return Action.SUCCESS;
				}
				else{
					return Action.INPUT;
				}
			}
		}		
	}
	
	/**
	 * Lista las provincias de una region
	 * @return SUCCESS en caso de éxito, INPUT en caso de no completarse, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String listarProvinciasByRegion(){
		boolean error=false;
		boolean exito=false;

		try{
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			try{				
				
				
				System.out.println("(Dentro de listarProvinciasByRegion) region --> "+region);
				
				if(region != null && region != 0){

					Region reg =(Region)session.load(Region.class,(Serializable) region);
					
					listaProvincias.put(0, " ");
					
					Set<Provincia> provinciaSet = reg.getProvincias();									
					
					Iterator<Provincia> itP = provinciaSet.iterator();
					while(itP.hasNext()){
						Provincia pr = itP.next();
					
						listaProvincias.put(pr.getId(),pr.getNombre());
					}
				}
				
				exito=true;
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return Action.ERROR;
					}
				}
				return Action.ERROR;
			}
			else{
				if(exito == true){					
					return Action.SUCCESS;
				}
				else{
					return Action.INPUT;
				}
			}
		}		
	}
	
	
	/**
	 * Lista las provincias de un pais
	 * @return SUCCESS en caso de éxito, INPUT en caso de no completarse, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String listarProvinciasByPais(){
		boolean error=false;
		boolean exito=false;

		try{
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			try{				
				
				
				System.out.println("(Dentro de listarProvincias) pais --> "+pais);
				
				if(pais != null && pais != 0){

					Pais pa =(Pais)session.load(Pais.class,(Serializable) pais);
					
					listaProvincias.put(0, " ");
					
					Set<Region> regionSet = pa.getRegiones();
					List<Provincia> provinciaList = new ArrayList<Provincia>();
					
					Iterator<Region> it = regionSet.iterator();
					while(it.hasNext()){
						provinciaList.addAll(it.next().getProvincias());
					}				
					

					Map<Integer,String> mapa = new HashMap<Integer,String>();
					Iterator<Provincia> itP = provinciaList.iterator();
					while(itP.hasNext()){
						Provincia pr = itP.next();
					
						mapa.put(pr.getId(),pr.getNombre());
					}
					
					
					
					List<Integer> misMapKeys = new ArrayList<Integer>(mapa.keySet());
					List<String> misMapValues = new ArrayList<String>(mapa.values());
					TreeSet<String> conjuntoOrdenado = new TreeSet<String>(misMapValues);
					Object[] arrayOrdenado = conjuntoOrdenado.toArray();
					int size = arrayOrdenado.length;
					for (int i=0; i<size; i++) {
						listaProvincias.put(misMapKeys.get(misMapValues.indexOf(arrayOrdenado[i])),(String) arrayOrdenado[i]);
						System.out.println("listaProvincias...   ID --> "+misMapKeys.get(misMapValues.indexOf(arrayOrdenado[i]))+
								",  Provincia --> "+arrayOrdenado[i]);
					}
					
				}
				
				exito=true;
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return Action.ERROR;
					}
				}
				return Action.ERROR;
			}
			else{
				if(exito == true){					
					return Action.SUCCESS;
				}
				else{
					return Action.INPUT;
				}
			}
		}		
	}
	
	/**
	 * Lista los municipios de una provincia
	 * @return SUCCESS en caso de éxito, INPUT en caso de no completarse, y ERROR si ocurre algún fallo
	 */
	public String listarMunicipiosByProvincia(){
		return listarMunicipiosByProvinciaConNull(false);
	}
	
	/**
	 * Lista los municipios de una provincia
	 * @return SUCCESS en caso de éxito, INPUT en caso de no completarse, y ERROR si ocurre algún fallo
	 */
	public String listarMunicipiosByProvinciaNull(){
		return listarMunicipiosByProvinciaConNull(true);
	}
	
	/**
	 * Lista los municipios de una provincia
	 * @return SUCCESS en caso de éxito, INPUT en caso de no completarse, y ERROR si ocurre algún fallo
	 */
	@SuppressWarnings("finally")
	public String listarMunicipiosByProvinciaConNull(boolean conNull){
		boolean error=false;
		boolean exito=false;
	
		try{
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			try{

				System.out.println("(Dentro de listarMunicipios) provincia --> "+provincia);
				if(provincia != null && provincia != 0){
					
					Provincia pr =(Provincia)session.load(Provincia.class,(Serializable) provincia);
					
					if(conNull){
						listaMunicipios.put(0, " ");	
					}
					
					Set<Municipio> municipioSet = pr.getMunicipios();
					
					Iterator<Municipio> it = municipioSet.iterator();
					while(it.hasNext()){
						Municipio mu = it.next();
						
						listaMunicipios.put(mu.getId(),mu.getNombre());
					}
				}

				exito=true;
			}
			catch(Exception e){
				e.printStackTrace();
				error = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			error = true;
		}
		finally{
			if(error){
				if(session != null){
					try{
						session.close();
					}
					catch(HibernateException he){
						// Informar de error haciendo cierre de sesion
						he.printStackTrace();
						session.close();
						return Action.ERROR;
					}
				}
				return Action.ERROR;
			}
			else{
				if(exito == true){					
					return Action.SUCCESS;
				}
				else{
					return Action.INPUT;
				}
			}
		}
	}

	
//	
	//Agregado al implementar SessionAware
	@Override
	public void setSession(Map s) {
	sesion = s;
	}
	
	public Map getSession() {

	return sesion;

	}

}
