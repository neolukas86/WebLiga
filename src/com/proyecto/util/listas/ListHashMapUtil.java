package com.proyecto.util.listas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import com.proyecto.dao.ActividadDAO;
import com.proyecto.dao.ActividadDAOImpl;
import com.proyecto.dao.EquipoDAO;
import com.proyecto.dao.EquipoDAOImpl;
import com.proyecto.dao.LugarDAO;
import com.proyecto.dao.LugarDAOImpl;
import com.proyecto.dao.PlataformaDAO;
import com.proyecto.dao.PlataformaDAOImpl;
import com.proyecto.dao.ReglaDAO;
import com.proyecto.dao.ReglaDAOImpl;
import com.proyecto.dao.UserDAO;
import com.proyecto.dao.UserDAOImpl;
import com.proyecto.dominio.Actividad;
import com.proyecto.dominio.Equipo;
import com.proyecto.dominio.Pais;
import com.proyecto.dominio.Plataforma;
import com.proyecto.dominio.Regla;
import com.proyecto.dominio.Torneo;
import com.proyecto.dominio.User;
import com.proyecto.util.properties.GlobalResourceUtil;

/**
 * Clase de utilidades para principalmente sacar HashMaps
 * a partir de listas de objetos para los formularios
 * @author Lucas Sánchez López
 * @version 2.0
 *
 */
public class ListHashMapUtil {
	private ActividadDAO actividadDAO;
	private PlataformaDAO plataformaDAO;
	private LugarDAO lugarDAO;
	private ReglaDAO reglaDAO;
	private UserDAO userDAO;
	private EquipoDAO equipoDAO;
	
	/**
	 * Rellena una HashMap de actividades
	 * @param lista Lista de actividades
	 * @param hashMap HashMap para ser rellenado
	 * @param lenguaje Cadena de caracteres con el lenguaje correspondiente
	 */
	public void FillLinkedHashMapActividadesFromList(List<Actividad> lista,LinkedHashMap<Integer,String> hashMap, String lenguaje){
		Iterator<Actividad> it = lista.iterator();
		
		if(lenguaje.equals("es")){
			while(it.hasNext()){
				Actividad a = it.next();
				hashMap.put(a.getId(),a.getNombre());
			}
		}
		else if(lenguaje.equals("en")){
				while(it.hasNext()){
					Actividad a = it.next();
					if(a.getNombre_EN() != null){
						hashMap.put(a.getId(),a.getNombre_EN());
					}
					else{
						hashMap.put(a.getId(),a.getNombre());	
					}				
				}
		}
			else if(lenguaje.equals("ca")){
				while(it.hasNext()){
					Actividad a = it.next();
					if(a.getNombre_CA() != null){
						hashMap.put(a.getId(),a.getNombre_CA());
					}
					else{
						hashMap.put(a.getId(),a.getNombre());	
					}				
				}
			}				
	}
	
	/**
	 * Rellena una HashMap de paises
	 * @param lista Lista de paises
	 * @param hashMap HashMap para ser rellenado
	 * @param lenguaje Cadena de caracteres con el lenguaje correspondiente
	 */
	public void FillLinkedHashMapPaisesFromList(List<Pais> lista,LinkedHashMap<Integer,String> hashMap, String lenguaje){
		Iterator<Pais> it = lista.iterator();
		
		if(lenguaje.equals("es")){
			while(it.hasNext()){
				Pais p = it.next();
				hashMap.put(p.getId(),p.getNombre());
			}
		}
		else if(lenguaje.equals("en")){
				while(it.hasNext()){
					Pais p = it.next();					
					if(p.getNombre_EN() != null){
						hashMap.put(p.getId(),p.getNombre_EN());
					}
					else{
						hashMap.put(p.getId(),p.getNombre());	
					}				
				}
		}
			else if(lenguaje.equals("ca")){
				while(it.hasNext()){
					Pais p= it.next();
					if(p.getNombre_CA() != null){
						hashMap.put(p.getId(),p.getNombre_CA());
					}
					else{
						hashMap.put(p.getId(),p.getNombre());	
					}				
				}
			}				
	}
	
