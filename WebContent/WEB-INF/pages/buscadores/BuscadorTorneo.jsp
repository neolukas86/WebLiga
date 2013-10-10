<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link href="<s:url value="/images/iconos/web/favicon.png" />" rel="shortcut icon"  />
<link href="<s:url value="/css/style.css" />" rel="stylesheet" type="text/css" />
<link href="<s:url value="/css/displaytag.css" />" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]>
	<link href="<s:url value="/css/explorer8.css" />" rel="stylesheet" type="text/css" />
<![endif]--> 
<sj:head locale="%{#request.locale}" jqueryui="true" jquerytheme="redmond"/>

<title><s:text name="buscar.torneo"/> - <s:text name="title" /></title>

</head>

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


<body onLoad="disable_spinner()">
<div class="main">
  <jsp:include page="/WEB-INF/pages/include/Header.jsp" />

  <div class="clr"></div>
  <div class="body">
    <div class="body_resize">
    	<jsp:include page="/WEB-INF/pages/include/Menu.jsp" />
	    <div class="menu_central">
	    <div class="menu_central_header degradado sombra rounded-corners">
	    <div class="menu_central_header_buscarTorneo_imagen">
			<h3><s:text name="buscar.torneo"/></h3>
		</div>
		</div>
	    <div class="menu_central_body sombra rounded-corners">


	<s:form id="formulario" action="SearchTorneo" method="post" cssClass="form">
	<fieldset>
	<legend><s:text name="buscar.torneo"/></legend>
	    <s:textfield name="nombre" key="nombre" size="20" maxlength="100" />
	    <fieldset>
	    <legend><s:text name="campeonato"/></legend>
	    <table>
	    <tr>
	    <td style="padding-right:10px;"><s:select name="VarLiga" key="tipo.campeonato" list="listaTipoTorneoInteger" /></td>
	    <td style="padding-right:10px;"><s:select  name="VarPorEquipos" key="tipo.participantes" list="listaTipoParticipanteInteger" /></td>
	    <td style="padding-right:10px;"><s:select name="VarOnline" key="online" list="listaBooleanInteger" /></td>
		<td style="padding-right:10px;"><s:select  id="rondas" name="rondas" key="rondas" list="listaRondas"  
			onChange="enable_spinner(this)"/></td>
		<td><sj:spinner id="rondasExtras" name="rondasExtras"
				min="3" max="20" size="2" key="rondas.extras" disabled="true" /></td>		
		</tr>
		</table>
		</fieldset>
		<fieldset>
		<legend><s:text name="fechas"/></legend>
		<table>
		<tr>
		<td><sj:datepicker name="fechaComienzo" key="fecha.comienzo" firstDay="1" minDate="0y0m0d" changeYear="true" changeMonth="true"
	    		yearRange="-2:+2" displayFormat="dd-MM-yy" /></td>
	    <td><sj:datepicker name="fechaFinal" key="fecha.final" firstDay="1" minDate="0y0m0d" changeYear="true" changeMonth="true"
	    		yearRange="-2:+2" displayFormat="dd-MM-yy"/></td>
	    </tr>
	    </table>
		</fieldset>
		
	<!-- 	Tenemos que poner menos,exacto,más para rondas y antes,exacto,después para las fechas  -->
	
		<fieldset>
		    <legend><s:text name="palabra.actividad"/></legend>
		    <table>
		    <tr>
			<td><s:select id="deporte" key="palabra.deportes" list="listaDeportes" name="deporte" onChange="cambiarActividad()"/></td>
		
			<td><s:select id="juego" key="palabra.videojuegos" list="listaJuegos" name="juego" onChange="cambiarActividad()"/></td>
			
						
			<td><s:select id="carta" key="palabra.cartas" list="listaCartas" name="carta" onChange="cambiarActividad()"/></td>

			<td><s:select id="juegodemesa" key="palabra.juegos.mesa" list="listaJuegosdemesa" name="juegodemesa" onChange="cambiarActividad()"/></td>
			</tr>
			</table>
			</fieldset>
			
			<fieldset>
			<legend><s:text name="palabra.plataforma"/></legend>
			<s:select id="plataforma" key="palabra.plataforma" list="listaPlataformas" name="plataforma" />
			</fieldset>
			
			<fieldset>
			<legend><s:text name="seguridad"/></legend>
			<table>
			<tr>
			<td><s:select name="tab" key="password.protected" list="listaBooleanInteger"/></td>
			<td><s:select name="subTab" key="confirmacion.registro" list="listaBooleanInteger" /></td>
			</tr>
			</table>
			</fieldset>
	</fieldset>
	</s:form>


	
<sj:a
	id="ajaxformlinkBuscadorTorneo"
	formIds="formulario"
	targets="resultTorneos"
	indicator="indicator"
	button="true"
	buttonIcon="ui-icon-gear"
	cssStyle="margin-left:10px;"
>
<s:text name="buscar"/>
</sj:a>

<div id="resultTorneos" class="result ui-widget-content ui-corner-all" style="margin:0px 10px; padding-bottom:10px">
</div>

	</div>
		</div>
	</div>
  </div>
 
  <div class="clr"></div>
  <jsp:include page="/WEB-INF/pages/include/Footer.jsp" />
</div>
</body>
</html>