package com.proyecto.util.sorteo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;
import java.util.Queue;

import java.lang.Math;

import com.proyecto.dominio.Equipo;
import com.proyecto.dominio.PartidoEquiposVersus;
import com.proyecto.dominio.PartidoIndividualVersus;
import com.proyecto.dominio.Torneo;
import com.proyecto.dominio.User;

/**
 * Clase de utilidades para realizar los diferentes sorteos
 * de calendarios de torneo
 * @author Lucas Sánchez López
 * @version 2.0
 */
public class SorteoUtil {

	/**
	 * Realiza el sorteo de una ronda de un campeonato de formato copa
	 * @param listEquipos Lista de participantes
	 * @param torneo Torneo sobre el que realizar el sorteo
	 * @return Lista de partidos
	 */
	public static List<?> sorteoCopa(List<Object> listEquipos, Torneo torneo){
		int rondas = torneo.getRondas();
		int jornada = torneo.getJornadaActual();
		
		Collections.shuffle(listEquipos); // Removemos un poquito la lista de equipos ;)
		
		int numEquipos = listEquipos.size();
		int numExcluidos = 0;
		
		List<Object> listPartido = new ArrayList<Object>();
		
		double logaritmo = Math.log(numEquipos)/Math.log(2);
		
		if(logaritmo % (int)logaritmo != 0){ // Si hay decimales
			numExcluidos = (int) (Math.pow(2, (int)logaritmo+1) - numEquipos);			
		}
		
		System.out.println("----- Número de excluidos -> "+numExcluidos+", Número de equipos total -> "+numEquipos);
		
		for(int i=0; i < (numEquipos-numExcluidos)/2; i++){
			if(!torneo.getPorEquipos()){
				for(int ronda=1; ronda <= rondas; ronda++){
					if(ronda % 2 != 0){ // Es la ida
						listPartido.add(new PartidoIndividualVersus
								(jornada,(User)listEquipos.get(i+numExcluidos),(User)listEquipos.get(numEquipos-i -1),
								torneo));
					}
					else{ // Es la vuelta
						listPartido.add(new PartidoIndividualVersus
								(jornada,(User)listEquipos.get(numEquipos-i -1),(User)listEquipos.get(i+numExcluidos),
								torneo));
					}
				}
			}
			else{ // Si es por equipos
				for(int ronda=1; ronda <= rondas; ronda++){
					if(ronda % 2 != 0){ // Es la ida
						listPartido.add(new PartidoEquiposVersus
								(jornada,(Equipo) listEquipos.get(i+numExcluidos),(Equipo)listEquipos.get(numEquipos-i -1),
								torneo));
					}
					else{ // Es la vuelta
						listPartido.add(new PartidoEquiposVersus
								(jornada,(Equipo) listEquipos.get(numEquipos-i -1),(Equipo)listEquipos.get(i+numExcluidos),
								torneo));
					}
				}
			}
		}
		
		return listPartido;
	}
	