	/**
	 * Comprueba el lenguaje en uso
	 * @param rb Objeto ResourceBundle
	 * @return Cadena con el lenguaje en uso
	 */
	public String SacarLenguaje(ResourceBundle rb){
		Locale locale = rb.getLocale();
		
		if(locale != null){
			return locale.getLanguage();	
		}
		else{
			return "es";
		}		
	}
	
	/**
	 * Rellena unos HashMap de deportes, cartas, juegos de mesa y videojuegos
	 * @param sesion Objeto Map con la sesión
	 * @param header Cadena con el texto de cabecera
	 * @param listaDeportes HashMap de deportes a rellenar
	 * @param listaCartas HashMap de cartas a rellenar
	 * @param listaJuegosdemesa HashMap de juegos de mesa a rellenar
	 * @param listaJuegos HashMap de videojuegos a rellenar
	 * @param actividadList Lista de actividades
	 */
	public void SacarActividadesFull(Map<?,?> sesion,String header, LinkedHashMap<Integer,String> listaDeportes,
			LinkedHashMap<Integer,String> listaCartas,LinkedHashMap<Integer,String> listaJuegosdemesa,
			LinkedHashMap<Integer,String> listaJuegos, List<Actividad> actividadList){
		
		Iterator<Actividad> itList = actividadList.iterator();

		List<Actividad> actividadDeportesList = new ArrayList<Actividad>();
		List<Actividad> actividadJuegoList = new ArrayList<Actividad>();
		List<Actividad> actividadMesaList = new ArrayList<Actividad>();
		List<Actividad> actividadCartasList = new ArrayList<Actividad>();
		
		while(itList.hasNext()){
			Actividad act = itList.next();
			Integer valor = act.getParentTipo().getId();
			
			switch(valor){
				case 1:
					actividadDeportesList.add(act);
					break;
				case 2:
					actividadJuegoList.add(act);
					break;
				case 3:
					actividadMesaList.add(act);
					break;
				case 4:
					actividadCartasList.add(act);
					break;
				default:break;
			}
		}
		
		ResourceBundle rb = null;
		
		if(header != null && !header.equals("")){
			// Cargamos el archivo global de properties
			rb = new GlobalResourceUtil().loadResourceBundle(sesion);
			
			if(!header.equals(" ")){			
				header = rb.getString(header);
			}
			if(!actividadDeportesList.isEmpty()){
				listaDeportes.put(null, header);
			}
			if(!actividadCartasList.isEmpty()){
				listaCartas.put(null, header);
			}
			if(!actividadMesaList.isEmpty()){
				listaJuegosdemesa.put(null, header);
			}
			if(!actividadJuegoList.isEmpty()){
				listaJuegos.put(null, header);
			}
		}
		
		if(rb == null){
			// Cargamos el archivo global de properties
			rb = new GlobalResourceUtil().loadResourceBundle(sesion);
		}
		
		String lenguaje = SacarLenguaje(rb);

		/* Ordenamos las listas */
		Collections.sort(actividadJuegoList,new ActividadNombreComparator()); // Tienen el mismo nombre da igual el idioma
		
		if(lenguaje.equals("es")){
			Collections.sort(actividadDeportesList,new ActividadNombreComparator());
			Collections.sort(actividadCartasList,new ActividadNombreComparator());
			Collections.sort(actividadMesaList,new ActividadNombreComparator());	
		}
		if(lenguaje.equals("en")){
			Collections.sort(actividadDeportesList,new ActividadNombreENComparator());
			Collections.sort(actividadCartasList,new ActividadNombreENComparator());
			Collections.sort(actividadMesaList,new ActividadNombreENComparator());	
		}
		if(lenguaje.equals("ca")){
			Collections.sort(actividadDeportesList,new ActividadNombreCAComparator());
			Collections.sort(actividadCartasList,new ActividadNombreCAComparator());
			Collections.sort(actividadMesaList,new ActividadNombreCAComparator());	
		}
		
		//----------------------//
		
		FillLinkedHashMapActividadesFromList(actividadDeportesList,listaDeportes,lenguaje);
		FillLinkedHashMapActividadesFromList(actividadCartasList,listaCartas,lenguaje);
		FillLinkedHashMapActividadesFromList(actividadJuegoList,listaJuegos,lenguaje);
		FillLinkedHashMapActividadesFromList(actividadMesaList,listaJuegosdemesa,lenguaje);
		
	}
	
