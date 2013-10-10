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

</script>
			
		<s:actionerror theme="jquery"/>
		<s:fielderror theme="jquery"/>
	
	<s:if test="sorteado == false">		
		<s:form name="formulario" action="ModificarSettingsTorneo" method="post" cssClass="form">
		<fieldset>
		<legend><s:text name="change.torneo.properties"/></legend>
			<s:hidden name="id" value="%{id}" />
			
			<fieldset>
			    <legend><s:text name="campeonato"/></legend>
			    <table>
			    <tr>
<%-- 			    <td style="padding-right:10px;"><s:select name="liga" key="tipo.campeonato" list="listaTipoTorneoBoolean" value="%{liga}"/></td> --%>
<%-- 			    <td style="padding-right:10px;"><s:select name="porEquipos" key="tipo.participantes" list="listaTipoParticipanteBoolean" value="%{porEquipos}"/></td> --%>
			    <td style="padding-right:10px;"><s:select name="online"  key="online" list="listaBooleanOnline" value="%{online}"/></td>
				<td style="padding-right:10px;"><s:select  id="rondas" name="rondas" key="rondas" list="listaRondas" onChange="enable_spinner(this)" value="%{rondas}"/></td>
				<td><sj:spinner id="rondasExtras" name="rondasExtras"
						min="3" max="20" size="2" key="rondas.extras" disabled="true" value="%{rondas}"/></td>		
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
	    				
		    <sj:submit  button="true" key="modify" 
		    	cssClass="centrado" cssStyle="font-size:15px;"/>
		</fieldset>
		</s:form>
	</s:if>
	<s:else>
		<s:form action="CambiarJornadasJugables" method="post" cssClass="form">
		<fieldset>
		<legend><s:text name="change.torneo.properties"/></legend>
			<s:hidden name="id" value="%{id}" />
			<s:select name="jornadasJugables" list="listaJornadasJugables" key="jornadas.jugables" value="1"/>
			<sj:submit  button="true" key="modify" 
		    	cssClass="centrado" cssStyle="font-size:15px;"/>
		</fieldset>
		</s:form>
	</s:else>		
