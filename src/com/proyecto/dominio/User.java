package com.proyecto.dominio;

import java.util.Date;
import java.util.Set;


public class User {
	private int id;
	private String alias;
	private String password;
	private String nombre;
	private String apellido;
	private String email;
	private String homepage;
	private String lenguaje;
	private Boolean sexo;
	private Date nacimiento;
	private Date fechaRegistro;
	private Boolean invitado;
	private Boolean expulsado;
	private Boolean superadministrador;
	private Pais parentPais;
	private Municipio parentMunicipio;
	private Set<UserComunidad> userComunidades;
	private Set<UserTorneo> userTorneos;
	private Set<UserEquipo> userEquipos;
	private Set<UserActividad> userActividades;
	private Set<UserPlataforma> userPlataformas;
	private Set<PartidoIndividualVersus> partidosVSLocal;
	private Set<PartidoIndividualVersus> partidosVSVisitante;
	private Set<PartidoIndividualVersus> partidosVSGanados;
	private Set<PartidoIndividualVersus> partidosVSPerdidos;
	private Set<Comunidad> comunidadesRequest;
	private Set<Torneo> torneosRequest;
	private Set<Equipo> equiposRequest;
	private Set<User> invitadosRequest;
	private Set<User> usuariosRequest;
	

	
	// Constructor público
	public User(){
	}
	public User(Integer id){
		this.id = id;
	}
	public User(String alias,String password){
		this.alias = alias;
		this.password = password;
	}
	public User(String alias,String password,
			String nombre,String apellido,
			String email,
			Boolean sexo,
			Date nacimiento,Date fechaRegistro){
		this.alias = alias;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.nacimiento = nacimiento;
		this.fechaRegistro = fechaRegistro;
	}
	
	// Métodos getters y setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public Boolean getSexo() {
		return sexo;
	}
	public void setSexo(Boolean sexo) {
		this.sexo = sexo;
	}
	public Date getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(Date value) {
		nacimiento = value;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	public Boolean getInvitado() {
		return invitado;
	}
	public void setInvitado(Boolean invitado) {
		this.invitado = invitado;
	}
	public Set<UserComunidad> getUserComunidades() {
		return userComunidades;
	}
	public void setUserComunidades(Set<UserComunidad> userComunidades) {
		this.userComunidades = userComunidades;
	}
	public Set<UserTorneo> getUserTorneos() {
		return userTorneos;
	}
	public void setUserTorneos(Set<UserTorneo> userTorneos) {
		this.userTorneos = userTorneos;
	}
	public Pais getParentPais() {
		return parentPais;
	}
	public void setParentPais(Pais parentPais) {
		this.parentPais = parentPais;
	}
	public Municipio getParentMunicipio() {
		return parentMunicipio;
	}
	public void setParentMunicipio(Municipio parentMunicipio) {
		this.parentMunicipio = parentMunicipio;
	}

	public Set<UserEquipo> getUserEquipos() {
		return userEquipos;
	}
	public void setUserEquipos(Set<UserEquipo> userEquipos) {
		this.userEquipos = userEquipos;
	}
	
	public Set<UserActividad> getUserActividades() {
		return userActividades;
	}
	public void setUserActividades(Set<UserActividad> userActividades) {
		this.userActividades = userActividades;
	}
	public Set<UserPlataforma> getUserPlataformas() {
		return userPlataformas;
	}
	public void setUserPlataformas(Set<UserPlataforma> userPlataformas) {
		this.userPlataformas = userPlataformas;
	}
	public Set<PartidoIndividualVersus> getPartidosVSLocal() {
		return partidosVSLocal;
	}
	public void setPartidosVSLocal(
			Set<PartidoIndividualVersus> partidosVSLocal) {
		this.partidosVSLocal = partidosVSLocal;
	}
	public Set<PartidoIndividualVersus> getPartidosVSVisitante() {
		return partidosVSVisitante;
	}
	public void setPartidosVSVisitante(
			Set<PartidoIndividualVersus> partidosVSVisitante) {
		this.partidosVSVisitante = partidosVSVisitante;
	}
	public Set<PartidoIndividualVersus> getPartidosVSGanados() {
		return partidosVSGanados;
	}
	public void setPartidosVSGanados(
			Set<PartidoIndividualVersus> partidosVSGanados) {
		this.partidosVSGanados = partidosVSGanados;
	}
	public Set<PartidoIndividualVersus> getPartidosVSPerdidos() {
		return partidosVSPerdidos;
	}
	public void setPartidosVSPerdidos(
			Set<PartidoIndividualVersus> partidosVSPerdidos) {
		this.partidosVSPerdidos = partidosVSPerdidos;
	}
	public String getLenguaje() {
		return lenguaje;
	}
	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}
	public Set<Comunidad> getComunidadesRequest() {
		return comunidadesRequest;
	}
	public void setComunidadesRequest(Set<Comunidad> comunidadesRequest) {
		this.comunidadesRequest = comunidadesRequest;
	}
	public Set<Torneo> getTorneosRequest() {
		return torneosRequest;
	}
	public void setTorneosRequest(Set<Torneo> torneosRequest) {
		this.torneosRequest = torneosRequest;
	}
	public Set<Equipo> getEquiposRequest() {
		return equiposRequest;
	}
	public void setEquiposRequest(Set<Equipo> equiposRequest) {
		this.equiposRequest = equiposRequest;
	}
	public Set<User> getInvitadosRequest() {
		return invitadosRequest;
	}
	public void setInvitadosRequest(Set<User> invitadosRequest) {
		this.invitadosRequest = invitadosRequest;
	}
	public Set<User> getUsuariosRequest() {
		return usuariosRequest;
	}
	public void setUsuariosRequest(Set<User> usuariosRequest) {
		this.usuariosRequest = usuariosRequest;
	}
	public Boolean getSuperadministrador() {
		return superadministrador;
	}
	public void setSuperadministrador(Boolean superadministrador) {
		this.superadministrador = superadministrador;
	}
	public Boolean getExpulsado() {
		return expulsado;
	}
	public void setExpulsado(Boolean expulsado) {
		this.expulsado = expulsado;
	}
	
}