	/**
	 * Rellena un HashMap de plataformas
	 * @param sesion Objeto Map con la sesión
	 * @param header Cadena con el texto de cabecera
	 * @param listaPlataformas HashMap a rellenar
	 * @param plataformaList Lista de plataformas
	 */
	public void SacarPlataformasFull(Map<?,?> sesion,String header, 
			LinkedHashMap<Integer,String> listaPlataformas,List<Plataforma> plataformaList){
		
		if(header != null && !header.equals("")){
			if(!header.equals(" ")){
				// Cargamos el archivo global de properties
				ResourceBundle rb = new GlobalResourceUtil().loadResourceBundle(sesion);
				
				header = rb.getString(header);
			}
			
			listaPlataformas.put(null, header);
		}
		
		/* Ordenamos la lista */
		Collections.sort(plataformaList,new PlataformaComparator());
		//----------------------//
		
		Iterator<Plataforma> itP = plataformaList.iterator();
		while(itP.hasNext()){
			Plataforma p = itP.next();
			listaPlataformas.put(p.getId(),p.getNombre());
		}		
	}
	
	/**
	 * Rellena un HashMap de actividades
	 * @param sesion Objeto Map con la sesión
	 * @param header Cadena de caracteres con el texto de cabecera
	 * @param listaActividades HashMap a rellenar
	 * @param actividadList Lista de actividades
	 */
	public void SacarActividadesFullList(Map<?,?> sesion,String header, LinkedHashMap<Integer,String> listaActividades,
			List<Actividad> actividadList){
		ResourceBundle rb = null;
		
		if(header != null && !header.equals("")){
			// Cargamos el archivo global de properties
			rb = new GlobalResourceUtil().loadResourceBundle(sesion);
			
			if(!header.equals(" ")){	
				header = rb.getString(header);
			}			
			listaActividades.put(null, header);
		}

		if(rb == null){
			// Cargamos el archivo global de properties
			rb = new GlobalResourceUtil().loadResourceBundle(sesion);
		}
		
		/* Ordenamos la lista */
		Collections.sort(actividadList,new ActividadComparator());
		
		//----------------------//
		
		FillLinkedHashMapActividadesFromList(actividadList,listaActividades,SacarLenguaje(rb));
		
	}
	
	/**
	 * Rellena unos HashMap de deportes, cartas, juegos de mesa y videojuegos
	 * @param sesion Objeto Map con la sesión
	 * @param header Cadena con el texto de cabecera
	 * @param listaDeportes HashMap de deportes a rellenar
	 * @param listaCartas HashMap de cartas a rellenar
	 * @param listaJuegosdemesa HashMap de juegos de mesa a rellenar
	 * @param listaJuegos HashMap de videojuegos a rellenar
	 */
	public void SacarActividades(Map<?,?> sesion,String header, LinkedHashMap<Integer,String> listaDeportes,
			LinkedHashMap<Integer,String> listaCartas,LinkedHashMap<Integer,String> listaJuegosdemesa,
			LinkedHashMap<Integer,String> listaJuegos){
		
		actividadDAO = new ActividadDAOImpl();
		List<Actividad> actividadList = actividadDAO.listActividad(); // Listamos las actividades
		
		SacarActividadesFull(sesion,header,listaDeportes,listaCartas,listaJuegosdemesa,listaJuegos,actividadList);
	}
	