	/**
	 * Realiza el sorteo de calendario de un torneo de formato liga
	 * @param listEquipos Lista de participantes
	 * @param torneo Torneo sobre el que realizar el sorteo
	 * @return Lista de partidos
	 */
	public static List<?> sorteoLiga(List<Object> listEquipos, Torneo torneo){
		
		int rondas = torneo.getRondas();
		int numero = listEquipos.size(); // Tamaño de la lista de Equipos en el sorteo
		
		int tamTotal = numero; // Tamaño de la cola, el numero de equipos, y uno más si son impares

		int num_partidos = numero/2;
		int num_jornadas = numero -1;
		int FICTICIO = 0;
		int entero = FICTICIO;
		Boolean individual = torneo.getPorEquipos();
		
		List<Object> listPartido = new ArrayList<Object>();

		Collections.shuffle(listEquipos); // Removemos un poquito la lista de equipos ;)

		if(numero % 2 != 0) // Si numero de equipos impar añadiremos un equipo ficticio
		{
			/** Veremos si esto funciona, no se yo **/
			if(individual){
				User us = new User();
				
				us.setId(FICTICIO);
				listEquipos.add(us);
			}
			else{
				Equipo eq = new Equipo();
				
				eq.setId(FICTICIO);
				listEquipos.add(eq);
			}
			
			num_partidos = (numero+1)/2;
			num_jornadas = numero;
			
			tamTotal++; // Le sumamos uno más porque añadimos el ficticio
		}
		
		// PRIMERA JORNADA
		// ----------------
		System.out.println("------------ PRIMERA JORNADA -----------");
		System.out.println("------------=================-----------");
		
		for(int i=0; i< num_partidos; i++)
		{
			if(!((numero % 2 !=0) && (i==0))){ // Si no es el primer partido habiendo número impar de equipos, ya que estará el ficticio
				if(individual){
//					PartidoIndividualVersus partido = new PartidoIndividualVersus();
					
					for(int ronda=1;ronda <= rondas;ronda++){
						
						if(ronda % 2 != 0){ // Es la ida
							listPartido.add(new PartidoIndividualVersus(1+(num_jornadas*(ronda-1)),
													(User)listEquipos.get(i),(User)listEquipos.get(tamTotal-1-i),
													torneo));
							System.out.println("Jornada -->"+(1+(num_jornadas*(ronda-1))));
						}

						else{ // Es la vuelta
							if(numero!=4){
								listPartido.add(new PartidoIndividualVersus(1+(num_jornadas*(ronda-1)),
														(User)listEquipos.get(tamTotal-1-i),(User)listEquipos.get(i),
														torneo));
								System.out.println("Jornada -->"+(1+(num_jornadas*(ronda-1))));
							}
							else{ // Orden inverso, será la ultima en la segunda vuelta
								listPartido.add(new PartidoIndividualVersus(num_jornadas*(ronda-1)+num_jornadas,
														(User)listEquipos.get(tamTotal-1-i),(User)listEquipos.get(i),
														torneo));
								System.out.println("Jornada -->"+(num_jornadas*(ronda-1)+num_jornadas));
							}
						}
					}
				}
				else{
//					PartidoEquiposVersus partido = new PartidoEquiposVersus();
					
					for(int ronda=1;ronda <= rondas;ronda++){
						if(ronda % 2 != 0){ // Es la ida
							listPartido.add(new PartidoEquiposVersus(1+(num_jornadas*(ronda-1)),
													(Equipo)listEquipos.get(i),(Equipo)listEquipos.get(tamTotal-1-i),
													torneo));
						}
					
						else{ // Es la vuelta
							if(numero!=4){
								listPartido.add(new PartidoEquiposVersus(1+(num_jornadas*(ronda-1)),
														(Equipo)listEquipos.get(tamTotal-1-i),(Equipo)listEquipos.get(i),
														torneo));
							}
							else{ // Orden inverso, será la ultima en la segunda vuelta
								listPartido.add(new PartidoEquiposVersus((num_jornadas*(ronda-1))+num_jornadas,
														(Equipo)listEquipos.get(tamTotal-1-i),(Equipo)listEquipos.get(i),
														torneo));
							}						
						}
					}					
				}
			}
		}
		
		System.out.println("=======================");
		
		// COLA PARA LAS ROTACIONES DE EQUIPOS
		// -----------------------------------
		
		Queue<User> q1User = null;
		Queue<Equipo> q1Equipo = null;
		
		User userComodin = null;
		Equipo equipoComodin = null;
		
		if(individual){
			q1User = new LinkedList<User>();
				
			for(int i=tamTotal - 1; i > tamTotal/2 -1; i--)   q1User.add((User)listEquipos.get(i));
			for(int i=tamTotal/2 -2; i >=0; i--)			 	q1User.add((User)listEquipos.get(i));
				
			userComodin = (User)listEquipos.get(tamTotal/2 - 1);
		}
		else{
			q1Equipo = new LinkedList<Equipo>();
				 
			for(int i=tamTotal - 1; i > tamTotal/2 -1; i--)  	q1Equipo.add((Equipo)listEquipos.get(i));
			for(int i=tamTotal/2 -2; i >=0; i--)			  	q1Equipo.add((Equipo)listEquipos.get(i));
				
			equipoComodin = (Equipo)listEquipos.get(tamTotal/2 - 1);
		}
		
		
		List<User> listUserQ = null;  // Pasamos la cola a una lista para poder recuperar datos agusto
		List<Equipo> listEquipoQ = null;
		
		if(individual){ // Hago esto para no andar guardando en memoria el doble
			listUserQ = new ArrayList<User>(q1User);
		}
		else{
			listEquipoQ = new ArrayList<Equipo>(q1Equipo);
		}
		
		// --------- Pruebas ---------------------

//		User usuario = listUserQ.get(1);
			
//		System.out.println("Id -> "+usuario.getId()+"  Alias -> "+usuario.getAlias());
//		
//		System.out.println("Alias del comodin -> "+userComodin.getAlias());

		// ---------------------------------------

		
		// JORNADAS SIGUIENTES
		// --------------------
		// Si el número de equipos es 4 el algoritmo normal no nos vale
		// por eso habrá comprobaciones de tamTotal != 4 y numero!=4 tal
		// el orden de la segunda vuelta en ese caso será inverso
		// como en la liguilla de Champions
		for(int jornada=2; jornada <= num_jornadas; jornada++)
		{		
			if(jornada %2 != 0) // Si la jornada es impar, ya que cambia la cola en la jornada par previa
			{	
				if(individual){
					listUserQ.addAll(q1User);
				}
				else{
					listEquipoQ.addAll(q1Equipo);
				}
			}
				
			for(int j=0; j < num_partidos; j++)
			{			
				User userRival = null;
				User userContendiente = null;
					
				Equipo equipoRival = null;
				Equipo equipoContendiente = null;
					
				if(individual){
					userRival = listUserQ.get(j); // Cogemos el equipo "local" par, "visitante" impar
					if(numero % 2 != 0){ // Si los equipos son impares
						entero = userRival.getId();
					}
				}
				else{
					equipoRival = listEquipoQ.get(j); // Cogemos el equipo "local" par, "visitante" impar
					if(numero % 2 != 0){ // Si los equipos son impares
						entero = equipoRival.getId();
					}
				}
				
				// Si es el partido del comodin para las pares o impares
				if(((j == 0) && (jornada % 2 == 0)) ||((j == num_partidos-1) && (jornada % 2 != 0)))
				{
					if((numero % 2 == 0) || entero!=FICTICIO) // Si son pares o distinto de Ficticio
					{
						
						// Si es jornada penultima y num de equipos distinto de 4(o 3) o 
						// 			jornada impar distinta de la ultima sin que sea la jornada 3 de una liga de equipos impares mayores que 3 o
						//			última jornada con num de equipos igual a 4(o 3) o
						//			jornada 4 de liga de equipos impares mayores que 5
						if((jornada == num_jornadas -1 && tamTotal != 4) ||
								(jornada % 2 != 0 && jornada != num_jornadas && !(numero % 2 !=0 && numero > 3 && jornada == 3)) || 
								(jornada == num_jornadas && tamTotal == 4) ||
								(numero % 2 != 0 && numero > 5 && jornada == 4))
						{
							if(individual){
//								PartidoIndividualVersus partido = new PartidoIndividualVersus();
								
								for(int ronda=1;ronda <= rondas;ronda++){									
									if(ronda % 2 != 0){ // Es la ida
										listPartido.add(new PartidoIndividualVersus(jornada+(num_jornadas*(ronda-1)),
																userComodin,userRival,
																torneo));
									}
								
									else{ // Es la vuelta
										if(numero!=4){
											listPartido.add(new PartidoIndividualVersus(jornada+(num_jornadas*(ronda-1)),
													userRival,userComodin,
													torneo));											
										}
										else{
											listPartido.add(new PartidoIndividualVersus(num_jornadas*(ronda-1)+num_jornadas+1 - jornada,
													userRival,userComodin,
													torneo));
										}
										
									}
								}
							}
							else{
//								PartidoEquiposVersus partido = new PartidoEquiposVersus();
								
								for(int ronda=1;ronda <= rondas;ronda++){
									if(ronda % 2 != 0){ // Es la ida								
										listPartido.add(new PartidoEquiposVersus(jornada+(num_jornadas*(ronda-1)),
																equipoComodin,equipoRival,
																torneo));
									}
								
									else{ // Es la vuelta
										if(numero!=4){
											listPartido.add(new PartidoEquiposVersus(jornada+(num_jornadas*(ronda-1)),
													equipoRival,equipoComodin,
													torneo));											
										}
										else{
											listPartido.add(new PartidoEquiposVersus(num_jornadas*(ronda-1)+num_jornadas+1 - jornada,
													equipoRival,equipoComodin,
													torneo));
										}

									}
								}								
							}
						}
							
						else // Si no es 
						{
							if(individual){
//								PartidoIndividualVersus partido = new PartidoIndividualVersus();
								
								for(int ronda=1;ronda <= rondas;ronda++){
									if(ronda % 2 != 0){ // Es la ida								
										listPartido.add(new PartidoIndividualVersus(jornada+(num_jornadas*(ronda-1)),
																userRival,userComodin,
																torneo));
									}
									else{ // Es la vuelta
										if(numero!=4){
											listPartido.add(new PartidoIndividualVersus(jornada+(num_jornadas*(ronda-1)),
																	userComodin,userRival,
																	torneo));
										}
										else{ // Orden inverso la vuelta si los equipos son 4
											listPartido.add(new PartidoIndividualVersus((num_jornadas*(ronda-1))+num_jornadas+1 - jornada,
																	userComodin,userRival,
																	torneo));
										}							
									}
								}
							}
							else{
//								PartidoEquiposVersus partido = new PartidoEquiposVersus();
								
								for(int ronda=1;ronda <= rondas;ronda++){
									if(ronda % 2 != 0){ // Es la ida								
										listPartido.add(new PartidoEquiposVersus(jornada+(num_jornadas*(ronda-1)),
																equipoRival,equipoComodin,
																torneo));
									}
								
									else{ // Es la vuelta
										if(numero!=4){
											listPartido.add(new PartidoEquiposVersus(jornada+(num_jornadas*(ronda-1)),
																	equipoComodin,equipoRival,
																	torneo));
										}
										else{ // Orden inverso la vuelta si los equipos son 4
											listPartido.add(new PartidoEquiposVersus((num_jornadas*(ronda-1))+num_jornadas+1 - jornada,
																	equipoComodin,equipoRival,
																	torneo));
										}									
									}
								}								
							}
						}
					}		
				}
					
				else // Si no es el partido del comodín
				{
					if((numero % 2 == 0) || entero != FICTICIO){ // Lo de ficticio solo tendrá sentido cuando los equipos sean impares
						if(individual){
							if(jornada % 2 == 0){
								userContendiente = listUserQ.get(tamTotal-1 - j); // Hay que restar siempre uno pq el comodin no está en la cola
							}
							else{
								userContendiente = listUserQ.get(tamTotal-1 - j - 1); // Para las jornadas impares
							}
							if(numero % 2 != 0){ // Si el número de equipos es impar comprueba que el otro rival no sea tp ficticio
								entero = userContendiente.getId();
							}

							if((numero % 2 == 0) || entero != FICTICIO){
//								PartidoIndividualVersus partido = new PartidoIndividualVersus();
							
								if((numero % 2 != 0 && numero > 5 && jornada == num_jornadas && j == num_partidos - 3) ||
										(numero % 2 != 0 && numero == 7 && jornada == 5 && j == 0) ||
										(jornada %2 == 0 && !(numero % 2 != 0 && numero == 7 && jornada == 6 && j == 1)))
								{ 
									for(int ronda=1;ronda <= rondas;ronda++){
										if(ronda % 2 != 0){ // Es la ida	
											listPartido.add(new PartidoIndividualVersus(jornada+(num_jornadas*(ronda-1)),
																	userRival,userContendiente,
																	torneo));
										}
									
										else{ // Es la vuelta
											if(numero!=4){
												listPartido.add(new PartidoIndividualVersus(jornada+(num_jornadas*(ronda-1)),
																		userContendiente,userRival,
																		torneo));
											}
											else{
												listPartido.add(new PartidoIndividualVersus((num_jornadas*(ronda-1))+num_jornadas+1 - jornada,
																		userContendiente,userRival,
																		torneo));
											}
										}
									}
								}
								else{ 
									for(int ronda=1;ronda <= rondas;ronda++){
										if(ronda % 2 != 0){ // Es la ida									
											listPartido.add(new PartidoIndividualVersus(jornada+(num_jornadas*(ronda-1)),
																	userContendiente,userRival,
																	torneo));
										}
										else{ // Es la vuelta
											if(numero!=4){
												listPartido.add(new PartidoIndividualVersus(jornada+(num_jornadas*(ronda-1)),
																		userRival,userContendiente,
																		torneo));
											}
											else{ // Orden inverso la vuelta si los equipos son 4(o 3)
												listPartido.add(new PartidoIndividualVersus((num_jornadas*(ronda-1))+num_jornadas+1 - jornada,
																		userRival,userContendiente,
																		torneo));
											}
										}
									}
								}
							}
						}
								
						else{
							if(jornada % 2 == 0){
								equipoContendiente = listEquipoQ.get(tamTotal-1 - j); // Hay que restar siempre uno pq el comodin no está en la cola
							}
							else{
								equipoContendiente = listEquipoQ.get(tamTotal-1 - j - 1); // Para las jornadas impares
							}							
							if(numero % 2 != 0){ // Si el número de equipos es impar comprueba que el otro rival no sea tp ficticio
								entero = equipoContendiente.getId();
							}

							if((numero % 2 == 0) || entero != FICTICIO){
//								PartidoEquiposVersus partido = new PartidoEquiposVersus();
							
								if((numero % 2 != 0 && numero > 5 && jornada == num_jornadas && j == num_partidos - 3) ||
										(numero % 2 != 0 && numero == 7 && jornada == 5 && j == 0) ||
										(jornada %2 == 0 && !(numero % 2 != 0 && numero == 7 && jornada == 6 && j == 1)))
								{
									for(int ronda=1;ronda <= rondas;ronda++){
										if(ronda % 2 != 0){ // Es la ida	
											listPartido.add(new PartidoEquiposVersus(jornada+(num_jornadas*(ronda-1)),
																	equipoRival,equipoContendiente,
																	torneo));
										}
									
										else{
											if(numero!=4){
												listPartido.add(new PartidoEquiposVersus(jornada+(num_jornadas*(ronda-1)),
																		equipoContendiente,equipoRival,
																		torneo));
											}
											else{ // Orden inverso la vuelta si los equipos son 4(o 3)
												listPartido.add(new PartidoEquiposVersus((num_jornadas*(ronda-1))+num_jornadas+1 - jornada,
																		equipoContendiente,equipoRival,
																		torneo));
											}										
										}
									}									
								}
								else{
									for(int ronda=1;ronda <= rondas;ronda++){
										if(ronda % 2 != 0){ // Es la ida									
											listPartido.add(new PartidoEquiposVersus(jornada+(num_jornadas*(ronda-1)),
																	equipoContendiente,equipoRival,
																	torneo));
										}
									
										else{
											if(numero!=4){
												listPartido.add(new PartidoEquiposVersus(jornada+(num_jornadas*(ronda-1)),
																		equipoRival,equipoContendiente,
																		torneo));
											}
											else{ // Orden inverso la vuelta si los equipos son 4(o 3)
												listPartido.add(new PartidoEquiposVersus((num_jornadas*(ronda-1))+num_jornadas+1 - jornada,
																		equipoRival,equipoContendiente,
																		torneo));
											}										
										}
									}									
								}			
							}
						}
					}
				}
			}
				
			if(jornada % 2 == 0){ // Si la jornada es par hacemos pop y luego push a la cola
				if(individual){
					User us = q1User.remove();
					q1User.add(us);
					listUserQ.clear();
				}
				else{
					Equipo eq = q1Equipo.remove();
					q1Equipo.add(eq);
					listEquipoQ.clear();
				}
			}
		}

	return listPartido;
}
	
}
