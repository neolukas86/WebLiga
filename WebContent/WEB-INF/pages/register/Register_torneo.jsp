<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<script type="text/javascript" src="<s:url value="/javascript/mostrarOcultar.js" />" ></script>
<script type="text/javascript" src="<s:url value="/javascript/actividades.js" />" ></script>
<script type="text/javascript">

function enable_spinner(elementos){
	if(elementos.value == -1){ 
		$('#rondasExtras').spinner('enable'); 
	}
	else{
		$('#rondasExtras').spinner('disable');
	}
}


function participacion(){
	if(formulario.porEquipos.options[0].selected){
		formulario.participante.options[1].selected = true;
		formulario.participante.disabled = true;
	}
	else{
		formulario.participante.disabled = false;
		formulario.participante.options[0].selected = true;		
	}
}

function campeonato(){
	if(formulario.liga.options[0].selected){
		formulario.rondas.disabled = false;
		if(formulario.rondas.value == -1){
			$('#rondasExtras').spinner('enable');
		}
	}
	else{
		formulario.rondas.options[0].selected = true;
		formulario.rondas.disabled = true;	
		$('#rondasExtras').spinner('disable');	
	}
}

</script>

			<div class="menu_central_subheader sombra rounded-corners">
			<div class="menu_central_header_registroTorneo_imagen">
				<h3><s:text name="register.torneo"/></h3>
			</div>
			</div>
			
		<s:actionerror theme="jquery"/>
		<s:fielderror theme="jquery"/>
			
		<s:form id="formulario" name="formulario" action="RegisterTorneo" method="post" cssClass="form">
		<fieldset>
		<legend><s:text name="register.torneo"/></legend>
			<s:hidden name="idcom" value="%{#parameters.id}" />
		    <s:textfield name="nombre" required="true" key="nombre" size="20" maxlength="100" />
			<s:select name="participante" key="participante" list="listaBoolean" value="true" />
			
			<fieldset>
			    <legend><s:text name="campeonato"/></legend>
			    <table>
			    <tr>
			    <td style="padding-right:10px;"><s:select name="liga" key="tipo.campeonato" list="listaTipoTorneoBoolean" 
			    		value="true" onChange="campeonato()" /></td>			    
			    <td style="padding-right:10px;"><s:select name="porEquipos" key="tipo.participantes" 
			    		list="listaTipoParticipanteBoolean" value="false" onChange="participacion()"/></td>
			    <td style="padding-right:10px;"><s:select name="online"  key="online" list="listaBooleanOnline" /></td>
				<td style="padding-right:10px;"><s:select  id="rondas" name="rondas" key="rondas" 
						list="listaRondas" onChange="enable_spinner(this)"/></td>
				<td><sj:spinner id="rondasExtras" name="rondasExtras"
						min="3" max="20" size="2" key="rondas.extras" disabled="true" /></td>		
				</tr>
				</table>
			</fieldset>
			<fieldset>
			<legend><s:text name="fechas"/></legend>    
			<table>
			<tr>
		    <td><sj:datepicker name="fechaComienzo"  key="fecha.comienzo" firstDay="1" minDate="0y0m0d" maxDate="+2y0m0d" 
		    	changeYear="true" changeMonth="true" yearRange="-2:+2" displayFormat="dd-MM-yy"/></td>
			
		    <td><sj:datepicker name="fechaFinal" key="fecha.final" firstDay="1" minDate="0y0m0d" maxDate="+2y0m0d" 
		    	changeYear="true" changeMonth="true" yearRange="-2:+2" displayFormat="dd-MM-yy" /></td>
		    </tr>
		    </table>
			</fieldset>
			<fieldset>
			<legend><s:text name="palabra.actividad"/></legend>
			<table>
			<tr>
 			<td>
			<s:if test="listaDeportes !=null && !listaDeportes.isEmpty"> 
				<s:select id="deporte" key="palabra.deportes" list="listaDeportes" name="deporte" onChange="cambiarActividad()"/>  				
			</s:if>
			</td>
			<td>
			<s:if test="listaJuegos !=null && !listaJuegos.isEmpty()">
				<s:select id="juego" key="palabra.videojuegos" list="listaJuegos" name="juego" onChange="cambiarActividad()"/>				 
			</s:if>
			</td>					
			<td>
			<s:if test="listaCartas !=null && !listaCartas.isEmpty()">				
				<s:select id="carta" key="palabra.cartas" list="listaCartas" name="carta" onChange="cambiarActividad()"/>				 
			</s:if>
			</td>
			<td>
			<s:if test="listaJuegosdemesa !=null && !listaJuegosdemesa.isEmpty()">
				<s:select id="juegodemesa" key="palabra.juegos.mesa" list="listaJuegosdemesa" name="juegodemesa" 
				onChange="cambiarActividad()"/>
			</s:if>
			</td>
			</tr><tr>
			<td><s:if test="listaPlataformas !=null && !listaPlataformas.isEmpty()">
				<s:select id="plataforma" key="palabra.plataforma" list="listaPlataformas" name="plataforma" disabled="true"/>
			</s:if></td>
			</tr>
			</table>
			</fieldset>
			<fieldset>
			<legend><s:text name="seguridad"/></legend>
			<table>
				<tr>
				<td style="padding-right:15px;"><s:select key="password.protected" name="passwordProtected" list="listaBoolean" onchange="mostrar_ocultar_elemento_boolean(this,'joinPassword')"/></td>
			    <td><s:password id="joinPassword" name="joinPassword" key="password" size="20" maxlength="20" disabled="true" /></td>
			    </tr><tr>
				<td><s:select name="regRequest" key="confirmacion.registro" list="listaBoolean" /></td>
				<td><s:select name="estado" key="estado.registro" list="listaEstados" /></td>
				</tr>				
			</table>
			</fieldset>
			
	    				
		    <sj:submit  button="true" key="register.torneo" 
		    	cssClass="centrado" cssStyle="font-size:15px;"/>
		</fieldset>
		</s:form>
		