	/**
	 * Rellena un HashMap de actividades
	 * @param sesion Objeto Map con la sesión
	 * @param header Cadena de caracteres con el texto de cabecera
	 * @param listaActividades HashMap a rellenar
	 */
	public void SacarActividades(Map<?,?> sesion,String header, LinkedHashMap<Integer,String> listaActividades){
		actividadDAO = new ActividadDAOImpl();
		List<Actividad> actividadList = actividadDAO.listActividad(); // Listamos las actividades
		
		SacarActividadesFullList(sesion,header,listaActividades,actividadList);
		
	}
	
	/**
	 * Rellena un HashMap de lenguajes
	 * @param sesion Objeto Map con la sesión
	 * @param listaLenguajes Lista de lenguajes
	 */
	public void SacarLenguajes(Map<?,?> sesion,LinkedHashMap<String,String> listaLenguajes){
		ResourceBundle rb = new GlobalResourceUtil().loadResourceBundle(sesion);
		
		listaLenguajes.put("es", rb.getString("language.español"));
		listaLenguajes.put("en", rb.getString("language.ingles"));
		listaLenguajes.put("ca", rb.getString("language.catalan"));
		
	}
	
	/**
	 * Rellena un HashMap de plataformas
	 * @param sesion Objeto Map con la sesión
	 * @param header Cadena de caracteres con el texto de cabecera
	 * @param listaPlataformas HashMap a rellenar
	 */
	public void SacarPlataformas(Map<?,?> sesion,String header,LinkedHashMap<Integer,String> listaPlataformas){
		List<Plataforma> plataformaList = new ArrayList<Plataforma>();
		
		plataformaDAO = new PlataformaDAOImpl();
		plataformaList.addAll(plataformaDAO.listPlataforma());
		
		if(header != null && !header.equals("")){
			if(!header.equals(" ")){
				// Cargamos el archivo global de properties
				ResourceBundle rb = new GlobalResourceUtil().loadResourceBundle(sesion);
				
				header = rb.getString(header);
			}
			
			listaPlataformas.put(null, header);
		}
		
		/* Ordenamos la lista */
		Collections.sort(plataformaList,new PlataformaComparator());
		//----------------------//
		
		Iterator<Plataforma> itP = plataformaList.iterator();
		while(itP.hasNext()){
			Plataforma p = itP.next();
			listaPlataformas.put(p.getId(),p.getNombre());
		}
	}
	
	/**
	 * Rellena un HashMap de paises
	 * @param sesion Objeto Map con la sesión
	 * @param header Cadena de caracteres con el texto de cabecera
	 * @param listaPaises HashMap a rellenar
	 */
	public void SacarPaises(Map<?,?> sesion,String header,LinkedHashMap<Integer,String> listaPaises){
		List<Pais> paisList = new ArrayList<Pais>();
		
		lugarDAO = new LugarDAOImpl();
		paisList.addAll(lugarDAO.listPais());
		
		ResourceBundle rb = null;
	
		if(header != null && !header.equals("")){
			// Cargamos el archivo global de properties
			rb = new GlobalResourceUtil().loadResourceBundle(sesion);
			
			if(!header.equals(" ")){

				header = rb.getString(header);
			}
			listaPaises.put(null,header);
		}

		if(rb == null){
			// Cargamos el archivo global de properties
			rb = new GlobalResourceUtil().loadResourceBundle(sesion);
		}
		FillLinkedHashMapPaisesFromList(paisList,listaPaises,SacarLenguaje(rb));
		
	}	
	
	/**
	 * Rellena un HashMap de rondas de un torneo
	 * @param sesion Objeto Map con la sesión
	 * @param header Cadena de caracteres con el texto de cabecera
	 * @param listaRondas HashMap a rellenar
	 */
	public void SacarRondas(Map<?,?> sesion, String header, LinkedHashMap<Integer,String> listaRondas){
		// Cargamos el archivo global de properties
		ResourceBundle rb = new GlobalResourceUtil().loadResourceBundle(sesion);		
		
		if(header != null && !header.equals("")){
			if(!header.equals(" ")){
				header = rb.getString(header);
			}
			listaRondas.put(null,header);
		}
		
 		listaRondas.put(1,rb.getString("rondas.ida"));
 		listaRondas.put(2,rb.getString("rondas.ida.vuelta"));
 		listaRondas.put(-1,rb.getString("rondas.extras"));
	}
	
	/**
	 * Rellena un HashMap de estados de un torneo
	 * @param sesion Objeto Map con la sesión
	 * @param header Cadena de caracteres con el texto de cabecera
	 * @param listaEstados HashMap a rellenar
	 */
	public void SacarEstados(Map<?,?> sesion, String header, LinkedHashMap<Integer,String> listaEstados ){
		// Cargamos el archivo global de properties
		ResourceBundle rb = new GlobalResourceUtil().loadResourceBundle(sesion);		
		
		if(header != null && !header.equals("")){
			if(!header.equals(" ")){
				header = rb.getString(header);
			}
			listaEstados.put(null,header);
		}
		
 		listaEstados.put(1,rb.getString("registro.abierto"));
 		listaEstados.put(0,rb.getString("registro.cerrado"));	
	}
	
	/**
	 * Rellena un HashMap de sexos de un usuario
	 * @param sesion Objeto Map con la sesión
	 * @param header Cadena de caracteres con el texto de cabecera
	 * @param listaSexo HashMap a rellenar
	 */
	public void SacarSexos(Map<?,?> sesion, String header, LinkedHashMap<Integer,String> listaSexo){
		SacarBooleanInteger(sesion,header,listaSexo,"sexo.masculino","sexo.femenino");
	}

	/**
	 * Rellena un HashMap de tipos de torneo
	 * @param sesion Objeto Map con la sesión
	 * @param header Cadena de caracteres con el texto de cabecera
	 * @param listaTipoTorneo HashMap a rellenar
	 */
	public void SacarTipoTorneo(Map<?,?> sesion, String header, LinkedHashMap<Integer,String> listaTipoTorneo){
		SacarBooleanInteger(sesion,header,listaTipoTorneo,"liga","copa");
	}
	
	/**
	 * Rellena un HashMap de tipos de torneo
	 * @param sesion Objeto Map con la sesión
	 * @param listaTipoTorneo HashMap a rellenar
	 */
	public void SacarTipoTorneo(Map<?,?> sesion, LinkedHashMap<Boolean,String> listaTipoTorneo){
		SacarBoolean(sesion,listaTipoTorneo,"liga","copa");
	}	
	
	/**
	 * Rellena un HashMap de tipos de participante en un torneo
	 * @param sesion Objeto Map con la sesión
	 * @param header Cadena caracteres con el texto de cabecera
	 * @param listaTipoParticipante HashMap a rellenar
	 */
	public void SacarTipoParticipante(Map<?,?> sesion, String header, LinkedHashMap<Integer,String> listaTipoParticipante){
		SacarBooleanInteger(sesion,header,listaTipoParticipante,"palabra.equipos","individual");
	}	
	
	/**
	 * Rellena un HashMap de tipos de participante en un torneo
	 * @param sesion Objeto Map con la sesión
	 * @param listaTipoParticipante HashMap a rellenar
	 */
	public void SacarTipoParticipante(Map<?,?> sesion, LinkedHashMap<Boolean,String> listaTipoParticipante){
		SacarBoolean(sesion,listaTipoParticipante,"palabra.equipos","individual");
	}
	
	/**
	 * Rellena un HashMap con los rangos para una comunidad
	 * @param sesion Objeto Map con la sesión
	 * @param rango Cadena de caracteres con el rango del usuario
	 * @param listaRangos HashMap a rellenar
	 */
	public void SacarRangosComunidad(Map<?,?> sesion,String rango,LinkedHashMap<Integer,String> listaRangos){
 		// Cargamos el archivo global de properties
 		ResourceBundle rb = new GlobalResourceUtil().loadResourceBundle(sesion);
 		
 		if(rango!=null){
 			if(rango.equals("creador")){
 				listaRangos.put(1, rb.getString("rango.admin"));
 				listaRangos.put(2, rb.getString("rango.mod"));
 				listaRangos.put(3, rb.getString("rango.poweruser"));
 				listaRangos.put(4, rb.getString("rango.normaluser"));
 			}
 			else if(rango.equals("admin")){
 					listaRangos.put(2, rb.getString("rango.mod"));
 					listaRangos.put(3, rb.getString("rango.poweruser"));
 					listaRangos.put(4, rb.getString("rango.normaluser"));
 				}
 				else if(rango.equals("mod")){
 						listaRangos.put(3, rb.getString("rango.poweruser"));
 						listaRangos.put(4, rb.getString("rango.normaluser"));
 					}
 		}
	}
	
	/**
	 * Rellena un HashMap con los rangos para un torneo
	 * @param sesion Objeto Map con la sesión
	 * @param rango Cadena de caracteres con el rango del usuario
	 * @param listaRangos HashMap a rellenar
	 */
	public void SacarRangosTorneo(Map<?,?> sesion,String rango,LinkedHashMap<Integer,String> listaRangos){
 		// Cargamos el archivo global de properties
 		ResourceBundle rb = new GlobalResourceUtil().loadResourceBundle(sesion);
 		
 		if(rango!=null){
 			if(rango.equals("creador")){
 				listaRangos.put(1, rb.getString("rango.admin"));
 				listaRangos.put(2, rb.getString("rango.mod"));
 				listaRangos.put(4, rb.getString("rango.normaluser"));
 			}
 			else if(rango.equals("admin")){
 					listaRangos.put(2, rb.getString("rango.mod"));
 					listaRangos.put(4, rb.getString("rango.normaluser"));
 				}
 		}
	}	
	
	/**
	 * Rellena un HashMap con un "si" y un "no" según el idioma
	 * @param sesion Objeto Map con la sesión
	 * @param listaBoolean HashMap a rellenar
	 */
	public void SacarBool(Map<?,?> sesion, LinkedHashMap<Boolean,String> listaBoolean){
		SacarBoolean(sesion,listaBoolean,"yes","no");
	}
	
	/**
	 * Rellena un HashMap con un "si" y un "no" según el idioma
	 * @param sesion Objeto Map con la sesión
	 * @param header Cadena de caracteres con el texto de la cabecera
	 * @param listaBooleanInteger HashMap a rellenar
	 */
	public void SacarBool(Map<?,?> sesion,String header, LinkedHashMap<Integer,String> listaBooleanInteger){
		SacarBooleanInteger(sesion,header,listaBooleanInteger,"yes","no");
	}
	
	/**
	 * Rellena un HashMap con un "si" y un "no" según el idioma
	 * @param sesion Objeto Map con la sesión
	 * @param header Cadena de caracteres con el texto de la cabecera
	 * @param lista HashMap a rellenar
	 * @param strTrue cadena afirmativa
	 * @param strFalse cadena negativa
	 */
	public void SacarBooleanInteger(Map<?,?> sesion,String header, LinkedHashMap<Integer,String> lista, String strTrue, String strFalse){
 		// Cargamos el archivo global de properties
 		ResourceBundle rb = new GlobalResourceUtil().loadResourceBundle(sesion);

 		// Ojo con lo del null !!
 		
 		if(header != null && !header.equals("")){
 			lista.put(null,rb.getString(header));
 		}
 		else{
 			lista.put(null, " ");
 		}
 		
 		lista.put(1,rb.getString(strTrue));
 		lista.put(2,rb.getString(strFalse));	
	}
	
	/**
	 * Rellena un HashMap con un "si" y un "no" según el idioma
	 * @param sesion Objeto Map con la sesión
	 * @param listaBoolean HashMap a rellenar
	 * @param strTrue cadena afirmativa
	 * @param strFalse cadena negativa
	 */
	public void SacarBoolean(Map<?,?> sesion, LinkedHashMap<Boolean,String> listaBoolean, String strTrue, String strFalse){
 		// Cargamos el archivo global de properties
 		ResourceBundle rb = new GlobalResourceUtil().loadResourceBundle(sesion);
 		
 		if(strTrue != null){
 			listaBoolean.put(true,rb.getString(strTrue));	
 		}
 		if(strFalse != null){
 			listaBoolean.put(false,rb.getString(strFalse));	
 		}
	}
	
	/**
	 * Rellena un HashMap con la lista de jornada jugables
	 * @param sesion Objeto Map con la sesión
	 * @param header Cadena de caracteres con el texto de la cabecera
	 * @param lista HashMap a rellenar
	 * @param numero Número de jornadas totales del campeonato
	 */
	public void SacarJornadasJugables(Map<?,?> sesion,String header, LinkedHashMap<Integer,String> lista, Integer numero){
 		// Cargamos el archivo global de properties
 		ResourceBundle rb = new GlobalResourceUtil().loadResourceBundle(sesion);

 		// Ojo con lo del null que se lo pasa por Antequera!!
 		
 		if(header != null && !header.equals("")){
 			lista.put(null,rb.getString(header));
 		}
 		else{
 			lista.put(null, " ");
 		}

 		for(int i=1;i<numero;i++){
 			lista.put(i, Integer.toString(i));
 		}	
	}
	
	/**
	 * Rellena un HashMap con las opciones de selección 
	 * para mostrar en las estadísticas de un torneo
	 * @param sesion Objeto Map con la sesión
	 * @param listaEstadisticas HashMap a rellenar
	 */
	public void SacarEstadisticasTorneo(Map<?,?> sesion, LinkedHashMap<String,String> listaEstadisticas){
 		// Cargamos el archivo global de properties
 		ResourceBundle rb = new GlobalResourceUtil().loadResourceBundle(sesion);
 		
 		listaEstadisticas.put("partidosConfirmados",rb.getString("partidos.confirmados"));
 		listaEstadisticas.put("tantos",rb.getString("tantos"));			
	}
	
	/**
	 * Rellena un HashMap con las opciones de selección 
	 * para mostrar en las estadísticas de un usuario en un torneo
	 * @param sesion Objeto Map con la sesión
	 * @param listaEstadisticas HashMap a rellenar
	 */
	public void SacarEstadisticasUsuarioTorneo(Map<?,?> sesion, LinkedHashMap<String,String> listaEstadisticas){
 		// Cargamos el archivo global de properties
 		ResourceBundle rb = new GlobalResourceUtil().loadResourceBundle(sesion);
 		
 		listaEstadisticas.put("partidos",rb.getString("partidos"));
 		listaEstadisticas.put("tantos",rb.getString("tantos"));			
	}
	
	/**
	 * Rellena un HashMap con las reglas de un torneo
	 * @param sesion Objeto Map con la sesión
	 * @param listaReglas HashMap a rellenar
	 * @param act Actividad del torneo
	 */
	public void SacarReglasTorneo(Map<?,?> sesion, LinkedHashMap<Integer,String> listaReglas, Actividad act){
		reglaDAO = new ReglaDAOImpl();
		List<Regla> lista;
		Iterator<Regla> it;
		Integer ptosVict = act.getPtosVictoria();
		
		if(ptosVict != null){
			lista = reglaDAO.listReglaConPuntos();			
		}
		else{
			lista = reglaDAO.listReglaSinPuntos();
		}
		
		it = lista.iterator();
		
		while(it.hasNext()){
			Regla reg = it.next();
			
			listaReglas.put(reg.getId(), reg.getNombre());
		}
	}
	
	/**
	 * Rellena un HashMap con los invitados de un torneo
	 * @param sesion Objeto Map con la sesión
	 * @param listaInvitados HashMap a rellenar
	 * @param tor Torneo
	 */
	public void SacarInvitadosTorneo(Map<?,?> sesion, LinkedHashMap<Integer,String> listaInvitados, Torneo tor){
		if(!tor.getPorEquipos()){
			userDAO = new UserDAOImpl();		
			
			List<User> lista = userDAO.listInvitadosByTorneo(tor);		
			
			Iterator<User> it = lista.iterator();
			
			while(it.hasNext()){
				User us = it.next();
				
				listaInvitados.put(us.getId(), us.getAlias());
			}	
		}
		else{
			equipoDAO = new EquipoDAOImpl();
			
			List<Equipo> lista = equipoDAO.listInvitadosByTorneo(tor);
			
			Iterator<Equipo> it = lista.iterator();
			
			while(it.hasNext()){
				Equipo eq = it.next();
				
				listaInvitados.put(eq.getId(), eq.getNombre());
			}
		}
		
	}
	
	/**
	 * Rellena un HashMap con los nombres de las rondas de una copa
	 * @param sesion Objeto Map con la sesión
	 * @param listaNumeros HashMap a rellenar
	 * @param participantes Número de participantes
	 * @param jorActual Número de jornada actual del campeonato
	 */
	public void SacarNumeros(Map<?,?> sesion, LinkedHashMap<Integer,String> listaNumeros, Integer participantes, Integer jorActual){		
		// Cargamos el archivo global de properties
 		ResourceBundle rb = new GlobalResourceUtil().loadResourceBundle(sesion);
		
		if(participantes > 32){
			listaNumeros.put(1, rb.getString("treintaidosavos.final"));
			if(jorActual >= 2){
				listaNumeros.put(2, rb.getString("dieciseisavos.final"));
				
				if(jorActual >=3){
					listaNumeros.put(3, rb.getString("octavos.final"));
					
					if(jorActual >=4){
						listaNumeros.put(4, rb.getString("cuartos.final"));
						
						if(jorActual >=5){
							listaNumeros.put(5, rb.getString("semis.final"));
							
							if(jorActual >=6){
								listaNumeros.put(6, rb.getString("final.final"));
							}
						}
					}
				}				
			}
		}
		
		else if(participantes > 16){
			listaNumeros.put(1, rb.getString("dieciseisavos.final"));
			if(jorActual >= 2){
				listaNumeros.put(2, rb.getString("octavos.final"));
				
				if(jorActual >=3){
					listaNumeros.put(3, rb.getString("cuartos.final"));
					
					if(jorActual >=4){
						listaNumeros.put(4, rb.getString("semis.final"));
						
						if(jorActual >=5){
							listaNumeros.put(5, rb.getString("final.final"));
						}
					}
				}				
			}
		}
		
		else if(participantes > 8){
			listaNumeros.put(1, rb.getString("octavos.final"));
			if(jorActual >= 2){
				listaNumeros.put(2, rb.getString("cuartos.final"));
				
				if(jorActual >=3){
					listaNumeros.put(3, rb.getString("semis.final"));
					
					if(jorActual >=4){
						listaNumeros.put(4, rb.getString("final.final"));
					}
				}				
			}
		}
		
		else if(participantes > 4){
			listaNumeros.put(1, rb.getString("cuartos.final"));
			if(jorActual >= 2){
				listaNumeros.put(2, rb.getString("semis.final"));
				
				if(jorActual >=3){
					listaNumeros.put(3, rb.getString("final.final"));					
				}				
			}
		}
		
		else if(participantes > 2){
			listaNumeros.put(1, rb.getString("semis.final"));
			if(jorActual >= 2){
				listaNumeros.put(2, rb.getString("final.final"));
			}
		}
		
		else if(participantes <=2){
			listaNumeros.put(1, rb.getString("final.final"));
		}
		
		
	}

}
